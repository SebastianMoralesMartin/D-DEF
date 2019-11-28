package mx.itesm.seb.Inputs.CheckBoxes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import mx.itesm.seb.Videogame;

public class SwitchSound extends EnhancedCheckBox {

    public SwitchSound(Videogame videogame, Skin skin) {
        super(videogame, "", skin, "switch");
        this.update();
        this.setSetting();
    }

    public SwitchSound(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "", skin, styleName);
        this.update();
        this.setSetting();
    }

    public SwitchSound(Videogame videogame, CheckBoxStyle checkBoxStyle) {
        super(videogame, "", checkBoxStyle);
        this.update();
        this.setSetting();
    }

    private void update(){
        if (this.videogame.getSettings().getSound() == true){
            this.setChecked(true);
        } else {
            this.setChecked(false);
        }
    }

    public void setSetting(){
        this.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (videogame.getSettings().getSound() == true){
                    System.out.println("Sound is NOT set!");
                    videogame.getSettings().setSound(false);
                } else {
                    System.out.println("Sound is set!");
                    videogame.getSettings().setSound(true);
                }
            }
        });
    }
}
