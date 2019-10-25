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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;

import mx.itesm.seb.Entities.EnemyPlane;
import mx.itesm.seb.Entities.PlayerProjectile;
import mx.itesm.seb.Entities.PlayerSubmarine;
import mx.itesm.seb.Entities.Projectile;
import mx.itesm.seb.Entities.TestProjectile;
import mx.itesm.seb.Outputs.Texts.Text;
import mx.itesm.seb.Videogame;

public class ScreenSurvive implements Screen {
    private final Videogame videogame;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Array<EnemyPlane> enemies;
    private int DX = +7;
    private int steps = 0;
    private float timerStep = 0f;
    private float MAX_STEP = 0.055f;
    private PlayerSubmarine playerSubmarine;
    private Movement stateSubmarine = Movement.STATIC;
    private Texture textureBackground;
    private Stage survive;
    private float energy = 999;
    private Text text;
    private Texture LOW_PROJECTILE_TEXTURE;
    private Texture MID_PROJECTILE_TEXTURE;
    private Texture MAX_PROJECTILE_TEXTURE;
    private PlayerProjectile playerProjectile;
    private float speed;
    private long startTime = (long)Gdx.graphics.getDeltaTime();

    //Music
    private Music backgroundMusic;

    public ScreenSurvive(Videogame videogame) {
        this.videogame = videogame;
    }

    @Override
    public void show() {
        setView();
        setTextures();
        setStage();
        createSubmarine();
        createEnemies();
        setTimer();
        setMusic();

        //PROTOTYPE: Sets listener and input processor
        //Gdx.input.setInputProcessor(new ProcesadorEntrada());

        //PROTOTYPE: Creates an object that draws text outputs onto a screen
        text = new Text("Energy");
    }

    private void setMusic(){
        AssetManager manager = videogame.callAssetManager();

        manager.load("Music/Battle.mp3", Music.class);
        manager.finishLoading();
        backgroundMusic = manager.get("Music/Battle.mp3");
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(50);
        backgroundMusic.play();
    }

    private void setTimer() {
        startTime = TimeUtils.nanoTime();
    }

    private void createSubmarine() {
        Texture textureSubmarine = new Texture("Entities/Player/AAPlayer.png");
        LinkedList<Texture> textures = new LinkedList<Texture>();
        textures.add(textureSubmarine);
        playerSubmarine = new PlayerSubmarine(textures, Videogame.WIDTH /2, 3* Videogame.HEIGHT /7);
    }

    private void createEnemies() {
        Texture textureStable = new Texture("Entities/Enemies/Planes/enemLuftRight.png");
        Texture textureTilted = new Texture("Entities/Enemies/Planes/enemLuft.png");
        LinkedList<Texture> textures = new LinkedList<Texture>();
        textures.add(textureStable);
        textures.add(textureTilted);
        enemies = new Array<>(11 * 5);
        for(int renglon = 0; renglon < 3; renglon++){
            for(int columna=0; columna <4; columna++){
                EnemyPlane enemyPlane = new EnemyPlane(textures, 40 + columna * 250, 5* Videogame.HEIGHT /7 + renglon * 120);
                enemies.add(enemyPlane);
            }
        }
    }

    private void setStage() {
        survive = new Stage(view);
        ImageButton btnBack = configurarBotonBack();
        ImageButton btnDer = configurarBotonDerecha();
        ImageButton btnIzq = configurarBotonIzquierda();
        ImageButton btnFire = configureFireButton();
        addButtons(btnBack, btnDer, btnIzq, btnFire);
        Gdx.input.setInputProcessor(survive);
    }

    private void addButtons(ImageButton btnBack, ImageButton btnRight, ImageButton btnLeft, ImageButton btnFire) {
        survive.addActor(btnBack);
        survive.addActor(btnRight);
        survive.addActor(btnLeft);
        survive.addActor(btnFire);
    }
    private ImageButton configureFireButton(){
        TextureRegionDrawable trdDisparo = new TextureRegionDrawable(
                new TextureRegion(new Texture("Buttons/buttonBase.png")));
        ImageButton btnFire = new ImageButton(trdDisparo);
        btnFire.setPosition(Videogame.WIDTH - btnFire.getWidth() - 50, 50);
        btnFire.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                startTime = TimeUtils.nanoTime();

                return true;
            }
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Fire interaction
                if(playerProjectile == null) {
                    if (TimeUtils.timeSinceNanos(startTime) < TimeUtils.millisToNanos(200)) {
                        speed = 300;
                            LinkedList<Texture> textures = new LinkedList<Texture>();
                            textures.add(LOW_PROJECTILE_TEXTURE);
                            textures.add(MID_PROJECTILE_TEXTURE);
                            textures.add(MAX_PROJECTILE_TEXTURE);
                            playerProjectile = new PlayerProjectile(textures, playerSubmarine);
                            energy-= 25;
                            //startTime = 0;
                    } else if (TimeUtils.timeSinceNanos(startTime) < TimeUtils.millisToNanos(500)) {
                        //Fire interaction
                        speed = 200;
                            LinkedList<Texture> textures = new LinkedList<Texture>();
                            textures.add(LOW_PROJECTILE_TEXTURE);
                            textures.add(MID_PROJECTILE_TEXTURE);
                            textures.add(MAX_PROJECTILE_TEXTURE);
                            playerProjectile = new PlayerProjectile(textures, playerSubmarine);
                            energy -= 75f;
                            //startTime = 0;
                    } else{
                        speed = 150;
                        //Fire interaction
                            LinkedList<Texture> textures = new LinkedList<Texture>();
                            textures.add(LOW_PROJECTILE_TEXTURE);
                            textures.add(MID_PROJECTILE_TEXTURE);
                            textures.add(MAX_PROJECTILE_TEXTURE);
                            playerProjectile = new PlayerProjectile(textures, playerSubmarine);
                            energy -= 100f;
                            startTime = 0;
                    }

                }
            }
        });
        return btnFire;
    }

    private ImageButton configurarBotonIzquierda() {
        //Botón Izquierda
        TextureRegionDrawable trdIzq = new TextureRegionDrawable(new TextureRegion(new Texture("Buttons/flechaIzquierda.png")));
        ImageButton btnIzq = new ImageButton(trdIzq);
        btnIzq.setPosition(0, 0);
        //Evento de Botón Izquierda
        btnIzq.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stateSubmarine = Movement.LEFT;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stateSubmarine = Movement.STATIC;
            }
        });
        return btnIzq;
    }

    private ImageButton configurarBotonDerecha() {
        //Botón Derecha
        TextureRegionDrawable trdDer = new TextureRegionDrawable(new TextureRegion(new Texture("Buttons/flechaDerecha.png")));
        ImageButton btnDer = new ImageButton(trdDer);
        btnDer.setPosition(btnDer.getWidth() + 10, 0);
        //Evento de Botón Derecha
        btnDer.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stateSubmarine = Movement.RIGHT;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stateSubmarine = Movement.STATIC;
            }
        });
        return btnDer;
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
                videogame.StopMusic();
                videogame.setScreen(new ScreenMenu(videogame));
            }
        });
        return btnBack;
    }

    private void setTextures() {
        textureBackground = new Texture("Screens/Backgrounds/oceanBackgroundPlayVertical.png");
        LOW_PROJECTILE_TEXTURE = new Texture("Entities/Projectiles/bala.png");
        MID_PROJECTILE_TEXTURE = new Texture("Entities/Projectiles/bala.png");
        MAX_PROJECTILE_TEXTURE = new Texture("Entities/Projectiles/bala.png");
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
        //Dibujar enemies
        updateEnemies(delta);
        //Dibujar playerSubmarine
        updateSubmarine();
        //Update bullet path
        updateProjectile(delta);
        //Borrar pantalla
        eraseScreen();
    }

    private void updateProjectile(float delta) {
        if (playerProjectile != null){
            playerProjectile.moveProjectile(delta, speed);
            if (playerProjectile.getSprite().getY()>Videogame.HEIGHT){
                playerProjectile = null;
            }
        }
    }

    private void updateSubmarine() {
        switch(stateSubmarine){
            case RIGHT:
                playerSubmarine.move(3, 0);
                energy -=.25;
                break;
            case LEFT:
                playerSubmarine.move(-3, 0);
                energy-=.25;
                break;
        }
    }

    private void updateEnemies(float delta) {
        timerStep += delta;
        if (timerStep > MAX_STEP){
            timerStep = 0;
            for(EnemyPlane enemyPlane : enemies){
                enemyPlane.move(DX,0);
                //enemyPlane.switchTexture();
            }
            steps++;
            if(steps >= 40){
                steps = 0;
                DX = -DX;
                for(EnemyPlane enemyPlane : enemies){
                    enemyPlane.move(0, -40);
                    enemyPlane.switchTexture();
                }
            }
        }

    }

    private void eraseScreen() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawElements();
        survive.draw();
    }

    private void drawElements() {
        //Batch escalado a la vista y la camara
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawBackground();
        drawEnemies();
        drawSubmarine();
        if(playerProjectile != null){
            playerProjectile.render(batch);}
        text.setMessage("Energy: " + Float.toString(energy));
        text.draw(batch, (60 * Videogame.WIDTH)/100, Videogame.HEIGHT - text.getHeight());
        batch.end();
    }

    private void drawSubmarine() {
        playerSubmarine.render(batch);
    }

    private void drawEnemies() {
        //Dibujo de enemies
        for(EnemyPlane enemyPlane : enemies){
            enemyPlane.render(batch);
        }
    }

    private void drawBackground() {
        //Dibujo de Fondo
        batch.draw(textureBackground, 0, 0);
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

    public enum Movement {
        STATIC,
        RIGHT,
        LEFT
    }
    //Procesador de entrada de la pantalla tactil (toma control total y los botones dejan de funcionar)
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
            Vector3 v = new Vector3(screenX, screenY, 0);
            camera.unproject(v);
            playerSubmarine.getSprite().setX(v.x);
            return true;
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
