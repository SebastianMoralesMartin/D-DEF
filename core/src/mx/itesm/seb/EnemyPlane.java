package mx.itesm.seb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyPlane {
    private Sprite sprite;
    private Texture textureStable;
    private Texture textureTilted;

    public EnemyPlane(Texture texture, float x, float y){
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
    }

    public EnemyPlane(Texture textureStable, Texture textureTilted, float x, float y){
        this.textureStable = textureStable;
        this.textureTilted = textureTilted;
        sprite = new Sprite(this.textureStable);
        sprite.setPosition(x,y);
    }
    public void switchTexture(){
        if (sprite.getTexture()== textureStable){
            sprite.setTexture(textureTilted);
        }else{
            sprite.setTexture(textureStable);
        }
    }

    public void move(float dx, float dy){
        sprite.setPosition(sprite.getX() + dx, sprite.getY() + dy);
    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

}
