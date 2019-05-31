package repository;

import markovModel.DataProcessor;
import markovModel.MarkovModel;
import parameters.PrefixKey;
import parameters.State;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class PredictionRepository {
    private MarkovModel markovModel;

    public PredictionRepository(MarkovModel markovModel) {
        this.markovModel = markovModel;
    }

    public List<State> getPrediction(PrefixKey prefix){
        List<State> topPredictions = markovModel.getPredictibilityTransitions().get(prefix);

        if(topPredictions == null)
            return null;

        if(topPredictions.size() < 3)
            return topPredictions;

        return topPredictions.subList(0,3);
    }

    public BigDecimal testModel(String dataFile){
        File file = new File(dataFile);
        BigInteger countWords = BigInteger.ZERO;
        BigInteger rigthPredictions = BigInteger.ZERO;

        BufferedReader br = null;
        PrefixKey prefixKey = new PrefixKey();
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                countWords = BigInteger.ZERO;
                String[] words = DataProcessor.removePunctuation(line).split(" ");
                countWords = countWords.add(BigInteger.valueOf(words.length));

                for (String word : words ){
                    List<State> predictions = getPrediction(prefixKey);

                    State currentState = new State(word);
                    if(predictions != null && predictions.contains(currentState))
                        rigthPredictions = rigthPredictions.add(BigInteger.ONE);

                    prefixKey.setPrefix1(prefixKey.getPrefix2());
                    prefixKey.setPrefix2(word);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new BigDecimal(rigthPredictions.divide(countWords).multiply(BigInteger.valueOf(100)));
    }
}
