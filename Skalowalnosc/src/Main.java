import java.io.IOException;

/**
 * klasa Main, tworzy obiekt okna programu
 *
 */
public class Main {

    public static void main(String args[]) throws IOException {
        Parser parse = new Parser();
        HighScores hs = new HighScores();

       // hs.saveScore("janek",1100);

        //new GameWindow();
       new Mainmenu();

    }
}
