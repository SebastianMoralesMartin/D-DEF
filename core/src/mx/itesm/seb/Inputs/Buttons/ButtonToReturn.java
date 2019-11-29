//Alberto

package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Outputs.Screens.ScreenSurvive;
import mx.itesm.seb.Videogame;

public class ButtonToReturn extends EnhancedTextButton {

    public ButtonToReturn(Videogame videogame, Skin skin) {
        super(videogame, "Return", skin, "orangeRectangñe");
        this.setScreen();
    }

    public ButtonToReturn(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "Return", skin, styleName);
        this.setScreen();
    }

    public ButtonToReturn(Videogame videogame, TextButtonStyle style) {
        super(videogame, "Return", style);
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
