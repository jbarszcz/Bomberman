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
    /**
     * licznik pomocny przy animacji i obsludze zdarzeń
     */
    Timer tm = new Timer(15,this);
    /**
     * Wektor wszystkich Obiektow na planszy, z ktorymi bomber bedzie oddzialywal
     */
    public Vector<GameObject> vGameObjects;
    /**
     * wektor obiektów o specjalnym efekcie - bonusów
     */
    public Vector<Bonus> vSpecialGameObjects;

    /**
     * wektor bomb
     */
    public static Vector<Bomb> vBombs;
    /**
     * Obiekt bombera
     */
    Bomber bomber;
    /**
     * zmienna przechowujaca aktualna ilosc puntkow
     */
    public static int numberOfPoints = 0;

    /**
     * zmienna zmiany pozycji poziomej przy ruchu
     */
    int dX = 0;
    /**
     * zmienna zmiany pozycji pionowej przy ruchu
     */
    int dY = 0;

    /***
     *zmienne pomocnicze służące do płynnego poruszania się bombera
     **/
    boolean up,down,left,right = false;

    /**
     * konstruktor - za parametr bierze numer poziomu porzebny do wczytania.
     * Wywolywana jest funkcja loadLevel z klasy Parser
     */
    public GameMap(String levelNumber) {




        vGameObjects = new Vector<>();
        vSpecialGameObjects = new Vector<>();
        vBombs = new Vector<Bomb>();


        //wczytywanie danych poziomu
        Parser.loadLevel("src/level" + levelNumber + ".txt", this);
        //start timera
        tm.start();
        addKeyListener(this);


    }

    /**
     * metoda rysujaca wszystkie obiekty na planszy oraz HUD
     */
    public void paint(Graphics g) {

        super.paintComponent(g);

        //rysuje wszystkie bonus - przed blokami i potworami, tak żeby mogły być schowane
        for (GameObject go : vSpecialGameObjects) {
            go.draw(g);
        }

        //najpierw rysuje wszystkie bloki
        for (GameObject go : vGameObjects) {
            go.draw(g);
        }




        //rysuje wszystkie bomby
        for (Bomb b : vBombs){
            b.draw(g);
        }

        //rysuje bombera
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
        /**
         * pętla zbierająca bonusy
         */
        for (Bonus go : vSpecialGameObjects) {
            a = Math.abs((bomber.getX()+dx) - go.getX());
            b = Math.abs((bomber.getY()+dy) - go.getY());

            if (a < GameWindow.lengthUnit & b < GameWindow.lengthUnit) {
                go.catched();
                vSpecialGameObjects.remove(go);
                return true;
            }
        }
        /**
         *  pętla realizująca fizykę śćian
         */
        for (GameObject go : vGameObjects) {

          a = (bomber.getX()+dx) - go.getX();
          b = (bomber.getY()+dy) - go.getY();

            //ustawiamy margines 5 pikseli - bardzo ciężko jest wcelować się bomberem co do 1 piksela
          if (Math.abs(a) <= GameWindow.lengthUnit-5 & Math.abs(b) <= GameWindow.lengthUnit-5) {
             return false;
         }

        }
        return true;
    }


public void actionPerformed(ActionEvent e){

        if(canBomberMove(dX,dY)) {
            bomber.setX(bomber.getX() + bomber.speed * dX);
            bomber.setY(bomber.getY() + bomber.speed * dY);
            //bomber.move(dX,dY);

            repaint();
        }

    }

    /**
     * metoda obsługująca zdarzenie wciśnięcia przycisku
     * @param e
     */
    public void keyPressed(KeyEvent e)
    {   //tm.start();
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_DOWN){
            dX=0;
            dY =1;
            down = true;
        }

        if (key == KeyEvent.VK_UP){
            dX=0;
            dY=-1;
            up = true;
        }

        if (key == KeyEvent.VK_LEFT){
            dX=-1;
            dY=0;
            left = true;
        }

        if (key == KeyEvent.VK_RIGHT){
            dX=1;
            dY=0;
            right = true;
        }
        //spacja -> rysowana jest nowa bomba
        if (key == KeyEvent.VK_SPACE){
            vBombs.add(new Bomb(bomber.getX(),bomber.getY(),GameWindow.lengthUnit,GameWindow.lengthUnit,Parser.bombImage));
        }

    }

    /***
     * metoda obsługująca zdarzenie zwolnienia przycisku
     * @param e
     */
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();


        //to jest po to żeby nie lagowało przy szybkiej zmianie przycisków (wciskamy jeden zanim puścimy drugi);
        if (key==KeyEvent.VK_DOWN) down = false;
        if (key==KeyEvent.VK_UP) up = false;
        if (key==KeyEvent.VK_LEFT) left = false;
        if (key==KeyEvent.VK_RIGHT) right = false;

        //postać zatrzyma się tylko jeśli wszystkie przyciski są puszczone
        // a nie np. kiedy wcisnęliśmy jeden i dopiero puściliśmy drugi
        if (down == false & up == false & right == false & left ==false) {
            //tm.stop();
            dX=0;
            dY=0;
        }
    }

    public void keyTyped(KeyEvent e)
    {

    }

}
