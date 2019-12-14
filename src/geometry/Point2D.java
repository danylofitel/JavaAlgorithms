package geometry;

import java.util.Comparator;
import lib.StdDraw;

public class Point2D implements Comparable<Point2D> {

    public final Comparator<Point2D> SLOPE_ORDER = new SlopeOrder(this);
    public final Comparator<Point2D> POLAR_ORDER = new PolarOrder(this);
    private final int x;
    private final int y;

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public double r() {
        return Math.sqrt(x * x + y * y);
    }

    public double theta() {
        return Math.atan2(y, x);
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point2D that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point2D that) {
        if (this.x == that.x) {
            if (this.y == that.y) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        } else {
            if (this.y == that.y) {
                return +0.0;
            } else {
                return ((double) (that.y - this.y)) / ((double) (that.x - this.x));
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        } else {
            return ((Point2D) obj).x == this.x
                    && ((Point2D) obj).y == this.y;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        return hash;
    }

    @Override
    public int compareTo(Point2D that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y == that.y) {
            return Integer.compare(this.x, that.x);
        } else {
            return 1;
        }
    }

    private static class SlopeOrder implements Comparator<Point2D> {

        private final Point2D base;

        public SlopeOrder(Point2D base) {
            this.base = base;
        }

        @Override
        public int compare(Point2D p, Point2D q) {
            return Double.compare(base.slopeTo(p), base.slopeTo(q));
        }
    }

    private static class PolarOrder implements Comparator<Point2D> {

        private final Point2D base;

        public PolarOrder(Point2D base) {
            this.base = base;
        }

        @Override
        public int compare(Point2D p, Point2D q) {
            if (p.compareTo(base) == 0) {
                return -1;
            } else if (q.compareTo(base) == 0) {
                return 1;
            } else if (p.y > base.y && q.y < base.y) {
                return -1;
            } else if (p.y < base.y && q.y > base.y) {
                return 1;
            } else {
                return -ccw(base, p, q);
            }
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
