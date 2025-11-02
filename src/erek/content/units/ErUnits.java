package erek.content.units;

import arc.freetype.FreeType.Size;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import erek.content.ErSounds;
import erek.content.ai.ErMinerAI;
import erek.content.effects.*;
import erek.content.utils.*;
import mindustry.ai.*;
import mindustry.ai.types.*;
import mindustry.content.Bullets;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.part.DrawPart.PartProgress;
import mindustry.entities.pattern.*;
import mindustry.entities.units.*;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;
import erek.content.bullets.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class ErUnits{

    public static ErUnitType wisp, nanite, bastion, remedy, spidern;

    public static void load(){

        spidern = new ErUnitType("spidern"){{
                speed = 0.6F;
                drag = 0.1F;
                hitSize = 21.0F;
                rotateSpeed = 3.0F;
                health = 3200.0F;
                armor = 10.0F;
                fogRadius = 30.0F;
                stepShake = 0.75F;
                legCount = 4;
                legLength = 22.0F;
                legGroupSize = 2;
                lockLegBase = true;
                legContinuousMove = true;
                legExtension = -3.0F;
                legBaseOffset = 7.0F;
                legMaxLength = 1.1F;
                legMinLength = 0.2F;
                legLengthScl = 0.925F;
                legForwardScl = 0.9075F;
                legMoveSpace = 1.085F;
                hovering = true;
                shadowElevation = 0.22F;
                groundLayer = 74.0F;
                faceTarget = false;
    };
};
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
            deathExplosionEffect = new Effect(1, e -> {
                Fx.scatheExplosion.at(e.x, e.y);
                float radius = 60f;
                float damage = 80f;
                Team team = e.data instanceof Teamc ? ((Teamc)e.data).team() : Team.derelict;
        
                Groups.unit.intersect(e.x - radius, e.y - radius, radius * 2, radius * 2, u -> {
                    if (u.team != team && !u.dead && u.dst(e.x, e.y) <= radius) {
                        u.damage(damage);
                    }
                });
        
                Groups.build.each(b -> {
                    if (b.team != team && b.dst(e.x, e.y) <= radius) {
                        b.damage(damage);
                    }
                });
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
			range = 100.0F;
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
bastion = new ErUnitType("bastion"){{
    defaultCommand = UnitCommand.assistCommand;
    constructor = EntityMapping.map(3);
            setEnginesMirror(
            new UnitEngine(30 / 4f, -32 / 4f, 3.1f, 315f)
            );

            parts.add(
            new RegionPart("-tip"){{
                moveY = 1f;
                moves.add(new PartMove(PartProgress.reload, 0f, -1f, 0f));
                progress = PartProgress.warmup;
                mirror = false;

                children.add(new RegionPart("-blade"){{
                    moveX = 0f;
                    moveY = 1f;
                    progress = PartProgress.warmup;
                    under = true;
                    mirror = false;
                    moves.add(new PartMove(PartProgress.reload, 0f, 0f, 0f));
                }});
            }});
            
            weapons.add(new Weapon(){{
                x = 0f;
                y = 8.25f;
                mirror = false;
                reload = 5f * 60f;
                shootSound = ErSounds.bastionlaser;
                shootStatus = StatusEffects.slow;
                shoot.firstShotDelay =  0f;

                bullet = new ReflectingLaserBulletType(50f){{
                    lifetime = 40f;
                    shootEffect = ChargeFx.bouncingLaserShoot;
                    healPercent = 10f;
                    splashDamage = 10f;
                    splashDamageRadius = 10f;
                    lightningDamage = 10f;
                    hitEffect = HitFx.coloredHitLarge;
                    hitColor = lightningColor = Pal.surge;
                    pierceCap = 1;
                    targetGround = true;
                    targetAir = true;
                    collidesTeam = true;
                    lightningLength = 6; 
                    colors = new Color[]{Pal.surge.cpy().a(0f), Pal.surge.cpy().a(0.5f), Pal.surge.cpy().mul(1f), Color.white};
                }};
                
            }});
    armor = 4;
    hitSize = 24f;
    flying = true;
    speed = 2f;
    accel = 0.06f;
    drag = 0.017f;
    itemCapacity = 60;
    health = 2000.0F;
    engineSize = 3.4F;
    engineOffset = 14.2F;
    range = 120.0F;
    isEnemy = false;
    mineTier = 3;
    mineSpeed = 6F;
    buildSpeed = 1.6f;
    buildBeamOffset = 13;
    mineWalls = true;
    mineFloor = true;
    lowAltitude = true;
    payloadCapacity = Mathf.sqr(2f) * tilePayload;
    
    mineItems.addAll(Items.beryllium, Items.graphite, Items.tungsten);
};
};
remedy = new ErUnitType("remedy"){{
    aiController = FlyingFollowAI::new;
    constructor = EntityMapping.map(3);
            setEnginesMirror(
            new UnitEngine(55 / 4f, -60 / 4f, 3.1f, 315f),
            new UnitEngine(30 / 6f, -70 / 4f, 3.1f, 270f)
            );

            parts.add(
            new RegionPart("-tip"){{
                moveY = 1f;
                moves.add(new PartMove(PartProgress.reload, 0f, -1f, 0f));
                progress = PartProgress.warmup;
                mirror = false;

                children.add(new RegionPart("-blade"){{
                    moveX = 0f;
                    moveY = 1f;
                    progress = PartProgress.warmup;
                    under = true;
                    mirror = false;
                    moves.add(new PartMove(PartProgress.reload, 0f, 0f, 0f));
                }});
            }});
            weapons.add(new Weapon("remedy-emp"){{
                rotate = false;
                mirror = false;
                reload = 300f;
                shake = 3f;
                rotateSpeed = 2f;
                shootY = 30f;
                recoil = 4f;
                cooldownTime = reload - 10f;
                shootSound = Sounds.laser;

                bullet = new EmpBulletType(){{
                    damage = 40;
                    speed = 3f;
                    lifetime = 60f;
                    hitShake = despawnShake = 1.2f;
                    hitEffect = HitFx.surgeBlast;
                    status = StatusEffects.electrified;
                    hitSound = Sounds.none;

                    fragBullet = new ReflectingLaserBulletType(30f){{
                        lifetime = 40f;
                        shootEffect = ChargeFx.bouncingLaserShoot;
                        splashDamage = 10f;
                        splashDamageRadius = 5f;
                        lightningDamage = 10f;
                        hitEffect = HitFx.coloredHitLarge;
                        hitColor = lightningColor = Pal.surge;
                        pierceCap = 1;
                        targetGround = true;
                        targetAir = true;
                        collidesTeam = false;
                        lightningLength = 6; 
                        colors = new Color[]{Pal.surge.cpy().a(0f), Pal.surge.cpy().a(0.4f), Pal.surge.cpy().mul(0.8f), Color.white};
                    }};
                    fragBullets = 3;
                    fragRandomSpread = 40f;
                    fragSpread = 120f;

                    trailRotation = true;
                    trailEffect = ChargeFx.triSpark1;
                    trailInterval = 3f;
                    trailWidth = 4f;
                    trailLength = 3;

                    backColor = lightColor = lightningColor = trailColor = hitColor = Pal.surge;

                    despawnEffect = HitFx.surgeBlast;
                    
                    };
                };
            }});
            abilities.add(new ShieldTurret(){{
				radius = hitSize + 60f;
				angle = 120;
				regen = 1f;
				cooldown = 60f * 10f;
				max = 3000f;
				width = 11f;
				drawWidth = 5f;
				whenShooting = false;
			}},(new ShieldTurret(){{
				radius = hitSize + 56f;
				angle = 60;
				regen = 1f;
				cooldown = 60f * 10f;
				max = 3000f;
				width = 11f;
				drawWidth = 5f;
				whenShooting = false;
			}}),
            (new ErekRepairField(100f, 60f * 3, 100f)));
    armor = 5;
    hitSize = 34f;
    flying = true;
    speed = 1f;
    accel = 0.04f;
    drag = 0.04f;
    rotateSpeed = 0.9f;
    itemCapacity = 160;
    health = 4000.0F;
    engineSize = 0F;
    engineOffset = 14.2F;
    range = 120.0F;
    isEnemy = false;
    payloadCapacity = Mathf.sqr(3f) * tilePayload;
    mineTier = 0;
    buildSpeed = 2.4f;
    buildBeamOffset = 19;
    lowAltitude = false;
    
};
};
}
}