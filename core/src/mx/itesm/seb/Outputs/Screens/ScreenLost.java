//Eduardo

package mx.itesm.seb.Outputs.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import mx.itesm.seb.Outputs.Texts.Text;
import mx.itesm.seb.Videogame;

class ScreenLost implements Screen {
    private final Videogame videogame;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    private Texture textureBackground;
    private Table mainLayout;
    private Label title;
    private Label content;
    private Stage lost;
    private Skin uiButton;
    private Skin uiSkin;
    private Music backgroundMusic;
    public int destroyed;
    private Text score_txt;


    public ScreenLost(int destroyed, Videogame videogame) {
        this.videogame = videogame;
        this.destroyed = destroyed;
    }

    @Override
    public void show() {
        this.setSkins();
        this.setView();
        this.setLabels();
        this.setTextures();
        this.setStage();
        score_txt = new Text("Final Score: " + destroyed);
        this.setMusic();
        this.videogame.getSettings().setHighScore(destroyed);
        Gdx.input.setCatchBackKey(true);
    }

    private void setMusic(){
        AssetManager manager = videogame.getAssetManager();
        manager.load("Music/Lost.mp3", Music.class);
        manager.finishLoading();
        backgroundMusic = manager.get("Music/Lost.mp3");
        backgroundMusic.setLooping(false);
        backgroundMusic.setVolume(80);
        backgroundMusic.play();
    }
    private void drawElements() {
        //Batch escalado a la vista y la camara
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(textureBackground, 1,1);
        batch.draw(textureBackground, 0,0);

        score_txt.setMessage("You lost :( \n"+"Score: " + Integer.toString(destroyed));
        score_txt.draw(batch, Videogame.WIDTH/2 - score_txt.getWidth()/2, Videogame.HEIGHT/2 - score_txt.getHeight());

        batch.end();
        lost.draw();

    }

    private void setLabels() {
        /*title = new Label("Hola, Bienvenido a D-Def", uiModeSkinSubscreens, "title");
        title.setAlignment(Align.center);
        title.setFontScale(1.5f);
        title.setWrap(true);
        title.setWidth(700);
        content = new Label("Inspirado en los eventos que sucedieron en la bahia de Dunquerque en 1943 durante la segunda guerra mundial. Tu mision es derribar a los aviones enemigos y sobrevivir el mayor tiempo posible. Pero cuidado porque tu submarino esta dañado y la energía con la que cuentas es limitada, tanto disparar como moverse hará que disminuya.", uiModeSkinSubscreens, "content-big");
        content.setAlignment(Align.center);
        content.setFontScale(1.3f);
        content.setWrap(true);
        content.setWidth(700);*/
    }

    private void setSkins() {
        this.uiButton = new Skin(Gdx.files.internal("Skins/Buttons/uiButton.json"),
                new TextureAtlas(Gdx.files.internal("Skins/Buttons/uiButton.atlas")));
        this.uiSkin = new Skin(Gdx.files.internal("Skins/Light/uiLightMode.json"),
                new TextureAtlas(Gdx.files.internal("Skins/Light/uiLightMode.atlas")));
    }

    private void setStage() {
        lost = new Stage(view);
        Gdx.input.setInputProcessor(lost);
        this.setMainLayout();
        ImageButton btnBack = configurarBotonBack();
        addButtons(btnBack);
        lost.addActor(this.mainLayout);
    }

    private void setMainLayout() {
        this.mainLayout = new Table();
        this.mainLayout.setFillParent(true);
        this.mainLayout.top();
        this.mainLayout.pad(20);
        this.addElementsToMainLayout();
    }

    private void addElementsToMainLayout() {
        this.addLabelsToMainLayout();
    }

    private void addLabelsToMainLayout() {
        this.mainLayout.add(this.title).pad(10).fillX().width(700);
        this.mainLayout.row();
        this.mainLayout.add(this.content).pad(5).fillX().width(700);
    }

    private void addButtons(ImageButton btnBack) {
        lost.addActor(btnBack);
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
                videogame.setScreen(new ScreenMenu(videogame));

            }
        });
        return btnBack;
    }

    private void setTextures() {
        textureBackground = new Texture("Screens/Backgrounds/oceanBackgroundPlayVertical.png");
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
        this.eraseScreen();
        this.drawElements();
        checkBackButton();
    }

    private void checkBackButton() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            videogame.setScreen(new ScreenMenu(videogame));
        }
    }


    private void eraseScreen() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.drawElements();
        lost.draw();
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
}
