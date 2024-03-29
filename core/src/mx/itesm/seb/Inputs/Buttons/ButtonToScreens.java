//Alberto

package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import mx.itesm.seb.Outputs.Screens.ScreenAbout;
import mx.itesm.seb.Outputs.Screens.ScreenMenu;
import mx.itesm.seb.Outputs.Screens.ScreenSurvive;
import mx.itesm.seb.Videogame;

public class ButtonToScreens extends TestButton {
    protected ToScreen toScreen = ToScreen.DEFAULT;

    public ButtonToScreens(Videogame game, ToScreen toScreenReference){
        super();
        new ButtonToScreens(game, ToScreen.DEFAULT,"Buttons/buttonBase.png", "Buttons/buttonBasePressed.png");
    }

    public ButtonToScreens(Videogame game, ToScreen toScreenReference, String unpressedPath){
        super();
        new ButtonToScreens(game, toScreenReference, unpressedPath, unpressedPath);
    }

    public ButtonToScreens(Videogame game, ToScreen toScreenReference, String unpressedPath, String pressedPath){
        super();
        new ButtonToScreens(game, toScreenReference, unpressedPath, pressedPath, (float) 0.0, (float) 0.0);
    }

    public ButtonToScreens(Videogame game, ToScreen toScreenReference, String unpressedPath, String pressedPath, float x, float y){
        super();
        this.game = game;
        this.setUnpressed(unpressedPath);
        this.setPressed(pressedPath);
        this.button = new ImageButton(this.unpressed, this.pressed);
        this.width = this.button.getWidth();
        this.height = this.button.getHeight();
        this.setX(x);
        this.setY(y);
        this.setToScreen(toScreenReference);
    }

    public void setUnpressed(String unpressedPath) {
        this.unpressed = new TextureRegionDrawable(new TextureRegion(new Texture(unpressedPath)));
    }

    public void setPressed(String pressedPath) {
        this.pressed = new TextureRegionDrawable(new TextureRegion(new Texture(pressedPath)));
    }

    public void setX(float x){
        this.x = x - this.width/2;
        button.setX(this.x);
    }

    public void setY(float y){
        this.y = y - this.height/2;
        button.setY(this.y);
    }

    public void setPosition(float x, float y){
        this.setX(x);
        this.setY(y);
    }

    public float getX(float x){
        return this.x + this.width/2;
    }

    public float getY(float y){
        return this.y + this.height/2;
    }

    public ImageButton getButton(){
        return button;
    }

    public ToScreen getToScreen(){
        return this.toScreen;
    }

    public void setToScreen(ToScreen toScreenReference){
        this.toScreen = toScreenReference;
        switch(this.toScreen){
            case MENU:
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);

                        game.setScreen(new ScreenMenu(game));
                    }
                });
                break;
            case GAME:
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);

                        game.setScreen(new ScreenSurvive(game));
                    }
                });
                break;
            case SETTINGS:
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);

                        //game.setScreen(new ScreenSettings(game));
                    }
                });
                break;
            case ABOUT:
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);

                        game.setScreen(new ScreenAbout(game));
                    }
                });
                break;
            case DEFAULT:
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        System.exit(0);
                    }
                });
                break;
            case SUBMENU:
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        //game.setScreen(new ScreenSubMenuAbout(game));
                    }
                });
                break;

        }
    }

    public enum ToScreen{
        DEFAULT,
        MENU,
        GAME,
        SETTINGS,
        ABOUT,
        SUBMENU;
    }
}
