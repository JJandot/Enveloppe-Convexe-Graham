package geometry;

import java.util.Vector;

public class Utils {

    private Utils() {
    }

    public static Point getMaxYPoint(Vector<Point> points){
        Point p = points.firstElement();
        for (Point point : points){
            if (point.y > p.y)
                p = point;
        }
        return p;
    }

    private static double getCrossProduct(double vecteur1x, double vecteur2x, double vecteur1y, double vecteur2y){
        return ((vecteur1x * vecteur2y) - (vecteur2x * vecteur1y));
    }
}
