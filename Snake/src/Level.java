import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level {
    Game gam;
    List<Entity> Delete = new ArrayList<>();
    List<Integer> Lines = new ArrayList<>();
    Entity start;
    int Req;

    public Level(Game g) {
        gam = g;
    }

    public void doIt(int number) {
        clear();
        start = new Entity(3, 4);
        Req = 10;
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
            start = new Entity(17, 12);
            Req = 20;

        }
        if (number == 2) {
            emptyRect(9, 2, 10, 2);
            place(30, 2);
            place(30, 3);
            place(30, 4);
            emptyRect(30, 5, 32, 5);
            place(7, 4);
            place(12, 4);
            place(12, 5);
            place(12, 5);
            place(4, 7);
            place(7, 5);
            emptyRect(9, 7, 10, 7);
            place(15, 7);
            place(5, 8);
            place(14, 8);
            emptyRect(21, 8, 28, 8);
            place(33, 7);
            place(33, 8);
            place(6, 9);
            place(13, 9);
            emptyRect(24, 9, 25, 9);
            place(33, 9);
            place(7, 10);
            place(12, 10);
            emptyRect(9, 10, 10, 10);
            emptyRect(24, 10, 25, 10);
            emptyRect(9, 11, 10, 11);
            emptyRect(9, 12, 10, 12);
            place(30, 12);
            emptyRect(9, 13, 10, 13);
            emptyRect(24, 13, 25, 13);
            place(31, 13);
            emptyRect(9, 14, 10, 14);
            emptyRect(24, 14, 25, 14);
            place(32, 14);
            emptyRect(19, 15, 21, 15);
            emptyRect(33, 15, 35, 15);
            place(36, 14);
            place(37, 13);
            place(38, 12);
            place(8, 16);
            place(11, 16);
            emptyRect(27, 16, 29, 16);
            place(34, 16);
            place(7, 17);
            place(12, 17);
            place(27, 17);
            place(6, 18);
            place(13, 18);
            emptyRect(27, 18, 28, 18);
            place(5, 19);
            place(14, 19);
            place(27, 19);
            place(4, 20);
            place(15, 20);
            emptyRect(27, 20, 29, 20);
            Deadzone(28, 19);
            Deadzone(28, 17);
            start = new Entity(11, 12);
        }


        if (number == 3) {
            emptyRect(5, 10, 5, 7);
            place(6, 11);
            place(7, 12);
            place(6, 6);
            emptyRect(7, 5, 10, 5);
            place(11, 6);
            emptyRect(12, 7, 17, 7);
            place(18, 6);
            place(19, 6);
            place(20, 5);
            place(20, 4);
            quer(11, 10, 12, 11);
            quer(13, 11, 15, 13);
            quer(16, 13, 17, 14);
            emptyRect(19, 11, 21, 11);
            emptyRect(22, 12, 22, 14);
            emptyRect(21, 15, 21, 18);
            Lines = Arrays.asList(22, 19, 22, 20, 23);
            DrawLines();
            emptyRect(24, 21, 25, 21);
            quer(26, 20, 30, 16);
            quer(27, 20, 29, 18);
            quer(29, 16, 30, 15);
            place(27, 10);
            Lines = Arrays.asList(26, 9, 26, 5, 27, 4, 28, 3, 32);
            DrawLines();
            emptyRect(33, 4, 34, 7);
            Lines = Arrays.asList(32, 8, 32, 10, 35);
            DrawLines();
            emptyRect(36, 9, 37, 9);
            emptyRect(38, 10, 38, 13);
            emptyRect(37, 14, 37, 17);
            emptyRect(36, 18, 36, 20);
            place(35, 21);
            Lines = Arrays.asList(2, 22, 2, 20, 3, 17);
            DrawLines();
            emptyRect(4, 16, 5, 16);
            emptyRect(6, 17, 6, 23);
            emptyRect(7, 24, 8, 24);
            quer(9, 23, 11, 21);
            quer(10, 21, 12, 19);
            quer(11, 19, 14, 16);
            quer(13, 16, 14, 15);
            place(15, 16);
            emptyRect(16, 17, 16, 22);
            emptyRect(17, 23, 18, 23);
            emptyRect(19, 22, 19, 21);
        }
        if (number == 4) {
            fillRect(3, 4, 5, 6);
            place(3, 3);
            emptyRect(14, 21, 19, 24);
            delEmptyRect(15, 21, 19, 21);
            Lines = Arrays.asList(4, 9, 4, 12, 7, 15, 11);
            DrawLines();
            emptyRect(11,3,11,6);
            emptyRect(17,3,23,3);
            emptyRect(34,2,35,3);
            emptyRect(15,7,16,8);
            Lines = Arrays.asList(27,6,31,11);
            DrawLines();
            emptyRect(25,9,27,10);
            emptyRect(35,7,39,8);
            fillRect(19,12,21,14);
            Lines = Arrays.asList(17,18,21,15);
            DrawLines();
            emptyRect(29,13,29,15);
            place(30,15);
            fillRect(35,14,37,16);
            place(7,19);
            Lines = Arrays.asList(8,20,6,23,8,24);
            DrawLines();
            Lines = Arrays.asList(24,21,24,23,28,20);
            DrawLines();
            emptyRect(32,21,34,22);

            start = new Entity(6, 7);

        }
        if(number==5) {
            place(14, 2);
            place(16, 2);
            place(13, 3);
            place(15, 3);
            emptyRect(17, 3, 18, 3);
            place(12, 4);
            place(19, 4);
            place(11, 5);
            place(15, 5);
            place(19, 5);
            place(11, 6);
            place(12, 6);
            place(13, 6);
            place(14, 6);
            emptyRect(17, 6, 18, 6);
            place(19, 7);
            place(10, 8);
            place(19, 8);
            place(10, 9);
            place(13, 9);
            place(16, 9);
            place(19, 9);
            place(11, 10);
            place(18, 10);
            place(12, 11);
            place(18, 11);
            place(13, 12);
            place(14, 12);
            place(16, 12);
            place(17, 12);
            place(14, 13);
            place(17, 13);
            place(14, 14);
            place(17, 14);
            place(13, 15);
            place(17, 15);
            place(13, 16);
            place(16, 16);
            place(12, 17);
            place(16, 17);
            place(12, 18);
            place(16, 18);
            place(12, 19);
            place(16, 19);
            place(12, 20);
            place(12, 21);
            emptyRect(17, 20, 19, 20);
            place(20, 19);
            place(21, 18);
            emptyRect(23, 17, 24, 17);
            emptyRect(25, 18, 26, 18);
            place(27, 19);
            place(28, 20);
            emptyRect(29, 21, 31, 21);
            place(32, 20);
            place(13, 22);
            place(14, 23);
            emptyRect(15, 24, 20, 24);
            place(21, 23);
            emptyRect(22, 22, 23, 22);
            place(24, 21);
            emptyRect(25, 22, 26, 22);
            place(27, 23);
            emptyRect(28, 24, 31, 24);
            place(32, 23);
            place(33, 23);
            Deadzone(12, 5);
            Deadzone(14, 3);
            Deadzone(16, 3);
            Deadzone(19, 6);
            Deadzone(23, 22);
            start = new Entity(24, 4);
        }
        if(number==6){
            emptyRect(12, 2, 17, 2);
            place(11, 3);
            place(18, 3);
            place(10, 4);
            place(20, 4);
            place(9, 5);
            emptyRect(14, 5, 16, 5);
            place(21, 5);
            place(9, 6);
            place(13, 6);
            place(17, 6);
            place(22, 6);
            place(9, 7);
            place(13, 7);
            place(18, 7);
            place(22, 7);
            place(9, 8);
            place(14, 8);
            place(19, 8);
            place(23, 8);
            place(10, 9);
            place(15, 9);
            place(16, 9);
            place(20, 9);
            place(23, 9);
            place(11, 10);
            place(17, 10);
            place(21, 10);
            place(23, 10);
            place(13, 11);
            place(18, 11);
            place(21, 11);
            place(24, 11);
            place(10, 12);
            place(12, 12);
            place(15, 12);
            place(19, 12);
            place(21, 12);
            place(24, 12);
            place(28, 11);
            place(30, 11);
            place(28, 12);
            place(31, 12);
            place(9, 13);
            place(13, 13);
            place(16, 13);
            place(19, 13);
            place(21, 13);
            place(24, 13);
            place(29, 13);
            place(32, 13);
            place(8, 14);
            place(15, 14);
            place(15, 14);
            place(20, 14);
            place(22, 14);
            place(25, 14);
            place(30, 14);
            place(33, 14);
            place(8, 15);
            place(11, 15);
            place(20, 15);
            place(22, 15);
            place(25, 15);
            place(31, 15);
            place(33, 15);
            place(8, 16);
            place(11, 16);
            place(13, 16);
            place(19, 16);
            place(23, 16);
            place(26, 16);
            place(31, 16);
            place(33, 16);
            place(8, 17);
            place(11, 17);
            place(14, 17);
            place(18, 17);
            place(23, 17);
            place(26, 17);
            place(30, 17);
            place(33, 17);
            place(9, 18);
            place(12, 18);
            place(16, 18);
            place(24, 18);
            place(27, 18);
            place(29, 18);
            place(33, 18);
            place(8, 19);
            place(13, 19);
            place(24, 19);
            place(32, 19);
            place(9, 20);
            place(13, 20);
            place(25, 20);
            place(26, 20);
            place(30, 20);
            place(31, 20);
            place(9, 21);
            place(10, 21);
            place(13, 21);
            place(9, 21);
            place(27, 21);
            place(29, 21);
            place(11, 22);
            place(12, 23);
            place(13, 24);
            Deadzone(11, 11);
            Deadzone(12, 11);
            Deadzone(13, 12);
            Deadzone(14, 12);
            Deadzone(15, 13);
            Deadzone(8, 18);
            Deadzone(9, 19);
            Deadzone(12, 16);
            Deadzone(12, 17);
            Deadzone(13, 17);
            Deadzone(13, 18);
            Deadzone(14, 6);
            Deadzone(15, 6);
            Deadzone(16, 6);
            Deadzone(14, 7);
            Deadzone(15, 7);
            Deadzone(16, 7);
            Deadzone(17, 7);
            Deadzone(15, 8);
            Deadzone(16, 8);
            Deadzone(17, 8);
            Deadzone(18, 8);
            Deadzone(17, 9);
            Deadzone(18, 9);
            Deadzone(19, 9);
            Deadzone(18, 10);
            Deadzone(19, 10);
            Deadzone(20, 10);
            Deadzone(19, 11);
            Deadzone(20, 11);
            Deadzone(20, 12);
            Deadzone(20, 13);
            Deadzone(21, 14);
            Deadzone(21, 15);
            start = new Entity(25, 6);
        }

        if(number==11){
            fillRect(1, 1, 40, 25);
            delFillRect(8,3,15,5);
            delFillRect(11, 6, 12, 13);
            delFillRect(3, 9, 6, 18);
            delFillRect(7,14,7,18);
            delFillRect(8,14,15,20);
            delFillRect(16,16,24,17);
            delFillRect(25,13,31,22);
            delFillRect(32,13,37,17);
            delFillRect(29,12,30,9);
            delFillRect(29,6,36,8);
            delFillRect(26,3,31,7);
            delFillRect(21,6,25,7);
            delFillRect(17,8,22,10);
            start = new Entity(3,16);

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
                    if (x != Lines.get(i)) {
                        emptyRect(x, y, Lines.get(i), y);
                        x = Lines.get(i);
                    }
                } else {
                    if (x != Lines.get(i)) {
                        emptyRect(x, y, x, Lines.get(i));
                        y = Lines.get(i);
                    }
                }
                sss = !sss;

            }
        }
        Lines = new ArrayList<>();
    }

    public void quer(int x1, int y1, int x2, int y2) {
        place(x1, y1);
        if ((x2 - x1) - (y2 - y1) == 0 || (x2 - x1) + (y2 - y1) == 0) {
            while (x1 - x2 != 0) {
                if (x1 - x2 < 0) {
                    x1++;
                } else {
                    x1--;
                }
                if (y1 - y2 < 0) {
                    y1++;
                } else {
                    y1--;
                }
                place(x1, y1);
            }

        } else {
            System.out.println("quer problem! " + x1 + y1);
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

    public void fillRect(int x1, int y1, int x2, int y2) {
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
            for (int j = y1; j <= y2; j++) {
                place(i, j);

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

    public void delFillRect(int x1, int y1, int x2, int y2) {
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
            for (int j = y1; j <= y2; j++) {
                del(i, j);

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
