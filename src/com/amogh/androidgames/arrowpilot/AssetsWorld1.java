package com.amogh.androidgames.arrowpilot;

import java.util.ArrayList;
import com.amogh.androidgames.framework.gl.Animation;
import com.amogh.androidgames.framework.gl.Texture;
import com.amogh.androidgames.framework.gl.TextureRegion;
import com.amogh.androidgames.framework.impl.FlareMap;
import com.amogh.androidgames.framework.impl.GLGame;

public class AssetsWorld1 {
	public static Texture assetsWorld1;
	public static Texture bgWorld;
	
	public static final int numLevelsAdvanced = 17;
	public static final int numLevelsEasy = 5;
	public static final int areaNo = 1;

	public static TextureRegion background;
	
	public static TextureRegion arrow;
	public static TextureRegion blades;
	public static TextureRegion coinBronze;
	public static TextureRegion coinGold;
	public static TextureRegion gemBlue;
	public static TextureRegion apple;
	public static TextureRegion magnet;
	public static TextureRegion gemGreen;
	public static TextureRegion gemYellow;
	public static TextureRegion coinSilver;
	public static TextureRegion spider;
	public static TextureRegion watermelon;
	public static TextureRegion weight;
	public static TextureRegion spellBound;
	public static TextureRegion grapes;
	public static ArrayList<TextureRegion> leaves;
	public static TextureRegion sun;
	public static TextureRegion cloud;
	public static Animation bee;
	public static Animation ring;

	public static ArrayList<ArrayList<String>> mapsEasy;
	public static ArrayList<ArrayList<String>> mapsAdvanced;

	public static void load(GLGame game){		
		assetsWorld1 = new Texture(game, "textureWorld1.png");
		bgWorld = new Texture(game, "background1.png");
		
		background = new TextureRegion(bgWorld, 0, 0, 1706, 480);
		arrow = new TextureRegion(assetsWorld1, 0, 213, 200, 33);
		blades = new TextureRegion(assetsWorld1, 203, 380, 26, 50);
		coinBronze = new TextureRegion(assetsWorld1, 101, 391, 50, 50);
		coinGold = new TextureRegion(assetsWorld1, 202, 293, 50, 50);
		coinSilver = new TextureRegion(assetsWorld1, 152, 391, 50, 50);
		apple = new TextureRegion(assetsWorld1, 202, 239, 50, 53);
		spellBound = new TextureRegion(assetsWorld1, 253, 290, 50, 50);
		magnet = new TextureRegion(assetsWorld1, 253, 239, 50, 50);
		gemGreen = new TextureRegion(assetsWorld1, 202, 344, 50, 35);
		gemYellow = new TextureRegion(assetsWorld1, 153, 442, 50, 35);
		spider = new TextureRegion(assetsWorld1, 304, 239, 50, 50);
		watermelon = new TextureRegion(assetsWorld1, 304, 290, 50, 50);
		weight = new TextureRegion(assetsWorld1, 355, 215, 50, 50);
		grapes = new TextureRegion(assetsWorld1, 253, 341, 50, 50);
		
		bee = new Animation(0.1f,
				new TextureRegion(assetsWorld1, 0, 439, 50, 36),
				new TextureRegion(assetsWorld1, 51, 442, 50, 36),
				new TextureRegion(assetsWorld1, 102, 442, 50, 36),
				new TextureRegion(assetsWorld1, 51, 442, 50, 36));

		ring = new Animation(0.2f,
				new TextureRegion(assetsWorld1, 0, 0, 200, 70),
				new TextureRegion(assetsWorld1, 0, 71, 200, 70),
				new TextureRegion(assetsWorld1, 0, 142, 200, 70),
				new TextureRegion(assetsWorld1, 201, 0, 200, 70),
				new TextureRegion(assetsWorld1, 201, 71, 200, 70));
		
		leaves = new ArrayList<TextureRegion>();
		leaves.add(new TextureRegion(assetsWorld1, 201, 142, 140, 96));
		leaves.add(new TextureRegion(assetsWorld1, 402, 0, 100, 115));
		leaves.add(new TextureRegion(assetsWorld1, 101, 247, 100, 78));
		leaves.add(new TextureRegion(assetsWorld1, 101, 326, 100, 64));
		leaves.add(new TextureRegion(assetsWorld1, 0, 247, 100, 95));
		
		sun = new TextureRegion(assetsWorld1, 0, 343, 100, 95);
		cloud = new TextureRegion(assetsWorld1, 342, 142, 120, 72);
		
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
		assetsWorld1.reload();
		bgWorld.reload();
	}
	
	public static void unload(){
		assetsWorld1.dispose();
		bgWorld.dispose();
	}
}
