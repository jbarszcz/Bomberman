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
 * klasa majšca na celu pobieranie danych z pliku konfiguracyjnego
 */
public final class Parser {


    public static int LevelNumber;
    public static int GameWindowWidth;
    public static int GameWindowHeight;
    public static int NumberOfLifes;
    public static int BomberSpeed;
    public static int MonsterSpeed;
    public static int BombPower;


    public static BufferedImage wallImage;
    public static BufferedImage brickImage;
    public static BufferedImage bomberImage;
    public static BufferedImage monsterImage;
    public static BufferedImage lifeImage;
    public static BufferedImage bombImage;
    public static BufferedImage bonus1Image;


    /**
     * konstruktor klasy Parser. Obiekt ten tworzony jest tylko raz, więc od razu odczytywany jest plik konfiguracyjny
     **/
    public Parser(){
        readCfg();
    }

    /**
     * funkcja odczytująca kolejne dane z pliku konfiguracynego
     */
    public void readCfg() {
        try {
            File sciezka = new File("src/config.txt");
            FileReader plik = new FileReader(sciezka);
            BufferedReader odczyt = new BufferedReader(plik);

            odczyt.readLine();

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

            odczyt.close();
        }
        catch (FileNotFoundException e) {System.out.println("Nie udało się odnaleźć pliku konfiguracyjnego");}
        catch (IOException e) {System.out.println("Błąd strumienia IO");}

        try {
            wallImage = ImageIO.read(getClass().getResourceAsStream("castleWall.png"));
            brickImage= ImageIO.read(getClass().getResourceAsStream("brickWall.png"));
            bomberImage= ImageIO.read(getClass().getResourceAsStream("bomberIMG.png"));
            monsterImage= ImageIO.read(getClass().getResourceAsStream("monsterIMG.png"));
            lifeImage= ImageIO.read(getClass().getResourceAsStream("lifeIMG.png"));
            bonus1Image= ImageIO.read(getClass().getResourceAsStream("bonus1IMG.png"));
        }

        catch (IOException e)
        {System.out.println("IO exception przy wczytywaniu obrazkow");}


    }


/**
 *funkcja wczytująca dane poziomu gry
 **/
    public static void loadLevel(String path, GameMap gameMap) {

        int state = 0;

        int numberOfRows = 0;
        int numberOfColumns = 0;
        int x;
        int y;
        try {

            File level = new File(path);
            FileReader levelfile = new FileReader(level);
            BufferedReader readlevel = new BufferedReader(levelfile);
            String temp = readlevel.readLine();
            Scanner s;


            while (!temp.equals("")) {

                System.out.println(temp);



                if (temp.charAt(0) == '#') {
                    state++;
                    temp = readlevel.readLine();
                    continue;
                }

                s = new Scanner(temp);
                x = s.nextInt();
                y = s.nextInt();


                switch (state) {
                    case (1): //wczytywanie liczby wierszy i kolumn
                        numberOfRows=x;
                        numberOfColumns=y;
                        GameWindow.lengthUnit = max(GameWindowHeight/x,GameWindowHeight/y);
                        break;

                    case (2): //wczytywanie scian niezniszczalnych
                       // gameMap.MapOfObjects.put(new PointXY(x,y), new Wall(x, y, 50, 50, wallImage));
                       gameMap.vGameObjects.add(new Wall(
                               (GameWindowWidth/numberOfColumns)*x,
                               (GameWindowHeight/numberOfRows)*y,
                               GameWindowHeight/numberOfRows,
                               GameWindowWidth/numberOfColumns,wallImage));
                        break;

                    case (3): //wczytywanie scian zniszczalnych

                       // gameMap.MapOfObjects.put(new PointXY(x,y), new Brick(x, y, 50, 50, brickImage));
                        gameMap.vGameObjects.add(new Brick(
                                (GameWindowWidth/numberOfColumns)*x,
                                (GameWindowHeight/numberOfRows)*y,
                                GameWindowHeight/numberOfRows,
                                GameWindowWidth/numberOfColumns,brickImage));
                    break;

                    case (4): //wczytanie pozycji bombera
                        gameMap.bomber=new Bomber((GameWindowWidth/numberOfColumns)*x,
                                (GameWindowHeight/numberOfRows)*y,
                                GameWindowHeight/numberOfRows,
                                GameWindowWidth/numberOfColumns,bomberImage);

                    break;

                    case (5): gameMap.vGameObjects.add(new Monster(
                            (GameWindowWidth/numberOfColumns)*x,
                            (GameWindowHeight/numberOfRows)*y,
                            GameWindowHeight/numberOfRows,
                            GameWindowWidth/numberOfColumns,monsterImage));

                        break;

                    case (6): gameMap.vGameObjects.add(new Bonus(
                            (GameWindowWidth/numberOfColumns)*x,
                            (GameWindowHeight/numberOfRows)*y,
                            GameWindowHeight/numberOfRows,
                            GameWindowWidth/numberOfColumns,lifeImage,1));

                        break;

                    case (7): gameMap.vGameObjects.add(new Bonus(
                            (GameWindowWidth/numberOfColumns)*x,
                            (GameWindowHeight/numberOfRows)*y,
                            GameWindowHeight/numberOfRows,
                            GameWindowWidth/numberOfColumns,bonus1Image,2));

                        break;

                    default: break;


                }   temp = readlevel.readLine();

            }   readlevel.close();

        } catch (FileNotFoundException e) {System.out.println("Nie udało się odnaleźć pliku poziomu");

        } catch (IOException e) {System.out.println("wyjatek IO");}
    }






}