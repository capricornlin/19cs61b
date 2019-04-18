import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {

    private int R = 128;
    private Node root;

    public MyTrieSet(){
        root = new Node(false);
    }

    /** DataIndexcharMap is a instance for - V[]items array
     *  and <V> = <Node>
     * */
    private class DataindexcharMap<V>{
        private V[] items;
        public DataindexcharMap(int R){
            items = (V[]) new Object[R];
        }
        /** Means items's c element point to another new Node */
        public void put(char c,V val){
            items[c] = val;
        }
        /** return a item[c]'s Node pointer */
        public V get(char c){
            return items[c];
        }

        public boolean containsKey(char c){
            if(items[c] != null)
                return true;
            return false;
        }

    }

    /** Node is a intance of three elements: ch, isKey, next */
    private class Node{
        private char ch;
        private boolean isKey;
        /** DataindexcharMap is an instances */
        private DataindexcharMap map;

        private Node(boolean b){
            isKey = b;
            map = new DataindexcharMap<Node>(R);
        }

        private Node(char c,boolean b){
            ch = c;
            isKey = b;
            map = new DataindexcharMap<Node>(R);
        }

    }

    /** Clears all items out of Trie */
    @Override
    public void clear(){
        root = new Node(false);

    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key){
        char a = key.charAt(0);
        if (key == null || key.length() < 1) {
            return false;
        }

        /** If root is a Null Node */
        if(root.map.items[a] == null)
            return false;

        Node curr = root;
        int n = key.length();
        int m = 0;
        for (int i = 0; i < n; i++) {
            /** charAt: return i's element character. */
            char c = key.charAt(i);
            /**check if root's map has key or not. */
            if (curr.map.containsKey(c)) {
                m += 1;
            }
            curr = (Node) curr.map.get(c);
        }
        if(m == n)
            return true;
        return false;
    }


    /** Inserts string KEY into Trie  */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            /** charAt: return i's element character. */
            char c = key.charAt(i);
            /** check if root's map has key or not. */
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = (Node)curr.map.get(c);
        }
        curr.isKey = true;
    }


    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix){
        Node curr = root;
        List<String> a = new ArrayList<>();
        for(int i = 0,n = prefix.length();i < n ; i+=1 ){
            char c = prefix.charAt(i);
            curr = (Node)curr.map.get(c);
        }
        if(curr.isKey)
            a.add(prefix);
        return PrefixHelper(prefix,a,curr);
    }

    public List<String> PrefixHelper(String s,List<String> x, Node n){

        int m = s.length() - 1;
        char c = s.charAt(m);
        Node a;
        for(int i =0; i < R;i+=1){
            if(n.map.items[i] != null){
                a = (Node)n.map.items[i];
                char v = a.ch;
                if(!(a.isKey)){
                    PrefixHelper(s + v,x,a);
                }
                if(a.isKey){
                    x.add(s + v);
                    PrefixHelper(s + v,x,a);
                }
            }
        }
        return x;
    }


    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key){
        throw new UnsupportedOperationException("Wrong!");
    }

    public static void main(String[] args){

        MyTrieSet a = new MyTrieSet();
        a.add("ab");
        a.add("abc");
        a.add("agh");
        a.add("nbw");
        a.add("aboiwcyd");
        a.add("ajideh");
        a.add("mn");
        a.add("abcde");
        a.add("abcikjh");
        //a.contains("abc");
        //System.out.println(a.contains("abc"));
        System.out.println(a.keysWithPrefix("abc"));

    }


}
