package modelDTO;


import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class StateDTO implements Serializable {
    private String value;
    private Double probability;

    public StateDTO(String value, Double probability) {
        this.value = value;
        this.probability = probability;
    }

    public StateDTO() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }
}
