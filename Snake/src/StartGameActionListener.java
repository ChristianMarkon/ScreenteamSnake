import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartGameActionListener implements ActionListener {
    private final Menu menu;

    @Override
    public void actionPerformed(ActionEvent e) {
        menu.setVisible(false);
        menu.startGame();
    }

    public StartGameActionListener(Menu menu){
        this.menu = menu;
    }
}
