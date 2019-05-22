package repository;

import markovModel.MarkovModel;
import model.PrefixKey;
import model.State;

import java.util.List;
import java.util.stream.Collectors;

public class PredictionRepository {
    private MarkovModel markovModel;

    public PredictionRepository(MarkovModel markovModel) {
        this.markovModel = markovModel;
    }

    public List<State> getPrediction(PrefixKey prefix){
        List<State> topPredictions = markovModel.getPredictibilityTransitions().get(prefix);

        if(topPredictions.size() < 3)
            return topPredictions;

        return topPredictions.subList(0,3);
    }
}
