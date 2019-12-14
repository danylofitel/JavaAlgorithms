package algorithms.lineRecognition;

import geometry.Point2D;
import java.util.Arrays;
import lib.Stopwatch;

public class Brute4 {

    private double precision;
    private Point2D[] points;

    public Brute4(double precision) {
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

    public void processData(Point2D[] points) {
        if (points == null) {
            throw new NullPointerException("Null argument in processData method");
        }

        this.points = Arrays.copyOf(points, points.length);

        process();
    }

    private void process() {
        drawPoints();

        Stopwatch timer = new Stopwatch();

        Arrays.sort(points);

        if (points.length >= 4) {
            for (int i = 0; i < points.length; ++i) {
                for (int j = i + 1; j < points.length; ++j) {
                    for (int k = j + 1; k < points.length; ++k) {
                        if (collinear(points[i], points[j], points[k])) {
                            for (int l = k + 1; l < points.length; ++l) {
                                if (collinear(points[i], points[j], points[l])) {
                                    drawLine(points[i], points[j], points[k], points[l]);
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(timer.elapsedTime() + " milliseconds.");
    }

    private void drawPoints() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    private void drawLine(Point2D p, Point2D q, Point2D r, Point2D s) {
        Point2D min = p;
        Point2D max = p;

        if (q.compareTo(min) < 0) {
            min = q;
        } else if (q.compareTo(max) > 0) {
            max = q;
        }

        if (r.compareTo(min) < 0) {
            min = r;
        } else if (r.compareTo(max) > 0) {
            max = r;
        }

        if (s.compareTo(min) < 0) {
            min = s;
        } else if (s.compareTo(max) > 0) {
            max = s;
        }

        min.drawTo(max);
        System.out.println(p + " -> " + q + " -> " + r + " -> " + s);
    }

    private boolean collinear(Point2D p, Point2D q, Point2D r) {
        double pq = p.slopeTo(q);
        double pr = p.slopeTo(r);
        return (pq == pr || Math.abs(pq - pr) < precision);
    }
}
