package mx.itesm.seb;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class ProcesadorInput implements InputProcessor{
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
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 v = new Vector3(screenX, screenY, 0);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
