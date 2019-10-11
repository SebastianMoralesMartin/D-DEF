package mx.itesm.seb.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Autor: Sebastian Morales Martin

public class Projectile {
    private Sprite sprite;
    private float speed;
    private float power;
    private ProjectileType type;

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
        switch(type){
            case LOW:
                //sprite.setTexture();
                speed = 750;
                power = 100;
                break;
            case MID:
                //sprite.setTexture();
                speed = 500;
                power = 200;
                break;
            case HIGH:
                //sprite.setTexture();
                speed = 300;
                power = 300;
                break;
        }
    }

    private enum ProjectileType {
        LOW,
        MID,
        HIGH;
    }
}
