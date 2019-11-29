//Alberto

package mx.itesm.seb.Inputs.CheckBoxes;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import mx.itesm.seb.Videogame;

public class EnhancedCheckBox extends CheckBox {
    protected Videogame videogame;

    public EnhancedCheckBox(Videogame videogame, String text, Skin skin){
        super(text, skin);
        this.videogame = videogame;
    }

    public EnhancedCheckBox(Videogame videogame, String text, Skin skin, String styleName){
        super(text, skin, styleName);
        this.videogame = videogame;
    }

    public EnhancedCheckBox(Videogame videogame, String text, CheckBoxStyle checkBoxStyle){
        super(text, checkBoxStyle);
        this.videogame = videogame;
    }
}
