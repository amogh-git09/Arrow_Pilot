package reserve;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class Anchor extends DynamicGameObject{
	public static final int TYPE_ANCHOR = 0;
	public static final int TYPE_WEIGHT = 1;
	public static final float ANCHOR_WIDTH = 1.8f;
	public static final float ANCHOR_HEIGHT = 2;
	public static final float WEIGHT_WIDTH = 2;
	public static final float WEIGHT_HEIGHT = 2;
	
	public int type;
	public static final float GRAVITY = -6;
	
	public Anchor(float x, float y, int type){
		super(x, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		velocity.set(0, 0);
		this.type = type;
	}
	
	public void update(float deltaTime){
		velocity.add(0, GRAVITY*deltaTime);
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
	}
}
