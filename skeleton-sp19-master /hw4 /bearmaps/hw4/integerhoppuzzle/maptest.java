package bearmaps.hw4.integerhoppuzzle;

import java.util.HashMap;
import java.util.Map;

public class maptest {

    public static void main(String[] args){

        Map<Integer,Integer> a = new HashMap<>();
        a.put(15,1);
        a.put(16,1);
        a.put(8,5);
        a.put(34,5);
        a.put(289,10);
        a.put(16,5);
        System.out.println(a.get(16));


    }

}
