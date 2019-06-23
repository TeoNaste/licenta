package server;

import model.Accuracy;
import modelDTO.AccuracyDTO;
import modelDTO.PrefixDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.PredictionService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class AutocompleteServer {

    @Autowired
    private PredictionService predictionService;


    @RequestMapping(value = "/predictions",method = RequestMethod.POST)
    @ResponseBody
    public PrefixDTO getPredictions(@RequestBody PrefixDTO prefixKey){
        return predictionService.getPrediction(prefixKey);
    }

    @RequestMapping(value = "/predictions/update",method = RequestMethod.PUT)
    @ResponseBody
    public void updateModel(@RequestBody String text){
        predictionService.updateStatesProbability(text);
    }

    @RequestMapping(value = "/predictions/accuracy",method = RequestMethod.POST)
    @ResponseBody
    public Accuracy updateAccuracy(@RequestBody AccuracyDTO accuracy){
        return predictionService.updateAccuracy(accuracy);
    }
}
