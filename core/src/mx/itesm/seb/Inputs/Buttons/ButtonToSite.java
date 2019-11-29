//Alberto

package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Outputs.Screens.ScreenSurvive;
import mx.itesm.seb.Videogame;

public class ButtonToSite extends EnhancedTextButton {

    public ButtonToSite(Videogame videogame, Skin skin) {
        super(videogame, "Facebook Site", skin, "greenRectangle");
        this.setScreen();
    }

    public ButtonToSite(Videogame videogame, Skin skin, String styleName) {
        super(videogame, "Facebook Site", skin, styleName);
        this.setScreen();
    }

    public ButtonToSite(Videogame videogame, TextButtonStyle style) {
        super(videogame, "Facebook Site", style);
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.net.openURI("https://www.facebook.com/Dunkirk-Defense-D-DEF-100744494744204/?__tn__=%2Cd%2CP-R&eid=ARBAEMjKHsI07Ojh0aVA_dcoPZ52K_YB5k_JS9T7Asnwd9N42hMoZwfHnLrfufS0VxHEeknEPvocNk3O");
            }
        });
    }
}
