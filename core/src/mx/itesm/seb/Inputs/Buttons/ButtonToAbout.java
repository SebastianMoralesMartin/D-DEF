package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import mx.itesm.seb.Outputs.Screens.ScreenAbout;
import mx.itesm.seb.Videogame;

public class ButtonToAbout extends EnhancedTextButton {

    public ButtonToAbout(Videogame videogame, String text, Skin skin) {
        super(videogame, text, skin, "rectangle");
        this.setScreen();
    }

    public ButtonToAbout(Videogame videogame, Skin skin) {
        super(videogame, "The Game", skin, "rectangle");
        this.setScreen();
    }

    public ButtonToAbout(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "The Game", skin, styleName);
        this.setScreen();
    }

    public ButtonToAbout(Videogame videogame, String text, TextButtonStyle style) {
        super(videogame, "The Game", style);
        this.setScreen();
    }

    public ButtonToAbout(Videogame videogame, TextButtonStyle style) {
        super(videogame, "The Game", style);
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                videogame.setScreen(new ScreenAbout(videogame));
            }
        });
    }
}
