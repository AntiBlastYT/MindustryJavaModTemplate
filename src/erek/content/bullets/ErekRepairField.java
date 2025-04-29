package erek.content.bullets;

import arc.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.Ability;
import mindustry.gen.*;
import erek.content.effects.ChargeFx;

import static mindustry.Vars.*;

public class ErekRepairField extends Ability{
    public float amount = 1, reload = 100, range = 60;
    public Effect healEffect = ChargeFx.surgeHeal;
    public Effect activeEffect = ChargeFx.surgeHealWave;
    public boolean parentizeEffects = false;

    protected float timer;
    protected boolean wasHealed = false;

    ErekRepairField(){}

    public ErekRepairField(float amount, float reload, float range){
        this.amount = amount;
        this.reload = reload;
        this.range = range;
    }

    @Override
    public void addStats(Table t){
        super.addStats(t);
        t.add(Core.bundle.format("bullet.range", Strings.autoFixed(range / tilesize, 2)));
        t.row();
        t.add("[accent]" + Core.bundle.get("stat.repairspeed") + ":[] " + Strings.autoFixed(amount * 60f / reload, 2));

    }

    @Override
    public void update(Unit unit){
        timer += Time.delta;

        if(timer >= reload){
            wasHealed = false;

            Units.nearby(unit.team, unit.x, unit.y, range, other -> {
                if(other.damaged()){
                    healEffect.at(other, parentizeEffects);
                    wasHealed = true;
                }
                other.heal(amount);
            });

            if(wasHealed){
                activeEffect.at(unit, range);
            }

            timer = 0f;
        }
    }
}