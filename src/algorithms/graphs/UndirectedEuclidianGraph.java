package algorithms.graphs;

import geometry.Point2D;
import lib.StdDraw;

public class UndirectedEuclidianGraph extends UndirectedGraph {

    private final Point2D[] points;
    private double max;
    private static final int loopPart = 100;

    public UndirectedEuclidianGraph(Point2D[] vertices) {
        super(vertices.length);
        
        max = 0.0;
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
                } else {
                    StdDraw.circle(points[i].x(), points[i].y(), max / loopPart);
                }
            }
        }
    }
}
