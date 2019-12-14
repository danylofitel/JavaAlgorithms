package algorithms.lineRecognition;

import geometry.Point2D;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import lib.Stopwatch;

public class Brute implements LineRecognition {

    private int threshold;
    private double precision;
    private Point2D[] points;
    private Set<Set<Point2D>> lines;

    public Brute(double precision) {
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

        process(points);
    }

    private void process(Point2D[] data) {
        points = Arrays.copyOf(data, data.length);
        lines = new HashSet<>();
        drawPoints();

        Stopwatch timer = new Stopwatch();

        for (Point2D p : points) {
            Set<Point2D> set = new TreeSet<>();
            set.add(p);
            selectSubsets(set, p, 0, 1);
        }

        drawLines();

        System.out.println(timer.elapsedTime() + " milliseconds.");
    }

    private void selectSubsets(Set<Point2D> current, Point2D firstPoint, double slope, int currentIndex) {
        if (currentIndex < points.length) {
            Point2D currentPoint = points[currentIndex];

            if (current.size() > 1) {
                double currentSlope = firstPoint.slopeTo(currentPoint);

                if ((currentSlope == slope || Math.abs(currentSlope - slope) < precision)) {
                    current.add(currentPoint);
                    selectSubsets(current, firstPoint, currentSlope, currentIndex + 1);
                    current.remove(currentPoint);
                } else {
                    if (current.size() >= threshold) {
                        addLine(current);
                    }
                }
            } else if (current.size() == 1) {
                double newSlope = firstPoint.slopeTo(currentPoint);

                current.add(currentPoint);
                selectSubsets(current, firstPoint, newSlope, currentIndex + 1);
                current.remove(currentPoint);
            }

            if (current.size() + points.length - currentIndex + 1 >= threshold) {
                selectSubsets(current, firstPoint, slope, currentIndex + 1);
            }
        } else {
            if (current.size() >= threshold) {
                addLine(current);
            }
        }
    }

    private void addLine(Set<Point2D> setOfPoints) {
        Set<Point2D> line = new TreeSet<>();
        line.addAll(setOfPoints);
        lines.add(line);
    }

    private void drawPoints() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    private void drawLines() {
        for (Set<Point2D> line : lines) {
            Point2D min = (Point2D) ((SortedSet) line).first();
            Point2D max = (Point2D) ((SortedSet) line).last();

            Iterator<Point2D> i = line.iterator();
            String output = i.next().toString();

            while (i.hasNext()) {
                output += " -> " + i.next().toString();
            }

            System.out.println(output);
            min.drawTo(max);
        }
    }
}
