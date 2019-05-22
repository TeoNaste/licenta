package markovModel;

import java.util.List;

public class DataProcessor {

    public static String removePunctuation(String sentence){
       return sentence.replaceAll("[.?!,;:\\[\\]{}()'*\"’“”]"," ")
               .replaceAll("( )( )*"," ");
    }

    public static double calculateProbaility(){

        return 420;
    }
}
