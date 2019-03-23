package DisjointSets;



public class UnionFind {
    private int[] parent;

    public UnionFind(int n){
        parent = new int[n];
        for(int i = 0; i<n;i+=1){
            parent[i] = i;
        }

    }

    /*
    public void validate(int v1){

    }
    */

    public int sizeOf(int v1){
        if(find(v1) == v1)
            return 1;
        return -parent[find(v1)];
    }

    public int parent(int v1){
        return parent[v1];
    }

    public boolean connected(int v1, int v2){
        int num1 = find(v1);
        int num2 = find(v2);
        if(num1 == num2)
            return true;
        return false;
    }

    public void union(int v1, int v2){
        if(sizeOf(v1) ==1 && sizeOf(v2)==1){
            parent[v1] = v2;
            parent[v2] = -2;
            return ;
        }
        if(sizeOf(v1) > sizeOf(v2)){
            int newv1;
            int index_v2 = find(v2);
            int index_v1 = find(v1);
            if(sizeOf(v2) == 1) {
                newv1 = -1 + parent[index_v1];
            }
            else {
                newv1 = parent[index_v1] + parent[index_v2];
            }
            parent[index_v2] = index_v1;
            parent[index_v1] = newv1;
        }
        else if (sizeOf(v1) < sizeOf(v2)) {
            int newv2;
            int index_v2 = find(v2);
            int index_v1 = find(v1);
            if(sizeOf(v1) == 1) {
                newv2 = -1 + parent[index_v2];
            }
            else {
                newv2 = parent[index_v1] + parent[index_v2];
            }
            parent[index_v1] = index_v2;
            parent[index_v2] = newv2;

        }
        else{
            int index_v1 = find(v1);
            int index_v2 = find(v2);
            int newv2 = parent[index_v1]+parent[index_v2];
            parent[index_v1] = index_v2;
            parent[index_v2] = newv2;

        }

    }

    // return index of root
    public int find(int v1){
        if(parent[v1]==v1)
            return v1;
        while(parent[v1] >= 0)
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
