import javax.swing.*;
import java.awt.*;


public class Main {


    public static void main(String[] args) {
        JFrame frame = new JFrame("SNAAAAAAKE");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setBackground(new Color(0, 0, 0));
        frame.requestFocus();
        frame.setVisible(true);
        Menu menu = new Menu(frame);
    }

}
