import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa tworząca główne menu oraz urchomiająca podmenu oraz grę
 */
public class Mainmenu {
    /**
     * Tworzymy dla menu i podmenu ramki, panele, przyciski, checkboxy
     */
    JFrame f = new JFrame("Menu główne");
    JFrame f2 = new JFrame("Podmenu - Najlepsze Wyniki");
    JFrame f3 = new JFrame("Podmenu - Ustawienia");

    JPanel p = new JPanel(new GridBagLayout());  // Panel mainowego menu
    JPanel p2 = new JPanel(new GridBagLayout()); // Panel do highscorów
    JPanel p3 = new JPanel(new GridBagLayout()); // Panel do setingsow

    JButton b1 = new JButton("Play");
    JButton b2 = new JButton ("High score");
    JButton b3 = new JButton ("Settings");
    JButton b4 = new JButton ("Exit");

    JButton powrotny = new JButton("Powrót");
    JButton powrotny2 = new JButton("Powrót");

    JLabel l1 = new JLabel("Dyna Blaster Alfa 1.0");
    JLabel l2 = new JLabel("Highscore");
    JLabel l3 = new JLabel("Settings");

    String[] items = {"łatwy","średni", "trudny"};
    JComboBox level = new JComboBox(items);

    Object rowData[][] = { { "1.", "Grażyna", "1111"},
            { "2.", "Janusz", "999"},
            { "3.", "Dzesika", "699"},
            { "4.", "Brajnek", "599"},
            { "5.", "Bomberman", "499"},
            { "6.", "Barszcz", "399"},
            { "7.", "Wojtas", "299"},
            { "8.", "User", "199"},
            { "9.", "On", "99"},
            { "10.", "John", "9"},
    };
    Object columnNames[] = { "L.p", "User", "Result"};
    JTable tab1 = new JTable(rowData, columnNames);

    JTextField txt1 = new JTextField("Bomberman");


    GridBagConstraints c = new GridBagConstraints();

    /**
     * Konstruktor klasy Mainmenu wywołujący metode gui()
     */
    public Mainmenu(){

        gui();
    }

    /**
     * Metoda gui odpowiedzalana za rysowanie wszystkich elementów oraz właściwy ich układ
     * Metoda, także obsługuje zdarzenia (np. przechodzenie do podmenu)
     */
    public void gui(){
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(Parser.GameWindowWidth , Parser.GameWindowHeight);
        f.setVisible(true);
        f2.setSize(Parser.GameWindowWidth, Parser.GameWindowHeight);
        f3.setSize(Parser.GameWindowWidth, Parser.GameWindowHeight);



        c.insets=new Insets(10, 10, 10, 10);  //to jest odpowiedzialne za szpary

        /**
         * Tworzymy pod menu Highscore
         */
        p2.add(l2, c);
        c.gridx = 0;
        c.gridy = 1;
        p2.add(tab1, c);
        c.gridx = 0;
        c.gridy = 2;
        p2.add(powrotny, c);
        f2.add(p2);



        /**
         * Tworzymy pod menu Settings
         */

        c.gridx = 0;
        c.gridy = 0;
        p3.add(new JLabel("Settings"),c);

        JPanel tmp = new JPanel(new GridBagLayout());   //po to zeby Setting i Powrot byly wysrodkowane, bylo na wykladzie
        c.gridx = 0;
        c.gridy = 1;
        tmp.add(new JLabel("Nick: "),c);
        c.gridx = 1;
        c.gridy = 1;
        tmp.add(txt1,c);
        c.gridx = 0;
        c.gridy = 2;
        tmp.add(new JLabel("Level: "),c);
        c.gridx = 1;
        c.gridy = 2;
        tmp.add(level,c);
        c.gridx = 0;
        c.gridy = 2;
        p3.add(tmp,c);

        c.gridx = 0;
        c.gridy = 3;
        p3.add(powrotny2,c);

        f3.add(p3);



        /**
         * Tworzymy menu główne z 4 przycskimi
         */
        c.gridx=0;
        c.gridy=0;
        p.add(l1, c);

        c.gridx = 0;
        c.gridy = 1;
        p.add(b1,c);

        c.gridx = 0;
        c.gridy = 2;
        p.add(b2,c);

        c.gridx = 0;
        c.gridy = 3;
        p.add(b3,c);

        c.gridx = 0;
        c.gridy = 4;
        p.add(b4,c);

        f.add(p);





        /**
         * Obługa zdarzenia przycisku START
         */

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // ODPALIC PLANSZE TRZEBA TUTAJ
                f.setVisible(false);
                new GameWindow();
                //f.dispose();

            }
        });
        /**
         * Obsługa zdarzenia przycisku Highscore
         */
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f2.setVisible(true);
                f.setVisible(false);
                powrotny.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.setVisible(true);
                        f2.setVisible(false);

                    }
                });
            }
        });

        /**
         * Obsługa zdarzenia przycisku SETTINGS
         */
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f3.setVisible(true);
                f.setVisible(false);
                powrotny2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.setVisible(true);
                        f3.setVisible(false);

                    }
                });
            }
        });

        /**
         * Obłsuga zdarzenia przycisku EXIT
         */
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        /**
         * Obługa zdarzenia obszaru tekstu, w którym użytownik może zmienić nick postaci
         */
        txt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user;
                user = txt1.getText();
                System.out.println("Wpisany nick: " + user);

                // mozna tego string gdzies dalej przekazac


            }
        });
        /**
         * Obłsuga zdarzenia checkbox, w którym użytkownik może wybrać poziom planszy
         */
        level.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int levelek;
                levelek = level.getSelectedIndex(); //czyli 0 - łatwy, 1 - sredni, 2 - trudny
                System.out.println("Wybrany level: " + levelek);

                // mozna tego inta gdzies dalej przekazac


            }
        });




    }

}
