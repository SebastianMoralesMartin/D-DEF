package mx.itesm.seb.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

public class PlayerSubmarine extends AdvancedEntity {

    public PlayerSubmarine(LinkedList<Texture> textures, float x, float y){
        super(textures, x, y);
        this.health = 1000;
        this.maxHealth = 1000;
        this.fadeAwayRate = 0.1f;
        this.baseMovementExhaust = 0.2f;
        this.movementExhaust = this.baseMovementExhaust;
        this.baseUnderwaterExhaust = 0.4f;
        this.underwaterExhaust = this.baseUnderwaterExhaust;
        this.baseExistanceExhaust = 0.1f;
        this.existanceExhaust = this.baseExistanceExhaust;
        this.fieldDepth = FieldDepth.MIDDLE;
        this.heightLevel = HeightLevel.SURFACE;
    }

}
