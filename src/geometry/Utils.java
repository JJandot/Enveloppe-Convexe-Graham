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

    static boolean isPositive(Point extSegment, Point s1, Point s2){
        return crossProduct(s2, extSegment, s1) > 0;
    }

    static double crossProduct(Point ext1, Point origine, Point ext2){
        return (((ext2.x - origine.x) * (ext1.y - origine.y)) - ((ext1.x - origine.x) * (ext2.y - origine.y)));
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
