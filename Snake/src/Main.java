//DO: Apple spawn, pickup, Wait for input on start, Bestenliste? Multiplayer? Zeit/Speedrun? custom border? Portals? Levels? Enemies? Variable Speed? Special Pickups? Quests? Timer? Dash? Abilities?
//Blerghhh

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main  {





    public static void main(String[] args) {
        JFrame frame = new JFrame("SNAAAAAAKE");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400  , 400);
        frame.setBackground(new Color(0, 0, 0));
        frame.requestFocus();
        frame.setVisible(true);
        Menu menu = new Menu(frame);




    }


}

/*Bugs: racecondition bei neustart
flackern
leeres fenster bei aufruf
wenn für äpfel kein platz is -> loop
 */