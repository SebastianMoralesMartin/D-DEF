package mx.itesm.seb.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerSubmarine {
    private Sprite sprite;

    public PlayerSubmarine(Texture texture, float x, float y){
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
    }
    public Sprite getSprite(){
        return sprite;
    }

    public void move(float dx, float dy){
        sprite.setX(sprite.getX() + dx);
    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

}
