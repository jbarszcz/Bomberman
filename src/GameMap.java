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
import java.util.Iterator;
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
    Timer tm = new Timer(25,this);
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

    int previousdX;

    int previousdY;

    /***
     *zmienne pomocnicze służące do płynnego poruszania się bombera
     **/
    boolean up,down,left,right,keypressed= false;

    /**
     * konstruktor - za parametr bierze numer poziomu porzebny do wczytania.
     * Wywolywana jest funkcja loadLevel z klasy Parser
     */

    public static int height=Parser.GameWindowHeight;
    public static int width=Parser.GameWindowWidth;

    public GameMap(String levelNumber) {




       // System.out.println(height +"  oraz  " + width);

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


       // g.drawImage(Parser.lifeImage, 0, Parser.GameWindowHeight, GameWindow.lengthUnit, GameWindow.lengthUnit, null);

       // g.setFont(new Font("Calibri", Font.PLAIN, GameWindow.lengthUnit));
        g.setColor(Color.white);

        String lifes = Integer.toString(bomber.lifesLeft);

        //g.drawString("=" + lifes, GameWindow.lengthUnit, Parser.GameWindowHeight + GameWindow.lengthUnit);

        //g.drawString("Score=" + Integer.toString(numberOfPoints), 3 * GameWindow.lengthUnit, Parser.GameWindowHeight + GameWindow.lengthUnit);


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

            if (a < GameWindow.lengthUnitX & b < GameWindow.lengthUnitY) {
                go.catched();
                vSpecialGameObjects.remove(go);
                return true;
            }
        }
        /**
         *  pętla realizująca fizykę śćian
         */
        for (GameObject go : vGameObjects) {

          a = (int)((bomber.ratioX + (float)bomber.speed*(float)dX*(float)0.001) * (float)GameMap.width) - go.getX();
          b = (int)((bomber.ratioY + (float)bomber.speed*(float)dY*(float)0.001) * (float)GameMap.height) - go.getY();

          //ogólnie - za każdym razem zwiększamy WZGLĘDNE współrzędne bombera o 0.01 * jego prędkość ...

          //System.out.println(a + " " + b);



            // i sprawdzamy czy wpadnie na ścianę
          if (Math.abs(a) <= 0.9*GameWindow.lengthUnitX  & Math.abs(b) <= 0.9*GameWindow.lengthUnitY ) {



              return false;

         }

        }
        return true;
    }


public void actionPerformed(ActionEvent e) {

    if (canBomberMove(dX, dY) && keypressed) {


        //bomber.setX(bomber.getX() + bomber.speed * dX);
        //bomber.setY(bomber.getY() + bomber.speed * dY);

        //WZGLĘDNE współrzędne bombera zwiększają się o 0.001 * jego prędkość raz na 25 ms (okres timera)

        bomber.ratioX = ((float) bomber.getX() / (float) GameMap.width) + (float) bomber.speed * (float) dX * (float) 0.001;
        bomber.ratioY = ((float) bomber.getY() / (float) GameMap.height) + (float) bomber.speed * (float) dY * (float) 0.001;

        //System.out.println(bomber.ratioX+" "+bomber.ratioY + " " + bomber.getX() + " " + bomber.getY());




        //bomber.move(dX,dY);

        repaint();

    }
// to jest do dokończenia
    for (Bomb bo : vBombs) {

        if (bo.timer > 50) { //z każdym tyknięciem timera zwiększamy timer bomby , a więc bomba wybuchnie po 50*25ms = 1,25 s

            //aby usuwać elementy z listy po której lecimy forem trzeba użyć iteratora (inaczej wywala wyjątek)
            Iterator<GameObject> iter = vGameObjects.iterator();

            while (iter.hasNext()){
                //tutaj odbywa się fizyka fali uderzeniowej
                GameObject go = iter.next();

                //jeśli lengthunitY długość jednego bloku, lengthunitX - szerokosc.
                if (Math.abs(bo.getY() +GameWindow.lengthUnitY -go.getY())<=GameWindow.lengthUnitY &&  Math.abs(bo.getX()-go.getX())<=GameWindow.lengthUnitX && go.isBreakable)

                    iter.remove(); //zniszczony blok jest usuwany
            }

            vBombs.remove(bo); //sama bomba też jest usuwana
            break;
        }

        else {
            bo.timer += 1; // jeśli jeszcze nie czas na odpalenie bomby to zwiekszamy jej timer
        }
    }
}
    /**
     * metoda obsługująca zdarzenie wciśnięcia przycisku
     * @param e
     */
    public void keyPressed(KeyEvent e)
    {   //tm.start();
        keypressed=true;


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


            //tutaj zabawa współrzędnymi żeby bomby ustawiały się na równych pozycjach
           float ratioXfixed =  ((float)Math.round((double)(bomber.ratioX*Parser.numberOfColumns))) +1;
           float ratioYfixed =  ((float)Math.round((double)(bomber.ratioY*Parser.numberOfRows))) +1;

          //System.out.println("BomberX: " + bomber.ratioX*11 + " BomberY: "+ bomber.ratioY*11+ "ratioX  " +ratioXfixed  + " ratioY:  "+ratioYfixed);
            //nie jest ważne jak, ważne że działą
            vBombs.add(new Bomb((int)ratioXfixed,(int)ratioYfixed,GameMap.width/Parser.numberOfColumns,GameMap.height/Parser.numberOfRows,Parser.bombImage));
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
           if (dX != 0) previousdX = dX;
           if (dY !=0) previousdY = dY;

            dX=0;
            dY=0;
        }

       // keypressed = false;
    }

    public void keyTyped(KeyEvent e)
    {

    }


}
