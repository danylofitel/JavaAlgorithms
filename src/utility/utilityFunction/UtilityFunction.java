package utility.utilityFunction;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFrame;

public class UtilityFunction {

    // Range of utility
    private static final double MIN_UTILITY = 0.0;
    private static final double MAX_UTILITY = 1.0;

    // Argument range
    private final double a;
    private final double b;

    // Lottery probabilities
    private final double p;
    private final double q;

    // Delta between points
    private final double delta;

    // Computation precision
    private final double e;

    // Points of utility function
    private final Map<Double, Double> utilities;

    // User input scanner
    private final Scanner scanner;

    public UtilityFunction() {
        // Create utility map
        utilities = new TreeMap<>();

        // Create input scanner
        scanner = new Scanner(System.in);

        System.out.println("Left bound of the interval");
        a = readDouble(0, Double.MAX_VALUE, "a can't be negative");

        System.out.println("Right bound of the interval");
        b = readDouble(a, Double.MAX_VALUE, "It has to be larger than " + a);

        System.out.println("Probability of the first item");
        p = readDouble(0, 1, "Probability has to be between 0 and 1");
        q = 1.0 - p;

        System.out.println("Delta between points");
        delta = readDouble(0, Double.MAX_VALUE, "Delta can't be negative");

        System.out.println("e");
        e = readDouble(0, Double.MAX_VALUE, "Precision can't be negative");

        // Build utility function
        build();

        // Close scanner since user input is no longer required
        scanner.close();
    }

    public UtilityFunction(double a, double b, double p, double delta, double e) {
        // Check range of arguments
        if (b < a) {
            throw new IllegalArgumentException("b has to be larger than a");
        } else if (p < 0 || p > 1) {
            throw new IllegalArgumentException("p has to be in range [0; 1]");
        } else if (delta <= 0) {
            throw new IllegalArgumentException("delta can't be negative");
        } else if (e <= 0) {
            throw new IllegalArgumentException("e has to be positive");
        }

        this.a = a;
        this.b = b;
        this.p = p;
        this.q = 1.0 - p;
        this.delta = delta;
        this.e = e;

        // Create utility map
        utilities = new TreeMap<>();

        // Create input scanner
        scanner = new Scanner(System.in);

        // Build utility function
        build();

        // Close scanner since user input is no longer required
        scanner.close();
    }

    public double a() {
        return a;
    }

    public double b() {
        return b;
    }

    public double p() {
        return p;
    }

    public double q() {
        return q;
    }

    public double delta() {
        return delta;
    }

    public double e() {
        return e;
    }

    public Set<Double> keys() {
        return utilities.keySet();
    }

    public double utilityOf(double key) {
        if (key < a || key > b) {
            throw new IllegalArgumentException("Key out of range");
        }

        return utilities.get(key);
    }

    private void build() {
        // Build utility function
        buildFunction();

        // Print the function points
        printFunction();

        // Draw plot of utility function
        drawFunction();
    }

    private void buildFunction() {
        // Add borders of the interval to utility function
        utilities.put(a, MIN_UTILITY);
        utilities.put(b, MAX_UTILITY);

        // Add the whole interval to the queue
        buildInterval(a, b, MIN_UTILITY, MAX_UTILITY);
    }

    private void buildInterval(double amountL, double amountR, double utilityL, double utilityR) {
        if (amountR - amountL > delta) {
            // Calculate utilities of new points
            double u1 = utilityL * p + utilityR * q;
            double u2 = utilityL * p + u1 * q;
            double u3 = u1 * p + utilityR * q;

            // New points with corresponding utility values
            double x1, x2, x3, x4;

            do {
                // Read user input
                x1 = readUtility(amountL, amountR);
                x2 = readUtility(amountL, x1);
                x3 = readUtility(x1, amountR);
                x4 = readUtility(x2, x3);

                // Try again if x1 is not equal to x4
                if (Math.abs(x1 - x4) > e) {
                    System.out.println("Oops, some values did not match! Try again!");
                }
            } while (Math.abs(x1 - x4) > e);

            // Three new points have been calculated
            utilities.put(x1, u1);
            utilities.put(x2, u2);
            utilities.put(x3, u3);

            // Recursively build utility function on subintervals
            buildInterval(amountL, x2, utilityL, u2);
            buildInterval(x2, x1, u2, u1);
            buildInterval(x1, x3, u1, u3);
            buildInterval(x3, amountR, u3, utilityR);
        }
    }

    private void printFunction() {
        for (double key : utilities.keySet()) {
            System.out.println("utility[" + key + "] = " + utilities.get(key));
        }
    }

    private void drawFunction() {
        UtilityFrame frame = new UtilityFrame(utilities, b, MAX_UTILITY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private double readUtility(double left, double right) {
        System.out.println("Value of <" + left + ", " + right + ">");
        return readDouble(left, right, "Input out of range");
    }

    private double readDouble(double min, double max, String error) {
        double input;
        do {
            System.out.print("->");
            input = scanner.nextDouble();
            if (input < min || input > max) {
                System.out.println(error);
            }
        } while (input < min || input > max);
        return input;
    }

    public static void main(String[] args) {
        UtilityFunction uf = new UtilityFunction();
    }
}
