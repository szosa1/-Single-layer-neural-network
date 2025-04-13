public class Perceptron {
    double[] weights;
    double threshold;
    double learnRate;
    int length;

    public Perceptron(double[] weights, double threshold, double learnRate) {
        this.weights = weights;
        this.threshold = threshold;
        this.learnRate = learnRate;
        length = weights.length;
        for(int i=0; i<length; i++) {
            weights[i] =0.0;
        }
    }

    public int compute(double[] input){
        double sum =0;
        for(int i=0; i<weights.length; i++){
            sum += weights[i] * input[i];
        }
        if(sum >= threshold){
            return 1;
        }else return 0;
    }

    public void learn(double[] input, int expectedAnswer){
        int guess = compute(input);

        for(int i=0; i<weights.length; i++){
            weights[i] += (expectedAnswer - guess) * learnRate * input[i];
        }

        threshold = threshold + (guess - expectedAnswer) * learnRate;
    }

    public double computeActivation(double[] input) {
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * input[i];
        }
        return sum - threshold;
    }
}
