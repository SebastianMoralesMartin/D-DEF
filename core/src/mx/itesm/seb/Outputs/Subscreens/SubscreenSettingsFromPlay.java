//Alberto

package mx.itesm.seb.Outputs.Subscreens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;

import mx.itesm.seb.Inputs.Buttons.ButtonToMainFromSubSettings;
import mx.itesm.seb.Inputs.Buttons.ButtonToWarningDeleteData;
import mx.itesm.seb.Inputs.CheckBoxes.SwitchDarkMode;
import mx.itesm.seb.Inputs.CheckBoxes.SwitchMusic;
import mx.itesm.seb.Inputs.CheckBoxes.SwitchSound;
import mx.itesm.seb.Inputs.TextFields.InsertNameTextField;
import mx.itesm.seb.Outputs.Screens.EnhancedScreen;
import mx.itesm.seb.Videogame;

public class SubscreenSettingsFromPlay {
    private Window window;
    private Skin uiSkin;
    private Skin uiButton;
    private Videogame videogame;
    private InsertNameTextField insertNameTextField;
    private SwitchMusic switchMusic;
    private SwitchSound switchSound;
    private Label name;
    private Label darkMode;
    private Label music;
    private Label sound;
    private ButtonToMainFromSubSettings buttonToMainFromSubSettings;


    public SubscreenSettingsFromPlay(Videogame videogame, String title, Skin uiSkin, Skin uiButton){
        this.window = new Window(title, uiSkin, "subscreen");
        this.videogame = videogame;
        this.uiSkin = uiSkin;
        this.uiButton = uiButton;
        this.setElements();
        this.setWindow();
    }

    public SubscreenSettingsFromPlay(Videogame videogame, Skin uiSkin, Skin uiButton){
        this.window = new Window("Settings", uiSkin, "subscreen");
        this.videogame = videogame;
        this.uiSkin = uiSkin;
        this.uiButton = uiButton;
        this.setElements();
        this.setWindow();
    }

    private void setElements() {
        this.setLabels();
        this.setTextFields();
        this.setButtons();
        this.setCheckBoxes();
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
        this.switchMusic = new SwitchMusic(this.videogame, this.uiButton);
        this.switchSound = new SwitchSound(this.videogame, this.uiButton);
    }

    private void setButtons() {
        this.buttonToMainFromSubSettings = new ButtonToMainFromSubSettings(this.videogame, (EnhancedScreen) this.videogame.getScreen(), this.insertNameTextField, this.uiButton);
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
        this.window.add(this.darkMode).pad(10).fillX();
        this.window.row();
        this.window.add(this.switchMusic).pad(10).fillX();
        this.window.add(this.music).pad(10).fillX();
        this.window.row();
        this.window.add(this.switchSound).pad(10).fillX();
        this.window.add(this.sound).pad(10).fillX();
        this.window.row();
        this.window.row();
        this.window.add(this.buttonToMainFromSubSettings).width(500).height(150).pad(20).colspan(2);
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
