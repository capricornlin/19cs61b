package byow.lab13;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private String randomstring;
    private static int Seed;
    private int width;
    private int height;
    private int round;
    private String r = "Round: ";
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }
        int seed = Integer.parseInt(args[0]);
        Seed = seed;
        MemoryGame game = new MemoryGame(40, 40);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
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

        //TODO: Initialize random number generator
        rand = new Random(Seed);

    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n

        String l = "";
        for(int i = 0; i < n ;i+=1){
            int num = rand.nextInt(26);
            l += CHARACTERS[num];
        }
        return l;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(width/2,height/2,s);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.show();
        StdDraw.pause(1000);
        StdDraw.clear();
        StdDraw.show();
        StdDraw.pause(500);
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        int size = letters.length();
        for(int i = 0; i < size; i+=1){
            String t = ""+letters.charAt(i);
            Font font = new Font("Monaco", Font.BOLD, 30);
            StdDraw.setFont(font);
            StdDraw.text(width/2,height/2,t);
            //StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.show();
            StdDraw.pause(1000);
            StdDraw.clear();
            StdDraw.show();
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String s = "";
        for(int i = 0;i < n;i+=1){
            s+=randomstring.charAt(i);
        }
        String c = "";
        while(!c.equals(s)){
            if(StdDraw.hasNextKeyTyped()){
                c += StdDraw.nextKeyTyped();
            }
        }
        return c;
    }

    public void displayRound(int i){
        String s = "Round ";
        String n = String.valueOf(i);
        String sn = s+n;
        Font font = new Font("Monaco", Font.BOLD, 15);
        StdDraw.setFont(font);
        StdDraw.text(2,height-1,sn);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.show();
        StdDraw.pause(1500);
        StdDraw.clear();

    }

    public void gameover(int i ){
        String s = "Game Over! You made it to round: ";
        String n = String.valueOf(i);
        String sn = s+n;
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(width/2,height/2,sn);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.show();
        StdDraw.pause(2000);
        StdDraw.clear();

    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        //TODO: Establish Engine loop
        int i = 1;
        randomstring = generateRandomString(1);
        displayRound(1);
        drawFrame(randomstring);
        flashSequence(randomstring);
        String s = solicitNCharsInput(1);
        while(s.equals(randomstring)){
            i +=1;
            randomstring = generateRandomString(i);
            displayRound(i);
            drawFrame(randomstring);
            flashSequence(randomstring);
            s = solicitNCharsInput(i);
            }
        //gameover(i);

        System.out.println("gameover");
        }
    }
