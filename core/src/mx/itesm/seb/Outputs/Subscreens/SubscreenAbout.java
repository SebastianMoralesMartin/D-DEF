package mx.itesm.seb.Outputs.Subscreens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import mx.itesm.seb.Inputs.Buttons.ButtonToAbout;
import mx.itesm.seb.Inputs.Buttons.ButtonToSettings;
import mx.itesm.seb.Videogame;

public class SubscreenAbout {
    private Window window;
    private Skin uiSkin;
    private Skin uiButton;
    private Videogame videogame;
    private ButtonToAbout buttonToAbout;
    private ButtonToSettings buttonToSettings;


    public SubscreenAbout(Videogame videogame, String title, Skin uiSkin, Skin uiButton){
        this.window = new Window(title, uiSkin, "subscreen");
        this.videogame = videogame;
        this.uiSkin = uiSkin;
        this.uiButton = uiButton;
        this.setElements();
        this.setWindow();
    }

    public SubscreenAbout(Videogame videogame, Skin uiSkin, Skin uiButton){
        this.window = new Window("About", uiSkin, "subscreen");
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
        this.buttonToAbout = new ButtonToAbout(this.videogame, this.uiButton);
    }

    private void setWindow() {
        this.setWindowElements();
        this.setWindowProperties();
    }

    private void setWindowElements() {
        this.window.add(this.buttonToAbout).fillX();
    }

    private void setWindowProperties() {
        this.window.padTop(64);
        this.window.setModal(true);
        this.window.pack();
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
