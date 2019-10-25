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


	public AssetManager callAssetManager(){
		AssetManager assetManager = new AssetManager();
		return assetManager;
	}

	public Stack<Object> screenStack = new Stack<>();


	@Override
	public void create () {
		setScreen(new ScreenMenu(this));
	}

}
