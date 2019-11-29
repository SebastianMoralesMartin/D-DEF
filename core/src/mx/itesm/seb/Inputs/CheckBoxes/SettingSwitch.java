//Alberto

package mx.itesm.seb.Inputs.CheckBoxes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import mx.itesm.seb.Videogame;

public class SettingSwitch extends EnhancedCheckBox {

    public SettingSwitch(Videogame videogame, Skin skin) {
        super(videogame, "", skin, "switch");
        this.setSetting();
    }

    public SettingSwitch(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "", skin, styleName);
        this.setSetting();
    }

    public SettingSwitch(Videogame videogame, CheckBoxStyle checkBoxStyle) {
        super(videogame, "", checkBoxStyle);
        this.setSetting();
    }

    public void setSetting(){
        this.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Set!");
            }
        });
    }
}
