//Alberto

package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import mx.itesm.seb.Outputs.Screens.ScreenAbout;
import mx.itesm.seb.Outputs.Screens.ScreenAboutTheGame;
import mx.itesm.seb.Outputs.Screens.ScreenLoading;
import mx.itesm.seb.Videogame;

public class ButtonToAboutTheGame extends EnhancedTextButton {

    public ButtonToAboutTheGame(Videogame videogame, String text, Skin skin) {
        super(videogame, text, skin, "yellowRectangle");
        this.setScreen();
    }

    public ButtonToAboutTheGame(Videogame videogame, Skin skin) {
        super(videogame, "The Game", skin, "yellowRectangle");
        this.setScreen();
    }

    public ButtonToAboutTheGame(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "The Game", skin, styleName);
        this.setScreen();
    }

    public ButtonToAboutTheGame(Videogame videogame, String text, TextButtonStyle style) {
        super(videogame, "The Game", style);
        this.setScreen();
    }

    public ButtonToAboutTheGame(Videogame videogame, TextButtonStyle style) {
        super(videogame, "The Game", style);
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                videogame.setScreen(new ScreenLoading(videogame, ScreenLoading.GameScreen.ABOUT_THE_GAME));
            }
        });
    }
}
