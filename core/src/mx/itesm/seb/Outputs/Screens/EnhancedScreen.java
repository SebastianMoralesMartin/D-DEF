//Alberto

package mx.itesm.seb.Outputs.Screens;

public abstract class EnhancedScreen {
    public subscreen screenState = subscreen.MAIN;

    public void setScreenState(subscreen screenState){
        this.screenState = screenState;
    }

    public abstract void updateScreen();

    public abstract void setSkins();

    public enum subscreen {
        MAIN,
        SUBSCREEN_1,
        SUBSCREEN_2,
        SUBSCREEN_3,
        SUBSCREEN_4,
        SUBSCREEN_5
    }
}
