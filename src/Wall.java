import java.awt.image.BufferedImage;

/**
 * Klasa sciany niezniszczalnej
 */
public class Wall extends GameObject {
    /**
     * kosntruktor przyjmujacy parametry niezbedne do narysowania
     */
    public Wall(int x, int y, int xsize, int ysize, BufferedImage image)
    {
        super(x,y,xsize,ysize,image);
        isBreakable = false;

    }

}
