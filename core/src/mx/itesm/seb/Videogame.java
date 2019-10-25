package mx.itesm.seb;

import com.badlogic.gdx.Game;

import java.util.Stack;

import mx.itesm.seb.Outputs.Screens.ScreenMenu;

public class Videogame extends Game {

	//Dimensiones del mundo
	public static final float WIDTH = 720;
	public static final float HEIGHT = 1280;

	public Stack<Object> screenStack = new Stack<>();

	@Override
	public void create () {
		setScreen(new ScreenMenu(this));
	}

}
