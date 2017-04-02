import sun.security.krb5.internal.PAData;
import sun.security.util.Length;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Klasa przechowująca mapę gry
 */
public class GameMap extends JPanel

{
    /**
     * Wektor wszystkich Obiektow na planszy, z ktorymi bomber bedzie oddzialywal
     */
    public Vector<GameObject> vGameObjects;
    /**
     * Obiekt bombera
     */
    Bomber bomber;
    /**
     *zmienna przechowujaca aktualna ilosc puntkow
     */
    int numberOfPoints=500;

    /**
     *konstruktor - za parametr bierze numer poziomu porzebny do wczytania.
     * Wywolywana jest funkcja loadLevel z klasy Parser
     */
    public GameMap(String levelNumber) {

        vGameObjects = new Vector<>();


        Parser.loadLevel("src/level" +levelNumber+  ".txt",this);


       // repaint();
    }

    /**
     *metoda rysujaca wszystkie obiekty na planszy oraz HUD
     */
    public void paint(Graphics g){


        for(GameObject go:vGameObjects){ //narysuj każdy obiekt w vGameObjects
            go.draw(g);
        }

        bomber.draw(g);
        drawHUD(g);

    }

    /**
     *metoda rysujaca HUD - informacje o liczbie zyc oraz punktow
     */
    public void drawHUD(Graphics g){


        g.drawImage(Parser.lifeImage,0,Parser.GameWindowHeight,GameWindow.lengthUnit,GameWindow.lengthUnit,null);

        g.setFont(new Font("Calibri",Font.PLAIN, GameWindow.lengthUnit));
        g.setColor(Color.white);

        String lifes = Integer.toString(bomber.lifesLeft);

        g.drawString("=" + lifes ,GameWindow.lengthUnit,Parser.GameWindowHeight + GameWindow.lengthUnit);

        g.drawString("Score=" + Integer.toString(numberOfPoints),3*GameWindow.lengthUnit,Parser.GameWindowHeight+GameWindow.lengthUnit);

    }

}
