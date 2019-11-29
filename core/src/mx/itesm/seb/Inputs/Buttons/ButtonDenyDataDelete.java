//Alberto

package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Outputs.Screens.EnhancedScreen;
import mx.itesm.seb.Videogame;

public class ButtonDenyDataDelete extends EnhancedButton {

    private EnhancedScreen screen;

    public ButtonDenyDataDelete(Videogame videogame, EnhancedScreen screen, Skin skin) {
        super(videogame, skin, "no");
        this.screen = screen;
        this.setScreen();
    }

    public ButtonDenyDataDelete(Videogame videogame, EnhancedScreen screen, Skin skin, String styleName) {
        super(videogame, skin, styleName);
        this.screen = screen;
        this.setScreen();
    }

    public ButtonDenyDataDelete(Videogame videogame, EnhancedScreen screen, ButtonStyle style) {
        super(videogame, style);
        this.screen = screen;
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                screen.setScreenState(EnhancedScreen.subscreen.SUBSCREEN_2);
                screen.updateScreen();
            }
        });
    }
}
