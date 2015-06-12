package com.amogh.androidgames.arrowpilotrev;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class Arrow extends DynamicGameObject{
	public static final int ARROW_FLYING = 0;
	public static final int ARROW_PUSH_UP = 1;
	public static final int ARROW_PUSH_DOWN = 2;
	public static float ARROW_VELOCITY_X = 0;
	public static float ARROW_VELOCITY_Y = 16;
	public static float WATER_CURRENT_ACCEL = 2.5f;
	public static float ARROW_ACCEL_Y = 80f;
	public static final float ARROW_WIDTH = 2.5f;
	public static final float ARROW_HEIGHT = 0.4f;
	public static final float ARROW_COL_WIDTH = 2.5f;
	public static final float ARROW_COL_HEIGHT = 0.4f;
	
	int state;
	boolean hit = false;
	boolean disappear = false;
	public boolean spellBound = false;
	public boolean magnet = false;
	public float magnetTimer = 0;
	public float spellBoundTimer = 0;
	public float ringStateTime = 0;
	public boolean waterCurrent = false;

	public Arrow(float x, float y){
		super(x, y, ARROW_COL_WIDTH, ARROW_COL_HEIGHT);
		state = ARROW_FLYING;
		velocity.set(ARROW_VELOCITY_X, 0);
	}

	public void update(float deltaTime, float touchY, float touchX){
		if(!hit){
			if(touchY != 0){
				
				//velocity.set(ARROW_VELOCITY_X, touchY<240?ARROW_VELOCITY_Y:-ARROW_VELOCITY_Y);

				if(touchX < 400){
					velocity.set(ARROW_VELOCITY_X, -ARROW_VELOCITY_Y);	
				}
				if(touchX > 400){
					velocity.set(ARROW_VELOCITY_X, ARROW_VELOCITY_Y);	
				}
			}

			if(velocity.y != 0 && !waterCurrent){
				if(velocity.y > -2.5f && velocity.y < 2.1f)
					velocity.y = 0;
				else{
					accel.set(0, velocity.y<0?ARROW_ACCEL_Y:-ARROW_ACCEL_Y);
					velocity.add(accel.x*deltaTime, accel.y*deltaTime);
				}
			}
			if(velocity.y != 0 && waterCurrent){
				if(velocity.y > -1.5f && velocity.y < 1.5f)
					velocity.y = 0;
				else{
					accel.set(0, velocity.y<0?ARROW_ACCEL_Y:-ARROW_ACCEL_Y);
					addWaterCurrent(0);
					velocity.add(accel.x*deltaTime, accel.y*deltaTime);
				}
			}
		}
		
		if(waterCurrent){
			addWaterCurrent(0);
			velocity.add(accel.x*deltaTime, accel.y*deltaTime);
		}

		position.add(velocity.x * deltaTime, velocity.y * deltaTime);

		if(!hit){
			if(position.y > WorldEndless.WORLD_HEIGHT - ARROW_HEIGHT/2)
				position.y = WorldEndless.WORLD_HEIGHT - ARROW_HEIGHT/2;
			if(position.y < ARROW_HEIGHT/2)
				position.y = ARROW_HEIGHT/2;
		}
		
		bounds.lowerLeft.set(position).sub(bounds.width / 2, bounds.height / 2);
		
		ringStateTime += deltaTime;
	}

	public void hitBlade(){
		if(spellBound)
			return;
		
		velocity.set(0,-15);
		hit = true;
	}
	
	public void addWaterCurrent(int direction){
		accel.add(0, WATER_CURRENT_ACCEL);
	}
}








