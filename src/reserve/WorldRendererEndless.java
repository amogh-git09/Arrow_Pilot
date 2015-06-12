package reserve;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.amogh.androidgames.framework.gl.Animation;
import com.amogh.androidgames.framework.gl.Camera2D;
import com.amogh.androidgames.framework.gl.SpriteBatcher;
import com.amogh.androidgames.framework.gl.TextureRegion;
import com.amogh.androidgames.framework.impl.GLGraphics;

public class WorldRendererEndless {
	static final float FRUSTUM_WIDTH = 16;
	static final float FRUSTUM_HEIGHT = 10;
	static final int AREA1 = 0;
	static final int AREA2 = 1;
	GLGraphics glGraphics;
	WorldEndless world;
	int area;
	Camera2D cam;
	SpriteBatcher batcher;

	public WorldRendererEndless(GLGraphics glGraphics, SpriteBatcher batcher, 
			WorldEndless world, int area){
		this.glGraphics = glGraphics;
		this.batcher = batcher;
		this.area = area;
		this.cam = new Camera2D(glGraphics, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.world = world;
	}

	public void render(){
		if(world.arrow.position.x + 5 > cam.position.x){
			cam.position.x = world.arrow.position.x + 5;
		}
		cam.setVeiwPortAndMatrices();
		renderBackground();
		renderObjects();
	}

	private void renderBackground(){
		if(area == AREA1)
			batcher.beginBatch(AssetsWorld1.bgWorld);
		if(area == AREA2)
			batcher.beginBatch(AssetsWorld2.assetsWorld2);
		batcher.drawSprite(cam.position.x, cam.position.y, 
				FRUSTUM_WIDTH, FRUSTUM_HEIGHT, area==AREA1?AssetsWorld1.background:
					AssetsWorld2.background);
		batcher.endBatch();
	}

	private void renderObjects(){
		GL10 gl = glGraphics.getGL();
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		if(area == AREA1)
			batcher.beginBatch(AssetsWorld1.assetsWorld1);
		if(area == AREA2)	
			batcher.beginBatch(AssetsWorld2.assetsWorld2);
		renderArrow();
		renderBlades();
		renderCoins();
		renderFruits();
		renderPowerUps();
		renderSwordFishes();
		renderMonsters();
		renderBubbles();
		renderAnchors();
		batcher.endBatch();
		gl.glDisable(GL10.GL_BLEND);
	}

	private void renderArrow(){
		if(!world.arrow.disappear){
			batcher.drawSprite(world.arrow.position.x, world.arrow.position.y, 
					Arrow.ARROW_WIDTH, Arrow.ARROW_HEIGHT, area==AREA1?AssetsWorld1.arrow:
						AssetsWorld2.arrow);
			
			if(world.arrow.spellBound){
					TextureRegion keyFrame = null;
					if(area == AREA1){
						keyFrame = AssetsWorld1.ring.getKeyFrame(world.arrow.ringStateTime, 
								Animation.ANIMATION_LOOPING);
					}
					if(area == AREA2){
						keyFrame = AssetsWorld2.ring.getKeyFrame(world.arrow.ringStateTime, 
								Animation.ANIMATION_LOOPING);
					}
					batcher.drawSprite(world.arrow.position.x, world.arrow.position.y, 
							Arrow.ARROW_WIDTH + 1, 
							Arrow.ARROW_HEIGHT + 1, 
							keyFrame);
			}
		}
	}

	private void renderBlades(){
		int len = world.stones.size();
		for(int i = 0; i < len; i++){
			Stone blade = world.stones.get(i);
			batcher.drawSprite(blade.position.x, 
					blade.position.y, 
					Stone.STONE_WIDTH, Stone.STONE_HEIGHT, area==AREA1?AssetsWorld1.blades:
						AssetsWorld2.stone);
		}	
	}

	private void renderMonsters(){
		int len = world.monsters.size();
		for(int i = 0; i < len; i++){
			Monster m = world.monsters.get(i);
			if(m.type == Monster.TYPE_OCTOPUS){
				batcher.drawSprite(m.position.x, m.position.y, 
						Monster.OCTOPUS_WIDTH, Monster.OCTOPUS_HEIGHT, 
						area==AREA1?AssetsWorld1.spider:AssetsWorld2.octopus);
			}
		}
	}

	private void renderAnchors(){
		int len = world.anchors.size();
		for(int i = 0; i < len; i++){
			Anchor anchor = world.anchors.get(i);
			batcher.drawSprite(anchor.position.x, anchor.position.y, 
					Anchor.ANCHOR_WIDTH, Anchor.ANCHOR_HEIGHT, 
					area==AREA1?AssetsWorld1.weight:AssetsWorld2.anchor);
		}
	}

	private void renderSwordFishes(){
		int len = world.swordFishes.size();
		if(area == AREA1){
			for(int i = 0; i < len; i++){
				SwordFish s = world.swordFishes.get(i);
				TextureRegion keyFrame = AssetsWorld1.bee.getKeyFrame(s.stateTime, 
						Animation.ANIMATION_LOOPING);
				batcher.drawSprite(s.position.x, s.position.y, 
						SwordFish.BEE_WIDTH, SwordFish.BEE_HEIGHT, 
						keyFrame);
			}
		}
		if(area == AREA2){
			for(int i = 0; i < len; i++){
				SwordFish s = world.swordFishes.get(i);
				batcher.drawSprite(s.position.x, s.position.y, 
						SwordFish.SWORDFISH_WIDTH, SwordFish.SWORDFISH_HEIGHT, 
						AssetsWorld2.swordFish);
			}
		}
	}


	private void renderBubbles(){
		if(area == AREA2){
			int len = world.bubbles.size();
			for(int i = 0; i < len; i++){
				Bubble bubble = world.bubbles.get(i);
				batcher.drawSprite(bubble.position.x, bubble.position.y, 
						Bubble.BUBBLE_WIDTH, Bubble.BUBBLE_HEIGHT, 
						AssetsWorld2.bubble);
			}
		}
	}

	private void renderPowerUps(){
		int len = world.powerUps.size();
		for(int i = 0; i < len; i++){
			PowerUp powerUp = world.powerUps.get(i);
			if(powerUp.type == PowerUp.SPELL_BOUND)
				batcher.drawSprite(powerUp.position.x, powerUp.position.y, 
						PowerUp.POWER_UP_WIDTH, 
						PowerUp.POWER_UP_HEIGHT, 
						area==AREA1?AssetsWorld1.spellBound:AssetsWorld2.spellBound);

			
			if(powerUp.type == PowerUp.MAGNET)
				batcher.drawSprite(powerUp.position.x, powerUp.position.y, 
						PowerUp.POWER_UP_WIDTH, 
						PowerUp.POWER_UP_HEIGHT, 
						area==AREA1?AssetsWorld1.magnet:AssetsWorld2.magnet);
		}
	}


	private void renderCoins(){

		int len = world.coins.size();
		for(int i = 0; i < len; i++){
			Coin coin = world.coins.get(i);
			if(coin.type == Coin.COIN_TYPE_GOLD)
				batcher.drawSprite(coin.position.x, 
						coin.position.y, 
						Coin.COIN_WIDTH , 
						Coin.COIN_HEIGHT, 
						area==AREA1?AssetsWorld1.coinGold:
							AssetsWorld2.coinGold);
			if(coin.type == Coin.COIN_TYPE_BRONZE)
				batcher.drawSprite(coin.position.x, 
						coin.position.y, 
						Coin.COIN_WIDTH, 
						Coin.COIN_HEIGHT, 
						area==AREA1?AssetsWorld1.coinBronze:
							AssetsWorld2.coinBronze);
			if(coin.type == Coin.COIN_TYPE_SILVER)
				batcher.drawSprite(coin.position.x, 
						coin.position.y, 
						Coin.COIN_WIDTH, 
						Coin.COIN_HEIGHT, 
						area==AREA1?AssetsWorld1.coinSilver:
							AssetsWorld2.coinSilver);
			if(coin.type == Coin.TYPE_GEM_GREEN)
				batcher.drawSprite(coin.position.x, 
						coin.position.y, 
						Coin.GEM_WIDTH, 
						Coin.GEM_HEIGHT, 
						area==AREA1?AssetsWorld1.gemGreen:
							AssetsWorld2.gemGreen);
			if(coin.type == Coin.TYPE_GEM_YELLOW)
				batcher.drawSprite(coin.position.x, 
						coin.position.y, 
						Coin.GEM_WIDTH, 
						Coin.GEM_HEIGHT, 
						area==AREA1?AssetsWorld1.gemYellow:
							AssetsWorld2.gemYellow);

		}
	}

	private void renderFruits(){

		int len = world.fishes.size();
		for(int i = 0; i < len; i++){
			Fish fish = world.fishes.get(i);
			if(fish.type == Fish.CLOWN_FISH)
				batcher.drawSprite(fish.position.x,
						fish.position.y, 
						area==AREA1?Fish.FRUIT_WIDTH:Fish.FISH_WIDTH, 
								area==AREA1?Fish.FRUIT_HEIGHT:Fish.FISH_HEIGHT,
										area==AREA1?AssetsWorld1.apple:AssetsWorld2.clownFish
						);
			if(fish.type == Fish.ORANGE_FISH)
				batcher.drawSprite(fish.position.x,
						fish.position.y, 
						area==AREA1?Fish.FRUIT_WIDTH:Fish.FISH_WIDTH, 
								area==AREA1?Fish.FRUIT_HEIGHT:Fish.FISH_HEIGHT,
										area==AREA1?AssetsWorld1.watermelon:AssetsWorld2.orangeFish
						);
			if(fish.type == Fish.PURPLE_FISH)
				batcher.drawSprite(fish.position.x,
						fish.position.y, 
						area==AREA1?Fish.FRUIT_WIDTH:Fish.FISH_WIDTH, 
								area==AREA1?Fish.FRUIT_HEIGHT:Fish.FISH_HEIGHT,
										area==AREA1?AssetsWorld1.grapes:AssetsWorld2.purpleFish
						);
		}
	}
}
