package com.amogh.androidgames.arrowpilotrev;

import com.amogh.androidgames.framework.Sound;
import com.amogh.androidgames.framework.gl.Font;
import com.amogh.androidgames.framework.gl.Texture;
import com.amogh.androidgames.framework.gl.TextureRegion;
import com.amogh.androidgames.framework.impl.GLGame;

public class AssetsGameScreen {

	public static boolean isLoaded = false;
	
	public static Texture gameScreenTexture;
	
	public static TextureRegion gameOver;
	public static TextureRegion levelClear;
	public static TextureRegion levelSelectButton;
	public static TextureRegion mainMenuButton;
	public static TextureRegion nextLevelButton;
	public static TextureRegion ready;
	public static TextureRegion star;
	public static TextureRegion tryAgainButton;
	public static TextureRegion pauseButton;
	public static TextureRegion playButton;
	public static TextureRegion quitButton;
	public static TextureRegion resumeButton;
	public static Font font;
	
	public static void load(GLGame game){
		isLoaded = true;
				
		gameScreenTexture = new Texture(game, "textureGameScreen.png");
		
		gameOver = new TextureRegion(gameScreenTexture, 0, 0, 350, 82);
		levelClear = new TextureRegion(gameScreenTexture, 0, 83, 350, 51);
		levelSelectButton = new TextureRegion(gameScreenTexture, 351, 78, 150, 31);
		mainMenuButton = new TextureRegion(gameScreenTexture, 257, 172, 150, 30);
		nextLevelButton = new TextureRegion(gameScreenTexture, 351, 42, 150, 35);
		ready = new TextureRegion(gameScreenTexture, 0, 392, 200, 60);
		star = new TextureRegion(gameScreenTexture, 358, 203, 70, 70);
		tryAgainButton = new TextureRegion(gameScreenTexture, 257, 135, 150, 36);
		pauseButton = new TextureRegion(gameScreenTexture, 257, 320, 50, 81);
		playButton = new TextureRegion(gameScreenTexture, 257, 203, 100, 116);
		quitButton = new TextureRegion(gameScreenTexture, 408, 110, 100, 44);
		resumeButton = new TextureRegion(gameScreenTexture, 351, 0, 150, 41);
		
		font = new Font(gameScreenTexture, 0, 135, 8, 32, 32);
	}
	
	public static void reload(){
		gameScreenTexture.reload();
	}
	
	public static void unload(){
		isLoaded = false;
		gameScreenTexture.dispose();
	}
	
	public static void playSound(Sound sound){
		if(Settings.soundEnabled)
			sound.play(1);
	}
}
