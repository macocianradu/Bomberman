/*
 * Decompiled with CFR 0.139.
 */
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class Creep
extends Movable {
    int id;
    Image img;
    static ArrayList<Creep> creepList = new ArrayList();

    public Creep(int x, int y, int backX, int backY, int id) {
        super(x, y, backX, backY);
        try {
            this.img = ImageIO.read(Game.class.getResourceAsStream("/Img/cDown.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.id = id;
    }

    public static void populateCreeps() {
        int id = 65;
        for (int i = 0; i < Map.layout.length; ++i) {
            for (int j = 0; j < Map.layout[0].length; ++j) {
                if (Map.layout[i][j] != '0') continue;
                creepList.add(new Creep(j * Settings.Dim.width, i * Settings.Dim.height, j, i, id));
                Map.layout[i][j] = (char)id++;
            }
        }
    }

    @Override
    public void up() {
        if (Map.layout[this.backY][this.backX] == (char)this.id) {
            Map.layout[this.backY][this.backX] = 42;
        }
        this.newy += -Settings.Dim.height;
        --this.backY;
        try {
            this.img = ImageIO.read(Game.class.getResourceAsStream("/Img/cUp.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (Map.layout[this.backY][this.backX] == 'x') {
            Game.dead = true;
        }
        Map.layout[this.backY][this.backX] = (char)this.id;
    }

    @Override
    public void down() {
        if (Map.layout[this.backY][this.backX] == (char)this.id) {
            Map.layout[this.backY][this.backX] = 42;
        }
        this.newy += Settings.Dim.height;
        ++this.backY;
        try {
            this.img = ImageIO.read(Game.class.getResourceAsStream("/Img/cDown.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (Map.layout[this.backY][this.backX] == 'x') {
            Game.dead = true;
        }
        Map.layout[this.backY][this.backX] = (char)this.id;
    }

    @Override
    public void left() {
        if (Map.layout[this.backY][this.backX] == (char)this.id) {
            Map.layout[this.backY][this.backX] = 42;
        }
        this.newx += -Settings.Dim.width;
        --this.backX;
        try {
            this.img = ImageIO.read(Game.class.getResourceAsStream("/Img/cLeft.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (Map.layout[this.backY][this.backX] == 'x') {
            Game.dead = true;
        }
        Map.layout[this.backY][this.backX] = (char)this.id;
    }

    @Override
    public void right() {
        if (Map.layout[this.backY][this.backX] == (char)this.id) {
            Map.layout[this.backY][this.backX] = 42;
        }
        this.newx += Settings.Dim.width;
        ++this.backX;
        try {
            this.img = ImageIO.read(Game.class.getResourceAsStream("/Img/cRight.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (Map.layout[this.backY][this.backX] == 'x') {
            Game.dead = true;
        }
        Map.layout[this.backY][this.backX] = (char)this.id;
    }

    public void move() {
        Random r = new Random();
        switch (r.nextInt(30)) {
            case 3: {
                this.currentAction = Actions.up;
                this.actioned = true;
                break;
            }
            case 2: {
                this.currentAction = Actions.down;
                this.actioned = true;
                break;
            }
            case 1: {
                this.currentAction = Actions.left;
                this.actioned = true;
                break;
            }
            case 0: {
                this.currentAction = Actions.right;
                this.actioned = true;
                break;
            }
        }
    }
}

