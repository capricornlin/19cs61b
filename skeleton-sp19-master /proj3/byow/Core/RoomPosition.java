package byow.Core;

import java.util.Random;

public class RoomPosition {
    private int Width;
    private int Height;
    public RoomPosition(int W,int H){
        Width = W;
        Height = H;
    }

    public int randomNumber(int i,int j){
        Random random = new Random();
        RandomUtils r = new RandomUtils();
        int a = r.uniform(random,i,j);
        return a;
    }

    int rooml = randomNumber(3,6);
    int roomw = randomNumber(3,6);
    int startx = randomNumber(0,80 - roomw-1);
    int starty = randomNumber(0,30 - rooml-1);
    int endx = startx + roomw;
    int endy = starty + rooml;
    int random_x = random_x();
    int random_y = random_y();

    public int random_x(){
        int a = randomNumber(startx,endx);
        return a;
    }

    public int random_y(){
        int a = randomNumber(starty,endy);
        return a;
    }








}
