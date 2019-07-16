package byow.Core;
import java.util.Random;

public class newPosition {
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
    int roomx1 = 3;
    int roomx2 = 10;


    public newPosition(int sx,int sy,int ex,int ey,int w,int l,int mw,int ml){
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

        newlegth = randomNumber(roomx1, roomx2);
        newwidth = randomNumber(roomx1, roomx2);
        newend_x = start_x;
        newend_y = randomNumber(start_y,end_y);
        newstart_x = newend_x - newwidth;
        newstart_y = newend_y - newlegth;
        formate(newstart_x,newstart_y,newend_x,newend_y);
    }

    public void go_right(){

        newlegth = randomNumber(roomx1, roomx2);
        newwidth = randomNumber(roomx1, roomx2);
        newstart_x = end_x;
        newstart_y = randomNumber(start_y,end_y);
        newend_x = newstart_x + newwidth;
        newend_y = newstart_y + newlegth;
        formate(newstart_x,newstart_y,newend_x,newend_y);
    }

    public void go_up(){

        newlegth = randomNumber(roomx1, roomx2);
        newwidth = randomNumber(roomx1, roomx2);
        newstart_y = end_y;
        newstart_x = randomNumber(start_x,end_x);
        newend_x = newstart_x + newwidth;
        newend_y = newstart_y + newlegth;
        formate(newstart_x,newstart_y,newend_x,newend_y);
    }

    public void go_down(){

        newlegth = randomNumber(roomx1, roomx2);
        newwidth = randomNumber(roomx1, roomx2);
        newend_y = start_y;
        newend_x = randomNumber(start_x,end_x);
        newstart_x = newend_x - newwidth;
        newstart_y = newend_y - newlegth;
        formate(newstart_x,newstart_y,newend_x,newend_y);
    }





}
