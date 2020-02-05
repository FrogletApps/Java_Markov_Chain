import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class markov {
    public static void main(String[] args)throws Exception
    { 
        //Get words from file
        File file = new File("./testFile.txt"); 
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        String wholeString = "";
        String st; 
        while ((st = br.readLine()) != null) {
            wholeString += st;
        }
        br.close();

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

        Integer size = words.size();
        int[][] count = new int[size+1][size+1];

        for (int i=0; i<words.size()-1; i++){
            String firstWord = words.get(i);
            String secondWord = words.get(i+1);
            int firstWordInt = stringToInteger.get(firstWord);
            int secondWordInt = stringToInteger.get(secondWord);

            //System.out.println(count[firstWordInt][secondWordInt]);
            /*if (count[firstWordInt][secondWordInt]){
                count[firstWordInt][secondWordInt] = 0;
            }*/
            count[firstWordInt][secondWordInt] += 1;
            //System.out.println(count[firstWordInt][secondWordInt]);
        }
        System.out.println(Arrays.toString(count));
    }
}
