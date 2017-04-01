import java.awt.image.BufferedImage;

/**
 * Klasa opisująca bombera
 */
public class Bomber extends GameObject {

    public int lives;
    public int speed;
    public int lifesLeft=Parser.NumberOfLifes;

    public Bomber(int x, int y, int xsize, int ysize, BufferedImage image)
    {
        super(x,y,xsize,ysize,image);
        this.isBreakable = false;
        this.lifesLeft = Parser.NumberOfLifes;
        lives = Parser.NumberOfLifes;
        speed = Parser.BomberSpeed;

    }

}
