package geometry;

import java.util.*;

import static geometry.Utils.*;

public class Algorithms {

    private Algorithms() {
    }

    public static Vector<Segment> grahamScan(Vector<Point> points, Vector<Segment> segments){
        Point lowestPoint = getLowestPoint(points);
        Point horizontalBuffer = new Point(lowestPoint.x - 50, lowestPoint.y);

        for (Point point : points) {
            addCoords(point, lowestPoint, horizontalBuffer);
        }
        Vector<Point> sortedPoints = quickSort(points);
        for(int i = 0; i < sortedPoints.size() - 1; ++i){
            segments.add(new Segment(sortedPoints.get(i), sortedPoints.get(i + 1)));
        }
        segments.add(new Segment(sortedPoints.firstElement(), sortedPoints.lastElement()));

        Stack<Point> stack = makeConvex(sortedPoints);
        return createSegments(stack);
    }
}
