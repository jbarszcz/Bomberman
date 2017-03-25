import java.awt.image.BufferedImage;

/**
 * Klasa ściany
 */
public class Wall extends GameObject {

    public Wall(int x, int y, int xsize, int ysize, BufferedImage image)
    {
        super(x,y,xsize,ysize,image);
        isBreakable = false;

    }

}
