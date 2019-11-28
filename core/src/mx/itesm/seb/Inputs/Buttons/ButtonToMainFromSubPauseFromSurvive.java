package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Outputs.Screens.EnhancedScreen;
import mx.itesm.seb.Videogame;

public class ButtonToMainFromSubPauseFromSurvive extends EnhancedTextButton {

    private EnhancedScreen screen;

    public ButtonToMainFromSubPauseFromSurvive(Videogame videogame, EnhancedScreen screen, Skin skin) {
        super(videogame, "Return",skin, "orangeRectangle");
        this.screen = screen;
        this.setScreen();
    }

    public ButtonToMainFromSubPauseFromSurvive(Videogame videogame, EnhancedScreen screen, Skin skin, String styleName) {
        super(videogame, "Return",skin, styleName);
        this.screen = screen;
        this.setScreen();
    }

    public ButtonToMainFromSubPauseFromSurvive(Videogame videogame, EnhancedScreen screen, TextButtonStyle style) {
        super(videogame, "Return", style);
        this.screen = screen;
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                    screen.updateScreen();
                    screen.setScreenState(EnhancedScreen.subscreen.MAIN);
            }
        });
    }
}
