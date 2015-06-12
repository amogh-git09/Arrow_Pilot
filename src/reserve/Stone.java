package reserve;

import com.amogh.androidgames.framework.game2d.GameObject;

public class Stone extends GameObject{
	public static final float STONE_WIDTH = 0.7f;
	public static final float STONE_HEIGHT = 1;
	
	public Stone(float x, float y){
		super(x, y, STONE_WIDTH, STONE_HEIGHT);
	}
}
