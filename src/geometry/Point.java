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

    double angle;
    double distance;

    /**
     * Constructeur avec initialisation de x et y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", angle=" + angle +
                '}';
    }
}