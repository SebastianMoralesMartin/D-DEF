package mx.itesm.seb.Outputs.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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
    private Skin buttonSkins;
    private Skin uiModeSkinSubscreens;
    private Skin uiModeSkinDialog;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Image imageBackground;
    private Image imageTitle;
    private Label titleHead;
    private Label subtitleHead;
    private ButtonToGame btnNewGame;
    private ButtonToSettings btnSettings;
    private ButtonToAbout btnAbout;
    private Stage menu;
    private Table mainLayout;
    private Table secondaryLayout;
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
            this.buttonSkins = new Skin(Gdx.files.internal("Skins/Buttons/uiButton.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Buttons/buttonTextureAtlas.atlas")));
            this.uiModeSkinDialog = new Skin(Gdx.files.internal("Skins/Light/Dialog/uiLightDialog.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Light/Dialog/uiDialog.atlas")));
            this.uiModeSkinSubscreens = new Skin(Gdx.files.internal("Skins/Light/Subscreen/uiLightSubmenu.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Light/Subscreen/uiSubmenu.atlas")));
        } else {
            this.buttonSkins = new Skin(Gdx.files.internal("Skins/Buttons/uiButton.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Buttons/buttonTextureAtlas.atlas")));
            this.uiModeSkinDialog = new Skin(Gdx.files.internal("Skins/Dark/Dialog/uiDarkDialog.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Dark/Dialog/uiDialogDark.atlas")));
            this.uiModeSkinSubscreens = new Skin(Gdx.files.internal("Skins/Dark/Subscreen/uiSubmenuDark.json"),
                    new TextureAtlas(Gdx.files.internal("Skins/Dark/Subscreen/uiSubmenuDark.atlas")));
        }
    }

    /*private void setMusic(){
        backgroundMusic = videogame.getBackgroundMusic();
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(50);
        backgroundMusic.play();
    }*/

    private void setStage() {
        menu = new Stage(view);
        Gdx.input.setInputProcessor(menu);
        this.setMainLayout();
        this.setSecondaryLayout();
        menu.addActor(this.mainLayout);
    }

    private void setSecondaryLayout() {
        this.secondaryLayout = new Table();
        this.secondaryLayout.setFillParent(true);
        this.secondaryLayout.top();
        this.secondaryLayout.pad(20);
        this.addElementsToSecondaryLayout();
    }

    private void addElementsToSecondaryLayout() {
        this.addButtonsToSecondaryLayout();
    }

    private void addButtonsToSecondaryLayout() {
        this.secondaryLayout.add();
    }

    private void addElementsToMainLayout() {
        this.addImagesToMainLayout();
        this.addLabelsToMainLayout();
        this.addButtonsMainLayout();
    }

    private void addLabelsToMainLayout() {
        this.mainLayout.add(this.titleHead).colspan(3).pad(10).fillX();
        this.mainLayout.row();
        this.mainLayout.add(this.subtitleHead).colspan(3).pad(5).fillX();
        this.mainLayout.row();
    }

    private void addImagesToMainLayout() {
        mainLayout.add(this.imageTitle).colspan(3).pad(20).uniformX().maxHeight(279f).maxWidth(710f);
        mainLayout.row();
    }

    private void setMainLayout() {
        this.mainLayout = new Table();
        this.mainLayout.setFillParent(true);
        this.mainLayout.top();
        this.mainLayout.pad(20);
        this.addElementsToMainLayout();
    }

    private void addButtonsMainLayout() {
        mainLayout.add(this.btnAbout).pad(20);
        mainLayout.add(this.btnSettings).pad(20);
        mainLayout.add(this.btnNewGame).pad(20);
    }

    private void setButtons(){
        this.btnNewGame = new ButtonToGame(videogame, buttonSkins);
        this.btnSettings = new ButtonToSettings(videogame, buttonSkins);
        this.btnAbout = new ButtonToAbout(videogame, buttonSkins);
    }

    private void addButton(ImageButton button) {
        menu.addActor(button);
    }

    private void setImages() {
        this.imageBackground = new Image(new Texture(Gdx.files.internal("Screens/Backgrounds/oceanBackgroundVertical.png")));
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
        titleHead = new Label("Dunkirk-Defense", uiModeSkinSubscreens, "title");
        titleHead.setAlignment(Align.center);
        titleHead.setFontScale(2);
        subtitleHead = new Label("SALVA HEROES", uiModeSkinSubscreens, "title");
        subtitleHead.setAlignment(Align.center);
        subtitleHead.setFontScale(1.5f);
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
    }
}
