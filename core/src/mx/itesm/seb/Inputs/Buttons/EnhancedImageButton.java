package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import mx.itesm.seb.Videogame;

public class EnhancedImageButton extends ImageButton {
    protected Videogame videogame;

    public EnhancedImageButton(Videogame videogame, Skin skin) {
        super(skin);
        this.videogame = videogame;
    }

    public EnhancedImageButton(Videogame videogame, Skin skin, String styleName) {
        super(skin, styleName);
        this.videogame = videogame;
    }

    public EnhancedImageButton(Videogame videogame, ImageButtonStyle style) {
        super(style);
        this.videogame = videogame;
    }

}
