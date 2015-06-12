package com.amogh.androidgames.arrowpilotrev;

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
	public static Animation bee;
	public static Animation ring;

	public static ArrayList<ArrayList<String>> mapsEasy;
	public static ArrayList<ArrayList<String>> mapsAdvanced;

	public static void load(GLGame game){
/*		assetsWorld1 = new Texture(game, "world1Atlas.png");
		
		background = new TextureRegion(assetsWorld1, 0, 0, 800, 480);
		arrow = new TextureRegion(assetsWorld1, 0, 481, 339, 56);
		blades = new TextureRegion(assetsWorld1, 201, 653, 103, 200);
		coinBronze = new TextureRegion(assetsWorld1, 71, 868, 70, 70);
		coinGold = new TextureRegion(assetsWorld1, 142, 868, 70, 70);
		coinSilver = new TextureRegion(assetsWorld1, 902, 115, 70, 70);
		apple = new TextureRegion(assetsWorld1, 0, 538, 200, 214);
		spellBound = new TextureRegion(assetsWorld1, 305, 683, 70, 70);
		magnet = new TextureRegion(assetsWorld1, 0, 868, 70,70);
		gemGreen = new TextureRegion(assetsWorld1, 561, 481, 70, 70);
		gemYellow = new TextureRegion(assetsWorld1, 460, 582, 70, 70);
		spider = new TextureRegion(assetsWorld1, 305, 754, 70, 70);
		watermelon = new TextureRegion(assetsWorld1, 376, 683, 70, 70);
		weight = new TextureRegion(assetsWorld1, 213, 854, 70, 70);
		grapes = new TextureRegion(assetsWorld1, 902, 186, 70, 70);
		
		bee = new Animation(0.1f,
				new TextureRegion(assetsWorld1, 201, 538, 157, 114),
				new TextureRegion(assetsWorld1, 0, 753, 157, 114),
				new TextureRegion(assetsWorld1, 801, 0, 157, 114),
				new TextureRegion(assetsWorld1, 0, 753, 157, 114));

		ring = new Animation(0.2f,
				new TextureRegion(assetsWorld1, 359, 481, 100, 100),
				new TextureRegion(assetsWorld1, 801, 115, 100, 100),
				new TextureRegion(assetsWorld1, 359, 582, 100, 100),
				new TextureRegion(assetsWorld1, 460, 481, 100, 100),
				new TextureRegion(assetsWorld1, 801, 216, 100, 100));*/
		
		assetsWorld1 = new Texture(game, "textureWorld1.png");
		bgWorld = new Texture(game, "backgroundWorld1.png");
		
		background = new TextureRegion(bgWorld, 0, 0, 800, 480);
		arrow = new TextureRegion(assetsWorld1, 0, 213, 200, 33);
		blades = new TextureRegion(assetsWorld1, 405, 175, 26, 50);
		coinBronze = new TextureRegion(assetsWorld1, 201, 196, 50, 50);
		coinGold = new TextureRegion(assetsWorld1, 402, 0, 50, 50);
		coinSilver = new TextureRegion(assetsWorld1, 252, 193, 50, 50);
		apple = new TextureRegion(assetsWorld1, 201, 142, 50, 53);
		spellBound = new TextureRegion(assetsWorld1, 303, 142, 50, 50);
		magnet = new TextureRegion(assetsWorld1, 252, 142, 50, 50);
		gemGreen = new TextureRegion(assetsWorld1, 354, 193, 50, 35);
		gemYellow = new TextureRegion(assetsWorld1, 405, 139, 50, 35);
		spider = new TextureRegion(assetsWorld1, 402, 51, 50, 50);
		watermelon = new TextureRegion(assetsWorld1, 453, 0, 50, 50);
		weight = new TextureRegion(assetsWorld1, 354, 142, 50, 50);
		grapes = new TextureRegion(assetsWorld1, 303, 193, 50, 50);
		
		bee = new Animation(0.1f,
				new TextureRegion(assetsWorld1, 453, 51, 50, 36),
				new TextureRegion(assetsWorld1, 402, 102, 50, 36),
				new TextureRegion(assetsWorld1, 453, 88, 50, 36),
				new TextureRegion(assetsWorld1, 402, 102, 50, 36));

		ring = new Animation(0.2f,
				new TextureRegion(assetsWorld1, 0, 0, 200, 70),
				new TextureRegion(assetsWorld1, 0, 71, 200, 70),
				new TextureRegion(assetsWorld1, 0, 142, 200, 70),
				new TextureRegion(assetsWorld1, 201, 0, 200, 70),
				new TextureRegion(assetsWorld1, 201, 71, 200, 70));
		
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
