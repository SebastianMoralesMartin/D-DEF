package mx.itesm.seb.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Projectile {
    private Sprite sprite;
    private float speed;
    private Power power;

    public Projectile(Texture texture, float x, float y){
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
    }

    public void MoveBullet(float delta){
        float distance = delta * speed;
        sprite.setY(sprite.getY() + distance);
    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }
    public void setSpeed(Projectile Power){
        switch(power){
            case LOW:
                speed = 750;
                break;
            case MID:
                speed = 500;
                break;
            case HIGH:
                speed = 300;
                break;

        }
    }

    private enum Power{
        LOW,
        MID,
        HIGH;
    }
}
