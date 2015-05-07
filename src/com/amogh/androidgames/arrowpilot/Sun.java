package com.amogh.androidgames.arrowpilot;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class Sun extends DynamicGameObject{
	public static final float SUN_WIDTH = 2;
	public static final float SUN_HEIGHT = 2;
	public static final float SUN_VEL_X = -0.07f;
	public static final float SUN_VEL_Y = 0.02f;
	public static final float GRAVITY = 0.1f;
	
	boolean goingUp = true;
	
	public Sun(float x, float y){
		super(x, y, SUN_WIDTH, SUN_HEIGHT);
		this.velocity.set(SUN_VEL_X, SUN_VEL_Y);
	}
	
	public void update(float deltaTime){
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
		
		if(position.x < 0){
			position.x = WorldEndless.DEFAULT_LEVEL_OFFSET + 2;
		}
		if(position.y > WorldEndless.WORLD_HEIGHT - 1 && goingUp){
			velocity.y = -velocity.y;
			goingUp = false;
		}
		if(position.y < WorldEndless.WORLD_HEIGHT - 3 && !goingUp){
			velocity.y = -velocity.y;
			goingUp = true;
		}
	}
}
