package byow.Core;

import byow.SaveDemo.Editor;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.io.Serializable;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    TETile[][] sworld;
    int si =1;
    String savew = "";
    String as = "";

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        int N0fmove = 0;
        int n = 0;
        String cn = "";
        Map<Integer, Character> number0fMove = new HashMap<>();
        Map<Integer, Character> saveworldMove = new HashMap<>();

        KeyboardInput keyboard = new KeyboardInput(HEIGHT, WIDTH);
        char c = keyboard.getNextKey();

        if (c == 'l' || c == 'L') {
            si = 0;
            as = loadEditor();
            sworld = interactWithInputString(as);
            MapGenerater a = new MapGenerater(sworld);


            while (keyboard.possibleNextInput()) {
                //si += 1;
                char x = keyboard.getNextKey();
                if (x == 'w' || x == 'W') {
                    number0fMove.put(0, 'w');
                    saveworldMove.put(si, 'w');
                } else if (x == 'A' || x == 'a') {
                    number0fMove.put(0, 'a');
                    saveworldMove.put(si, 'a');
                } else if (x == 'S' || x == 's') {
                    number0fMove.put(0, 's');
                    saveworldMove.put(si, 's');
                } else if (x == 'D' || x == 'd') {
                    number0fMove.put(0, 'd');
                    saveworldMove.put(si, 'd');
                } else if (x == 'Q' || x == 'q') {
                    number0fMove.put(0, 'q');
                    int size = saveworldMove.size();
                    savew = "";
                    for (int i = 0; i < size; i += 1) {
                        savew += saveworldMove.get(i);
                    }
                    saveEditor(as+savew);
                    System.exit(0);

                }
                si += 1;
                /** Avatar Move without blink */
                a.Mapchange(sworld, number0fMove);
            }

        }

        if (c == 'N' || c == 'n') {
            saveworldMove.put(0, 'n');
            Font font = new Font("Monaco", Font.BOLD, 15);
            StdDraw.setFont(font);
            StdDraw.text(0.5, 0.30, "Enter seed");
            StdDraw.show();
            StdDraw.pause(1000);

            while (keyboard.possibleNextInput()) {
                char x = keyboard.getNextKey();
                saveworldMove.put(si, x);
                si += 1;
                if (x == 'S' || x == 's') {
                    saveworldMove.put(si, 's');
                    break;
                } else {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(0.5, 0.25, cn);
                    int k = Character.getNumericValue(x);
                    n = n * 10 + k;
                    cn = String.valueOf(n);
                    font = new Font("Monaco", Font.BOLD, 15);
                    StdDraw.setFont(font);
                    StdDraw.setPenColor(StdDraw.YELLOW);
                    StdDraw.text(0.5, 0.25, cn);
                    StdDraw.show();
                    StdDraw.pause(100);
                }
            }
            //when we input seed and generate the world!
            MapGenerater a = new MapGenerater();
            TETile[][] world = a.createMap(n, number0fMove);
            si-=1;
            //Avatar show in the map!
            while (keyboard.possibleNextInput()) {
                si += 1;
                char x = keyboard.getNextKey();
                if (x == 'w' || x == 'W') {
                    number0fMove.put(0, 'w');
                    saveworldMove.put(si, 'w');
                } else if (x == 'A' || x == 'a') {
                    number0fMove.put(0, 'a');
                    saveworldMove.put(si, 'a');
                } else if (x == 'S' || x == 's') {
                    number0fMove.put(0, 's');
                    saveworldMove.put(si, 's');
                } else if (x == 'D' || x == 'd') {
                    number0fMove.put(0, 'd');
                    saveworldMove.put(si, 'd');
                    /**Save the seed and directions String*/
                } else if (x == 'Q' || x == 'q') {
                    number0fMove.put(0, 'q');
                    int size = saveworldMove.size();
                    savew = "";
                    for (int i = 0; i < size; i += 1) {
                        savew = savew + saveworldMove.get(i);
                    }
                    saveEditor(as+savew);
                    System.exit(0);
                }
                /** Avatar Move without blink */
                a.Mapchange(world, number0fMove);
            }
        }
        if(c == 'Q' || c == 'q'){
            System.exit(0);
        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().

        int n = 0;
        int N0fmove = 0;
        Map<Integer,Character> number0fMove = new HashMap<>();
        StringInput s = new StringInput(input);
        char c = s.getNextKey();

        //Get the first word 'N'
        if(c == 'N' || c == 'n') {
            while (s.possibleNextInput()) {
                char x = s.getNextKey();
                if(x == 'S' || x == 's'){
                    break;
                }else {
                    int k = Character.getNumericValue(x);
                    n = n * 10 + k;
                }
            }

            //Avatar show in the map!
            while(s.possibleNextInput()){
                char x = s.getNextKey();
                if(x == 'w' || x == 'W'){
                    number0fMove.put(N0fmove,'w');
                }else if(x == 'A' || x == 'a'){
                    number0fMove.put(N0fmove,'a');
                }else if(x == 'S' || x =='s'){
                    number0fMove.put(N0fmove,'s');
                }else if(x == 'D' || x == 'd'){
                    number0fMove.put(N0fmove,'d');
                }
                N0fmove+=1;
            }
        }
        TETile[][] finalWorldFrame;
        MapGenerater a = new MapGenerater();
        finalWorldFrame = a.createMap(n,number0fMove);
        return finalWorldFrame;
    }

    private static String loadEditor() {
        File f = new File("./saveworld.txt");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (String) os.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        /* In the case no Editor has been saved yet, we return a new one. */
        return  "";
    }

    private static void saveEditor(String sworld) {
        File f = new File("./saveworld.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(sworld);
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
