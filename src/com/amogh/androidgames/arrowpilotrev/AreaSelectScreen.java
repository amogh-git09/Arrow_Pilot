package com.amogh.androidgames.arrowpilotrev;

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

public class AreaSelectScreen extends GLScreen{
	Camera2D guiCam;
	SpriteBatcher batcher;

	static Rectangle[] areaBounds;
	Rectangle musicBounds;
	Rectangle backBounds;
	Rectangle soundBounds;
	Vector2 touchPoint;

	public AreaSelectScreen(Game game){
		super(game);
		guiCam = new Camera2D(glGraphics, 800, 480);
		batcher = new SpriteBatcher(glGraphics, 100);
		areaBounds = new Rectangle[Settings.noOfAreas];
		musicBounds = new Rectangle(0, 0, 43, 40);
		backBounds = new Rectangle(0, 480 - 40, 62, 40);
		soundBounds = new Rectangle(44, 0, 43, 40);
		touchPoint = new Vector2();

		for(int i = 0; i < Settings.noOfAreas; i++){
			areaBounds[i] = new Rectangle(200 + (i*275), 480 - 375, 110, 280);
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

				for(int j = 0; j < Settings.noOfAreas; j++){
					if(OverlapTester.pointInRectangle(areaBounds[j], touchPoint)){
						AssetsSound.playSound(AssetsSound.clickSound);
						AssetsMain.unload();
						game.setScreen(new GameScreen(game, j));
						return;
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
					return;
				}
				Log.d("AreaSelectScreen", "back test follows");
				if(OverlapTester.pointInRectangle(backBounds, touchPoint)){
					Log.d("AreaSelectScreen", "back pressed");
					AssetsSound.playSound(AssetsSound.clickSound);
					game.setScreen(new MainMenuScreen(glGame));
					return;
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

		for(int i = 0; i < Settings.noOfAreas; i++){
			batcher.drawSprite(areaBounds[i].lowerLeft.x + areaBounds[i].width/2, 
					areaBounds[i].lowerLeft.y + areaBounds[i].height/2, 
					areaBounds[i].width, 
					areaBounds[i].height, 
					AssetsMain.areas.get(i));
		}
		
		batcher.drawSprite(85, 480 - 453, 40, 40, Settings.soundEnabled?AssetsMain.soundOn:
			AssetsMain.soundOff);
		batcher.drawSprite(30, 480 - 456, 40, 40, Settings.musicEnabled?AssetsMain.musicOn:
			AssetsMain.musicOff);
		batcher.drawSprite(35, 480 - 20, 55, 35, AssetsMain.backButton);
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























