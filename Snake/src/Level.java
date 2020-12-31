import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level {
    Game gam;
    List<Entity> Delete = new ArrayList<>();
    List<Integer> Lines = new ArrayList<>();

    public Level(Game g) {
        gam = g;
    }

    public void doIt(int number) {
        clear();
        if (gam.WandTod) {
            emptyRect(0, 0, gam.WindowsizeW + 1, gam.WindowsizeH + 1);
        }
        if (number == 1) {
            emptyRect(1, 1, gam.WindowsizeW, gam.WindowsizeH);
            emptyRect(3, 3, 6, 4);
            emptyRect(3, 6, 6, 6);
            emptyRect(1, 9, 5, 11);
            emptyRect(1, 14, 5, 16);
            emptyRect(1, 22, 4, 22);
            emptyRect(4, 20, 7, 20);
            emptyRect(7, 21, 7, 22);
            emptyRect(8, 7, 8, 11);
            emptyRect(9, 9, 11, 9);
            emptyRect(8, 14, 8, 16);
            emptyRect(9, 3, 16, 4);
            emptyRect(11, 13, 12, 17);
            emptyRect(10, 20, 16, 20);
            emptyRect(11, 22, 15, 23);
            emptyRect(15, 10, 26, 15);
            emptyRect(16, 6, 25, 6);
            emptyRect(20, 7, 21, 8);
            emptyRect(16, 18, 25, 18);
            emptyRect(20, 19, 21, 20);
            emptyRect(20, 22, 21, 23);
            emptyRect(20, 2, 21, 4);
            emptyRect(25, 3, 32, 4);
            emptyRect(35, 3, 38, 4);
            emptyRect(35, 6, 38, 6);
            emptyRect(30, 9, 32, 9);
            emptyRect(33, 7, 33, 11);
            emptyRect(36, 9, 40, 11);
            emptyRect(29, 13, 30, 17);
            emptyRect(33, 14, 33, 16);
            emptyRect(25, 20, 31, 20);
            emptyRect(26, 22, 30, 23);
            emptyRect(36, 14, 40, 16);
            emptyRect(34, 20, 37, 20);
            emptyRect(34, 21, 34, 22);
            emptyRect(37, 22, 39, 22);
            del(1, 10);
            delEmptyRect(1, 12, 1, 13);
            del(1, 15);
            delEmptyRect(19, 10, 22, 10);
            del(40, 10);
            delEmptyRect(40, 12, 40, 13);
            del(40, 15);
            delEmptyRect(0, 12, 0, 13);
            delEmptyRect(41, 12, 41, 13);
            AppleEmptyRect(1, 10, 4, 10);
            AppleEmptyRect(1, 15, 4, 15);
            AppleEmptyRect(37, 10, 40, 10);
            AppleEmptyRect(37, 15, 40, 15);
        }
        if (number == 2) {
            Lines = Arrays.asList(10, 10, 11, 12, 8, 8);
            DrawLines();
        }

    }

    public void DrawLines() {
        boolean sss = true;
        int x;
        int y;
        if (Lines.size() > 1) {
            x = Lines.get(0);
            y = Lines.get(1);
            place(x, y);
            for (int i = 2; i < Lines.size(); i++) {
                if (sss) {
                    emptyRect(x, y, Lines.get(i), y);
                    x = Lines.get(i);
                } else {
                    emptyRect(x, y, x, Lines.get(i));
                    y = Lines.get(i);
                }
                sss = !sss;

            }
        }

    }

    public void AppleEmptyRect(int x1, int y1, int x2, int y2) {

        for (int i = x1; i <= x2; i++) {
            Deadzone(i, y1);
            if (y1 != y2) {
                Deadzone(i, y2);
            }
        }
        if (y2 - y1 > 0) {
            for (int i = y1 + 1; i < y2; i++) {
                Deadzone(x1, i);
                if (x1 != x2) {
                    Deadzone(x2, i);
                }
            }
        }

    }

    public void emptyRect(int x1, int y1, int x2, int y2) {
        if (x2 < x1) {
            int X = x2;
            x2 = x1;
            x1 = X;
        }
        if (y2 < y1) {
            int Y = y2;
            y2 = y1;
            y1 = Y;
        }
        for (int i = x1; i <= x2; i++) {
            place(i, y1);
            if (y1 != y2) {
                place(i, y2);
            }
        }
        if (y2 - y1 > 0) {
            for (int i = y1 + 1; i < y2; i++) {
                place(x1, i);
                if (x1 != x2) {
                    place(x2, i);
                }
            }
        }

    }

    public void delEmptyRect(int x1, int y1, int x2, int y2) {

        for (int i = x1; i <= x2; i++) {
            del(i, y1);
            if (y1 != y2) {
                del(i, y2);
            }
        }
        if (y2 - y1 > 0) {
            for (int i = y1 + 1; i < y2; i++) {
                del(x1, i);
                if (x1 != x2) {
                    del(x2, i);
                }
            }
        }

    }


    public void place(int x, int y) {
        if (!(x < 1 || x > 40 || y < 1 || y > 40)) {
            gam.blocks.add(new Entity("Block", x, y));
        }
        gam.Cells[x][y] = "Block";

    }

    public void del(int x, int y) {
        if (!(x < 1 || x > gam.WindowsizeW || y < 1 || y > gam.WindowsizeH)) {
            for (int i = 0; i < gam.blocks.size(); i++) {
                if (gam.blocks.get(i).x == x && gam.blocks.get(i).y == y) {
                    gam.blocks.remove(i);
                    i--;
                }
            }
        } else {
            gam.Portal.add(new Entity("leer", x, y));
        }
        gam.Cells[x][y] = "leer";
    }

    public void Deadzone(int x, int y) {
        gam.Cells[x][y] = "Dead";
    }

    public void clear() {
        gam.blocks.removeAll(gam.blocks);
        for (int i = 0; i < gam.WindowsizeW + 2; i++) {
            for (int j = 0; j < gam.WindowsizeH + 2; j++) {
                gam.Cells[i][j] = "leer";
            }
        }
    }


}
