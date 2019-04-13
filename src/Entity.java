/*
 * Decompiled with CFR 0.139.
 */
public class Entity {
    int x;
    int y;
    int backX;
    int backY;
    boolean actioned = false;
    public Actions currentAction;
    public Actions lastAction;

    public Entity(int x, int y, int backX, int backY) {
        this.x = x;
        this.y = y;
        this.backX = backX;
        this.backY = backY;
    }

    public int getx() {
        return this.x;
    }

    public int gety() {
        return this.y;
    }
}

