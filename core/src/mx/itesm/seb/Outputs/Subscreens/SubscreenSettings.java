package mx.itesm.seb.Outputs.Subscreens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;

import mx.itesm.seb.Inputs.Buttons.ButtonToWarningDeleteData;
import mx.itesm.seb.Inputs.Buttons.ButtonToMain;
import mx.itesm.seb.Inputs.CheckBoxes.SettingSwitch;
import mx.itesm.seb.Inputs.TextFields.InsertNameTextField;
import mx.itesm.seb.Outputs.Screens.EnhancedScreen;
import mx.itesm.seb.Videogame;

public class SubscreenSettings {
    private Window window;
    private Skin uiSkin;
    private Skin uiButton;
    private Videogame videogame;
    private InsertNameTextField insertNameTextField;
    private SettingSwitch switchDarkMode;
    private SettingSwitch switchMusic;
    private SettingSwitch switchSound;
    private ButtonToWarningDeleteData buttonToWarningDeleteData;
    private Label name;
    private Label darkMode;
    private Label music;
    private Label sound;
    //SUBSCREEN ALERTA
    //LANGUAGE
    private ButtonToMain buttonToMain;


    public SubscreenSettings(Videogame videogame, String title, Skin uiSkin, Skin uiButton){
        this.window = new Window(title, uiSkin, "subscreen");
        this.videogame = videogame;
        this.uiSkin = uiSkin;
        this.uiButton = uiButton;
        this.setElements();
        this.setWindow();
    }

    public SubscreenSettings(Videogame videogame, Skin uiSkin, Skin uiButton){
        this.window = new Window("Settings", uiSkin, "subscreen");
        this.videogame = videogame;
        this.uiSkin = uiSkin;
        this.uiButton = uiButton;
        this.setElements();
        this.setWindow();
    }

    private void setElements() {
        this.setLabels();
        this.setButtons();
        this.setCheckBoxes();
        this.setTextFields();
    }

    private void setTextFields() {
        this.insertNameTextField = new InsertNameTextField(this.videogame, this.uiSkin);
    }

    private void setLabels() {
        this.name = new Label("Name", this.uiSkin, "content-subtitle");
        this.name.setAlignment(Align.center);
        this.darkMode = new Label("Dark Mode", this.uiSkin, "content-subtitle");
        this.music = new Label("Music", this.uiSkin, "content-subtitle");
        this.sound = new Label("Sound", this.uiSkin, "content-subtitle");
    }

    private void setCheckBoxes() {
        this.switchDarkMode = new SettingSwitch(this.videogame, this.uiButton);
        this.switchMusic = new SettingSwitch(this.videogame, this.uiButton);
        this.switchSound = new SettingSwitch(this.videogame, this.uiButton);
    }

    private void setButtons() {
        this.buttonToWarningDeleteData = new ButtonToWarningDeleteData(this.videogame, (EnhancedScreen) this.videogame.getScreen(), this.uiButton);
        this.buttonToMain = new ButtonToMain(this.videogame, (EnhancedScreen) this.videogame.getScreen(), this.uiButton);
    }

    private void setWindow() {
        this.setWindowElements();
        this.setWindowProperties();
    }

    private void setWindowElements() {
        this.window.add(this.name).pad(10).colspan(3);
        this.window.row();
        this.window.add(this.insertNameTextField).pad(10).padBottom(20).colspan(3).fillX();
        this.window.row();
        this.window.add(this.switchDarkMode).pad(10).fillX();
        this.window.add(this.darkMode).pad(10).fillX();
        this.window.row();
        this.window.add(this.switchMusic).pad(10).fillX();
        this.window.add(this.music).pad(10).fillX();
        this.window.row();
        this.window.add(this.switchSound).pad(10).fillX();
        this.window.add(this.sound).pad(10).fillX();
        this.window.row();
        this.window.add(this.buttonToWarningDeleteData).width(600).height(150).pad(20).colspan(3).fillX();
        this.window.row();
        this.window.add(this.buttonToMain).width(500).height(150).pad(20).colspan(2);
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
