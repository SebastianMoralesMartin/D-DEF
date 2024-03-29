package mx.itesm.seb.Outputs.Texts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {
    private BitmapFont font;
    private String message;
    private float width;
    private float height;
    private GlyphLayout glyph;

    public Text(){
        new Text("DEFAULT");
    }

    public Text(String message){
        this.font = new BitmapFont(Gdx.files.internal("Fonts/pixelNormal.fnt"));
        this.glyph = new GlyphLayout();
        setMessage(message);
    }

    public void setMessage(String message){
        font.setColor(Color.RED);
        this.message = new String(message);
        this.glyph.setText(this.font, this.message);
        this.width = glyph.width;
        this.height = glyph.height;
    }

    public String getMessage(){
        return this.message;
    }

    public float getWidth() {
        return glyph.width;
    }
    public float getHeight(){
        return glyph.height;
    }

    public void draw(SpriteBatch batch, float x, float y){
        //font.setColor(Color.TAN);
        float anchoTexto = glyph.width;
        font.draw(batch, this.glyph, x, y);
    }
}
