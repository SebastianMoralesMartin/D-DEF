//Alberto

package mx.itesm.seb.Outputs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import mx.itesm.seb.Videogame;

public class ScreenLoading implements Screen {
    private final float TIME_BETWEEN_FRAMES = 0.05f;
    private float animationTimer = TIME_BETWEEN_FRAMES;
    private Skin uiSkin;
    private final Videogame videogame;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Image imageBackground;
    private Image loadingImage;
    private Label toScreen;
    private Label loadingProgress;
    private Label whatsLoading;
    private Table midLayout;
    private Stage loading;
    private AssetManager assetManager;
    private GameScreen gameScreen;

    public ScreenLoading(Videogame videogame, GameScreen nextScreen) {
        this.videogame = videogame;
        this.gameScreen = nextScreen;
    }

    @Override
    public void show() {
        this.assetManager = this.videogame.getAssetManager();
        this.setSkins();
        this.setView();
        this.setImages();
        this.setLabels();
        this.setStage();
        this.loadResources();
    }

    private void setSkins(){
        if(this.videogame.getSettings().getDarkMode() == false) {
            this.imageBackground = new Image(new Texture(Gdx.files.internal("Skins/Light/lightBackground.png")));
            this.uiSkin = new Skin(Gdx.files.internal("Skins/Light/uiLightMode.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Light/uiLightMode.atlas")));
        } else {
            this.imageBackground = new Image(new Texture(Gdx.files.internal("Skins/Dark/darkBackground.png")));
            this.uiSkin = new Skin(Gdx.files.internal("Skins/Dark/uiDarkMode.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Dark/uiDarkMode.atlas")));
        }
    }

    private void setStage(){
        this.loading = new Stage(view);
        this.setMidLayout();
        this.loading.addActor(this.midLayout);
        Gdx.input.setInputProcessor(loading);
    }

    private void setMidLayout(){
        this.midLayout = new Table();
        this.midLayout.setFillParent(true);
        this.midLayout.center();
        this.addElementsToMidLayout();
    }

    private void addElementsToMidLayout() {
        this.midLayout.add(this.loadingImage).pad(20).padBottom(50).height(500).width(500);
        this.midLayout.row();
        this.midLayout.add(this.toScreen).pad(5).width(600);
        this.midLayout.row();
        this.midLayout.add(this.loadingProgress).pad(5).width(600);
        this.midLayout.row();
        this.midLayout.add(this.whatsLoading).pad(5).width(600);
    }

    private void setImages(){
        this.loadingImage = new Image(new Texture("Icons/loadingDDef.png"));
        this.loadingImage.setOrigin(this.loadingImage.getWidth()/2, this.loadingImage.getHeight()/2);
    }

    private void setLabels(){
        switch (this.gameScreen){
            case MENU:
                this.toScreen = new Label("Jumping to Menu", this.uiSkin, "default-title");
                break;
            case ABOUT_THE_DEVS:
                this.toScreen = new Label("Jumping to About the Devs", this.uiSkin, "default-title");
                break;
            case ABOUT_THE_GAME:
                this.toScreen = new Label("Jumping to About the Game", this.uiSkin, "default-title");
                break;
            case SURVIVE:
                this.toScreen = new Label("Jumping to Survive", this.uiSkin, "default-title");
                break;
            case TUTORIAL:
                this.toScreen = new Label("Jumping to Tutorial", this.uiSkin, "default-title");
                break;
            case DEFENSE:
                this.toScreen = new Label("Jumping to Defense", this.uiSkin, "default-title");
                break;
        }
        this.toScreen.setAlignment(Align.center);
        this.toScreen.setWrap(true);
        this.loadingProgress = new Label("Loading", this.uiSkin, "default-subtitle");
        this.loadingProgress.setAlignment(Align.center);
        this.loadingProgress.setWrap(true);
        this.whatsLoading = new Label("Preparing to Load...", this.uiSkin, "default");
        this.whatsLoading.setAlignment(Align.center);
        this.whatsLoading.setWrap(true);
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
        this.eraseScreen();
        this.drawElements();
        this.updateAnimations(delta);
        this.updateloadResources();
    }

    private void updateloadResources() {
        this.loadingProgress.setText("Loading: %" + (int) (this.assetManager.getProgress() * 100));
        if (assetManager.update()){
            switch (this.gameScreen) {
                case MENU:
                    this.videogame.setScreen(new ScreenMenu(this.videogame));
                    break;
                case ABOUT_THE_DEVS:
                    this.videogame.setScreen(new ScreenAboutTheDevs(this.videogame));
                    break;
                case ABOUT_THE_GAME:
                    this.videogame.setScreen(new ScreenAboutTheGame(this.videogame));
                    break;
                case SURVIVE:
                    this.videogame.setScreen(new ScreenSurvive(this.videogame));
                    break;
                case TUTORIAL:
                    //...
                    break;
                case DEFENSE:
                    //...
                    break;
            }
        }
    }

    private void updateAnimations(float delta) {
        animationTimer = animationTimer - delta;
        if(animationTimer <= 0){
            loadingImage.rotateBy(15);
            animationTimer = TIME_BETWEEN_FRAMES;
        }
    }

    private void eraseScreen() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawElements(){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        imageBackground.draw(this.batch, 1);
        batch.end();
        loading.draw();
    }

    private void loadResources(){
        this.loadSkins();
        switch (this.gameScreen){
            case MENU:
                this.loadResourcesMenu();
                break;
            case ABOUT_THE_DEVS:
                this.loadResourcesAboutTheDevs();
                break;
            case ABOUT_THE_GAME:
                this.loadResourcesAboutTheGame();
                break;
            case SURVIVE:
                this.loadResourcesSurvive();
                break;
            case TUTORIAL:
                this.loadResourcesTutorial();
                break;
            case DEFENSE:
                this.loadResourcesDefense();
                break;
        }
    }

    private void loadResourcesDefense() {

    }

    private void loadResourcesTutorial() {

    }

    private void loadResourcesSurvive() {

    }

    private void loadResourcesAboutTheGame() {
        this.whatsLoading.setText("Loading Dunkirk Logo.");
        this.assetManager.load("Photos/dunkirkBeach.png", Texture.class);
    }

    private void loadResourcesAboutTheDevs() {
        this.whatsLoading.setText("Loading Tec de Monterrey Logo.");
        this.assetManager.load("Icons/tecDeMonterrey.png", Texture.class);
        this.whatsLoading.setText("Loading Alberto photo.");
        this.assetManager.load("Photos/alberto.png", Texture.class);
        this.whatsLoading.setText("Loading Eduardo photo.");
        this.assetManager.load("Photos/eduardo.png", Texture.class);
        this.whatsLoading.setText("Loading Raul photo.");
        this.assetManager.load("Photos/raul.png", Texture.class);
        this.whatsLoading.setText("Loading Sebastian photo.");
        this.assetManager.load("Photos/sebastian.png", Texture.class);
    }

    private void loadResourcesMenu() {

    }

    private void loadSkins(){
        this.whatsLoading.setText("Loading Light Texture Buttons.");
        this.assetManager.load("Skins/Light/uiLightMode.atlas", TextureAtlas.class);
        this.whatsLoading.setText("Loading Light Buttons.");
        this.assetManager.load("Skins/Buttons/uiButton.json", Skin.class);
        if(this.videogame.getSettings().getDarkMode() == false) {
            this.whatsLoading.setText("Loading Light Background.");
            this.assetManager.load("Skins/Light/lightBackground.png", Texture.class);
            this.whatsLoading.setText("Loading Light Textures.");
            this.assetManager.load("Skins/Light/uiLightMode.atlas", TextureAtlas.class);
            this.whatsLoading.setText("Loading Light Skin.");
            this.assetManager.load("Skins/Light/uiLightMode.json", Skin.class);
        } else {
            this.whatsLoading.setText("Loading Dark Background.");
            this.assetManager.load("Skins/Dark/darkBackground.png", Texture.class);
            this.whatsLoading.setText("Loading Dark Textures.");
            this.assetManager.load("Skins/Dark/uiDarkMode.atlas", TextureAtlas.class);
            this.whatsLoading.setText("Loading Dark Skin.");
            this.assetManager.load("Skins/Dark/uiDarkMode.json", Skin.class);
        }
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

    public enum GameScreen{
        MENU,
        ABOUT_THE_GAME,
        ABOUT_THE_DEVS,
        TUTORIAL,
        SURVIVE,
        DEFENSE
    }
}
