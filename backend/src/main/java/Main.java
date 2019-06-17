import markovModel.MarkovModel;


public class Main {

    public static void main(String[] args){
//        DataProcessor.generateDataFromFile("D:\\an3\\licenta\\text_completion\\backend\\src\\main\\resources\\thegoodplace.txt");
        MarkovModel mk = new MarkovModel();
        mk.trainMarkovModel();
        mk.saveToDB();
//

//        Double evaluation = mk.testModel("D:\\an3\\licenta\\text_completion\\backend\\src\\main\\resources\\generatedTestData.txt");
//        System.out.println(evaluation);

//        Scanner s = new Scanner(System.in);
//        PrefixKey prefixKey = new PrefixKey();
//        List<State> predictions = mk.getPrediction(prefixKey);
//        System.out.println(predictions.get(0).getValue()+" "+predictions.get(1).getValue()+" "+predictions.get(2).getValue());

//        while(true){
//            String word = s.next();
//            if(word.equals("$end$"))
//                break;
//            prefixKey.setPrefix1(prefixKey.getPrefix2());
//            prefixKey.setPrefix2(word);
//
//            predictions = pr.getPrediction(prefixKey);
//            if(predictions != null)
//                predictions.forEach(prediction -> System.out.print(prediction.getValue()+" "));
//        }
    }
}
