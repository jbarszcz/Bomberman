import java.io.*;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Vector;

/**
 * klasa służąca do odczytu i zapisu najlepszych wyników
 */
public class HighScores {



   public static Vector<String> names = new Vector<>();
    public static Vector<Integer> points = new Vector<>();


    HighScores() {

        loadScores();

    }

    public void saveScore(String nick, int score) throws IOException {

        for (int i = 0; i<points.size();i++) {

            if (score > points.elementAt(i)) {



                points.insertElementAt(score, i); //wstawiamy nowy wynik
                names.insertElementAt(nick, i);

                try{PrintWriter pw = new PrintWriter(new FileWriter("src/highscores.txt"));

                    for (i = 0; i < Parser.HighScoreNumber; i++)
                    {
                        pw.println(names.elementAt(i) + " " + points.elementAt(i));

                    }


                    pw.close();}

                    catch(IOException e) {
                System.out.println("IOexeption in saveScore");}


            }


            }
        loadScores();

        }




    public void loadScores(){

        try {

            names.removeAllElements();
            points.removeAllElements();
            /**
             * otwieranie pliku
             */

            File sciezka = new File("src/highscores.txt");
            FileReader plik = new FileReader(sciezka);
            BufferedReader odczyt = new BufferedReader(plik);
            String temp;

            int i = 0;
            while (i<Parser.HighScoreNumber) {
                temp = odczyt.readLine();
                //System.out.println(temp);

                int j=0;
                while (temp.charAt(j)!=' '){
                    j++;
                }
                String name = temp.substring(0,j);
                names.add(name);
                points.add(Integer.parseInt(temp.substring(j+1,temp.length())));
                System.out.println(names.elementAt(i) + " " + points.elementAt(i));

                i++;


            }


            odczyt.close();
        }
        catch (FileNotFoundException e) {System.out.println("Nie udało się odnaleźć pliku konfiguracyjnego");}
        catch (IOException e) {System.out.println("Błąd strumienia IO");}
    }
}
