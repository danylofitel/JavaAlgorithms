package algorithms.graphs;

import geometry.Point2D;
import lib.StdDraw;

public class DirectedEuclidianGraph extends DirectedGraph {

    private final Point2D[] points;
    private double max;
    private static final int loopPart = 50;

    public DirectedEuclidianGraph(Point2D[] vertices) {
        super(vertices.length);

        points = new Point2D[vertices.length];
        for (int i = 0; i < vertices.length; ++i) {
            points[i] = vertices[i];
            if (Math.abs(vertices[i].x()) > max) {
                max = vertices[i].x();
            } else if (Math.abs(vertices[i].y()) > max) {
                max = vertices[i].y();
            }
        }
    }

    public void show() {
        // Draw vertices
        for (int i = 0; i < points.length; ++i) {
            StdDraw.point(points[i].x(), points[i].y());

            // Draw all edges of current vertex
            for (int e : adjVertices(i)) {
                if (e != i) {
                    StdDraw.line(points[i].x(), points[i].y(), points[e].x(), points[e].y());

                    double xDist = 4 * loopPart * (points[e].x() - points[i].x()) / max;
                    double yDist = 4 * loopPart * (points[e].y() - points[i].y()) / max;
                    double xFrom = points[e].x() - xDist;
                    double yFrom = points[e].y() - yDist;
                    double dist = Math.sqrt(xDist * xDist + yDist * yDist);

                    StdDraw.filledSquare(xFrom, yFrom, dist);
                } else {
                    StdDraw.circle(points[i].x(), points[i].y(), max / loopPart);
                }
            }
        }
    }
}
