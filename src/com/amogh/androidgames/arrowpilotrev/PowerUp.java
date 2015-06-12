package com.amogh.androidgames.arrowpilotrev;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class PowerUp extends DynamicGameObject{
	public static final float POWER_UP_WIDTH = 1;
	public static final float POWER_UP_HEIGHT = 1;
	public static final float POWER_UP_COL_WIDTH = 1;
	public static final float POWER_UP_COL_HEIGHT = 1;
	
	public static final int SPELL_BOUND = 0;
	public static final int MAGNET = 1;
	
	public static final float SPELL_BOUND_TIME = 15;
	public static final float MAGNET_TIME = 8;
	
	public int type;
	public boolean startSpellBound = false;
	public boolean startMagnet = false;
	public float powerUpTimer = 0;

	public PowerUp(float x, float y, int type){
		super(x, y, POWER_UP_COL_WIDTH, POWER_UP_COL_HEIGHT);
		this.type = type;
		this.velocity.set(WorldEndless.WORLD_VEL_X + 0, 0);
	}

	public void update(float deltaTime){
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
	}
}












