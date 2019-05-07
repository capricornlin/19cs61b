package bearmaps;

public class ArrayHeapMinPQ<T extends Comparable<T>> {

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

    public void changeP(T key,double p){
        changeP(root, key,p);
    }

    private void changeP(ItemNode x,T key,double p){
        if(key == null)
            return ;
        if(x == null)
            return ;
        int cmp = key.compareTo(x.item);
        if(cmp < 0)
            changeP(x.left,key,p);
        else if(cmp > 0)
            changeP(x.right,key,p);
        else
            x.priority = p;
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
    public boolean contains(T item){
        double s = get(item);
        if(s == 99.9999)
            return false;
        return true;

    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest(){
        /** (T)Object!? */
        T x = (T)pq[1].getItems();
        return x;

    }



    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest(){
        swap(1,n-1);
        Node s = pq[n-1];
        pq[n-1] = null;
        swimdown(1);
        n-=1;
        return (T)s.getItems();
    }



    /* Returns the number of items in the PQ. */
    public int size(){
        return n-1;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority){
        double s = get(item);
        changeP(item,priority);
        Node x = searchP(s,1);
        int n  = searchNum(s,1);
        if(x == null){
            return;
        }else{
            x.priority = priority;

            if(parent(n) == 0){
                swimdown(n);
            }
            else if (pq[n].priority < pq[parent(n)].priority){
                swim(n);
            }

            else if(rightchild(n) >size() && leftchild(n) > size()){
                return;
            }
            else if(pq[n].priority > pq[leftchild(n)].priority || pq[n].priority > pq[rightchild(n)].priority){
                swimdown(n);
            }else
                return;
        }

    }

    /** Search which index of the double a in pq[] Array */
    private int searchNum(double a,int n){
        Node x;
        if(a == pq[n].priority) {
            //return (T)pq[1].items;
            x = pq[n];
            return n;
        }
        /** if n is greater than size */
        if(rightchild(n) > size() && leftchild(n) > size())
            return 0;
        else if(rightchild(n) > size()){
            n = leftchild(n);
            return searchNum(a,n);
        }else if(leftchild(n) > size()){
            n = rightchild(n);
            return searchNum(a,n);
        }




        /** if n is not greater than size */
        else if( a == pq[leftchild(n)].priority){
            x = pq[leftchild(n)];
            return leftchild(n);
        }else if(a == pq[rightchild(n)].priority){
            x = pq[rightchild(n)];
            return rightchild(n);
        }

        /** go left */
        else if(a > pq[leftchild(n)].priority && a < pq[rightchild(n)].priority){
            {
                n = leftchild(n);
                return searchNum(a,n);
            }

        }
        /** go right */
        else if(a > pq[rightchild(n)].priority && a < pq[leftchild(n)].priority){
            {
                n = rightchild(n);
                return searchNum(a,n);
            }
        }
        /** same priority */
        else if(a > pq[rightchild(n)].priority){
            if(a > pq[leftchild(n)].priority){
                int left = leftchild(n);
                int right = rightchild(n);
                int l = searchNum(a,left);
                int r = searchNum(a,right);
                if(l != 0)
                    return l;
                else if(r != 0)
                    return r;

            }
        }

        return 99999999;
    }



    /** Search which Node of the double a in pq[] Array */
    private Node searchP(double a,int n){
        Node x;

        if(a == pq[n].priority) {
            //return (T)pq[1].items;
            x = pq[n];
            return x;
        }
        /** if n is greater than size */
        if(rightchild(n) > size() && leftchild(n) > size())
            return null;
        else if(rightchild(n) > size()){
            n = leftchild(n);
            return searchP(a,n);
        }else if(leftchild(n) > size()){
            n = rightchild(n);
            return searchP(a,n);
        }


        /** if n is not greater than size */
        else if( a == pq[leftchild(n)].priority){
            x = pq[leftchild(n)];
            return x;
        }else if(a == pq[rightchild(n)].priority){
            x = pq[rightchild(n)];
            return x;
        }

        /** go left */
        else if(a > pq[leftchild(n)].priority && a < pq[rightchild(n)].priority){
            {
                n = leftchild(n);
                return searchP(a,n);
            }

        }
        /** go right */
        else if(a > pq[rightchild(n)].priority && a < pq[leftchild(n)].priority){
            {
                n = rightchild(n);
                return searchP(a,n);
            }
        }
        /** same priority */
        else if(a > pq[rightchild(n)].priority && a > pq[leftchild(n)].priority){
            {
                int left = leftchild(n);
                int right = rightchild(n);
                //search both side right and left.
                Node l = searchP(a,left);
                Node r = searchP(a,right);
                if(l != null)
                    return l;
                else if(r != null)
                    return r;
            }
        }

        return null;
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
        //k is the root.
        if(k == 1)
            return 0;
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

    /** If index k's item greater to their children, we need to swap with their children. */
    private void swimdown(int k){
        /**left child smaller than right child */
        if(rightchild(k) > size() && leftchild(k) > size())
            return;
        if(rightchild(k)  > size() && pq[leftchild(k)].priority < pq[k].priority){
            swap(k,leftchild(k));
            return;
        }
        if(leftchild(k) >size() && pq[rightchild(k)].priority < pq[k].priority){
            swap(k,rightchild(k));
            return;
        }
        //go left.
        if(pq[leftchild(k)].getPriority() < pq[rightchild(k)].getPriority()){
            if(pq[k].getPriority() > pq[leftchild(k)].getPriority()){
                swap(k,leftchild(k));
                k = leftchild(k);
                swimdown(k);
            }
            //go right.
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
        a.add("LOLLL",0.2);
        a.changePriority("LOLLL",8);
        //a.add("kolll",10.2);
        //a.removeSmallest();
        //a.removeSmallest();
        //System.out.println(a.size());
        //System.out.println(a.getSmallest());
        System.out.println(a.contains("hello"));

    }


}
