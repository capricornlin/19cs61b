package byow.Core;

public class AvatarPosition {
    private int x;
    private int y;

    public AvatarPosition(int i,int j) {
        x = i;
        y = j;
    }
    public int get_x(){
        return x;
    }
    public int get_y(){
        return y;
    }
    public void change_x(int changex){
        x = changex;
    }
    public void change_y(int changey){
        y = changey;
    }

}
