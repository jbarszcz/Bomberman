import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * klasa zawierająca ramkę programu (JFrame)
 */
public class GameWindow extends JFrame {
    Parser parse = new Parser();
    GameMap gmap = new GameMap("1");
    GameMenu gmenu = new GameMenu();
    public int width = Parser.GameWindowWidth;
    public int height = Parser.GameWindowHeight;

    public GameWindow(){
        super("Dyna Blaster");

        setSize(width,height);
        setBackground(new Color(34,139,34));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.add(gmap);
       // this.add(gmenu);







    }

}