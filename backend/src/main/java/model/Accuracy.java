package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Accuracy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double accuracy;
    private Double wordsCount;
    private Double rightPredictions;

    public Accuracy() {
    }

    public Accuracy(Double accuracy, Double wordsCount, Double rightPredictions) {
        this.accuracy = accuracy;
        this.wordsCount = wordsCount;
        this.rightPredictions = rightPredictions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Double getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(Double wordsCount) {
        this.wordsCount = wordsCount;
    }

    public Double getRightPredictions() {
        return rightPredictions;
    }

    public void setRightPredictions(Double rightPredictions) {
        this.rightPredictions = rightPredictions;
    }
}
