package mx.itesm.seb;

import com.badlogic.gdx.Game;

import mx.itesm.seb.Outputs.Screens.ScreenMenu;

public class Videogame extends Game {

	//Dimensiones del mundo
	public static final float WIDTH = 720;
	public static final float HEIGHT = 1280;

	@Override
	public void create () {
		setScreen(new ScreenMenu(this));
	}

}
