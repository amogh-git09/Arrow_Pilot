package com.amogh.androidgames.arrowpilot;

import com.amogh.androidgames.framework.Music;
import com.amogh.androidgames.framework.Sound;
import com.amogh.androidgames.framework.impl.GLGame;

public class AssetsSound {
	public static Music music;
	public static Sound clickSound;
	public static Sound hit;
	public static Sound pickup;
	public static Sound push;
	public static Sound powerup;
	
	public static void load(GLGame game){
		music = game.getAudio().newMusic("LukHash.mp3");
		music.setLooping(true);
		if(Settings.musicEnabled)
			music.play();
		
		clickSound = game.getAudio().newSound("click.ogg");
		hit = game.getAudio().newSound("hit.ogg");
		pickup = game.getAudio().newSound("pickup.ogg");
		push = game.getAudio().newSound("push.ogg");
		powerup = game.getAudio().newSound("powerup.ogg");	
	}
	
	public static void reload(){
		if(Settings.musicEnabled){
			music.play();
		}
	}
	
	public static void playSound(Sound sound){
		if(Settings.soundEnabled)
			sound.play(1);
	}
}
