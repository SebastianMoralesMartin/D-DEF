package mx.itesm.seb;

import com.badlogic.gdx.Game;

public class Juego extends Game {

	//Dimensiones del mundo
	public static final float ANCHO = 1280;
	public static final float ALTO = 720;

	@Override
	public void create () {
		setScreen(new PantallaMenu(this));
	}

}
