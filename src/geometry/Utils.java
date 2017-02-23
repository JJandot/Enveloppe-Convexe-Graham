package geometry;

import java.util.Collections;
import java.util.Vector;

import static java.lang.Math.acos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Utils {

    private Utils() {
    }

    static Point getMaxYPoint(Vector<Point> points){
        Point p = points.firstElement();
        for (Point point : points){
            if (point.y > p.y)
                p = point;
        }
        return p;
    }

    static void addCoords(Point point, Point lowestPoint, Point horizontalBuffer){
        point.distance = sqrt(pow((point.x - lowestPoint.x), 2) + pow((point.y - lowestPoint.y), 2));

        double x = point.x - lowestPoint.x;
        double y = point.y - lowestPoint.y;

        double x2 = horizontalBuffer.x - lowestPoint.x;
        double y2 = horizontalBuffer.y - lowestPoint.y;

        double scalarProduct = x*x2 + y*y2;

        double v1Size = sqrt(pow(x ,2) + pow(y ,2));
        double v2Size = sqrt(pow(x2 ,2) + pow(y2 ,2));

        double cos = scalarProduct / (v1Size * v2Size);
        if(Double.isNaN(cos)){
            cos = 1;
        }
        point.angle = Math.toDegrees(acos(cos));
    }

    static double crossProduct(Point originPoint, Point ext1, Point ext2){
        return  ((ext1.x - originPoint.x) * (ext2.y - originPoint.y)) -((ext1.y - originPoint.y) * (ext2.x - originPoint.x));
    }

    static void quickSort(Vector<Point> points){
        quickSort(points, 0, points.size() -1);
    }


    private static int partition(Vector<Point> points, int debut, int fin){
        int cpt = debut;
        double pivot = points.get(debut).angle;

        for (int i = debut + 1; i <= fin; ++i){
            if (points.get(i).angle < pivot){
                ++cpt;
                Collections.swap(points, cpt, i);
            }
        }
        Collections.swap(points, debut, cpt);
        return cpt;
    }

    private static void quickSort(Vector<Point> points, int debut, int fin){
        if(debut < fin){
            int pivot = partition(points, debut, fin);
            quickSort(points, debut, pivot - 1);
            quickSort(points, pivot + 1, fin);
        }
    }
}
