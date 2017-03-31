import geometry.Algorithms;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/** La classe ZoneSaisirPointsAfficherSegments. */
class ZonePoints extends JPanel  {

    private CanvasSaisirPointsAfficherSegments canvas;

    /** Creation de la zone d'affichage. */
    ZonePoints(){
        canvas = new CanvasSaisirPointsAfficherSegments();

        // Panel des boutons
        JPanel panelBoutons = new JPanel();

        final JTextField textNombrePoint = new JTextField("50");
        textNombrePoint.setColumns(5);

        JButton rand = new JButton("Rand Points");
        rand.addActionListener(e -> doRandomPoints(Integer.parseInt(textNombrePoint.getText())));

        JButton enveloppe = new JButton("Create convex");
        enveloppe.addActionListener(e -> makeConvex());

        JButton effacer = new JButton("Effacer");
        effacer.addActionListener(evt -> clearScreen());

        panelBoutons.add(effacer);
        panelBoutons.add(rand);
        panelBoutons.add(textNombrePoint);
        panelBoutons.add(enveloppe);
        setLayout(new BorderLayout());

        add(canvas, BorderLayout.CENTER);

        add(panelBoutons, BorderLayout.SOUTH);
    }

    private void doRandomPoints(int nbPoints){
        canvas.points.removeAllElements();
        canvas.segments.removeAllElements();
        Random random = new Random();
        for(int i = 0; i < nbPoints; ++i){
            canvas.points.add(new geometry.Point(2 + random.nextDouble() * (canvas.getWidth() - 5), 2 + random.nextDouble() * (canvas.getHeight() - 5)));
        }
        canvas.repaint();
    }

    private void clearScreen(){
        canvas.points.removeAllElements();
        canvas.segments.removeAllElements();
        canvas.repaint();
    }

    private void makeConvex() {
        canvas.segments.removeAllElements();
        long initTime = System.nanoTime();
        canvas.segments = Algorithms.grahamScan(canvas.points, canvas.segments);
        //System.out.println("Travail effectué en : " + ((System.nanoTime() - initTime)/ 1000000) + " milli-secondes");
        System.out.println("Travail effectué en : " + (System.nanoTime() - initTime) + " nanosecondes");
        canvas.repaint();
    }
}