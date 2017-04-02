import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * klasa zawierająca ramkę programu (JFrame)
 */
public class GameWindow extends JFrame {
    GameMap gmap;
    GameMenu gmenu = new GameMenu();
    public int width = Parser.GameWindowWidth;
    public int height = Parser.GameWindowHeight;
    public static int lengthUnit;

    public GameWindow(){
        super("Dyna Blaster");

        //setSize(width,height); //ustawiamy trochę dłuższy rozmiar żeby zmieścić licznik żyć i punktów
        setBackground(new Color(34,139,34));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        gmap = new GameMap("2");
        this.add(gmap);
        gmap.setPreferredSize(new Dimension(width +lengthUnit,height + lengthUnit)); //poszerzamy okno o 1 jednostke zeby zmieścić HUD
        pack();








    }

}