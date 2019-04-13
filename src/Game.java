/*
 * Decompiled with CFR 0.139.
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game
extends JPanel {
    Image[] bmb = new Image[10];
    Image block;
    Image brick;
    Image grass;
    Image power;
    Image extra;
    Image playerImg = ImageIO.read(Game.class.getResourceAsStream("/Img/bDown.png"));
    Bomber p1;
    Timer t;
    JButton pause;
    static boolean paused = false;
    static JFrame game = new JFrame("Bomberman");
    static boolean dead = false;
    Sound exploder;
    int chance;
    ArrayList<Creep> toRemove;

    public Game() throws IOException {
        this.p1 = new Bomber(Settings.Dim.width, Settings.Dim.height, 1, 1);
        this.t = new Timer(5, null);
        this.pause = new JButton("Pause");
        try {
            this.exploder = new Sound("/Sound/Explosion.mp3");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.chance = 20;
        this.toRemove = new ArrayList();
        game.setVisible(true);
        game.setContentPane(new GamePane());
        game.setSize((int)Settings.resWidth, (int)Settings.resHeight);
        game.setLocationRelativeTo(null);
        game.setDefaultCloseOperation(3);
        this.block = ImageIO.read(this.getClass().getResource("/Img/TextureBlock.png"));
        this.brick = ImageIO.read(this.getClass().getResource("/Img/TextureBrick.png"));
        this.power = ImageIO.read(this.getClass().getResource("/Img/power.png"));
        this.extra = ImageIO.read(Game.class.getResource("/Img/extraBomb.png"));
        this.grass = ImageIO.read(Game.class.getResource("/Img/grass.png"));
        try {
            this.exploder.clip.open(this.exploder.ais);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        this.exploder.setVolume(Settings.volume);
        Bomber.bombsList.add(new Bomb(0, 0, 0, 0));
        for (int i = 0; i < 10; ++i) {
            this.bmb[i] = ImageIO.read(Game.class.getResource("/Img/bomb.png"));
        }
    }

    public void paintMap(Graphics g) {
        for (int i = 0; i < Map.layout.length * Settings.Dim.height; i += Settings.Dim.height) {
            for (int j = 0; j < Map.layout[0].length * Settings.Dim.width; j += Settings.Dim.width) {
                if (Map.layout[i / Settings.Dim.height][j / Settings.Dim.width] == '#') {
                    g.drawImage(this.block, j, i, Settings.Dim.width, Settings.Dim.height, null);
                    continue;
                }
                if (Map.layout[i / Settings.Dim.height][j / Settings.Dim.width] == '*') {
                    g.drawImage(this.grass, j, i, Settings.Dim.width, Settings.Dim.height, null);
                    continue;
                }
                if (Map.layout[i / Settings.Dim.height][j / Settings.Dim.width] == '-') {
                    g.drawImage(this.brick, j, i, Settings.Dim.width, Settings.Dim.height, null);
                    continue;
                }
                if (Map.layout[i / Settings.Dim.height][j / Settings.Dim.width] == 'p') {
                    g.drawImage(this.power, j, i, Settings.Dim.width, Settings.Dim.height, null);
                    continue;
                }
                if (Map.layout[i / Settings.Dim.height][j / Settings.Dim.width] == 'e') {
                    g.drawImage(this.extra, j, i, Settings.Dim.width, Settings.Dim.height, null);
                    continue;
                }
                g.drawImage(this.grass, j, i, Settings.Dim.width, Settings.Dim.height, null);
            }
        }
    }

    public void paintPlayer(Graphics g, Bomber b) {
        g.drawImage(this.playerImg, b.x, b.y, Settings.Dim.width, Settings.Dim.height, null);
    }

    public void paintCreeps(Graphics g) {
        for (Creep c : Creep.creepList) {
            g.drawImage(c.img, c.x, c.y, Settings.Dim.width, Settings.Dim.height, null);
        }
    }

    public void Tick(Bomber b) throws IOException {
        if (b.actioned) {
            if (b.currentAction == Actions.up) {
                if (Map.canMove(Actions.up, this.p1)) {
                    if (b.lastAction != Actions.up) {
                        switch (b.mov) {
                            case 0: {
                                b.newy = b.y - Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bUp12.png"));
                                ++b.mov;
                                break;
                            }
                            case 1: {
                                b.newy = b.y - Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bUp11.png"));
                                ++b.mov;
                                break;
                            }
                            case 2: {
                                b.newy = b.y - Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bUp12.png"));
                                ++b.mov;
                                break;
                            }
                            case 3: {
                                b.newy = b.y - Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bUp.png"));
                                b.mov = 0;
                                b.up();
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    } else {
                        switch (b.mov) {
                            case 0: {
                                b.newy = b.y - Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bUp22.png"));
                                ++b.mov;
                                break;
                            }
                            case 1: {
                                b.newy = b.y - Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bUp21.png"));
                                ++b.mov;
                                break;
                            }
                            case 2: {
                                b.newy = b.y - Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bUp22.png"));
                                ++b.mov;
                                break;
                            }
                            case 3: {
                                b.newy = b.y - Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bUp.png"));
                                b.mov = 0;
                                b.up();
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                }
            } else if (b.currentAction == Actions.down) {
                if (Map.canMove(Actions.down, this.p1)) {
                    if (b.lastAction != Actions.down) {
                        switch (b.mov) {
                            case 0: {
                                b.newy = b.y + Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bDown12.png"));
                                ++b.mov;
                                break;
                            }
                            case 1: {
                                b.newy = b.y + Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bDown11.png"));
                                ++b.mov;
                                break;
                            }
                            case 2: {
                                b.newy = b.y + Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bDown12.png"));
                                ++b.mov;
                                break;
                            }
                            case 3: {
                                b.newy = b.y + Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bDown.png"));
                                b.mov = 0;
                                b.down();
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    } else {
                        switch (b.mov) {
                            case 0: {
                                b.newy = b.y + Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bDown22.png"));
                                ++b.mov;
                                break;
                            }
                            case 1: {
                                b.newy = b.y + Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bDown21.png"));
                                ++b.mov;
                                break;
                            }
                            case 2: {
                                b.newy = b.y + Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bDown22.png"));
                                ++b.mov;
                                break;
                            }
                            case 3: {
                                b.newy = b.y + Settings.Dim.height / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bDown.png"));
                                b.mov = 0;
                                b.down();
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                }
            } else if (b.currentAction == Actions.left) {
                if (Map.canMove(Actions.left, this.p1)) {
                    if (b.lastAction != Actions.left) {
                        switch (b.mov) {
                            case 0: {
                                b.newx = b.x - Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bLeft12.png"));
                                ++b.mov;
                                break;
                            }
                            case 1: {
                                b.newx = b.x - Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bLeft11.png"));
                                ++b.mov;
                                break;
                            }
                            case 2: {
                                b.newx = b.x - Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bLeft12.png"));
                                ++b.mov;
                                break;
                            }
                            case 3: {
                                b.newx = b.x - Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bLeft.png"));
                                b.mov = 0;
                                b.left();
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    } else {
                        switch (b.mov) {
                            case 0: {
                                b.newx = b.x - Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bLeft22.png"));
                                ++b.mov;
                                break;
                            }
                            case 1: {
                                b.newx = b.x - Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bLeft21.png"));
                                ++b.mov;
                                break;
                            }
                            case 2: {
                                b.newx = b.x - Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bLeft22.png"));
                                ++b.mov;
                                break;
                            }
                            case 3: {
                                b.newx = b.x - Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bLeft.png"));
                                b.mov = 0;
                                b.left();
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                }
            } else if (b.currentAction == Actions.right) {
                if (Map.canMove(Actions.right, this.p1)) {
                    if (b.lastAction != Actions.right) {
                        switch (b.mov) {
                            case 0: {
                                b.newx = b.x + Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bRight12.png"));
                                ++b.mov;
                                break;
                            }
                            case 1: {
                                b.newx = b.x + Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bRight11.png"));
                                ++b.mov;
                                break;
                            }
                            case 2: {
                                b.newx = b.x + Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bRight12.png"));
                                ++b.mov;
                                break;
                            }
                            case 3: {
                                b.newx = b.x + Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bRight.png"));
                                b.mov = 0;
                                b.right();
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    } else {
                        switch (b.mov) {
                            case 0: {
                                b.newx = b.x + Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bRight22.png"));
                                ++b.mov;
                                break;
                            }
                            case 1: {
                                b.newx = b.x + Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bRight21.png"));
                                ++b.mov;
                                break;
                            }
                            case 2: {
                                b.newx = b.x + Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bRight22.png"));
                                ++b.mov;
                                break;
                            }
                            case 3: {
                                b.newx = b.x + Settings.Dim.width / 4;
                                this.playerImg = ImageIO.read(Bomber.class.getResource("/Img/bRight.png"));
                                b.mov = 0;
                                b.right();
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                }
            } else if (b.currentAction == Actions.bomb) {
                this.bomb(b);
            }
            if (b.mov == 0) {
                b.actioned = false;
                b.lastAction = b.lastAction != b.currentAction ? b.currentAction : null;
            }
        }
        this.repaint();
    }

    public void CreepTick(Creep b) {
        if (b.actioned) {
            if (b.currentAction == Actions.up) {
                if (Map.canMove(Actions.up, b)) {
                    b.up();
                }
            } else if (b.currentAction == Actions.down) {
                if (Map.canMove(Actions.down, b)) {
                    b.down();
                }
            } else if (b.currentAction == Actions.left) {
                if (Map.canMove(Actions.left, b)) {
                    b.left();
                }
            } else if (b.currentAction == Actions.right && Map.canMove(Actions.right, b)) {
                b.right();
            }
            b.actioned = false;
        }
        this.repaint();
    }

    public void bomb(Bomber p) {
        for (Bomb b : Bomber.bombsList) {
            if (b.timer != 0) continue;
            b.timer = 50;
            b.backX = p.backX;
            b.backY = p.backY;
            b.x = p.x;
            b.y = p.y;
            Map.layout[b.backY][b.backX] = 98;
            return;
        }
    }

    public void explosion(Bomber p, Bomb b, Graphics g) {
        int i;
        --b.timer;
        b.power = p.getPower();
        Random r = new Random();
        int x = b.backX;
        int y = b.backY;
        b.sizeD = 0;
        b.sizeL = 0;
        b.sizeR = 0;
        b.sizeU = 0;
        if (b.timer > 3) {
            g.drawImage(b.exC[6 - b.timer], b.x, b.y, Settings.Dim.width, Settings.Dim.height, null);
        } else {
            g.drawImage(b.exC[b.timer], b.x, b.y, Settings.Dim.width, Settings.Dim.height, null);
        }
        if (Map.layout[b.backY][b.backX] == 'x') {
            dead = true;
        }
        while (Map.canMove(Actions.up, b) && b.power > 0) {
            --b.backY;
            --b.power;
            ++b.sizeU;
        }
        for (i = 1; i <= b.sizeU; ++i) {
            if (b.timer > 3) {
                g.drawImage(b.exY[6 - b.timer], b.x, b.y - Settings.Dim.height * i, Settings.Dim.width, Settings.Dim.height, null);
                continue;
            }
            g.drawImage(b.exY[b.timer], b.x, b.y - Settings.Dim.height * i, Settings.Dim.width, Settings.Dim.height, null);
        }
        if (Map.layout[b.backY - 1][b.backX] == '-' && b.power > 0 && b.timer == 0) {
            Map.layout[b.backY - 1][b.backX] = r.nextInt(this.chance) == 1 ? (char)112 : (r.nextInt(this.chance) == 2 ? 101 : (char)42);
        }
        if (Map.layout[b.backY - 1][b.backX] == 'x' && b.power > 0) {
            if (b.timer > 3) {
                g.drawImage(b.exX[6 - b.timer], b.x, (b.backY - 1) * Settings.Dim.height, Settings.Dim.width, Settings.Dim.height, null);
            } else {
                g.drawImage(b.exX[b.timer], b.x, (b.backY - 1) * Settings.Dim.height, Settings.Dim.width, Settings.Dim.height, null);
            }
            dead = true;
        }
        if (Map.layout[b.backY - 1][b.backX] >= 'A' && Map.layout[b.backY - 1][b.backX] <= 'Z' && b.power > 0) {
            for (Creep c : Creep.creepList) {
                if (c.id != Map.layout[b.backY - 1][b.backX]) continue;
                this.toRemove.add(c);
                Map.layout[b.backY - 1][b.backX] = 42;
                Map.layout[b.backY][b.backX] = 42;
            }
            if (b.timer > 3) {
                g.drawImage(b.exX[6 - b.timer], b.x, (b.backY - 1) * Settings.Dim.height, Settings.Dim.width, Settings.Dim.height, null);
            } else {
                g.drawImage(b.exX[b.timer], b.x, (b.backY - 1) * Settings.Dim.height, Settings.Dim.width, Settings.Dim.height, null);
            }
        }
        if (Map.layout[b.backY - 1][b.backX] == 'b' && b.power > 0) {
            for (Bomb b2 : Bomber.bombsList) {
                if (b2.backX != b.backX || b2.backY != b.backY - 1) continue;
                b2.timer = b.timer;
                Map.layout[b2.backY][b2.backX] = 42;
            }
        }
        b.power = p.getPower();
        b.backY = y;
        while (Map.canMove(Actions.down, b) && b.power > 0) {
            ++b.backY;
            --b.power;
            ++b.sizeD;
        }
        for (i = 1; i <= b.sizeD; ++i) {
            if (b.timer > 3) {
                g.drawImage(b.exY[6 - b.timer], b.x, b.y + Settings.Dim.height * i, Settings.Dim.width, Settings.Dim.height, null);
                continue;
            }
            g.drawImage(b.exY[b.timer], b.x, b.y + Settings.Dim.height * i, Settings.Dim.width, Settings.Dim.height, null);
        }
        if (Map.layout[b.backY + 1][b.backX] == '-' && b.power > 0 && b.timer == 0) {
            Map.layout[b.backY + 1][b.backX] = r.nextInt(this.chance) == 1 ? (char)112 : (r.nextInt(this.chance) == 2 ? 101 : (char)42);
        }
        if (Map.layout[b.backY + 1][b.backX] == 'x' && b.power > 0) {
            if (b.timer > 3) {
                g.drawImage(b.exX[6 - b.timer], b.x, (b.backY + 1) * Settings.Dim.height, Settings.Dim.width, Settings.Dim.height, null);
            } else {
                g.drawImage(b.exX[b.timer], b.x, (b.backY + 1) * Settings.Dim.height, Settings.Dim.width, Settings.Dim.height, null);
            }
            dead = true;
        }
        if (Map.layout[b.backY + 1][b.backX] >= 'A' && Map.layout[b.backY + 1][b.backX] <= 'Z' && b.power > 0) {
            for (Creep c : Creep.creepList) {
                if (c.id != Map.layout[b.backY + 1][b.backX]) continue;
                Map.layout[b.backY + 1][b.backX] = 42;
                this.toRemove.add(c);
                Map.layout[b.backY][b.backX] = 42;
            }
            if (b.timer > 3) {
                g.drawImage(b.exX[6 - b.timer], b.x, (b.backY + 1) * Settings.Dim.height, Settings.Dim.width, Settings.Dim.height, null);
            } else {
                g.drawImage(b.exX[b.timer], b.x, (b.backY + 1) * Settings.Dim.height, Settings.Dim.width, Settings.Dim.height, null);
            }
        }
        if (Map.layout[b.backY + 1][b.backX] == 'b' && b.power > 0) {
            for (Bomb b2 : Bomber.bombsList) {
                if (b2.backX != b.backX || b2.backY != b.backY + 1) continue;
                b2.timer = b.timer;
                Map.layout[b2.backY][b2.backX] = 42;
            }
        }
        b.power = p.getPower();
        b.backY = y;
        while (Map.canMove(Actions.left, b) && b.power > 0) {
            --b.backX;
            --b.power;
            ++b.sizeL;
        }
        for (i = 1; i <= b.sizeL; ++i) {
            if (b.timer > 3) {
                g.drawImage(b.exX[6 - b.timer], b.x - Settings.Dim.width * i, b.y, Settings.Dim.width, Settings.Dim.height, null);
                continue;
            }
            g.drawImage(b.exX[b.timer], b.x - Settings.Dim.width * i, b.y, Settings.Dim.width, Settings.Dim.height, null);
        }
        if (Map.layout[b.backY][b.backX - 1] == '-' && b.power > 0 && b.timer == 0) {
            Map.layout[b.backY][b.backX - 1] = r.nextInt(this.chance) == 1 ? (char)112 : (r.nextInt(this.chance) == 2 ? 101 : (char)42);
        }
        if (Map.layout[b.backY][b.backX - 1] == 'x' && b.power > 0) {
            if (b.timer > 3) {
                g.drawImage(b.exX[6 - b.timer], (b.backX - 1) * Settings.Dim.width, b.y, Settings.Dim.width, Settings.Dim.height, null);
            } else {
                g.drawImage(b.exX[b.timer], (b.backX - 1) * Settings.Dim.width, b.y, Settings.Dim.width, Settings.Dim.height, null);
            }
            dead = true;
        }
        if (Map.layout[b.backY][b.backX - 1] >= 'A' && Map.layout[b.backY][b.backX - 1] <= 'Z' && b.power > 0) {
            for (Creep c : Creep.creepList) {
                if (c.id != Map.layout[b.backY][b.backX - 1]) continue;
                Map.layout[b.backY][b.backX] = 42;
                this.toRemove.add(c);
                Map.layout[b.backY][b.backX - 1] = 42;
            }
            if (b.timer > 3) {
                g.drawImage(b.exX[6 - b.timer], (b.backX - 1) * Settings.Dim.width, b.y, Settings.Dim.width, Settings.Dim.height, null);
            } else {
                g.drawImage(b.exX[b.timer], (b.backX - 1) * Settings.Dim.width, b.y, Settings.Dim.width, Settings.Dim.height, null);
            }
        }
        if (Map.layout[b.backY][b.backX - 1] == 'b' && b.power > 0) {
            for (Bomb b2 : Bomber.bombsList) {
                if (b2.backX != b.backX - 1 || b2.backY != b.backY) continue;
                Map.layout[b2.backY][b2.backX] = 42;
                b2.timer = b.timer;
            }
        }
        b.power = p.getPower();
        b.backX = x;
        while (Map.canMove(Actions.right, b) && b.power > 0) {
            ++b.backX;
            --b.power;
            ++b.sizeR;
        }
        for (i = 1; i <= b.sizeR; ++i) {
            if (b.timer > 3) {
                g.drawImage(b.exX[6 - b.timer], b.x + Settings.Dim.width * i, b.y, Settings.Dim.width, Settings.Dim.height, null);
                continue;
            }
            g.drawImage(b.exX[b.timer], b.x + Settings.Dim.width * i, b.y, Settings.Dim.width, Settings.Dim.height, null);
        }
        if (Map.layout[b.backY][b.backX + 1] == '-' && b.power > 0 && b.timer == 0) {
            Map.layout[b.backY][b.backX + 1] = r.nextInt(this.chance) == 1 ? (char)112 : (r.nextInt(this.chance) == 2 ? 101 : (char)42);
        }
        if (Map.layout[b.backY][b.backX + 1] == 'x' && b.power > 0) {
            if (b.timer > 3) {
                g.drawImage(b.exX[6 - b.timer], (b.backX + 1) * Settings.Dim.width, b.y, Settings.Dim.width, Settings.Dim.height, null);
            } else {
                g.drawImage(b.exX[b.timer], (b.backX + 1) * Settings.Dim.width, b.y, Settings.Dim.width, Settings.Dim.height, null);
            }
            dead = true;
        }
        if (Map.layout[b.backY][b.backX + 1] >= 'A' && Map.layout[b.backY][b.backX + 1] <= 'Z' && b.power > 0) {
            for (Creep c : Creep.creepList) {
                if (c.id != Map.layout[b.backY][b.backX + 1]) continue;
                this.toRemove.add(c);
                Map.layout[b.backY][b.backX + 1] = 42;
                Map.layout[b.backY][b.backX] = 42;
            }
            if (b.timer > 3) {
                g.drawImage(b.exX[6 - b.timer], (b.backX + 1) * Settings.Dim.width, b.y, Settings.Dim.width, Settings.Dim.height, null);
            } else {
                g.drawImage(b.exX[b.timer], (b.backX + 1) * Settings.Dim.width, b.y, Settings.Dim.width, Settings.Dim.height, null);
            }
        }
        if (Map.layout[b.backY][b.backX + 1] == 'b' && b.power > 0) {
            for (Bomb b2 : Bomber.bombsList) {
                if (b2.backX != b.backX + 1 || b2.backY != b.backY) continue;
                b2.timer = b.timer;
                Map.layout[b2.backY][b2.backX] = 42;
            }
        }
        b.backX = x;
        b.backY = y;
    }

    public void gameOver() {
        try {
            Thread.sleep(1000L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        new GameOver();
        paused = true;
        game.setVisible(false);
        try {
            Map.generateMap();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Bomber.bombsList.clear();
        try {
            Bomber.bombsList.add(new Bomb(0, 0, 0, 0));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Movable.list.clear();
        Creep.creepList.clear();
        Creep.populateCreeps();
        this.p1.x = Settings.Dim.width;
        this.p1.y = Settings.Dim.height;
        this.p1.backX = 1;
        this.p1.backY = 1;
        this.p1.newx = this.p1.x;
        this.p1.newy = this.p1.y;
        this.p1.power = 2;
        Bomber.bombsList.clear();
        try {
            Bomber.bombsList.add(new Bomb(0, 0, 0, 0));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Movable.list.add(this.p1);
        dead = false;
        this.repaint();
    }

    class GamePane
    extends JPanel
    implements KeyListener,
    ActionListener {
        public GamePane() {
            new Timer(Settings.tickRate, new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!Game.paused) {
                        for (Creep c : Creep.creepList) {
                            c.move();
                        }
                        try {
                            Game.this.Tick(Game.this.p1);
                        }
                        catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        for (Creep c : Creep.creepList) {
                            Game.this.CreepTick(c);
                        }
                        for (Movable m : Movable.list) {
                            m.x = m.newx;
                            m.y = m.newy;
                        }
                        GamePane.this.repaint();
                        GamePane.this.requestFocus();
                        if (Game.dead || Creep.creepList.size() == 0) {
                            Game.this.gameOver();
                        }
                    } else {
                        Game.this.exploder.setVolume(Settings.volume);
                    }
                }
            }).start();
            Game.this.t.start();
            this.addKeyListener(this);
            this.setFocusable(true);
            this.setFocusTraversalKeysEnabled(false);
            Game.this.pause.setText("Pause");
            Game.this.pause.setPreferredSize(new Dimension(Settings.Dim.width * 2, Settings.Dim.height));
            this.add(Game.this.pause);
            Game.this.pause.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    Game.paused = true;
                    new Pause();
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            Game.this.paintMap(g);
            Game.this.paintPlayer(g, Game.this.p1);
            for (Bomb b : Bomber.bombsList) {
                if (b.timer > 5) {
                    g.drawImage(Game.this.bmb[Bomber.bombsList.indexOf(b)], b.x, b.y, Settings.Dim.width + b.timer % 10, Settings.Dim.height + b.timer % 10, null);
                    --b.timer;
                    continue;
                }
                if (b.timer == 5) {
                    Game.this.exploder.clip.loop(1);
                    Game.this.explosion(Game.this.p1, b, g);
                    Map.layout[b.backY][b.backX] = 42;
                    continue;
                }
                if (b.timer <= 0) continue;
                Game.this.explosion(Game.this.p1, b, g);
            }
            for (Creep c : Game.this.toRemove) {
                Creep.creepList.remove(c);
                Movable.list.remove(c);
            }
            Game.this.paintCreeps(g);
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Game.this.p1.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.repaint();
            for (Movable m : Movable.list) {
                m.x = m.newx;
                m.y = m.newy;
            }
        }

    }

}

