package algorithms.convex;

import collections.Stack;
import collections.StackOnArrays;
import geometry.Point2D;
import java.awt.Color;
import java.util.Arrays;
import java.util.Iterator;
import lib.StdDraw;

public class ConvexHull {

    // Indicates whether the points on the convex hull between edges should also be included
    private boolean includeAllConvexPoints = false;
    // Points inside the convex hull
    private Point2D[] points = null;
    // Sequence of points that form a convex hull
    private Stack<Point2D> convexHull = null;
    // Color of the points
    private Color pointsColor = Color.BLACK;
    // Color of the convex hull
    private Color convexHullColor = Color.BLACK;

    public Color getPointsColor() {
        return pointsColor;
    }

    public void setPointsColor(Color newColor) {
        pointsColor = newColor;
    }

    public Color getConvexHullColor() {
        return convexHullColor;
    }

    public void setConvexHullColor(Color newColor) {
        convexHullColor = newColor;
    }

    public void build(Point2D[] points, boolean includeAllConvexPoints) {
        this.includeAllConvexPoints = includeAllConvexPoints;
        this.points = Arrays.copyOf(points, points.length);

        drawPoints();
        buildConvex();
        drawConvexHull();
    }

    // Draw all points
    private void drawPoints() {
        StdDraw.setPenColor(pointsColor);
        for (Point2D p : points) {
            p.draw();
        }
    }

    // Push the sequence of points of the convex hull to the stack
    private void buildConvex() {
        // Initialize the stack
        convexHull = new StackOnArrays<>();

        // Sort the points
        Arrays.sort(points);

        // If there are less than three points, there's no need to perform calculations
        if (count() <= 3) {
            for (Point2D p : points) {
                convexHull.push(p);
            }
        } else {
            // Get the bottom point
            Point2D bottom = bottomPoint();

            // Sort all points by the polar order relative to the bottom point
            Arrays.sort(points, bottom.POLAR_ORDER);

            // Push the first point to the stack
            convexHull.push(points[0]);

            // Initialize the next pair of points
            int currentIndex = 1;
            Point2D current = points[currentIndex];
            Point2D next = points[++currentIndex];

            // For all remaining points in the array
            while (currentIndex <= points.length) {
                // Find the turn direction

                int ccw = Point2D.ccw(convexHull.top(), current, next);

                if (ccw > 0) {
                    // The points make anticlockwise turn

                    // Push the current point to the stack and get the next point
                    convexHull.push(current);
                    current = next;
                    next = points[++currentIndex % points.length];
                } else if (ccw == 0) {
                    // The points are collinear

                    if (current.equals(next)) {
                        // Skip duplicate point
                    } else if (isBetween(current, convexHull.top(), next)) {
                        // The current point is between the previous and the next ones

                        if (includeAllConvexPoints) {
                            convexHull.push(current);
                        }

                        current = next;
                    } else if (isBetween(convexHull.top(), current, next)) {
                        // The previous point is between the current and the next ones

                        if (!includeAllConvexPoints) {
                            convexHull.pop();
                        }

                        convexHull.push(current);
                    } else {
                        // The next point is between the previous and the current ones

                        if (includeAllConvexPoints) {
                            convexHull.push(next);
                        }
                    }

                    next = points[++currentIndex % points.length];
                } else if (ccw < 0) {
                    // The points make counterclockwise turn

                    current = convexHull.pop();
                }
            }
        }
    }

    // Draw lines between points of the convex hull
    private void drawConvexHull() {
        StdDraw.setPenColor(convexHullColor);

        Iterator<Point2D> i = convexHull.iterator();
        if (i.hasNext()) {
            Point2D start = i.next();
            Point2D from = start;

            while (i.hasNext()) {
                Point2D to = i.next();
                System.out.println(from + " ->" + to);
                from.drawTo(to);
                from = to;
            }

            System.out.println(from + " ->" + start);
            from.drawTo(start);
        }
    }

    // Number of distinct points
    private int count() {
        int count = 0;
        int first = 0;

        // Calculate the number of distinct points
        while (first < points.length) {
            ++count;

            int last = first;

            // Skip all copies of current point
            while (last < points.length - 1 && points[first].equals(points[last + 1])) {
                ++last;
            }

            first = last + 1;
        }

        return count;
    }

    // Get the bottom point with the greatest x
    private Point2D bottomPoint() {
        int y = points[0].y();
        int index = 0;

        while (index + 1 < points.length && points[index + 1].y() == y) {
            ++index;
        }

        return points[index];
    }

    // Point p is between q1 and q2 assuming that they all are collinear
    private static boolean isBetween(Point2D p, Point2D q1, Point2D q2) {
        int minX = Math.min(q1.x(), q2.x());
        int maxX = Math.max(q1.x(), q2.x());
        int minY = Math.min(q1.y(), q2.y());
        int maxY = Math.max(q1.y(), q2.y());

        return minX <= p.x() && p.x() <= maxX
                && minY <= p.y() && p.y() <= maxY;
    }
}
