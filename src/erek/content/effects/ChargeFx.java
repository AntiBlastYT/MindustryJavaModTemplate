package erek.content.effects;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.entities.*;
import mindustry.graphics.*;
import erek.content.effects.*;
import erek.content.utils.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static arc.math.Angles.randLenVectors;

public final class ChargeFx{
	private static final Rand rand = new Rand();
    public static final Effect

    greenLaserChargeParent = new ParentEffect(80f, 100f, e -> {
        color(Pal.surge);
        stroke(e.fin() * 2f);
        Lines.circle(e.x, e.y, 4f + e.fout() * 100f);

        Fill.circle(e.x, e.y, e.fin() * 20);

        randLenVectors(e.id, 20, 40f * e.fout(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fin() * 5f);
            Drawf.light(e.x + x, e.y + y, e.fin() * 15f, Pal.heal, 0.7f);
        });

        color();

        Fill.circle(e.x, e.y, e.fin() * 10);
        Drawf.light(e.x, e.y, e.fin() * 20f, Pal.surge, 0.7f);
    }),

    sagittariusCharge = new Effect(2f * 60f, e -> {
        float size = e.fin() * 15f;
        color(Pal.surge);
        Fill.circle(e.x, e.y, size);
        MathU.randLenVectors(e.id * 9999L, 15, e.fout(), 0.5f, 0.6f, 0.2f,
            f -> f * f * f * 90f, (ex, ey, fin) -> {
                float fout = 1f - fin;
                if(fin < 0.9999) Fill.circle(ex + e.x, ey + e.y, fout * 11f);
            });
        float f = Mathf.curve(e.fin(), 0.4f);

        if(f > 0.0001f){
            for(int s : Mathf.signs){
                Drawf.tri(e.x, e.y, Interp.pow2Out.apply(f) * 15f * 1.22f, f * f * 80f, e.rotation + 90f * s);
            }
        }

        color(Color.white);
        Fill.circle(e.x, e.y, size * 0.5f);
    }).followParent(true).rotWithParent(true),

    bouncingLaserShoot = new Effect(21f, e -> {
        color(Pal.surge);

        for(int i : Mathf.signs){
            Drawf.tri(e.x, e.y, 4f * e.fout(), 29f, e.rotation + 45f * i);
        }
    }),

    triSpark1 = new Effect(26, e -> {
        rand.setSeed(e.id);
        Draw.color(Pal.surge, Color.white, e.fin());
        randLenVectors(e.id, 3, 3f + 24f * e.fin(), 5f, (x, y) -> {
            float randN = rand.random(120f);
            Fill.poly(e.x + x, e.y + y, 4, e.fout() * 8f * rand.random(0.8f, 1.2f), e.rotation + randN * e.fin());
        });
    }),
    surgeHealWave = new Effect(60, e -> {
        color(Pal.surge);
        stroke(e.fout() * 4f);
        Lines.circle(e.x, e.y, 4f + e.finpow() * e.rotation);
    }),
    surgeHeal = new Effect(60, e -> {
        rand.setSeed(e.id);
        color(Pal.surge);
        stroke(e.fout() * 1f);
        Lines.circle(e.x, e.y, 2f + e.finpow() * 7f);
        randLenVectors(e.id, 3, 1f + 24f * e.fin(), 2f, (x, y) -> {
            float randN = rand.random(120f);
            Fill.poly(e.x + x, e.y + y, 4, e.fout() * 2f * rand.random(0.2f, 0.4f), e.rotation + randN * e.fin());
        });
    });
    private ChargeFx(){
        throw new AssertionError();
    }
    
}
