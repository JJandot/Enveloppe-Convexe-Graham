package geometry;

import java.util.Collections;
import java.util.Vector;

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
