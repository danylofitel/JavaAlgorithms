package algorithms.lineRecognition;

import geometry.Line2D;
import geometry.Point2D;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import lib.Stopwatch;

public class Smart implements LineRecognition {

    private int threshold;
    private Point2D[] points;
    private Map<Line2D, Set<Point2D>> lines;

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
        lines = new LinkedHashMap<>();
        drawPoints();

        Stopwatch timer = new Stopwatch();

        for (int i = 0; i < points.length; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                Line2D currentLine = new Line2D(points[i], points[j]);
                TreeSet<Point2D> pointsOnCurrentLine = (TreeSet<Point2D>) lines.get(currentLine);

                if (pointsOnCurrentLine == null) {
                    pointsOnCurrentLine = new TreeSet<>();
                    lines.put(currentLine, pointsOnCurrentLine);
                }

                pointsOnCurrentLine.add(points[i]);
                pointsOnCurrentLine.add(points[j]);
            }
        }

        drawLines();

        System.out.println(timer.elapsedTime() + " milliseconds.");
    }

    private void drawPoints() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    private void drawLines() {
        for (Set<Point2D> pointsOnLine : lines.values()) {
            if (pointsOnLine.size() >= threshold) {
                Point2D min = (Point2D) ((SortedSet) pointsOnLine).first();
                Point2D max = (Point2D) ((SortedSet) pointsOnLine).last();

                Iterator<Point2D> i = pointsOnLine.iterator();
                String output = i.next().toString();

                while (i.hasNext()) {
                    output += " -> " + i.next().toString();
                }

                System.out.println(output);
                min.drawTo(max);
            }
        }
    }
}
