package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Prefix implements Serializable {
    @EmbeddedId
    private PrefixKey prefixKey;
    private int count;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "prefix1",referencedColumnName = "prefix1"),
            @JoinColumn(name = "prefix2",referencedColumnName = "prefix2")
    })
    private Set<State> states;

    public Prefix(PrefixKey prefixKey, int count, Set<State> states) {
        this.prefixKey = prefixKey;
        this.count = count;
        this.states = states;
    }

    public Prefix(PrefixKey prefixKey, int count) {
        this.prefixKey = prefixKey;
        this.count = count;
        states = new HashSet<>();
    }

    public Prefix() { }

    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }

    public PrefixKey getPrefixKey() {
        return prefixKey;
    }

    public void setPrefixKey(PrefixKey prefixKey) {
        this.prefixKey = prefixKey;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
