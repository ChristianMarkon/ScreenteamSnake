import javax.swing.*;
import java.awt.*;
import java.awt.Font;

public class Menu extends Panel {

    JPanel titleNamePanel;
    JLabel title;


    public Menu(JFrame frame) {
        frame.setSize(400  , 400);
        frame.setBackground(new Color(0, 0, 0));
        Game game = new Game(frame);
        Font titlesFont = new Font("Arial", Font.BOLD, 50);
        Font buttonsFont = new Font("Arial", Font.PLAIN, 20);


        StartGameActionListener startGameActionListener = new StartGameActionListener(game, this);

        titleNamePanel = new JPanel();
        title = new JLabel("Snake");

        this.add(titleNamePanel);
        titleNamePanel.add(title);

        title.setForeground(new Color(0, 255, 32));
        title.setFont(titlesFont);

        titleNamePanel.setBackground(Color.black);

        game.init();
        game.start();


       /* Button startButton = new Button("Start");
        this.add(startButton);
        startButton.addActionListener(startGameActionListener);
        startButton.setBackground(new Color(0, 0, 0));
        startButton.setForeground(new Color(255, 255, 255));
        startButton.setFont(buttonsFont);


        this.add(new Button("Levels"));
        frame.add(this);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));*/


    }

}
