package markovModel;

import parameters.PrefixKey;
import parameters.State;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MarkovModel {
    private String filename;
    private Map<PrefixKey,List<State>> predictibilityTransitions;
    private Map<PrefixKey, Integer> prefixCount;

    public MarkovModel(String filename) {
        this.filename = filename;
        predictibilityTransitions = new HashMap<>();
        prefixCount = new HashMap<>();
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

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = DataProcessor.removePunctuation(line).split(" ");
                int lineLen = words.length;
                for(int i=0;i < lineLen; i++){
                    String currentWord = words[i];
                    if(i==0){
                        //first word
                        PrefixKey prefix = new PrefixKey();
                        addState(prefix,currentWord);
                    }else{
                        String prevWord = words[i-1];
//                        if(i==words.length-1) {
//                            //sentence end
//                            PrefixKey prefix = new PrefixKey(prevWord, currentWord);
//                            addState(prefix, "$END$");
//                        }else
                        if(i==1){
                            //second word
                            PrefixKey prefix = new PrefixKey(prevWord);
                            addState(prefix, currentWord);
                        }else{
                            String prevPrevWord = words[i-2];
                            PrefixKey prefix = new PrefixKey(prevPrevWord, prevWord);
                            addState(prefix,currentWord);
                        }
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

    public Map<PrefixKey,List<State>> getPredictibilityTransitions(){
        return predictibilityTransitions;
    }
}
