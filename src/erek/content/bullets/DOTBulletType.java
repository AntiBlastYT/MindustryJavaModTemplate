package erek.content.bullets;

import arc.math.Mathf;
import arc.math.geom.Vec2;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.bullet.ContinuousBulletType;
import mindustry.entities.bullet.EmpBulletType;
import mindustry.gen.Bullet;
import mindustry.type.StatusEffect;

import static mindustry.Vars.state;

public class DOTBulletType extends EmpBulletType {

    public float DOTRadius = 12f;
    public float DOTDamage = 100f;
    public float radIncrease = 0.25f;
    public Effect fx = Fx.none;
    public DOTBulletType() {
        speed = 0f;
        lifetime = 120f;
        collides = false;
        hittable = false;
        absorbable = false;

        damage = DOTDamage;
        buildingDamageMultiplier = 0f;
        despawnEffect = hitEffect = Fx.none;
    }

    @Override
    public void draw(Bullet b) {
        float rad = (float) b.data;
        for (int i = 0; i < 2; i++) {
            float chance = Mathf.lerp(0.5f, 0.2f, b.time/b.lifetime);
            if (Mathf.chance(chance) && state.isPlaying() && b.timer(1, 1)){
                float a0 = Mathf.random(360) + b.rotation();
                float r0 = Mathf.random(-rad/5, rad/2) + rad;
                float a1 = Mathf.random(360) + b.rotation();
                float r1 = Mathf.random(-rad/5, rad/2) + rad;


            }
        }
    }

    @Override
    public void init(Bullet b) {
        super.init(b);
        b.data = DOTRadius;
    }

    @Override
    public void update(Bullet b) {
        super.update(b);
        float rad = (float) b.data;
        rad += radIncrease;
        b.data = rad;
    }
}