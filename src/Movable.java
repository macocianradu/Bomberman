/*
 * Decompiled with CFR 0.139.
 */
import java.awt.Dimension;
import java.util.ArrayList;

public class Movable
extends Entity {
    int newx;
    int newy;
    static ArrayList<Movable> list = new ArrayList();

    public Movable(int x, int y, int backX, int backY) {
        super(x, y, backX, backY);
        this.newx = x;
        this.newy = y;
        list.add(this);
    }

    public void up() {
        if (Map.layout[this.backY][this.backX] == 'x') {
            Map.layout[this.backY][this.backX] = 42;
        }
        this.newy += -Settings.Dim.height;
        --this.backY;
        Map.layout[this.backY][this.backX] = 120;
    }

    public void down() {
        if (Map.layout[this.backY][this.backX] == 'x') {
            Map.layout[this.backY][this.backX] = 42;
        }
        this.newy += Settings.Dim.height;
        ++this.backY;
        Map.layout[this.backY][this.backX] = 120;
    }

    public void left() {
        if (Map.layout[this.backY][this.backX] == 'x') {
            Map.layout[this.backY][this.backX] = 42;
        }
        this.newx += -Settings.Dim.width;
        --this.backX;
        Map.layout[this.backY][this.backX] = 120;
    }

    public void right() {
        if (Map.layout[this.backY][this.backX] == 'x') {
            Map.layout[this.backY][this.backX] = 42;
        }
        this.newx += Settings.Dim.width;
        ++this.backX;
        Map.layout[this.backY][this.backX] = 120;
    }
}

