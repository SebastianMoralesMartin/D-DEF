package mx.itesm.seb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//Raul
//Sebastian
class PantallaDemo implements Screen {
    //Variables iniciales
    private final Juego juego;
    private OrthographicCamera camera;
    private Viewport view;
    private SpriteBatch batch;
    //... para enemigos
    private Array<Enemigo> enemigos;
    private int DX = +7;
    private int pasos = 0;
    private float timerPaso = 0f;
    private float MAX_PASO = 0.055f;
    //... para bote
    private Bote bote;
    private Movimiento estadoBote = Movimiento.QUIETO;

    //Referencias de texturas
    private Texture texturaFondo;

    //Escena: HUD
    private Stage escenaHUD;

    //Marcador de ENERGIA en pantalla

    private int energy = 999999;
    private Text text;

    public PantallaDemo(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearHUD();
        crearBote();
        crearEnemigos();

        //PROTOTYPE: Sets listener and input processor
        //Gdx.input.setInputProcessor(new ProcesadorEntrada());

        //PROTOTYPE: Creates an object that draws text outputs onto a screen
        text = new Text("Energy");
    }

    private void crearBote() {
        Texture texturaNave = new Texture("D-DEF/AAPlayer.png");
        bote = new Bote(texturaNave, Juego.ANCHO/2, 3*Juego.ALTO/7);
    }

    private void crearEnemigos() {
        Texture texturaEnemigoArriba = new Texture("D-DEF/enemLuftRight.png");
        Texture texturaEnemigoAbajo = new Texture("D-DEF/enemLuft.png");
        enemigos = new Array<>(11 * 5);
        for(int renglon = 0; renglon < 3; renglon++){
            for(int columna=0; columna <4; columna++){
                Enemigo enemigo = new Enemigo(texturaEnemigoArriba, texturaEnemigoAbajo, 40 + columna * 250, 5*Juego.ALTO/7 + renglon * 120);
                enemigos.add(enemigo);
            }
        }
    }

    private void crearHUD() {
        escenaHUD = new Stage(view);
        ImageButton btnBack = configurarBotonBack();
        ImageButton btnDer = configurarBotonDerecha();
        ImageButton btnIzq = configurarBotonIzquierda();
        agregarBotones(btnBack, btnDer, btnIzq);
        Gdx.input.setInputProcessor(escenaHUD);
    }

    private void agregarBotones(ImageButton btnBack, ImageButton btnDer, ImageButton btnIzq) {
        escenaHUD.addActor(btnBack);
        escenaHUD.addActor(btnDer);
        escenaHUD.addActor(btnIzq);
    }

    private ImageButton configurarBotonIzquierda() {
        //Botón Izquierda
        TextureRegionDrawable trdIzq = new TextureRegionDrawable(new TextureRegion(new Texture("space/flechaIzquierda.png")));
        ImageButton btnIzq = new ImageButton(trdIzq);
        btnIzq.setPosition(0, 0);
        //Evento de Botón Izquierda
        btnIzq.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                estadoBote = Movimiento.IZQUIERDA;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                estadoBote = Movimiento.QUIETO;
            }
        });
        return btnIzq;
    }

    private ImageButton configurarBotonDerecha() {
        //Botón Derecha
        TextureRegionDrawable trdDer = new TextureRegionDrawable(new TextureRegion(new Texture("space/flechaDerecha.png")));
        ImageButton btnDer = new ImageButton(trdDer);
        btnDer.setPosition(btnDer.getWidth() + 10, 0);
        //Evento de Botón Derecha
        btnDer.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                estadoBote = Movimiento.DERECHA;
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                estadoBote = Movimiento.QUIETO;
            }
        });
        return btnDer;
    }

    private ImageButton configurarBotonBack() {
        //Botón Back
        TextureRegionDrawable trdBack = new TextureRegionDrawable(new TextureRegion(new Texture("back.png")));
        TextureRegionDrawable trdBackPressed = new TextureRegionDrawable(new TextureRegion(new Texture("backPressed.png")));
        ImageButton btnBack = new ImageButton(trdBack, trdBackPressed);
        btnBack.setPosition(0, Juego.ALTO - btnBack.getHeight());
        //Evento de Botón Back
        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaMenu(juego));
            }
        });
        return btnBack;
    }

    private void cargarTexturas() {
        texturaFondo = new Texture("D-DEF/oceanBackgroundPlayVertical.png");
    }

    private void configurarVista(){
        configurarCamara();
        view = new StretchViewport(Juego.ANCHO, Juego.ALTO, camera);
        batch = new SpriteBatch();
    }

    private void configurarCamara() {
        camera = new OrthographicCamera();
        camera.position.set(Juego.ANCHO/2, Juego.ALTO/2, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        //Dibujar enemigos
        actualizarEnemigos(delta);
        //Dibujar bote
        actualizarBote();
        //Borrar pantalla
        borrarPantalla();
    }

    private void actualizarBote() {
        switch(estadoBote){
            case DERECHA:
                bote.mover(3, 0);
                energy -=1;
                break;
            case IZQUIERDA:
                bote.mover(-3, 0);
                energy-=1;
                break;
        }
    }

    private void actualizarEnemigos(float delta) {
        timerPaso += delta;
        if (timerPaso>MAX_PASO){
            timerPaso = 0;
            for(Enemigo enemigo:enemigos){
                enemigo.mover(DX,0);
                //enemigo.cambiarImagen();
            }
            pasos++;
            if(pasos>= 40){
                pasos = 0;
                DX = -DX;
                for(Enemigo enemigo:enemigos){
                    enemigo.mover(0, -40);
                    enemigo.cambiarImagen();
                }
            }
        }

    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        dibujarElementos();
        escenaHUD.draw();
    }

    private void dibujarElementos() {
        //Batch escalado a la vista y la camara
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        dibujarFondo();
        dibujarEnemigos();
        dibujarBote();
        text.setMessage("Energy: " + Integer.toString(energy));
        text.draw(batch, Juego.ANCHO/2, Juego.ALTO - 60);
        batch.end();
    }

    private void dibujarBote() {
        bote.render(batch);
    }

    private void dibujarEnemigos() {
        //Dibujo de enemigos
        for(Enemigo enemigo : enemigos){
            enemigo.render(batch);
        }
    }

    private void dibujarFondo() {
        //Dibujo de Fondo
        batch.draw(texturaFondo, 0, 0);
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
        texturaFondo.dispose();
    }

    private enum Movimiento {
        QUIETO,
        DERECHA,
        IZQUIERDA
    }

    class ProcesadorEntrada implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            camera.unproject(v);
            bote.getSprite().setX(v.x);
            return true;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
