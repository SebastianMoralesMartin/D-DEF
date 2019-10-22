package mx.itesm.seb.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import mx.itesm.seb.Videogame;

public class Entity {
    protected Videogame videogame;
    protected float health;
    protected float maxHealth;
    protected float fadeAwayRate;
    protected float movementExhaust;
    protected float baseMovementExhaust;
    protected float underwaterExhaust;
    protected float baseUnderwaterExhaust;
    protected float existanceExhaust;
    protected float baseExistanceExhaust;
    protected float dz = 150;
    protected float speed;
    protected HealthStatus healthStatus;
    protected FieldDepth fieldDepth;
    protected HeightLevel heightLevel;
    protected Sprite sprite;

    public Entity(Texture texture, float x, float y){
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        this.healthStatus = HealthStatus.ALIVE;
    }

    public Sprite getSprite(){
        return sprite;
    }

    public void move(float dx, float dy){
        sprite.setPosition(sprite.getX() + dx, sprite.getY() + dy);
        this.health = this.health - (dy * this.movementExhaust);
        this.health = this.health - (dx * this.movementExhaust);
    }

    public void moveX(float dx){
        sprite.setX(sprite.getX() + dx);
        this.reduceHealthBy(dx * this.movementExhaust);
    }

    public void moveY(float dy){
        sprite.setY(sprite.getY() + dy);
        this.reduceHealthBy(dy * this.movementExhaust);
    }

    public void moveFront(){
        switch (this.fieldDepth){
            case MIDDLE:
                this.fieldDepth = FieldDepth.FRONT;
                break;
            case BACK:
                this.fieldDepth = FieldDepth.MIDDLE;
                break;
        }
        this.reduceHealthBy(this.dz * this.movementExhaust);
    }

    public void moveBack(){
        switch (this.fieldDepth){
            case FRONT:
                this.fieldDepth = FieldDepth.MIDDLE;
                break;
            case MIDDLE:
                this.fieldDepth = FieldDepth.BACK;
                break;
        }
        this.reduceHealthBy(this.dz * this.movementExhaust);
    }

    public void moveUp(){
        switch (this.heightLevel){
            case SURFACE:
                this.heightLevel = heightLevel.AIR;
                break;
            case UNDERWATER:
                this.heightLevel = heightLevel.SURFACE;
                break;
        }
    }

    public void moveDown(){
        switch (this.heightLevel){
            case AIR:
                this.heightLevel = heightLevel.SURFACE;
                break;
            case SURFACE:
                this.heightLevel = heightLevel.UNDERWATER;
                break;
        }
    }

    public void reduceHealthBy(float healthReduction){
        this.health = this.health - healthReduction;
    }

    public void increaseHealthBy(float healthIncrease){
        this.health = this.health + healthIncrease;
    }

    protected void die(float delta){
        while (this.sprite.getColor().a != 0){
            this.sprite.setAlpha(this.sprite.getColor().a - this.fadeAwayRate * delta);
        }
    }

    public void setMovementExhaust(float movementExhaustFactor){
        this.movementExhaust = this.baseMovementExhaust * movementExhaustFactor;
    }

    public float getMovementExhaust(){
        return this.movementExhaust;
    }

    public void setUnderwaterExhaust(float underwaterExhaustFactor){
        this.underwaterExhaust = this.baseUnderwaterExhaust * underwaterExhaustFactor;
    }

    public float getUnderwaterExhaust(){
        return this.underwaterExhaust;
    }

    public void setExistanceExhaust(float existanceExhaustFactor){
        this.existanceExhaust = this.baseExistanceExhaust * existanceExhaustFactor;
    }

    public float getExistanceExhaust(){
        return this.existanceExhaust;
    }

    public FieldDepth getFieldDepth(){
        return this.fieldDepth;
    }

    public HealthStatus getHealthStatus(){
        return this.healthStatus;
    }

    public HeightLevel getHeightLevel(){
        return this.heightLevel;
    }

    public float getSpeed(){
        return this.speed;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void updateEntity(float delta){
        if (this.health <= 0){
            this.die(delta);
            this.healthStatus = HealthStatus.DEATH;
        }
        switch (this.healthStatus){
            case ALIVE:
                break;
            case DEATH:
                break;
        }
        switch (this.heightLevel){
            case SURFACE:
                this.reduceHealthBy(this.existanceExhaust * delta);
                break;
            case UNDERWATER:
                this.reduceHealthBy(this.underwaterExhaust * delta);
                break;
        }
    }

    protected enum HealthStatus{
        ALIVE,
        DEATH
    }

    protected enum FieldDepth{
        FRONT,
        MIDDLE,
        BACK
    }

    protected enum HeightLevel{
        AIR,
        SURFACE,
        UNDERWATER
    }

}

