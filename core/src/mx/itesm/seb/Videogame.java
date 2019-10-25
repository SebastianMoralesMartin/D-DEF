package mx.itesm.seb;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

import java.util.Stack;

import mx.itesm.seb.Outputs.Screens.ScreenMenu;

public class Videogame extends Game {

	//Dimensiones del mundo
	public static final float WIDTH = 720;
	public static final float HEIGHT = 1280;
	private Music backgroundMusic;
	private AssetManager assetManager;


	public AssetManager callAssetManager(){
		assetManager = new AssetManager();
		return assetManager;
	}

	public Music getBackgroundMusic(){
		AssetManager assetManager = this.callAssetManager();
		assetManager.load("Music/Phantoms Castle.mp3", Music.class);
		assetManager.finishLoading();
		backgroundMusic = assetManager.get("Music/Phantoms Castle.mp3");
		return backgroundMusic;
	}

	public void StopMusic(){
		if (backgroundMusic.isPlaying()){backgroundMusic.stop();}
	}
	public Stack<Object> screenStack = new Stack<>();


	@Override
	public void create () {
		setScreen(new ScreenMenu(this));
	}

}
