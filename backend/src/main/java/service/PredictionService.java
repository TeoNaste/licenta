package service;

import converters.PrefixConverter;

import markovModel.DataProcessor;
import model.Accuracy;
import model.Prefix;
import model.PrefixKey;
import model.State;
import modelDTO.AccuracyDTO;
import modelDTO.PrefixDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.AccuracyRepository;
import repository.PrefixRepository;
import java.util.HashSet;

@Component
public class PredictionService {

    @Autowired
    private AccuracyRepository accuracyRepository;
    @Autowired
    private PrefixRepository prefixRepository;
    @Autowired
    private PrefixConverter prefixConverter;

    public PredictionService() {
    }

    public PrefixDTO getPrediction(PrefixDTO prefixKeyDTO){
        PrefixKey prefixKey = new PrefixKey();
        prefixKey.setPrefix1(prefixKeyDTO.getPrefix1());
        prefixKey.setPrefix2(prefixKeyDTO.getPrefix2());

        Prefix theOne = prefixRepository.findOne(prefixKey);
        if(theOne == null){
            //new prefix
            addNewPrefix(prefixKey);
        }
        return prefixConverter.convertToDTO(prefixRepository.findOne(prefixKey));
    }

    private void addNewPrefix(PrefixKey prefixKey){
        Prefix newPrefix = new Prefix();

        newPrefix.setPrefixKey(prefixKey);
        newPrefix.setCount(1);
        newPrefix.setStates(new HashSet<>());

        prefixRepository.save(newPrefix);
    }

    public void updateStatesProbability(String text){
        String prefix1 ="",prefix2 = "";
        boolean endOfSentence = false, isNewState = true;
        String[] words = DataProcessor.removePunctuation(text).split(" ");
        int lineLen = words.length;

        for(int i=0;i < lineLen; i++){
            String currentWord = words[i];
            if(currentWord.matches(".*[.?!]")){
                //end of sentence
                endOfSentence = true;
                currentWord = currentWord.replaceAll("[.?!]","");
            }
            if(currentWord.equals(""))
                continue;

            PrefixKey key = new PrefixKey(prefix1,prefix2);
            Prefix prefix = prefixRepository.findOne(key);
            prefix.setCount(prefix.getCount()+1);

            //iterate states and update their probabilities
            for (State state: prefix.getStates()) {
                if(state.getValue().equals(currentWord)){
                    state.setCount(state.getCount()+1);
                    isNewState = false;
                }
                state.setProbability((double)state.getCount() / prefix.getCount());
            }

            if(isNewState){
                State newState = new State();
                newState.setCount(1);
                newState.setValue(currentWord);
                newState.setPrefix(prefix);
                newState.setProbability((double)newState.getCount() / prefix.getCount());
                prefix.getStates().add(newState);
            }

            prefixRepository.update(key, prefix);
            isNewState = true;

            if(endOfSentence){
                prefix1 = "";
                prefix2 = "";
                endOfSentence = false;
            }else {
                prefix1 = prefix2;
                prefix2 = currentWord;
            }
        }
    }

    public Accuracy updateAccuracy(AccuracyDTO a){
        int lastId = accuracyRepository.size();
        Accuracy accuracy = accuracyRepository.findOne(lastId);
        Accuracy toPutAccuracy = new Accuracy();
        toPutAccuracy.setRightPredictions(accuracy.getRightPredictions()+a.getRightPredictions());
        toPutAccuracy.setWordsCount(accuracy.getWordsCount() + a.getWordsCount());
        double newAccuracy = toPutAccuracy.getRightPredictions() / toPutAccuracy.getWordsCount() * 100;
        toPutAccuracy.setAccuracy(newAccuracy);

        accuracyRepository.save(toPutAccuracy);
        return toPutAccuracy;
    }



}
