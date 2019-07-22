package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class KeyboardInput implements Input {
    private int height;
    private int width;


    public KeyboardInput(int h,int w) {
        height = h;
        width = w;
        StdDraw.setCanvasSize(width*16, height*16);
        //make the backround become black.
        StdDraw.clear(StdDraw.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        //setPen => change the text's(or others) color.
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(0.5, 0.8, "CS61B: THE GAME");
        font = new Font("Monaco", Font.BOLD, 15);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.5, "New Game(N)");
        StdDraw.text(0.5,0.45,"Load Game(L)");
        StdDraw.text(0.5,0.40,"Quit(Q)");
        StdDraw.show();
        StdDraw.pause(1000);
    }

    /** Collect the input String type with Keyboard. */
    public char getNextKey() {
        //StdDraw.hasNextKeyTyped();
        //StdDraw.nextKeyTyped();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toUpperCase(StdDraw.nextKeyTyped());
                if (true) {
                    System.out.print(c);
                }
                return c;
            }
        }
    }
    public boolean possibleNextInput(){
        return true;
    }
}
