package mx.itesm.seb.Inputs.TextFields;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import mx.itesm.seb.Videogame;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

public class InsertNameTextField extends EnhancedTextField {

    public InsertNameTextField(Videogame videogame, Skin skin) {
        super(videogame, videogame.getSettings().getName(), skin, "default");
        this.setTextField();
    }

    public InsertNameTextField(Videogame videogame, Skin skin, String styleName) {
        super(videogame, videogame.getSettings().getName(), skin, styleName);
        this.setTextField();
    }

    public InsertNameTextField(Videogame videogame, TextField.TextFieldStyle style) {
        super(videogame, videogame.getSettings().getName(), style);
        this.setTextField();
    }

    private void setTextField(){
        this.setMaxLength(30);
        this.setAlignment(Align.center);
    }

    private void updateTextField(){
        this.setMessageText(this.videogame.getSettings().getName());
    }
}
