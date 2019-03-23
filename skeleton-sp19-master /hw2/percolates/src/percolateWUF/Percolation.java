package percolateWUF;


public class Percolation {
    private boolean [][] p;
    private int Num;
    private WeightedQuickUnionUF u;
    private int x,y;


    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        u = new WeightedQuickUnionUF(1000*1000);
        Num = N;
        p = new boolean[N][N];
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
        n = y*Num + x;
        return n;
    }

    //transfer (row,col) to new coordinates
    public void transferNum(int row , int col){
        y = Num - 1 - col;
        x = row;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        int n;
        transferNum(row,col);
        p[x][y] = true;
        n = pocalationNum(x,y);

        //up
        if(col == 0)
            if(isOpen(row+1,col) == true)
                u.union(row+1,col);
            if(isOpen(row-1,col) == true)
                u.union(row-1,col);
            else if(isOpen(row,col+1) == true)
                u.union(row,col+1);
            /*
         //botton
         if(col == Num-1)
             if(isOpen(row+1,col) == true)
                 u.union(row+1,col);
            if(isOpen(row-1,col) == true)
                u.union(row-1,col);
            if(isOpen(row,col-1) == true)
                u.union(row,col-1);
         //right
        if(row == 0)
            if(isOpen(row+1,col) == true)
                u.union(row+1,col);
            if(isOpen(row,col+1) == true)
                u.union(row,col+1);
            if(isOpen(row+1,col-1) == true)
                u.union(row+1,col-1);




        /*
        if(row ==0 && col ==0)
            if(isOpen(row+1,col) == true)
                u.union(row+1,col);
            else if(isOpen(row,col+1) == true)
                u.union(row,col+1);
        // middle square part
        if(row > 0 && row < (Num-1) && col > 0 && col <(col-1)){
            //n's rught
            if(isOpen(row+1,col) == true)
                u.union(n,pocalationNum(row+1,col));
                //n's left
            else if(isOpen(row-1,col) == true)
                u.union(n,pocalationNum(row-1,col));
                //n's up
            else if(isOpen(row,col-1) == true)
                u.union(n,pocalationNum(row,col-1));
                //n's botton
            else
                u.union(n,pocalationNum(row,col+1));
        }
        */

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        transferNum(row,col);
        if(p[x][y] == true)
            return true;
        return false;

    }

    // is the site (row, col) full?
    public boolean isFull(int row ,int col){
        int n;
        //transferNum(row,col);
        n = pocalationNum(row,col);
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

    Percolation p = new Percolation(5);
    p.open(2,3);
    //p.open(3,4);
    p.open(3,3);
    p.open(2,2);
    p.open(2,1);
    p.open(2,0);
    System.out.println(p.isFull(2,3));

    //p.open(2,0);
    //p.open(2,1);
    //p.open(2,2);
    //System.out.println(p.isOpen(2,3));
    //System.out.println(p.isOpen(3,4));
    //System.out.println(p.isOpen(3,3));
    //System.out.println(p.x);
    //System.out.println(p.y);



    }

}

