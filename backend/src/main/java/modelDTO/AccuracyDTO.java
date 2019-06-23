package modelDTO;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class AccuracyDTO implements Serializable {

    private Double accuracy;
    private Double wordsCount;
    private Double rightPredictions;

    public AccuracyDTO() {
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
