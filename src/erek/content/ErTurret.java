package erek.content;

import arc.graphics.*;
//import arc.graphics.g2d.*;
//import arc.math.Interp;
//import arc.math.Mathf;
//import arc.math.geom.*;
//import arc.struct.Seq;
//import mindustry.entities.*;
import mindustry.entities.bullet.*;
//import mindustry.entities.effect.*;
//import mindustry.entities.part.*;
//import mindustry.entities.pattern.*;
//import mindustry.game.Team;
//import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
//import mindustry.type.unit.MissileUnitType;
import mindustry.world.*;
import mindustry.content.*;
//import mindustry.world.blocks.campaign.LaunchPad;
//import mindustry.world.blocks.defense.*;
//import mindustry.world.blocks.distribution.*;
//import mindustry.world.blocks.heat.*;
//import mindustry.world.blocks.payloads.Constructor;
//import mindustry.world.blocks.power.*;
//import mindustry.world.blocks.storage.*;
//import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
//import mindustry.world.blocks.production.*;
//import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.meta.*;

//import static arc.graphics.g2d.Draw.*;
//import static arc.graphics.g2d.Lines.*;
//import static mindustry.Vars.state;
import static mindustry.content.Items.*;
//import static mindustry.content.Liquids.*;
import static mindustry.type.ItemStack.*;

public class ErTurret {

        //turret
    public static Block  spencer;

    public static void load(){

        spencer = new ItemTurret("spencer") {{
            requirements(Category.turret, with(beryllium, 130, silicon, 185, graphite, 260));

            ammo(
                    beryllium, new BasicBulletType(5f, 20){{
                        width = 8f;
                        hitSize = 7f;
                        height = 12f;
                        ammoMultiplier = 1;
                        hitColor = backColor = trailColor = Pal.berylShot;
                        frontColor = Color.white;
                        trailWidth = 1.6f;
                        trailLength = 7;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        buildingDamageMultiplier = 0.3f;
                        pierceArmor = true;
                    }},
                    silicon, new BasicBulletType(5f, 35){{
                        width = 8f;
                        height = 12f;
                        hitSize = 7f;
                        ammoMultiplier = 1;
                        reloadMultiplier = 1f;
                        hitColor = backColor = trailColor = Pal.darkerGray;
                        frontColor = Color.white;
                        trailWidth = 1.6f;
                        trailLength = 7;
                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        buildingDamageMultiplier = 0.3f;
                        pierceArmor = true;
                    }}
            );

            squareSprite = false;
            coolantMultiplier = 5f;
            shake = 1f;
            drawer = new DrawTurret("reinforced-");
            outlineColor = Pal.darkOutline;
            size = 3;
            envEnabled |= Env.space;
            reload = 10f;
            recoil = 2f;
            range = 200;
            shootCone = 5f;
            scaledHealth = 130;
            researchCostMultiplier = 0.05f;

            coolant = consume(new ConsumeLiquid(Liquids.water, 15f / 60f));
        }};
}
}