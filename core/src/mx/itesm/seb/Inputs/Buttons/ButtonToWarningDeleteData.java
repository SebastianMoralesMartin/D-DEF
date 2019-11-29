//Alberto

package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Outputs.Screens.EnhancedScreen;
import mx.itesm.seb.Outputs.Screens.ScreenAbout;
import mx.itesm.seb.Videogame;

public class ButtonToWarningDeleteData extends EnhancedTextButton {
    EnhancedScreen screen;

    public ButtonToWarningDeleteData(Videogame videogame, EnhancedScreen screen, String text, Skin skin) {
        super(videogame, text, skin, "redRectangle");
        this.screen = screen;
        this.setScreen();
    }

    public ButtonToWarningDeleteData(Videogame videogame, EnhancedScreen screen, Skin skin) {
        super(videogame, "Delete Data", skin, "redRectangle");
        this.screen = screen;
        this.setScreen();
    }

    public ButtonToWarningDeleteData(Videogame videogame, EnhancedScreen screen, Skin skin, String styleName) {
        super(videogame, "Delete Data", skin, styleName);
        this.screen = screen;
        this.setScreen();
    }

    public ButtonToWarningDeleteData(Videogame videogame, EnhancedScreen screen, String text, TextButtonStyle style) {
        super(videogame, "Delete Data", style);
        this.screen = screen;
        this.setScreen();
    }

    public ButtonToWarningDeleteData(Videogame videogame, EnhancedScreen screen, TextButtonStyle style) {
        super(videogame, "Delete Data", style);
        this.screen = screen;
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                screen.setScreenState(EnhancedScreen.subscreen.SUBSCREEN_4);
                screen.updateScreen();
            }
        });
    }
}
