package server;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import model.PrefixKey;
import model.State;
import org.springframework.web.bind.annotation.*;
import repository.PredictionRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AutocompleteServer {

    @Autowired
    private PredictionRepository predictionRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name) {
        Gson gson = new Gson();
        return gson.toJson("Hello");
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<State> getPredictions(@RequestBody PrefixKey prefixKey){
//        return predictionRepository.getTop3Prediction(prefixKey);
        return new ArrayList<>();
    }
}
