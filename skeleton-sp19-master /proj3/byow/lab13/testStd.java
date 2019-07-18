package byow.lab13;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class testStd {
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static String a = "abcdefghijklmnopqrstuvwxyz";
    static int width = 80;
    static int height = 30;

    public testStd(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();

    }

    public static void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        for(int i = 0;i < 10;i+=1) {
            StdDraw.clear(StdDraw.BLACK);
            //StdDraw.enableDoubleBuffering();
            Font font = new Font("Monaco", Font.BOLD, 30);
            StdDraw.setFont(font);
            StdDraw.text(width / 2, height / 2, s);
            StdDraw.setPenColor(StdDraw.WHITE);
            //StdDraw.enableDoubleBuffering();
            StdDraw.show();
            StdDraw.pause(500);
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.show();
            StdDraw.pause(500);
        }

    }



    public static void main(String[] args) {
        testStd t = new testStd(80,30);
        t.drawFrame(a);
        StdDraw.hasNextKeyTyped();

    }

}


