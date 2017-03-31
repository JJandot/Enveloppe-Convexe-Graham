import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class App  {

    public static void main(String[] args) {
        // Construction de la fenetre
        JFrame frame = new JFrame("Enveloppe convexe : Graham");

        // Construction de la zone d'affichage
        ZonePoints zoneAffichage = new ZonePoints();

        // Ajout de la zone d'affichage a la fenetre
        frame.getContentPane().add(zoneAffichage);

        // Dimension de la zone d'affichage
        zoneAffichage.setPreferredSize(new Dimension(800,600));

        // Resize autour de la zone d'affichage
        frame.pack();

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Affichage de la fenetre
        frame.setVisible(true);
    }
}
