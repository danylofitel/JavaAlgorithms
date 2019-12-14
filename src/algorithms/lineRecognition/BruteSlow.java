package algorithms.lineRecognition;

import geometry.Point2D;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import lib.Stopwatch;
import algorithms.sets.SelectSubsets;

public class BruteSlow implements LineRecognition {

    private int threshold;
    private double precision;
    private Point2D[] points;

    public BruteSlow(double precision) {
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

        Stopwatch timer = new Stopwatch();

        SelectSubsets<Point2D> selector = new SelectSubsets<>();

        Set<Set<Point2D>> lines = selector.selectSubsetsOfSize(points, threshold, points.length);
        for (Set<Point2D> line : lines) {
            checkPoints(line);
        }

        System.out.println(timer.elapsedTime() + " milliseconds.");
    }

    private void drawPoints() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    private void checkPoints(Set<Point2D> set) {
        Iterator<Point2D> i = set.iterator();
        Point2D min = i.next();
        Point2D max = i.next();
        Point2D first = min;
        String output = min.toString() + " -> " + max.toString();
        if (min.compareTo(max) > 0) {
            Point2D temp = min;
            min = max;
            max = temp;
        }

        double slope = min.slopeTo(max);
        boolean sameLine = true;

        while (i.hasNext()) {
            Point2D p = i.next();
            output += " -> " + p.toString();
            double pSlope = first.slopeTo(p);
            if (!(slope == pSlope || Math.abs(slope - pSlope) < precision)) {
                sameLine = false;
                break;
            }

            if (p.compareTo(max) > 0) {
                max = p;
            } else if (p.compareTo(min) < 0) {
                min = p;
            }
        }
        if (sameLine) {
            System.out.println(output);
            min.drawTo(max);
        }
    }
}
