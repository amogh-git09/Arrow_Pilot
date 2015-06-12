package reserve;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

import com.amogh.androidgames.framework.math.OverlapTester;

public class WorldEndless {
	public static final float WORLD_WIDTH = 100 * 1000;
	public static final float WORLD_HEIGHT = 10;
	public static final float LEVEL_WIDTH = 100;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_LEVEL_CLEAR = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;

	public static final int BUBBLE = 1;
	public static final int MAGNET = 2;
	public static final int CLOWN_FISH = 3;
	public static final int ORANGE_FISH = 4;
	public static final int SPELL_BOUND = 5;
	public static final int COIN_BRONZE = 8;
	public static final int COIN_GOLD = 9;
	public static final int GEM_GREEN = 10;
	public static final int OCTOPUS = 11;
	public static final int STONE = 12;
	public static final int COIN_SILVER = 15;
	public static final int PURPLE_FISH = 16;
	public static final int GEM_YELLOW = 17;
	public static final int SWORD_FISH = 18;	
	public static final int ANCHOR = 19;

	public static final float BUBBLE_TIME = 0.2f;

	public final Arrow arrow;
	public final List<Stone> stones;
	public final List<Coin> coins;
	public final List<Fish> fishes;
	public final List<SwordFish> swordFishes;
	public final List<PowerUp> powerUps;
	public final List<Bubble> bubbles;
	public final List<Monster> monsters;
	public final List<Anchor> anchors;
	public int[] waterCurrentPos;
	public float bubbleTimer = 0;

	public static final int AREA1 = 0;
	public static final int AREA2 = 1;

	public final WorldListener listener;

	static Random rand;
	public int score;
	int area;
	public int state;
	int levelNumber;
	boolean renderedLevel2 = false;
	float powerUpTimer = 0;
	float timePassed = 0;
	boolean levelEnd = false;
	int pickUpCounter = 0;
	float gameOverTimer = 1.2f;
	int levelNo = 0;
	int totalPickUps = 0;
	public boolean waterCurrent = false;
	float newRenderTime, newRenderTimer = 0;
	int currentPosCounter = 0;

	public WorldEndless(WorldListener worldListener, int area){
		this.arrow = new Arrow(4, 5);
		this.arrow.spellBound = true;
		this.stones = new ArrayList<Stone>();
		this.anchors = new ArrayList<Anchor>();
		this.coins = new ArrayList<Coin>();
		this.fishes = new ArrayList<Fish>();
		this.swordFishes = new ArrayList<SwordFish>();
		this.bubbles = new ArrayList<Bubble>();
		this.powerUps = new ArrayList<PowerUp>();
		this.monsters = new ArrayList<Monster>();
		this.waterCurrentPos = new int[10];
		this.area = area;
		this.listener = worldListener;
		rand = new Random();

		generateLevel(0);

		newRenderTime = (LEVEL_WIDTH-8)/Arrow.ARROW_VELOCITY_X;
		this.score = 0;
		this.state = WORLD_STATE_RUNNING;		
	}

	private void generateLevel(int offset){
		ArrayList<String> map = null;
		if(area == AREA1){
			int ran = randInt(0,AssetsWorld1.numLevels-1);
			map = AssetsWorld1.maps.get(ran);
			Log.d("WorldEndless", "area 1 renderer level " + ran);
		}
		if(area == AREA2){
			int ran = randInt(0,AssetsWorld2.numLevels-1);
			map = AssetsWorld2.maps.get(ran);
			Log.d("WorldEndless", "area 2 renderer level " + ran);
		}

		for(int i = 0; i < map.size(); i++){
			String line = map.get(i);
			String[] array = line.split(",");

			for(int j = 0; j < array.length; j++){
				int d = Integer.parseInt(array[j]);

				if(d == 0)
					continue;
				if(d == COIN_BRONZE){
					Coin coin = new Coin(j + offset + Coin.COIN_WIDTH/2, 
							WORLD_HEIGHT - i - Coin.COIN_HEIGHT/2, 
							Coin.COIN_TYPE_BRONZE);
					coins.add(coin);
					totalPickUps++;
				}
				if(d == COIN_SILVER){
					Coin coin = new Coin(j + offset + Coin.COIN_WIDTH/2, 
							WORLD_HEIGHT - i - Coin.COIN_HEIGHT/2, 
							Coin.COIN_TYPE_SILVER);
					coins.add(coin);
					totalPickUps++;
				}
				if(d == COIN_GOLD){
					Coin coin = new Coin(j + offset + Coin.COIN_WIDTH/2, 
							WORLD_HEIGHT - i - Coin.COIN_HEIGHT/2,  
							Coin.COIN_TYPE_GOLD);
					coins.add(coin);
					totalPickUps++;
				}
				if(d == GEM_GREEN){
					Coin coin = new Coin(j + offset + Coin.COIN_WIDTH/2, 
							WORLD_HEIGHT - i - Coin.COIN_HEIGHT/2,  
							Coin.TYPE_GEM_GREEN);
					coins.add(coin);
					totalPickUps++;
				}if(d == GEM_YELLOW){
					Coin coin = new Coin(j + offset + Coin.COIN_WIDTH/2, 
							WORLD_HEIGHT - i - Coin.COIN_HEIGHT/2,  
							Coin.TYPE_GEM_YELLOW);
					coins.add(coin);
					totalPickUps++;
				}
				if(d == STONE){
					Stone stone = new Stone(j + offset + Stone.STONE_WIDTH/2, 
							WORLD_HEIGHT - i-Stone.STONE_HEIGHT/2);
					stones.add(stone);
				}
				if(d == CLOWN_FISH){
					Fish fish = new Fish(j + offset, -1, Fish.CLOWN_FISH);
					fishes.add(fish);
					totalPickUps++;
				}
				if(d == PURPLE_FISH){
					Fish fish = new Fish(j + offset, -1, Fish.PURPLE_FISH);
					fishes.add(fish);
					totalPickUps++;
				}
				if(d == ORANGE_FISH){
					Fish fish = new Fish(j + offset, -1, Fish.ORANGE_FISH);
					fishes.add(fish);
					totalPickUps++;
				}
				if(d == SPELL_BOUND){
					PowerUp spellBound = new PowerUp(j + offset+PowerUp.POWER_UP_WIDTH/2, 
							WORLD_HEIGHT - i-PowerUp.POWER_UP_HEIGHT/2, 
							PowerUp.SPELL_BOUND);
					powerUps.add(spellBound);
				}
				if(d == MAGNET){
					PowerUp magnet = new PowerUp(j + offset + PowerUp.POWER_UP_WIDTH/2, 
							WORLD_HEIGHT - i-PowerUp.POWER_UP_HEIGHT/2, 
							PowerUp.MAGNET);
					powerUps.add(magnet);
				}
				if(d == ANCHOR){
					Anchor anchor = new Anchor(j + offset, 
							WORLD_HEIGHT - i-1,
							Anchor.TYPE_ANCHOR);
					anchors.add(anchor);
				}
				if(d == BUBBLE){
					waterCurrentPos[currentPosCounter++] = j;
				}
				if(d == SWORD_FISH){
					SwordFish fish = new SwordFish(j + offset + SwordFish.SWORDFISH_WIDTH/2, 
							WORLD_HEIGHT - i-SwordFish.SWORDFISH_HEIGHT/2);
					swordFishes.add(fish);
				}
				if(d == OCTOPUS){
					Monster octopus = new Monster(j + offset + Monster.OCTOPUS_WIDTH/2, 
							WORLD_HEIGHT - i-Monster.OCTOPUS_HEIGHT/2, 
							Monster.TYPE_OCTOPUS);
					monsters.add(octopus);
				}
			}
		}
		currentPosCounter = 0;
	}

	public void update(float deltaTime, float touchY, float touchX){
		updateArrow(deltaTime, touchY, touchX);
		checkLevelEnd(deltaTime);
		updateFishes(deltaTime);
		updateCoins(deltaTime);
		updateMonsters(deltaTime);
		updateBubbles(deltaTime);
		updateAnchors(deltaTime);
		updateSwordFishes(deltaTime);
		if(!arrow.hit)
			checkCollisions();
		checkWaterCurrent(deltaTime);
		checkGameOver(deltaTime);
		removeItems();
		timePassed += deltaTime;
		int len = anchors.size()+monsters.size()+coins.size()+stones.size()+
				+fishes.size()+swordFishes.size()+powerUps.size();
		Log.d("WorldEndless", anchors.size()+", "+monsters.size()+", "+coins.size()+
				", "+stones.size()+", "
				+fishes.size()+", "+swordFishes.size()+", "+powerUps.size()+", total "+len
				+ ", time = " + timePassed + " pos " + arrow.position.x);
	}

	private void removeItems(){
		int len = anchors.size();
		for(int i = 0; i < len; i++){
			Anchor a = anchors.get(i);
			if(arrow.position.x > a.position.x + 5){
				anchors.remove(a);
				len = anchors.size();
			}
		}

		len = monsters.size();
		for(int i = 0; i < len; i++){
			Monster a = monsters.get(i);
			if(arrow.position.x > a.position.x + 5){
				monsters.remove(a);
				len = monsters.size();
			}
		}

		len = coins.size();
		for(int i = 0; i < len; i++){
			Coin a = coins.get(i);
			if(arrow.position.x > a.position.x + 5){
				Log.d("WorldEndless", "Removing coin");
				coins.remove(a);
				len = coins.size();
			}
		}

		len = swordFishes.size();
		for(int i = 0; i < len; i++){
			SwordFish a = swordFishes.get(i);
			if(arrow.position.x > a.position.x + 5){
				swordFishes.remove(a);
				len = swordFishes.size();
			}
		}

		len = stones.size();
		for(int i = 0; i < len; i++){
			Stone a = stones.get(i);
			if(arrow.position.x > a.position.x + 5){
				stones.remove(a);
				len = stones.size();
			}
		}

		len = bubbles.size();
		for(int i = 0; i < len; i++){
			Bubble a = bubbles.get(i);
			if(arrow.position.x > a.position.x + 5){
				bubbles.remove(a);
				len = bubbles.size();
			}
		}

		len = powerUps.size();
		for(int i = 0; i < len; i++){
			PowerUp a = powerUps.get(i);
			if(arrow.position.x > a.position.x + 5){
				powerUps.remove(a);
				len = powerUps.size();
			}
		}

		len = fishes.size();
		for(int i = 0; i < len; i++){
			Fish a = fishes.get(i);
			if(arrow.position.x > a.position.x + 5){
				fishes.remove(a);
				len = fishes.size();
			}
		}
	}

	private void checkLevelEnd(float deltaTime){
		newRenderTimer += deltaTime;
		if(newRenderTimer > newRenderTime){
			levelNo++;
			generateLevel(levelNo*(int)LEVEL_WIDTH);
			newRenderTimer = 0;
		}
	}

	private void updateArrow(float deltaTime, float touchY, float touchX){
		if(arrow.magnet){
			arrow.magnetTimer += deltaTime;
			if(arrow.magnetTimer > PowerUp.MAGNET_TIME){
				arrow.magnet = false;
				arrow.magnetTimer = 0;
			}
		}
		if(arrow.spellBound){
			arrow.spellBoundTimer += deltaTime;
			if(arrow.spellBoundTimer > PowerUp.SPELL_BOUND_TIME){
				arrow.spellBoundTimer = 0;
				//arrow.spellBound = false;
			}
		}

		arrow.update(deltaTime, touchY, touchX);
	}

	private void updateCoins(float deltaTime){
		if(arrow.magnet == false)
			return;

		int len = coins.size();
		for(int i = 0; i < len; i++){
			Coin coin = coins.get(i);
			if(arrow.position.distSquared(coin.position) < 121){
				coin.velocity.set(arrow.position.x - coin.position.x + 1.3f, 
						arrow.position.y - coin.position.y);
				coin.velocity.nor();
				coin.velocity.mul(15);
				coin.update(deltaTime);
			}
		}
	}

	private void updateAnchors(float deltaTime){
		int len = anchors.size();
		for(int i = 0; i < len; i++){
			Anchor anchor = anchors.get(i);
			if(anchor.position.x - arrow.position.x < randInt(2, 16)){
				anchor.update(deltaTime);
				if(anchor.position.y < 0){
					anchors.remove(anchor);
					len = anchors.size();
				}
			}
		}
	}

	private void updateMonsters(float deltaTime){
		int len = monsters.size();
		for(int i = 0; i < len; i++){
			Monster octopus = monsters.get(i);
			octopus.update(deltaTime);
		}
	}

	private void updateBubbles(float deltaTime){
		int len = bubbles.size();
		for(int i = 0; i < len; i++){
			Bubble bubble = bubbles.get(i);
			bubble.update(deltaTime);
			if(bubble.position.y > WORLD_HEIGHT){
				bubbles.remove(bubble);
				len = bubbles.size();
			}
		}
	}

	private void updateFishes(float deltaTime){
		for(int i = 0; i < fishes.size(); i++){
			Fish fish = fishes.get(i);
			if(fish.position.x - arrow.position.x < 9)
				fish.update(deltaTime);
		}
	}

	private void updateSwordFishes(float deltaTime){
		int len = swordFishes.size();
		for(int i = 0; i < len; i++){
			SwordFish fish = swordFishes.get(i);
			if(fish.position.x - arrow.position.x < 15)
				fish.update(deltaTime);

			if(fish.position.x < arrow.position.x - 5){
				swordFishes.remove(fish);
				len = swordFishes.size();
			}
		}
	}

	private void checkWaterCurrent(float deltaTime){
		if(arrow.position.x > waterCurrentPos[currentPosCounter] && 
				!(waterCurrentPos[currentPosCounter] == 0)){
			waterCurrent = !waterCurrent;
			currentPosCounter++;
			arrow.waterCurrent = waterCurrent;
		}
		if(waterCurrent){
			bubbleTimer += deltaTime;
			if(bubbleTimer > BUBBLE_TIME){
				bubbles.add(new Bubble(arrow.position.x + randInt(0, 9), 0));
				bubbleTimer = 0;
			}
		}
	}

	public static int randInt(int min, int max) {
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	private void checkCollisions(){
		checkStoneCollision();
		checkCoinCollision();
		checkFishCollision();
		checkMonsterCollision();
		checkSwordFishCollision();
		checkAnchorCollision();
		checkPowerUpCollision();
	}

	private void checkStoneCollision(){
		int len = stones.size();
		for(int i = 0; i < len; i++){
			Stone b = stones.get(i);
			if(OverlapTester.overlapRectangles(arrow.bounds, b.bounds)){
				arrow.hitBlade();
				listener.hit();
			}
		}
	}

	private void checkSwordFishCollision(){
		int len = swordFishes.size();
		for(int i = 0; i < len; i++){
			SwordFish fish = swordFishes.get(i);
			if(OverlapTester.overlapRectangles(arrow.bounds, fish.bounds)){
				arrow.hitBlade();
				listener.hit();
			}
		}
	}

	private void checkAnchorCollision(){
		int len = anchors.size();
		for(int i = 0; i < len; i++){
			Anchor anchor = anchors.get(i);
			if(OverlapTester.overlapRectangles(arrow.bounds, anchor.bounds)){
				arrow.hitBlade();
				listener.hit();
			}
		}
	}

	private void checkMonsterCollision(){
		int len = monsters.size();
		for(int i = 0; i < len; i++){
			Monster b = monsters.get(i);
			if(OverlapTester.overlapRectangles(arrow.bounds, b.bounds)){
				arrow.hitBlade();
				listener.hit();
			}
		}
	}

	private void checkFishCollision(){
		int len = fishes.size();
		for(int i = 0; i < len; i++){
			Fish fish = fishes.get(i);
			if(OverlapTester.overlapRectangles(arrow.bounds, fish.bounds)){
				score += Fish.score;
				fishes.remove(fish);
				len = fishes.size();
				pickUpCounter++;
				listener.pickUp();
			}
		}
	}

	private void checkPowerUpCollision(){
		int len = powerUps.size();
		for(int i = 0; i < len; i++){
			PowerUp powerUp = powerUps.get(i);
			if(OverlapTester.overlapRectangles(arrow.bounds, powerUp.bounds)){
				if(powerUp.type == PowerUp.SPELL_BOUND)
				{
					if(arrow.spellBound){
						arrow.spellBoundTimer = 0;
						powerUps.remove(powerUp);
						len = powerUps.size();
					}else{
						arrow.spellBound = true;
						powerUps.remove(powerUp);
						len = powerUps.size();
					}
				}
				if(powerUp.type == PowerUp.MAGNET){
					if(arrow.magnet){
						arrow.magnetTimer = 0;
						powerUps.remove(powerUp);
						len = powerUps.size();
					}else{
						arrow.magnet = true;
						powerUps.remove(powerUp);
						len = powerUps.size();
					}
				}
				listener.powerUp();
			}
		}
	}

	private void checkCoinCollision(){
		int len = coins.size();
		for(int i = 0; i < len; i++){
			Coin coin = coins.get(i);
			if(OverlapTester.overlapRectangles(arrow.bounds, coin.bounds)){
				score += coin.score;
				coins.remove(coin);
				len = coins.size();
				listener.pickUp();
				pickUpCounter++;
			}
		}
	}

	private void checkGameOver(float deltaTime){
		if(arrow.hit){
			gameOverTimer -= deltaTime;
			if(gameOverTimer < 0){
				state = WORLD_STATE_GAME_OVER;
			}
		}
	}
}
