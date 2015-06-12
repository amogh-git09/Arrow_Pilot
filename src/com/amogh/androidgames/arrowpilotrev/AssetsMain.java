package com.amogh.androidgames.arrowpilotrev;

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
		
		areas.add(new TextureRegion(mainSprite, 446,617,100,300));
		areas.add(new TextureRegion(mainSprite, 801,316,100,300));
		areas.add(new TextureRegion(mainSprite, 547,617,100,300));
		areas.add(new TextureRegion(mainSprite, 902,316,100,300));
		areas.add(new TextureRegion(mainSprite, 648,597,100,300));
		/*
		levels.add(new TextureRegion(mainSprite, 801, 0, 200, 200));
		levels.add(new TextureRegion(mainSprite, 801, 201, 200, 200));
		levels.add(new TextureRegion(mainSprite, 404, 617, 200, 200));
		levels.add(new TextureRegion(mainSprite, 801, 402, 200, 200));
		levels.add(new TextureRegion(mainSprite, 605, 603, 200, 200));
		levels.add(new TextureRegion(mainSprite, 806, 603, 200, 200));
		levels.add(new TextureRegion(mainSprite, 1002, 0, 200, 200));
		levels.add(new TextureRegion(mainSprite, 1002, 201, 200, 200));
		levels.add(new TextureRegion(mainSprite, 1203, 0, 200, 200));
		levels.add(new TextureRegion(mainSprite, 203, 617, 200, 200));
		levels.add(new TextureRegion(mainSprite, 1404, 0, 200, 200));
		*/
		gameTitle = new TextureRegion(mainSprite, 0,481,587,135);
		backButton = new TextureRegion(mainSprite, 257,669,188,242);
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






















