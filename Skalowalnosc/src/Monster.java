import java.awt.image.BufferedImage;

/**
 * Klasa opisująca przeciwników
 */
public class Monster extends GameObject {
    /**
     * predkosc potworow
     */
    public int speed;

    public float dX=1; //kierunek zmiany
    public float dY=0;
    public int d_monster_state=2; // gdzie: 1 - UP, 2- RIGHT, 3 - DOWN, 4 - LEFT

    /**
     * kosntruktor przyjmujacy parametry niezbedne do narysowania
     */
    public Monster(int x, int y, int xsize, int ysize, BufferedImage image)
    {
        super(x,y,xsize,ysize,image);
        this.isBreakable = true;

        speed = Parser.MonsterSpeed;

    }

    public void move()
    {
        //dX=0;
        //dY=1;
        this.ratioX+= dX*(float)0.004;
        this.ratioY+= dY*(float)0.004;

        //this.setX(this.getX()+1);
        //this.setY(this.getY()+0);
        //System.out.println("xDDD");

    }
    public void changedirect(){


        int wherego = (int) ((Math.random()*4)+1);

        while(wherego==d_monster_state){
            wherego = (int) ((Math.random()*4)+1);  //zapobiegam wybraniu tego samego kierunku
        }

        switch(wherego){
            case(1): //UP
                dX=0;
                dY=-1;
                d_monster_state=1;
                break;
            case(2): //RIGHT
                dX=1;
                dY=0;
                d_monster_state=2;
                break;
            case(3): //DOWN
                dX=0;
                dY=1;
                d_monster_state=3;
                break;
            case(4): //LEFT
                dX=-1;
                dY=0;
                d_monster_state=4;
                break;
            default: break;
        }
    }

}
