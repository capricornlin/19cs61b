package bearmaps;

import edu.princeton.cs.introcs.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    private final Random r = new Random(500);

    private static KDTree buildLectureTree(){
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3,p4,p5,p6,p7));
        return kd;
    }


    @Test
    /** test with slides cases. */
    public void testNearstemoSlides(){
        KDTree kd = buildLectureTree();
        Point actual = kd.nearest(0,7);
        Point expected = new Point(1,5);
        assertEquals(actual,expected);
    }

    private Point randomPoint(){
        double x = r.nextDouble();
        double y = r.nextDouble();

        return new Point(x,y);
    }

    private List<Point> randomPoints(int N){
        List<Point> points = new ArrayList<>();
        for(int i = 0;i < N; i+=1){
            points.add(randomPoint());
        }
        return points;
    }
    private void testWithPandQ(int pointcounts,int querycounts) {
        List<Point> points = randomPoints(pointcounts);
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kd = new KDTree(points);

        List<Point> queries = randomPoints(querycounts);
        for (Point p : queries) {
            Point excepted = nps.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(excepted, actual);
        }
    }

    @Test
    public void test1000points200queries(){
        int pointcounts = 1000;
        int querycounts = 200;
        testWithPandQ(pointcounts,querycounts);
    }

    @Test
    public void test10000points2000queries(){
        int pointcounts = 10000;
        int querycounts = 2000;
        testWithPandQ(pointcounts,querycounts);
    }

    private void timeswithPandQ(int pointcounts,int querycounts) {
        List<Point> points = randomPoints(pointcounts);
        KDTree kd = new KDTree(points);
        Stopwatch sw = new Stopwatch();
        List<Point> queries = randomPoints(querycounts);
        for (Point p : queries) {
            Point actual = kd.nearest(p.getX(), p.getY());
        }
        System.out.println("Time slapsed for "+ querycounts + " queries on "+
                           pointcounts + " points " + sw.elapsedTime());
    }

    @Test
    public void timeth10000Pand2000Q(){
        timeswithPandQ(10000,2000);
    }

    @Test
    public void timeWithvariablesp(){
        List<Integer> pointscount = List.of(1000,10000,100000);
        for(int N:pointscount){
            timeswithPandQ(N,2000);
        }
    }

    @Test
    public void timeCompareNavieandkdtree(){
        List<Point> randompoints = randomPoints(100000);
        KDTree kd = new KDTree(randompoints);
        NaivePointSet nps = new NaivePointSet(randompoints);
        List<Point> quertPoints = randomPoints(10000);
        Stopwatch sw = new Stopwatch();
        for(Point p:quertPoints){
            nps.nearest(p.getX(),p.getY());
        }
        double time = sw.elapsedTime();
        System.out.println("Navie 10000 queries on 100000 points: " + time);

        sw = new Stopwatch();
        for(Point p:quertPoints){
            kd.nearest(p.getX(),p.getY());
        }
        time = sw.elapsedTime();
        System.out.println("KDtree 10000 queries on 100000 points: " + time);










    }









}
