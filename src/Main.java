/*
 * Decompiled with CFR 0.139.
 */
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

public class Main {
    static FloatControl masterVolume;
    static Sound bgSound;

    public static void main(String[] args) throws IOException {
        Map.generateMap();
        new Settings(1024, 768, -4.0f);
        new StartMenu();
        try {
            bgSound = new Sound("/Sound/Background.wav");
            Main.bgSound.clip.open(Main.bgSound.ais);
            Main.bgSound.clip.loop(20);
            bgSound.setVolume(Settings.volume);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

