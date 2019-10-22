package mx.itesm.seb.Entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.LinkedList;

public class AdvancedEntity extends Entity{
    protected LinkedList<Texture> textures;
    protected int textureIndex;

    public AdvancedEntity(LinkedList<Texture> textures , float x, float y) {
        super(textures.getFirst(), x, y);
        this.textures = textures;
        this.textureIndex = 0;
    }

    public void switchTexture(){
        if (this.textureIndex == textures.toArray().length - 1){
            this.textureIndex = 0;
        } else {
            this.textureIndex = this.textureIndex + 1;
        }
        this.sprite.setTexture(this.textures.get(this.textureIndex));
    }

    public void updateEntity(float delta){
        if (this.health <= 0){
            this.die(delta);
            this.healthStatus = HealthStatus.DEATH;
        }
        this.switchTexture();
    }

    public void appendTexture(Texture texture){
        this.textures.addLast(texture);
    }

}
