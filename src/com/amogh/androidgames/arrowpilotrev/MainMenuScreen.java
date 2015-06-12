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

public class MainMenuScreen extends GLScreen{
	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle soundBounds; 
	Rectangle playBounds;
	Rectangle helpBounds;
	Rectangle highScoresBounds;
	Rectangle musicBounds;
	Vector2 touchPoint;
	
	public MainMenuScreen(Game game){
		super(game);
		
		ArrowPilot.assetsToUse = ArrowPilot.USE_ASSETS_MAIN;
		//AssetsMain.load((GLGame) game);
		//AssetsSound.load((GLGame) game);
		
		guiCam = new Camera2D(glGraphics, 800, 480);
		batcher = new SpriteBatcher(glGraphics, 100);
		musicBounds = new Rectangle(0, 0, 43, 40);
		soundBounds = new Rectangle(44, 0, 43, 40);
		highScoresBounds = new Rectangle(800 - 30, 0, 60, 60);
		playBounds = new Rectangle(315 - 10, 480 - 355, 200, 200);
		touchPoint = new Vector2();
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
				if(OverlapTester.pointInRectangle(playBounds, touchPoint)){
					AssetsSound.playSound(AssetsSound.clickSound);
					game.setScreen(new AreaSelectScreen(game));
					return;
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
				if(OverlapTester.pointInRectangle(highScoresBounds, touchPoint)){
					AssetsSound.playSound(AssetsSound.clickSound);
					game.setScreen(new HighScoresScreen(game));
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
		
		batcher.drawSprite(400, 480 - 257, 186, 183, AssetsMain.playButton);
		batcher.drawSprite(400, 480 - 76, 600, 170, AssetsMain.gameTitle);
		batcher.drawSprite(85, 480 - 453, 40, 40, Settings.soundEnabled?AssetsMain.soundOn:
							AssetsMain.soundOff);
		batcher.drawSprite(30, 480 - 456, 40, 40, Settings.musicEnabled?AssetsMain.musicOn:
							AssetsMain.musicOff);
		batcher.drawSprite(800 - 25, 28, 50, 50, AssetsMain.highScores);
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




















