/*
 * Decompiled with CFR 0.139.
 */
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Bomb
extends Entity {
    int timer;
    int power = 4;
    Image[] exY = new Image[4];
    Image[] exX = new Image[4];
    Image[] exC = new Image[4];
    int sizeU = 0;
    int sizeD = 0;
    int sizeL = 0;
    int sizeR = 0;

    public Bomb(int x, int y, int backX, int backY) throws IOException {
        super(x, y, backX, backY);
        for (int i = 1; i < 4; ++i) {
            this.exX[i] = ImageIO.read(this.getClass().getResource("/Img/ExX" + i + ".png"));
            this.exY[i] = ImageIO.read(this.getClass().getResource("/Img/ExY" + i + ".png"));
            this.exC[i] = ImageIO.read(this.getClass().getResource("/Img/ExC" + i + ".png"));
        }
    }
}

