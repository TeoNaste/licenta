package converters;

import model.State;
import modelDTO.StateDTO;
import org.springframework.stereotype.Component;

@Component
public class StateConverter {

    public StateDTO convertToDTO(State state) {
        StateDTO dto = new StateDTO();
        dto.setValue(state.getValue());
        dto.setProbability(state.getProbability());
        return dto;
    }


}
