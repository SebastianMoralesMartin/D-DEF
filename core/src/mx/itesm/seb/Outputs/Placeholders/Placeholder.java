package mx.itesm.seb.Outputs.Placeholders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import mx.itesm.seb.Outputs.Texts.Text;
import mx.itesm.seb.Videogame;

public abstract class Placeholder {
    protected Videogame videogame;
    protected Sprite sprite;
    protected Text text;
    protected int height;
    protected int width;
    protected float x;
    protected float y;


    public void setTextMessage(String text){
        this.text.setMessage(text);
    }

    public String getTextMessage(){
        return text.getMessage();
    }

    public void setText(Text text){
        this.text = text;
    }

    public Text getText(){
        return this.text;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

}
