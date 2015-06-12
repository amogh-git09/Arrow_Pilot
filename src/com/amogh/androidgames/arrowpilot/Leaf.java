package com.amogh.androidgames.arrowpilot;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class Leaf extends DynamicGameObject{
	public static final float LEAF_WIDTH_MAX = 1;
	public static final float LEAF_HEIGHT_MAX = 1;
	public static final int MAX_VEL = 16;
	public static final int MIN_VEL = 11;
	public static final int MAX_TYPES = AssetsWorld1.leaves.size();
	
	public int type;
	
	public Leaf(float x, float y){
		super(x, y, LEAF_WIDTH_MAX, LEAF_HEIGHT_MAX);
		this.velocity.set(-WorldEndless.randInt(MIN_VEL, MAX_VEL), 0);
		this.type = WorldEndless.randInt(0, MAX_TYPES-1);
	}
	
	public void update(float deltaTime){
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
	}
}
