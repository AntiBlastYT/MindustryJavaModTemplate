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



    public static ErekirUnitType wisp;

    public static void load(){

        	wisp = new ErekirUnitType("Wisp"){{
			aiController = RepairAI::new;
			defaultCommand = UnitCommand.repairCommand;
			constructor = EntityMapping.map(3);
            weapons.add(new RepairBeamWeapon("repair-beam-weapon-center"){{
				y = -2.5f;
				x = 0;
				shootY = 6f;
				mirror = false;
				beamWidth = 0.6f;
				repairSpeed = 0.6f;

				bullet = new BulletType(){{
					maxRange = 60f;
				}};
			}});
			armor = 0;
			hitSize = 8f;
			flying = true;
			drag = 0.06F;
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