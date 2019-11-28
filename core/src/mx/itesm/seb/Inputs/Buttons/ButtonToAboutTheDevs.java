package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Outputs.Screens.ScreenAbout;
import mx.itesm.seb.Outputs.Screens.ScreenAboutTheDevs;
import mx.itesm.seb.Videogame;

public class ButtonToAboutTheDevs extends EnhancedTextButton {

    public ButtonToAboutTheDevs(Videogame videogame, String text, Skin skin) {
        super(videogame, text, skin, "yellowRectangle");
        this.setScreen();
    }

    public ButtonToAboutTheDevs(Videogame videogame, Skin skin) {
        super(videogame, "The Devs", skin, "yellowRectangle");
        this.setScreen();
    }

    public ButtonToAboutTheDevs(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "The Devs", skin, styleName);
        this.setScreen();
    }

    public ButtonToAboutTheDevs(Videogame videogame, String text, TextButtonStyle style) {
        super(videogame, "The Devs", style);
        this.setScreen();
    }

    public ButtonToAboutTheDevs(Videogame videogame, TextButtonStyle style) {
        super(videogame, "The Devs", style);
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                videogame.setScreen(new ScreenAboutTheDevs(videogame));
            }
        });
    }
}
