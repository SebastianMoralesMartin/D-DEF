package mx.itesm.seb.Outputs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;

import mx.itesm.seb.Entities.EnemyPlane;
import mx.itesm.seb.Entities.PlayerProjectile;
import mx.itesm.seb.Entities.PlayerSubmarine;
import mx.itesm.seb.Entities.Projectile;
import mx.itesm.seb.Outputs.Texts.Text;
import mx.itesm.seb.Videogame;

import static java.lang.Math.abs;

public class ScreenSurvive implements Screen {
    private final Videogame videogame;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Array<EnemyPlane> enemies;
    private int DX = +7;
    private int steps = 0;
    private float timerStep = 0f;
    private float MAX_STEP = 0.075f;
    private PlayerSubmarine playerSubmarine;
    private Movement stateSubmarine = Movement.STATIC;
    private Texture textureBackground;
    private Stage survive;
    private int energy = 300;
    private Text text;
    private Texture LOW_PROJECTILE_TEXTURE;
    private Texture MID_PROJECTILE_TEXTURE;
    private Texture MAX_PROJECTILE_TEXTURE;
    private PlayerProjectile playerProjectile;
    private float speed;
    private float power;
    private long startTime = (long)Gdx.graphics.getDeltaTime();
    private long timeCounterAttack = 0;
    private gamestate state = gamestate.GAME;
    private int destroyed = 0;
    private Texture healthbarForeGround, healthbarBackGround;
    private PauseScene pauseScene;

    //Music
    private Music backgroundMusic;

    public ScreenSurvive(Videogame videogame) {
        this.videogame = videogame;
    }

    private void updateState(){
        if(state == gamestate.PAUSE){pause();}
        if(state == gamestate.LOSE){lost();}
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

        manager.load("Music/Double The Bits.mp3", Music.class);
        manager.finishLoading();
        backgroundMusic = manager.get("Music/Double The Bits.mp3");
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(50);
        backgroundMusic.play();
    }

    private void stopMusic(){
        backgroundMusic.stop();
    }

    private void setTimer() {
        startTime = TimeUtils.nanoTime();
        timeCounterAttack = TimeUtils.nanoTime();
    }
    private void gameEngine(){
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
        for (int renglon = 0; renglon < 3; renglon++) {
            for (int columna = 0; columna < 4; columna++) {
                EnemyPlane enemyPlane = new EnemyPlane(textures, columna * 150, 5 * Videogame.HEIGHT / 7 + renglon * 120);
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
        ImageButton btnPause = configurePauseButton();
        addButtons( btnDer, btnIzq, btnFire, btnPause);
        if(state == gamestate.PAUSE){addPauseBtn(btnBack);}

        Gdx.input.setInputProcessor(survive);
    }

    private void addPauseBtn(ImageButton btnBack) {
        survive.addActor(btnBack);
    }

    private void addButtons( ImageButton btnRight, ImageButton btnLeft, ImageButton btnFire, ImageButton btnPause) {
        if(state == gamestate.GAME){
            survive.addActor(btnRight);
            survive.addActor(btnLeft);
            survive.addActor(btnFire);
            survive.addActor(btnPause);
        }

    }
    private ImageButton configurePauseButton(){
        TextureRegionDrawable trdPause = new TextureRegionDrawable(new TextureRegion(new Texture("Buttons/NewStyle/buttonPause.png")));
        final ImageButton btnPause = new ImageButton(trdPause);
        btnPause.setPosition(0, Videogame.HEIGHT-btnPause.getHeight());
        btnPause.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (state == gamestate.GAME) {
                    state = gamestate.PAUSE;
                    backgroundMusic.pause();
                    if (pauseScene == null) {
                        pauseScene = new PauseScene(view, batch);
                    }
                } else {
                    state = gamestate.GAME;
                    backgroundMusic.play();
                }
                return true;
            }
        });

        return btnPause;
    }
    private ImageButton configureFireButton(){
        TextureRegionDrawable trdDisparo = new TextureRegionDrawable(
                new TextureRegion(new Texture("Buttons/buttonBase.png")));
        final ImageButton btnFire = new ImageButton(trdDisparo);
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
                LinkedList<Texture> textures = new LinkedList<Texture>();
                textures.add(LOW_PROJECTILE_TEXTURE);
                textures.add(MID_PROJECTILE_TEXTURE);
                textures.add(MAX_PROJECTILE_TEXTURE);
                if(playerProjectile == null) {
                    if (TimeUtils.timeSinceNanos(startTime) < TimeUtils.millisToNanos(500)) {
                        btnFire.setColor(Color.RED);
                        speed = 300;
                        power = 25f;
                        playerProjectile = new PlayerProjectile(textures, playerSubmarine);
                        playerProjectile.setType(Projectile.ProjectileType.HIGH);
                        playeffect();
                        energy-= 25;
                            //startTime = 0;
                    } else if (TimeUtils.timeSinceNanos(startTime) < TimeUtils.millisToNanos(1000)) {
                        //Fire interaction
                        btnFire.setColor(Color.BLUE);
                        speed = 200;
                        power = 75f;
                        playerProjectile = new PlayerProjectile(textures, playerSubmarine);
                        playerProjectile.setType(Projectile.ProjectileType.MID);
                        playerProjectile.switchTexture();
                        playeffect();
                        energy -= 75f;
                        //startTime = 0;
                    } else{
                        btnFire.setColor(Color.PURPLE);
                        speed = 150;
                        power = 100f;
                        //Fire interaction
                        playerProjectile = new PlayerProjectile(textures, playerSubmarine);
                        playerProjectile.setType(Projectile.ProjectileType.HIGH);
                        playerProjectile.switchTexture();
                        playerProjectile.switchTexture();
                        playeffect();
                        energy -= 100f;
                        startTime = 0;
                    }

                }
            }
        });
        return btnFire;
    }

    private void playeffect() {
        AssetManager manager = videogame.callAssetManager();
        manager.load("Music/explosion.mp3", Music.class);
        manager.finishLoading();
        Music effect = manager.get("Music/explosion.mp3");
        effect.setVolume(50);
        effect.play();
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
                if (playerSubmarine.getSprite().getX() == 0){
                    stateSubmarine = Movement.STATIC;
                    return true;
                }
                else{
                stateSubmarine = Movement.LEFT;
                return true;
                }
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
        btnBack.setPosition(Videogame.WIDTH/2, Videogame.HEIGHT/2);
        //Evento de Botón Back
        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                 stopMusic();
                videogame.setScreen(new ScreenMenu(videogame));
            }
        });
        return btnBack;
    }

    private void setTextures() {
        textureBackground = new Texture("Screens/Backgrounds/oceanBackgroundPlayVertical_try.png");
        LOW_PROJECTILE_TEXTURE = new Texture("Entities/Projectiles/fireball.png");
        MID_PROJECTILE_TEXTURE = new Texture("Entities/Projectiles/fireball2.png");
        MAX_PROJECTILE_TEXTURE = new Texture("Entities/Projectiles/fireball3.png");
    }

    private void setView(){
        setCamera();
        view = new StretchViewport(Videogame.WIDTH, Videogame.HEIGHT, camera);
        batch = new SpriteBatch();
        initHealthbar();
    }

    private void setCamera() {
        camera = new OrthographicCamera();
        camera.position.set(Videogame.WIDTH /2, Videogame.HEIGHT /2, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        if(state == gamestate.GAME){
            //Dibujar enemies
            updateEnemies(delta);
            //Dibujar playerSubmarine
            updateSubmarine();
            //Update bullet path
            updateProjectile(delta);
            //Borrar pantalla
            eraseScreen();
            colisionVerifier();
        }


    }

    private void colisionVerifier() {
        if(playerProjectile==null){return;}
        for(int i = enemies.size -1; i>=0; i--){
            Rectangle projectileRect = playerProjectile.getSprite().getBoundingRectangle();
            Rectangle enemyRect = enemies.get(i).getSprite().getBoundingRectangle();
            if(projectileRect.overlaps(enemyRect)){
                playerProjectile = null;
                enemies.removeIndex(i);
                playeffect();
                energy += power *1.25f;
                if (energy > 300){energy = 300;}
                destroyed ++;
                break;
            }
        }
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
        if (energy <= 0) {
            state = gamestate.LOSE;
            lost();
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
                    enemyPlane.move(0, -2*abs(DX));
                    enemyPlane.switchTexture();
                }
            }
        }
        for(EnemyPlane enemyPlane : enemies){
            if(enemyPlane.getSprite().getY() <= (playerSubmarine.getSprite().getY()+playerSubmarine.getSprite().getHeight())){
               state = gamestate.LOSE;
               lost();
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
        batch.draw(healthbarBackGround, Videogame.WIDTH/2 - 150, 20 * (Videogame.HEIGHT/100)+text.getHeight(), 300, 25);
        batch.draw(healthbarForeGround, Videogame.WIDTH/2 - 150, 20 * (Videogame.HEIGHT/100)+text.getHeight(), energy, 25);
        if(playerProjectile != null){
            playerProjectile.render(batch);}
        text.setMessage("Energy: " + Integer.toString(energy));
        text.draw(batch, (60 * Videogame.WIDTH)/100, 30 * (Videogame.HEIGHT/100)+text.getHeight());
        text.setMessage("Score: " + Integer.toString(destroyed));
        text.draw(batch, (10 * Videogame.WIDTH)/100, 30 * (Videogame.HEIGHT/100)+text.getHeight());
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

    public void lost(){
        stopMusic();
        videogame.setScreen(new ScreenLost(destroyed, videogame));
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
    private void initHealthbar() {

        int width =1 ;
        int height = 1;
        Pixmap pixmap = createProceduralPixmap(width, height,0,1,0);
        Pixmap pixmap2 = createProceduralPixmap(width, height,1,0,0);

        healthbarForeGround = new Texture(pixmap);
        healthbarBackGround = new Texture(pixmap2);



    }



    private Pixmap createProceduralPixmap (int width, int height,int r,int g,int b) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        pixmap.setColor(r, g, b, 1);
        pixmap.fill();

        return pixmap;
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
    private enum gamestate{
        PAUSE,
        GAME,
        LOSE
    }
    class PauseScene extends Stage {
        public PauseScene(Viewport view, SpriteBatch batch){
            super(view, batch);

        }
    }
}
