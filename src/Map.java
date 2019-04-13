/*
 * Decompiled with CFR 0.139.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

public class Map {
    static char[][] layout;

    public static void generateMap() throws IOException {
        InputStream level = Main.class.getResourceAsStream("/level.txt");
        InputStreamReader in = new InputStreamReader(level);
        BufferedReader r = new BufferedReader(in);
        int row = Integer.parseInt(r.readLine());
        int col = Integer.parseInt(r.readLine());
        layout = new char[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                Map.layout[i][j] = (char)r.read();
            }
            System.out.println();
            r.read();
            r.read();
        }
        r.close();
        in.close();
    }

    public static boolean canMove(Actions a, Entity b) {
        switch (a) {
            case up: {
                if (layout[b.backY - 1][b.backX] == '#' || layout[b.backY - 1][b.backX] == '-' || layout[b.backY - 1][b.backX] == 'b') break;
                return true;
            }
            case down: {
                if (layout[b.backY + 1][b.backX] == '#' || layout[b.backY + 1][b.backX] == '-' || layout[b.backY + 1][b.backX] == 'b') break;
                return true;
            }
            case left: {
                if (layout[b.backY][b.backX - 1] == '#' || layout[b.backY][b.backX - 1] == '-' || layout[b.backY][b.backX - 1] == 'b') break;
                return true;
            }
            case right: {
                if (layout[b.backY][b.backX + 1] == '#' || layout[b.backY][b.backX + 1] == '-' || layout[b.backY][b.backX + 1] == 'b') break;
                return true;
            }
        }
        return false;
    }

    public static boolean canMove(Actions a, Bomb b) {
        switch (a) {
            case up: {
                if (layout[b.backY - 1][b.backX] != '*') break;
                return true;
            }
            case down: {
                if (layout[b.backY + 1][b.backX] != '*') break;
                return true;
            }
            case left: {
                if (layout[b.backY][b.backX - 1] != '*') break;
                return true;
            }
            case right: {
                if (layout[b.backY][b.backX + 1] != '*') break;
                return true;
            }
        }
        return false;
    }

}

