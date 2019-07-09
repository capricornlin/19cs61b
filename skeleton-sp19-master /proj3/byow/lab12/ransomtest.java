package byow.lab12;

import java.util.Random;

public class ransomtest {

    private static final Random RANDOM = new Random(2873123);
    public static void main(String[] args){


        for(int i = 0 ;i < 5;i+=1) {
            int a = RANDOM.nextInt(3);
            System.out.println(a);
        }





    }



}
