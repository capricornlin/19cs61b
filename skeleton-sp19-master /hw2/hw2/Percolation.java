package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean [][] p;
    private int Num;
    private WeightedQuickUnionUF u;


    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        u = new WeightedQuickUnionUF(1000*1000);
        if(N < 0){
            throw new IllegalArgumentException("Indices smaller than zero.");
        }
        Num = N;
        p = new boolean[N][N];

        for(int i = 0;i < N ;i+=1){
            for(int j = 0; j < N; j+=1){
                p[i][j] = false;
            }
        }

        // union top line
        for(int i =0;i < N;i+=1){
            u.union(i,9999);
        }

        //union botton line
        for(int i =N*(N-1);i <N*N;i+=1){
            u.union(i,10000);
        }
    }

    // return a index of each (row,col) => xyTo1D
    public int pocalationNum(int col,int row){
        int n;
        int x = Num - 1 - col;
        n = (x-1)*Num+row;
        return n;
    }

    //change the coordinate index to the new coordinate index
    public int transferNum(int col){
        col = Num - 1 - col;
        return col;
    }

    // open the site (row, col) if it is not open already
    public void open(int col, int row){
        int n;
        if(col >= Num || col < 0 || row >= Num || row <0){
            throw new IndexOutOfBoundsException("Out of Bounds!");
        }
        n = pocalationNum(col,row);
        int x = transferNum(col);
        p[x][row] = true;

        if(x == 0){
            if(row == 0){
                if(isOpen(col,row+1) == true)
                    u.union(n,pocalationNum(col,row+1));
                else if(isOpen(col-1,row) == true)
                    u.union(n,pocalationNum(col-1,row));
            }else if(row == Num-1){
                if(isOpen(col,row-1) == true)
                    u.union(n,pocalationNum(col,row-1));
                else if(isOpen(col-1,row) == true)
                    u.union(n,pocalationNum(col-1,row));
            }else
            if(isOpen(col,row+1) == true)
                u.union(n,pocalationNum(col,row+1));
                //n's left
            else if(isOpen(col,row-1) == true)
                u.union(n,pocalationNum(col,row-1));
                //n's up
            else if(isOpen(col-1,row) == true)
                u.union(n,pocalationNum(col-1,row));
        }else if(x == Num -1){
            if(row == 0){
                if(isOpen(col,row+1) == true)
                    u.union(n,pocalationNum(col,row+1));
                else if(isOpen(col+1,row) == true)
                    u.union(n,pocalationNum(col+1,row));
            }else if(row == Num -1){
                if(isOpen(col,row-1) == true)
                    u.union(n,pocalationNum(col,row-1));
                else if(isOpen(col+1,row) == true)
                    u.union(n,pocalationNum(col+1,row));
            }else{
                if(isOpen(col,row+1) == true)
                    u.union(n,pocalationNum(col,row+1));
                    //n's left
                else if(isOpen(col,row-1) == true)
                    u.union(n,pocalationNum(col,row-1));
                else if(isOpen(col+1,row) == true)
                    u.union(n,pocalationNum(col+1,row));
            }

        }else if(row == 0){
            if(isOpen(col,row+1) == true)
                u.union(n,pocalationNum(col,row+1));
            else if(isOpen(col-1,row) == true)
                u.union(n,pocalationNum(col-1,row));
                //n's bottom
            else if(isOpen(col+1,row) == true)
                u.union(n,pocalationNum(col+1,row));
        }else if(row == Num-1){
            if(isOpen(col,row-1) == true)
                u.union(n,pocalationNum(col,row-1));
                //n's up
            else if(isOpen(col-1,row) == true)
                u.union(n,pocalationNum(col-1,row));
                //n's bottom
            else if(isOpen(col+1,row) == true)
                u.union(n,pocalationNum(col+1,row));
        }else {

            //n's right
            if (isOpen(col, row + 1) == true)
                u.union(n, pocalationNum(col, row + 1));
                //n's left
            else if (isOpen(col, row - 1) == true)
                u.union(n, pocalationNum(col, row - 1));
                //n's up
            else if (isOpen(col - 1, row) == true)
                u.union(n, pocalationNum(col - 1, row));
                //n's bottom
            else if (isOpen(col + 1, row) == true)
                u.union(n, pocalationNum(col + 1, row));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int col, int row){
        if(col >= Num || col < 0 || row >= Num || row <0){
            throw new IndexOutOfBoundsException("Out of Bounds!");
        }
        int x = transferNum(col);
        if(p[x][row])
            return true;
        return false;

    }

    // is the site (row, col) full?
    public boolean isFull(int col ,int row){
        if(col >= Num || col < 0 || row >= Num || row <0){
            throw new IndexOutOfBoundsException("Out of Bounds!");
        }
        int n;
        //int x = transferNum(col);
        n = pocalationNum(col,row);

        if(u.connected(n,9999)){
            return true;
        }
        return false;
    }


    // number of open sites
    public int numberOfOpenSites(){
        int n = 0;
        for(int i = 0; i < Num ;i+=1){
            for(int j = 0;j < Num;j+=1){
                if(p[i][j] == true)
                    n+=1;
            }
        }
        return n;
    }


    // does the system percolate?
    public boolean percolates(){

        if(u.connected(9999,10000))
            return true;
        return false;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){

        Percolation p = new Percolation(5);
        p.open(2,3);
        p.open(3,4);
        p.open(3,3);
        p.open(1,3);
        p.open(0,3);
        p.open(4,3);
        System.out.println(p.numberOfOpenSites());
        //System.out.println(p.isFull(0,3));
        System.out.println(p.percolates());
        //System.out.println(p.percolates());


    }

}
