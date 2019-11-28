package mx.itesm.seb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

import java.util.Stack;

import mx.itesm.seb.Outputs.Mechanics.Settings;
import mx.itesm.seb.Outputs.Screens.ScreenMenu;

public class Videogame extends Game {

	//Dimensiones del mundo
	public static final float WIDTH = 720;
	//public static final float WIDTHtest = Gdx.graphics.getWidth();
	public static final float HEIGHT = 1280;
	//public static final float HEIGHTtest = Gdx.graphics.getHeight();
	private Music backgroundMusic;
	private AssetManager assetManager;
	private Settings settings;


	public AssetManager callAssetManager(){
		assetManager = new AssetManager();
		return assetManager;
	}

	/*public Music getBackgroundMusic(){
		AssetManager assetManager = this.callAssetManager();
		assetManager.load("Music/Phantoms Castle.mp3", Music.class);
		assetManager.finishLoading();
		backgroundMusic = assetManager.get("Music/Phantoms Castle.mp3");
		return backgroundMusic;
	}

	public void StopMusic(){
		if (backgroundMusic.isPlaying()){backgroundMusic.stop();}
	}*/

	@Override
	public void create () {
		settings = new Settings();
		setScreen(new ScreenMenu(this));
	}

	public Settings getSettings(){
		return this.settings;
	}

}
