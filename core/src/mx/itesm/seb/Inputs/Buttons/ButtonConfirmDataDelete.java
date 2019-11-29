//Alberto

package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Outputs.Screens.EnhancedScreen;
import mx.itesm.seb.Outputs.Screens.ScreenLoading;
import mx.itesm.seb.Videogame;

public class ButtonConfirmDataDelete extends EnhancedButton {

    public ButtonConfirmDataDelete(Videogame videogame, Skin skin) {
        super(videogame, skin, "yes");
        this.setScreen();
    }

    public ButtonConfirmDataDelete(Videogame videogame, Skin skin, String styleName) {
        super(videogame, skin, styleName);
        this.setScreen();
    }

    public ButtonConfirmDataDelete(Videogame videogame, ButtonStyle style) {
        super(videogame, style);
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("Data deletion conpleted");
                videogame.getSettings().deleteData();
                videogame.setScreen(new ScreenLoading(videogame, ScreenLoading.GameScreen.MENU));
            }
        });
    }
}
