import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * klasa zawierająca ramkę programu (JFrame)
 */
public class GameWindow extends JFrame {
    Parser parse = new Parser();
    GameMap gm = new GameMap("1");

    public int width = Parser.GameWindowWidth;
    public int height = Parser.GameWindowHeight;

    public GameWindow(){
        super("Dyna Blaster");


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(34,139,34));
        setVisible(true);

        setSize(width,height);
        this.add(gm);





    }

}