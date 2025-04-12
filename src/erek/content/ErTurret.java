package erek.content;

import mindustry.ctype.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.geom.*;
import arc.struct.Seq;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.game.Team;
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
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.storage.*;
//import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static mindustry.Vars.state;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.type.ItemStack.*;

public class ErTurret {

        //turret
    public static Block spencer;

    public static void load(){

        spencer = new ItemTurret("spencer") {{
            requirements(Category.turret, with(Items.tungsten, 150, Items.silicon, 250, Items.oxide, 60, Items.surgeAlloy, 100));
            ammo(
                Items.surgeAlloy,  new ArtilleryBulletType(3f, 12){{
                    width = 12f;
                    height = 16f;
                    lifetime = 240f;
                    collidesAir = true;
                    collidesGround = true;
                    buildingDamageMultiplier = 0.5f;
                    absorbable = true;
                    hittable = false;
                    splashDamageRadius = 100;
                    lightningDamage = 120;
                    lightningLength = 10;
                    lightning = 10;
                    damage = 900;
                    splashDamage = 200;
                    lifetime = 19;
                    speed = 20;
                    scaleLife = true;
                    trailEffect = Fx.colorSpark;
                    trailRotation = true;
                    trailInterval = 3f;
                    hitColor = backColor = trailColor = lightningColor = Color.valueOf("FFFFFF");
                    frontColor = Color.white;
                    trailWidth = 3f;
                    trailLength = 12;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }}
            );
			moveWhileCharging = false;
            ammoUseEffect = Fx.casing3;
            range = 300f;
            reload = 500f;
            shootCone = 100f;
            scaledHealth = 210;
            inaccuracy = 1f;
            shootCone = 15f;
            size = 4;
            ammoEjectBack = 3f;
            shoot.shots = 4;
            shoot.shotDelay = 40f;
            consumePower(10f);
            outlineColor = Pal.darkOutline;
            recoil = 10f;
            recoilTime = 90f;
            rotateSpeed = 2f;
        }};
}
}