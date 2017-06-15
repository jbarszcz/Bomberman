import java.awt.image.BufferedImage;

/**
 * Klasa bomby
 */
public class Bomb extends GameObject {

    int timer = 0;
    /**
     * kosntruktor przyjmujacy parametry niezbedne do narysowania
     */
    public Bomb(int x, int y, int xsize, int ysize, BufferedImage image)
    {
        super(x,y,xsize,ysize,image);
        this.isBreakable = true;
    }


    public Bomb(float ratioX, float ratioY, BufferedImage bombImage) {
        super(ratioX,ratioY,bombImage);
    }
}
