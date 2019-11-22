package mx.itesm.seb.Outputs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import mx.itesm.seb.Outputs.Texts.Text;
import mx.itesm.seb.Videogame;

//Raul Ortiz Mateos
public class ScreenSettings implements Screen {
    private final Videogame videogame;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Texture textureBackground;
    private Text titleHead;
    private Text subtitleHead;
    private Text content;
    private Stage settings;
    private Music backgroundMusic;


    public ScreenSettings(Videogame videogame) {
        this.videogame = videogame;
    }

    @Override
    public void show() {
        setView();
        setTextures();
        setStage();
        //setMusic();
    }

    /*private void setMusic(){
        AssetManager manager = videogame.callAssetManager();

        manager.load("Music/Double The Bits.mp3", Music.class);
        manager.finishLoading();
        backgroundMusic = manager.get("Music/Double The Bits.mp3");
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(50);
        backgroundMusic.play();
    }*/

    private void setStage() {
        settings = new Stage(view);
        ImageButton btnBack = configurarBotonBack();
        addButtons(btnBack);
        Gdx.input.setInputProcessor(settings);
    }

    private void addButtons(ImageButton btnBack) {
        settings.addActor(btnBack);
    }

    private ImageButton configurarBotonBack() {

        //Botón Back
        TextureRegionDrawable trdBack = new TextureRegionDrawable(new TextureRegion(new Texture("Buttons/back.png")));
        TextureRegionDrawable trdBackPressed = new TextureRegionDrawable(new TextureRegion(new Texture("Buttons/backPressed.png")));
        ImageButton btnBack = new ImageButton(trdBack, trdBackPressed);
        btnBack.setPosition(0, Videogame.HEIGHT - btnBack.getHeight());
        //Evento de Botón Back
        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                videogame.setScreen(new ScreenMenu(videogame));
            }
        });
        return btnBack;
    }

    private void setTextures() {
        textureBackground = new Texture("Screens/Backgrounds/oceanBackgroundPlayVertical.png");
    }

    private void setView(){
        setCamera();
        view = new StretchViewport(Videogame.WIDTH, Videogame.HEIGHT, camera);
        batch = new SpriteBatch();
    }

    private void setCamera() {
        camera = new OrthographicCamera();
        camera.position.set(Videogame.WIDTH /2, Videogame.HEIGHT /2, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        eraseScreen();
        drawElements();
    }


    private void eraseScreen() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawElements() {
        //Batch escalado a la vista y la camara
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(textureBackground,1,1);
        batch.draw(textureBackground, 0, 0);

        batch.end();

        settings.draw();
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
        //dispose();
    }

    @Override
    public void dispose() {
        textureBackground.dispose();
    }


    class ProcesadorEntrada implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;

        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
