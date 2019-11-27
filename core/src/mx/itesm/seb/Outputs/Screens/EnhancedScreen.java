package mx.itesm.seb.Outputs.Screens;

public abstract class EnhancedScreen {
    public subscreen screenState = subscreen.MAIN;

    public void setScreenState(subscreen screenState){
        this.screenState = screenState;
    }

    public abstract void updateScreen();

    public enum subscreen {
        MAIN,
        ABOUT,
        SETTINGS,
        PLAY,
        PAUSE
    }
}
