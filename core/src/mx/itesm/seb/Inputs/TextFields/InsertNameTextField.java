package mx.itesm.seb.Inputs.TextFields;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import mx.itesm.seb.Videogame;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

public class InsertNameTextField extends EnhancedTextField {

    public InsertNameTextField(Videogame videogame, Skin skin) {
        super(videogame, "James", skin, "default");
        this.setAlignment(Align.center);
    }

    public InsertNameTextField(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "James", skin, styleName);
        this.setAlignment(Align.center);
    }

    public InsertNameTextField(Videogame videogame, TextField.TextFieldStyle style) {
        super(videogame, "James", style);
        this.setAlignment(Align.center);
    }
}
