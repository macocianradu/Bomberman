/*
 * Decompiled with CFR 0.139.
 */
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;

public class Sound {
    private String path;
    int x;
    Clip clip = AudioSystem.getClip();
    AudioInputStream ais;

    public Sound(String path) throws Exception {
        this.path = path;
        this.x = this.x;
        this.ais = AudioSystem.getAudioInputStream(Sound.class.getResource(this.path));
    }

    public void setVolume(float f) {
        Main.masterVolume = (FloatControl)this.clip.getControl(FloatControl.Type.MASTER_GAIN);
        Main.masterVolume.setValue(f);
    }
}

