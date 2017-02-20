package geometry;

import java.util.*;

import static geometry.Utils.*;

public class Algorithms {

    private Algorithms() {
    }

    public static void grahamScan(Vector<Point> points){
        Point lowestPoint = getMaxYPoint(points);
        Point horizontalBuffer = new Point(lowestPoint.x + 50, lowestPoint.y);

        for (Point point : points){
            point.angle = crossProduct(horizontalBuffer, lowestPoint, point);
        }
        Utils.quickSort(points);
    }
}
