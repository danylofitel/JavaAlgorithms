package algorithms.graphs.degrees;

import algorithms.graphs.DirectedGraph;

public class DegreesTest {

    private static final int V = 5;

    public static void main(String[] args) {
        DirectedGraph g = new DirectedGraph(V);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(2, 4);

        Degrees d = new Degrees(g);
        System.out.println(g);
        for (int i = 0; i < g.V(); ++i) {
            System.out.println(i + "; In: " + d.inDegree(i) + "; Out: " + d.outDegree(i));
        }
        System.out.print("Sources: ");
        for (int s : d.sources()) {
            System.out.print(s + " ");
        }
        System.out.println("");
        System.out.print("Sinks: ");
        for (int s : d.sinks()) {
            System.out.print(s + " ");
        }
        System.out.println("");

        System.out.println("Is map: " + d.isMap());

        DirectedGraph map = new DirectedGraph(V);
        for (int i = 0; i < V; ++i) {
            map.addEdge(i, (i + 1) % V);
        }
        d = new Degrees(map);
        System.out.println(map);
        System.out.println("Is map: " + d.isMap());
    }
}
