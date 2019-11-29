//Alberto

package mx.itesm.seb.Inputs.TextFields;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

import mx.itesm.seb.Videogame;

public class EnhancedTextField extends TextField {
    protected Videogame videogame;

    public EnhancedTextField(Videogame videogame, String text, Skin skin) {
        super(text, skin);
        this.videogame = videogame;
    }

    public EnhancedTextField(Videogame videogame, String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        this.videogame = videogame;
    }

    public EnhancedTextField(Videogame videogame, String text, TextFieldStyle style) {
        super(text, style);
        this.videogame = videogame;
    }
}
