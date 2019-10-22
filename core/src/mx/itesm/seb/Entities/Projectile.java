package mx.itesm.seb.Entities;

import com.badlogic.gdx.graphics.Texture;

import java.util.LinkedList;

public class Projectile extends AdvancedEntity{
    protected float power;
    protected AdvancedEntity advancedEntity;

    public Projectile(LinkedList<Texture> textures, float x, float y) {
        super(textures, x, y);
    }

    protected enum ProjectileType {
        LOW,
        MID,
        HIGH;
    }

}
