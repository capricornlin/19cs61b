package DisjointSets;

    public class Percolation {
        private boolean [][] p;
        private int Num;
        private UnionFind u ;



        // create N-by-N grid, with all sites initially blocked
        public Percolation(int N){
            u = new UnionFind(N*N);
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

            if(isOpen(row,col+1) == true)
                u.connected(n,pocalationNum(row,col+1));
            else if(isOpen(row,col-1) == true)
                u.connected(n,pocalationNum(row,col-1));
            else if(isOpen(row-5,col) == true)
                u.connected(n,pocalationNum(row-5,col));
            else
                u.connected(n,pocalationNum(row+5,col));
        }

        // is the site (row, col) open?
        public boolean isOpen(int row, int col){
            if(p[row][col] == true)
                return true;
            return false;

        }
        /*
        // is the site (row, col) full?
        public boolean isFull(int row ,int col){


        }

        // number of open sites
        public int numberOfOpenSites(){

        }

        // does the system percolate?
        public boolean percolates(){

        }
        */

        public static void main(String[] args){
            Percolation a = new Percolation(5);
            a.open(2,3);
            a.open(2,4);
            System.out.println(a.isOpen(2,4));
        }

    }
