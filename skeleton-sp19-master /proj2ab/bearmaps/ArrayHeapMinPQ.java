package bearmaps;

public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T>{

    private Node[] pq;        //the class that include <T> and Priority.
    private int n;              //the number of item in pq.
    private ItemNode root;      // Store items's hashcode by BST.


    public ArrayHeapMinPQ(int capacity){
        n = 1;
        /** this Casting!! Object is not is Node , but Node is a Object. */
        pq = new Node[capacity];
        for(int i = 0 ;i < capacity;i+=1)
            pq[i] = null;


    }

    /** =======================  ItemNode  ============================== */


    private class ItemNode{
        private T item;
        private double priority;
        private ItemNode left,right;
        private int size;

        public ItemNode(T a,double b,int c){
            item = a;
            priority = b;
            size = c;
        }
    }

    private int compareTo(T a){
        return this.hashCode() - a.hashCode();
    }

    public int Itemsize(){
        return size(root);
    }

    private int size(ItemNode x){
        if(x == null)
            return 0;
        else
            return x.size;
    }

    public void put(T key, double value){
        if(key == null)
            throw new IllegalArgumentException("NULL");
        root = put(root,key,value);

    }

    private ItemNode put(ItemNode x, T key, double value){
        if(x == null)
            return new ItemNode(key,value,1);
        int cmp = key.compareTo(x.item);
        //int cmp = compare(root,x);

        if(cmp < 0)
            x.left = put(x.left,key,value);
        else if(cmp > 0)
            x.right = put(x.right,key,value);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public double get(T key){
        return get(root ,key);

    }

    private double get(ItemNode x,T key){
        if(key == null)
            return 99.9999;
        if(x == null)
            return 99.9999;
        int cmp = key.compareTo(x.item);
        if(cmp < 0)
            return get(x.left,key);
        else if(cmp > 0)
            return get(x.right,key);
        else
            return x.priority;
    }

    /** ===================  ItemNode  ============================ */

    /**T his is the Node that include <T> items and Priority number. */
    private class Node<T>{

        private T items;
        private double priority;

        public Node(T e,double p){
            items = e;
            priority = p;
        }

        public T getItems(){
            return items;
        }

        public double getPriority(){
            return priority;
        }

        public void SetPriority(double p){
            priority = p;
        }

        @Override
        public int hashCode(){
            return items.hashCode();
        }

    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority){
        /** add item and priority to the ItemNode */
        put(item,priority);

        /** Resize the pq array when pq.length bigger than 3/4 pq.length  */
        if(n > (pq.length)*3/4)
            resize((pq.length)*2);

        Node x = new Node(item,priority);
        pq[n]=x;
        swim(n);
        n+=1;

    }

    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item){
        double s = get(item);
        if(s == 99.9999)
            return false;
        return true;

    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest(){
        /** (T)Object!? */
        T x = (T)pq[1].getItems();
        return x;

    }



    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest(){
        swap(1,n-1);
        Node s = pq[n-1];
        pq[n-1] = null;
        swimdown(1);
        n-=1;
        return (T)s.getItems();
    }



    /* Returns the number of items in the PQ. */
    @Override
    public int size(){
        return n-1;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority){


    }


    /** Resize the array by multiple 2 */
    private void resize(int capacity){
        Node[] temp = new Node[capacity];
        for(int i = 0; i < n ;i++){
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /** the parents of the Node */
    private int parent(int k){
        return k/2;
    }

    /** the left child of the Node */
    private int leftchild(int k){
        return k*2;
    }

    /** the right child of the Node */
    private int rightchild(int k){
        return (k*2)+1;
    }

    /** swap Node to the parent's Node if Node's Priority is smaller
     * than parent's Priority */
    private void swim(int k){
        /** if k = 1, parent is pq[0] */
        if(k == 1)
            return;
        if(pq[k].getPriority() < pq[parent(k)].getPriority()){
            swap(k,parent(k));
            swim(parent(k));
        }
    }

    private void swimdown(int k){
        /**left child smaller than right child */
        if(pq[rightchild(k)]  == null)
            return;
        if(pq[leftchild(k)] == null)
            return;
        if(pq[leftchild(k)].getPriority() < pq[rightchild(k)].getPriority()){
            if(pq[k].getPriority() > pq[leftchild(k)].getPriority()){
                swap(k,leftchild(k));
                k = leftchild(k);
                swimdown(k);
            }
        }else{
            if(pq[k].getPriority() > pq[rightchild(k)].getPriority()){
                swap(k,rightchild(k));
                k = rightchild(k);
                swimdown(k);
            }
        }
        return;
    }

    /** swap the two index of pq array */
    private void swap(int a, int b){
        Node temp;
        temp = pq[b];
        pq[b] = pq[a];
        pq[a] = temp;

    }



    public static void main(String[] args){

        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>(10);
        a.add("good",2);
        a.add("great",0.5);
        a.add("hello",0.9);
        a.add("aloha",4.9);
        a.add("hawaii",9.9);
        a.add("good morning",4.2);
        a.add("LOLLL",0.6);
        //a.add("kolll",10.2);
        //a.removeSmallest();
        //a.removeSmallest();
        //System.out.println(a.size());
        //System.out.println(a.getSmallest());
        System.out.println(a.contains("good mornin"));

    }


}
