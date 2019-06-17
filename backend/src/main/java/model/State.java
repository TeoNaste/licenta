package model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class State implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String value;
    private Double probability;
    private int count;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "prefix1", referencedColumnName = "prefix1"),
            @JoinColumn(name = "prefix2", referencedColumnName = "prefix2")
    })
    private Prefix prefix;

    public State(String value, Double probability,int count) {
        this.value = value;
        this.probability = probability;
        this.count = count;
        this.prefix = new Prefix();
    }

    public State(String value, Double probability, int count, Prefix prefix) {
        this.value = value;
        this.probability = probability;
        this.count = count;
        this.prefix = prefix;
    }

    public State(String value) {
        this.value = value;
        this.probability = 1.0;
        this.count = 1;
        this.prefix = new Prefix();
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;

        if(!(obj instanceof State))
            return false;

        State state = (State) obj;

        return state.value.equals(this.value);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + value.hashCode();
        result = 31 * result + probability.hashCode();
        result = 31 * result + count;
        return result;
    }



}
