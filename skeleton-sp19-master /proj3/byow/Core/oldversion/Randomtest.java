package byow.Core.oldversion;

import java.util.Random;

public class Randomtest {

    public static void main(String[] args) {

        //Random x = new Random(1234);
        /**
        RoomPosition a = new RoomPosition(1234,80,30);

        //RandomUtils a= new RandomUtils();

        System.out.println(a.rooml);
        System.out.println(a.roomw);
        System.out.println(a.startx);
        System.out.println(a.starty);



        RoomPosition a2 = new RoomPosition(1234,80,30);
        //RandomUtils a2= new RandomUtils();
        System.out.println(a2.rooml);
        System.out.println(a2.roomw);
        System.out.println(a2.startx);
        System.out.println(a2.starty);
        */
        Random x = new Random(12345);

        for(int i = 0;i < 4;i+=1){
            x.nextInt(80);
            if(i == 3) {
                System.out.println(x.nextInt(80));
            }
        }


        /**
        System.out.println(x.nextInt(80));
        System.out.println(x.nextInt(80));
        System.out.println(x.nextInt(80));
        System.out.println(x.nextInt(80));
        System.out.println(x.nextInt(80));
        System.out.println(x.nextInt(80));
        System.out.println(x.nextInt(80));
        System.out.println(x.nextInt(80));
        System.out.println(x.nextInt(80));
        */

        //Random x2 = new Random(12345);
        //System.out.println(x2.nextInt(80));
        //System.out.println(x2.nextInt(80));
        //System.out.println(x2.nextInt(80));






}



}
