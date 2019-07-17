package byow.Core.oldversion;

import byow.TileEngine.TETile;

import java.util.HashMap;
import java.util.Map;

public class StoreMap {

    public static Map<Integer, TETile[][]> MapCollect = new HashMap<>();


    public static void main(String[] args){
        System.out.println(StoreMap.MapCollect.size());
    }

}
