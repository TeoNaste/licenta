package markovModel;

import model.Prefix;
import org.springframework.stereotype.Component;
import model.PrefixKey;
import model.State;
import repository.PrefixRepository;
import repository.StateRepository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MarkovModel {
    private String filename;
    private Map<PrefixKey,List<State>> predictibilityTransitions;
    private Map<PrefixKey, Integer> prefixCount;
    private StateRepository stateRepository;
    private PrefixRepository prefixRepository;

    public MarkovModel() {
        this.filename = "D:\\an3\\licenta\\text_completion\\backend\\src\\main\\resources\\smol.txt";
//        this.filename = "D:\\an3\\licenta\\text_completion\\backend\\src\\main\\resources\\generatedDataSet.txt";
        predictibilityTransitions = new HashMap<>();
        prefixCount = new HashMap<>();
        stateRepository = new StateRepository();
        prefixRepository = new PrefixRepository();
    }

    public void addState(final PrefixKey prefix, String value){
        State newState = new State(value);
        List<State> states = predictibilityTransitions.get(prefix);

        if(states == null) {
            //new prefix
            states = new LinkedList<>();
            prefixCount.put(prefix, 0);
        }else{
            //check if state already exists
            Optional<State> existentState = states.stream().filter(state -> state.getValue().equals(value)).findAny();

            if(existentState.isPresent()){
                //state already exists
                newState.setCount(existentState.get().getCount()+1);
                states = states.stream().filter(state -> !state.getValue().equals(value)).collect(Collectors.toList());
            }
        }
        states.add(newState);

        //update transitions
        predictibilityTransitions.put(prefix,states);

        //update prefix count
        int noPrefix = prefixCount.get(prefix);
        prefixCount.put(prefix,noPrefix+1);
    }

    public void trainMarkovModel(){
        File file = new File(filename);
        String prefix1 ="",prefix2 = "";
        Boolean endOfSentence = false;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = DataProcessor.removePunctuation(line).split(" ");
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

                    PrefixKey prefix = new PrefixKey(prefix1, prefix2);
                    addState(prefix,currentWord);

                    if(endOfSentence){
                        prefix1 = "";
                        prefix2 = "";
                        endOfSentence = false;
                    }else {
                        prefix1 = prefix2;
                        prefix2 = currentWord;
                    }
                }

                predictibilityTransitions.keySet().forEach(key->{
                    int keyCount = prefixCount.get(key);
                    predictibilityTransitions.get(key).forEach(state -> {
                        state.setProbability(state.getCount() / (double)keyCount);
                    });
                    //sort
                    List<State> sorted = predictibilityTransitions.get(key).stream()
                            .sorted((s1,s2)-> s2.getProbability().compareTo(s1.getProbability())).collect(Collectors.toList());
                    predictibilityTransitions.put(key,sorted);
                });

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<State> getTop3Prediction(PrefixKey prefix){
        List<State> topPredictions = predictibilityTransitions.get(prefix);

        if(topPredictions == null)
            return null;

        if(topPredictions.size() < 3)
            return topPredictions;

        return topPredictions.subList(0,3);
    }

    public List<State> getTop5Prediction(PrefixKey prefix){
        List<State> topPredictions = predictibilityTransitions.get(prefix);

        if(topPredictions == null)
            return null;

        if(topPredictions.size() < 5)
            return topPredictions;

        return topPredictions.subList(0,5);
    }

    public Double testModel(String dataFile){
        File file = new File(dataFile);
        Double countWords = 0.0;
        Double rigthPredictions = 0.0;

        BufferedReader br = null;
        PrefixKey prefixKey = new PrefixKey();
        Boolean endOfSentence = false;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = DataProcessor.removePunctuation(line).split(" ");
                countWords += words.length;

                for (String word : words ){
                    List<State> predictions = getTop3Prediction(prefixKey);

                    if(word.matches(".*[.?!]")){
                        endOfSentence = true;
                        word = word.replaceAll("[.?!]","");
                    }

                    State currentState = new State(word);
                    if(predictions != null && predictions.contains(currentState))
                        rigthPredictions ++;

                    if(endOfSentence) {
                        prefixKey.setPrefix1("");
                        prefixKey.setPrefix2("");
                        endOfSentence = false;
                    }else {
                        prefixKey.setPrefix1(prefixKey.getPrefix2());
                        prefixKey.setPrefix2(word);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rigthPredictions/countWords * 100;
    }

    public void saveToDB(){
        this.predictibilityTransitions.forEach((prefixKey, states) -> {
            int prefixCount = this.prefixCount.get(prefixKey);
            Prefix prefix = new Prefix(prefixKey,prefixCount);

            states.forEach(state -> {
                prefix.getStates().add(state);
                state.setPrefix(prefix);
                if(prefixRepository.findOne(prefixKey) == null)
                    prefixRepository.save(prefix);
                else
                    prefixRepository.update(prefixKey,prefix);
             });
        });
    }



}
