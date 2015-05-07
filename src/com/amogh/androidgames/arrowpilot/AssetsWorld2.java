package com.amogh.androidgames.arrowpilot;

import java.util.ArrayList;

import com.amogh.androidgames.framework.gl.Animation;
import com.amogh.androidgames.framework.gl.Texture;
import com.amogh.androidgames.framework.gl.TextureRegion;
import com.amogh.androidgames.framework.impl.FlareMap;
import com.amogh.androidgames.framework.impl.GLGame;

public class AssetsWorld2 {
	
	public static final int numLevelsAdvanced = AssetsWorld1.numLevelsAdvanced;
	public static final int numLevelsEasy = AssetsWorld1.numLevelsEasy;
	public static final int areaNo = 2;
	public static Texture assetsWorld2;
	public static Texture bgWorld2;
	
	public static TextureRegion background;
	
	public static TextureRegion arrow;
	public static TextureRegion coinBronze;
	public static TextureRegion coinGold;
	public static TextureRegion gemBlue;
	public static TextureRegion magnet;
	public static TextureRegion gemGreen;
	public static TextureRegion gemYellow;
	public static TextureRegion octopus;
	public static TextureRegion clownFish;
	public static TextureRegion anchor;
	public static TextureRegion spellBound;
	public static TextureRegion orangeFish;
	public static TextureRegion purpleFish;
	public static TextureRegion bubble;
	public static TextureRegion coinSilver;
	public static TextureRegion stone;
	public static TextureRegion swordFish;
	public static Animation ring;

	public static ArrayList<ArrayList<String>> mapsEasy;
	public static ArrayList<ArrayList<String>> mapsAdvanced;

	public static void load(GLGame game){
		assetsWorld2 = new Texture(game, "textureWorld2.png");
		bgWorld2 = new Texture(game, "bgSeven2.png");
		
		background = new TextureRegion(bgWorld2, 0, 0, 1708, 480);
		arrow = new TextureRegion(assetsWorld2, 0, 213, 200, 33);
		stone = new TextureRegion(assetsWorld2, 51, 283, 38, 68);
		coinBronze = new TextureRegion(assetsWorld2, 453, 0, 50, 50);
		coinGold = new TextureRegion(assetsWorld2, 453, 51, 50, 50);
		coinSilver = new TextureRegion(assetsWorld2, 352, 193, 50, 50);
		clownFish = new TextureRegion(assetsWorld2, 0, 289, 50, 35);
		gemGreen = new TextureRegion(assetsWorld2, 51, 247, 50, 35);
		gemYellow = new TextureRegion(assetsWorld2, 0, 325, 50, 35);
		spellBound = new TextureRegion(assetsWorld2, 454, 193, 50, 50);
		magnet = new TextureRegion(assetsWorld2, 402, 51, 50, 50);
		octopus = new TextureRegion(assetsWorld2, 201, 142, 150, 110);
		purpleFish = new TextureRegion(assetsWorld2, 403, 193, 50, 50);
		anchor = new TextureRegion(assetsWorld2, 402, 0, 50, 50);
		orangeFish = new TextureRegion(assetsWorld2, 0, 247, 50, 41);
		swordFish = new TextureRegion(assetsWorld2, 352, 142, 139, 50);
		ring = new Animation(0.2f,
				new TextureRegion(assetsWorld2, 0, 0, 200, 70),
				new TextureRegion(assetsWorld2, 0, 71, 200, 70),
				new TextureRegion(assetsWorld2, 0, 142, 200, 70),
				new TextureRegion(assetsWorld2, 201, 0, 200, 70),
				new TextureRegion(assetsWorld2, 201, 71, 200, 70));

		mapsEasy = new ArrayList<ArrayList<String>>();
		mapsAdvanced = new ArrayList<ArrayList<String>>();

		for(int i = 0; i < numLevelsEasy; i++){
			mapsEasy.add(FlareMap.loadMap(game.getFileIO(), "tut" + (i+1) + ".txt"));
		}
		
		for(int i = 0; i < numLevelsAdvanced; i++){
			mapsAdvanced.add(FlareMap.loadMap(game.getFileIO(), "level" + (i+1) + ".txt"));
		}
	}

	public static void reload(){
		assetsWorld2.reload();
		bgWorld2.reload();
	}
	
	public static void unload(){
		assetsWorld2.dispose();
		bgWorld2.reload();
	}

}
