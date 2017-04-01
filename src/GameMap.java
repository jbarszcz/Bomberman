import sun.security.krb5.internal.PAData;
import sun.security.util.Length;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Klasa przechowująca mapę gry
 */
public class GameMap extends JPanel

{
    public Vector<GameObject> vGameObjects;
    Bomber bomber;
    int numberOfPoints=500;


    public GameMap(String levelNumber) {

        vGameObjects = new Vector<>();


        Parser.loadLevel("src/level" + levelNumber +  ".txt",this);


       // repaint();
    }


    public void paint(Graphics g){


        for(GameObject go:vGameObjects){ //narysuj każdy obiekt w vGameObjects
            go.draw(g);
        }

        bomber.draw(g);
        drawHUD(g);

    }

    public void drawHUD(Graphics g){


        g.drawImage(Parser.lifeImage,GameWindow.lengthUnit,Parser.GameWindowHeight,GameWindow.lengthUnit,GameWindow.lengthUnit,null);
        g.setFont(new Font("Calibri",Font.PLAIN, GameWindow.lengthUnit));
        g.setColor(Color.white);

        String lifes = Integer.toString(bomber.lifesLeft);

        g.drawString("=" + lifes,2*GameWindow.lengthUnit,Parser.GameWindowHeight + GameWindow.lengthUnit);
        g.drawString("Score=" + Integer.toString(numberOfPoints),4*GameWindow.lengthUnit,Parser.GameWindowHeight+GameWindow.lengthUnit);

    }

}
