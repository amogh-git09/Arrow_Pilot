package reserve;

import com.amogh.androidgames.framework.game2d.DynamicGameObject;

public class SwordFish extends DynamicGameObject{
	public static final float SWORDFISH_WIDTH = 2;
	public static final float SWORDFISH_HEIGHT = 0.6f;
	public static final float BEE_WIDTH = 1;
	public static final float BEE_HEIGHT = 0.7f;
	public static final float SWORDFISH_VEL_X = -8;
	
	public float stateTime = 0;
	
	public SwordFish(float x, float y){
		super(x, y, SWORDFISH_WIDTH, SWORDFISH_HEIGHT);
		velocity.set(SWORDFISH_VEL_X, 0);
	}
	
	public void update(float deltaTime){
		position.add(velocity.x*deltaTime, velocity.y*deltaTime);
		bounds.lowerLeft.set(position).sub(bounds.width/2, bounds.height/2);
		stateTime += deltaTime;
	}
	
}
