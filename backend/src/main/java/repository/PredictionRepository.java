package repository;

import markovModel.MarkovModel;
import parameters.PrefixKey;
import parameters.State;

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
}
