package mx.itesm.seb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {
    private BitmapFont font;

    public Text(){
        font = new BitmapFont(Gdx.files.internal("D-DEF/pixelNormal.fnt"));

    }
    public void mostrarMensaje(SpriteBatch batch, String mensaje, float x, float y){
        GlyphLayout glyph = new GlyphLayout();
        glyph.setText(font, mensaje);
        float anchoTexto = glyph.width;
        font.draw(batch, glyph, x- anchoTexto/2, y);
        float altoTexto = glyph.height;
    }
}
