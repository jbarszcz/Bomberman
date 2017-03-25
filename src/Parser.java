import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;
import java.io.IOException;

/**
 * klasa majšca na celu pobieranie danych z pliku konfiguracyjnego
 */
public  class Parser {


    public static int LevelNumber;
    public static int GameWindowWidth;
    public static int GameWindowHeight;

    public static BufferedImage wallImage;
    public static BufferedImage brickImage;

    /**
     * funkcja odczytująca kolejne dane z pliku konfiguracynego
     */
    public void odczytaj() {
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
            odczyt.close();

        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się odnaleźć pliku konfiguracyjnego");
        } catch (IOException e) {
            System.out.println("Błąd strumienia IO");
        }

        try {
            wallImage = ImageIO.read(getClass().getResourceAsStream("castleWall.png"));
            brickImage= ImageIO.read(getClass().getResourceAsStream("brickWall.png"));
        }

        catch (IOException e)
        {
            System.out.println("IO exception przy wczytywaniu obrazkow");
        }


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
            String temp=temp = readlevel.readLine();
            Scanner s;


            while (!temp.equals("")&& temp.length()!=0) { //TODO jakoś poustawiac te warunki , zeby nie uzywac tego ifa nizej

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

                    case (2): //wczytywanie scian niezniszczalnych
                       // gameMap.MapOfObjects.put(new PointXY(x,y), new Wall(x, y, 50, 50, wallImage));
                       gameMap.vGameObjects.add(new Wall((GameWindowWidth/numberOfColumns)*x,(GameWindowHeight/numberOfRows)*y,
                               GameWindowHeight/numberOfRows,GameWindowWidth/numberOfColumns,wallImage));
                        break;

                    case (3): //wczytywanie scian zniszczalnych

                       // gameMap.MapOfObjects.put(new PointXY(x,y), new Brick(x, y, 50, 50, brickImage));
                        gameMap.vGameObjects.add(new Brick((GameWindowWidth/numberOfColumns)*x,(GameWindowHeight/numberOfRows)*y,GameWindowHeight/numberOfRows,
                                GameWindowWidth/numberOfColumns,brickImage));

                    break;

                    default: break; //gdy state spoza zakresu przerywa while'a


                }temp = readlevel.readLine();

            }   readlevel.close();


            //TODO: wczytanie obrazków, ustawienia rozmiaru w case

        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się odnaleźć pliku poziomu");
        } catch (IOException e) {

            System.out.println("wyjatek IO");
        }

    }





    public Parser(){
        odczytaj();
    }
}