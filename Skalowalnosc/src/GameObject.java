import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * klasa abstrakcyjna większości obiektów w grze
 */
public abstract class GameObject {

    public int positionX ;

    public int positionY;

    public float ratioX;
    public float ratioY;

    /**
     * współrzędna x - rozmiar okna przez numer wiersza
     */
    public int x;
    /**
     * współrzędna y - wysokosc okna przez numer kolumny
     */
    public int y;
    /**
     * szerokosc obiektu potrzebna do narysowania
     */
    private int xsize;
    /**
     * dlugosc obiektu potrzebna do narysowania
     */
    private int ysize;
    /**
     * obrazek obiektu
     */
    private BufferedImage image;
    /**
     * czy obiekt jest zniszczalny przez bombe
     */
    public boolean isBreakable;

    /**
     * konstruktor przyjmujacy wszystkie niezbedne dane do narysowania obiektu
     */
    public GameObject(int x1,int y1,int xsize,int ysize,BufferedImage image){
        this.positionX = x1;
        this.positionY = y1;
        this.x = (GameMap.width/Parser.numberOfColumns)*(positionX-1);
        this.y = (GameMap.height/Parser.numberOfRows)*(positionY-1);

        //System.out.println(this.x + " " + this.y + " " );

        this.xsize = xsize;
        this.ysize = ysize;
        this.image = image;

        ratioX=(float)this.x/(float)GameMap.width;
        ratioY=(float)this.y/(float)GameMap.height;

        //pozycje względne

    }

    public GameObject(float ratioX1,float ratioY1,BufferedImage image){
        ratioX = ratioX1;
        ratioY = ratioY1;

        x = (int)(ratioX1*(float)GameMap.width);
        y = (int)(ratioY1*(float)GameMap.height);

        this.xsize = GameMap.width/Parser.numberOfColumns;
        this.ysize = GameMap.width/Parser.numberOfRows;
        this.image = image;

        ratioX=(float)this.x/(float)GameMap.width;
        ratioY=(float)this.y/(float)GameMap.height;

        //pozycje względne

    }

    /**
     *
     * funkcja rysująca obiekt, jako parametr przyjmuje kontekst graficzny
     */
    public void draw(Graphics g) {





       // System.out.println(ratioX + " " + this.x + " " + GameMap.height);


        x = (int)(ratioX*(float)GameMap.width);
        y = (int)(ratioY*(float)GameMap.height);



        //System.out.println(ratioX + " " + ratioY);
        xsize = GameMap.width/Parser.numberOfColumns;
        ysize = GameMap.height/Parser.numberOfRows;

        g.drawImage(image, this.x,this.y, xsize, ysize,null);

        //System.out.println(this.x + " " + this.y + " " );


       /* System.out.print(x);
        System.out.print(" ");
        System.out.print(y);
        System.out.println();*/
    }


    /**
     * ustawia @param x
     */
    public void setX(int x){
        this.x = x;
    }


    /**
     * ustawia @param y
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * zwraca obecny x
     */
    public int getX(){
        return x;
    }

    /**
     * zwraca obecny y
     */
    public int getY(){
        return y;
    }

    public void rescale()
    {

    }


}


