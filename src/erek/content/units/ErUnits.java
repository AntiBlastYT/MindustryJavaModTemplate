package erek.content.units;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.ai.*;
import mindustry.ai.types.*;
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



    public static ErUnitType wisp;

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
                beamWidth = 0.7f;
                repairSpeed = 3.1f;
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
			armor = 0;
			hitSize = 8f;
			flying = true;
			drag = 0.06F;
            faceTarget = true;
			accel = 0.12F;
			itemCapacity = 15;
			speed = 1.2F;
			health = 300.0F;
			engineSize = 1.4F;
			engineOffset = 5.7F;
			range = 60.0F;
			isEnemy = false;
			lowAltitude = true;
            mineTier = 0;
		};
    };
}
}