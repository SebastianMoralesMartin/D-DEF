//Alberto

package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import mx.itesm.seb.Inputs.TextFields.InsertNameTextField;
import mx.itesm.seb.Outputs.Screens.EnhancedScreen;
import mx.itesm.seb.Videogame;

public class ButtonToMainFromSubSettingsFromPauseFromSurvive extends EnhancedTextButton {

    private EnhancedScreen screen;
    private InsertNameTextField insertNameTextField;

    public ButtonToMainFromSubSettingsFromPauseFromSurvive(Videogame videogame, EnhancedScreen screen, InsertNameTextField insertNameTextField, Skin skin) {
        super(videogame, "Return",skin, "orangeRectangle");
        this.insertNameTextField = insertNameTextField;
        this.screen = screen;
        this.setScreen();
    }

    public ButtonToMainFromSubSettingsFromPauseFromSurvive(Videogame videogame, EnhancedScreen screen, Skin skin, String styleName) {
        super(videogame, "Return", skin, styleName);
        this.insertNameTextField = insertNameTextField;
        this.screen = screen;
        this.setScreen();
    }

    public ButtonToMainFromSubSettingsFromPauseFromSurvive(Videogame videogame, EnhancedScreen screen, TextButtonStyle style) {
        super(videogame, "Return", style);
        this.insertNameTextField = insertNameTextField;
        this.screen = screen;
        this.setScreen();
    }

    public void setScreen(){
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                    String newName = insertNameTextField.getText();
                    System.out.println(newName);
                    if (newName.isEmpty()){
                        videogame.getSettings().setName("James");
                    } else {
                        videogame.getSettings().setName(newName);
                    }
                    screen.updateScreen();
                    screen.setScreenState(EnhancedScreen.subscreen.SUBSCREEN_1);
            }
        });
    }
}
