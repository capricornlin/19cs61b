

public class UnionFind {
    private int[] parent;


    // TODO - Add instance variables?

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i += 1) {
            parent[i] = i;
        }
    }

        /* Throws an exception if v1 is not a valid index. */
        private void validate ( int vertex){
            // TODO
        }

        /* Returns the size of the set v1 belongs to. */
        public int sizeOf ( int v1){
            if (find(v1) == v1)
                return 1;
            return -parent[find(v1)];
        }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
        public int parent ( int v1){
            return parent[v1];
        }

        /* Returns true if nodes v1 and v2 are connected. */
        public boolean connected ( int v1, int v2){
            int num1 = find(v1);
            int num2 = find(v2);
            if (num1 == num2)
                return true;
            return false;
        }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
        public void union ( int v1, int v2){
            if (sizeOf(v1) == 1 && sizeOf(v2) == 1) {
                parent[v1] = v2;
                parent[v2] = -2;
                return;
            }
            if (sizeOf(v1) > sizeOf(v2)) {
                int newv1;
                int index_v2 = find(v2);
                int index_v1 = find(v1);
                if (sizeOf(v2) == 1) {
                    newv1 = -1 + parent[index_v1];
                } else {
                    newv1 = parent[index_v1] + parent[index_v2];
                }
                parent[index_v2] = index_v1;
                parent[index_v1] = newv1;
            } else if (sizeOf(v1) < sizeOf(v2)) {
                int newv2;
                int index_v2 = find(v2);
                int index_v1 = find(v1);
                if (sizeOf(v1) == 1) {
                    newv2 = -1 + parent[index_v2];
                } else {
                    newv2 = parent[index_v1] + parent[index_v2];
                }
                parent[index_v1] = index_v2;
                parent[index_v2] = newv2;

            } else {
                int index_v1 = find(v1);
                int index_v2 = find(v2);
                int newv2 = parent[index_v1] + parent[index_v2];
                parent[index_v1] = index_v2;
                parent[index_v2] = newv2;

            }

        }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
        public int find ( int v1){
            if (parent[v1] == v1)
                return v1;
            while (parent[v1] >= 0)
                v1 = parent[v1];
            int index = v1;
            return index;
        }


    public static void main(String[] args){
        UnionFind ua = new UnionFind(7);

        ua.union(2,3);
        ua.union(4,5);
        ua.union(2,4);
        System.out.println(ua.connected(2,4));
        System.out.println(ua.connected(3,5));

    }
}
