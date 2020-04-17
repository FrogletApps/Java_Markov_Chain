import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;

public class markov {
    public static void main(String[] args)throws Exception
    { 
        //Get words from file
        String wholeString = new String(Files.readAllBytes(Paths.get("testFile.txt")));

        //Convert wholeString to lowercase
        wholeString = wholeString.toLowerCase();
        System.out.println(wholeString);

        //Put words into arraylist
        ArrayList<String> words = new ArrayList<String>();
        for(String word : wholeString.split(" ")) {
            words.add(word);
        }
        System.out.println(words);


        //String to Integer HashMap
        Integer counter = 0;
        HashMap<String, Integer> stringToInteger = new HashMap<String, Integer>();
        for (int i=0; i<words.size(); i++){
            String currentWord = words.get(i);
            if (!stringToInteger.containsKey(currentWord)){
                stringToInteger.put(currentWord, counter);
                counter++;
            }
        }
        System.out.println(stringToInteger);
        //String to Integer HashMap
        counter = 0;
        HashMap<Integer, String> integerToString = new HashMap<Integer, String>();
        for (int i=0; i<words.size(); i++){
            String currentWord = words.get(i);
            if (!integerToString.containsValue(currentWord)){
                integerToString.put(counter, currentWord);
                counter++;
            }
        }
        System.out.println(integerToString);

        //Add counts to count array
        int size = stringToInteger.size();
        int[][] count = new int[size+1][size+1];

        for (int i=0; i<words.size()-1; i++){
            String firstWord = words.get(i);
            String secondWord = words.get(i+1);
            int firstWordInt = stringToInteger.get(firstWord);
            int secondWordInt = stringToInteger.get(secondWord);
            count[firstWordInt][secondWordInt] += 1;
            //System.out.println(count[firstWordInt][secondWordInt]);
        }
        //System.out.println(Arrays.deepToString(count));
        printArray(count);


        //Count the total in each row
        int rowTotals[] = new int[size+1];
        //Print row totals
        for (int y=0; y<size; y++){
            for (int x=0; x<size; x++){
                rowTotals[y] += count[y][x];
            }
            System.out.println(rowTotals[y]);
        }

        //prob
        int[][] prob = new int[size+1][size+1];
    }

    //Prints a 2D array (integer)
    private static void printArray(int[][] twoDArray){
        int ySize = twoDArray.length;
        int xSize = twoDArray[0].length;

        for (int y=0; y<ySize; y++){
            for (int x=0; x<xSize; x++){
                System.out.print(twoDArray[y][x] + ", ");
            }
            System.out.println("");
        }
    }

    //Prints a 2D array (double)
    private static void printArray(double[][] twoDArray){
        int ySize = twoDArray.length;
        int xSize = twoDArray[0].length;

        for (int y=0; y<ySize; y++){
            for (int x=0; x<xSize; x++){
                System.out.print(twoDArray[y][x] + ", ");
            }
            System.out.println("");
        }
    }
}
