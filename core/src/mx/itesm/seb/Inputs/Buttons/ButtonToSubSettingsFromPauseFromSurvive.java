package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Outputs.Screens.EnhancedScreen;
import mx.itesm.seb.Videogame;

public class ButtonToSubSettingsFromPauseFromSurvive extends EnhancedTextButton {

    private EnhancedScreen screen;

    public ButtonToSubSettingsFromPauseFromSurvive(Videogame videogame, EnhancedScreen screen, Skin skin) {
        super(videogame, "Settings", skin, "yellowRectangle");
        this.screen = screen;
        this.setScreen();
    }

    public ButtonToSubSettingsFromPauseFromSurvive(Videogame videogame, EnhancedScreen screen, Skin skin, String styleName) {
        super(videogame, "Settings", skin, styleName);
        this.screen = screen;
        this.setScreen();
    }

    public ButtonToSubSettingsFromPauseFromSurvive(Videogame videogame, EnhancedScreen screen, TextButtonStyle style) {
        super(videogame, "Settings", style);
        this.screen = screen;
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                    screen.setScreenState(EnhancedScreen.subscreen.SUBSCREEN_2);
                    //screen.updateScreen();
            }
        });
    }
}
