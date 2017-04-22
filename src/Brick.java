import java.awt.image.BufferedImage;

/**
 * Klasa sciany zniszczalnej
 */
public class Brick extends GameObject {
    /**
     * kosntruktor przyjmujacy parametry niezbedne do narysowania
     */
    public Brick(int x, int y, int xsize, int ysize, BufferedImage image)
    {
        super(x,y,xsize,ysize,image);
        this.isBreakable = true;

    }

}

