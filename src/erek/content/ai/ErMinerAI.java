package erek.content.ai;

import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.ai.BlockIndexer;
import mindustry.ai.types.MinerAI;
import arc.*;
import arc.func.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.Units.*;
import mindustry.game.EventType.*;
import mindustry.game.*;
import mindustry.game.Teams.*;
import mindustry.gen.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class ErMinerAI extends MinerAI{
    public boolean mining = true;
    public Item targetItem;
    public Tile ore;
    private int quadWidth, quadHeight;
    /** Stores all ore quadrants on the map. Maps ID to qX to qY to a list of tiles with that ore. */
    private IntSeq[][][] ores, wallOres;
    /** Stores all damaged tile entities by team. */
    private Seq<Building>[] damagedTiles = new Seq[Team.all.length];
    /** All ores present on the map - can be wall or floor. */
    private Seq<Item> allPresentOres = new Seq<>();
    /** All ores available on this map. */
    private ObjectIntMap<Item> allOres = new ObjectIntMap<>(), allWallOres = new ObjectIntMap<>();
    /** Stores teams that are present here as tiles. */
    private Seq<Team> activeTeams = new Seq<>(Team.class);
    /** Maps teams to a map of flagged tiles by flag. */
    private Seq<Building>[][] flagMap = new Seq[Team.all.length][BlockFlag.all.length];
    /** Counts whether a certain floor is present in the world upon load. */
    private boolean[] blocksPresent;
    /** Array used for returning and reusing. */
    private Seq<Building> breturnArray = new Seq<>(Building.class);
    /** Maps block flag to a list of floor tiles that have it. */
    private Seq<Tile>[] floorMap;
    

    public Tile findClosestOre(float xp, float yp, Item item){
        if(ores[item.id] != null){
            float minDst = 0f;
            Tile closest = null;
            for(int qx = 0; qx < quadWidth; qx++){
                for(int qy = 0; qy < quadHeight; qy++){
                    var arr = ores[item.id][qx][qy];
                    if(arr != null && arr.size > 0){
                        Tile tile = world.tile(arr.first());
                        if(tile.block() == Blocks.air){
                            float dst = Mathf.dst2(xp, yp, tile.worldx(), tile.worldy());
                            if(closest == null || dst < minDst){
                                closest = tile;
                                minDst = dst;
                            }
                        }
                    }
                }
            }
            return closest;
        }

        return null;
    }

    /** Find the closest ore wall relative to a position. */
    public Tile findClosestWallOre(float xp, float yp, Item item){
        //(stolen from foo's client :))))
        if(wallOres[item.id] != null){
            float minDst = 0f;
            Tile closest = null;
            for(int qx = 0; qx < quadWidth; qx++){
                for(int qy = 0; qy < quadHeight; qy++){
                    var arr = wallOres[item.id][qx][qy];
                    if(arr != null && arr.size > 0){
                        Tile tile = world.tile(arr.first());
                        if(tile.block() != Blocks.air){
                            float dst = Mathf.dst2(xp, yp, tile.worldx(), tile.worldy());
                            if(closest == null || dst < minDst){
                                closest = tile;
                                minDst = dst;
                            }
                        }
                    }
                }
            }
            return closest;
        }

        return null;
    }

    /** Find the closest ore block relative to a position. */
    public Tile findClosestOre(Unit unit, Item item){
        return findClosestOre(unit.x, unit.y, item);
    }

    /** Find the closest ore block relative to a position. */
    public Tile findClosestWallOre(Unit unit, Item item){
        return findClosestWallOre(unit.x, unit.y, item);
    }

    @Override
    public void updateMovement(){
        Building core = unit.closestCore();

        if(!unit.canMine() || core == null) return;

        if(!unit.validMine(unit.mineTile)){
            unit.mineTile(null);
        }

        if(mining){
            if(timer.get(timerTarget2, 60 * 4) || targetItem == null){
                targetItem = unit.type.mineItems.min(i -> indexer.hasOre(i) && unit.canMine(i), i -> core.items.get(i));
            }

            //core full of the target item, do nothing
            if(targetItem != null && core.acceptStack(targetItem, 1, unit) == 0){
                unit.clearItem();
                unit.mineTile = null;
                return;
            }

            //if inventory is full, drop it off.
            if(unit.stack.amount >= unit.type.itemCapacity || (targetItem != null && !unit.acceptsItem(targetItem))){
                mining = false;
            }else{
                if(timer.get(timerTarget3, 60) && targetItem != null){
                    ore = null;
                    if(unit.type.mineWalls = true) ore = findClosestWallOre(unit, targetItem);
                    if(ore == null && unit.type.mineFloor) ore = findClosestOre(unit, targetItem);
                }

                if(ore != null){
                    moveTo(ore, unit.type.mineRange / 2f, 20f);

                    if(unit.within(ore, unit.type.mineRange) && unit.validMine(ore)){
                        unit.mineTile = ore;
                    }
                }
            }
        }else{
            unit.mineTile = null;

            if(unit.stack.amount == 0){
                mining = true;
                return;
            }

            if(unit.within(core, unit.type.range)){
                if(core.acceptStack(unit.stack.item, unit.stack.amount, unit) > 0){
                    Call.transferItemTo(unit, unit.stack.item, unit.stack.amount, unit.x, unit.y, core);
                }

                unit.clearItem();
                mining = true;
            }

            circle(core, unit.type.range / 1.8f);
        }
    }
}