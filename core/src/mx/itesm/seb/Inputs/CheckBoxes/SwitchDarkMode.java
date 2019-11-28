package mx.itesm.seb.Inputs.CheckBoxes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import mx.itesm.seb.Outputs.Screens.EnhancedScreen;
import mx.itesm.seb.Videogame;

public class SwitchDarkMode extends EnhancedCheckBox {

    public SwitchDarkMode(Videogame videogame, Skin skin) {
        super(videogame, "", skin, "switch");
        this.update();
        this.setSetting();
    }

    public SwitchDarkMode(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "", skin, styleName);
        this.update();
        this.setSetting();
    }

    public SwitchDarkMode(Videogame videogame, CheckBoxStyle checkBoxStyle) {
        super(videogame, "", checkBoxStyle);
        this.update();
        this.setSetting();
    }

    private void update(){
        if (this.videogame.getSettings().getDarkMode() == true){
            this.setChecked(true);
        } else {
            this.setChecked(false);
        }
    }

    public void setSetting(){
        this.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (videogame.getSettings().getDarkMode() == true){
                    System.out.println("DarkMode is NOT set!");
                    videogame.getSettings().setDarkMode(false);
                } else {
                    System.out.println("DarkMode is set!");
                    videogame.getSettings().setDarkMode(true);
                }
                System.out.println("Updating Screen...");
                EnhancedScreen screen = (EnhancedScreen) videogame.getScreen();
                screen.setSkins();
                //screen.updateScreen();
            }
        });
    }
}
