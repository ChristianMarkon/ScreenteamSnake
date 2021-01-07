import javax.swing.*;
import java.awt.*;
import java.awt.Font;

public class Menu extends Panel {

    JPanel titleNamePanel;
    JLabel title;
    Game game;
    JFrame frame;
    //Graphic Options
    public int WindowsizeW = 40; //Wie viele Zellen es im Fenster gibt
    public int WindowsizeH = 25; //Wie viele Zellen es im Fenster gibt
    private static int Cellsize = 30; //Wie groß die Zellen sind
    private static int Spacesize = 6; //Wie groß der Abstand zwischen den Zellen ist
    private static boolean menu = true;
    public int StartLevel = 8;

    public Menu(JFrame Frame) {
        frame = Frame;

        if(menu) {
            StartGameActionListener startGameActionListener = new StartGameActionListener(this);

            Font titlesFont = new Font("Arial", Font.BOLD, 50);
            Font buttonsFont = new Font("Arial", Font.PLAIN, 20);

            titleNamePanel = new JPanel();
            title = new JLabel("Snake");

            this.add(titleNamePanel);
            titleNamePanel.add(title);

            title.setForeground(new Color(0, 255, 32));
            title.setFont(titlesFont);

            titleNamePanel.setBackground(Color.black);




       Button startButton = new Button("Start");
        this.add(startButton);
        startButton.addActionListener(startGameActionListener);
        startButton.setBackground(new Color(0, 0, 0));
        startButton.setForeground(new Color(255, 255, 255));
        startButton.setFont(buttonsFont);


        this.add(new Button("Levels"));
        frame.add(this);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        }else{
            startGame();
        }


    }

    public void startGame() {
        game = new Game(frame, WindowsizeW, WindowsizeH, Cellsize, Spacesize, this);
        game.init(StartLevel);
        game.start();
    }

}
