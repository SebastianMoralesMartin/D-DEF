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

import mx.itesm.seb.Inputs.Buttons.ButtonToAbout;
import mx.itesm.seb.Inputs.Buttons.ButtonToGame;
import mx.itesm.seb.Inputs.Buttons.ButtonToSettings;
import mx.itesm.seb.Videogame;

public class ScreenMenu implements Screen {
    private final Videogame videogame;
    private Skin uiButton;
    private Skin uiSkin;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Image imageBackground;
    private Image imageTitle;
    private Label title;
    private Label subtitle;
    private Label about;
    private Label settings;
    private Label play;
    private ButtonToGame btnNewGame;
    private ButtonToSettings btnSettings;
    private ButtonToAbout btnAbout;
    private Stage menu;
    private Table topLayout;
    private Table midLayout;
    private Table bottomLayout;
    private Music backgroundMusic;

    public ScreenMenu(Videogame videogame) {
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

    private void setSkins() {
        Boolean flag = new Boolean(true);
        if(flag == false) {
            this.uiButton = new Skin(Gdx.files.internal("Skins/Buttons/uiButton.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Buttons/uiButton.atlas")));
            this.uiSkin = new Skin(Gdx.files.internal("Skins/Light/uiLightMode.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Light/uiLightMode.atlas")));
        } else {
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
        this.menu = new Stage(view);
        this.setTopLayout();
        this.setMidLayout();
        this.setBottomLayout();
        this.menu.addActor(this.topLayout);
        this.menu.addActor(this.midLayout);
        this.menu.addActor(this.bottomLayout);
        Gdx.input.setInputProcessor(menu);
    }

    private void setMidLayout() {
        this.midLayout = new Table();
        this.midLayout.setFillParent(true);
        this.midLayout.center();
        this.midLayout.debug();
        this.addElementsToMidLayout();
    }

    private void addElementsToMidLayout() {
    }

    private void setBottomLayout() {
        this.bottomLayout = new Table();
        this.bottomLayout.setFillParent(true);
        this.bottomLayout.bottom();
        this.bottomLayout.debug();
        this.addElementsToBottomLayout();
    }

    private void addElementsToBottomLayout() {
        this.addButtonsToBottomLayout();
        this.addLabelsToBottomLayout();
    }

    private void addLabelsToBottomLayout() {
        this.bottomLayout.row();
        this.bottomLayout.add(this.about).pad(20).padBottom(5).padTop(0).fillX();
        this.bottomLayout.add(this.settings).pad(20).padBottom(5).padTop(0).fillX();
        this.bottomLayout.add(this.play).pad(20).padBottom(5).padTop(0).fillX();
    }

    private void addButtonsToBottomLayout() {
        bottomLayout.add(this.btnAbout).pad(20).padBottom(5);
        bottomLayout.add(this.btnSettings).pad(20).padBottom(5);
        bottomLayout.add(this.btnNewGame).pad(20).padBottom(5);
    }

    private void addElementsToTopLayout() {
        this.addImagesToTopLayout();
        this.addLabelsToTopLayout();
        this.addButtonsToTopLayout();
    }

    private void addLabelsToTopLayout() {
        this.topLayout.add(this.title).pad(10).padLeft(20).padRight(20).fillX();
        this.topLayout.row();
        this.topLayout.add(this.subtitle).pad(10).padLeft(20).padRight(20).fillX();
        this.topLayout.row();
    }

    private void addImagesToTopLayout() {
        topLayout.add(this.imageTitle).pad(20).padBottom(5).uniformX().maxHeight(279f).maxWidth(710f);
        topLayout.row();
    }

    private void addButtonsToTopLayout() {

    }

    private void setTopLayout() {
        this.topLayout = new Table();
        this.topLayout.setFillParent(true);
        this.topLayout.top();
        this.topLayout.debug();
        this.addElementsToTopLayout();
    }

    private void setButtons(){
        this.btnNewGame = new ButtonToGame(videogame, uiButton);
        this.btnSettings = new ButtonToSettings(videogame, uiButton);
        this.btnAbout = new ButtonToAbout(videogame, uiButton);
    }

    private void addButton(ImageButton button) {
        menu.addActor(button);
    }

    private void setImages() {
        this.imageBackground = new Image(new Texture(Gdx.files.internal("Skins/Dark/darkBackground.png")));
        this.imageTitle = new Image(new Texture(Gdx.files.internal("Screens/Titles/TitleHead.png")));
    }

    private void setView(){
        camera = new OrthographicCamera();
        camera.position.set(Videogame.WIDTH /2, Videogame.HEIGHT /2, 0);
        camera.update();
        view = new StretchViewport(Videogame.WIDTH, Videogame.HEIGHT, camera);
        batch = new SpriteBatch();
    }

    private void setLabels(){
        title = new Label("Dunkirk-Defense", uiSkin, "default-bold");
        title.setAlignment(Align.center);
        title.setFontScale(1.5f);
        subtitle = new Label("SALVA HEROES", uiSkin, "default");
        subtitle.setAlignment(Align.center);
        subtitle.setFontScale(1f);
        settings = new Label("Settings", uiSkin, "default-bold");
        settings.setAlignment(Align.center);
        settings.setFontScale(1f);
        about = new Label("About", uiSkin, "default-bold");
        about.setAlignment(Align.center);
        about.setFontScale(1f);
        play = new Label("Play", uiSkin, "default-bold");
        play.setAlignment(Align.center);
        play.setFontScale(1f);
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
        imageBackground.draw(batch, 1);
        batch.end();
        menu.draw();
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
        menu.dispose();
    }
}
