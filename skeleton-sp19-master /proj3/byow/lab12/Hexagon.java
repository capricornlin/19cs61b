package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Hexagon {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 50;

    /** Create new World. */
    public static void createworld(TETile[][] world){
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

    }

    /** Add new Hexxagon in the World */
    public static void addHexagon(int number,Position p,TETile[][] world){
        hex(number,world,p.x,p.y);

    }


    public static int bottomlevel(int k){
        int level = k;
        int bottom = k+(level-1)*2;
        return bottom;
    }

    public static void hex(int number,TETile[][] world,int locationx,int locationy){
        int top = bottomlevel(number);
        int level = number*2;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int top_x = 0;
        for(int y = 0+locationy ;y< level/2+locationy;y += 1 ){
                for (int x = 0 + locationx - a; x < number + locationx + b; x += 1) {
                    world[x][y] = Tileset.FLOWER;
                    top_x = x;
                    }
                a+=1;
                b+=1;
            }
        top_x-=top-1;
        for(int y = level/2+locationy;y < level+locationy;y+=1){
            for(int x = top_x+c;x < top_x + top-d;x+=1){
                world[x][y] = Tileset.FLOWER;
                }
                c+=1;
                d+=1;
            }

        }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        createworld(world);
        Position p = new Position(10,10);
        addHexagon(3,p,world);
        ter.renderFrame(world);


    }

}
