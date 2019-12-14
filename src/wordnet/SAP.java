package wordnet;

import algorithms.graphs.paths.BreadthFirstPaths;
import algorithms.graphs.DirectedGraph;
import algorithms.graphs.paths.GraphPaths;
import collections.Queue;
import collections.QueueOnArrays;

public class SAP {

    private final DirectedGraph graph;
    private final int[] lengthV;
    private final int[] lengthW;
    private Integer ancestor;
    private Integer length;

    public SAP(DirectedGraph G) {
        this.graph = G;
        this.lengthV = new int[G.V()];
        this.lengthW = new int[G.V()];
        this.ancestor = null;
        this.length = null;
    }

    public int length(int v, int w) {
        findAncestor(v, w);
        return ancestor == null ? -1 : length;
    }

    public int ancestor(int v, int w) {
        findAncestor(v, w);
        return ancestor == null ? -1 : ancestor;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        findAncestor(v, w);
        return ancestor == null ? -1 : length;
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        findAncestor(v, w);
        return ancestor == null ? -1 : ancestor;
    }

    private void findAncestor(int v, int w) {
        ancestor = null;
        length = 0;
        for (int i = 0; i < lengthV.length; ++i) {
            lengthV[i] = -1;
            lengthW[i] = -1;
        }

        if (v == w) {
            ancestor = v;
            return;
        }

        Queue<Integer> pathV = new QueueOnArrays<>();
        Queue<Integer> pathW = new QueueOnArrays<>();

        pathV.pushBack(v);
        pathW.pushBack(w);

        lengthV[v] = 0;
        lengthW[w] = 0;

        while (!pathV.isEmpty() || !pathW.isEmpty()) {
            ++length;

            if (!pathV.isEmpty()) {
                int vFront = pathV.popFront();
                for (int adj : graph.adjVertices(vFront)) {
                    if (lengthW[adj] >= 0) {
                        ancestor = adj;
                        length += lengthW[adj];
                        return;
                    }
                    if (lengthV[adj] < 0) {
                        pathV.pushBack(adj);
                        lengthV[adj] = length;
                    }
                }
            }

            if (!pathW.isEmpty()) {
                int wFront = pathW.popFront();
                for (int adj : graph.adjVertices(wFront)) {
                    if (lengthV[adj] >= 0) {
                        ancestor = adj;
                        length += lengthV[adj];
                        return;
                    }
                    if (lengthW[adj] < 0) {
                        pathW.pushBack(adj);
                        lengthW[adj] = length;
                    }
                }
            }
        }
    }

    private void findAncestor(Iterable<Integer> v, Iterable<Integer> w) {
        ancestor = null;
        length = Integer.MAX_VALUE;

        GraphPaths pathsV = new BreadthFirstPaths(graph, v);
        GraphPaths pathsW = new BreadthFirstPaths(graph, w);

        for (int i = 0; i < graph.V(); ++i) {
            if (pathsV.hasPathTo(i) && pathsW.hasPathTo(i)) {
                int pathLength = pathsV.pathLengthTo(i) + pathsW.pathLengthTo(i);

                if (pathLength < length) {
                    ancestor = i;
                    length = pathLength;
                }
            }
        }
    }
}
