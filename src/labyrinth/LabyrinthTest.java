package labyrinth;

import algorithms.graphs.search.GraphSearch;
import algorithms.graphs.UndirectedGraph;
import lib.In;

public class LabyrinthTest {

    private static final String testFile = "tinyDG.txt";

    private static void printSearchStats(GraphSearch search) {
        System.out.println("Path exists = " + search.hasPath());
        System.out.println("Path length = " + search.pathLength());
        if (search.path() != null) {
            for (int i : search.path()) {
                System.out.print(i + " - ");
            }
            System.out.println();
        }
        System.out.println("Vertices visited = " + search.verticesVisited());
    }

    private static void testLabyrinth(UndirectedGraph g) {
        System.out.println("Testing labyrinth on a graph");
        System.out.println(g);

        System.out.println("Testing labyrinth");
        for (int i = 0; i < g.V(); ++i) {
            System.out.println("Testing path from vertex " + i);
            Labyrinth lab = new Labyrinth(g, i);
            System.out.println("Start = " + lab.start());
            System.out.println("Finish = " + lab.finish());
            System.out.println();

            System.out.println("Depth first stats");
            printSearchStats(lab.dfs());
            System.out.println();

            System.out.println("Breadth first stats");
            printSearchStats(lab.bfs());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        UndirectedGraph in = new UndirectedGraph(new In(testFile));

        UndirectedGraph g = new UndirectedGraph(in.V());
        testLabyrinth(g);
        System.out.println();

        for (int i = 0; i < g.V(); ++i) {
            g.addEdge(i, i);
        }

        testLabyrinth(g);
        System.out.println();

        g = in;
        testLabyrinth(g);
        System.out.println();

        for (int i = 0; i < g.V(); ++i) {
            for (int j = 0; j < g.V(); ++j) {
                g.addEdge(i, j);
            }
        }

        testLabyrinth(g);
    }
}
