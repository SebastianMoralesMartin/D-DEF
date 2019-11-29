//Alberto

package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import mx.itesm.seb.Videogame;

public class EnhancedTextButton extends TextButton {
    protected Videogame videogame;

    public EnhancedTextButton(Videogame videogame, String text, Skin skin) {
        super(text, skin);
        this.videogame = videogame;
    }

    public EnhancedTextButton(Videogame videogame, String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        this.videogame = videogame;
    }

    public EnhancedTextButton(Videogame videogame, String text, TextButtonStyle style) {
        super(text, style);
        this.videogame = videogame;
    }
}
