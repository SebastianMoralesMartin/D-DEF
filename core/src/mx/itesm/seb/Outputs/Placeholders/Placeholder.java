package mx.itesm.seb.Outputs.Placeholders;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import mx.itesm.seb.Videogame;

public class Placeholder {
    protected Videogame videogame;
    protected Skin skin;
    protected Table table;
    protected Sprite sprite;

    public Placeholder(Videogame videogame, Skin skin, Sprite sprite){
        this.videogame = videogame;
        this.skin = skin;
        this.sprite = sprite;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
