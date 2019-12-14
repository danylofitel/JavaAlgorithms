package algorithms.graphs;

import geometry.Point2D;
import lib.StdDraw;

public class EuclidianGraphTest {
    
    private static Point2D[] vertices() {
        return new Point2D[] {
                new Point2D(0, 32768),
                new Point2D(32768, 0),
                new Point2D(0, 0),
                new Point2D(0, 16384),
                new Point2D(16384, 0)
        };
    }
    
    private static void addEdges(Graph g) {
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(0, 4);
        g.addEdge(1, 1);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
    }
    
    private static void addAllEdges(Graph g) {
        for (int i = 0; i < g.V(); ++i) {
            for (int j = 0; j < g.V(); ++j) {
                g.addEdge(i, j);
            }
        }
    }
    
    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        UndirectedEuclidianGraph ueg = new UndirectedEuclidianGraph(vertices());
        addEdges(ueg);
        ueg.show();
        StdDraw.clear();
        addAllEdges(ueg);
        ueg.show();
        StdDraw.clear();
        
        DirectedEuclidianGraph deg = new DirectedEuclidianGraph(vertices());
        addEdges(deg);
        deg.show();
        StdDraw.clear();
        addAllEdges(deg);
        deg.show();
    }
}
