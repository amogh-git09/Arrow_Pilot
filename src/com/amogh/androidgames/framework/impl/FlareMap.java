package com.amogh.androidgames.framework.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.util.Log;

import com.amogh.androidgames.framework.FileIO;

public class FlareMap {
	public static ArrayList<String> lines;
	
	public static ArrayList<String> loadMap(FileIO files, String fileName){
		BufferedReader in = null;
		try{
			in = new BufferedReader(new InputStreamReader(files.readAsset(fileName)));
			lines = new ArrayList<String>();
			String line;
			while((line = in.readLine()) != null){
				lines.add(line);
			}
		}catch(IOException e){
			Log.d("FlareMap", "IOException");
			e.printStackTrace();
		}catch(NumberFormatException e){
			Log.d("FlareMap", "NumberFormatException");
		}finally{
			try{
				if(in != null)
					in.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		//Log.d("FLareMap", lines.toString());
		return lines;
	}
}
