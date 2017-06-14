import java.awt.image.BufferedImage;

/**
 * Klasa bomby
 */
public class Bomb extends GameObject {
    /**
     * kosntruktor przyjmujacy parametry niezbedne do narysowania
     */

    int timer=0;
    public Bomb(int x, int y, int xsize, int ysize, BufferedImage image)
    {
        super(x,y,xsize,ysize,image);
        this.isBreakable = true;
    }


    public Bomb(float ratioX, float ratioY, BufferedImage bombImage) {

        super(ratioX ,ratioY, bombImage);
    }
}

