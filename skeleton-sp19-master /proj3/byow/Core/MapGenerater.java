package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerater {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    List<RoomPosition> Room_coordinate = new ArrayList<>();




    public int randomNumber(int i,int j){
        Random random = new Random();
        RandomUtils r = new RandomUtils();
        int a = r.uniform(random,i,j);
        return a;
    }


    /** Create random room.*/
    public void Createroom(TETile[][] world){
        int room_num = randomNumber(15,20);
        System.out.println("room_num: "+room_num);
        for(int i = 0; i < room_num;i+=1){
            RoomPosition a = new RoomPosition(WIDTH,HEIGHT);
            int rooml = a.rooml;
            int roomw = a.roomw;
            int startx = a.startx;
            int starty = a.starty;
            int endx = a.endx;
            int endy = a.endy;
            Room_coordinate.add(a);
            /**
            System.out.println(Room_coordinate.size());
            System.out.println("rstartx: "+startx);
            System.out.println("rstarty: "+starty);
            System.out.println("rendx: "+endx);
            System.out.println("rendy: "+endy);
            System.out.println("rrooml: "+rooml);
            System.out.println("rroomw: "+roomw);
            System.out.println("random_x: "+a.random_x);
            System.out.println("random_y: "+a.random_y);
            */
            DrawRoom(startx,starty,endx,endy,world);
        }
    }

    /** Draw random Room. */
    public void DrawRoom(int startx,int starty,int endx,int endy,TETile[][] world){
        for(int x = startx;x< endx;x+=1){
            for(int y = starty;y< endy;y+=1){
                if(x == 0 || x == WIDTH || y == 0 || y == HEIGHT){
                    world[x][y] = Tileset.NOTHING;
                }else {
                    world[x][y] = Tileset.FLOOR;
                }
            }
        }
    }

    /***/
    public void CreateHallways(TETile[][] world){
        //smallest to biggest
        int size = Room_coordinate.size();
        for(int j = 0;j < size; j+=1 ) {
            int smallest = Room_coordinate.get(j).random_x;
            //int size = Room_coordinate.size();
            for (int i = 1+j; i < size; i += 1) {
                if (Room_coordinate.get(i).random_x < smallest) {
                    RoomPosition x = Room_coordinate.get(j);
                    RoomPosition y = Room_coordinate.get(i);
                    Room_coordinate.set(j, y);
                    Room_coordinate.set(i, x);
                    smallest = Room_coordinate.get(j).random_x;
                }
            }
        }
        for(int i = 0 ;i < size - 1; i+=1){
            int x1 = Room_coordinate.get(i).random_x;
            int x2 = Room_coordinate.get(i+1).random_x;
            int y1 = Room_coordinate.get(i).random_y;
            int y2 = Room_coordinate.get(i+1).random_y;
            int x = Room_coordinate.get(i+1).random_x - Room_coordinate.get(i).random_x;
            int y = Room_coordinate.get(i+1).random_y - Room_coordinate.get(i).random_y;
            for(int k = x1; k <=x2 ;k+=1 ){
                world[k][y1] = Tileset.FLOOR;
            }
            if(y > 0){
                for(int k = y1; k < y2; k+=1){
                    world[x2][k] = Tileset.FLOOR;
                }
            }else{
                for(int k = y2; k < y1; k+=1){
                    world[x2][k] = Tileset.FLOOR;
                }
            }

        }

    }

    /** Draw wall */
    public void CreateWall(TETile[][] world){
        for(int i = 0;i < WIDTH;i+=1){
            world[i][0] = Tileset.NOTHING;
            world[i][HEIGHT-1] = Tileset.NOTHING;
        }
        for(int j = 0; j<HEIGHT;j+=1){
            world[0][j] = Tileset.NOTHING;
            world[WIDTH-1][j] = Tileset.NOTHING;
        }

        for(int x = 1; x < WIDTH;x+=1){
            for(int y = 1;y < HEIGHT;y+=1){
                if(world[x][y] == Tileset.FLOOR){
                    if(world[x+1][y] == Tileset.NOTHING){
                        world[x+1][y] = Tileset.WALL;
                    }
                    if(world[x-1][y] == Tileset.NOTHING){
                        world[x-1][y] = Tileset.WALL;
                    }
                    if(world[x][y+1] == Tileset.NOTHING){
                        world[x][y+1] = Tileset.WALL;
                    }
                    if(world[x][y-1] == Tileset.NOTHING){
                        world[x][y-1] = Tileset.WALL;
                    }
                    if(world[x+1][y+1] == Tileset.NOTHING){
                        world[x+1][y+1] = Tileset.WALL;
                    }
                    if(world[x+1][y-1] == Tileset.NOTHING){
                        world[x+1][y-1] = Tileset.WALL;
                    }
                    if(world[x-1][y+1] == Tileset.NOTHING){
                        world[x-1][y+1] = Tileset.WALL;
                    }
                    if(world[x-1][y-1] == Tileset.NOTHING){
                        world[x-1][y-1] = Tileset.WALL;
                    }
                }
            }
        }
    }

    public static TETile[][] generateSpace(){
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    public TETile[][] createMap(){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = generateSpace();
        Createroom(world);
        CreateHallways(world);
        CreateWall(world);
        ter.renderFrame(world);
        return world;
    }
}
