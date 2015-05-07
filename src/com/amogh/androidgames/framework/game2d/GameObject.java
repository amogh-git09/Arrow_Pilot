package com.amogh.androidgames.framework.game2d;

import com.amogh.androidgames.framework.math.Rectangle;
import com.amogh.androidgames.framework.math.Vector2;

public class GameObject {
	public final Vector2 position;
	public final Rectangle bounds;
	
	public GameObject(float x, float y, float width, float height){
		this.position = new Vector2(x,y);
		this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
	}
	
	public void reset(float x, float y, float width, float height){
		position.set(x,y);
		bounds.lowerLeft.set(x-width/2, y-height/2);
		bounds.width = width;
		bounds.height = height;
	}
}
