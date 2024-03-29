//Alberto

package mx.itesm.seb.Outputs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

import mx.itesm.seb.Inputs.Buttons.ButtonToSubAbout;
import mx.itesm.seb.Inputs.Buttons.ButtonToSubPlay;
import mx.itesm.seb.Inputs.Buttons.ButtonToSubSettings;
import mx.itesm.seb.Outputs.Subscreens.SubscreenAbout;
import mx.itesm.seb.Outputs.Subscreens.SubscreenPlay;
import mx.itesm.seb.Outputs.Subscreens.SubscreenSettings;
import mx.itesm.seb.Outputs.Subscreens.SubscreenWarningDeleteData;
import mx.itesm.seb.Videogame;

public class ScreenMenu extends EnhancedScreen implements Screen {
    private final Videogame videogame;
    private Skin uiButton;
    private Skin uiSkin;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Image imageBackground;
    private Image imageTitle;
    private Image playerSubmarine;
    private Label title;
    private Label subtitle;
    private Label welcome;
    private Label highScore;
    private Label about;
    private Label settings;
    private Label play;
    private ButtonToSubPlay btnSubPlay;
    private ButtonToSubSettings btnSubSettings;
    private ButtonToSubAbout btnSubAbout;
    private Stage menu;
    private Table topLayout;
    private Table midLayout;
    private Table bottomLayout;
    private SubscreenAbout subscreenAbout;
    private SubscreenSettings subscreenSettings;
    private SubscreenPlay subscreenPlay;
    private SubscreenWarningDeleteData subscreenWarningDeleteData;
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
        if (this.screenState != subscreen.MAIN) {
            switch (this.screenState) {
                case MAIN:
                    if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                        Gdx.app.exit();
                    }
                case SUBSCREEN_1:
                    this.setSubscreenAbout();
                    if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                        screenState = subscreen.MAIN;
                    }
                    break;
                case SUBSCREEN_2:
                    this.setSubscreenSettings();
                    if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                        screenState = subscreen.MAIN;
                    }
                    break;
                case SUBSCREEN_3:
                    this.setSubscreenPlay();
                    if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                        screenState = subscreen.MAIN;
                    }
                    break;
                case SUBSCREEN_4:
                    this.setSubscreenWarningDeleteData();
                    if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                        screenState = subscreen.MAIN;
                    }
                    break;
            }
        }
        this.setStage();
        Gdx.input.setCatchBackKey(true);
    }

    public void setSkins() {
        this.uiButton = this.videogame.getAssetManager().get("Skins/Buttons/uiButton.json");
        if(this.videogame.getSettings().getDarkMode() == false) {
            Texture textureBackground = this.videogame.getAssetManager().get("Skins/Light/lightBackground.png");
            this.imageBackground = new Image(textureBackground);
            this.uiSkin = this.videogame.getAssetManager().get("Skins/Light/uiLightMode.json");
        } else {
            Texture textureBackground = this.videogame.getAssetManager().get("Skins/Dark/darkBackground.png");
            this.imageBackground = new Image(textureBackground);
            this.uiSkin = this.videogame.getAssetManager().get("Skins/Dark/uiDarkMode.json");
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
        if (this.screenState != subscreen.MAIN) {
            switch (this.screenState) {
                case SUBSCREEN_1:
                    this.menu.addActor(this.subscreenAbout.getWindow());
                    break;
                case SUBSCREEN_2:
                    this.menu.addActor(this.subscreenSettings.getWindow());
                    break;
                case SUBSCREEN_3:
                    this.menu.addActor(this.subscreenPlay.getWindow());
                    break;
                case SUBSCREEN_4:
                    this.menu.addActor(this.subscreenWarningDeleteData.getWindow());
                    break;
            }
        }
        Gdx.input.setInputProcessor(menu);
    }

    private void setMidLayout() {
        this.midLayout = new Table();
        this.midLayout.setFillParent(true);
        this.midLayout.center();
        //this.midLayout.debug();
        this.addElementsToMidLayout();
    }

    private void addElementsToMidLayout() {
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
        this.addLabelsToBottomLayout();
    }

    private void addLabelsToBottomLayout() {
        this.bottomLayout.row();
        this.bottomLayout.add(this.about).pad(20).padBottom(5).padTop(0).fillX();
        this.bottomLayout.add(this.settings).pad(20).padBottom(5).padTop(0).fillX();
        this.bottomLayout.add(this.play).pad(20).padBottom(5).padTop(0).fillX();
    }

    private void addButtonsToBottomLayout() {
        this.bottomLayout.add(this.btnSubAbout).pad(20).padBottom(5);
        this.bottomLayout.add(this.btnSubSettings).pad(20).padBottom(5);
        this.bottomLayout.add(this.btnSubPlay).pad(20).padBottom(5);
    }

    private void addElementsToTopLayout() {
        this.topLayout.add(this.imageTitle).pad(20).padBottom(5).uniformX().maxHeight(279f).maxWidth(710f).colspan(2);
        this.topLayout.row();
        this.topLayout.add(this.title).pad(10).padLeft(20).padRight(20).padBottom(0).colspan(2).fillX();
        this.topLayout.row();
        this.topLayout.add(this.subtitle).padTop(5).padLeft(20).padRight(20).padBottom(0).colspan(2).fillX();
        this.topLayout.row();
        this.topLayout.add(this.welcome).padTop(5).padLeft(20).padRight(5).padBottom(0).width(295);
        this.topLayout.add(this.highScore).padTop(5).padLeft(5).padRight(20).padBottom(0).width(375);
        this.topLayout.row();
        this.topLayout.add(this.playerSubmarine).padTop(20).padLeft(20).padRight(20).colspan(2).width(450).height(311);
    }

    private void setTopLayout() {
        this.topLayout = new Table();
        this.topLayout.setFillParent(true);
        this.topLayout.top();
        //this.topLayout.debug();
        this.addElementsToTopLayout();
    }

    private void setButtons(){
        this.btnSubPlay = new ButtonToSubPlay(videogame, this, uiButton);
        this.btnSubSettings = new ButtonToSubSettings(videogame, this, uiButton);
        this.btnSubAbout = new ButtonToSubAbout(videogame, this, uiButton);
    }

    private void addButton(ImageButton button) {
        menu.addActor(button);
    }

    private void setImages() {
        Texture textureTitle = this.videogame.getAssetManager().get("Screens/Titles/TitleHead.png");
        this.imageTitle = new Image(textureTitle);
        Texture texturePlayerSubmarine = this.videogame.getAssetManager().get("Entities/Player/AAPlayer.png");
        this.playerSubmarine = new Image(texturePlayerSubmarine);
    }

    private void setView(){
        this.camera = new OrthographicCamera();
        this.camera.position.set(Videogame.WIDTH /2, Videogame.HEIGHT /2, 0);
        this.camera.update();
        this.view = new StretchViewport(Videogame.WIDTH, Videogame.HEIGHT, camera);
        this.batch = new SpriteBatch();
    }

    private void setLabels(){
        this.title = new Label("Dunkirk-Defense", uiSkin, "default-title");
        this.title.setAlignment(Align.center);
        this.subtitle = new Label("SAVE HEROES", uiSkin, "default-subtitle");
        this.subtitle.setFontScale(.5f);
        this.subtitle.setAlignment(Align.center);
        this.welcome = new Label("Hi, " + this.videogame.getSettings().getName() + "!", this.uiSkin, "default-bold");
        this.welcome.setAlignment(Align.center);
        this.welcome.setEllipsis(true);
        this.highScore = new Label("High Score: " + this.videogame.getSettings().getHighScore() + "!", this.uiSkin, "default-bold");
        this.highScore.setAlignment(Align.center);
        this.highScore.setEllipsis(true);
        this.settings = new Label("Settings", uiSkin, "default-bold");
        this.settings.setAlignment(Align.center);
        this.about = new Label("About", uiSkin, "default-bold");
        this.about.setAlignment(Align.center);
        this.play = new Label("Play", uiSkin, "default-bold");
        this.play.setAlignment(Align.center);
    }

    @Override
    public void render(float delta) {
        this.eraseScreen();
        this.drawElements();
        if (this.screenState != subscreen.MAIN) {
            switch (this.screenState) {
                case MAIN:
                    if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                        Gdx.app.exit();
                    }
                    break;
                case SUBSCREEN_1:
                    if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                        screenState = subscreen.MAIN;
                        this.updateScreen();
                    }
                    break;
                case SUBSCREEN_2:
                    if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                        screenState = subscreen.MAIN;
                        this.updateScreen();
                    }
                    break;
                case SUBSCREEN_3:
                    if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                        screenState = subscreen.MAIN;
                        this.updateScreen();
                    }
                    break;
                case SUBSCREEN_4:
                    if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
                        screenState = subscreen.MAIN;
                        this.updateScreen();
                    }
                    break;
            }
        }

    }

    private void eraseScreen() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawElements(){
        this.batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
        this.imageBackground.draw(this.batch, 1);
        if (this.screenState != subscreen.MAIN) {
            switch (this.screenState) {
                case SUBSCREEN_1:
                    this.subscreenAbout.getWindow().setY(0);
                    this.subscreenAbout.draw(this.batch, 1f);
                    break;
                case SUBSCREEN_2:
                    this.subscreenSettings.getWindow().setY(0);
                    this.subscreenSettings.draw(this.batch, 1f);
                    break;
                case SUBSCREEN_3:
                    this.subscreenPlay.getWindow().setY(0);
                    this.subscreenPlay.draw(this.batch, 1f);
                    break;
                case SUBSCREEN_4:
                    this.subscreenWarningDeleteData.getWindow().setY(0);
                    this.subscreenWarningDeleteData.draw(this.batch, 1f);
                    break;
            }
        }
        this.batch.end();
        this.menu.draw();
    }

    @Override
    public void updateScreen(){
        this.show();
    }

    private void setSubscreenAbout(){
        this.subscreenAbout = new SubscreenAbout(this.videogame, this.uiSkin, this.uiButton);
    }

    private void setSubscreenSettings(){
        this.subscreenSettings = new SubscreenSettings(this.videogame, this.uiSkin, this.uiButton);
    }

    private void setSubscreenPlay(){
        this.subscreenPlay = new SubscreenPlay(this.videogame, this.uiSkin, this.uiButton);
    }

    private void setSubscreenWarningDeleteData(){
        this.subscreenWarningDeleteData = new SubscreenWarningDeleteData(this.videogame, this.uiSkin, this.uiButton);
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
        uiSkin.dispose();
        uiButton.dispose();
        videogame.getAssetManager().dispose();
    }
}
