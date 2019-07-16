package byow.Core;

public class StringInput implements Input{
    private String input;
    private int index;

    public StringInput(String s){
        input = s;
        index = 0;
    }

    public char getNextKey(){
        char returnChar = input.charAt(index);
        index += 1;
        return returnChar;
    }
    public boolean possibleNextInput(){
        return index < input.length();
    }
}
