package com.amogh.androidgames.arrowpilotrev;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.amogh.androidgames.framework.Game;
import com.amogh.androidgames.framework.Input.TouchEvent;
import com.amogh.androidgames.framework.gl.Camera2D;
import com.amogh.androidgames.framework.gl.FPSCounter;
import com.amogh.androidgames.framework.gl.SpriteBatcher;
import com.amogh.androidgames.framework.impl.GLGame;
import com.amogh.androidgames.framework.impl.GLScreen;
import com.amogh.androidgames.framework.math.OverlapTester;
import com.amogh.androidgames.framework.math.Rectangle;
import com.amogh.androidgames.framework.math.Vector2;

public class GameScreen extends GLScreen{
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_CLEAR = 3;
	static final int GAME_OVER = 4;

	static final int AREA1 = 0;
	static final int AREA2 = 1;
	
	int state;
	Camera2D guiCam;
	Vector2 touchPoint;
	SpriteBatcher batcher;
	WorldEndless world;
	WorldListener worldListener;
	WorldRendererEndless renderer;
	Rectangle pauseBounds;
	Rectangle resumeBounds;
	Rectangle quitBounds;
	Rectangle nextLevelBounds;
	Rectangle levelSelectBounds;
	Rectangle tryAgainBounds;
	Rectangle mainMenuBounds;
	int lastScore;
	String scoreString;
	int levelNumber;
	int area;
	FPSCounter fps;

	float gameOverTimeOut = 1;
	float starTimeOut = 2.5f;

	public GameScreen(Game game, int areaNumber){
		super(game);
		AssetsGameScreen.load((GLGame) game);

		this.area = areaNumber;
		state = GAME_READY;
		guiCam = new Camera2D(glGraphics, 800, 480);
		touchPoint = new Vector2();
		batcher = new SpriteBatcher(glGraphics, 2000);
		fps = new FPSCounter();
		
		if(area == AREA1){
			Log.d("GameScreen", "Loading assetsWorld1");
			AssetsWorld1.load((GLGame)game);
			ArrowPilot.assetsToUse = ArrowPilot.USE_ASSETS_AREA_1;
		}
		if(area == AREA2){
			AssetsWorld2.load((GLGame)game);
			ArrowPilot.assetsToUse = ArrowPilot.USE_ASSETS_AREA_2;
		}
		
		worldListener = new WorldListener(){
			public void push(){
				AssetsSound.playSound(AssetsSound.push);
			}
			public void pickUp(){
				AssetsSound.playSound(AssetsSound.pickup);
			}
			public void hit(){
				AssetsSound.playSound(AssetsSound.hit);
			}
			public void powerUp(){
				AssetsSound.playSound(AssetsSound.powerup);
			}
		};

		world = new WorldEndless(worldListener, area);
		renderer  = new WorldRendererEndless(glGraphics, batcher, world, area);

		pauseBounds = new Rectangle(0, 0, 35, 45);
		resumeBounds = new Rectangle(400 - 133, 480 - 172 - 41, 266, 82);
		quitBounds = new Rectangle(400 - 90, 480 - 280 - 40, 180, 81);
		nextLevelBounds = new Rectangle(255 - 225/2, 480 - 284 - 51/2, 225, 51);
		levelSelectBounds = new Rectangle(565 - 225/2, 480 - 284 - 51/2, 225, 51);
		tryAgainBounds = new Rectangle(302 - 65 - 5, 480 - 285 - 20 - 5, 140, 50);
		mainMenuBounds = new Rectangle(501 - 65 - 5, 480 - 285 - 20 - 5, 140, 50);
		lastScore = 0;
		scoreString = "0";
	}

	public void update(float deltaTime){

		fps.logFrame();
		
		if(!AssetsGameScreen.isLoaded){
			AssetsGameScreen.load(glGame);
		}

		if(deltaTime > 0.1f)
			deltaTime = 0.1f;

		switch(state){
		case GAME_READY:
			updateReady();
			break;
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		/*case GAME_LEVEL_CLEAR:
			updateLevelClear(deltaTime);
			break;
			*/
		case GAME_OVER:
			updateGameOver();
			break;
		}
	}

	private void updateReady(){
		if(game.getInput().getTouchEvents().size() > 0){
			state = GAME_RUNNING;
		}
	}

	private void updateRunning(float deltaTime){
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		float touchY = 0;
		float touchX = 0;
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type != TouchEvent.TOUCH_UP)
				continue;

			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);

			if(OverlapTester.pointInRectangle(pauseBounds, touchPoint)){
				AssetsSound.playSound(AssetsSound.clickSound);
				state = GAME_PAUSED;
				return;
			}	
			touchY = touchPoint.y;
			touchX = touchPoint.x;
		}
		world.update(deltaTime, touchY, touchX);
		if(world.score != lastScore){
			lastScore = world.score;
			scoreString = lastScore + "";
		}
		if(world.state == WorldEndless.WORLD_STATE_LEVEL_CLEAR){
			state = GAME_LEVEL_CLEAR;
		}
		if(world.state == WorldEndless.WORLD_STATE_GAME_OVER){
			state = GAME_OVER;
			if(lastScore >= Settings.highscores[0])
				scoreString = "NEW HIGHSCORE: " + lastScore;
			else
				scoreString = "" + lastScore;
			Settings.addScore(lastScore);
			Settings.save(game.getFileIO());
		}
	}

	private void updatePaused(){
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type != TouchEvent.TOUCH_UP)
				continue;

			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);

			if(OverlapTester.pointInRectangle(resumeBounds, touchPoint)){
				AssetsSound.playSound(AssetsSound.clickSound);
				state = GAME_RUNNING;
				return;
			}

			if(OverlapTester.pointInRectangle(quitBounds, touchPoint)){
				AssetsMain.load(glGame);
				AssetsSound.playSound(AssetsSound.clickSound);
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}

/*	private void updateLevelClear(float deltaTime){
		Settings.levelStates.get(area).set(levelNumber + 1, true);
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type != TouchEvent.TOUCH_UP)
				continue;

			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);

			if(OverlapTester.pointInRectangle(nextLevelBounds, touchPoint)){
				if(levelNumber <= 8){
					game.setScreen(new GameScreen(glGame, area));
					return;
				}
				return;
			}
			if(OverlapTester.pointInRectangle(levelSelectBounds, touchPoint)){
				AssetsMain.load(glGame);
				game.setScreen(new LevelSelectScreen(game, area));	
				return;
			}
		}
	}
	
	*/

	private void updateGameOver() {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type != TouchEvent.TOUCH_UP)
				continue;

			touchPoint.set(event.x, event.y);
			guiCam.touchToWorld(touchPoint);

			if(OverlapTester.pointInRectangle(tryAgainBounds, touchPoint)){
				game.setScreen(new GameScreen(glGame, area));
				return;
			}

			if(OverlapTester.pointInRectangle(mainMenuBounds, touchPoint)){
				AssetsMain.load(glGame);
				game.setScreen(new MainMenuScreen(game));
			}
		}
	}

	@Override
	public void present(float deltaTime){
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_TEXTURE_2D);

		renderer.render();

		guiCam.setVeiwPortAndMatrices();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		batcher.beginBatch(AssetsGameScreen.gameScreenTexture);
		switch(state){
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING:
			presentRunning();
			break;
		case GAME_LEVEL_CLEAR:
			presentLevelClear(deltaTime);
			break;
		case GAME_PAUSED:
			presentPaused();
			break;
		case GAME_OVER:
			presentGameOver(deltaTime);
			break;
		}
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}

	private void presentReady(){
		batcher.drawSprite(415, 480 - 216, 310, 83, AssetsGameScreen.ready);
	}

	private void presentPaused(){
		batcher.drawSprite(400, 480 - 172, 266, 82, AssetsGameScreen.resumeButton);
		batcher.drawSprite(400, 480 - 280, 180, 81, AssetsGameScreen.quitButton);
		AssetsGameScreen.font.drawText(batcher, scoreString, 20, 480 - 30);
	}

	private void presentRunning(){
		batcher.drawSprite(15, 19, 30, 38, AssetsGameScreen.pauseButton);
		AssetsGameScreen.font.drawText(batcher, scoreString, 20, 480 - 30);
	}

	private void presentLevelClear(float deltaTime){
		gameOverTimeOut -= deltaTime;
		starTimeOut -= deltaTime;
		batcher.drawSprite(403, 480 - 182, 619, 85, AssetsGameScreen.levelClear);
		if(gameOverTimeOut < 0.5f)
			batcher.drawSprite(255, 480 - 284, 225, 51, AssetsGameScreen.nextLevelButton);
		if(gameOverTimeOut < 0)
			batcher.drawSprite(565, 480 - 284, 225, 51, AssetsGameScreen.levelSelectButton);
		float ratio = 0;
		ratio = (float)world.pickUpCounter / (float)world.totalPickUps;
		if(ratio > 0.5f && starTimeOut < 0.9f){
			batcher.drawSprite(115, 357, 80, 80, AssetsGameScreen.star);
		}
		if(ratio > 0.7f && starTimeOut < 0.45f)
			batcher.drawSprite(115 + 44, 357, 80, 80, AssetsGameScreen.star);
		if(ratio > 0.9f && starTimeOut < 0)
			batcher.drawSprite(115 + 88, 357, 80, 80, AssetsGameScreen.star);
	}

	private void presentGameOver(float deltaTime){
		gameOverTimeOut -= deltaTime;
		batcher.drawSprite(400, 480 - 240 + 25 + 20, 350, 150, AssetsGameScreen.gameOver);
		if(gameOverTimeOut < 0.5f)
			batcher.drawSprite(302, 480 - 285, 130, 40, AssetsGameScreen.tryAgainButton);
		if(gameOverTimeOut < 0)
			batcher.drawSprite(501, 480 - 285, 130, 40, AssetsGameScreen.mainMenuButton);

		float scoreWidth = AssetsGameScreen.font.glyphWidth * scoreString.length();
		
		AssetsGameScreen.font.drawText(batcher, scoreString, 20, 480 - 30);
	}

	@Override
	public void pause() {
		if(state == GAME_RUNNING)
			state = GAME_PAUSED;
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		AssetsGameScreen.unload();
	}
}





















