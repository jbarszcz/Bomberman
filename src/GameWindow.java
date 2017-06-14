import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.lang.Math.max;

/**
 * klasa opisujaca glowne okno gry
 */
public class GameWindow extends JFrame implements ComponentListener {
    /**
     * Plansza gry rysowana w ramce
     */
    GameMap gmap;
    /**
     * szerokosc okna odczytana z parsowania plikow
     */
    public static int width = Parser.GameWindowWidth;

    boolean start = true;

    /**
     * wysokosc okna odczytana z klasy parsowania plikos
     */
    public static int height = Parser.GameWindowHeight;
    /**
     * jedostka dlugosci - jest to najmniejsza odleglosc miedzy jednym obiektem a drugim
     * czyli min(Szerokosc/liczba kolumn, Wysokosc/liczba wierszy)
     */
    public static int lengthUnitX;
    public static int lengthUnitY;

    public static float scale=1;

    public GameWindow(){
        super("Dyna Blaster");

        setSize(width,height); //ustawiamy trochę dłuższy rozmiar żeby zmieścić licznik żyć i punktów
        //setBackground(new Color(34,139,34));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        //JPanel listPane = new JPanel();
        //JButton button = new JButton();
       // this.add(listPane);
        //listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
       // listPane.setPrefferedSize(new Dimension(width+lengthUnit ,height+lengthUnit ));

        gmap = new GameMap("1");
        gmap.setBackground(new Color(34,139,34));
        //listPane.add(gmap);
       // listPane.add(button);

        add(gmap);
        gmap.setPreferredSize(new Dimension(width ,height)); //poszerzamy okno o 1 jednostke zeby zmieścić HUD
        //button.setPreferredSize(new Dimension(100,100));
        gmap.setFocusable(true);
        gmap.requestFocusInWindow();
        pack();
        addComponentListener(this);







    }

    @Override
    public void componentResized(ComponentEvent e) {
        gmap.keypressed = false; // componentResized wywołuje actionPerformed, przez co rusza bomberem, trzeba jakoś to odróżnić od ruszania się przyciskami

        int changeX = width-this.getWidth();
        int changeY = height-this.getHeight();


        width = this.getWidth();
        height = this.getHeight();


        //z jakiegoś powodu zdarzenie wywołuje się przy starcie gry i wszystko psuje, więc "elegancko" to obchodzimy
        if (start== false) {
            GameMap.height = GameMap.height - changeY;
            GameMap.width = GameMap.width - changeX;
        }
        lengthUnitX = GameMap.width/Parser.numberOfColumns;
        lengthUnitY = GameMap.height/Parser.numberOfRows;

        start = false;

       // System.out.println("Resized!" + GameMap.width + " " + GameMap.height);

        gmap.repaint();






    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }


}