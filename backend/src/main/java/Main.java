import markovModel.DataProcessor;
import markovModel.MarkovModel;
import model.PrefixKey;
import model.State;
import repository.PredictionRepository;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        MarkovModel mk = new MarkovModel("D:\\an3\\licenta\\text_completion\\backend\\src\\main\\resources\\trainingData.txt");
        mk.trainMarkovModel();

        PredictionRepository pr = new PredictionRepository(mk);

        Scanner s = new Scanner(System.in);
        PrefixKey prefixKey = new PrefixKey();
        List<State> predictions = pr.getPrediction(prefixKey);
        System.out.println(predictions.get(0).getValue()+" "+predictions.get(1).getValue()+" "+predictions.get(2).getValue());

        while(true){
            String word = s.next();
            if(word.equals("$end$"))
                break;
            prefixKey.setPrefix1(prefixKey.getPrefix2());
            prefixKey.setPrefix2(word);

            predictions = pr.getPrediction(prefixKey);
            predictions.forEach(prediction -> System.out.print(prediction.getValue()+" "));
        }
    }
}
