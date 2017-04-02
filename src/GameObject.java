import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * klasa abstrakcyjna większości obiektów w grze
 */
public abstract class GameObject {
    /**
     * współrzędna x - rozmiar okna przez numer wiersza
     */
    private int x;
    /**
     * współrzędna y - wysokosc okna przez numer kolumny
     */
    private int y;
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
    public GameObject(int x,int y,int xsize,int ysize,BufferedImage image){

        this.x = x;
        this.y = y;
        this.xsize = xsize;
        this.ysize = ysize;
        this.image = image;
    }

    /**
     *
     * funkcja rysująca obiekt, jako parametr przyjmuje kontekst graficzny
     */
    public void draw(Graphics g) {
        g.drawImage(image, x, y, xsize, ysize,null);

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

}


