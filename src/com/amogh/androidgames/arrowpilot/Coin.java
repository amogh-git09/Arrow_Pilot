package com.amogh.androidgames.arrowpilot;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class Coin extends DynamicGameObject{
	public static final float COIN_WIDTH = 0.6f;
	public static final float COIN_HEIGHT = 0.6f;
	public static final float COIN_COL_WIDTH = 0.6f;
	public static final float COIN_COL_HEIGHT = 0.6f;
	public static final int COIN_TYPE_GOLD = 0;
	public static final int COIN_TYPE_SILVER = 1;
	public static final int COIN_TYPE_BRONZE = 2;
	public static final int TYPE_GEM_GREEN = 3;
	public static final int TYPE_GEM_YELLOW = 4;
	public static final float GEM_WIDTH = 0.7f;
	public static final float GEM_HEIGHT = 0.5f;
	
	public int score;
	public int type;
	public boolean magnet = false;
	
	public Coin(float x, float y, int type){
		super(x, y, COIN_COL_WIDTH, COIN_COL_HEIGHT);
		this.type = type;
		if(type == COIN_TYPE_GOLD)
			score = 50;
		if(type == COIN_TYPE_SILVER)
			score = 30;
		if(type == COIN_TYPE_BRONZE)
			score = 20;		
		if(type == TYPE_GEM_GREEN)
			score = 150;		
		if(type == TYPE_GEM_YELLOW)
			score = 250;		
	
		velocity.set(WorldEndless.WORLD_VEL_X, 0);
	}
	
	public void update(float deltaTime){
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
	}
}
