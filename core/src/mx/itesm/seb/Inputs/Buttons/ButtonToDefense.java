package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Outputs.Screens.ScreenSurvive;
import mx.itesm.seb.Videogame;

public class ButtonToDefense extends EnhancedTextButton {

    public ButtonToDefense(Videogame videogame, String text, Skin skin) {
        super(videogame, text, skin, "yellowRectangle");
        this.setScreen();
    }

    public ButtonToDefense(Videogame videogame, Skin skin) {
        super(videogame, "Defense", skin, "yellowRectangle");
        this.setScreen();
    }

    public ButtonToDefense(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "Defense", skin, styleName);
        this.setScreen();
    }

    public ButtonToDefense(Videogame videogame, String text, TextButtonStyle style) {
        super(videogame, "Defense", style);
        this.setScreen();
    }

    public ButtonToDefense(Videogame videogame, TextButtonStyle style) {
        super(videogame, "Defense", style);
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                videogame.setScreen(new ScreenSurvive(videogame));
            }
        });
    }
}
