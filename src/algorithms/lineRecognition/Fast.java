package algorithms.lineRecognition;

import geometry.Point2D;
import java.util.Arrays;
import lib.Stopwatch;

public class Fast implements LineRecognition {

    private int threshold;
    private double precision;
    private Point2D[] points;
    private Point2D[] permutation;

    public Fast(double precision) {
        if (precision < 0.0) {
            throw new IllegalArgumentException("Invalid precision value " + precision);
        }

        this.precision = precision;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecison(double value) {
        if (value < 0.0) {
            throw new IllegalArgumentException("Invalid precision value " + value);
        }

        this.precision = value;
    }

    @Override
    public void processData(Point2D[] points, int threshold) {
        if (points == null) {
            throw new NullPointerException("Null argument in processData method");
        }

        if (threshold < 2) {
            throw new IllegalArgumentException("Invalid threshold value " + threshold);
        }

        this.threshold = threshold;
        this.points = Arrays.copyOf(points, points.length);

        process();
    }

    private void process() {
        drawPoints();
        permutation = Arrays.copyOf(points, points.length);

        Stopwatch timer = new Stopwatch();

        Arrays.sort(points);

        for (int i = 0; i < points.length; ++i) {
            Point2D p = points[i];
            Arrays.sort(permutation, p.SLOPE_ORDER);

            boolean newLine = true;
            int first = 0;
            while (first < permutation.length - threshold + 2) {
                int last = first;

                if (permutation[first].compareTo(p) <= 0) {
                    newLine = false;
                }

                while (last < permutation.length - 1
                        && sameSlope(p, permutation[first], permutation[last + 1])) {
                    ++last;
                    if (permutation[last].compareTo(p) <= 0) {
                        newLine = false;
                    }
                }

                if (newLine && last - first >= threshold - 2) {
                    drawLine(p, first, last);
                }

                first = last + 1;
                newLine = true;
            }
        }

        System.out.println(timer.elapsedTime() + " milliseconds.");
    }

    private void drawPoints() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    private void drawLine(Point2D p, int first, int last) {
        Arrays.sort(permutation, first, last + 1);

        String output = p.toString();

        for (int i = first; i <= last; ++i) {
            output += " -> " + permutation[i];
        }

        System.out.println(output);
        p.drawTo(permutation[last]);
    }

    private boolean sameSlope(Point2D p, Point2D first, Point2D second) {
        return p.slopeTo(first) == p.slopeTo(second)
                || Math.abs(p.slopeTo(first) - p.slopeTo(second)) < precision;
    }
}
