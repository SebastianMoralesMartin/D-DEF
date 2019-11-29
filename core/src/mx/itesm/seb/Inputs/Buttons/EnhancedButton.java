//Alberto

package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import mx.itesm.seb.Videogame;

public class EnhancedButton extends Button {
    protected Videogame videogame;

    public EnhancedButton(Videogame videogame, Skin skin) {
        super(skin);
        this.videogame = videogame;
    }

    public EnhancedButton(Videogame videogame, Skin skin, String styleName) {
        super(skin, styleName);
        this.videogame = videogame;
    }

    public EnhancedButton(Videogame videogame, ButtonStyle style) {
        super(style);
        this.videogame = videogame;
    }
}
