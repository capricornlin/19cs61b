package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.util.*;

public class MapGenerater {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private static AvatarPosition AvatarP; //Avatar's Position
    List<RoomCoordinates> Room_coordinate = new ArrayList<>();
    TERenderer ter;

    public MapGenerater(){
        ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
    }


    /** ============= Create random room. ====================*/
    public void Createroom(TETile[][] world,int seed){
        RoomPosition a = new RoomPosition(seed,WIDTH,HEIGHT);
        Random a2 = new Random(seed);
        int room_num = 15+a2.nextInt(5);
        for(int i = 0; i < room_num;i+=1){

            int rooml = a.rooml(room_num,i);
            int roomw = a.roomw(room_num,i);
            int startx = a.startx(room_num,i,roomw);
            int starty = a.starty(room_num,i,rooml);
            int endx = a.endx(startx,roomw);
            int endy = a.endy(starty,rooml);
            int random_x = a.random_x(room_num,i,startx,endx);
            int random_y = a.random_y(room_num,i,starty,endy);
            RoomCoordinates rr = new RoomCoordinates(startx,starty,endx,endy,roomw,rooml,random_x,random_y);
            Room_coordinate.add(rr);
            DrawRoom(startx,starty,endx,endy,world);
        }
    }

    /** ============= Draw random Room. =================*/
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

    /** ============= Draw hallways between each Room =============*/
    public void CreateHallways(TETile[][] world){
        //smallest to biggest
        int size = Room_coordinate.size();
        for(int j = 0;j < size; j+=1 ) {
            int smallest = Room_coordinate.get(j).randomx;
            //int size = Room_coordinate.size();
            for (int i = 1+j; i < size; i += 1) {
                if (Room_coordinate.get(i).randomx < smallest) {
                    RoomCoordinates x = Room_coordinate.get(j);
                    RoomCoordinates y = Room_coordinate.get(i);
                    Room_coordinate.set(j, y);
                    Room_coordinate.set(i, x);
                    smallest = Room_coordinate.get(j).randomx;
                }
            }
        }
        for(int i = 0 ;i < size - 1; i+=1){
            int x1 = Room_coordinate.get(i).randomx;
            int x2 = Room_coordinate.get(i+1).randomx;
            int y1 = Room_coordinate.get(i).randomy;
            int y2 = Room_coordinate.get(i+1).randomy;
            int x = Room_coordinate.get(i+1).randomx - Room_coordinate.get(i).randomx;
            int y = Room_coordinate.get(i+1).randomy - Room_coordinate.get(i).randomy;
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


    /** ============= After the Map is done, we draw the bounding wall =============*/
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

    /** ============= Create the Bounding space ===================*/
    public static TETile[][] generateSpace(){
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    /**=============  Avatar show in the map. ============= */
    public AvatarPosition createAvatar(TETile[][] world,int seed){
        int i = 0;
        Random r = new Random(seed);
        Map<Integer,AvatarPosition> avatar = new HashMap<>();
        for(int x = 0; x < WIDTH ; x+=1){
            for(int y = 0; y < HEIGHT; y+=1){
                if(world[x][y] == Tileset.FLOOR){
                    AvatarPosition a = new AvatarPosition(x,y);
                    avatar.put(i,a);
                    i+=1;
                }

            }
        }
        int rr = r.nextInt(i);
        AvatarP = avatar.get(rr);
        int avatar_x = AvatarP.get_x();
        int avatar_y = AvatarP.get_y();
        world[avatar_x][avatar_y] = Tileset.AVATAR;
        return AvatarP;
    }

    /**=============== Avatar Move's step calculate in the Map ==================*/
    public void AvatarMoveStep(TETile[][] world,Map<Integer,Character> N){
        int size = N.size();
        for(int i = 0; i < size;i+=1){
            char avatarMove = N.get(i);
            AvatarMove(world,avatarMove,AvatarP);
        }
    }
    /**===============Avatar move one step by one step ===============*/
    public void AvatarMoveoneStep(TETile[][] world,Map<Integer,Character> N) {
        char avatarMove = N.get(0);
        AvatarMove(world,avatarMove,AvatarP);

    }
    /** ===============Avatar Move in the Map ===============*/
    public void AvatarMove(TETile[][] world,char c,AvatarPosition avatar){
        int avatar_x = avatar.get_x();
        int avatar_y = avatar.get_y();
        world[avatar_x][avatar_y] = Tileset.FLOOR;
        if(c == 'w'){
            avatar_y+=1;
            if(world[avatar_x][avatar_y] == Tileset.WALL){
                changeAvatar(world,avatar_x,avatar_y-1);
            }else {
                changeAvatar(world, avatar_x, avatar_y);
            }
        }else if(c =='s'){
            avatar_y-=1;
            if(world[avatar_x][avatar_y] == Tileset.WALL){
                changeAvatar(world,avatar_x,avatar_y+1);
            }else {
                changeAvatar(world, avatar_x, avatar_y);
            }
        }else if(c == 'a'){
            avatar_x-=1;
            if(world[avatar_x][avatar_y] == Tileset.WALL){
                changeAvatar(world,avatar_x+1,avatar_y);
            }else {
                changeAvatar(world, avatar_x, avatar_y);
            }
        }else if(c == 'd'){
            avatar_x+=1;
            if(world[avatar_x][avatar_y] == Tileset.WALL){
                changeAvatar(world,avatar_x-1,avatar_y);
            }else {
                changeAvatar(world, avatar_x, avatar_y);
            }
        }
    }

    /**=============== Move Avatar in world[x][y] ===============*/
    public void changeAvatar(TETile[][] world,int x,int y){
        world[x][y] = Tileset.AVATAR;
        AvatarP.change_x(x);
        AvatarP.change_y(y);
    }
    public void Mapchange(TETile[][] world,Map<Integer,Character> N){
        //AvatarMoveStep(world,N);
        AvatarMoveoneStep(world,N);
        ter.renderFrame(world);
    }
    /** ============= Create the Entire Map ================*/
    public TETile[][] createMap(int seed,Map<Integer,Character> N){
        //TERenderer ter = new TERenderer();
        //ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = generateSpace();
        Createroom(world,seed);
        CreateHallways(world);
        CreateWall(world);
        createAvatar(world,seed);
        AvatarMoveStep(world,N);
        ter.renderFrame(world);
        return world;
    }
}
