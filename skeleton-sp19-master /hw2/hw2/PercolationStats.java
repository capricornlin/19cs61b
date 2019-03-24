package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {


    private Percolation p;
    private double means;
    private int open;
    private int o;
    private int times;


    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N,int T,PercolationFactory pf){
        if(N < 0 || T < 0) {
            throw new java.lang.IllegalArgumentException("Wrong!");
        }
        times = T;
        //int i = 0;
        for(int i = 0;i < T ;i+=1){
            p = pf.make(N);
            while(p.percolates()){
                int r1 = StdRandom.uniform(N);
                int r2 = StdRandom.uniform(N);
                pf.make(N).open(r1,r2);
                o = p.numberOfOpenSites();
            }
            open += o;
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return open/times;
    }

    // sample standard deviation of percolation threshold
    //public double stddev(){

    //}

    // low endpoint of 95% confidence interval
    //public double confidenceLow()

    // high endpoint of 95% confidence interval
    //public double confidenceHigh()


}
