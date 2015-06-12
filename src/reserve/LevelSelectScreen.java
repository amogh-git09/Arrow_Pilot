package reserve;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.amogh.androidgames.framework.Game;
import com.amogh.androidgames.framework.Input.TouchEvent;
import com.amogh.androidgames.framework.gl.Camera2D;
import com.amogh.androidgames.framework.gl.SpriteBatcher;
import com.amogh.androidgames.framework.impl.GLScreen;
import com.amogh.androidgames.framework.math.OverlapTester;
import com.amogh.androidgames.framework.math.Rectangle;
import com.amogh.androidgames.framework.math.Vector2;

public class LevelSelectScreen extends GLScreen{
	Camera2D guiCam;
	SpriteBatcher batcher;

	static final int numLevels = 10;
	static final int AREA1 = 0;
	static final int AREA2 = 1;
	static Rectangle[] levelBounds;
	Rectangle musicBounds;
	Rectangle soundBounds;
	Vector2 touchPoint;
	int area;

	public LevelSelectScreen(Game game, int area){
		super(game);
		if(area == AREA1)
			ArrowPilot.assetsToUse = ArrowPilot.USE_ASSETS_AREA_1;
		if(area == AREA2)
			ArrowPilot.assetsToUse = ArrowPilot.USE_ASSETS_AREA_2;

		guiCam = new Camera2D(glGraphics, 800, 480);
		batcher = new SpriteBatcher(glGraphics, 100);
		levelBounds = new Rectangle[numLevels];
		musicBounds = new Rectangle(0, 0, 43, 40);
		soundBounds = new Rectangle(44, 0, 43, 40);
		touchPoint = new Vector2();
		this.area = area;
		Log.d("LevelSelectScreen", "area = " + area);
		int numRows = numLevels / 5;
		int numCols = numRows >= 1?5:(numLevels % 5);

		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numCols; j++){
				levelBounds[i*numCols + j] = new Rectangle(120 + 120*j, 480 - 200 - 160*i, 90, 90);
			}
		}
	}

	@Override
	public void update(float deltaTime){
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				for(int j = 0; j < Settings.noOfLevels; j++){
					if(Settings.levelStates.get(area).get(j)){
						if(OverlapTester.pointInRectangle(levelBounds[j], touchPoint)){
							AssetsSound.playSound(AssetsSound.clickSound);
							AssetsMain.unload();
							game.setScreen(new GameScreen(game, area));
							return;
						}
					}
				}

				if(OverlapTester.pointInRectangle(musicBounds, touchPoint)){
					AssetsSound.playSound(AssetsSound.clickSound);
					Settings.musicEnabled = !Settings.musicEnabled;
					if(Settings.musicEnabled)
						AssetsSound.music.play();
					else
						AssetsSound.music.pause();
					return;
				}
				if(OverlapTester.pointInRectangle(soundBounds, touchPoint)){
					AssetsSound.playSound(AssetsSound.clickSound);
					Settings.soundEnabled = !Settings.soundEnabled;
				}	
			}
		}
	}

	@Override
	public void present(float deltaTime){
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setVeiwPortAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		batcher.beginBatch(AssetsMain.mainSprite);
		batcher.drawSprite(400, 240, 800, 480, AssetsMain.backgroundRegion);

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		for(int i = 0; i < Settings.noOfLevels; i++){
			if(Settings.levelStates.get(area).get(i)){
				batcher.drawSprite(levelBounds[i].lowerLeft.x + levelBounds[i].width/2, 
						levelBounds[i].lowerLeft.y + levelBounds[i].height/2, 
						levelBounds[i].width, 
						levelBounds[i].height, 
						AssetsMain.levels.get(i));
			}else{
				batcher.drawSprite(levelBounds[i].lowerLeft.x + levelBounds[i].width/2, 
						levelBounds[i].lowerLeft.y + levelBounds[i].height/2, 
						levelBounds[i].width, 
						levelBounds[i].height, 
						AssetsMain.levels.get(10));
			}
		}

		batcher.endBatch();

		gl.glDisable(GL10.GL_BLEND);
	}

	@Override
	public void pause(){
		Settings.save(game.getFileIO());
	}

	@Override
	public void resume(){

	}

	@Override
	public void dispose(){

	}
}
