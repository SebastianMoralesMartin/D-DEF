//Alberto

package mx.itesm.seb.Inputs.CheckBoxes;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import mx.itesm.seb.Videogame;

public class SwitchMusic extends EnhancedCheckBox {

    public SwitchMusic(Videogame videogame, Skin skin) {
        super(videogame, "", skin, "switch");
        this.update();
        this.setSetting();
    }

    public SwitchMusic(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "", skin, styleName);
        this.update();
        this.setSetting();
    }

    public SwitchMusic(Videogame videogame, CheckBoxStyle checkBoxStyle) {
        super(videogame, "", checkBoxStyle);
        this.update();
        this.setSetting();
    }

    private void update(){
        if (this.videogame.getSettings().getMusic() == true){
            this.setChecked(true);
        } else {
            this.setChecked(false);
        }
    }

    public void setSetting(){
        this.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (videogame.getSettings().getMusic() == true){
                    System.out.println("Music is NOT set!");
                    videogame.getSettings().setMusic(false);
                } else {
                    System.out.println("Music is set!");
                    videogame.getSettings().setMusic(true);
                }
            }
        });
    }
}
