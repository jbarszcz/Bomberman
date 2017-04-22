import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * klasa opisujaca glowne okno gry
 */
public class GameWindow extends JFrame {
    /**
     * Plansza gry rysowana w ramce
     */
    GameMap gmap;
    /**
     * szerokosc okna odczytana z parsowania plikow
     */
    public int width = Parser.GameWindowWidth;
    /**
     * wysokosc okna odczytana z klasy parsowania plikos
     */
    public int height = Parser.GameWindowHeight;
    /**
     * jedostka dlugosci - jest to najmniejsza odleglosc miedzy jednym obiektem a drugim
     * czyli min(Szerokosc/liczba kolumn, Wysokosc/liczba wierszy)
     */
    public static int lengthUnit;

    public GameWindow(){
        super("Dyna Blaster");

        //setSize(width,height); //ustawiamy trochę dłuższy rozmiar żeby zmieścić licznik żyć i punktów
        //setBackground(new Color(34,139,34));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);



        gmap = new GameMap("1");
        gmap.setBackground(new Color(34,139,34));
        this.add(gmap);
        gmap.setPreferredSize(new Dimension(width +lengthUnit,height + lengthUnit)); //poszerzamy okno o 1 jednostke zeby zmieścić HUD
        gmap.setFocusable(true);
        gmap.requestFocusInWindow();
        pack();








    }

}