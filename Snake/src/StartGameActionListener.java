import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGameActionListener implements ActionListener {
    private final Game game;
    private final Menu menu;

    @Override
    public void actionPerformed(ActionEvent e) {
        menu.setVisible(false);
        game.init();
        game.start();
    }

    public StartGameActionListener(Game game, Menu menu){
        this.game = game;
        this.menu = menu;
    }
}
