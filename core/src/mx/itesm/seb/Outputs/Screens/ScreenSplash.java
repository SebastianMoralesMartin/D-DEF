
package mx.itesm.seb.Outputs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import mx.itesm.seb.Videogame;

public class ScreenSplash implements Screen {
    private final float TIME_BETWEEN_FRAMES = 0.05f;
    private float animationTimer = TIME_BETWEEN_FRAMES;
    private Skin uiSkin;
    private final Videogame videogame;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private long startTime;
    private long differenceTime;
    private Sprite splashSprite;
    private Stage splash;

    public ScreenSplash(Videogame videogame) {
        this.videogame = videogame;
        this.startTime = TimeUtils.millis();
    }

    @Override
    public void show() {
        this.setView();
        this.setImages();
        this.setStage();
    }

    private void setStage(){
        this.splash = new Stage(view);
        Gdx.input.setInputProcessor(splash);
    }

    private void setImages(){
        this.splashSprite = new Sprite(new Texture("Screens/Titles/TitleHeadSmall.png"));
        this.splashSprite.setPosition(this.videogame.WIDTH/2 - this.splashSprite.getWidth()/2,
                this.videogame.HEIGHT/2 + this.splashSprite.getHeight()/4);
    }


    private void setView(){
        camera = new OrthographicCamera();
        camera.position.set(Videogame.WIDTH /2, Videogame.HEIGHT /2, 0);
        camera.update();
        view = new StretchViewport(Videogame.WIDTH, Videogame.HEIGHT, camera);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        differenceTime = TimeUtils.millis() - startTime;
        if(TimeUtils.millis() - startTime < 2000){
            this.eraseScreen();
            this.drawElements();
        } else {
            this.videogame.setScreen(new ScreenLoading(this.videogame, ScreenLoading.GameScreen.MENU));
        }
    }

    private void eraseScreen() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawElements(){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        splashSprite.draw(this.batch, (float) 1/2000 * this.differenceTime);
        batch.end();
        splash.draw();
    }

    @Override
    public void resize(int width, int height) {
        view.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
