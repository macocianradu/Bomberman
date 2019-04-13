/*
 * Decompiled with CFR 0.139.
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class Bomber
extends Movable
implements KeyListener {
    int power = 2;
    static ArrayList<Bomb> bombsList = new ArrayList();
    int mov = 0;

    public Bomber(int x, int y, int backX, int backY) {
        super(x, y, backX, backY);
    }

    public int getPower() {
        return this.power;
    }

    @Override
    public void up() {
        if (Map.layout[this.backY][this.backX] == 'x') {
            Map.layout[this.backY][this.backX] = 42;
        }
        --this.backY;
        if (Map.layout[this.backY][this.backX] == 'p' && this.power < 9) {
            ++this.power;
        }
        if (Map.layout[this.backY][this.backX] == 'e' && bombsList.size() < 9) {
            try {
                bombsList.add(new Bomb(0, 0, 0, 0));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Map.layout[this.backY][this.backX] >= 'A' && Map.layout[this.backY][this.backX] <= 'Z') {
            Game.dead = true;
        }
        Map.layout[this.backY][this.backX] = 120;
    }

    @Override
    public void down() {
        if (Map.layout[this.backY][this.backX] == 'x') {
            Map.layout[this.backY][this.backX] = 42;
        }
        ++this.backY;
        if (Map.layout[this.backY][this.backX] == 'p' && this.power < 9) {
            ++this.power;
        }
        if (Map.layout[this.backY][this.backX] == 'e' && bombsList.size() < 9) {
            try {
                bombsList.add(new Bomb(0, 0, 0, 0));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Map.layout[this.backY][this.backX] >= 'A' && Map.layout[this.backY][this.backX] <= 'Z') {
            Game.dead = true;
        }
        Map.layout[this.backY][this.backX] = 120;
    }

    @Override
    public void left() {
        if (Map.layout[this.backY][this.backX] == 'x') {
            Map.layout[this.backY][this.backX] = 42;
        }
        --this.backX;
        if (Map.layout[this.backY][this.backX] == 'p' && this.power < 9) {
            ++this.power;
        }
        if (Map.layout[this.backY][this.backX] == 'e' && bombsList.size() < 9) {
            try {
                bombsList.add(new Bomb(0, 0, 0, 0));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Map.layout[this.backY][this.backX] >= 'A' && Map.layout[this.backY][this.backX] <= 'Z') {
            Game.dead = true;
        }
        Map.layout[this.backY][this.backX] = 120;
    }

    @Override
    public void right() {
        if (Map.layout[this.backY][this.backX] == 'x') {
            Map.layout[this.backY][this.backX] = 42;
        }
        ++this.backX;
        if (Map.layout[this.backY][this.backX] == 'p' && this.power < 9) {
            ++this.power;
        }
        if (Map.layout[this.backY][this.backX] == 'e' && bombsList.size() < 9) {
            try {
                bombsList.add(new Bomb(0, 0, 0, 0));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Map.layout[this.backY][this.backX] >= 'A' && Map.layout[this.backY][this.backX] <= 'Z') {
            Game.dead = true;
        }
        Map.layout[this.backY][this.backX] = 120;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (!this.actioned) {
            this.actioned = true;
            if (code == 39) {
                this.currentAction = Actions.right;
            } else if (code == 40) {
                this.currentAction = Actions.down;
            } else if (code == 37) {
                this.currentAction = Actions.left;
            } else if (code == 38) {
                this.currentAction = Actions.up;
            } else if (code == 32) {
                this.currentAction = Actions.bomb;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

