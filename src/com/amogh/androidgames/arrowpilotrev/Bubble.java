package com.amogh.androidgames.arrowpilotrev;

import android.util.FloatMath;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class Bubble extends DynamicGameObject{
	public static final float BUBBLE_VELOCITY_X = 0;
	public static final float BUBBLE_VELOCITY_Y = 6;
	public static final float BUBBLE_WIDTH = 1.2f;
	public static final float BUBBLE_HEIGHT = 1.2f;
	
	public Bubble(float x, float y){
		super(x, y, BUBBLE_WIDTH, BUBBLE_HEIGHT);
	}
	
	public void update(float deltaTime){
		velocity.set(FloatMath.sin(deltaTime), BUBBLE_VELOCITY_Y);
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
	}
}
