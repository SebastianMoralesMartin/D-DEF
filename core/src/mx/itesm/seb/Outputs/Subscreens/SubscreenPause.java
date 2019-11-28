package mx.itesm.seb.Outputs.Subscreens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import mx.itesm.seb.Inputs.Buttons.ButtonToAboutTheDevs;
import mx.itesm.seb.Inputs.Buttons.ButtonToAboutTheGame;
import mx.itesm.seb.Inputs.Buttons.ButtonToMain;
import mx.itesm.seb.Outputs.Screens.EnhancedScreen;
import mx.itesm.seb.Videogame;

public class SubscreenPause {
    private Window window;
    private Skin uiSkin;
    private Skin uiButton;
    private Videogame videogame;
    private ButtonToAboutTheGame buttonToAboutTheGame;
    private ButtonToAboutTheDevs buttonToAboutTheDevs;
    private ButtonToMain buttonToMain;


    public SubscreenPause(Videogame videogame, String title, Skin uiSkin, Skin uiButton){
        this.window = new Window(title, uiSkin, "subscreen");
        this.videogame = videogame;
        this.uiSkin = uiSkin;
        this.uiButton = uiButton;
        this.setElements();
        this.setWindow();
    }

    public SubscreenPause(Videogame videogame, Skin uiSkin, Skin uiButton){
        this.window = new Window("Paused", uiSkin, "subscreen");
        this.videogame = videogame;
        this.uiSkin = uiSkin;
        this.uiButton = uiButton;
        this.setElements();
        this.setWindow();
    }

    private void setElements() {
        this.setButtons();
    }

    private void setButtons() {
        this.buttonToAboutTheGame = new ButtonToAboutTheGame(this.videogame, this.uiButton);
        this.buttonToAboutTheDevs = new ButtonToAboutTheDevs(this.videogame, this.uiButton);
        this.buttonToMain = new ButtonToMain(this.videogame, (EnhancedScreen) this.videogame.getScreen(), this.uiButton);
    }

    private void setWindow() {
        this.setWindowElements();
        this.setWindowProperties();
    }

    private void setWindowElements() {
        this.window.add(this.buttonToAboutTheGame).pad(10).fillX();
        this.window.row();
        this.window.add(this.buttonToAboutTheDevs).pad(10).fillX();
        this.window.row();
        this.window.add(this.buttonToMain).width(500).height(150).pad(20).colspan(3);
    }

    private void setWindowProperties() {
        this.window.padTop(64);
        this.window.setModal(true);
        this.window.pack();
        //this.window.debug();
        this.window.setPosition(this.videogame.WIDTH/2 - this.window.getWidth()/2,
                this.videogame.HEIGHT/2 - this.window.getHeight()/2);
    }

    public Window getWindow(){
        return this.window;
    }

    public void draw(SpriteBatch batch, float parentAlpha){
        this.window.draw(batch, parentAlpha);
    }
}

