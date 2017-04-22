import sun.security.krb5.internal.PAData;
import sun.security.util.Length;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
public class GameMap extends JPanel implements ActionListener, KeyListener

{

    Timer tm = new Timer(5,this);
    /**
     * Wektor wszystkich Obiektow na planszy, z ktorymi bomber bedzie oddzialywal
     */
    public Vector<GameObject> vGameObjects;
    /**
     * Obiekt bombera
     */

    public Vector<GameObject> vSpecialGameObjects;

    Bomber bomber;
    /**
     * zmienna przechowujaca aktualna ilosc puntkow
     */
    int numberOfPoints = 500;
    int dX = 0;
    int dY = 0;

    /**
     * konstruktor - za parametr bierze numer poziomu porzebny do wczytania.
     * Wywolywana jest funkcja loadLevel z klasy Parser
     */

    public GameMap(String levelNumber) {




        vGameObjects = new Vector<>();
        vSpecialGameObjects = new Vector<>();



        Parser.loadLevel("src/level" + levelNumber + ".txt", this);
        tm.start();
        addKeyListener(this);


        // repaint();
    }

    /**
     * metoda rysujaca wszystkie obiekty na planszy oraz HUD
     */
    public void paint(Graphics g) {

        super.paintComponent(g);
        for (GameObject go : vGameObjects) {
            go.draw(g);
        }

        for (GameObject go : vSpecialGameObjects) {
            go.draw(g);
        }

        bomber.draw(g);
        drawHUD(g);

    }

    /**
     * metoda rysujaca HUD - informacje o liczbie zyc oraz punktow
     */
    public void drawHUD(Graphics g) {


        g.drawImage(Parser.lifeImage, 0, Parser.GameWindowHeight, GameWindow.lengthUnit, GameWindow.lengthUnit, null);

        g.setFont(new Font("Calibri", Font.PLAIN, GameWindow.lengthUnit));
        g.setColor(Color.white);

        String lifes = Integer.toString(bomber.lifesLeft);

        g.drawString("=" + lifes, GameWindow.lengthUnit, Parser.GameWindowHeight + GameWindow.lengthUnit);

        g.drawString("Score=" + Integer.toString(numberOfPoints), 3 * GameWindow.lengthUnit, Parser.GameWindowHeight + GameWindow.lengthUnit);

    }

    public boolean canBomberMove(int dx, int dy) {

        int a;
        int b;

        for (GameObject go : vSpecialGameObjects) {
            a = Math.abs((bomber.getX()+dx) - go.getX());
            b = Math.abs((bomber.getY()+dy) - go.getY());

            if (a < GameWindow.lengthUnit & b < GameWindow.lengthUnit) {
                vSpecialGameObjects.remove(go);
                return true;
            }
        }

        for (GameObject go : vGameObjects) {

          a = Math.abs((bomber.getX()+dx) - go.getX());
          b = Math.abs((bomber.getY()+dy) - go.getY());
         if (a < GameWindow.lengthUnit-5 & b < GameWindow.lengthUnit-5) {
             return false;
         }

        }
        return true;
    }


public void actionPerformed(ActionEvent e){

        if(canBomberMove(dX,dY)) {
            bomber.setX(bomber.getX() + bomber.speed * dX);
            bomber.setY(bomber.getY() + bomber.speed * dY);
            repaint();
        }
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_DOWN){
            dX=0;
            dY =1;
        }

        if (key == KeyEvent.VK_UP){
            dX=0;
            dY=-1;
        }

        if (key == KeyEvent.VK_LEFT){
            dX=-1;
            dY=0;
        }

        if (key == KeyEvent.VK_RIGHT){
            dX=1;
            dY=0;
        }

    }
    public void keyReleased(KeyEvent e)
    {
        dX=0;
        dY=0;
    }
    public void keyTyped(KeyEvent e)
    {

    }

}
