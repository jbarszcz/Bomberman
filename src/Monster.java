import java.awt.image.BufferedImage;

/**
 * Klasa opisująca przeciwników
 */
public class Monster extends GameObject {

    public int speed;

    public Monster(int x, int y, int xsize, int ysize, BufferedImage image)
    {
        super(x,y,xsize,ysize,image);
        this.isBreakable = true;

        speed = Parser.MonsterSpeed;

    }

}
