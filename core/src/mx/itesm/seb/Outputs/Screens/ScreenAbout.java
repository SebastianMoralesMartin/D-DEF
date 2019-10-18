package mx.itesm.seb.Outputs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
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

public class ScreenAbout implements Screen {
    private final Videogame videogame;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Texture textureBackground;
    private Text titleHead;
    private Text subtitleHead;
    private Text content;
    private Stage about;


    public ScreenAbout(Videogame videogame) {
        this.videogame = videogame;
    }

    @Override
    public void show() {
        setView();
        setTextures();
        setTexts();
        setStage();

    }//commentario

    private void setTexts() {
        titleHead = new Text("About");
        subtitleHead = new Text("Bienvenido a D-DEF"+"\n Salva Heroes");
        content =new Text("Hoy Te encuentras en 1943 en la bahia de "+"\nDunquerque."+"\nTu misión es salvar a tus compañeros que"
                +"\nquieren regresar a casa sanos y salvos.Estas"+"\nen un submarino que fue dañado en la batalla"+"\ny no se puede sumergir"
                +"\nte van a atacar desde el aire y necesitas defenderte"+"\ncuentas con energia ilimitada para salvar a tus compañeros"+
                "\nasí que aprende a moverte de una manera tactica y ofensiva"+"\ncuentas con diferentes tipos de "+"\ndisparo para poder defenderte"
                +"\nSi derribas el Zeppelín recibiras "+"\nuna recompensa."+"\nMucha Suerte!!!!!!");

    }

    private void setStage() {
        about = new Stage(view);
        ImageButton btnBack = configurarBotonBack();
        addButtons(btnBack);
        Gdx.input.setInputProcessor(about);
    }

    private void addButtons(ImageButton btnBack) {
        about.addActor(btnBack);
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
        textureBackground = new Texture("Screens/Backgrounds/Background.jpeg");
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
        this.eraseScreen();
        this.drawElements();
    }


    private void eraseScreen() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.drawElements();
        about.draw();
    }

    private void drawElements() {
        //Batch escalado a la vista y la camara
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(textureBackground, 0, 0);
        batch.draw(textureBackground,1,1);
        titleHead.draw(batch, videogame.WIDTH /2 - titleHead.getWidth(), videogame.HEIGHT -80);
        subtitleHead.draw(batch, videogame.WIDTH /2 - subtitleHead.getWidth()/2, videogame.HEIGHT -120);
        content.draw(batch, videogame.WIDTH -170 - content.getWidth()/2, videogame.HEIGHT -220);

        batch.end();

        about.draw();
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
