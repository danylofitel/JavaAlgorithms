package utility.utilityGame;

public class UtilityGame {

    // Left utility bound
    private final double left;

    // Right utility bound
    private final double right;

    public UtilityGame(double left, double right) throws IllegalArgumentException {
        // Utility bounds must form a correct interval
        if (left > right) {
            throw new IllegalArgumentException();
        }

        this.left = left;
        this.right = right;
    }

    public double play(double x) throws IllegalArgumentException {
        if (x < left || x > right) {
            throw new IllegalArgumentException("Argument " + x + " is out of range");
        }

        // A result is a random variable
        double result = 0.0;

        // Y is a random value of any distribution
        double Y = left + (double) (right - left) * Math.random();

        System.out.println("Y = " + Y);

        if (Y >= x) {
            // If Y exceeds x, Y is the end result
            result = Y;
        } else {
            // If Y is smaller than X, the result is a new random value X
            // X = left with probability 1/2 and X = right with probability 1/2
            double X = Math.random() > 0.5 ? right : left;
            result = X;
        }

        return result;
    }
}
