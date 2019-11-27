package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import mx.itesm.seb.Outputs.Screens.ScreenSurvive;
import mx.itesm.seb.Videogame;

public class ButtonToSurvive extends EnhancedTextButton {

    public ButtonToSurvive(Videogame videogame, String text, Skin skin) {
        super(videogame, text, skin, "yellowRectangle");
        this.setScreen();
    }

    public ButtonToSurvive(Videogame videogame, Skin skin) {
        super(videogame, "Survive", skin, "yellowRectangle");
        this.setScreen();
    }

    public ButtonToSurvive(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "Survive", skin, styleName);
        this.setScreen();
    }

    public ButtonToSurvive(Videogame videogame, String text, TextButton.TextButtonStyle style) {
        super(videogame, "Survive", style);
        this.setScreen();
    }

    public ButtonToSurvive(Videogame videogame, TextButton.TextButtonStyle style) {
        super(videogame, "Survive", style);
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
