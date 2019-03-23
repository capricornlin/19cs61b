import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

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

    }

    @Override
    V get(K key){

    }

    @Override
    int size(){

    }

    @Override
    void put(K key, V value){

    }

    public int compareTo(K object){

    }

    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException("Invalid operation for this.")
    }

    @Override
    public V remove(K key){
        throw new UnsupportedOperationException("Invalid operation for this.")
    }

    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException("Invalid operation for this.")
    }

    @Override
    public Iterator<K> iterator(){
        throw new UnsupportedOperationException("Invalid operation for this.")
    }






}
