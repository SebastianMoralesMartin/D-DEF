package mx.itesm.seb;

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
        this.font = new BitmapFont(Gdx.files.internal("D-DEF/pixelNormal.fnt"));
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

    public void draw(SpriteBatch batch, float x, float y){
        //font.setColor(Color.TAN);
        font.draw(batch, this.glyph, x, y);
    }
}
