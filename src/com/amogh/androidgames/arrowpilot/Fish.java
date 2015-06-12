package com.amogh.androidgames.arrowpilot;

import java.util.Random;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class Fish extends DynamicGameObject{
	public static final float FISH_VELOCITY_X = 2;
	public static final float FISH_VELOCITY_Y = 14;
	public static final float GRAVITY = -10;
	public static final float FISH_WIDTH = 1;
	public static final float FISH_HEIGHT = 0.4f;
	public static final float FISH_COL_WIDTH = 1;
	public static final float FISH_COL_HEIGHT = 0.4f;
	public static final float FRUIT_WIDTH = 1;
	public static final float FRUIT_HEIGHT = 1;
	public static final float FRUIT_COL_WIDTH = 1;
	public static final float FRUIT_COL_HEIGHT = 1;
	
	public static final int CLOWN_FISH = 0;
	public static final int PURPLE_FISH = 1;
	public static final int ORANGE_FISH = 2;
	
	int type;
	public static int score = 120;
	static Random rand;
	public boolean arrowNearby = false;
	
	public Fish(float x, float y, int type){
		super(x, y, FISH_COL_WIDTH, FISH_COL_HEIGHT);
		this.type = type;
		rand = new Random();
		velocity.set(WorldEndless.WORLD_VEL_X, 0);
	}
	
	public void update(float deltaTime){
		if(arrowNearby)
			velocity.add(0, GRAVITY * deltaTime);
		
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
	}
	
	public static int randInt(int min, int max) {
		int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

}
