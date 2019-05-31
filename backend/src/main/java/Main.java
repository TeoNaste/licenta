import markovModel.MarkovModel;
import parameters.PrefixKey;
import parameters.State;
import repository.PredictionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        MarkovModel mk = new MarkovModel("D:\\an3\\licenta\\text_completion\\backend\\src\\main\\resources\\doriangray.txt");
        mk.trainMarkovModel();

        PredictionRepository pr = new PredictionRepository(mk);
        BigDecimal evaluation = pr.testModel("D:\\an3\\licenta\\text_completion\\backend\\src\\main\\resources\\testData.txt");
        System.out.println(evaluation);
//        BigDecimal evaluation2 = pr.testModel("D:\\an3\\licenta\\text_completion\\backend\\src\\main\\resources\\largerTestData.txt");

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
            if(predictions != null)
                predictions.forEach(prediction -> System.out.print(prediction.getValue()+" "));
        }
    }
}
