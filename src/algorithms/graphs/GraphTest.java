package algorithms.graphs;

import lib.In;

public class GraphTest {

    private static final String testFile = "tinyCG.txt";

    private static UndirectedGraph undirectedTextInput() {
        return new UndirectedGraph(new In(testFile));
    }

    private static DirectedGraph directedTextInput() {
        return new DirectedGraph(new In(testFile));
    }

    private static UndirectedGraph undirectedManualInput() {
        UndirectedGraph g = new UndirectedGraph(5);
        g.addEdge(0, 0);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(4, 4);
        return g;
    }

    private static DirectedGraph directedManualInput() {
        return new DirectedGraph(undirectedTextInput());
    }

    private static void testGraph(Graph g) {
        System.out.println(g);
        System.out.println();

        System.out.println("Connecting all vertices");
        for (int i = 0; i < g.V(); ++i) {
            for (int j = 0; j < g.V(); ++j) {
                if (i != j) {
                    g.addEdge(i, j);
                }
            }
        }
        System.out.println(g);

        System.out.println("Adding all loops");
        for (int i = 0; i < g.V(); ++i) {
            g.addEdge(i, i);
        }
        System.out.println(g);
    }

    public static void main(String[] args) {
        testGraph(undirectedTextInput());
        testGraph(directedTextInput());
        testGraph(undirectedManualInput());
        testGraph(directedManualInput());
    }
}
