import java.awt.image.BufferedImage;

/**
 * Klasa bonusów
 */
public class Bonus extends GameObject {
    /**
     * ID bonusu - w zaleznosci od wartosci bonus bedzie wywolywal rozne efekty po zdobyciu
     */
    int bonusType;
    /**
     * kosntruktor przyjmujacy parametry niezbedne do narysowania
     */
    public Bonus(int x, int y, int xsize, int ysize, BufferedImage image, int type)
    {
        super(x,y,xsize,ysize,image);
        bonusType = type;

    }

    /**
     * funkcja opisujaca dzialanie bonusu po jego zdobyciu
     */

    public void catched(){


        switch(bonusType){
            case(1): Bomber.lifesLeft++;
                break;

            case(2): GameMap.numberOfPoints+=100;
                break;

                default: break;
        }

    };
}
