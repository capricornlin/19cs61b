
package bearmaps;


import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> myPoints;


    /** You can assume points has at least size 1.*/
    public NaivePointSet(List<Point> points){
        myPoints = new ArrayList<>();
        //Iterator , need to check;
        for(Point p: points){
            myPoints.add(p);
        }
    }


    /**Returns the closest point to the inputted coordinates.
     * This should take θ(N) time where N is the number of points.*/
    @Override
    public Point nearest(double x, double y){
        Point best = myPoints.get(0);
        Point virtual = new Point(0,0);
        Point target = new Point(x,y);
        double bestdistance = virtual.distance(best,target);
        for(Point p :myPoints){
            double distance = virtual.distance(p,target);
            if(bestdistance > distance){
                bestdistance = distance;
                best = p;
            }
        }

        return best;

    }

    public static void main(String[] args){
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }

}
