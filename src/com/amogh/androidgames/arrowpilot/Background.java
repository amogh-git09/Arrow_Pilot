package com.amogh.androidgames.arrowpilot;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class Background extends DynamicGameObject{
	public static final float BG_VEL_X = -2;
	public static final float BG_WIDTH = 32;
	public static final float BG_HEIGHT = 10;
	
	public Background(float x, float y){
		super(x, y, BG_WIDTH, BG_HEIGHT);
		this.velocity.set(BG_VEL_X, 0);
	}
	
	public void update(float deltaTime){
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
	}
}
