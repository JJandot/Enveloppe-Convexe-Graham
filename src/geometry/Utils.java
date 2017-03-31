package geometry;

import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

import static java.lang.Math.acos;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class Utils {

    private Utils() {}

    /**
     * Parcours un vecteur de points pour en trouver le plus bas (celui avec la coordonée <code>y</code> la plus grande)
     * @param points le vecteur à parcourir
     * @return le point trouvé
     */
    static Point getLowestPoint(Vector<Point> points){
        Point p = points.firstElement();
        for (Point point : points){
            if (point.y > p.y)
                p = point;
        }
        return p;
    }

    /**
     * Calcule la distance entre deux points
     * @param point le premier point
     * @param lowestPoint le second point
     * @return la distance calculée
     */
    private static double calcDistance(Point point, Point lowestPoint){
        return sqrt(pow((point.x - lowestPoint.x), 2) + pow((point.y - lowestPoint.y), 2));
    }

    /**
     * Calcule l'angle entre le point le plus bas du repère, l'axe des abscisses et un autre point
     * @param point le point dont on veut connaitre la mesure de l'angle
     * @param lowestPoint le point d'origine de l'angle
     * @param horizontalBuffer le point horizontal à l'origine, représentant l'axe des abscisses
     * @return la valeur de l'angle formé, en degrés
     */
    private static double calcAngle(Point point, Point lowestPoint, Point horizontalBuffer){
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
        return Math.toDegrees(acos(cos));
    }

    /**
     * Appelle les fonctions <code>calcDistance</code> et <code>calcAngle</code> sur le point courant, pour lui affecter
     * ces coordonées.
     * @param point le point dont les coordonées vont être affectées
     * @param lowestPoint le point le plus bas du repère, utilisé pour la distance, et le calcul d'angle
     * @param horizontalBuffer le point représentant l'axe des abscisses, utilisé pour le calcul d'angle
     */
    static void addCoords(Point point, Point lowestPoint, Point horizontalBuffer){
        point.distance = calcDistance(point, lowestPoint);
        point.angle = calcAngle(point, lowestPoint, horizontalBuffer);
    }


    /**
     * Créée l'enveloppe convexe correspondant à un ensemble de points donné. La méthode utilisée est le parcours
     * de Graham
     * @param points le vector de points trié par angle
     * @return la pile contenant les sommets de l'enveloppe dans l'ordre du parcours
     */
    static Stack<Point> makeConvex(Vector<Point> points){
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
        return stack;
    }


    private static double crossProduct(Point originPoint, Point ext1, Point ext2){
        return  ((ext1.x - originPoint.x) * (ext2.y - originPoint.y)) - ((ext1.y - originPoint.y) * (ext2.x - originPoint.x));
    }

    static Vector<Point> quickSort(Vector<Point> points){
        Vector<Point> pointVector = new Vector<>(points);
        quickSort(pointVector, 0, pointVector.size() -1);
        return pointVector;
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

    static Vector<Segment> createSegments(Stack<Point> stack){
        Vector<Segment> segmentsFinal = new Vector<>();
        for(int i = 0; i < stack.size() - 1; ++i){
            segmentsFinal.add(new Segment(stack.get(i), stack.get(i + 1)));
        }
        segmentsFinal.add(new Segment(stack.firstElement(), stack.lastElement()));
        return segmentsFinal;
    }
}
