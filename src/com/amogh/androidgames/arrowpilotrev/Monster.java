package com.amogh.androidgames.arrowpilotrev;

import java.util.Random;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class Monster extends DynamicGameObject{
	public static final int TYPE_OCTOPUS = 0;
	public static final int TYPE_SPIDER = 1;
	public static final float OCTOPUS_WIDTH = 1.8f;
	public static final float OCTOPUS_HEIGHT = 1.5f;
	public static final int OCTOPUS_VEL_Y = 3;
	public static final float OCTOPUS_VEL_SWAP_TIME = 1;  
	public static final float SPIDER_WIDTH = 1.8f;
	public static final float SPIDER_HEIGHT = 1.5f;
	
	public int type;
	public float velTimer;
	Random rand;

	public Monster(float x, float y, int type){
		super(x, y, OCTOPUS_WIDTH, OCTOPUS_HEIGHT);
		this.type = type;
		this.rand = new Random();
		velTimer = 0;
		if(type == TYPE_OCTOPUS){
			reset(x, y, OCTOPUS_WIDTH, OCTOPUS_HEIGHT);
			velocity.set(WorldEndless.WORLD_VEL_X + 0, WorldEndless.randInt(2, OCTOPUS_VEL_Y));
		}
	}

	public void update(float deltaTime){
		velTimer += deltaTime;
		
		if(type == TYPE_OCTOPUS){
			if(velTimer > OCTOPUS_VEL_SWAP_TIME){
				velocity.set(WorldEndless.WORLD_VEL_X + 0, -velocity.y);
				velTimer = 0;
			}
		}
		
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
		
		if(type == TYPE_OCTOPUS){
			if(position.y > WorldEndless.WORLD_HEIGHT)
				position.y = WorldEndless.WORLD_HEIGHT - OCTOPUS_HEIGHT/2;
			if(position.y < 0)
				position.y = OCTOPUS_HEIGHT/2;
		}
	}
}








