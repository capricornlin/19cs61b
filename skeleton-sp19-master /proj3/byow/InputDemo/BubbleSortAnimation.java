package byow.InputDemo;

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
// 其實應該使用StdDraw.show(int t); //單位為毫秒,不過這個方法被deprecated了,
// 只能把裡面的三個函式單獨寫出來運行了
                StdDraw.show();
                StdDraw.pause(500);//單位為毫秒,表示每畫完一個小矩形後停頓的時間,也就是Thread.sleep(..)
                StdDraw.enableDoubleBuffering(); //很重要,如果不寫,會導致每次畫矩形時螢幕閃爍
                StdDraw.clear();
            }
            StdDraw.clear();
        }
    }
}
