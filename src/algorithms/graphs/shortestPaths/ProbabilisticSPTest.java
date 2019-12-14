package algorithms.graphs.shortestPaths;

import algorithms.graphs.DirectedEdge;
import algorithms.graphs.ProbabilisticEdge;
import algorithms.graphs.ProbabilisticGraph;

import java.util.Arrays;

public class ProbabilisticSPTest {

    public static void main(String[] args) {
        final int V = 6;
        final ProbabilisticEdge[] edges = {
                new ProbabilisticEdge(0, 1, 2.0, 0.1),
                new ProbabilisticEdge(0, 1, 1.0, 0.9),
                new ProbabilisticEdge(0, 4, 5.0, 0.3),
                new ProbabilisticEdge(0, 4, 4.0, 0.2),
                new ProbabilisticEdge(0, 4, 10.0, 0.5),
                new ProbabilisticEdge(1, 4, 0.0, 0.1),
                new ProbabilisticEdge(1, 4, 1.0, 0.9),
                new ProbabilisticEdge(1, 5, 20, 0.1),
                new ProbabilisticEdge(1, 5, 9, 0.9),
                new ProbabilisticEdge(2, 1, 8, 0.8),
                new ProbabilisticEdge(2, 1, 4, 0.2),
                new ProbabilisticEdge(4, 3, 3, 0.5),
                new ProbabilisticEdge(4, 3, 1, 0.5),
                new ProbabilisticEdge(3, 5, 9, 0.3),
                new ProbabilisticEdge(3, 5, 11, 0.7),
                new ProbabilisticEdge(5, 2, 7, 0.7),
                new ProbabilisticEdge(5, 2, 3, 0.3),
                new ProbabilisticEdge(5, 4, 9, 0.4),
                new ProbabilisticEdge(5, 4, 6, 0.6),
        };

        ProbabilisticGraph pg = new ProbabilisticGraph(V, Arrays.asList(edges));

        ProbabilisticGuaranteedSP minmax = new ProbabilisticGuaranteedSP(pg, 0);
        ProbabilisticExpectedSP expected = new ProbabilisticExpectedSP(pg, 0);

        int from = 0;
        int to = 5;

        for (DirectedEdge e : minmax.pathTo(to)) System.out.print(e.to() + " <- ");
        System.out.println(from);

        for (DirectedEdge e : expected.pathTo(to)) System.out.print(e.to() + " <- ");
        System.out.println(from);
    }
}
