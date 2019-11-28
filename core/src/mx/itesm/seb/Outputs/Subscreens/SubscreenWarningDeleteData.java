package mx.itesm.seb.Outputs.Subscreens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;

import mx.itesm.seb.Inputs.Buttons.ButtonConfirmDataDelete;
import mx.itesm.seb.Inputs.Buttons.ButtonDenyDataDelete;
import mx.itesm.seb.Inputs.Buttons.ButtonToAboutTheDevs;
import mx.itesm.seb.Inputs.Buttons.ButtonToAboutTheGame;
import mx.itesm.seb.Inputs.Buttons.ButtonToMain;
import mx.itesm.seb.Outputs.Screens.EnhancedScreen;
import mx.itesm.seb.Videogame;

public class SubscreenWarningDeleteData {
    private Window window;
    private Skin uiSkin;
    private Skin uiButton;
    private Videogame videogame;
    private Label question;
    private Label description;
    private Label cancel;
    private Label confirm;
    private ButtonDenyDataDelete buttonDenyDeleteData;
    private ButtonConfirmDataDelete buttonConfirmDataDelete;


    public SubscreenWarningDeleteData(Videogame videogame, String title, Skin uiSkin, Skin uiButton){
        this.window = new Window(title, uiSkin, "red");
        this.videogame = videogame;
        this.uiSkin = uiSkin;
        this.uiButton = uiButton;
        this.setElements();
        this.setWindow();
    }

    public SubscreenWarningDeleteData(Videogame videogame, Skin uiSkin, Skin uiButton){
        this.window = new Window("Warning!", uiSkin, "red");
        this.videogame = videogame;
        this.uiSkin = uiSkin;
        this.uiButton = uiButton;
        this.setElements();
        this.setWindow();
    }

    private void setElements() {
        this.setLabels();
        this.setButtons();
    }

    private void setLabels() {
        this.question = new Label("Are you sure you want to delete all your game data?", this.uiSkin, "content-subtitle");
        this.question.setAlignment(Align.center);
        this.question.setWrap(true);
        this.description = new Label("Doing so erases all your game efforts and achievements.", this.uiSkin, "content-bold");
        this.description.setAlignment(Align.center);
        this.description.setWrap(true);
        this.cancel = new Label("No, please cancel.", this.uiSkin, "content");
        this.cancel.setAlignment(Align.center);
        this.description.setWrap(true);
        this.confirm = new Label("Yes, delete my data.", this.uiSkin, "content");
        this.confirm.setAlignment(Align.center);
        this.description.setWrap(true);
    }

    private void setButtons() {
        this.buttonDenyDeleteData = new ButtonDenyDataDelete(this.videogame, (EnhancedScreen) this.videogame.getScreen(), this.uiButton);
        this.buttonConfirmDataDelete = new ButtonConfirmDataDelete(this.videogame, this.uiButton);
    }

    private void setWindow() {
        this.setWindowElements();
        this.setWindowProperties();
    }

    private void setWindowElements() {
        this.window.add(this.question).width(600).pad(20).colspan(2).fillX();
        this.window.row();
        this.window.add(this.description).width(600).pad(20).padBottom(40).colspan(2).fillX();
        this.window.row();
        this.window.add(this.buttonDenyDeleteData).pad(10);
        this.window.add(this.buttonConfirmDataDelete).pad(10);
        this.window.row();
        this.window.add(this.cancel).pad(20).padBottom(5).padTop(0).fillX();
        this.window.add(this.confirm).pad(20).padBottom(5).padTop(0).fillX();
    }

    private void setWindowProperties() {
        this.window.padTop(64);
        this.window.setModal(true);
        this.window.pack();
        this.window.setWidth(this.videogame.WIDTH);
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

