import java.awt.image.BufferedImage;

/**
 * Klasa bonusów
 */
public class Bonus extends GameObject {
    int bonusType;

    public Bonus(int x, int y, int xsize, int ysize, BufferedImage image, int type)
    {
        super(x,y,xsize,ysize,image);
        bonusType = type;

    }

    public void actionWhenMet(){};
}
