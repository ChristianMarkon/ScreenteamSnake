import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;


public class Menu extends Panel implements ActionListener {

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
    public static int StartLevel = 0;
    public JButton startButton;

    //Panel für Levels
    public static JPanel panel_levels;

    //Level Buttons
    public static JButton button_0;
    public static JButton button_1;
    public static JButton button_2;
    public static JButton button_3;
    public static JButton button_4;
    public static JButton button_5;
    public static JButton button_6;
    public static JButton button_7;
    public static JButton button_8;
    public static JButton button_9;
    public static JButton button_10;
    public static JButton button_11;
    public static JButton button_12;


    public Menu(JFrame Frame) {
        frame = Frame;

        if (menu) {
            Font titlesFont = new Font("Arial", Font.BOLD, 50);
            Font buttonsFont = new Font("Arial", Font.PLAIN, 20);

            titleNamePanel = new JPanel();
            title = new JLabel("Snake");
            titleNamePanel.setBackground(Color.black);
            title.setForeground(new Color(0, 255, 32));
            title.setFont(titlesFont);

            startButton = new JButton("Start");
            JButton levelsButton = new JButton("Levels");

            title.setBounds(125, 20, 200, 100);
            startButton.setBounds(45, 120, 300, 60);
            levelsButton.setBounds(45, 200, 300, 60);

            startButton.addActionListener(this);
            levelsButton.addActionListener(this);

            startButton.setBackground(new Color(0, 0, 0));
            startButton.setForeground(new Color(255, 255, 255));
            startButton.setFont(buttonsFont);
            levelsButton.setBackground(new Color(0, 0, 0));
            levelsButton.setForeground(new Color(255, 255, 255));
            levelsButton.setFont(buttonsFont);

            startButton.setBorder(new LineBorder(Color.GREEN));
            levelsButton.setBorder(new LineBorder(Color.GREEN));

            titleNamePanel.setLayout(null);
            titleNamePanel.add(title);
            titleNamePanel.add(startButton);
            titleNamePanel.add(levelsButton);

            startButton.setFocusPainted(false);

            this.add(titleNamePanel);
            frame.add(this);

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        } else {
            startGame(StartLevel);
        }
    }

    public void startGame(int StartLevel) {
        game = new Game(frame, WindowsizeW, WindowsizeH, Cellsize, Spacesize, this);
        game.init(StartLevel);
        game.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) { //Methode für die Aktion von den Buttons
        if (e.getActionCommand().equals("Start")) {
            this.setVisible(false);
            menu = false;
            this.startGame(StartLevel);
        } else if (e.getActionCommand().equals("Levels")) {
            levels();
        } else if (e.getActionCommand().equals("1. Playground")) {
            this.setVisible(false);
            menu = false;
            this.startGame(1);
        } else if (e.getActionCommand().equals("2. Squiggles")) {
            this.setVisible(false);
            menu = false;
            this.startGame(2);
        } else if (e.getActionCommand().equals("3. Cave 1")) {
            this.setVisible(false);
            menu = false;
            this.startGame(3);
        } else if (e.getActionCommand().equals("4. Shapes")) {
            this.setVisible(false);
            menu = false;
            this.startGame(4);
        } else if (e.getActionCommand().equals("5. Snake easy")) {
            this.setVisible(false);
            menu = false;
            this.startGame(5);
        } else if (e.getActionCommand().equals("6. Cave 2")) {
            this.setVisible(false);
            menu = false;
            this.startGame(6);
        } else if (e.getActionCommand().equals("7. Kinda sus")) {
            this.setVisible(false);
            menu = false;
            this.startGame(7);
        } else if (e.getActionCommand().equals("8. Snake hard")) {
            this.setVisible(false);
            menu = false;
            this.startGame(8);
        } else if (e.getActionCommand().equals("9. Credits 1")) {
            this.setVisible(false);
            menu = false;
            this.startGame(9);
        } else if (e.getActionCommand().equals("10. Credits 2")) {
            this.setVisible(false);
            menu = false;
            this.startGame(10);
        } else if (e.getActionCommand().equals("11. Credits 3")) {
            this.setVisible(false);
            menu = false;
            this.startGame(11);
        } else if (e.getActionCommand().equals("12. Pacman")) {
            this.setVisible(false);
            menu = false;
            this.startGame(12);
        }

    }

    public void levels() {
        titleNamePanel.removeAll();
        titleNamePanel.setVisible(false);
        panel_levels = new JPanel();

        button_0 = new JButton("Level 0");
        button_1 = new JButton("1. Playground");
        button_2 = new JButton("2. Squiggles");
        button_3 = new JButton("3. Cave 1");
        button_4 = new JButton("4. Shapes");
        button_5 = new JButton("5. Snake easy");
        button_6 = new JButton("6. Cave 2");
        button_7 = new JButton("7. Kinda sus");
        button_8 = new JButton("8. Snake hard");
        button_9 = new JButton("9. Credits 1");
        button_10 = new JButton("10. Credits 2");
        button_11 = new JButton("11. Credits 3");
        button_12 = new JButton("12. Pacman");

        button_0.addActionListener(this);
        button_1.addActionListener(this);
        button_2.addActionListener(this);
        button_3.addActionListener(this);
        button_4.addActionListener(this);
        button_5.addActionListener(this);
        button_6.addActionListener(this);
        button_7.addActionListener(this);
        button_8.addActionListener(this);
        button_9.addActionListener(this);
        button_10.addActionListener(this);
        button_11.addActionListener(this);
        button_12.addActionListener(this);

        button_1.setBounds(15, 20, 177, 50);
        button_2.setBounds(193, 20, 177, 50);

        button_3.setBounds(15, 70, 177, 50);
        button_4.setBounds(193, 70, 177, 50);
        button_5.setBounds(15, 120, 177, 50);
        button_6.setBounds(193, 120, 177, 50);
        button_7.setBounds(15, 170, 177, 50);
        button_8.setBounds(193, 170, 177, 50);

        button_10.setBounds(193, 220, 177, 50);
        button_9.setBounds(15, 220, 177, 50);
        button_12.setBounds(193, 270, 177, 50);
        button_11.setBounds(15, 270, 177, 50);


        button_0.setBackground(new Color(0, 0, 0));
        button_0.setForeground(new Color(255, 255, 255));
        button_1.setBackground(new Color(0, 0, 0));
        button_1.setForeground(new Color(255, 255, 255));
        button_2.setBackground(new Color(0, 0, 0));
        button_2.setForeground(new Color(255, 255, 255));
        button_3.setBackground(new Color(0, 0, 0));
        button_3.setForeground(new Color(255, 255, 255));
        button_4.setBackground(new Color(0, 0, 0));
        button_4.setForeground(new Color(255, 255, 255));
        button_5.setBackground(new Color(0, 0, 0));
        button_5.setForeground(new Color(255, 255, 255));
        button_6.setBackground(new Color(0, 0, 0));
        button_6.setForeground(new Color(255, 255, 255));
        button_7.setBackground(new Color(0, 0, 0));
        button_7.setForeground(new Color(255, 255, 255));
        button_8.setBackground(new Color(0, 0, 0));
        button_8.setForeground(new Color(255, 255, 255));
        button_9.setBackground(new Color(0, 0, 0));
        button_9.setForeground(new Color(255, 255, 255));
        button_10.setBackground(new Color(0, 0, 0));
        button_10.setForeground(new Color(255, 255, 255));
        button_11.setBackground(new Color(0, 0, 0));
        button_11.setForeground(new Color(255, 255, 255));
        button_12.setBackground(new Color(0, 0, 0));
        button_12.setForeground(new Color(0, 0, 0));


        button_1.setBorder(new LineBorder(Color.GREEN));
        button_2.setBorder(new LineBorder(Color.GREEN));
        button_3.setBorder(new LineBorder(Color.GREEN));
        button_4.setBorder(new LineBorder(Color.GREEN));
        button_5.setBorder(new LineBorder(Color.GREEN));
        button_6.setBorder(new LineBorder(Color.GREEN));
        button_7.setBorder(new LineBorder(Color.GREEN));
        button_8.setBorder(new LineBorder(Color.GREEN));
        button_9.setBorder(new LineBorder(Color.GREEN));
        button_10.setBorder(new LineBorder(Color.GREEN));
        button_11.setBorder(new LineBorder(Color.GREEN));
        button_12.setBorder(new LineBorder(Color.BLACK));


        panel_levels.setLayout(null); //damit man selber entscheiden kann wo man die Buttons platziert
        panel_levels.setSize(getParent().getSize()); //Panel nimmt die größe von dem Frame
        panel_levels.setBackground(Color.black);
        panel_levels.add(button_0);
        panel_levels.add(button_1);
        panel_levels.add(button_2);
        panel_levels.add(button_3);
        panel_levels.add(button_4);
        panel_levels.add(button_5);
        panel_levels.add(button_6);
        panel_levels.add(button_7);
        panel_levels.add(button_8);
        panel_levels.add(button_9);
        panel_levels.add(button_10);
        panel_levels.add(button_11);
        panel_levels.add(button_12);
        panel_levels.setVisible(true);

        this.add(panel_levels); //panel wird dem Frame hinzugefügt
    }


}
