package erek.content.blocks;

import erek.content.*;
import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.UnitTypes;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.payloads.PayloadConveyor;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.blocks.units.UnitFactory.UnitPlan;
import erek.content.units.*;

import static erek.Vars.tick;
import static mindustry.content.Items.graphite;
import static mindustry.content.Items.silicon;
import static mindustry.type.ItemStack.with;

public class ErUnitBlocks {
    public static Block supportFactory, supportRefabricator;

    public static void load() {
        supportFactory = new UnitFactory("support-factory") {
            {
                requirements(Category.units, with(Items.tungsten, 80, Items.silicon, 250, Items.graphite, 350));
                size = 3; health = 700;
                configurable = false;
                fogRadius = 3;
                consumePower(2f);
                regionSuffix = "-dark";
                plans.add(new UnitPlan(ErUnits.wisp, 60f * 35f, with(Items.graphite, 50, Items.silicon, 80)));
        }};
        supportRefabricator = new Reconstructor("support-refabricator") {
            {
                requirements(Category.units, with(Items.tungsten, 100, Items.silicon, 200, Items.graphite, 200, Items.oxide, 60));
                regionSuffix = "-dark";

                size = 3;
                consumePower(3f);
                consumeLiquid(Liquids.hydrogen, 3f / 60f);
                consumeItems(with(Items.silicon, 70, Items.tungsten, 50));

                constructTime = 60f * 60f;
                researchCostMultiplier = 0.75f;
                upgrades.addAll(
                new UnitType[]{ErUnits.wisp, ErUnits.nanite}
            );
        }};
    };
}
