package ai.digamma.business;

public class FScore {

    public double f1Score(double precision, double recall) {
        return (2 * ((precision * recall) / (precision + recall)));
    }

    public double precision(int tp, int fp) {
        return (tp / (double) (tp + fp));
    }

    public double recall(int tp, int fn) {
        return (tp / (double) (tp + fn));
    }

    public double accuracy(int tp, int tn, int fp, int fn) {
        return ((tp + tn) / (double) (tp + tn + fp + fn));
    }

}
