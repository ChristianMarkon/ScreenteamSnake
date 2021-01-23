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
        Req = 15;
        if (gam.WandTod) {
            emptyRect(0, 0, gam.WindowsizeW + 1, gam.WindowsizeH + 1);
        }

        if (number == 1) { //Players unknown Playground
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
            deadzone(28, 19);
            deadzone(28, 17);
            delEmptyRect(0,0,41,26);
            start = new Entity(11, 12);
        }

        if (number == 2) {//Squiggles
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
            delEmptyRect(0,0,41,26);
        }

        if (number == 3) {//Cave 1
            fillRect(1, 1, 40, 25);
            delFillRect(8, 3, 15, 5);
            delFillRect(11, 6, 12, 13);
            delFillRect(3, 9, 6, 18);
            delFillRect(7, 14, 7, 18);
            delFillRect(8, 14, 15, 20);
            delFillRect(16, 16, 24, 17);
            delFillRect(25, 13, 31, 22);
            delFillRect(32, 13, 37, 17);
            delFillRect(29, 12, 30, 9);
            delFillRect(29, 6, 36, 8);
            delFillRect(26, 3, 31, 7);
            delFillRect(21, 6, 25, 7);
            delFillRect(17, 8, 22, 10);
            delEmptyRect(16,3, 20 , 3);
            delEmptyRect(20,4, 25 , 4);

            start = new Entity(3, 16);
        }

        if (number == 4) { //Formen
            fillRect(3, 4, 5, 6);
            place(3, 3);
            emptyRect(14, 21, 19, 24);
            delEmptyRect(15, 21, 19, 21);
            Lines = Arrays.asList(4, 9, 4, 12, 7, 15, 11);
            DrawLines();
            emptyRect(11, 3, 11, 6);
            emptyRect(17, 3, 23, 3);
            emptyRect(34, 2, 35, 3);
            emptyRect(15, 7, 16, 8);
            Lines = Arrays.asList(27, 6, 31, 11);
            DrawLines();
            emptyRect(25, 9, 27, 10);
            emptyRect(35, 7, 39, 8);
            fillRect(19, 12, 21, 14);
            Lines = Arrays.asList(17, 18, 21, 15);
            DrawLines();
            emptyRect(29, 13, 29, 15);
            place(30, 15);
            fillRect(35, 14, 37, 16);
            place(7, 19);
            Lines = Arrays.asList(8, 20, 6, 23, 8, 24);
            DrawLines();
            Lines = Arrays.asList(24, 21, 24, 23, 28, 20);
            DrawLines();
            emptyRect(32, 21, 34, 22);

            start = new Entity(6, 7);
            delEmptyRect(0,0,41,26);
        }

        if (number == 5) {//Snek
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
            deadFillRect(12, 5, 18, 4);
            deadzone(14,3);
            deadzone(16, 3);
            delEmptyRect(0,0,41,26);
            start = new Entity(24, 4);
        }

        if(number==6){//Cave 2
            start = new Entity(27, 12);

            emptyRect(4, 2, 11, 9);
            delEmptyRect(11, 7, 11, 9);
            del(10, 9);
            Lines = Arrays.asList(12, 2, 12, 6);
            DrawLines();
            emptyRect(2, 11, 8, 19);
            delEmptyRect(8, 12, 8, 13);
            Lines = Arrays.asList(3, 2, 3, 10);
            DrawLines();
            Lines = Arrays.asList(4, 10, 9, 10);
            DrawLines();
            place(9, 11);
            fillRect(3, 19, 4, 24);
            fillRect(6, 19, 15, 23);
            fillRect(8, 14, 11, 18);
            fillRect(13, 12, 15, 15);
            Lines = Arrays.asList(12, 14, 12, 15);
            DrawLines();
            fillRect(16, 19, 37, 20);
            fillRect(21, 14, 24, 19);
            fillRect(28, 14, 37, 18);
            fillRect(17, 22, 38, 24);
            del(5, 19);
            place(37, 13);
            fillRect(39, 12, 40, 24);
            fillRect(37, 3, 40, 11);
            fillRect(1, 1, 40, 2);
            fillRect(1, 1, 2, 25);
            fillRect(1, 25, 40, 25);
            Lines = Arrays.asList(16, 3, 22, 3);
            DrawLines();
            fillRect(25, 3, 26, 4);
            fillRect(22, 5, 26, 6);
            fillRect(16, 6, 18, 8);
            fillRect(12, 9, 21, 10);
            fillRect(30, 6, 36, 7);
            fillRect(30, 8, 33, 11);
            fillRect(19, 11, 21, 12);
        }

        if (number == 7) {//Kinda Sus
            start = new Entity(1, 5);
            Lines = Arrays.asList(11, 10, 11, 18);
            DrawLines();
            place(12, 10);
            Lines = Arrays.asList(12, 9, 14, 9);
            DrawLines();
            place(14, 8);
            Lines = Arrays.asList(15, 6, 15, 7);
            DrawLines();
            place(16, 5);
            Lines = Arrays.asList(17, 4, 21, 4);
            DrawLines();
            Lines = Arrays.asList(22, 5, 23, 5);
            DrawLines();
            Lines = Arrays.asList(24, 6, 24, 7);
            DrawLines();
            place(25, 8);
            Lines = Arrays.asList(26, 9, 26, 10);
            DrawLines();
            Lines = Arrays.asList(25, 13, 25, 19);
            DrawLines();
            emptyRect(21, 20, 24, 22);
            delEmptyRect(22, 20, 23, 20);
            Lines = Arrays.asList(19, 19, 21, 19);
            DrawLines();
            place(20, 20);
            fillRect(18, 20, 19, 21);
            Lines = Arrays.asList(15, 23, 18, 23);
            DrawLines();
            Lines = Arrays.asList(14, 19, 14, 22);
            DrawLines();
            place(15, 22);
            place(13, 19);
            Lines = Arrays.asList(12, 18, 12, 19);
            DrawLines();

            Lines = Arrays.asList(14, 11, 14, 17);
            DrawLines();

            quer(19, 7, 17, 9);
            quer(17, 10, 20, 13);
            Lines = Arrays.asList(20, 7, 22, 7);
            DrawLines();
            Lines = Arrays.asList(21, 13, 22, 13);
            DrawLines();
            Lines = Arrays.asList(23, 12, 24, 12);
            DrawLines();
            delEmptyRect(0,0,41,26);
        }

        if (number == 8) {//Snake
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
            deadzone(11, 11);
            deadzone(12, 11);
            deadzone(13, 12);
            deadzone(14, 12);
            deadzone(15, 13);
            deadzone(8, 18);
            deadzone(9, 19);
            deadzone(12, 16);
            deadzone(12, 17);
            deadzone(13, 17);
            deadzone(13, 18);
            deadzone(14, 6);
            deadzone(15, 6);
            deadzone(16, 6);
            deadzone(14, 7);
            deadzone(15, 7);
            deadzone(16, 7);
            deadzone(17, 7);
            deadzone(15, 8);
            deadzone(16, 8);
            deadzone(17, 8);
            deadzone(18, 8);
            deadzone(17, 9);
            deadzone(18, 9);
            deadzone(19, 9);
            deadzone(18, 10);
            deadzone(19, 10);
            deadzone(20, 10);
            deadzone(19, 11);
            deadzone(20, 11);
            deadzone(20, 12);
            deadzone(20, 13);
            deadzone(21, 14);
            deadzone(21, 15);
            start = new Entity(25, 6);
            delEmptyRect(0,0,41,26);
        }

        if (number == 9) { //Credits Chris, Abdul
            quer(5, 2, 3, 4);
            quer(3, 6, 5, 8);
            quer(6, 8, 7, 7);
            place(3, 5);
            quer(6, 2, 7, 3);
            fillRect(11, 3, 11, 7);
            fillRect(14, 3, 14, 7);
            fillRect(12, 5, 13, 5);
            fillRect(18, 3, 18, 8);
            fillRect(19, 3, 21, 3);
            fillRect(21, 4, 21, 5);
            fillRect(19, 5, 20, 5);
            quer(19, 6, 21, 8);
            fillRect(26, 3, 26, 8);
            fillRect(31, 2, 34, 2);
            fillRect(31, 3, 31, 5);
            fillRect(32, 5, 34, 5);
            fillRect(34, 6, 34, 8);
            fillRect(33, 8, 31, 8);


            place(6, 15);
            fillRect(5, 16, 5, 17);
            fillRect(4, 18, 4, 19);
            fillRect(3, 20, 3, 20);
            fillRect(7, 16, 7, 17);
            fillRect(8, 18, 8, 19);
            place(9, 20);
            fillRect(5, 18, 7, 18);
            fillRect(13, 15, 13, 21);
            fillRect(14, 15, 15, 15);
            fillRect(14, 18, 15, 18);
            fillRect(14, 21, 15, 21);
            fillRect(16, 16, 16, 20);
            fillRect(20, 12, 20, 18);
            place(24, 15);
            place(21, 12);
            place(21, 18);
            quer(22, 12, 24, 14);
            quer(22, 18, 24, 16);

            fillRect(27, 16, 27, 20);
            fillRect(28, 21, 30, 21);
            fillRect(31, 16, 31, 20);

            fillRect(34, 11, 34, 16);
            fillRect(35, 16, 38, 16);

            deadzone(6, 16);
            deadzone(6, 17);
            deadzone(14, 16);
            deadzone(14, 17);
            deadzone(15, 16);
            deadzone(15, 17);
            deadzone(14, 19);
            deadzone(14, 20);
            deadzone(15, 19);
            deadzone(14, 20);
            deadzone(15, 20);
            deadzone(21, 13);
            deadzone(21, 14);
            deadzone(21, 15);
            deadzone(21, 16);
            deadzone(21, 17);
            deadzone(22, 13);
            deadzone(22, 14);
            deadzone(22, 15);
            deadzone(22, 16);
            deadzone(22, 17);
            deadzone(23, 14);
            deadzone(23, 15);
            deadzone(19, 4);
            deadzone(20, 4);


            start = new Entity(10, 11);
        }

        if (number == 10) { //Credits Sahani Boki

            emptyRect(4, 3, 7, 6);
            delEmptyRect(7, 4, 7, 5);
            emptyRect(4, 6, 7, 9);
            delEmptyRect(4, 7, 4, 8);

            quer(12, 6, 11, 7);
            quer(11, 8, 10, 9);
            quer(10, 10, 9, 11);
            fillRect(13, 7, 13, 7);
            quer(13, 8, 14, 9);
            quer(14, 10, 15, 11);
            fillRect(11, 9, 13, 9);

            fillRect(17, 2, 17, 8);
            fillRect(20, 2, 20, 8);
            fillRect(18, 5, 19, 5);

            quer(25, 6, 24, 7);
            quer(24, 8, 23, 9);
            quer(23, 10, 22, 11);
            fillRect(26, 7, 26, 7);
            quer(26, 8, 27, 9);
            quer(27, 10, 28, 11);
            fillRect(24, 9, 26, 9);

            fillRect(30, 2, 30, 7);
            fillRect(34, 2, 34, 7);
            quer(31, 3, 32, 4);
            quer(32, 5, 33, 6);
            place(31, 2);
            place(33, 7);

            fillRect(37, 6, 37, 11);

            emptyRect(5, 14, 8, 20);
            del(8, 20);
            del(8, 14);
            fillRect(6, 17, 7, 17);

            fillRect(14, 18, 14, 22);
            fillRect(15, 22, 17, 23);
            fillRect(18, 18, 18, 22);
            fillRect(15, 17, 17, 17);

            fillRect(24, 15, 24, 22);
            quer(25, 18, 28, 15);
            quer(25, 19, 28, 22);

            fillRect(33, 13, 33, 20);

            deadzone(12, 7);
            deadzone(12, 8);
            deadzone(25, 7);
            deadzone(25, 8);
            deadzone(31, 4);
            deadzone(31, 5);
            deadzone(33, 5);
            deadzone(33, 4);
            deadzone(6, 15);
            deadzone(6, 16);
            deadzone(7, 15);
            deadzone(7, 16);
            deadzone(6, 18);
            deadzone(6, 19);
            deadzone(7, 18);
            deadzone(7, 19);
            deadzone(15, 18);
            deadzone(15, 19);
            deadzone(15, 20);
            deadzone(15, 21);
            deadzone(16, 18);
            deadzone(16, 19);
            deadzone(16, 20);
            deadzone(16, 21);
            deadzone(17, 18);
            deadzone(17, 19);
            deadzone(17, 20);
            deadzone(17, 21);
            deadzone(25, 17);
            deadzone(25, 20);
            delEmptyRect(0,0,41,26);

            start = new Entity(3, 12);
        }

        if(number == 11)
        {
            fillRect(19,3,21,3);
            fillRect(22,2,23,2);
            fillRect(24,3,26,3);
            fillRect(27,4,28,4);
            quer(29,5,31,7);
            quer(32,7,34,9);
            fillRect(33,11,35,11);
            fillRect(27,10,32,10);
            quer(28,10,26,12);
            quer(24,9,26,11);
            deadzone(25,11);

            fillRect(25,12,22,12);
            fillRect(19,11,21,11);
            fillRect(11,12,18,12);
            place(15,11);
            place(10,11);
            fillRect(8,11,7,10);
            del(8,10);
            deadzone(8,10);

            fillRect(8,9,10,9);
            fillRect(11,7,11,8);
            fillRect(10,5,10,6);
            place(12,5);
            fillRect(13,6,14,6);
            fillRect(15,5,16,5);
            fillRect(17,4,18,4);

            place(13,9);
            place(16,9);

            quer(20,5,18,5);
            fillRect(21,6,21,8);
            deadzone(20,6);

            quer(7, 15, 6, 16);
            quer(6, 17, 5, 18);
            quer(5, 19, 4, 20);
            fillRect(8, 16, 8, 18);
            place(9,18);
            quer(9, 19, 10, 20);
            fillRect(6, 18, 7, 18);
            deadEmptyRect(7,16,7,17);

            fillRect(14,18,15,19);
            fillRect(14,20,14,23);
            fillRect(16,20,16,21);
            fillRect(17,22,18,23);
            fillRect(18,18,18,22);
            deadEmptyRect(15,20,15,21);
            deadEmptyRect(17,20,17,21);

            fillRect(23,15,24,16);
            fillRect(23,17,23,20);
            fillRect(25,17,25,18);
            fillRect(26,19,27,20);
            fillRect(27,18,27,15);
            deadEmptyRect(25,17,26,18);
            deadEmptyRect(25,17,26,18);

            quer(33, 18, 32, 19);
            quer(32, 20, 31, 21);
            quer(31, 22, 30, 23);
            fillRect(34, 19, 34, 20);
            fillRect(35,21,35,22);
            place(36,23);
            fillRect(32, 21, 34, 21);
            deadEmptyRect(33,19,33,20);
        }

        if (number >= 12) {//PacMan
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
            deadEmptyRect(1, 10, 4, 10);
            deadEmptyRect(1, 15, 4, 15);
            deadEmptyRect(37, 10, 40, 10);
            deadEmptyRect(37, 15, 40, 15);
            start = new Entity(17, 12);
            Req = 250;

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

    public void deadEmptyRect(int x1, int y1, int x2, int y2) {

        for (int i = x1; i <= x2; i++) {
            deadzone(i, y1);
            if (y1 != y2) {
                deadzone(i, y2);
            }
        }
        if (y2 - y1 > 0) {
            for (int i = y1 + 1; i < y2; i++) {
                deadzone(x1, i);
                if (x1 != x2) {
                    deadzone(x2, i);
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

    public void deadFillRect(int x1, int y1, int x2, int y2) {
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
                deadzone(i, j);

            }
        }


    }

    public void deadzone(int x, int y) {
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
