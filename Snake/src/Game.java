import javax.sound.sampled.Port;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {
    //Graphic Options
    public int WindowsizeW = 40; //Wie viele Zellen es im Fenster gibt
    public int WindowsizeH = 22; //Wie viele Zellen es im Fenster gibt
    private static int Cellsize = 30; //Wie groß die Zellen sind
    private static int Spacesize = 6; //Wie groß der Abstand zwischen den Zellen ist
    private static int MaxH = 9000; //Wie viele Pixel groß das Fenster sein darf
    private static int MaxW = 18000; //Wie viele Pixel groß das Fenster sein darf
    private final JFrame frame;

    //Gameplay options
    public int ApplePower = 2; //Wie viele Körperteile pro Apfel generiert werden
    public int speed = 100; //Wie negativ proportional schnell das Spiel ist
    public boolean WandTod = true;
    public int StartLevel = 1;

    //Debugging stuff
    public static boolean grid = false; //Ob ein Raster angezeigt wird
    public static boolean spacer = false; //Ob eine Hilfslinie zum zählen der pixel existiert
    public static boolean CellState = false; //Ob permanent der Status vieler Zellen (bis 20 jeweils?) ausgegeben wird zum debuggen der Zellen selbst
    public static int SpawnApples = 50;


    public int Width = (Cellsize + Spacesize) * WindowsizeW + Spacesize;
    public int Height = (Cellsize + Spacesize) * WindowsizeH + Spacesize; //Wie groß das Fenster tatsächlich ist basierend auf der größe der Zellen und Abständen
    public Thread thread; //Der thread der die Hauptmethode ausführt
    public ArrayList<Entity> snake = new ArrayList<Entity>(0); //Die Arrayliste die den Zustand und die Länge der Schlange selbst speichert
    public ArrayList<Entity> blocks = new ArrayList<Entity>(0);
    public ArrayList<Entity> Apfel = new ArrayList<Entity>(0);
    public ArrayList<Entity> Portal = new ArrayList<Entity>(0);
    Color gre = new Color(0, 255, 32); //Die Farbe der Schlange
    public int tick = 0; //Nicht verwendet, eventuell zum speichern der Zeit                               FIX
    public String direction = "right"; //Die richtung in die die Schlange als nächstes geht
    public String lastdir = "right"; //Die Richtung in die die Schlagen zuletzt gegangen ist
    public int Growth = 2; //Wie groß die Schlange am Anfang ist, wird auch verwendet für wie viele Teile noch generiert werden sollen nachdem man einen Apfel gegessen hat
    public boolean running; //Ob das Spiel weitergeht oder nicht, soll false sein wenn es vorbei ist (man in sich selbst reinläuft)
    public String[][] Cells; //Welchen Zustand die Zellen haben (leer, besetzt, Apfel)
    public Random rand = new Random();
    public Level lvl = new Level(this);

    public boolean paused = false;
    public static String menuP = "leer";

    public Game(JFrame frame) {  //Setup
        this.frame = frame;
    }

    public void init() {
        while (Width > MaxW) {
            WindowsizeW--;
            Width = (Cellsize + Spacesize) * WindowsizeW + Spacesize;
        }
        while (Height > MaxH) {
            WindowsizeH--;
            Height = (Cellsize + Spacesize) * WindowsizeH + Spacesize;
        }

        frame.setSize(Width + 16, Height + 39);
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.getContentPane().add(this);
        frame.getContentPane().requestFocusInWindow();
        frame.getFocusableWindowState();
        frame.getContentPane();

        //setFocusable(true); //Ob das Fenster angeklickt werden kann
        frame.requestFocus();
        addKeyListener(this); //Damit die Knöpfe funktioneren
        Cells = new String[WindowsizeW + 2][WindowsizeH + 2]; //+2 damit das Spiel am Rand nicht abstürzt (1 links extra, 1 rechts extra)

        lvl.doIt(StartLevel);

        createBody(16, 12); //erzeugt ein Körperteil an den Koordinaten x, y
        createApple();

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

        gr.setColor(Color.BLUE);  //das folgende wird in rot gezeichnet
        for (int i = 0; i < Spacesize; i++) {
            gr.drawRect(0 + i, 0 + i, Width - 1 - 2 * i, Height - 1 - 2 * i);
        }

        gr.setColor(Color.BLACK);
        for (int i = 0; i < Portal.size(); i++) {
            if (Portal.get(i).x < 1) {
                gr.fillRect((Portal.get(i).x) * (Cellsize + Spacesize), (Portal.get(i).y - 1) * (Cellsize + Spacesize), Spacesize, Cellsize+Spacesize*2);
            }
            if (Portal.get(i).x > WindowsizeW) {
                gr.fillRect((Portal.get(i).x-1) * (Cellsize + Spacesize), (Portal.get(i).y - 1) * (Cellsize + Spacesize), Spacesize, Cellsize+Spacesize*2);
            }
            if (Portal.get(i).y < 1) {
                gr.fillRect((Portal.get(i).x-1) * (Cellsize + Spacesize), (Portal.get(i).y) * (Cellsize + Spacesize), Cellsize+Spacesize*2, Spacesize);
            }
            if (Portal.get(i).y > WindowsizeH) {
                gr.fillRect((Portal.get(i).x-1) * (Cellsize + Spacesize), (Portal.get(i).y-1) * (Cellsize + Spacesize), Cellsize+Spacesize*2, Spacesize);
            }
        }


        if (grid) { //Optionales Raster zum Debuggen
            gr.setColor(new Color(0, 245, 195));
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
            gr.setColor(new Color(0, 245, 195));
            for (int i = (Cellsize + Spacesize) * 10; i < Width; i = i + (Cellsize + Spacesize) * 10 - Spacesize) {
                for (int j = 1; j <= Spacesize; j++) {
                    gr.drawLine(0, i, Width, i);
                    i++;
                }
            }
        }

        //Schlangenteile Zeichnen
        gr.setColor(gre);
        for (int i = 0; i < snake.size(); i++) { //Elemente der Arrayliste durchlaufen

            gr.fillRect((snake.get(i).x - 1) * (Cellsize + Spacesize) + Spacesize, (snake.get(i).y - 1) * (Cellsize + Spacesize) + Spacesize, Cellsize, Cellsize); //Körper an richtiger Stelle zeichnen
        }
        gr.setColor(Color.BLUE);
        for (int i = 0; i < blocks.size(); i++) { //Elemente der Arrayliste durchlaufen
            gr.fillRect((blocks.get(i).x - 1) * (Cellsize + Spacesize) + Spacesize, (blocks.get(i).y - 1) * (Cellsize + Spacesize) + Spacesize, Cellsize, Cellsize); //Körper an richtiger Stelle zeichnen
        }

        gr.setColor(Color.RED);
        for (int i = 0; i < Apfel.size(); i++) { //Elemente der Arrayliste durchlaufen
            gr.fillRect((Apfel.get(i).x - 1) * (Cellsize + Spacesize) + Spacesize, (Apfel.get(i).y - 1) * (Cellsize + Spacesize) + Spacesize, Cellsize, Cellsize); //Körper an richtiger Stelle zeichnen
        }


    }


    public void run() {//Hauptmethode des Spiels
        boolean end = true;
        while(true) {
            while (running) {
                if (!paused) { //während man noch am leben ist

                    if (CellState) { //Ob die Zustände der Zellen zum Debuggen ausgegeben werden sollen
                        for (int i = 1; i < 21; i++) { //Alle Zellen bis 20 durchgehen
                            for (int j = 1; j < 21; j++) {
                                switch (Cells[j][i]) {

                                    case "leer" -> System.out.print("O"); //Wenn die Zelle Leer ist "O" ausgeben

                                    case "Body" -> System.out.print("X"); //Wenn da ein Körperteil drin is dann "X"
                                    case "Apple" -> System.out.print("A"); //Wenn da ein Apfel drin is dann "A"
                                    case "Block" -> System.out.println("H");
                                    default -> System.out.print("F");
                                }
                                System.out.print("|"); //Abstand zwischen Zellen zum leichteren lesen
                            }
                            System.out.println(); //Nächste Zeile
                        }
                        System.out.println("next"); //Nächster frame kennzeichnen
                    }


                    if (direction == "right") { //Richtung in die gegangen wird
                        if (Cells[snake.get(0).x + 1][snake.get(0).y].equals("leer") || Cells[snake.get(0).x + 1][snake.get(0).y].equals("Apple") || Cells[snake.get(0).x + 1][snake.get(0).y].equals("Dead")) { //Schauen die Zelle in der Richtung in die gegangen wird frei ist
                            Move(); //Alle Körperteile ausser Kopf nachrücken. Muss zuerst ausgeführt werden bevor sich der Kopf bewegt!
                            if (snake.get(0).x == WindowsizeW) { //Wenn der Kopf am rand ist
                                snake.get(0).x = 1; //Auf der Anderen Seite wieder raus
                            } else {
                                snake.get(0).x++; //ansonsten einfach in die richtung gehen
                            }
                            lastdir = "right"; //es wurde zuletzt nach rechts gegangen. siehe @InputBlocker
                        } else {
                            running = false;//wenn der Kopf blockiert ist, dann Spiel beenden
                            //endMethod();
                        }
                    }
                    if (direction == "left") {
                        if (Cells[snake.get(0).x - 1][snake.get(0).y].equals("leer") || Cells[snake.get(0).x - 1][snake.get(0).y].equals("Apple") || Cells[snake.get(0).x - 1][snake.get(0).y].equals("Dead")) {
                            Move();
                            if (snake.get(0).x == 1) {
                                snake.get(0).x = WindowsizeW;
                            } else {
                                snake.get(0).x--;
                            }
                            lastdir = "left";
                        } else {
                            running = false;
                            // endMethod();
                        }
                    }
                    if (direction == "up") {
                        if (Cells[snake.get(0).x][snake.get(0).y - 1].equals("leer") || Cells[snake.get(0).x][snake.get(0).y - 1].equals("Apple") || Cells[snake.get(0).x][snake.get(0).y - 1].equals("Dead")) {
                            Move();
                            if (snake.get(0).y == 1) {
                                snake.get(0).y = WindowsizeH;
                            } else {
                                snake.get(0).y--;
                            }
                            lastdir = "up";
                        } else {
                            running = false;
                            // endMethod();
                        }
                    }
                    if (direction == "down") {
                        if (Cells[snake.get(0).x][snake.get(0).y + 1].equals("leer") || Cells[snake.get(0).x][snake.get(0).y + 1].equals("Apple") || Cells[snake.get(0).x][snake.get(0).y + 1].equals("Dead")) {
                            Move();
                            if (snake.get(0).y == WindowsizeH) {
                                snake.get(0).y = 1;
                            } else {
                                snake.get(0).y++;
                            }
                            lastdir = "down";
                        } else {
                            running = false;
                            // endMethod();
                        }
                    }
                    if (Cells[snake.get(0).x][snake.get(0).y].equals("Apple")) {
                        Growth = Growth + ApplePower;
                        createApple();
                        for (int i = 0; i < Apfel.size(); i++) {
                            if (Apfel.get(i).x == snake.get(0).x && Apfel.get(i).y == snake.get(0).y) {
                                Apfel.remove(i);
                            }
                        }
                    }
                    Cells[snake.get(0).x][snake.get(0).y] = "Body"; //Die zelle in die gegangen wurde soll auf "besetzt" gesetzt werden

                    repaint();//Änderungen anzeigen
                }

                try { //try catch wird bei manchen methoden benötigt, ansonsten laufen die anscheinend nich.
                    thread.sleep(speed); //Wie schnell das Spiel ist: Alle (speed) millisekunden wird die Methode ausgeführt, aka 1/(speed) frames pro sekunde. Beispiel: speed=100 -> 10 Bilder pro sekunde
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (end  == true)
            {
                endMethod();
                end = false;
            }

        }


    }


    public void Move() {  //Die Körperteile nachrücken lassen
        if (Growth > 0) { //falls ein Apfel aufgehoben wurde bzw. die schlange noch wachsen soll
            snake.add(new Entity("Body", snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y)); //neues Glied erzeugen an der stelle des letzten gliedes
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
        if (key == KeyEvent.VK_SPACE) {
            paused = !paused;

        }

        if (!menuP.equals("leer") ) {
            if (key == KeyEvent.VK_S) {
                //starting

            }
            if (key == KeyEvent.VK_E) {
                frame.setVisible(false);
            }

        }

    }


    @Override
    public void keyReleased(KeyEvent e) { //wenn die taste losgelassen wird
    }


    @Override
    public void keyTyped(KeyEvent e) { //äh?
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_E) {
            running=true;
            paused = !paused;
            frame.getContentPane().revalidate();
            frame.getContentPane().removeAll();
            /*repaint();
            thread.start();*/
            //frame.setVisible(false);

        }
    }

    public void createBody(int X, int Y) { //hiermit erzeugt man ein neues Objekt des typs "Body". damit mach ich neue körperteile wenn die schlange wachsen soll
        snake.add(new Entity("Body", X, Y));//snake = die arrayliste. der liste wird ein neues glied gegeben das an den koordinaten (X|Y) erzeugt wird.
        Cells[X][Y] = "Body"; //die Zelle in dem der körper erzeugt wird soll blockiert sein
    }

    public void createApple() { //neuen Apfel erzeugen
        do {
            int ax;
            int ay;
            do {
                ax = rand.nextInt(WindowsizeW) + 1;  //mit zufälligen Koordinaten
                ay = rand.nextInt(WindowsizeH) + 1;
            } while (!Cells[ax][ay].equals("leer")); //wenn die Zelle besetzt ist dann nochmal würfeln
            Cells[ax][ay] = "Apple"; //die Zelle als Apfel markieren
            Apfel.add(new Entity("Apple", ax, ay));
            SpawnApples--;
        } while (SpawnApples > 0);
    }

    public void endMethod()
    {

        JLabel label = new JLabel("You Lost !!");
        label.setForeground(new Color(255,255,255));
        //label.setBackground(Color.pink);
        label.setSize(100,100);
        label.setLayout(null);
        label.setVisible(true);
        label.setBounds(450,300,200,100);
        label.setFont(new Font("DialogInput" ,Font.BOLD,20));

        JLabel label1 = new JLabel("To start the game please press S");
        label1.setForeground(new Color(255,255,255));
        //label.setBackground(Color.pink);
        label1.setSize(100,100);
        label1.setLayout(null);
        label1.setVisible(true);
        label1.setBounds(700,300,500,100);
        label1.setFont(new Font("DialogInput" ,Font.BOLD,20));

        JLabel label2 = new JLabel("To end the Game Press E");
        label2.setForeground(new Color(255,255,255));
        //label.setBackground(Color.pink);
        label2.setSize(100,100);
        label2.setLayout(null);
        label2.setVisible(true);
        label2.setBounds(700,350,500,100);
        label2.setFont(new Font("DialogInput" ,Font.BOLD,20));




        JPanel panel = new JPanel();
        //panel.setBounds(500,500,500,500);
        panel.setBackground(new Color(0,0,0));
        panel.setSize(100,100);
        panel.setVisible(true);
        panel.setLayout(null);
        frame.getContentPane().revalidate();
        frame.getContentPane().removeAll();

       // String menuP = "a";
        panel.add(label);
        panel.add(label1);
        panel.add(label2);
        frame.add(panel);

        /*KeyEvent ke = new KeyEvent(new Component() {}, 0, 0l, 0, 0);
        addKeyListener(this);
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_S)
        {

        }

        this.keyTyped(ke);*/



    }
}
