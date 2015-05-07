package com.amogh.androidgames.arrowpilot;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.amogh.androidgames.framework.Game;
import com.amogh.androidgames.framework.Input.TouchEvent;
import com.amogh.androidgames.framework.gl.Camera2D;
import com.amogh.androidgames.framework.gl.SpriteBatcher;
import com.amogh.androidgames.framework.impl.GLScreen;
import com.amogh.androidgames.framework.math.OverlapTester;
import com.amogh.androidgames.framework.math.Rectangle;
import com.amogh.androidgames.framework.math.Vector2;

public class HighScoresScreen extends GLScreen{
	Camera2D guiCam;
	SpriteBatcher batcher;
	Rectangle backBounds;
	Vector2 touchPoint;
	String[] highscores;
	float xOffset = 0;
	
	public HighScoresScreen(Game game){
		super(game);
		
		guiCam = new Camera2D(glGraphics, 800, 480);
		backBounds = new Rectangle(0, 480 - 40, 62, 40);
		touchPoint = new Vector2();
		batcher = new SpriteBatcher(glGraphics, 100);
		highscores = new String[5];
		for(int i = 0; i < 5; i++){
			highscores[i] = (i + 1) + ". " + Settings.highscores[i];
			xOffset = Math.max(highscores[i].length() * AssetsMain.font.glyphWidth, xOffset);
		}
		xOffset = 400 - xOffset / 2;
	}
	
	@Override
	public void update(float deltaTime){
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);
			
			if(event.type == TouchEvent.TOUCH_UP){
				if(OverlapTester.pointInRectangle(backBounds, touchPoint)){
					game.setScreen(new MainMenuScreen(game));
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
		batcher.endBatch();
		
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		batcher.drawSprite(400, 480 - 70, 460, 100, AssetsMain.highScoresText);
		
		float y = 480 - 340;
		for(int i = 4; i >= 0; i--){
			AssetsMain.font.drawText(batcher, highscores[i], xOffset, y);
			y += AssetsMain.font.glyphHeight + 10;
		}
		
		batcher.drawSprite(30, 480 - 26, 50, 50, AssetsMain.backButton);
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}
	
	@Override
	public void resume(){
		
	}
	
	@Override
	public void pause(){
		
	}
	
	@Override 
	public void dispose(){
		
	}
}
