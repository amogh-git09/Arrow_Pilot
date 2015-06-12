package reserve;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import com.amogh.androidgames.framework.Screen;
import com.amogh.androidgames.framework.impl.GLGame;

public class ArrowPilot extends GLGame {
	boolean firstTimeCreate = true;
	public static final int USE_ASSETS_MAIN = 0;
	public static final int USE_ASSETS_AREA_1 = 1;
	public static final int USE_ASSETS_AREA_2 = 2;
	public static int assetsToUse = USE_ASSETS_MAIN;

	public Screen getStartScreen(){
		Settings.load(getFileIO());
		return new MainMenuScreen(this);
	}

	@SuppressLint("NewApi") @Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config){
		Log.d("ArrowPilot", "onSurfaceCreated called");
		super.onSurfaceCreated(gl, config);

		Settings.load(getFileIO());
		Log.d("ArrowPilot", assetsToUse + "");
		if(firstTimeCreate){
			Settings.load(getFileIO());
			AssetsMain.load(this);
			AssetsSound.load(this);
			firstTimeCreate = false;
		}else{
			switch(assetsToUse){
			case USE_ASSETS_MAIN:
				AssetsMain.reload();
				AssetsSound.reload();
				break;
			case USE_ASSETS_AREA_1:
				AssetsWorld1.reload();
				AssetsGameScreen.reload();
				AssetsSound.reload();
				break;
			case USE_ASSETS_AREA_2:
				AssetsWorld2.reload();
				AssetsGameScreen.reload();
				AssetsSound.reload();
				break;
			}
		}
	}

	@Override
	public void onPause(){
		super.onPause();
		Log.d("ArrowPilot", "onPause called");
		if(Settings.musicEnabled){
			AssetsSound.music.pause();
		}
		Settings.save(this.getFileIO());
	}
	
	/*@Override
	public void onResume(){
		super.onResume();
		Log.d("ArrowPilot", "onResume called");
	}
	
	@Override
	public void onStop(){
		super.onStop();
		Log.d("ArrowPilot", "onStop called");
	}*/
}
