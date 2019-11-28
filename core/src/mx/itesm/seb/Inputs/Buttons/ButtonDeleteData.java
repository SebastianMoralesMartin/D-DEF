package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Outputs.Screens.ScreenAbout;
import mx.itesm.seb.Videogame;

public class ButtonDeleteData extends EnhancedTextButton {

    public ButtonDeleteData(Videogame videogame, String text, Skin skin) {
        super(videogame, text, skin, "redRectangle");
        this.setScreen();
    }

    public ButtonDeleteData(Videogame videogame, Skin skin) {
        super(videogame, "Delete Data", skin, "redRectangle");
        this.setScreen();
    }

    public ButtonDeleteData(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "Delete Data", skin, styleName);
        this.setScreen();
    }

    public ButtonDeleteData(Videogame videogame, String text, TextButtonStyle style) {
        super(videogame, "Delete Data", style);
        this.setScreen();
    }

    public ButtonDeleteData(Videogame videogame, TextButtonStyle style) {
        super(videogame, "The Devs", style);
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("Deleting");
            }
        });
    }
}
