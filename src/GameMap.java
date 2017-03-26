import sun.security.krb5.internal.PAData;

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


    public GameMap(String levelNumber) {

        vGameObjects = new Vector<>();

        Parser.loadLevel("src/level" + levelNumber +  ".txt",this);


       // repaint();
    }


    public void paint(Graphics g){


        for(GameObject go:vGameObjects){ //narysuj każdy obiekt w vGameObjects
            go.draw(g);
        }


    }

}
