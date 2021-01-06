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


    //Gameplay options
    public int ApplePower = 2; //Wie viele Körperteile pro Apfel generiert werden
    public int speed = 100; //Wie negativ proportional schnell das Spiel ist
    public boolean WandTod = true;
    public int StartLevel = 0;

    //Debugging stuff
    public static boolean grid = false; //Ob ein Raster angezeigt wird
    public static boolean spacer = false; //Ob eine Hilfslinie zum zählen der pixel existiert
    public static boolean CellState = false; //Ob permanent der Status vieler Zellen (bis 20 jeweils?) ausgegeben wird zum debuggen der Zellen selbst
    public static int SpawnApples;

    public int WindowsizeW; //Wie viele Zellen es im Fenster gibt
    public int WindowsizeH; //Wie viele Zellen es im Fenster gibt
    private int Cellsize; //Wie groß die Zellen sind
    private int Spacesize; //Wie groß der Abstand zwischen den Zellen ist
    public int Width;
    public int Height; //Wie groß das Fenster tatsächlich ist basierend auf der größe der Zellen und Abständen
    private final JFrame frame;
    Menu back;
    public Thread thread; //Der thread der die Hauptmethode ausführt
    public ArrayList<Entity> snake; //Die Arrayliste die den Zustand und die Länge der Schlange selbst speichert
    public ArrayList<Entity> blocks;
    public ArrayList<Entity> Apfel;
    public ArrayList<Entity> Portal;
    Color gre = new Color(0, 255, 32); //Die Farbe der Schlange
    //public int tick = 0; //Nicht verwendet, eventuell zum speichern der Zeit                               FIX
    public String direction; //Die richtung in die die Schlange als nächstes geht
    public String lastdir; //Die Richtung in die die Schlagen zuletzt gegangen ist
    public int Growth; //Wie groß die Schlange am Anfang ist, wird auch verwendet für wie viele Teile noch generiert werden sollen nachdem man einen Apfel gegessen hat
    public boolean running; //Ob das Spiel weitergeht oder nicht, soll false sein wenn es vorbei ist (man in sich selbst reinläuft)
    public String[][] Cells; //Welchen Zustand die Zellen haben (leer, besetzt, Apfel)
    public Random rand = new Random();
    public Level lvl = new Level(this);
    boolean end;

    public boolean paused;
    public static String menuP = "leer";
    public static Graphics gr;

    public Game(JFrame frame, int WsW, int WsH, int Cs, int Ss, Menu b) {  //Setup
        back = b;
        this.frame = frame;
        WindowsizeW = WsW; //Wie viele Zellen es im Fenster gibt
        WindowsizeH = WsH; //Wie viele Zellen es im Fenster gibt
        Cellsize = Cs; //Wie groß die Zellen sind
        Spacesize = Ss;
        Width = (Cellsize + Spacesize) * WindowsizeW + Spacesize;
        Height = (Cellsize + Spacesize) * WindowsizeH + Spacesize;
        addKeyListener(this); //Damit die Knöpfe funktioneren
        frame.requestFocus();
    }

    public void init() {
       /* while (Width > MaxW) {
            WindowsizeW--;
            Width = (Cellsize + Spacesize) * WindowsizeW + Spacesize;
        }
        while (Height > MaxH) {
            WindowsizeH--;
            Height = (Cellsize + Spacesize) * WindowsizeH + Spacesize;
        }*/
        snake = new ArrayList<Entity>(0); //Die Arrayliste die den Zustand und die Länge der Schlange selbst speichert
        blocks = new ArrayList<Entity>(0);
        Apfel = new ArrayList<Entity>(0);
        Portal = new ArrayList<Entity>(0);
        paused = false;
        running = true;
        paused = false;
        Growth = 2;
        end = true;
        direction = "right";
        lastdir = "right";
        SpawnApples = 50;
        frame.setSize(Width + 16, Height + 39);
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.getContentPane().add(this);
        frame.getContentPane().requestFocusInWindow();
        frame.getContentPane();
        //setFocusable(true); //Ob das Fenster angeklickt werden kann
        frame.requestFocus();
        Cells = new String[WindowsizeW + 2][WindowsizeH + 2]; //+2 damit das Spiel am Rand nicht abstürzt (1 links extra, 1 rechts extra)
        lvl.doIt(StartLevel);
        createBody(lvl.start.getx(), lvl.start.gety()); //erzeugt ein Körperteil an den Koordinaten x, y
        createApple();
        frame.requestFocus();

    }


    public void start() {
        thread = new Thread(this); //neuer thread. verstehe nicht ganz genau wie das tatsächlich funktioniert.
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
                gr.fillRect((Portal.get(i).x) * (Cellsize + Spacesize), (Portal.get(i).y - 1) * (Cellsize + Spacesize), Spacesize, Cellsize + Spacesize * 2);
            }
            if (Portal.get(i).x > WindowsizeW) {
                gr.fillRect((Portal.get(i).x - 1) * (Cellsize + Spacesize), (Portal.get(i).y - 1) * (Cellsize + Spacesize), Spacesize, Cellsize + Spacesize * 2);
            }
            if (Portal.get(i).y < 1) {
                gr.fillRect((Portal.get(i).x - 1) * (Cellsize + Spacesize), (Portal.get(i).y) * (Cellsize + Spacesize), Cellsize + Spacesize * 2, Spacesize);
            }
            if (Portal.get(i).y > WindowsizeH) {
                gr.fillRect((Portal.get(i).x - 1) * (Cellsize + Spacesize), (Portal.get(i).y - 1) * (Cellsize + Spacesize), Cellsize + Spacesize * 2, Spacesize);
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
  /*      gr.setColor(Color.YELLOW);
gr.drawString("Fuck this", 100, 100);*/
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

        while (true) {
                if (!paused&&running) { //während man noch am leben ist

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
            if (end&&!running) {
                end=false;
                endMethod();
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

            if (key == KeyEvent.VK_E) {
                System.exit(0);

            }
            if (key == KeyEvent.VK_S) {

                init();

            }


    }


    @Override
    public void keyReleased(KeyEvent e) { //wenn die taste losgelassen wird
    }


    @Override
    public void keyTyped(KeyEvent e) { //äh?

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

    public void endMethod() {

        JLabel label = new JLabel("You Lost !!");
        label.setForeground(new Color(255, 255, 255));
        //label.setBackground(Color.pink);
        label.setSize(100, 100);
        label.setLayout(null);
        label.setVisible(true);
        label.setBounds(450, 300, 200, 100);
        label.setFont(new Font("DialogInput", Font.BOLD, 20));

        JLabel label1 = new JLabel("To start the game please press S");
        label1.setForeground(new Color(255, 255, 255));
        //label.setBackground(Color.pink);
        label1.setSize(100, 100);
        label1.setLayout(null);
        label1.setVisible(true);
        label1.setBounds(700, 300, 500, 100);
        label1.setFont(new Font("DialogInput", Font.BOLD, 20));

        JLabel label2 = new JLabel("To end the Game Press E");
        label2.setForeground(new Color(255, 255, 255));
        //label.setBackground(Color.pink);
        label2.setSize(100, 100);
        label2.setLayout(null);
        label2.setVisible(true);
        label2.setBounds(700, 350, 500, 100);
        label2.setFont(new Font("DialogInput", Font.BOLD, 20));


        JPanel panel = new JPanel();
        //panel.setBounds(500,500,500,500);
        panel.setBackground(new Color(0, 0, 0));
        panel.setSize(getParent().getSize());
        panel.setVisible(true);
        panel.setLayout(null);
        frame.getContentPane().revalidate();
        frame.getContentPane().removeAll();

        // String menuP = "a";
        panel.add(label);
        panel.add(label1);
        panel.add(label2);
        panel.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        frame.add(panel);
        frame.requestFocus();

        KeyEvent ke = new KeyEvent(new Component() {
        }, 0, 0l, 0, 0);
        frame.addKeyListener(this);
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_S) {
            this.keyPressed(new KeyEvent(new Component() {
            }, 0, 0l, 0, key));
        }
        if (key == KeyEvent.VK_E) {
            this.keyPressed(new KeyEvent(new Component() {
            }, 0, 0l, 0, key));
        }


    }
}
