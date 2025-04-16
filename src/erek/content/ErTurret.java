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
import mindustry.entities.part.DrawPart.PartProgress;
import mindustry.entities.pattern.*;
import mindustry.game.Team;
import mindustry.gen.Sounds;
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
                    width = 16f;
                    height = 20f;
                    lifetime = 240f;
                    collidesAir = true;
                    collidesGround = true;
                    buildingDamageMultiplier = 0.5f;
                    absorbable = true;
                    hittable = false;
                    splashDamageRadius = 100;
                    lightningDamage = 170;
                    lightningLength = 9;
                    lightning = 4;
                    lightningLengthRand = 5;
                    damage = 700;
                    splashDamage = 20;
                    lifetime = 19;
                    speed = 20;
                    scaleLife = true;
                    hitColor = backColor = trailColor = lightningColor = Pal.surge;
                    frontColor = Color.white;   
                    shootEffect = Fx.instShoot;
                    hitShake = 2f;
                    status = StatusEffects.shocked;
                    statusDuration = 20f;
                    smokeEffect = Fx.smokeCloud;
                    hitEffect = new ExplosionEffect(){{
                        lifetime = 21f;
                    
                        waveColor = Pal.surge;
                        waveStroke = 7.5f;
                        waveRad = 60f;
                    
                        sparkColor = Pal.surge;
                        sparkStroke = 1.2f;
                        sparkRad = 7f;
                        sparkLen = 9f;
                        sparks = 4;
                    }};
                    trailColor = Pal.surge;
                    trailRotation = true;
                    trailEffect = Fx.disperseTrail;
                    trailInterval = 2f;
                    trailWidth = 3f;
                    trailLength = 3;
                    fragBullets = 4;
                    fragLifeMin = 0.3f;
                    fragLifeMax = 0.6f;
                    fragVelocityMin = 1.8f;
                    fragVelocityMax = 3.6f;
                    fragRandomSpread = 16f;
                    fragBullet = new ArtilleryBulletType(){{
                        collides = true;
                        collidesTiles = true;
                        collidesGround = true;
                        shrinkY = 0.3f;
                        height = 14f;
                        width = 10f;
                        splashDamageRadius = 16f;
                        splashDamage = 72f;
                        pierceBuilding = true;
                        pierceCap = 3;
                        lifetime = 30f;
                        damage = 70f;
                        pierceArmor = true;
                        despawnEffect = Fx.none;
                        speed = 3.5f;
                        lightColor = Color.valueOf("#ffffff");
                        lightOpacity = 0.8f;
                        lightRadius = 18.5f;
                    
                        lightningDamage = 40;
                        lightningLength = 6;
                        lightning = 4;
                    
                        trailColor = Pal.surge;
                        trailRotation = true;
                        trailEffect = Fx.disperseTrail;
                        trailInterval = 3f;
                        trailWidth = 3f;
                        trailLength = 3;
                    
                        frontColor = Color.valueOf("ffffff");
                        backColor = Color.valueOf("ffffff");
                    
                        despawnEffect = new MultiEffect(
                            new ParticleEffect(){{
                                startDelay = 5f;
                                particles = 4;
                                cone = 360f;
                                length = 7f;
                                lifetime = 45f;
                                sizeFrom = 2f;
                                sizeTo = 0f;
                                layer = 100.1f;
                                colorFrom = Color.valueOf("ffffff");
                                colorTo = Color.valueOf("ffffff");
                            }},
                            new WaveEffect(){{
                                startDelay = 0f;
                                sizeFrom = 0f;
                                sizeTo = 18f;
                                lifetime = 15f;
                                strokeFrom = 4.5f;
                                colorFrom = Color.valueOf("ffffff");
                                colorTo = Color.valueOf("ffffff");
                            }},
                            new ParticleEffect(){{
                                particles = 6;
                                cone = 360f;
                                line = true;
                                strokeFrom = 1f;
                                strokeTo = 0f;
                                lenFrom = 6f;
                                lenTo = 0f;
                                length = 5f;
                                colorFrom = Color.valueOf("ffffff");
                                colorTo = Color.valueOf("ffffff");
                                lifetime = 35f;
                            }},
                            new ParticleEffect(){{
                                particles = 6;
                                cone = 360f;
                                line = true;
                                strokeFrom = 1f;
                                strokeTo = 0f;
                                lenFrom = 6f;
                                lenTo = 0f;
                                length = 12f;
                                colorFrom = Color.valueOf("ffffff");
                                colorTo = Color.valueOf("ffffff");
                                lifetime = 35f;
                            }},
                            new ParticleEffect(){{
                                particles = 6;
                                cone = 360f;
                                line = true;
                                strokeFrom = 1f;
                                strokeTo = 0f;
                                lenFrom = 6f;
                                lenTo = 0f;
                                length = 12f;
                                colorFrom = Color.valueOf("ffffff");
                                colorTo = Color.valueOf("ffffff");
                                lifetime = 10f;
                            }}
                        );
                    }};
                }}
            );
            shootSound = Sounds.artillery;
			moveWhileCharging = false;
            ammoUseEffect = Fx.casing3;
            range = 500f;
            reload = 400f;
            shootCone = 100f;
            ammoPerShot = 2;
            scaledHealth = 210;
            inaccuracy = 1f;
            shootCone = 15f;
            size = 4;
            ammoEjectBack = 3f;
            shoot.shots = 3;
            shoot.shotDelay = 20f;
            outlineColor = Pal.darkOutline;
            recoil = 4f;
            recoilTime = 90f;
            rotateSpeed = 1.5f;
            coolantMultiplier = 0.4f;
            shake = 3f;
            coolant = consumeCoolant(1f);
            consumePower(10f);
            drawer = new DrawTurret("reinforced-"){{
                var heatp = PartProgress.warmup.blend(p -> Mathf.absin(2f, 1f) * p.warmup, 0.2f);
                heatColor = Color.valueOf("ff6214");

                parts.addAll(
                new RegionPart("-blade"){{
                    progress = heatp;
                    heatProgress = PartProgress.warmup;
                    heatColor = Color.valueOf("ff6214");
                    mirror = true;
                    moveX = 0.5f;
                    moves.add(new PartMove(PartProgress.recoil, 0f, -2.5f, 2.5f));
                    under = true;
                }},
                    new RegionPart(""){{
                    heatProgress = heatp;
                    progress = PartProgress.warmup;
                    heatColor = Color.valueOf("ff6214");
                }},
                new RegionPart("-back"){{
                    heatProgress = heatp;
                    heatColor = Color.valueOf("ff6214");
                    progress = PartProgress.warmup;
                    mirror = true;
                    moveX = 2f * 2f / 3.5f;
                    moveY = 4f;
                    moveRot = -5f;
                    under = true;
                }});
            }};

        }};
}}
