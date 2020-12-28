//DO: Apple spawn, pickup, Wait for input on start, Bestenliste? Multiplayer? Zeit/Speedrun? custom border? Portals? Levels? Enemies? Variable Speed? Special Pickups? Quests? Timer? Dash? Abilities?
//Blerghhh

import javax.swing.*;

public class Main  {

    public static void main(String[] args) {
        JFrame frame = new JFrame("SNAAAAAAKE");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Menu menu = new Menu(frame);

    }

}