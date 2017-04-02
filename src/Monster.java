import java.awt.image.BufferedImage;

/**
 * Klasa opisująca przeciwników
 */
public class Monster extends GameObject {
    /**
     * predkosc potworow
     */
    public int speed;

    /**
     * kosntruktor przyjmujacy parametry niezbedne do narysowania
     */
    public Monster(int x, int y, int xsize, int ysize, BufferedImage image)
    {
        super(x,y,xsize,ysize,image);
        this.isBreakable = true;

        speed = Parser.MonsterSpeed;

    }

}
