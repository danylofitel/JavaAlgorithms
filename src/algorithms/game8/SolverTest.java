package algorithms.game8;

import lib.In;
import lib.StdOut;

public class SolverTest {

    public static void main(String[] args) {
        In in = new In("puzzle31.txt");
        int N = in.readInt();

        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        }

        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable()) {
            StdOut.println("The board is not solvable");
        } else {
            StdOut.println("Minimal number of steps = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}
