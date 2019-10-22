package mx.itesm.seb.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

//Autor: Sebastian Morales Martin

public class TestProjectile extends Projectile{
    private Sprite sprite;
    private float speed = 300;
    private float power = 100;
    private ProjectileType type = ProjectileType.LOW;
    private Texture lowPowerTexture;
    private Texture midPowerTexture;
    private Texture highPowerTexture;
    public float powerLoss;

    public TestProjectile(LinkedList<Texture> textures, PlayerSubmarine playerSubmarine){
        super(textures, playerSubmarine.getSprite().getX(), playerSubmarine.getSprite().getY() + playerSubmarine.getSprite().getHeight()/2);
        this.power = power;
        this.speed = speed;
        this.lowPowerTexture = lowPowerTexture;
        this.midPowerTexture = midPowerTexture;
        this.highPowerTexture = highPowerTexture;
        type = ProjectileType.LOW;
    }

    public void move(float delta){
        float distance = delta * speed;
        sprite.setY(sprite.getY() + distance);
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void setType(ProjectileType type){
        switch(type){
            case LOW:
                sprite.setTexture(lowPowerTexture);
                speed = 750;
                power = 100;
                powerLoss = 20;
                break;
            case MID:
                sprite.setTexture(midPowerTexture);
                speed = 500;
                power = 200;
                powerLoss = 50;
                break;
            case HIGH:
                sprite.setTexture(highPowerTexture);
                speed = 300;
                power = 300;
                powerLoss = 100;
                break;
        }
    }

    private enum ProjectileType {
        LOW,
        MID,
        HIGH;
    }

    public Sprite getSprite(){
        return sprite;
    }
}
