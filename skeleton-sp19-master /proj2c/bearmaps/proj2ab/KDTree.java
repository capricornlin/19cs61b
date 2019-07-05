package bearmaps.proj2ab;
import java.util.List;

public class KDTree implements PointSet{
    private Node root;
    private final boolean horizontal = false;
    private final boolean vertical = true;

    private class Node{
        Point p;
        Node leftchild,rightchild;
        boolean orieantation = false;

        public Node(Point x,boolean d){
            p = x;
            orieantation = d;
        }
    }

    public KDTree(List<Point> points){
        for(Point p: points){
            insert(p);
        }
    }

    public void insert(Point p){
        root = insert(root,p,horizontal);
    }

    private Node insert(Node n,Point p,boolean d){
        if(n == null){
            return new Node(p,d);
        }
        if(n.p.getX() == p.getX() && n.p.getY() == p.getY()){
            return new Node(p,d);
        }
        //false = horizontal
        if(n.orieantation == horizontal){
            double px = p.getX();
            double cmp = px - n.p.getX();
            if(cmp >= 0){
                n.rightchild = insert(n.rightchild,p,vertical);
            }else{
                n.leftchild = insert(n.leftchild,p,vertical);
            }
        }
        //true = vertical
        else{
            double py = p.getY();
            double cmp = py - n.p.getY();
            if(cmp >= 0){
                n.rightchild = insert(n.rightchild,p,horizontal);
            }else{
                n.leftchild = insert(n.leftchild,p,horizontal);
            }
        }
        return n;
    }

    @Override
    //first implement inefficient version.
    public Point nearest(double x, double y){
        Point goal = new Point(x,y);
        Point p = nearest(root,goal,root).p;
        return p;
    }


    private  Node nearest(Node n, Point goal, Node best){
        Node goodside,badside;
        //n is null return best
        if(n == null){
            return best;
        }else{
            if(Point.distance(n.p,goal) <= Point.distance(best.p,goal)) {
                best = n;
            }
            // compare x.
            if(n.orieantation == horizontal){
                if(n.p.getX() > goal.getX()){
                    goodside = n.leftchild;
                    badside = n.rightchild;
                }else{
                    goodside = n.rightchild;
                    badside = n.leftchild;
                }
            }else{//compare y
                if(n.p.getY() < goal.getY()){
                    goodside = n.rightchild;
                    badside = n.leftchild;
                }else{
                    goodside = n.leftchild;
                    badside = n.rightchild;
                }
            }
            best = nearest(goodside,goal,best);
            //If bad side could still have something useful
            if(n.orieantation == horizontal){
                double x = n.p.getX();
                double bestx = best.p.getX();
                double besty = best.p.getY();
                double bestdis = Math.pow(bestx,2)+ Math.pow(besty,2);
                if(Math.pow(x-bestx,2) < bestdis){
                    best = nearest(badside,goal,best);
                }
            }else{
                double y = n.p.getY();
                double bestx = best.p.getX();
                double besty = best.p.getY();
                double bestdis = Math.pow(bestx,2)+ Math.pow(besty,2);
                if(Math.pow(y-besty,2) < bestdis){
                    best = nearest(badside,goal,best);
                }

            }
            //best = nearest(badside,goal,best);
        }
        return best;
    }


    public static void main(String[] args){
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        //KDTree kd = new KDTree(List.of(p1, p2, p3,p4,p5,p6,p7));
        //kd.insert(p7);
        //kd.nearest(0,7);
    }
}

