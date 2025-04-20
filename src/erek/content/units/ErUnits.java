package erek.content.units;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import erek.content.ai.ErMinerAI;
import mindustry.ai.*;
import mindustry.ai.types.*;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class ErUnits{

    public static ErUnitType wisp, nanite;

    public static void load(){

        	wisp = new ErUnitType("wisp"){{
			aiController = RepairAI::new;
			defaultCommand = UnitCommand.repairCommand;
			constructor = EntityMapping.map(3);
            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 0f;
                y = 6.5f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.6f;
                repairSpeed = 3f;
                fractionRepairSpeed = 0.06f;
                aimDst = 0f;
                shootCone = 15f;
                mirror = false;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Pal.accent;
                healColor = Pal.accent;

                bullet = new BulletType(){{
                    maxRange = 100f;
                }};
			}});
            deathExplosionEffect = new Effect(30f, e -> {
                // Visual effect
                Fx.scatheLight.at(e.x, e.y);
        
                // Apply splash damage
                Damage.damage(e.x, e.y, 60f, 5f); // radius, damage
            });
			armor = 0;
			hitSize = 8f;
			flying = true;
			drag = 0.06F;
            faceTarget = true;
			accel = 0.12F;
			itemCapacity = 15;
			speed = 1.8F;
			health = 300.0F;
			engineSize = 1.4F;
			engineOffset = 5.7F;
			range = 60.0F;
			isEnemy = false;
			lowAltitude = true;
            mineTier = 0;
		};
    };
        nanite = new ErUnitType("nanite"){{
			aiController = MinerAI::new;
			defaultCommand = UnitCommand.mineCommand;
			constructor = EntityMapping.map(3);
            weapons.add(new RepairBeamWeapon(){{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 0f;
                y = 6.5f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.6f;
                repairSpeed = 3f;
                fractionRepairSpeed = 0.06f;
                aimDst = 0f;
                shootCone = 15f;
                mirror = false;

                targetUnits = false;
                targetBuildings = true;
                autoTarget = false;
                controllable = true;
                laserColor = Pal.accent;
                healColor = Pal.accent;

                bullet = new BulletType(){{
                    maxRange = 60f;
                }};
			}});
			armor = 1;
			hitSize = 14f;
			flying = true;
			drag = 0.06F;
			accel = 0.12F;
			itemCapacity = 40;
			speed = 1.6F;
			health = 800.0F;
			engineSize = 3.4F;
			engineOffset = 9.2F;
			range = 80.0F;
			isEnemy = false;
			mineTier = 3;
			mineSpeed = 6F;
            mineWalls = true;
            mineFloor = true;
			lowAltitude = true;
			
			mineItems.addAll(Items.beryllium, Items.graphite);
    };
};
}
}