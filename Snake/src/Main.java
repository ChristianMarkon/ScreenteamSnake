//DO: Apple spawn, pickup, Wait for input on start, Bestenliste? Multiplayer? Zeit/Speedrun? custom border? Portals? Levels? Enemies? Variable Speed? Special Pickups? Quests? Timer? Dash? Abilities?
//Blerghhh

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Main extends Canvas implements Runnable, KeyListener {

    //Graphic Options
    public static int Windowsize =50; //Wie viele Zellen es im Fenster gibt                              FIX
    public static int Cellsize = 10; //Wie groß die Zellen sind
    public static int Spacesize = 2; //Wie groß der Abstand zwischen den Zellen ist

    //Gameplay options
    public int ApplePower = 2; //Wie viele Körperteile pro Apfel generiert werden
    public int speed = 100; //Wie negativ proportional schnell das Spiel ist

    //Debugging stuff
    public static boolean grid = false; //Ob ein Raster angezeigt wird
    public static boolean spacer = false; //Ob eine Hilfslinie zum zählen der pixel existiert
    public static boolean CellState = false; //Ob permanent der Status vieler Zellen (bis 20 jeweils?) ausgegeben wird zum debuggen der Zellen selbst


    public static int Width = (Cellsize + Spacesize) * Windowsize + Spacesize, Height = Width; //Wie groß das Fenster tatsächlich ist basierend auf der größe der Zellen und Abständen
    public Thread thread; //Der thread der die Hauptmethode ausführt
    private ArrayList<Body> snake; //Die Arrayliste die den Zustand und die Länge der Schlange selbst speichert
    Color gre = new Color(0, 255, 32); //Die Farbe der Schlange
    public int tick = 0; //Nicht verwendet, eventuell zum speichern der Zeit                               FIX
    public String direction = "right"; //Die richtung in die die SChlange als nächstes geht
    public String lastdir = "right"; //Die Richtung in die die Schlagen zuletzt gegangen ist
    public int Growth = 15; //Wie groß die Schlange am Anfang ist, wird auch verwendet für wie viele Teile noch generiert werden sollen nachdem man einen Apfel gegessen hat
    public boolean running; //Ob das Spiel weitergeht oder nicht, soll false sein wenn es vorbei ist (man in sich selbst reinläuft)
    public String[][] Cells; //Welchen Zustand die Zellen haben (leer, besetzt, Apfel)


    public static void main(String[] args) {
        Main can = new Main();
        JFrame frame = new JFrame("SNAAAAAAKE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Width + 16, Height + 39);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().add(can);
        frame.setVisible(true);
    }


    public Main() {  //Setup
        setFocusable(true); //Ob das Fenster angeklickt werden kann
        addKeyListener(this); //Damit die Knöpfe funktioneren
        snake = new ArrayList<Body>(3); //Arrayliste
        Cells = new String[Windowsize + 2][Windowsize + 2]; //+2 damit das Spiel am Rand nicht abstürzt (1 links extra, 1 rechts extra)
        create(5, 10); //erzeugt ein Körperteil an den Koordinaten x, y
        start(); //Startet das Spiel
    }



    @Override
    public void paint(Graphics gr) {
        gr.setColor(Color.RED);
        for (int i = 0; i < Spacesize; i++) {
            gr.drawLine(0, 0 + i, Width, 0 + i);
            gr.drawLine(0 + i, 0, 0 + i, Height);
            gr.drawLine(Width - i, 0, Width - i, Height);
            gr.drawLine(0, Height - i, Width, Height - i);
        }


        gr.setColor(Color.RED);
        if (grid) {
            for (int i = Cellsize + Spacesize; i < Width; i = i + Cellsize) {
                for (int j = 1; j <= Spacesize; j++) {
                    gr.drawLine(0, i, Width, i);
                    i++;
                }
            }
            for (int i = Cellsize + Spacesize; i < Height; i = i + Cellsize) {
                for (int j = 1; j <= Spacesize; j++) {
                    gr.drawLine(i, 0, i, Height);
                    i++;
                }
            }
        }

        if (spacer) {
            gr.setColor(Color.CYAN);
            for (int i = (Cellsize + Spacesize) * 10; i < Width; i = i + (Cellsize + Spacesize) * 10 - Spacesize) {
                for (int j = 1; j <= Spacesize; j++) {
                    gr.drawLine(0, i, Width, i);
                    i++;
                }
            }
        }

        gr.setColor(gre);
        for (int i = 0; i < snake.size(); i++) {
            gr.fillRect((snake.get(i).getx() - 1) * (Cellsize + Spacesize) + Spacesize, (snake.get(i).gety() - 1) * (Cellsize + Spacesize) + Spacesize, Cellsize, Cellsize);
        }
    }




    public void run() {
        while (running) {
            if (CellState) {
                for (int i = 1; i < 20; i++) {
                    for (int j = 1; j < 20; j++) {
                        if (Cells[i][j] == null) {
                            System.out.print("O");
                        } else {
                            System.out.print("X");
                        }
                        System.out.print("|");
                    }
                    System.out.println();
                }
                System.out.println("next");
            }


            if (direction == "right") {
                if (Cells[snake.get(0).x + 1][snake.get(0).y] == null) {
                    Move();
                    if (snake.get(0).x == 50) {
                        snake.get(0).x = 1;
                    } else {
                        snake.get(0).x++;
                    }
                    lastdir = "right";
                } else {
                    running = false;
                }
            }
            if (direction == "left") {
                if (Cells[snake.get(0).x - 1][snake.get(0).y] == null) {
                    Move();
                    if (snake.get(0).x == 1) {
                        snake.get(0).x = 50;
                    } else {
                        snake.get(0).x--;
                    }
                    lastdir = "left";
                } else {
                    running = false;
                }
            }
            if (direction == "up") {
                if (Cells[snake.get(0).x][snake.get(0).y-1] == null) {
                    Move();
                    if (snake.get(0).y == 1) {
                        snake.get(0).y = 50;
                    } else {
                        snake.get(0).y--;
                    }
                    lastdir = "up";
                } else {
                    running = false;
                }
            }
                if (direction == "down") {
                    if (Cells[snake.get(0).x][snake.get(0).y+1] == null) {
                        Move();
                        if (snake.get(0).y == 50) {
                            snake.get(0).y = 1;
                        } else {
                            snake.get(0).y++;
                        }
                        lastdir = "down";
                    } else{
                        running=false;
                    }
                }
                Cells[snake.get(0).x][snake.get(0).y] = "Body";
                repaint();
                try {
                    thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }





        public void Move(){
            if (Growth > 0) {
                snake.add(new Body(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y));
                Growth--;
            }
            for (int i = snake.size() - 1; i > 0; i--) {
                if (i == snake.size() - 1) {
                    Cells[snake.get(i).x][snake.get(i).y] = null;
                }
                snake.get(i).x = snake.get(i - 1).x;
                snake.get(i).y = snake.get(i - 1).y;
            }
        }

        public void start () {
            thread = new Thread(this);
            running = true;
            thread.start();
        }

        public void stop () {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        @Override
        public void keyPressed (KeyEvent k){
            int key = k.getKeyCode();
            if (key == KeyEvent.VK_RIGHT && !direction.equals("left") && !lastdir.equals("left")) {
                direction = "right";
            }
            if (key == KeyEvent.VK_LEFT && !direction.equals("right") && !lastdir.equals("right")) {
                direction = "left";
            }
            if (key == KeyEvent.VK_UP && !direction.equals("down") && !lastdir.equals("down")) {
                direction = "up";
            }
            if (key == KeyEvent.VK_DOWN && !direction.equals("up") && !lastdir.equals("up")) {
                direction = "down";
            }
        }



        @Override
        public void keyReleased (KeyEvent e){
        }


        @Override
        public void keyTyped (KeyEvent e){
        }

        public void create ( int X, int Y){
            snake.add(new Body(X, Y));
            Cells[X][Y] = "Body";
        }
    }
