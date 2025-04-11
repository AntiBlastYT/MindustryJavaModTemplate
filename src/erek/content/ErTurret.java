package erek.content;

import mindustry.ctype.*;
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
    public static Block spencer;

    public static void load(){

        spencer = new ItemTurret("spencer") {{
            requirements(Category.turret, with(Items.copper, 40, Items.lead, 25));
            ammo(
                Items.copper,  new BasicBulletType(3f, 12){{
                    width = 7f;
                    height = 9f;
                    lifetime = 60f;
                }}
            );
            range = 120f;
            reload = 20f;
            inaccuracy = 2f;
            shootCone = 15f;
        }};
}
}