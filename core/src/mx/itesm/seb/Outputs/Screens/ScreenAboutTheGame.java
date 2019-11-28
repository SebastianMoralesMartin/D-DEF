package mx.itesm.seb.Outputs.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import mx.itesm.seb.Inputs.Buttons.ButtonToMenu;
import mx.itesm.seb.Inputs.Buttons.ButtonToSite;
import mx.itesm.seb.Videogame;

public class ScreenAboutTheGame extends EnhancedScreen implements Screen {
    private final Videogame videogame;
    private Skin uiButton;
    private Skin uiSkin;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Image imageBackground;
    private Image imageDunkirk;
    private Label title;
    private Label gameDescription;
    private Label contactUs;
    private Label email;
    private ButtonToMenu btnToMenu;
    private ButtonToSite btnToSite;
    private Stage aboutTheGame;
    private Table topLayout;
    private Table bottomLayout;
    private Music backgroundMusic;

    public ScreenAboutTheGame(Videogame videogame) {
        this.videogame = videogame;
    }

    @Override
    public void show() {
        this.setSkins();
        this.setView();
        this.setLabels();
        this.setButtons();
        this.setImages();
        this.setStage();
        //this.setMusic();
    }

    public void setSkins() {
        if(this.videogame.getSettings().getDarkMode() == false) {
            this.imageBackground = new Image(new Texture(Gdx.files.internal("Skins/Light/lightBackground.png")));
            this.uiButton = new Skin(Gdx.files.internal("Skins/Buttons/uiButton.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Buttons/uiButton.atlas")));
            this.uiSkin = new Skin(Gdx.files.internal("Skins/Light/uiLightMode.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Light/uiLightMode.atlas")));
        } else {
            this.imageBackground = new Image(new Texture(Gdx.files.internal("Skins/Dark/darkBackground.png")));
            this.uiButton = new Skin(Gdx.files.internal("Skins/Buttons/uiButton.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Buttons/uiButton.atlas")));
            this.uiSkin = new Skin(Gdx.files.internal("Skins/Dark/uiDarkMode.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Dark/uiDarkMode.atlas")));
        }
    }

    /*private void setMusic(){
        backgroundMusic = videogame.getBackgroundMusic();
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(50);
        backgroundMusic.play();
    }*/

    private void setStage() {
        this.aboutTheGame = new Stage(view);
        this.setTopLayout();
        this.setBottomLayout();
        this.aboutTheGame.addActor(this.topLayout);
        this.aboutTheGame.addActor(this.bottomLayout);
        Gdx.input.setInputProcessor(aboutTheGame);
    }

    private void setBottomLayout() {
        this.bottomLayout = new Table();
        this.bottomLayout.setFillParent(true);
        this.bottomLayout.bottom();
        //this.bottomLayout.debug();
        this.addElementsToBottomLayout();
    }

    private void addElementsToBottomLayout() {
        this.addButtonsToBottomLayout();
    }

    private void addButtonsToBottomLayout() {
        bottomLayout.add(this.btnToSite).width(700).height(140).pad(20).padBottom(5);
        bottomLayout.row();
        bottomLayout.add(this.btnToMenu).width(500).height(140).pad(20).padTop(5);
    }

    private void addElementsToTopLayout() {
        this.topLayout.add(this.title).pad(20).padBottom(5).fillX();
        this.topLayout.row();
        this.topLayout.add(this.imageDunkirk).pad(20).padBottom(5).padTop(5).fillX().height(300);
        this.topLayout.row();
        this.topLayout.add(this.gameDescription).pad(20).padBottom(5).padTop(5).fillX();
        this.topLayout.row();
        this.topLayout.add(this.email).pad(5).padRight(20).padLeft(20).fillX();
    }

    private void setTopLayout() {
        this.topLayout = new Table();
        this.topLayout.setFillParent(true);
        this.topLayout.top();
        //this.topLayout.debug();
        this.addElementsToTopLayout();
    }

    private void setButtons(){
        this.btnToSite = new ButtonToSite(this.videogame, this.uiButton);
        this.btnToMenu = new ButtonToMenu(this.videogame, this.uiButton);
    }

    private void addButton(ImageButton button) {
        aboutTheGame.addActor(button);
    }

    private void setImages() {
        this.imageDunkirk = new Image(new Texture(Gdx.files.internal("Photos/dunkirkBeach.png")));
    }

    private void setView(){
        camera = new OrthographicCamera();
        camera.position.set(Videogame.WIDTH /2, Videogame.HEIGHT /2, 0);
        camera.update();
        view = new StretchViewport(Videogame.WIDTH, Videogame.HEIGHT, camera);
        batch = new SpriteBatch();
    }

    private void setLabels(){
        this.title = new Label("About the Game", uiSkin, "default-title");
        this.title.setAlignment(Align.center);
        this.title.setFontScale(.75f);
        this.gameDescription = new Label("For the elaboration of this game we, of course, took inspiration from the Second World War's battle of Dunkirk where British soldiers had to evacuate the coast to return to Great Britain. In the historical context of the game, we have placed you, the player, in a damaged vessel near the coast. Your purpose is to assist the evacuation by defending the rescue efforts. You may not survive.\n" +
                "\n" +
                "Your objective is to survive as long as you can, defending both the beach and the soldiers. Please, stay as much time as possible; to do so, you shall have to manage your resources efficiently and effectively.\n" +
                "\n" +
                "You are our only hope.\n" +
                "Save our heroes.", uiSkin, "default");
        this.gameDescription.setFontScale(.6f);
        this.gameDescription.setWrap(true);
        this.contactUs = new Label("Contact us at:", uiSkin, "default-subtitle");
        this.contactUs.setFontScale(.5f);
        this.email = new Label("ddef.contact@gmail.com", uiSkin, "default");
    }

    @Override
    public void render(float delta) {
        this.eraseScreen();
        this.drawElements();
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
        aboutTheGame.draw();
    }

    @Override
    public void updateScreen(){
        this.show();
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
        ((SpriteDrawable) imageBackground.getDrawable()).getSprite().getTexture().dispose();
        aboutTheGame.dispose();
        uiSkin.dispose();
        uiButton.dispose();
    }
}
