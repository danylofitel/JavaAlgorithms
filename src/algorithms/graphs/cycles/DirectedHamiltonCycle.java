package algorithms.graphs.cycles;

import algorithms.graphs.order.DepthFirstOrder;
import algorithms.graphs.DirectedGraph;
import algorithms.graphs.order.GraphOrder;
import collections.Stack;
import collections.StackOnArrays;
import java.util.Iterator;

public class DirectedHamiltonCycle {

    private Stack<Integer> cycle;
    private boolean hasHamiltonCycle;

    public DirectedHamiltonCycle(DirectedGraph G) {
        if (G == null) throw new IllegalArgumentException("Graph is null");

        cycle = new StackOnArrays<>();
        hasHamiltonCycle = true;
        findHamiltonCycle(G);
    }

    public Iterable<Integer> cycle() {
        if (hasHamiltonCycle) {
            return cycle;
        } else {
            return null;
        }
    }

    public boolean isHamiltonian() {
        return hasHamiltonCycle;
    }

    private void findHamiltonCycle(DirectedGraph G) {
        GraphOrder topologicalSort = new DepthFirstOrder(G);
        checkHamiltonianOrder(G, topologicalSort.postOrder().iterator());
        if (!hasHamiltonCycle) {
            cycle = new StackOnArrays<>();
            hasHamiltonCycle = true;
            checkHamiltonianOrder(G, topologicalSort.reversePostOrder().iterator());
        }
    }

    private void checkHamiltonianOrder(DirectedGraph G, Iterator<Integer> i) {
        int visited = 0;
        Integer first = null;
        if (i.hasNext()) {
            first = i.next();
            int v = first;
            int w = v;
            cycle.push(v);
            ++visited;
            while (i.hasNext()) {
                w = i.next();
                if (w != first) {
                    ++visited;
                }
                if (!G.hasEdge(v, w)) {
                    hasHamiltonCycle = false;
                    return;
                }
                v = w;
                cycle.push(v);
            }
        }

        if (first != null) {
            assert cycle.top().equals(first);
            if (G.hasEdge(cycle.top(), first)) {
                cycle.push(first);
            } else {
                hasHamiltonCycle = false;
            }
        }

        if (visited != G.V()) {
            hasHamiltonCycle = false;
        }
    }
}
