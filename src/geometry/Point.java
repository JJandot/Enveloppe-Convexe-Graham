package geometry;

/** La classe geometry.Point. */
public class Point {

    /**
     * La valeur de x.
     */
    public double x;

    /**
     * La valeur de y.
     */
    public double y;

    public boolean isUsed = false;

    /**
     * Constructeur avec initialisation de x et y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}