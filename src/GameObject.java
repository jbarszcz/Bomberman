import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * klasa abstrakcyjna większości obiektów w grze
 */
public abstract class GameObject {
    private int x;
    private int y;
    private int xsize;
    private int ysize;
    private BufferedImage image;
    public boolean isBreakable;


    public GameObject(int x,int y,int xsize,int ysize,BufferedImage image){

        this.x = x;
        this.y = y;
        this.xsize = xsize;
        this.ysize = ysize;
        this.image = image;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, xsize, ysize,null);
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}


