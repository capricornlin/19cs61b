package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;
//Vertex is a Generic name lol.
public class AStarSolver<Vertex> implements  ShortestPathsSolver<Vertex> {
    private double timeSpent;
    private List<Vertex> solution = new ArrayList<>();
    //private List<Double> distTo = new ArrayList<>();
    //private double[] distTo = new double[1000];
    private Map<Vertex,Double> dist = new HashMap<>();
    private Map<Vertex,Vertex> edgeTo = new HashMap<>();
    private SolverOutcome outcome;
    private double solutionWeight;
    private int Numdeque = 0;

    /** Constructor which finds the solution, computing everything necessary
     * for all other methods to return their results in constant time. Note that
     * timeout passed in is in seconds.
     * when deque Vertex end then stop running.
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        Stopwatch sw = new Stopwatch();
        DoubleMapPQ<Vertex> pq = new DoubleMapPQ<>();    // PriorityQueue
        /** 1. start with vertex start's neighbors Edge.
         * */
        List<WeightedEdge<Vertex>> neighborsEdge = input.neighbors(start);
        Vertex next = start;
        dist.put(start,0.0);
        double h = input.estimatedDistanceToGoal(start,end);
        pq.add(start,h);
        pq.removeSmallest();
        //distTo[0] = 0;

        /** 2. Relax Edge until the " pq's Smallest " equal to " Vertex end ".
         * */
        Vertex smallest = start;
        //stop when we find Vertex goal.
        while(!(smallest.equals(end))) {
            //which edge from start are bigger
            for (WeightedEdge<Vertex> e : neighborsEdge) {

                relax(e, input, end, pq);
            }
            //choose the path to goal.
            smallest = pq.getSmallest();
            pq.removeSmallest();
            Numdeque+=1;
            next = smallest;
            neighborsEdge = input.neighbors(next);
            timeSpent = sw.elapsedTime();
        }

        solpath(start,end,input);

        if(smallest.equals(end)){
            outcome = SolverOutcome.SOLVED;
        }else {
            outcome = SolverOutcome.UNSOLVABLE;
            timeSpent = sw.elapsedTime();
        }

    }

    /** relax Edge method. */
    public void relax(WeightedEdge<Vertex> e,AStarGraph<Vertex> input,Vertex goal,DoubleMapPQ<Vertex> pq){
        // Edge e: from p ,to q ,weight w.
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        if(!dist.containsKey(q)){
            dist.put(q,dist.get(p) + w );
            double disto = dist.get(q);
            double distance = disto + input.estimatedDistanceToGoal(q,goal);
            edgeTo.put(q,p);
            // if pq has vertex q then changepriority, if not add to the pq.
            if (pq.contains(q)) {
                pq.changePriority(q, distance);
            } else {
                pq.add(q, distance);
            }
        }
        else if(dist.get(p) + w < dist.get(q)){
            double disto = dist.get(p) + w;
            double distance = disto + input.estimatedDistanceToGoal(q,goal);
            edgeTo.put(q,p);
            dist.put(q,distance);
            // if pq has vertex q then changepriority, if not add to the pq.
            if (pq.contains(q)) {
                pq.changePriority(q, distance);
            } else {
                pq.add(q, distance);
            }
        }

    }

    /** The solution path and total weight. */
    public void solpath(Vertex start,Vertex end,AStarGraph<Vertex> input) {
        List<Vertex> road = new ArrayList<>();
        Vertex a = end;
        while (!a.equals(start)) {
            Vertex edge = edgeTo.get(a);
            road.add(edge);
            a = edge;
        }
        int x = road.size();
        for (int i = x - 1; i >= 0; i -= 1) {
            Vertex b = road.get(i);
            solution.add(b);
        }
        solution.add(end);
        for (int i = 0; i < solution.size(); i++) {
            Vertex k = solution.get(i);
            List<WeightedEdge<Vertex>> n = input.neighbors(start);
            for (WeightedEdge<Vertex> e : n) {
                if (e.to().equals(k)) {
                    solutionWeight += e.weight();
                }
            }
            start = k;
        }
    }


    /** Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or
     * SolverOutcome.UNSOLVABLE. Should be SOLVED if the AStarSolver was able
     * to complete all work in the time given. UNSOLVABLE if the priority queue
     * became empty. TIMEOUT if the solver ran out of time. You should check to
     * see if you have run out of time every time you dequeue.*/
    @Override
    public SolverOutcome outcome(){
        return outcome;

    }

    /** A list of vertices corresponding to a solution. Should be empty if
     * result was TIMEOUT or UNSOLVABLE.*/
    @Override
    public List<Vertex> solution(){
        return solution;
    }

    /** The total weight of the given solution, taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE.*/
    @Override
    public double solutionWeight(){

        return solutionWeight;
    }

    /**  The total number of priority queue dequeue operations.*/
    @Override
    public int numStatesExplored(){
        return Numdeque;
    }

    /** The total time spent in seconds by the constructor.*/
    @Override
    public double explorationTime(){
        return timeSpent;
    }

}

