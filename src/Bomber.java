import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Klasa opisująca bombera
 */
public class Bomber extends GameObject{
    /**
     * predkosc bombera
     */
    public int speed;
    /**
     * pozostala liczba zyc
     */
    public static int lifesLeft;
    int dx;
    int dy;

    /**
     *konstruktor klasy bomber. W odroznieniu do GameObject ustwia jeszcze liczbe zyc oraz predkosc
     */
    public Bomber(int x, int y, int xsize, int ysize, BufferedImage image)
    {
        super(x,y,xsize,ysize,image);
        this.isBreakable = false;
        this.lifesLeft = Parser.NumberOfLifes;
        speed = Parser.BomberSpeed;

    }



    public void move(int dX, int dY){

        x+=dx*speed;
        y+=dy*speed;

        System.out.println(x);
        System.out.println(y);

    }



}
