import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;
import java.io.IOException;

import static java.lang.Integer.max;
import static java.lang.Math.min;

/**
 * klasa pobierajaca dane z pliku konfiguracyjnego oraz definicje poziomow
 */
public final class Parser {

    /**
     * liczba poziomow gry
     */
    public static int LevelNumber;
    /**
     * poczatkowa szerokosc planszy
     */
    public static int GameWindowWidth;
    /**
     * poczatkowa wysokosc planszy
     */
    public static int GameWindowHeight;
    /**
     * liczba zyc gracza
     * */
    public static int NumberOfLifes;
    /**
     * predkosc gracza
     */
    public static int BomberSpeed;
    /**
     * predkosc potworow
     */
    public static int MonsterSpeed;
    /**
     * sila razenia bomby
     */
    public static int BombPower;

    /**
     * obrazek sciany niezniszczalnej
     */
    public static BufferedImage wallImage;
    /**
     * obrazek sciany zniszczalnej
     */
    public static BufferedImage brickImage;
    /**
     * obrazek bomby
     */
    public static BufferedImage bomberImage;
    /**
     * obrazek potwora
     */
    public static BufferedImage monsterImage;
    /**
     * obrazek serduszka
     */
    public static BufferedImage lifeImage;
    /**
     * obrazek bomby
     */
    public static BufferedImage bombImage;
    /**
     * obrazek bonusu1
     */
    public static BufferedImage bonus1Image;
    /**
     * obrazek bonusu2
     */
    public static BufferedImage bonus2Image;

    /**
     * konstruktor klasy Parser. Wywoluje metode odczytu pliku konfiguracyjnego
     **/
    public Parser(){
        readCfg();
    }

    /**
     * funkcja odczytująca kolejne dane z pliku konfiguracynego
     */
    public void readCfg() {
        try {
            /**
             * otwieranie pliku
             */
            File sciezka = new File("src/config.txt");
            FileReader plik = new FileReader(sciezka);
            BufferedReader odczyt = new BufferedReader(plik);

            odczyt.readLine();
            //wczytywanie parametrów gry
            LevelNumber = Integer.parseInt(odczyt.readLine());
            odczyt.readLine();
            GameWindowWidth = Integer.parseInt(odczyt.readLine());
            odczyt.readLine();
            GameWindowHeight = Integer.parseInt(odczyt.readLine());
            odczyt.readLine();
            NumberOfLifes = Integer.parseInt(odczyt.readLine());
            odczyt.readLine();
            BomberSpeed = Integer.parseInt(odczyt.readLine());
            odczyt.readLine();

            //System.out.println(GameWindowWidth);
            //System.out.println(GameWindowHeight);

            odczyt.close();
        }
        catch (FileNotFoundException e) {System.out.println("Nie udało się odnaleźć pliku konfiguracyjnego");}
        catch (IOException e) {System.out.println("Błąd strumienia IO");}

        try { //wczytywanie obrazków elementów na planszy
            wallImage = ImageIO.read(getClass().getResourceAsStream("castleWall.png"));
            brickImage= ImageIO.read(getClass().getResourceAsStream("brickWall.png"));
            bomberImage= ImageIO.read(getClass().getResourceAsStream("bomberIMG.png"));
            monsterImage= ImageIO.read(getClass().getResourceAsStream("monsterIMG.png"));
            lifeImage= ImageIO.read(getClass().getResourceAsStream("lifeIMG.png"));
            bonus1Image= ImageIO.read(getClass().getResourceAsStream("bonus1IMG.png"));
            bonus2Image=ImageIO.read(getClass().getResourceAsStream("doorIMG.png"));
            bombImage=ImageIO.read(getClass().getResourceAsStream("bomb.png"));
        }

        catch (IOException e)
        {System.out.println("IO exception przy wczytywaniu obrazkow");}


    }


/**
 *funkcja wczytująca dane poziomu gry
 **/
    public static void loadLevel(String path, GameMap gameMap) {

        int state = 0;
        /**
         * liczba wierszy planszy
         */
        int numberOfRows = 0;
        /**
         * liczba kolumn planszy
         */
        int numberOfColumns = 0;
        int x;
        int y;
        try {

            File level = new File(path);
            FileReader levelfile = new FileReader(level);
            BufferedReader readlevel = new BufferedReader(levelfile);
            String temp = readlevel.readLine();
            Scanner s;


            while (!temp.equals(".")) { //definicja poziomu kończy się linią zawierajacą kropkę

                //System.out.println(temp);



                if (temp.charAt(0) == '#') { //wczytywanie różnych elementów oddzielone jest linią rozpoczętą od '#'
                    state++;
                    temp = readlevel.readLine();
                    continue;
                }
                s = new Scanner(temp);
                x = s.nextInt();
                y = s.nextInt();


                switch (state) {
                    //wczytywanie liczby wierszy i kolumn
                    case (1):
                        numberOfRows=x;
                        numberOfColumns=y;
                        GameWindow.lengthUnit = min(GameWindowHeight/x,GameWindowHeight/y);
                        break;
                    //wczytywanie scian niezniszczalnych
                    case (2):
                       gameMap.vGameObjects.add(new Wall(
                               (GameWindowWidth/numberOfColumns)*(x-1),  //-1 ponieważ użytkownik zwykle indeksuje od 1
                               (GameWindowHeight/numberOfRows)*(y-1),
                               GameWindowHeight/numberOfRows,
                               GameWindowWidth/numberOfColumns,wallImage));
                        break;
                    //wczytywanie scian zniszczalnych
                    case (3):

                        gameMap.vGameObjects.add(new Brick(
                                (GameWindowWidth/numberOfColumns)*(x-1),
                                (GameWindowHeight/numberOfRows)*(y-1),
                                GameWindowHeight/numberOfRows,
                                GameWindowWidth/numberOfColumns,brickImage));
                    break;
                    //wczytanie pozycji bombera
                    case (4):
                        gameMap.bomber=new Bomber((GameWindowWidth/numberOfColumns)*(x-1),
                                (GameWindowHeight/numberOfRows)*(y-1),
                                GameWindowHeight/numberOfRows,
                                GameWindowWidth/numberOfColumns,bomberImage);

                    break;

                    case (5): gameMap.vGameObjects.add(new Monster(
                            (GameWindowWidth/numberOfColumns)*(x-1),
                            (GameWindowHeight/numberOfRows)*(y-1),
                            GameWindowHeight/numberOfRows,
                            GameWindowWidth/numberOfColumns,monsterImage));

                        break;
                    //dodatkowe życia
                    case (6): gameMap.vSpecialGameObjects.add(new Bonus(
                            (GameWindowWidth/numberOfColumns)*(x-1),
                            (GameWindowHeight/numberOfRows)*(y-1),
                            GameWindowHeight/numberOfRows,
                            GameWindowWidth/numberOfColumns,lifeImage,1));

                        break;
                    //bonusy
                    case (7): gameMap.vSpecialGameObjects.add(new Bonus(
                            (GameWindowWidth/numberOfColumns)*(x-1),
                            (GameWindowHeight/numberOfRows)*(y-1),
                            GameWindowHeight/numberOfRows,
                            GameWindowWidth/numberOfColumns,bonus1Image,2));

                        break;
                   //drzwi
                    case (8): gameMap.vSpecialGameObjects.add(new Bonus(
                            (GameWindowWidth/numberOfColumns)*(x-1),
                            (GameWindowHeight/numberOfRows)*(y-1),
                            GameWindowHeight/numberOfRows,
                            GameWindowWidth/numberOfColumns,bonus2Image,3));

                        break;

                    default: break;


                }   temp = readlevel.readLine();

            }   readlevel.close();

        } catch (FileNotFoundException e) {System.out.println("Nie udało się odnaleźć pliku poziomu");

        } catch (IOException e) {System.out.println("wyjatek IO");}
    }






}