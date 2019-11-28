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
import mx.itesm.seb.Inputs.Buttons.ButtonToSurvive;
import mx.itesm.seb.Inputs.Buttons.ButtonToSettings;
import mx.itesm.seb.Inputs.Buttons.ButtonToSubAbout;
import mx.itesm.seb.Outputs.Subscreens.SubscreenAbout;
import mx.itesm.seb.Videogame;

public class ScreenAboutTheDevs extends EnhancedScreen implements Screen {
    private final Videogame videogame;
    private Skin uiButton;
    private Skin uiSkin;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Image imageBackground;
    private Image imageTecDeMonterrey;
    private Image imageAlbertoLopez;
    private Image imageEduardoRoberto;
    private Image imageRaulOrtiz;
    private Image imageSebastianMorales;
    private Label title;
    private Label university;
    private Label teamMembers;
    private Label tecDeMonterrey;
    private Label albertoLopez;
    private Label albertoLopezContent;
    private Label eduardoRoberto;
    private Label eduardoRobertoContent;
    private Label raulOrtiz;
    private Label raulOrtizContent;
    private Label sebastianMorales;
    private Label sebastianMoralesContent;
    private ButtonToMenu btnToMenu;
    private Stage aboutTheDevs;
    private Table topLayout;
    private Table bottomLayout;
    private Music backgroundMusic;

    public ScreenAboutTheDevs(Videogame videogame) {
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
        this.aboutTheDevs = new Stage(view);
        this.setTopLayout();
        this.setBottomLayout();
        this.aboutTheDevs.addActor(this.topLayout);
        this.aboutTheDevs.addActor(this.bottomLayout);
        Gdx.input.setInputProcessor(aboutTheDevs);
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
        bottomLayout.add(this.btnToMenu).width(500).height(140).pad(20);
    }

    private void addElementsToTopLayout() {
        this.topLayout.add(this.title).padLeft(20).padRight(20).pad(5).colspan(2).fillX();
        this.topLayout.row();
        this.topLayout.add(this.university).padLeft(20).pad(5).colspan(2).fillX();
        this.topLayout.row();
        this.topLayout.add(this.imageTecDeMonterrey).padLeft(20).pad(5).height(150).width(150);
        this.topLayout.add(this.tecDeMonterrey).padRight(20).pad(5).height(150).fillX();
        this.topLayout.row();
        this.topLayout.add(this.teamMembers).padLeft(20).pad(5).colspan(2).fillX();
        this.topLayout.row();
        this.topLayout.add(this.imageAlbertoLopez).padLeft(20).pad(5).height(150).width(150);
        this.topLayout.add(this.albertoLopez).padRight(20).pad(5).height(150).fillX();
        //this.topLayout.row();
        //this.topLayout.add(this.albertoLopezContent).colspan(2).fillX();
        this.topLayout.row();
        this.topLayout.add(this.imageEduardoRoberto).padLeft(20).pad(5).height(150).width(150);
        this.topLayout.add(this.eduardoRoberto).padRight(20).pad(5).height(150).fillX();
        //this.topLayout.row();
        //this.topLayout.add(this.eduardoRobertoContent).colspan(2).fillX();
        this.topLayout.row();
        this.topLayout.add(this.imageRaulOrtiz).padLeft(20).pad(5).height(150).width(150);
        this.topLayout.add(this.raulOrtiz).padRight(20).pad(5).height(150).fillX();
        //this.topLayout.row();
        //this.topLayout.add(this.raulOrtizContent).colspan(2).fillX();
        this.topLayout.row();
        this.topLayout.add(this.imageSebastianMorales).padLeft(20).pad(5).height(150).width(150);
        this.topLayout.add(this.sebastianMorales).padRight(20).pad(5).height(150).fillX();
        //this.topLayout.row();
        //this.topLayout.add(this.sebastianMoralesContent).colspan(2).fillX();
        this.topLayout.row();
    }

    private void setTopLayout() {
        this.topLayout = new Table();
        this.topLayout.setFillParent(true);
        this.topLayout.top();
        //this.topLayout.debug();
        this.addElementsToTopLayout();
    }

    private void setButtons(){
        this.btnToMenu = new ButtonToMenu(this.videogame, this.uiButton);
    }

    private void addButton(ImageButton button) {
        aboutTheDevs.addActor(button);
    }

    private void setImages() {
        this.imageTecDeMonterrey = new Image(new Texture(Gdx.files.internal("Icons/tecDeMonterrey.png")));
        this.imageAlbertoLopez = new Image(new Texture(Gdx.files.internal("Photos/alberto.png")));
        this.imageEduardoRoberto = new Image(new Texture(Gdx.files.internal("Photos/eduardo.png")));
        this.imageRaulOrtiz  = new Image(new Texture(Gdx.files.internal("Photos/raul.png")));
        this.imageSebastianMorales = new Image(new Texture(Gdx.files.internal("Photos/sebastian.png")));
    }

    private void setView(){
        camera = new OrthographicCamera();
        camera.position.set(Videogame.WIDTH /2, Videogame.HEIGHT /2, 0);
        camera.update();
        view = new StretchViewport(Videogame.WIDTH, Videogame.HEIGHT, camera);
        batch = new SpriteBatch();
    }

    private void setLabels(){
        title = new Label("About the Developers", uiSkin, "default-title");
        title.setAlignment(Align.center);
        title.setFontScale(.75f);

        university = new Label("University", uiSkin, "default-subtitle");
        university.setFontScale(.5f);
        university.setAlignment(Align.center);

        tecDeMonterrey = new Label("Tecnológico de Monterrey, Campus Estado de México.", uiSkin, "default-bold");
        tecDeMonterrey.setAlignment(Align.left);
        tecDeMonterrey.setWrap(true);

        teamMembers = new Label("Team Members", uiSkin, "default-subtitle");
        teamMembers.setFontScale(.5f);
        teamMembers.setAlignment(Align.center);

        albertoLopez = new Label("Alberto López Reyes", uiSkin, "default-bold");
        albertoLopez.setFontScale(1.2f);
        albertoLopez.setAlignment(Align.center);

        //albertoLopezContent = new Label("A01745811@itesm.mx", uiSkin, "default");
        //albertoLopezContent.setAlignment(Align.left);
        //albertoLopezContent.setFontScale(.5f);

        eduardoRoberto = new Label("Eduardo Roberto Müller Romero", uiSkin, "default-bold");
        eduardoRoberto.setFontScale(1.2f);
        eduardoRoberto.setAlignment(Align.center);
        eduardoRoberto.setWrap(true);

        //eduardoRobertoContent = new Label("A01745219@itesm.mx", uiSkin, "default");
        //eduardoRobertoContent.setAlignment(Align.left);
        //eduardoRobertoContent.setFontScale(.5f);

        raulOrtiz = new Label("Raul Ortiz Mateos", uiSkin, "default-bold");
        raulOrtiz.setFontScale(1.2f);
        raulOrtiz.setAlignment(Align.center);

        //raulOrtizContent = new Label("A01375407@itesm.mx", uiSkin, "default");
        //raulOrtizContent.setAlignment(Align.left);
        //raulOrtizContent.setFontScale(.5f);

        sebastianMorales = new Label("Sebastián Morales Martín", uiSkin, "default-bold");
        sebastianMorales.setFontScale(1.2f);
        sebastianMorales.setAlignment(Align.center);

        //sebastianMoralesContent = new Label("A01375407@itesm.mx", uiSkin, "default");
        //sebastianMoralesContent.setAlignment(Align.left);
        //sebastianMoralesContent.setFontScale(.5f);
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
        aboutTheDevs.draw();
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
        aboutTheDevs.dispose();
        uiSkin.dispose();
        uiButton.dispose();
    }
}
