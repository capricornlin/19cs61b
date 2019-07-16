package byow.Core;

import byow.TileEngine.TETile;

public class testseed {

    public static void main(String[] args){
        Engine game = new Engine();
        TETile[][] world = game.interactWithInputString("N1234S");
        System.out.println(TETile.toString(world));
    }
}
