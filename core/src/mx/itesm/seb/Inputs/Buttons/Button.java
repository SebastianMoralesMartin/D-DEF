//Alberto
package mx.itesm.seb.Inputs.Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import mx.itesm.seb.Videogame;

public class Button extends Actor {
    protected Videogame game;
    protected ImageButton button;
    protected TextureRegionDrawable unpressed;
    protected TextureRegionDrawable pressed;
    protected float width;
    protected float height;
    protected float x = 0;
    protected float y = 0;

    public Button(){
    }

    public Button(Videogame game){
        new Button(game, "Buttons/buttonBase.png", "Buttons/buttonBasePressed.png");
    }

    public Button(Videogame game, String unpressedPath){
        new Button(game, unpressedPath, unpressedPath);
    }


    public Button(Videogame game, String unpressedPath, String pressedPath){
        new Button(game, unpressedPath, pressedPath, (float) 0.0, (float) 0.0);
    }

    public Button(Videogame game, String unpressedPath, String pressedPath, float x, float y){
        this.game = game;
        this.setUnpressed(unpressedPath);
        this.setPressed(pressedPath);
        this.button = new ImageButton(this.unpressed, this.pressed);
        this.width = this.button.getWidth();
        this.height = this.button.getHeight();
        this.setX(x);
        this.setY(y);
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
}
