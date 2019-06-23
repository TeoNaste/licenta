package converters;

import model.Prefix;
import model.PrefixKey;
import modelDTO.PrefixDTO;
import modelDTO.StateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PrefixConverter {

    @Autowired
    private StateConverter stateConverter;

    public PrefixDTO convertToDTO(Prefix prefix){
        PrefixDTO dto = new PrefixDTO();
        dto.setPrefix1(prefix.getPrefixKey().getPrefix1());
        dto.setPrefix2(prefix.getPrefixKey().getPrefix2());
        Set<StateDTO> stateDTOS = new HashSet<>();
        prefix.getStates().forEach(state ->{
            stateDTOS.add(stateConverter.convertToDTO(state));
        });
        dto.setStates(stateDTOS);

        return dto;
    }

    public PrefixKey convertFromDTO(PrefixDTO dto){
        PrefixKey key = new PrefixKey();
        key.setPrefix1(dto.getPrefix1());
        key.setPrefix2(dto.getPrefix2());

        return key;
    }
}
