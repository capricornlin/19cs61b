package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.HashSet;
import java.util.Random;

public class CreateMap {

    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;


    /** 1.Create random Number within input integer i .*/
    public int randomNumber(int i,int j){
        Random random = new Random();
        RandomUtils r = new RandomUtils();
        int a = r.uniform(random,i,j);
        return a;
    }

    /** 2.Create random Rooms with random Width and Length. */
    public void createRandomRoom(TETile[][] world){
        int room_num = randomNumber(20,30);
        System.out.println("room_num: "+room_num);
            int rooml = randomNumber(3,5);
            int roomw = randomNumber(3,5);
            int start_x = randomNumber(WIDTH/4,3*WIDTH/4 );
            int start_y = randomNumber(HEIGHT/3,2*HEIGHT/3 );
            int end_x = start_x+roomw;
            int end_y = start_y+rooml;
            System.out.println("1startx: "+start_x);
            System.out.println("1starty: "+start_y);
            System.out.println("1endx: "+end_x);
            System.out.println("1endy: "+end_y);
            System.out.println("1rooml: "+rooml);
            System.out.println("1roomw: "+roomw);
            CreateRoom(start_x,start_y,end_x,end_y,world);
                for (int i = 1; i < 5; i += 1) {
                    drawRoom(i, 1, start_x, start_y, end_x, end_y, roomw, rooml, world);
                }

            }
        //CreateWall(world);


    /**Draw four directions hallways. */
    public newPosition drawHallways(int dir,int times,int start_x,int start_y,int end_x,int end_y,int roomw,int rooml,TETile[][] world){
        newPosition a = new newPosition(start_x,start_y,end_x,end_y,roomw,rooml,WIDTH,HEIGHT);
        for(int i = 0; i < times ;i+=1) {
            //a =  new newPosition(start_x,start_y,end_x,end_y,roomw,rooml,WIDTH,HEIGHT);
            chooseDir(dir,a);
            start_x = a.newstart_x;
            start_y = a.newstart_y;
            end_x = a.newend_x;
            end_y = a.newend_y;
            rooml = a.newlegth;
            roomw = a.newwidth;
            System.out.println("hstartx: "+start_x);
            System.out.println("hstarty: "+start_y);
            System.out.println("hendx: "+end_x);
            System.out.println("hendy: "+end_y);
            System.out.println("hrooml: "+rooml);
            System.out.println("hroomw: "+roomw);
            if(dir == 1){
                CreateHHallways(start_x, start_y, end_x, end_y, world,dir);
            }else if( dir == 2) {
                CreateHHallways(start_x, start_y, end_x, end_y, world,dir);
            }else if(dir == 3){
                CreateVHallways(start_x, start_y, end_x, end_y, world,dir);
            }else if(dir == 4){
                CreateVHallways(start_x, start_y, end_x, end_y, world,dir);
            }

        }
        return a;
    }

    /**Draw four directions rooms */
    public newPosition drawRoom(int dir,int times,int start_x,int start_y,int end_x,int end_y,int roomw,int rooml,TETile[][] world){
        newPosition a =  new newPosition(start_x,start_y,end_x,end_y,roomw,rooml,WIDTH,HEIGHT);
        for(int i = 0; i < times ;i+=1) {
            //newPosition a =  new newPosition(start_x,start_y,end_x,end_y,roomw,rooml,WIDTH,HEIGHT);
            chooseDir(dir,a);
            start_x = a.newstart_x;
            start_y = a.newstart_y;
            end_x = a.newend_x;
            end_y = a.newend_y;
            rooml = a.newlegth;
            roomw = a.newwidth;
            System.out.println("rstartx: "+start_x);
            System.out.println("rstarty: "+start_y);
            System.out.println("rendx: "+end_x);
            System.out.println("rendy: "+end_y);
            System.out.println("rrooml: "+rooml);
            System.out.println("rroomw: "+roomw);
            CreateRoom(start_x, start_y, end_x, end_y, world);
        }
        return a;
    }

    /**Choose which way the room will go.*/
    public void chooseDir(int dir,newPosition a){
        switch(dir){
            case 1:
                a.go_left();
                System.out.println("go left");
                break;
            case 2:
                a.go_right();
                System.out.println("go right");
                break;
            case 3:
                a.go_up();
                System.out.println("go up");
                break;
            case 4:
                a.go_down();
                System.out.println("go down");
                break;
        }
    }

    /** Create Room */
    public void CreateRoom(int startx,int starty,int endx,int endy,TETile[][] world){
        // build room wall.
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
    /** Create hallways */
    public void CreateHHallways(int startx,int starty,int endx,int endy,TETile[][] world, int z) {
        //horizontal
        int y;
        for (int x = startx; x < endx; x += 1) {
            if(z == 1){
                y = endy;
            }else{
                y = starty;
            }
            //int y = endy; //left
            //int y = starty // right
            if (x == 0 || x == WIDTH || y == 0 || y == HEIGHT) {
                world[x][y] = Tileset.NOTHING;
            } else {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }
    public void CreateVHallways(int startx,int starty,int endx,int endy,TETile[][] world,int z){
        for(int y = starty; y < endy; y+=1){
            int x;
            if(z == 3){
                x = startx;
            }else{
                x = endx;
            }
            //int x = startx; //up
            //int x = endx // down
            if(x == 0 || x == WIDTH || y == 0 || y == HEIGHT ){
                world[x][y] = Tileset.NOTHING;
            }else{
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    public void CreateWall(TETile[][] world){
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
                }
            }
        }
    }

    public static void main(String[] args) {

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        CreateMap a = new CreateMap();
        a.createRandomRoom(world);
        ter.renderFrame(world);

    }

}


