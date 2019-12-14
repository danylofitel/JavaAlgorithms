package algorithms.game8;

import collections.Deque;
import collections.DequeOnList;
import collections.MinPriorityQueue;

public class Solver {

    // Sequence of board positions
    private Deque<Board> solution;

    public Solver(Board initial) {
        if (isSolvable(initial)) {
            solve(initial);
        }
    }

    public boolean isSolvable() {
        return solution != null;
    }

    public int moves() {
        return solution == null ? -1 : solution.size() - 1;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    private boolean isSolvable(Board board) {
        int permutations = 0;

        // Copy all items in the same order
        int[] blocks = new int[board.blocks()];
        int index = 0;
        for (int i = 0; i < board.dimension(); ++i) {
            for (int j = 0; j < board.dimension(); ++j) {
                if (board.get(i, j) != 0) {
                    blocks[index++] = board.get(i, j);
                }
            }
        }

        // Check if all items have been copied
        assert index == blocks.length;

        // Calculate the number of permutations by sorting an array
        for (int i = 0; i < blocks.length; ++i) {
            for (int j = i; j > 0; --j) {
                if (blocks[j] < blocks[j - 1]) {
                    int temp = blocks[j];
                    blocks[j] = blocks[j - 1];
                    blocks[j - 1] = temp;
                    ++permutations;
                } else {
                    break;
                }
            }
        }

        // The number of permutations must not be odd
        return permutations % 2 == 0;
    }

    private void solve(Board board) {
        MinPriorityQueue<SearchNode> searchGraph = new MinPriorityQueue<>();

        // Put the initial board to the priority queue
        searchGraph.pushBack(new SearchNode(board, null, 0));

        // While the goal board has not yet been found, extract the board with the highest priority
        while (!searchGraph.front().current.isGoal()) {
            SearchNode current = searchGraph.popFront();
            // Get all neighbours
            for (Board b : current.current.neighbors()) {
                // Put all neighbours except the previous board to the queue
                if (!b.equals(current.previous == null ? null : current.previous.current)) {
                    searchGraph.pushBack(new SearchNode(b, current, current.moves + 1));
                }
            }
        }

        // Put the sequence of boards to the solution deque
        solution = new DequeOnList<>();
        SearchNode current = searchGraph.front();
        while (current != null) {
            solution.addFirst(current.current);
            current = current.previous;
        }

        // Check solution invariants
        assert solution.getFirst().equals(board);
        assert solution.getLast().isGoal();
        assert solution.size() == searchGraph.front().moves + 1;
    }

    private static class SearchNode implements Comparable<SearchNode> {

        private final Board current;
        private final SearchNode previous;
        private final int moves;

        public SearchNode(Board current, SearchNode previous, int steps) {
            this.current = current;
            this.previous = previous;
            this.moves = steps;
        }

        @Override
        public int compareTo(SearchNode that) {
            return Integer.compare(this.current.manhattan() + this.moves,
                    that.current.manhattan() + that.moves);
        }
    }
}
