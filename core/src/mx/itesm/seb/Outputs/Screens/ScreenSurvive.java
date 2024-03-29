//Sebastian, Eduardo, y Alberto

package mx.itesm.seb.Outputs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Random;

import mx.itesm.seb.Entities.EnemyPlane;
import mx.itesm.seb.Entities.EnemyProjectile;
import mx.itesm.seb.Entities.PlayerProjectile;
import mx.itesm.seb.Entities.PlayerSubmarine;
import mx.itesm.seb.Entities.Projectile;
import mx.itesm.seb.Inputs.Buttons.ButtonToSubPauseFromSurvive;
import mx.itesm.seb.Outputs.Subscreens.SubscreenPause;
import mx.itesm.seb.Outputs.Texts.Text;
import mx.itesm.seb.Videogame;

import static java.lang.Math.abs;

public class ScreenSurvive extends EnhancedScreen implements Screen{
    private final Videogame videogame;
    private Skin uiButton;
    private Skin uiSkin;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Array<EnemyPlane> enemies;
    private Array<EnemyProjectile> enemyBullets;
    private Array<EnemyProjectile> lifeItems;
    private int DX = +7;
    private int steps = 0;
    private float timerStep = 0f;
    private float MAX_STEP = 0.075f;
    private PlayerSubmarine playerSubmarine;
    private Movement stateSubmarine = Movement.STATIC;
    private Image imageBackground;
    private Stage survive;
    private float energy = 300;
    private Text text;
    private Texture LOW_PROJECTILE_TEXTURE;
    private Texture MID_PROJECTILE_TEXTURE;
    private Texture MAX_PROJECTILE_TEXTURE;
    private Texture ENEMY_PROJECTILE_TEXTURE;
    private Texture LIFE_ITEM;
    private PlayerProjectile playerProjectile;
    private float speed;
    private float power;
    private long startTime = (long)Gdx.graphics.getDeltaTime();
    private long timeCounterAttack = 0;
    private gamestate state = gamestate.GAME;
    private int destroyed = 0;
    private Texture healthbarForeGround, healthbarBackGround;
    private Stage pause;
    private Table bottomLayout;
    private Table topBottomLayout;
    private ImageButton btnBack;
    private TextButton btnDer;
    private TextButton btnIzq;
    private TextButton btnFire;
    private ButtonToSubPauseFromSurvive btnPause;
    private Label labelEnergy;
    private Label labelScore;
    private SubscreenPause subscreenPause;
    private SubOrientation subOrientation;

    //Music
    private Music backgroundMusic;
    private Music battle;

    public ScreenSurvive(Videogame videogame) {
        this.videogame = videogame;
    }

    private void updateState(){
        if(state == gamestate.PAUSE){pause();}
        if(state == gamestate.LOSE){lost();}
    }

    @Override
    public void show() {

        this.setSkins();
        setView();
        setTextures();
        setStage();
        createSubmarine();
        createEnemies();
        setTimer();
        setMusic();

        text = new Text("Energy");
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void updateScreen() {
        this.setMusic();
        switch (this.screenState) {
            case SUBSCREEN_1:
                subscreenPause.getWindow().remove();
        }
    }

    public void setSkins() {
        this.uiButton = this.videogame.getAssetManager().get("Skins/Buttons/uiButton.json");
        if(this.videogame.getSettings().getDarkMode() == false) {
            Texture textureBackground = this.videogame.getAssetManager().get("Skins/Light/lightBackground.png");
            this.imageBackground = new Image(textureBackground);
            //"Skins/Light/lightBackground.png"
            this.uiSkin = this.videogame.getAssetManager().get("Skins/Light/uiLightMode.json");
        } else {
            Texture textureBackground = this.videogame.getAssetManager().get("Skins/Dark/darkBackground.png");
            this.imageBackground = new Image(textureBackground);
            this.uiSkin = this.videogame.getAssetManager().get("Skins/Dark/uiDarkMode.json");
        }
    }

    private void setMusic(){
        if (videogame.getSettings().getMusic() == true) {
            AssetManager manager = videogame.getAssetManager();
            manager.load("Music/Double The Bits.mp3", Music.class);
            manager.finishLoading();
            backgroundMusic = manager.get("Music/Double The Bits.mp3");
            backgroundMusic.setLooping(true);
            backgroundMusic.setVolume(70);
            backgroundMusic.play();
        }
    }

    private void stopMusic(){
        if (videogame.getSettings().getMusic() == true) {
            backgroundMusic.stop();
        }
    }

    private void setTimer() {
        startTime = TimeUtils.nanoTime();
        timeCounterAttack = TimeUtils.nanoTime();
    }
    private void gameEngine(){
    }

    private void createSubmarine() {
        subOrientation = subOrientation.LEFT;
        Texture textureSubmarine = new Texture("Entities/Player/AAPlayer.png");
        LinkedList<Texture> textures = new LinkedList<Texture>();
        textures.add(textureSubmarine);
        playerSubmarine = new PlayerSubmarine(textures, Videogame.WIDTH /2, 8 * Videogame.HEIGHT /20);
    }

    private void createEnemies() {
        if(DX == -7){
            DX = -DX;
            for(EnemyPlane enemyPlane: enemies){
                enemyPlane.switchTexture();
            }
        }
        enemyBullets = new Array<>(20);
        lifeItems = new Array<>(1);
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
        btnBack = configurarBotonBack();
        btnDer = configurarBotonDerecha();
        btnIzq = configurarBotonIzquierda();
        btnFire = configureFireButton();
        btnPause = configurePauseButton();
        labelEnergy = new Label("Energy", this.uiSkin, "default-bold");
        labelScore = new Label("Score", this.uiSkin, "default-bold");
        bottomLayout = new Table();
        bottomLayout.setFillParent(true);
        bottomLayout.bottom();
        topBottomLayout = new Table();
        topBottomLayout.setFillParent(true);
        topBottomLayout.bottom();
        addButtons();
        survive.addActor(bottomLayout);
        if(state == gamestate.PAUSE){addPauseBtn(btnBack);}

        Gdx.input.setInputProcessor(survive);
    }

    private void addPauseBtn(ImageButton btnBack) {

    }

    private void addButtons() {
        if(state == gamestate.GAME){
            topBottomLayout.add(labelEnergy).width(340).height(50).pad(20).padBottom(5).padRight(5);
            topBottomLayout.add(labelScore).width(340).height(50).pad(20).padBottom(5).padLeft(5);
            topBottomLayout.row();
            topBottomLayout.add(btnIzq).width(340).height(170).pad(20).padTop(5).padBottom(5).padRight(5);
            topBottomLayout.add(btnDer).width(340).height(170).pad(20).padTop(5).padBottom(5).padLeft(5);
            bottomLayout.add(topBottomLayout).colspan(2);
            bottomLayout.row();
            bottomLayout.add(btnFire).width(490).height(200).pad(20).padTop(5).padRight(5);
            bottomLayout.add(btnPause).pad(20).padTop(5).padLeft(5);
        }
    }

    private ButtonToSubPauseFromSurvive configurePauseButton(){
        final ButtonToSubPauseFromSurvive btnPause = new ButtonToSubPauseFromSurvive(this.videogame, this, this.uiButton);
        btnPause.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                subscreenPause = new SubscreenPause(videogame, uiSkin, uiButton);
                survive.addActor(subscreenPause.getWindow());
                /**super.clicked(event, x, y);
                    if (state == gamestate.GAME) {
                        subscreenPause = new SubscreenPause(videogame, uiSkin, uiButton);
                        state = gamestate.PAUSE;
                        backgroundMusic.pause();
                        if (pauseScene == null) {
                            pauseScene = new PauseScene(view, batch);
                        }
                    } else {
                        state = gamestate.GAME;
                        backgroundMusic.play();
                    } */
            }
        });

        return btnPause;
    }

    private TextButton configureFireButton(){
        final TextButton btnFire = new TextButton("Fire!", this.uiButton, "yellowRectangle");
        btnFire.getLabel().setFontScale(2f);
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
                        btnFire.setColor(Color.YELLOW);
                        speed = 300;
                        power = 25f;
                        playerProjectile = new PlayerProjectile(textures, playerSubmarine);
                        playerProjectile.setType(Projectile.ProjectileType.HIGH);
                        playEffect();
                        energy-= 25;
                            //startTime = 0;
                    } else if (TimeUtils.timeSinceNanos(startTime) < TimeUtils.millisToNanos(1000)) {
                        //Fire interaction
                        btnFire.setColor(Color.WHITE);
                        speed = 200;
                        power = 75f;
                        playerProjectile = new PlayerProjectile(textures, playerSubmarine);
                        playerProjectile.setType(Projectile.ProjectileType.MID);
                        playerProjectile.switchTexture();
                        playEffect();
                        energy -= 75f;
                        //startTime = 0;
                    } else{
                        btnFire.setColor(Color.GREEN);
                        speed = 150;
                        power = 100f;
                        //Fire interaction
                        playerProjectile = new PlayerProjectile(textures, playerSubmarine);
                        playerProjectile.setType(Projectile.ProjectileType.HIGH);
                        playerProjectile.switchTexture();
                        playerProjectile.switchTexture();
                        playEffect();
                        energy -= 100f;
                        startTime = 0;
                    }

                }
            }
        });
        return btnFire;
    }

    private void playEffect() {
        if (this.videogame.getSettings().getSound() == true) {
            AssetManager manager = videogame.getAssetManager();
            manager.load("Music/explosion.mp3", Music.class);
            manager.finishLoading();
            Music effect = manager.get("Music/explosion.mp3");
            effect.setVolume(50);
            effect.play();

        }
    }

    public void powerupeffect(){
        if (this.videogame.getSettings().getSound() == true) {
            AssetManager manager = videogame.getAssetManager();
            manager.load("Music/powerup.mp3", Music.class);
            manager.finishLoading();
            Music effect = manager.get("Music/powerup.mp3");
            effect.setVolume(75);
            effect.play();

        }
    }

    private TextButton configurarBotonIzquierda() {
        //Botón Izquierda
        btnIzq = new TextButton("< Left", this.uiButton, "orangeRectangle");
        btnIzq.getLabel().setFontScale(1.5f);
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

    private TextButton configurarBotonDerecha() {
        //Botón Derecha
        btnDer = new TextButton("Right >", this.uiButton, "orangeRectangle");
        btnDer.getLabel().setFontScale(1.5f);
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
        LOW_PROJECTILE_TEXTURE = this.videogame.getAssetManager().get("Entities/Projectiles/fireball.png");
        MID_PROJECTILE_TEXTURE = this.videogame.getAssetManager().get("Entities/Projectiles/fireball2.png");
        MAX_PROJECTILE_TEXTURE = this.videogame.getAssetManager().get("Entities/Projectiles/fireball3.png");
        ENEMY_PROJECTILE_TEXTURE = this.videogame.getAssetManager().get("Entities/Projectiles/fireballEnemy.png");
        LIFE_ITEM = this.videogame.getAssetManager().get("Entities/Projectiles/fireballLife.png");
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
        if(this.screenState == subscreen.MAIN){
            //Dibujar enemies
            updateEnemies(delta);
            //Dibujar playerSubmarine
            updateSubmarine();
            //Update bullet path
            updateProjectile(delta);
            colisionVerifier();
            enemyColisionVerifier();
            switch(this.screenState){
                case MAIN:
                    if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
                        this.screenState = subscreen.SUBSCREEN_1;
                        subscreenPause = new SubscreenPause(videogame, uiSkin, uiButton);
                        survive.addActor(subscreenPause.getWindow());
                    }
                    break;
                case SUBSCREEN_1:
                    if(this.screenState == subscreen.SUBSCREEN_1){
                        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
                            this.updateScreen();
                            this.setScreenState(EnhancedScreen.subscreen.MAIN);
                        }
                    }
                    break;
            }
        }
        //Borrar pantalla
        eraseScreen();
    }

    private void enemyColisionVerifier() {
        if(enemyBullets.size != 0){
            for(int i = enemyBullets.size -1; i>= 0; i--){
                Rectangle submarineRect = playerSubmarine.getSprite().getBoundingRectangle();
                Rectangle projectileRect = enemyBullets.get(i).getSprite().getBoundingRectangle();
                if (projectileRect.overlaps(submarineRect)) {
                    enemyBullets.removeIndex(i);
                    playenemyeffect();
                    energy -= 10;
                }
            }

        }
        if(lifeItems.size != 0){
            for(int i = lifeItems.size -1; i>= 0; i--){
                Rectangle submarineRect = playerSubmarine.getSprite().getBoundingRectangle();
                Rectangle projectileRect = lifeItems.get(i).getSprite().getBoundingRectangle();
                if (projectileRect.overlaps(submarineRect)) {
                    lifeItems.removeIndex(i);
                    energy += 30;
                    powerupeffect();
                    if(energy >= 300){energy = 300;}
                }
            }

        }
    }

    private void playenemyeffect() {
        if (this.videogame.getSettings().getSound() == true) {
            AssetManager manager = videogame.getAssetManager();
            manager.load("Music/bulletImpact.mp3", Music.class);
            manager.finishLoading();
            Music effect = manager.get("Music/bulletImpact.mp3");
            effect.setVolume(5);
            effect.play();

        }
    }

    private void colisionVerifier() {
        if(playerProjectile==null){return;}
        for(int i = enemies.size -1; i>=0; i--){
            Rectangle projectileRect = playerProjectile.getSprite().getBoundingRectangle();
            Rectangle enemyRect = enemies.get(i).getSprite().getBoundingRectangle();
            if(projectileRect.overlaps(enemyRect)){
                EnemyPlane enemyPlane = enemies.get(i);
                float px = enemyPlane.getSprite().getX();
                float py = enemyPlane.getSprite().getY();
                EnemyProjectile projectile = new EnemyProjectile(LIFE_ITEM, px, px);
                lifeItems.add(projectile);

                playerProjectile = null;
                enemies.removeIndex(i);
                playEffect();
                energy += power *1.25f;
                if (energy > 300){energy = 300;}
                destroyed += 100;
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
        if (enemyBullets.size != 0){
            for(EnemyProjectile projectile : enemyBullets){
                projectile.move(0, -2f);
                if(projectile.getSprite().getY() <= playerSubmarine.getSprite().getY() - 40){
                    enemyBullets.removeIndex(enemyBullets.indexOf(projectile, true));
                }


            }
        }
        if (lifeItems.size != 0){
            for(EnemyProjectile projectile : lifeItems){
                projectile.move(0, -1f);
                if(projectile.getSprite().getY() <= playerSubmarine.getSprite().getY() - 40){
                    lifeItems.removeIndex(lifeItems.indexOf(projectile, true));

                }


            }
        }

    }

    private void updateSubmarine() {
        switch(stateSubmarine){
            case RIGHT:
                if(subOrientation == SubOrientation.LEFT){
                    subOrientation = subOrientation.RIGHT;
                    playerSubmarine.getSprite().flip(true, false);
                }
                if(playerSubmarine.getSprite().getX() + playerSubmarine.getSprite().getHeight() < Videogame.WIDTH ){
                playerSubmarine.move(3, 0);
                energy -=.50f;
                }

                break;
            case LEFT:
                if(subOrientation == SubOrientation.RIGHT){
                    subOrientation = subOrientation.LEFT;
                    playerSubmarine.getSprite().flip(true, false);
                }
                if(playerSubmarine.getSprite().getX() > 0){
                playerSubmarine.move(-3, 0);
                energy-=.50f;
                }

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

        if (steps % 30 == 0){
            Random r = new Random();
            int planeFiring = r.nextInt(enemies.size);
            EnemyPlane plane = enemies.get(planeFiring);
            float pX = plane.getSprite().getX();
            float pY = plane.getSprite().getY();
            EnemyProjectile projectile = new EnemyProjectile(ENEMY_PROJECTILE_TEXTURE, pX, pY);
            enemyBullets.add(projectile);
        }
        if(energy <= 100){
            if (steps % 40 == 0){
                Random r = new Random();
                int planeFiring = r.nextInt(enemies.size);
                EnemyPlane plane = enemies.get(planeFiring);
                float pX = plane.getSprite().getX();
                float pY = plane.getSprite().getY();
                EnemyProjectile projectile = new EnemyProjectile(LIFE_ITEM, pX, pY);
                lifeItems.add(projectile);
            }
        }
        for(EnemyPlane enemyPlane : enemies){
            if(enemyPlane.getSprite().getY() <= (playerSubmarine.getSprite().getY()+playerSubmarine.getSprite().getHeight())){
               state = gamestate.LOSE;
               lost();
            }
        }
        if (enemies.size <= 0){
            steps = 0;
            createEnemies();
        }


    }

    private void eraseScreen() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawElements();
    }

    private void drawElements() {
        //Batch escalado a la vista y la camara
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawBackground();
        drawEnemies();
        drawSubmarine();
        batch.draw(healthbarBackGround, Videogame.WIDTH/2 - 300, 35 * (Videogame.HEIGHT/100)+text.getHeight(), 300 * 2, 25);
        batch.draw(healthbarForeGround, Videogame.WIDTH/2 - 300, 35 * (Videogame.HEIGHT/100)+text.getHeight(), energy * 2, 25);
        if(playerProjectile != null){
            playerProjectile.render(batch);}
        DecimalFormat df = new DecimalFormat("###");
        labelEnergy.setText("Energy: " + df.format(energy));
        labelScore.setText("Score: " + Integer.toString(destroyed));
        switch (this.screenState){
            case SUBSCREEN_1:
                this.backgroundMusic.pause();
                subscreenPause.getWindow().setY(0);
                this.subscreenPause.draw(this.batch, 1f);
                break;
        }

        batch.end();
        survive.draw();
    }

    private void drawSubmarine() {
        playerSubmarine.render(batch);
    }

    private void drawEnemies() {
        //Dibujo de enemies
        for(EnemyPlane enemyPlane : enemies){
            enemyPlane.render(batch);
        }for(EnemyProjectile projectile : enemyBullets){
            projectile.render(batch);
        }
        for(EnemyProjectile projectile : lifeItems){
            projectile.render(batch);
        }
    }

    private void drawBackground() {
        //Dibujo de Fondo
        imageBackground.draw(this.batch, 1);
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
        ((SpriteDrawable) imageBackground.getDrawable()).getSprite().getTexture().dispose();
        survive.dispose();
        uiSkin.dispose();
        uiButton.dispose();
        videogame.getAssetManager().dispose();
    }



    public enum Movement {
        STATIC,
        RIGHT,
        LEFT
    }
    private void initHealthbar() {

        int width = 1;
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
    private enum gamestate{
        PAUSE,
        GAME,
        LOSE
    }
    private enum SubOrientation {
        RIGHT,
        LEFT
    }
}
