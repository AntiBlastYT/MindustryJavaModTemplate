package erek.content.effects;

import arc.graphics.Color;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;

public class OptionalMultiEffect extends MultiEffect{
	
	public OptionalMultiEffect(Effect... effects){
		super(effects);
	}
	
	@Override
	public void init(){
		if(effects.length == 0)throw new IllegalArgumentException("The MultiEffect must contains at least one effect");
		super.init();
	}
	
	@Override
	public void create(float x, float y, float rotation, Color color, Object data){
		if(!shouldCreate()) return;
		

		else effects[0].create(x, y, rotation, color, data);
	}
}