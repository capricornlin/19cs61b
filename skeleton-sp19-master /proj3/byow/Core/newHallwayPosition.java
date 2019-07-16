package byow.Core;

import java.util.Random;

public class newHallwayPosition {
    int start_x;
    int start_y;
    int end_x;
    int end_y;
    int length;
    int width;

    int newstart_x;
    int newstart_y;
    int newend_x;
    int newend_y;
    int newlegth;
    int newwidth;

    int mapw;
    int mapl;
    int roomx2 = 5;


    public newHallwayPosition(int sx,int sy,int ex,int ey,int w,int l,int mw,int ml){
        start_x = sx;
        start_y = sy;
        end_x = ex;
        end_y = ey;
        length = l;
        width = w;
        mapw = mw;
        mapl = ml;
    }

    public int randomNumber(int i,int j){
        if(i == j){
            return i;
        }
        Random random = new Random();
        RandomUtils r = new RandomUtils();
        int a = r.uniform(random,i,j);
        return a;
    }

    public void formate(int sx,int sy,int ex, int ey){
        if(sx >= mapw){
            newstart_x = mapw - 1;
        }else if(sx < 0){
            newstart_x = 0;
        }
        if(sy >= mapl){
            newstart_y = mapl - 1;
        }else if(sy < 0){
            newstart_y = 0;  // holy fuck.
        }
        if(ex >= mapw){
            newend_x = mapw - 1;
        }else if(ex < 0){
            newend_x = 0;
        }
        if(ey >= mapl){
            newend_y = mapl - 1;
        }else if(ey < 0){
            newend_y = 0;
        }
    }

    public void go_left(){
        newlegth = randomNumber(3,roomx2);
        newend_x = start_x;
        newstart_x = newend_x - newlegth;
        newstart_y = randomNumber(start_y,end_y);
        newend_y = newstart_y;
        formate(newstart_x,newstart_y,newend_x,newend_y);
    }
    public void go_right(){

    }





}
