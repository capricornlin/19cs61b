import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;


public class MyHashMap<K,V> implements Map61B<K,V> {

    private int size;
    private double loadFactor;
    private Entry list;
    private ArrayList<Entry> a;
    private HashSet<K> b;

    /** create a linkedlist store (key , value) */
    private class Entry{
        K key;
        V val;
        Entry next;

        /** Entry constructor,concludes K key, V val, Entry next.*/
        public Entry(K key,V val,Entry n){
            this.key = key;
            this.val = val;
            next = n;
        }

        /** Entry's method get(K k), return which Entry contains this K key. */
        Entry get(K k) {
            if (k != null && k.equals(key)) {
                return this;
            }
            if (next == null) {
                return null;
            }
            return next.get(k);
        }

    }

    /** Constructor */
    public MyHashMap(){
        size = 16;
        loadFactor = 0.75;
        a = new ArrayList<>(size);
        for(int i =0;i < size;i+=1){
            a.add(null);
        }
        b = new HashSet<>(size);
    }

    /** Constructor */
    public MyHashMap(int initialSize){
        size = initialSize;
        loadFactor = 0.75;
        a = new ArrayList<>(initialSize);
        b = new HashSet<>(initialSize);
        for(int i =0;i < initialSize;i+=1){
            a.add(null);
        }

    }

    /** Constructor */
    public MyHashMap(int initialSize, double loadFactor){
        size = initialSize;
        this.loadFactor = loadFactor;
        a = new ArrayList<>(size);
        b = new HashSet<>(size);
        for(int i =0;i < size;i+=1){
            a.add(null);
        }
    }


    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        a.clear();
    }

    /** Returns true if this map contains a mapping for the specified key. */

    @Override
    public boolean containsKey(K key){
        if(get(key)!= null)
            return true;
        return false;

    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        int num = (key.hashCode() & 0x7FFFFFFF)%size;
        //a is a ArrayList, return the element in the array.
        list = a.get(num);
        if (list == null) {
            return null;
        }
        // list is a Entry,so returns the Entry which contains this 'key'.
        Entry lookup = list.get(key);
        if (lookup == null) {
            return null;
        }
        return lookup.val;

    }


    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value){
        b.add(key);
        int num = (key.hashCode() & 0x7FFFFFFF)%size;
        //a is a ArrayList, return the element in the array.
        if(a.get(num)!= null){
            list = a.get(num);
            // list is a Entry,so returns the Entry which contains this 'key'.
            if(list.get(key) == null){
                while(list.next != null){
                    list = list.next;
                }
                list.next = new Entry(key,value,null);
                return;
            }
            else if(list.get(key).key == key) {
                list.get(key).val = value;
                return;
            }
            else
                //Entry x = a.get(num);
                a.get(num).next = new Entry(key,value,a.get(num).next);

        }else {
            a.set(num, new Entry(key, value,null));
        }

    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){

        return b;
    }


    //returns an Iterator that iterates over the stored keys
    @Override
    public Iterator<K> iterator(){
        return new HashMapIter();

    }

    private class HashMapIter implements Iterator<K>{
        private Entry cur;

        HashMapIter(){
            cur = list;
        }

        @Override
        public boolean hasNext(){
            return cur != null;

        }

        @Override
        public K next(){
            K ret = cur.key;
            cur = cur.next;
            return ret;
        }


    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException("Worng");
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException("Wrong");
    }

    public static void main(String[] args){
        MyHashMap<String,Integer> a = new MyHashMap<>();
        a.put("good",10);
        a.put("nice",20);
        a.put("great",30);
        a.put("good",50);
        a.put("nice",90);
        a.put("monster",100);
        a.put("gray",99);
        a.put("josh",88);
        a.put("casey",66);
        /**
        Set<String> h = a.keySet();
        a.keySet();
        for(String k:h) {
            System.out.println(k);
        }
        for(String j:a){
            System.out.println(j);
        }
         */
        //System.out.println(a.containsKey("casey"));
        //System.out.println(a.containsKey("caseyl"));
        //a.clear();


        /**        size p     */



    }


}
