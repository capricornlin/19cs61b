package byow.Core;

import java.util.Random;

public class RoomPosition {
    private int Width;
    private int Height;
    private int seed;
    private int rx,ry;

    public RoomPosition(int s,int W,int H){
        seed = s;
        Width = W;
        Height = H;
    }
    Random x = new Random(seed);
    RandomUtils a= new RandomUtils();

    public int rooml(int range,int n){
        int l=0;
        for(int i =0;i < range ; i+=1) {
            a.uniform(x, 3, 6);
            if(i == n) {
                l = a.uniform(x, 3, 6);
            }
        }

        return l;
    }
    public int roomw(int range,int n){
        int l=0;
        for(int i =0;i < range ; i+=1) {
            a.uniform(x, 3, 6);
            if(i == n) {
                l = a.uniform(x, 3, 6);
            }
        }

        return l;
    }

    public int startx(int range,int n,int roomw){
        int l=0;
        for(int i =0;i < range ; i+=1) {
            a.uniform(x, 0, 80 - roomw-1);
            if(i == n) {
                l = a.uniform(x, 0, 80 - roomw-1);
            }
        }

        return l;
    }
    public int starty(int range,int n,int rooml){
        int l=0;
        for(int i =0;i < range ; i+=1) {
            a.uniform(x, 0, 30 - rooml-1);
            if(i == n) {
                l = a.uniform(x, 0, 30 - rooml-1);
            }
        }

        return l;
    }
    public int endx(int stax,int roomw){
        int l = stax + roomw;
        return l;
    }

    public int endy(int stay,int rooml){
        int l = stay + rooml;
        return l;
    }

    //int rooml = a.uniform(x,3,6);

    //int roomw = a.uniform(x,3,6);
    //int startx = a.uniform(x,0,80 - roomw-1);
    //int starty = a.uniform(x,0,30 - rooml-1);
    //int endx = startx + roomw;
    //int endy = starty + rooml;
    int random_x = addrandomx();
    int random_y = addrandomy();

    public int roomnumber(int range,int start,int end,int n){

        int l=0;
        for(int i =0;i < range ; i+=1) {
            a.uniform(x,start,end);
            if(i == n) {
                l = a.uniform(x, start, end);
            }
        }

        return l;
    }

    public int random_x(int range,int n,int startx,int endx){
        rx=0;
        for(int i =0;i < range ; i+=1) {
            a.uniform(x,startx,endx);
            if(i == n) {
                rx = a.uniform(x, startx, endx);
            }
        }

        return rx;
    }
    public int random_y(int range,int n,int starty,int endy){
        int ry=0;
        for(int i =0;i < range ; i+=1) {
            a.uniform(x,starty,endy);
            if(i == n) {
                ry = a.uniform(x, starty, endy);
            }
        }

        return ry;
    }

    public int addrandomx(){
        return rx;
    }

    public int addrandomy(){
        return ry;
    }

}
