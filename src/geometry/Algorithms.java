package geometry;

import java.util.*;

import static geometry.Utils.*;

public class Algorithms {

    private Algorithms() {
    }

    public static void grahamScan(Vector<Point> points, Vector<Segment> segments){
        Point lowestPoint = getMaxYPoint(points);
        Point horizontalBuffer = new Point(lowestPoint.x - 50, lowestPoint.y);

        for (Point point : points)
            addCoords(point, lowestPoint, horizontalBuffer);

        Utils.quickSort(points);

        for(int i = 0; i < points.size() - 1; ++i){
            segments.add(new Segment(points.get(i), points.get(i + 1)));
        }
        segments.add(new Segment(points.firstElement(), points.lastElement()));

        Stack<Point> stack = new Stack<>();
        stack.push(points.get(0));
        stack.push(points.get(1));


        for(int i = 2; i < points.size(); ++i){
            Point second = stack.pop();
            Point first = stack.peek();
            Point origin = points.get(i);

            double product = crossProduct(first, second, origin);

            if(product > 0){
                stack.push(second);
                stack.push(origin);
            }
            else if(product < 0){
                --i;
            }
            else {
                stack.push(origin);
            }
        }
        segments.removeAllElements();
        for(int i = 0; i < stack.size() - 1; ++i){
            segments.add(new Segment(stack.get(i), stack.get(i + 1)));
        }
        segments.add(new Segment(stack.firstElement(), stack.lastElement()));
    }
}
