package byow.lab13;

import edu.princeton.cs.algs4.StdDraw;

public class BubbleSortAnimation {
    public final static int N = 50; //表示矩形的個數
    public static void print(double[] a) {
        double x, y, rw, rh;
        for (int i = 0; i < N; i++) {
            x = 1.0 * i / N;
            y = a[i] / 2.0;
            rw = 0.5 / N;
            rh = a[i] / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
    public static void main(String[] args){
        while (true) { //不停的演示排序動畫
            double[] a = new double[N];
            for (int i = 0; i < N; i++) {
                a[i] = Math.random();
            }
            for (int i = 0; i < N - 1; i++) {
                for (int j = 0; j < N - i - 1; j++) {
                    if (a[j] > a[j + 1]) {
                        double t = a[j];
                        a[j] = a[j + 1];
                        a[j + 1] = t;
                    }
                }
                print(a);
                StdDraw.show();
                StdDraw.pause(500);
                StdDraw.enableDoubleBuffering();
                StdDraw.clear();
            }
            StdDraw.clear();
        }
    }

}
