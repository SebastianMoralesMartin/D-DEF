package mx.itesm.seb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bote {
    //Variables iniciales
    private Sprite sprite;

    public Bote(Texture textura, float x, float y){
        sprite = new Sprite(textura);
        sprite.setPosition(x, y);
    }

    public void mover(float dx, float dy){
        sprite.setX(sprite.getX() + dx);
    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

}
