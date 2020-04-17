import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.*;

public class markov {
    public static void main(String[] args)throws Exception { 
        //Get words from file and set to lowercase
        String wholeString = new String(Files.readAllBytes(Paths.get("testFile.txt"))).toLowerCase();
        System.out.println("1: " + wholeString);

        //Put words into arraylist (trim() removes new lines)
        ArrayList<String> words = new ArrayList<String>();
        //Split on one or more whitespaces
        for(String word : wholeString.split("\\s+")) { 
            words.add(word.trim());
        }
        System.out.println("2: " + words);

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
        System.out.println("Size = " + size);
        int[][] count = new int[size][size];

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

        double[][] prob = new double[size][size+1];
        int y = 0;
        int x = 0;
        double[] cumulativeProb = new double[size];

        for (y=0; y<size; y++){
            for (x=0; x<size; x++){
                prob[y][x+1] = Math.round(((double)count[y][x] / (double)rowTotals[y]) + cumulativeProb[y]);
                cumulativeProb[y] = prob[y][x+1];
                /*System.out.println("x = " + x + " y = " + y);
                System.out.println("currentVal = " + prob[y][x]);
                System.out.println("cumulative = " + cumulativeProb[y]);
                printArray(prob);
                System.out.println();*/
            }
        }
        printArray(prob);
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
