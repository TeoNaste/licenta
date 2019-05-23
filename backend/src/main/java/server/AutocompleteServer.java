package server;

import markovModel.MarkovModel;
import parameters.PrefixKey;
import parameters.State;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repository.PredictionRepository;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class AutocompleteServer {
    private MarkovModel markovModel = new MarkovModel("D:\\an3\\licenta\\text_completion\\backend\\src\\main\\resources\\trainingData.txt");
    private PredictionRepository predictionRepository = new PredictionRepository(markovModel);

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<State> getPredictions(@RequestBody PrefixKey prefixKey){
        return predictionRepository.getPrediction(prefixKey);
    }
}
