package com.amogh.androidgames.arrowpilotrev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.amogh.androidgames.framework.FileIO;

public class Settings {
	public static boolean soundEnabled = true;
	public static boolean musicEnabled = true;
	public static final int[] highscores = new int[]{100, 80, 60, 40, 10};
	public final static String file = ".superarrow";
	
	public static ArrayList<ArrayList<Boolean>> levelStates;
	public static int noOfAreas = 2;
	public static int noOfLevels = 10;
	
	public static void load(FileIO files){
		levelStates = new ArrayList<ArrayList<Boolean>>();
		
		for(int i = 0; i < noOfAreas; i++){
			levelStates.add(new ArrayList<Boolean>());
			levelStates.get(i).add(true);
			for(int j = 1; j < noOfLevels; j++){
				levelStates.get(i).add(false);
			}
		}
		
		BufferedReader in = null;
		
		try{
			in = new BufferedReader(new InputStreamReader(files.readFile(file)));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			musicEnabled = Boolean.parseBoolean(in.readLine());
			for(int i = 0; i < 5; i++){
				highscores[i] = Integer.parseInt(in.readLine());
			}
			
			for(int i = 0; i < noOfAreas; i++){
				for(int j = 0; j < noOfLevels; j++){
					levelStates.get(i).set(j, Boolean.parseBoolean(in.readLine()));
				}
			}	
		}catch(IOException e){
			
		}catch(NumberFormatException e){
			
		}finally{
			try{
				if(in != null)
					in.close();
			}catch(IOException e){
				
			}
		}
	}
	
	public static void save(FileIO files){
		BufferedWriter out = null;
		try{
			out = new BufferedWriter(new OutputStreamWriter(files.writeFile(file)));
			out.write(Boolean.toString(soundEnabled));
			out.write("\n");
			out.write(Boolean.toString(musicEnabled));
			for(int i = 0; i < 5; i++){
				out.write("\n");
				out.write(Integer.toString(highscores[i]));
			}
			for(int i = 0; i < noOfAreas; i++){
				for(int j = 0; j < noOfLevels; j++){
					out.write("\n");
					out.write(Boolean.toString(levelStates.get(i).get(j)));
				}
			}
		}catch(IOException e){
			
		}finally{
			try{
				if(out != null)
					out.close();
			}catch(IOException e){
				
			}
		}
	}
	
	public static void addScore(int score){
		for(int i = 0; i < 5; i++){
			if(highscores[i] < score){
				for(int j = 4; j > i; j--)
					highscores[j] = highscores[j-1];
				highscores[i] = score;
				break;
			}
		}
	}
}



















