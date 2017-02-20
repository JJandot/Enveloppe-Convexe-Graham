package geometry;

import java.util.*;

import static geometry.Utils.*;

public class Algorithms {

    private Algorithms() {
    }

    public static void grahamScan(Vector<Point> points){
        System.out.println(points);
        Point lowestPoint = getMaxYPoint(points);
        Point horizontalBuffer = new Point(lowestPoint.x + 50, lowestPoint.y);

        for (Point point : points){
            point.angle = crossProduct(horizontalBuffer, lowestPoint, point);
        }

        Utils.quickSort(points);
        System.out.println(points);

        Stack<Point> stack = new Stack<>();
        stack.push(points.firstElement());
        stack.push(points.elementAt(1));

        for(int i = 2; i < points.size(); ++i){
            while (stack.size() >= 2 && isPositive(points.get(i), stack.elementAt(1), stack.firstElement())){
                stack.pop();
            }
            stack.push(points.get(i));
        }

        System.out.println(stack);
    }
}
