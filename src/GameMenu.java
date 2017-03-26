import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Menu główne
 */
public class GameMenu extends JPanel {

public GameMenu(){
    JButton PlayButton = new JButton("Play");
    JButton HighscoreButton = new JButton("High scores");
    JButton ExitButton = new JButton("Exit");
    this.add(PlayButton);
    this.add(HighscoreButton);
    this.add(ExitButton);

    }

}
