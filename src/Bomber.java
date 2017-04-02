import java.awt.image.BufferedImage;

/**
 * Klasa opisująca bombera
 */
public class Bomber extends GameObject {
    /**
     * predkosc bombera
     */
    public int speed;
    /**
     * pozostala liczba zyc
     */
    public int lifesLeft;

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

}
