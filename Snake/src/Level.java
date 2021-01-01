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


        }if (number == 2) {
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
                    if(x!=Lines.get(i)) {
                        emptyRect(x, y, Lines.get(i), y);
                        x = Lines.get(i);
                    }
                } else {
                    if(x!= Lines.get(i)) {
                        emptyRect(x, y, x, Lines.get(i));
                        y = Lines.get(i);
                    }
                }
                sss = !sss;

            }
        }
        Lines = new ArrayList<>();
    }

    public void quer(int x1, int y1, int x2, int y2){
        place(x1, y1);
        if((x2-x1)-(y2-y1)==0||(x2-x1)+(y2-y1)==0) {
            while(x1-x2!=0){
                if(x1-x2<0){
                    x1++;
                }else{
                    x1--;
                }
                if(y1-y2<0){
                    y1++;
                }else{
                    y1--;
                }
                place(x1, y1);
            }

        }else{
            System.out.println("quer problem! "+x1+ y1);
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
