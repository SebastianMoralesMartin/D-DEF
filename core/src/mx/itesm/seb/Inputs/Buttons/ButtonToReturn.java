package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Outputs.Screens.ScreenSurvive;
import mx.itesm.seb.Videogame;

public class ButtonToReturn extends EnhancedImageButton {

    public ButtonToReturn(Videogame videogame, Skin skin) {
        super(videogame, skin, "sum");
        this.setScreen();
    }

    public ButtonToReturn(Videogame videogame, Skin skin, String styleName) {
        super(videogame, skin, styleName);
        this.setScreen();
    }

    public ButtonToReturn(Videogame videogame, ImageButtonStyle style) {
        super(videogame, style);
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
