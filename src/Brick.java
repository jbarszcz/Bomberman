import java.awt.image.BufferedImage;

/**
 * Klasa ściany
 */
public class Brick extends GameObject {

    public Brick(int x, int y, int xsize, int ysize, BufferedImage image)
    {
        super(x,y,xsize,ysize,image);
        this.isBreakable = true;

    }

}
