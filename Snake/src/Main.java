//DO: Apple spawn, pickup, Wait for input on start, Bestenliste? Multiplayer? Zeit/Speedrun? custom border? Portals? Levels? Enemies? Variable Speed? Special Pickups? Quests? Timer? Dash? Abilities?
//Blerghhh

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Canvas implements Runnable, KeyListener {

    //Graphic Options
    public static int WindowsizeW = 25; //Wie viele Zellen es im Fenster gibt
    public static int WindowsizeH = 10; //Wie viele Zellen es im Fenster gibt
    public static int Cellsize = 30; //Wie groß die Zellen sind
    public static int Spacesize = 6; //Wie groß der Abstand zwischen den Zellen ist
    public static int MaxH = 900; //Wie viele Pixel groß das Fenster sein darf
    public static int MaxW = 1800; //Wie viele Pixel groß das Fenster sein darf

    //Gameplay options
    public int ApplePower = 2; //Wie viele Körperteile pro Apfel generiert werden
    public int speed = 100; //Wie negativ proportional schnell das Spiel ist

    //Debugging stuff
    public static boolean grid = false; //Ob ein Raster angezeigt wird
    public static boolean spacer = false; //Ob eine Hilfslinie zum zählen der pixel existiert
    public static boolean CellState = false; //Ob permanent der Status vieler Zellen (bis 20 jeweils?) ausgegeben wird zum debuggen der Zellen selbst


    public static int Width = (Cellsize + Spacesize) * WindowsizeW + Spacesize;
    public static int Height = (Cellsize + Spacesize) * WindowsizeH + Spacesize; //Wie groß das Fenster tatsächlich ist basierend auf der größe der Zellen und Abständen
    public Thread thread; //Der thread der die Hauptmethode ausführt
    private ArrayList<Body> snake; //Die Arrayliste die den Zustand und die Länge der Schlange selbst speichert
    Color gre = new Color(0, 255, 32); //Die Farbe der Schlange
    public int tick = 0; //Nicht verwendet, eventuell zum speichern der Zeit                               FIX
    public String direction = "right"; //Die richtung in die die Schlange als nächstes geht
    public String lastdir = "right"; //Die Richtung in die die Schlagen zuletzt gegangen ist
    public int Growth = 2; //Wie groß die Schlange am Anfang ist, wird auch verwendet für wie viele Teile noch generiert werden sollen nachdem man einen Apfel gegessen hat
    public boolean running; //Ob das Spiel weitergeht oder nicht, soll false sein wenn es vorbei ist (man in sich selbst reinläuft)
    public String[][] Cells; //Welchen Zustand die Zellen haben (leer, besetzt, Apfel)
    public Random rand = new Random();
    int ax, ay;


    public static void main(String[] args) {
        while(Width>MaxW){
            WindowsizeW--;
            Width = (Cellsize + Spacesize) * WindowsizeW + Spacesize;
        }
        while(Height>MaxH){
            WindowsizeH--;
            Height = (Cellsize + Spacesize) * WindowsizeH + Spacesize;
        }
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
        Cells = new String[WindowsizeW + 2][WindowsizeH + 2]; //+2 damit das Spiel am Rand nicht abstürzt (1 links extra, 1 rechts extra)
        for (int i = 0; i < WindowsizeW + 2; i++) {
            for (int j = 0; j < WindowsizeH + 2; j++) {
                Cells[i][j] = "leer";
            }
        }
        createBody(5, 10); //erzeugt ein Körperteil an den Koordinaten x, y
        createApple();
        start(); //Startet das Spiel
    }


    public void start() {
        thread = new Thread(this); //neuer thread. verstehe nicht ganz genau wie das tatsächlich funktioniert.
        running = true; //das soll laufen (man ist am anfang nicht sofort gestorben
        thread.start(); //der thread soll anfangen seine aufgaben zu machen, das spiel wird jetzt gestartet
    }


    public void stop() {//ääähhhhhh... i guess der thread muss wissen was er zu machen hat wenn er vorbei is? oder man kann damit den thread beenden?
        running = false; //spiel wird angehalten
        try {
            thread.join(); //.... ganz ehrlich keine ahnung. soweit auch erstmal nich wichtig denk ich mal
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void paint(Graphics gr) { //zeichnet auf dem Hintergrund
        gr.setColor(Color.RED);  //das folgende wird in rot gezeichnet                                           
        for (int i = 0; i < Spacesize; i++) {
            gr.drawRect(0+i, 0+i , Width-1-2*i, Height-1-2*i);
        }


        if (grid) { //Optionales Raster zum Debuggen
            gr.setColor(Color.CYAN);
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

        if (spacer) { //Optionale Hilfslinien zum Zählen fürs Debuggen
            gr.setColor(Color.CYAN);
            for (int i = (Cellsize + Spacesize) * 10; i < Width; i = i + (Cellsize + Spacesize) * 10 - Spacesize) {
                for (int j = 1; j <= Spacesize; j++) {
                    gr.drawLine(0, i, Width, i);
                    i++;
                }
            }
        }

        gr.setColor(gre);  //Schlangenteile Zeichnen
        for (int i = 0; i < snake.size(); i++) { //Elemente der Arrayliste durchlaufen
            gr.fillRect((snake.get(i).getx() - 1) * (Cellsize + Spacesize) + Spacesize, (snake.get(i).gety() - 1) * (Cellsize + Spacesize) + Spacesize, Cellsize, Cellsize); //Körper an richtiger Stelle zeichnen
        }

        gr.setColor(Color.RED);
        gr.fillRect((ax - 1) * (Cellsize + Spacesize) + Spacesize, (ay - 1) * (Cellsize + Spacesize) + Spacesize, Cellsize, Cellsize);
    }


    public void run() { //Hauptmethode des Spiels
        while (running) { //während man noch am leben ist


            if (CellState) { //Ob die Zustände der Zellen zum Debuggen ausgegeben werden sollen
                for (int i = 1; i < 21; i++) { //Alle Zellen bis 20 durchgehen
                    for (int j = 1; j < 21; j++) {
                        switch (Cells[j][i]) {

                            case "leer" -> System.out.print("O"); //Wenn die Zelle Leer ist "O" ausgeben

                            case "Body" -> System.out.print("X"); //Wenn da ein Körperteil drin is dann "X"
                            case "Apple" -> System.out.print("A"); //Wenn da ein Apfel drin is dann "A"
                            default -> System.out.print("F");
                        }
                        System.out.print("|"); //Abstand zwischen Zellen zum leichteren lesen
                    }
                    System.out.println(); //Nächste Zeile
                }
                System.out.println("next"); //Nächster frame kennzeichnen
            }


            if (direction == "right") { //Richtung in die gegangen wird
                if (Cells[snake.get(0).x + 1][snake.get(0).y].equals("leer") || Cells[snake.get(0).x + 1][snake.get(0).y].equals("Apple")) { //Schauen die Zelle in der Richtung in die gegangen wird frei ist
                    Move(); //Alle Körperteile ausser Kopf nachrücken. Muss zuerst ausgeführt werden bevor sich der Kopf bewegt!
                    if (snake.get(0).x == WindowsizeW) { //Wenn der Kopf am rand ist
                        snake.get(0).x = 1; //Auf der Anderen Seite wieder raus
                    } else {
                        snake.get(0).x++; //ansonsten einfach in die richtung gehen
                    }
                    lastdir = "right"; //es wurde zuletzt nach rechts gegangen. siehe @InputBlocker
                } else {
                    running = false; //wenn der Kopf blockiert ist, dann Spiel beenden
                }
            }
            if (direction == "left") {
                if (Cells[snake.get(0).x - 1][snake.get(0).y].equals("leer") || Cells[snake.get(0).x - 1][snake.get(0).y].equals("Apple")) {
                    Move();
                    if (snake.get(0).x == 1) {
                        snake.get(0).x = WindowsizeW;
                    } else {
                        snake.get(0).x--;
                    }
                    lastdir = "left";
                } else {
                    running = false;
                }
            }
            if (direction == "up") {
                if (Cells[snake.get(0).x][snake.get(0).y - 1].equals("leer") || Cells[snake.get(0).x][snake.get(0).y - 1].equals("Apple")) {
                    Move();
                    if (snake.get(0).y == 1) {
                        snake.get(0).y = WindowsizeH;
                    } else {
                        snake.get(0).y--;
                    }
                    lastdir = "up";
                } else {
                    running = false;
                }
            }
            if (direction == "down") {
                if (Cells[snake.get(0).x][snake.get(0).y + 1].equals("leer") || Cells[snake.get(0).x][snake.get(0).y + 1].equals("Apple")) {
                    Move();
                    if (snake.get(0).y == WindowsizeH) {
                        snake.get(0).y = 1;
                    } else {
                        snake.get(0).y++;
                    }
                    lastdir = "down";
                } else {
                    running = false;
                }
            }
            if (Cells[snake.get(0).x][snake.get(0).y].equals("Apple")) {
                Growth = Growth + ApplePower;
                createApple();
            }
            Cells[snake.get(0).x][snake.get(0).y] = "Body"; //Die zelle in die gegangen wurde soll auf "besetzt" gesetzt werden
            repaint();//Änderungen anzeigen
            try { //try catch wird bei manchen methoden benötigt, ansonsten laufen die anscheinend nich.
                thread.sleep(speed); //Wie schnell das Spiel ist: Alle (speed) millisekunden wird die Methode ausgeführt, aka 1/(speed) frames pro sekunde. Beispiel: speed=100 -> 10 Bilder pro sekunde
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void Move() {  //Die Körperteile nachrücken lassen
        if (Growth > 0) { //falls ein Apfel aufgehoben wurde bzw. die schlange noch wachsen soll
            snake.add(new Body(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y)); //neues Glied erzeugen an der stelle des letzten gliedes
            Growth--;
        }

        for (int i = snake.size() - 1; i > 0; i--) {//Arrayliste durchlaufen
            if (i == snake.size() - 1) {//Falls es das letzte glied ist
                Cells[snake.get(i).x][snake.get(i).y] = "leer";//Die Zelle wieder freigeben damit sie nicht blockiert bleibt wenn der Körper sich weiter bewegt hat; damit man nicht an unsichtbaren Hindernissen stirbt
            }
            snake.get(i).x = snake.get(i - 1).x; //Die position wird die postion des vorausgehenden Gliedes
            snake.get(i).y = snake.get(i - 1).y;
        }
    }


    @Override
    public void keyPressed(KeyEvent k) { //wenn ein knopf gedrückt wird
        int key = k.getKeyCode(); // key ist hier der name für den "KeyListener". er sagt mir ob und welcher knopf wie gedrückt wird
        if (key == KeyEvent.VK_RIGHT && !direction.equals("left") && !lastdir.equals("left")) { //"VK_RIGHT" ist die rechte pfeiltaste, ich hab hier nen InputBlocker reingetan damit die schlange nicht nach rechts gehen kann wenn sie davor nach links gegangen ist (sie soll nicht wieder in sich selbst reingehen können)
            direction = "right"; //die schlange soll als nächstes nach rechts gehen
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
    public void keyReleased(KeyEvent e) { //wenn die taste losgelassen wird
    }


    @Override
    public void keyTyped(KeyEvent e) { //äh?
    }

    public void createBody(int X, int Y) { //hiermit erzeugt man ein neues Objekt des typs "Body". damit mach ich neue körperteile wenn die schlange wachsen soll
        snake.add(new Body(X, Y));//snake = die arrayliste. der liste wird ein neues glied gegeben das an den koordinaten (X|Y) erzeugt wird.
        Cells[X][Y] = "Body"; //die Zelle in dem der körper erzeugt wird soll blockiert sein
    }

    public void createApple() { //neuen Apfel erzeugen
        do {
            ax = rand.nextInt(WindowsizeW) + 1;  //mit zufälligen Koordinaten
            ay = rand.nextInt(WindowsizeH) + 1;
        } while (!Cells[ax][ay].equals("leer")); //wenn die Zelle besetzt ist dann nochmal würfeln
        Cells[ax][ay] = "Apple"; //die Zelle als Apfel markieren
    }
}
