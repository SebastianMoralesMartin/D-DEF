package mx.itesm.seb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class PantallaMenu implements Screen {
    //Variables iniciales
    private final Juego juego;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;

    //Referencias de texturas
    private Texture texturaFondo;
    private Texture texturaTitulo;

    //Escena: Menú
    private Stage escenaMenu;

    public PantallaMenu(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearMenu();
    }

    private void crearMenu() {
        escenaMenu = new Stage(view);
        ImageButton btnSDeploy = configurarBotonDeploy();
        agregarBotones(btnSDeploy);
        Gdx.input.setInputProcessor(escenaMenu);
    }

    private void agregarBotones(ImageButton btnSDeploy) {
        escenaMenu.addActor(btnSDeploy);
    }

    private ImageButton configurarBotonDeploy() {
        //Botón Deploy
        TextureRegionDrawable trdDeploy = new TextureRegionDrawable(new TextureRegion(new Texture("D-DEF/btnDeploy.png")));
        TextureRegionDrawable trdDeployPressed = new TextureRegionDrawable(new TextureRegion(new Texture("D-DEF/btnDeployPressed.png")));
        ImageButton btnSDeploy = new ImageButton(trdDeploy, trdDeployPressed);
        btnSDeploy.setPosition(Juego.ANCHO/2 - btnSDeploy.getWidth()/2, Juego.ALTO/6);
        //Evento de Botón Deploy
        btnSDeploy.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaDemo(juego));
            }
        });
        return btnSDeploy;
    }

    private void cargarTexturas() {
        texturaFondo = new Texture("D-DEF/fondoMarino.png");
        texturaTitulo = new Texture("D-DEF/TitleHead.png");
    }

    private void configurarVista(){
        camera = new OrthographicCamera();
        camera.position.set(Juego.ANCHO/2, Juego.ALTO/2, 0);
        camera.update();
        view = new StretchViewport(Juego.ANCHO, Juego.ALTO, camera);
        batch = new SpriteBatch();
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
        batch.draw(texturaTitulo, Juego.ANCHO/2 - texturaTitulo.getWidth()/2, Juego.ALTO - texturaTitulo.getHeight()- texturaTitulo.getHeight()/4);
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
