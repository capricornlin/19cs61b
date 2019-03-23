package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean [][] p;
    private int Num;
    private WeightedQuickUnionUF u;


    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        u = new WeightedQuickUnionUF(1000*1000);
        Num = N;
        p = new boolean[N][N];

        if(){
            throw new IllegalArgumentException("Fuck!!Wrong!!");
        }


        for(int i = 0;i < N ;i+=1){
            for(int j = 0; j < N; j+=1){
                p[i][j] = false;
            }
        }
    }

    // return a index of each (row,col)
    public int pocalationNum(int row,int col){
        int n;
        transferNum(row,col);
        n = row*Num + col;
        return n;
    }

    //transfer (row,col) to new coordinates
    public void transferNum(int row , int col){
        col = Num - 1 - col;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        int n;
        transferNum(row,col);
        p[row][col] = true;
        n = pocalationNum(row,col);

        if(){
            throw new IndexOutOfBoundsException("fuck!!! Out of boubds!");
        }

        //n's right
        if(isOpen(row,col+1) == true)
            u.union(n,pocalationNum(row,col+1));
        //n's left
        else if(isOpen(row,col-1) == true)
            u.union(n,pocalationNum(row,col-1));
        //n's up
        else if(isOpen(row-5,col) == true)
            u.union(n,pocalationNum(row-5,col));
        //n's botton
        else
            u.union(n,pocalationNum(row+5,col));

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(){
            throw new IndexOutOfBoundsException("fuck!!! Out of boubds!");
        }


        if(p[row][col] == true)
            return true;
        return false;

    }

    // is the site (row, col) full?
    public boolean isFull(int row ,int col){
        int n;
        transferNum(row,col);
        n = pocalationNum(row,col);

        if(){
            throw new IndexOutOfBoundsException("fuck!!! Out of boubds!");
        }

        for(int i = 0;i < Num ; i+=1){
            if(u.connected(n,i))
                return true;
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites(){
        int n = 0;
        for(int i = 0; i < Num ;i+=1){
            for(int j = 0;i <Num;j+=1){
                if(p[i][j] == true)
                    n+=1;
            }
        }
        return n;
    }

    // does the system percolate?
    public boolean percolates(){
        for(int i = 0;i <Num ;i+=1){
            u.union(i,999);
        }
        for(int i = Num*Num-1;i > Num*Num - Num-1;i-=1){
            u.union(i,1000);
        }
        if(u.connected(999,1000))
            return true;
        return false;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){



    }

}
