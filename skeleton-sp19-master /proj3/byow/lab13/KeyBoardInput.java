package byow.lab13;

import edu.princeton.cs.algs4.StdDraw;

public class KeyBoardInput {
    public static void main(String[] args) {
        char c = 0;
        double radius = 1 / 500.0;
        StdDraw.setCanvasSize(600, 600);
        while (c != 'q') {
            if (StdDraw.hasNextKeyTyped()) {
                c = StdDraw.nextKeyTyped();
            }
            StdDraw.circle(0.5, 0.5,
                    radius);
            radius = radius + 1 / 500.0;
            StdDraw.show();
            StdDraw.pause(100);

        }
    }
}
