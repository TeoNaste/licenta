package model;


public class State {
    private String value;
    private Double probability;
    private int count;

    public State(String value, Double probability,int count) {
        this.value = value;
        this.probability = probability;
        this.count = count;
    }

    public State(String value) {
        this.value = value;
        this.probability = 1.0;
        this.count = 1;
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



}
