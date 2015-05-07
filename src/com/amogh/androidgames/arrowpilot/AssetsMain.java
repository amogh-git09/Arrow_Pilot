package com.amogh.androidgames.arrowpilot;

import java.util.ArrayList;
import java.util.List;

import com.amogh.androidgames.framework.gl.Font;
import com.amogh.androidgames.framework.gl.Texture;
import com.amogh.androidgames.framework.gl.TextureRegion;
import com.amogh.androidgames.framework.impl.GLGame;

public class AssetsMain{
	public static Texture mainSprite;
	public static TextureRegion backgroundRegion;

	public static List<TextureRegion> areas;
	public static List<TextureRegion> levels;
	public static TextureRegion gameTitle;
	public static TextureRegion musicOn;
	public static TextureRegion musicOff;
	public static TextureRegion playButton;
	public static TextureRegion soundOn;
	public static TextureRegion highScores;
	public static TextureRegion soundOff;
	public static TextureRegion backButton;
	public static TextureRegion highScoresText;
	
	public static Font font;

	public static void load(GLGame game){		
		mainSprite = new Texture(game, "assetsMainFinal.png");
		
		areas = new ArrayList<TextureRegion>();
		levels = new ArrayList<TextureRegion>();
		
		areas.add(new TextureRegion(mainSprite, 545, 620, 80, 289));
		areas.add(new TextureRegion(mainSprite, 640, 620, 80, 288));
		areas.add(new TextureRegion(mainSprite, 547,617,100,300));
		areas.add(new TextureRegion(mainSprite, 902,316,100,300));
		areas.add(new TextureRegion(mainSprite, 648,597,100,300));
		gameTitle = new TextureRegion(mainSprite, 0, 481, 587, 135);
		backButton = new TextureRegion(mainSprite, 752, 760, 67, 67);
		backgroundRegion = new TextureRegion(mainSprite, 0, 0, 800, 480);
		musicOn = new TextureRegion(mainSprite, 749,617,99,119);
		musicOff = new TextureRegion(mainSprite, 849,617,99,119);
		playButton = new TextureRegion(mainSprite, 801,0,202,199);
		soundOn = new TextureRegion(mainSprite, 588,481,158,115);
		soundOff = new TextureRegion(mainSprite, 801,200,158,115);
		highScores = new TextureRegion(mainSprite, 257,912,100,100);
		highScoresText = new TextureRegion(mainSprite, 0,617,323,51);
		
		font = new Font(mainSprite, 0,669, 8, 32, 32);
	}
	
	public static void reload(){
		mainSprite.reload();
	}
	
	
	public static void unload(){
		mainSprite.dispose();
	}
}






















