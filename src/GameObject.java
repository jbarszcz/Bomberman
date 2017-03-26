import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * klasa abstrakcyjna większości obiektów w grze
 */
public abstract class GameObject {
    private int x; // współrzędna x - rozmiar okna podzielony przez numer wiersza
    private int y; //współrzędna y - rozmiar okna podzielony przez numer kolumny
    private int xsize; //szerokość obiektu
    private int ysize; //długość obiektu
    private BufferedImage image; //obrazek obiektu
    public boolean isBreakable; // czy obiekt jest zniszczalny przez bombę


    public GameObject(int x,int y,int xsize,int ysize,BufferedImage image){

        this.x = x;
        this.y = y;
        this.xsize = xsize;
        this.ysize = ysize;
        this.image = image;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, xsize, ysize,null);
    }

// zestaw getów i setów
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}


