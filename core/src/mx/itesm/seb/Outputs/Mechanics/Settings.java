//Alberto

package mx.itesm.seb.Outputs.Mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {

    Preferences preferences;

    public Settings(){
        this.preferences = Gdx.app.getPreferences("com.settings.dunkikdefense");
    }

    public void setMusic(Boolean value){
        preferences.putBoolean("MusicEnabled", value);
    }

    public Boolean getMusic(){
        return preferences.getBoolean("MusicEnabled", true);
    }

    public void setSound(Boolean value){
        preferences.putBoolean("SoundEnabled", value);
    }

    public Boolean getSound(){
        return preferences.getBoolean("SoundEnabled", true);
    }

    public void setDarkMode(Boolean value){
        preferences.putBoolean("Dark Mode", value);
    }

    public Boolean getDarkMode(){
        return preferences.getBoolean("Dark Mode", false);
    }

    public void setName(String name){
        preferences.putString("Name", name);
    }

    public String getName(){
        return preferences.getString("Name", "James");
    }

    public void setHighScore(int highScore){
        preferences.putInteger("High Score", highScore);
    }

    public int getHighScore(){
        return preferences.getInteger("High Score", 0);
    }
}
