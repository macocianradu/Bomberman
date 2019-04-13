/*
 * Decompiled with CFR 0.139.
 */
import java.awt.Dimension;

public class Settings {
    static double resWidth;
    static double resHeight;
    static float volume;
    static int tickRate;
    static Dimension Dim;

    public Settings(int w, int h, float v) {
        resWidth = w;
        resHeight = h;
        volume = v;
        Dim = Settings.setDim(Map.layout.length, Map.layout[0].length + 1);
    }

    public static Dimension setDim(int col, int row) {
        Dimension Dim = new Dimension((int)resWidth / col, (int)resHeight / row);
        return Dim;
    }

    static {
        tickRate = 50;
    }
}

