import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

//means K "must be" Comparable. like Sting.
public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {

    private Node root;

    private class Node{
        private K key;
        private V value;
        private Node left,right;
        private int size;

        public Node(K key,V value,int size){
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap(){

    }

    @Override
    public void clear(){

    }

    @Override
    public boolean containsKey(K key){
        if(key == null)
            throw new IllegalArgumentException("Argument is NULL");
        return get(key) != null;

    }

    @Override
    public V get(K key){
        return get(root ,key);

    }

    private V get(Node x,K key){
        if(key == null)
            throw new IllegalArgumentException("NULL");
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            return get(x.left,key);
        else if(cmp > 0)
            return get(x.right,key);
        else
            return x.value;
    }

    @Override
    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x == null)
            return 0;
        else
            return x.size;
    }

    @Override
    public void put(K key, V value){
        if(key == null)
            throw new IllegalArgumentException("NULL");
        //if(value == null){
            //delete(key);
          //  return;
        //}
        root = put(root,key,value);

    }

    private Node put(Node x,K key,V value){
        if(x == null)
            return new Node(key,value,1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            x.left = put(x.left,key,value);
        else if(cmp > 0)
            x.right = put(x.right,key,value);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Iterator<K> interator(){
        return new keyIterator(root);
    }

    private class keyIterator implements Iterator<K>{
            Node x;
        public keyIterator(Node a){
            x = a;
        }

        @Override
        public boolean hasNext() {
            if(x.size!= 0)
                return true;
            return false;
        }

        @Override
        public K next() {
            return x.key;
        }
    }


    public K printInOrder(){
        return printInOrder(root);

    }

    private K printInOrder(Node x){
        for(K key:x.key);
    }


    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException("Invalid operation for this.");
    }

    @Override
    public V remove(K key){
        throw new UnsupportedOperationException("Invalid operation for this.");
    }

    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException("Invalid operation for this.");
    }

    @Override
    public Iterator<K> iterator(){
        throw new UnsupportedOperationException("Invalid operation for this.");
    }


}
