package mx.itesm.seb.Outputs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import mx.itesm.seb.Inputs.Buttons.ButtonToScreens;
import mx.itesm.seb.Outputs.Texts.Text;
import mx.itesm.seb.Videogame;

public class ScreenSubMenuAbout implements Screen {
    private final Videogame videogame;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Texture textureBackground;
    private Texture textureTitle;
    //private Texture testTexture;
    private Text titleHead;
    private Text subtitleHead;
    private ButtonToScreens btnNewGame;
    private ButtonToScreens btnSettings;
    private ButtonToScreens btnAbout;
    private ButtonToScreens btnSubMenuAbout;
    private Stage submenu;

    public ScreenSubMenuAbout(Videogame videogame) {
        this.videogame = videogame;
    }

    @Override
    public void show() {
        setView();
        setTextures();
        setTexts();
        setStage();
    }

    private void setStage() {
        submenu = new Stage(view);
        this.setButtons();
        this.addButtons();
        Gdx.input.setInputProcessor(submenu);
    }

    private void addButtons() {
        submenu.addActor(btnNewGame.getButton());
        submenu.addActor(btnSettings.getButton());
        submenu.addActor(btnAbout.getButton());
        submenu.addActor(btnSubMenuAbout.getButton());
    }

    private void setButtons(){
        this.btnNewGame = new ButtonToScreens(videogame, ButtonToScreens.ToScreen.GAME, "Buttons/buttonNewGame.png", "Buttons/buttonNewGamePressed.png", 5* Videogame.WIDTH /6, Videogame.HEIGHT /10);
        this.btnSettings = new ButtonToScreens(videogame, ButtonToScreens.ToScreen.SETTINGS, "Buttons/buttonSettings.png", "Buttons/buttonSettings.png", 3* Videogame.WIDTH /6, Videogame.HEIGHT /10);
        this.btnAbout = new ButtonToScreens(videogame, ButtonToScreens.ToScreen.SUBMENU, "Buttons/buttonAbout.png", "Buttons/buttonAboutPressed.png", 1* Videogame.WIDTH /6, Videogame.HEIGHT /10);
        this.btnSubMenuAbout = new ButtonToScreens(videogame, ButtonToScreens.ToScreen.ABOUT, "Buttons/buttonBase.png", "Buttons/buttonBasePressed.png", Videogame.WIDTH /2, Videogame.HEIGHT /2);
    }

    private void addButton(ImageButton button) {
        submenu.addActor(button);
    }

    private void setTextures() {
        textureBackground = new Texture("Screens/Backgrounds/oceanBackgroundVertical.png");
        textureTitle = new Texture("Screens/Titles/TitleHeadVertical.png");
        //testTexture = new Texture("D-DEF/buttonNewGamePressed.png");
    }

    private void setView(){
        camera = new OrthographicCamera();
        camera.position.set(Videogame.WIDTH /2, Videogame.HEIGHT /2, 0);
        camera.update();
        view = new StretchViewport(Videogame.WIDTH, Videogame.HEIGHT, camera);
        batch = new SpriteBatch();
    }

    private void setTexts(){
        titleHead = new Text("Dunkirk-Defense");
        subtitleHead = new Text("¡SALVA HÉROES!");
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
        //Batch escalado a la vista y la camara
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(textureBackground, 0, 0);
        batch.draw(textureTitle, Videogame.WIDTH /2 - textureTitle.getWidth()/2, Videogame.HEIGHT - textureTitle.getHeight()- textureTitle.getHeight()/9);
        titleHead.draw(batch, videogame.WIDTH /2 - titleHead.getWidth()/2, videogame.HEIGHT /2 + videogame.HEIGHT /4);
        subtitleHead.draw(batch, videogame.WIDTH /2 - subtitleHead.getWidth()/2, videogame.HEIGHT /2 + videogame.HEIGHT /4 - 50);

        //batch.draw(testTexture, Videogame.WIDTH/2 - testTexture.getWidth()/2, Videogame.HEIGHT/2 - testTexture.getHeight()/2);
        batch.end();

        submenu.draw();
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
        textureBackground.dispose();
    }
}
