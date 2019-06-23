package modelDTO;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

@Entity
public class PrefixDTO implements Serializable {

    private String prefix1;
    private String prefix2;
    private Set<StateDTO> states;

    public PrefixDTO() {
    }

    public Set<StateDTO> getStates() {
        return states;
    }

    public void setStates(Set<StateDTO> states) {
        this.states = states;
    }

    public String getPrefix1() {
        return prefix1;
    }

    public void setPrefix1(String prefix1) {
        this.prefix1 = prefix1;
    }

    public String getPrefix2() {
        return prefix2;
    }

    public void setPrefix2(String prefix2) {
        this.prefix2 = prefix2;
    }
}
