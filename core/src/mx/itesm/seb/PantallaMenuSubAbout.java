package mx.itesm.seb;

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

public class PantallaMenuSubAbout implements Screen {
    //Variables iniciales
    private final Juego juego;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;

    //Referencias de texturas
    private Texture texturaFondo;
    private Texture texturaTitulo;

    //Referencias de texto
    private Text titleHead;
    private Text subtitleHead;

    private Texture texturaTest;

    //Referencias de botones
    private Button btnNewGame;
    private Button btnSettings;
    private Button btnAbout;

    //Escena: Menú
    private Stage escenaMenu;

    public PantallaMenuSubAbout(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearTexto();
        crearMenu();
    }

    private void crearMenu() {
        escenaMenu = new Stage(view);
        this.configurarBotones();
        this.agregarBotones();
        Gdx.input.setInputProcessor(escenaMenu);
    }

    private void agregarBotones() {
        escenaMenu.addActor(btnNewGame.getButton());
        escenaMenu.addActor(btnSettings.getButton());
        escenaMenu.addActor(btnAbout.getButton());
    }

    private void configurarBotones(){
        this.btnNewGame = new Button(juego, Button.ToScreen.GAME, "D-DEF/buttonNewGame.png", "D-DEF/buttonNewGamePressed.png", 5*Juego.ANCHO/6, Juego.ALTO/10);
        this.btnSettings = new Button(juego, Button.ToScreen.SETTINGS, "D-DEF/buttonSettings.png", "D-DEF/buttonSettings.png", 3*Juego.ANCHO/6, Juego.ALTO/10);
        this.btnAbout = new Button(juego, Button.ToScreen.ABOUT, "D-DEF/buttonAbout.png", "D-DEF/buttonAboutPressed.png", 1*Juego.ANCHO/6, Juego.ALTO/10);
    }

    private void agregarBoton(ImageButton button) {
        escenaMenu.addActor(button);
    }

    private void cargarTexturas() {
        texturaFondo = new Texture("D-DEF/oceanBackgroundVertical.png");
        texturaTitulo = new Texture("D-DEF/TitleHeadVertical.png");
        texturaTest = new Texture("D-DEF/buttonNewGame.png");
    }

    private void configurarVista(){
        camera = new OrthographicCamera();
        camera.position.set(Juego.ANCHO/2, Juego.ALTO/2, 0);
        camera.update();
        view = new StretchViewport(Juego.ANCHO, Juego.ALTO, camera);
        batch = new SpriteBatch();
    }

    private void crearTexto(){
        titleHead = new Text("Dunkirk-Defense");
        subtitleHead = new Text("¡SALVA HÉROES!");
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Batch escalado a la vista y la camara
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texturaFondo, 0, 0);
        batch.draw(texturaTitulo, Juego.ANCHO/2 - texturaTitulo.getWidth()/2, Juego.ALTO - texturaTitulo.getHeight()- texturaTitulo.getHeight()/9);
        titleHead.draw(batch,juego.ANCHO/2 - titleHead.getWidth()/2, juego.ALTO/2 + juego.ALTO/4);
        subtitleHead.draw(batch, juego.ANCHO/2 - subtitleHead.getWidth()/2, juego.ALTO/2 + juego.ALTO/4 - 50);

        batch.draw(texturaTest, Juego.ANCHO/2 - texturaTest.getWidth()/2, Juego.ALTO/2 - texturaTest.getHeight()/2);

        batch.end();

        escenaMenu.draw();
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
        texturaFondo.dispose();
    }
}
