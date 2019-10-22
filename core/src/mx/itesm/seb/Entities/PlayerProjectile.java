package mx.itesm.seb.Entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.LinkedList;

public class PlayerProjectile extends Projectile {
    private PlayerSubmarine playerSubmarine;

    public PlayerProjectile(LinkedList<Texture> textures, PlayerSubmarine playerSubmarine) {
        super(textures, playerSubmarine.getSprite().getX(), playerSubmarine.getSprite().getY());
        this.advancedEntity = playerSubmarine;
        this.health = 50;
        this.maxHealth = 50;
        this.baseMovementExhaust = 1;
        this.movementExhaust = this.baseMovementExhaust;
        this.baseUnderwaterExhaust = 10;
        this.underwaterExhaust = this.baseUnderwaterExhaust;
        this.baseExistanceExhaust = 1;
        this.existanceExhaust = 0;
        this.healthStatus = HealthStatus.ALIVE;
        this.fieldDepth = playerSubmarine.getFieldDepth();
        this.heightLevel = playerSubmarine.getHeightLevel();
    }

    public void setType(ProjectileType projectileType){
        switch(projectileType){
            case LOW:
                //sprite.setTexture(lowPowerTexture);
                this.setSpeed(750);
                power = 100;
                this.setExistanceExhaust(20);
                break;
            case MID:
                //sprite.setTexture(midPowerTexture);
                this.setSpeed(500);
                power = 200;
                this.setExistanceExhaust(50);
                break;
            case HIGH:
                //sprite.setTexture(highPowerTexture);
                this.setSpeed(300);
                power = 300;
                this.setExistanceExhaust(100);
                break;
        }
    }

}
