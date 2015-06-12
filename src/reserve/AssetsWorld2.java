package reserve;

import java.util.ArrayList;

import com.amogh.androidgames.framework.gl.Animation;
import com.amogh.androidgames.framework.gl.Texture;
import com.amogh.androidgames.framework.gl.TextureRegion;
import com.amogh.androidgames.framework.impl.FlareMap;
import com.amogh.androidgames.framework.impl.GLGame;

public class AssetsWorld2 {
	
	public static final int numLevels = 17;
	public static final int areaNo = 2;
	public static Texture assetsWorld2;

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

	public static ArrayList<ArrayList<String>> maps;

	public static void load(GLGame game){
		assetsWorld2 = new Texture(game, "world2Atlas.png");
		
		background = new TextureRegion(assetsWorld2, 0, 0, 800, 480);
		arrow = new TextureRegion(assetsWorld2, 0, 481, 339, 56);
		stone = new TextureRegion(assetsWorld2, 973, 164, 38, 68);
		coinBronze = new TextureRegion(assetsWorld2, 511, 582, 70, 70);
		coinGold = new TextureRegion(assetsWorld2, 612, 481, 70, 70);
		coinSilver = new TextureRegion(assetsWorld2, 410, 683, 70, 70);
		clownFish = new TextureRegion(assetsWorld2, 0, 538, 208, 147);
		gemGreen = new TextureRegion(assetsWorld2, 902, 235, 70, 70);
		gemYellow = new TextureRegion(assetsWorld2, 272, 887, 70, 70);
		spellBound = new TextureRegion(assetsWorld2, 612, 552, 70, 70);
		magnet = new TextureRegion(assetsWorld2, 201, 887, 70, 70);
		octopus = new TextureRegion(assetsWorld2, 201, 739, 200, 147);
		purpleFish = new TextureRegion(assetsWorld2, 209, 538, 200, 200);
		anchor = new TextureRegion(assetsWorld2, 0, 686, 200, 200);
		bubble = new TextureRegion(assetsWorld2, 902, 164, 70, 70);
		orangeFish = new TextureRegion(assetsWorld2, 801, 0, 200, 163);
		swordFish = new TextureRegion(assetsWorld2, 0, 887, 200, 72);
		ring = new Animation(0.2f,
				new TextureRegion(assetsWorld2, 410, 481, 100, 100),
				new TextureRegion(assetsWorld2, 801, 164, 100, 100),
				new TextureRegion(assetsWorld2, 410, 582, 100, 100),
				new TextureRegion(assetsWorld2, 511, 481, 100, 100),
				new TextureRegion(assetsWorld2, 801, 265, 100, 100));

		maps = new ArrayList<ArrayList<String>>();

		for(int i = 0; i < numLevels; i++){
			maps.add(FlareMap.loadMap(game.getFileIO(), "level" + (i+1) + ".txt"));
		}
		
	}

	public static void reload(){
		assetsWorld2.reload();
	}
	
	public static void unload(){
		assetsWorld2.dispose();
	}

}
