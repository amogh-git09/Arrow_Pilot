package com.amogh.androidgames.arrowpilotrev;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class Stone extends DynamicGameObject{
	public static final float STONE_WIDTH = 0.4f;
	public static final float STONE_HEIGHT = 1;
	
	public Stone(float x, float y){
		super(x, y, STONE_WIDTH, STONE_HEIGHT);
		this.velocity.set(WorldEndless.WORLD_VEL_X + 0, 0);
	}
	
	public void update(float deltaTime){
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
	}
}
