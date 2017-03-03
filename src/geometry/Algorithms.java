package geometry;

import java.util.*;

import static geometry.Utils.*;

public class Algorithms {

    private Algorithms() {
    }

    public static void grahamScan(Vector<Point> points, Vector<Segment> segments){
        Point lowestPoint = getLowestPoint(points);
        Point horizontalBuffer = new Point(lowestPoint.x - 50, lowestPoint.y);

        for (Point point : points)
            addCoords(point, lowestPoint, horizontalBuffer);

        quickSort(points);

        /*for(int i = 0; i < points.size() - 1; ++i){
            segments.add(new Segment(points.get(i), points.get(i + 1)));
        }
        segments.add(new Segment(points.firstElement(), points.lastElement()));*/

        Stack<Point> stack = makeConvex(points);

        createSegments(segments, stack);
    }
}
