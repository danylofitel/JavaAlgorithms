package algorithms.game8;

public class Game8Test {

    public static void main(String[] args) {
        final int dimension = 3;
        final int iterations = 10;

        // Create two-dimensional array
        int[][] blocks = new int[dimension][];
        for (int i = 0; i < blocks.length; ++i) {
            blocks[i] = new int[dimension];
        }
        
        // Initialize the array
        for (int i = 0; i < dimension * dimension - 1; ++i) {
            blocks[i / dimension][i % dimension] = i + 1;
        }

        // A testing game board
        Board board = new Board(blocks);

        // Execute the test
        for (int i = 0; i < iterations; ++i) {
            // Shuffle the board
            board.randomShuffle();
            
            // Solve the puzzle
            Solver solver = new Solver(board);
            
            // Print results
            System.out.println("Puzzle:");
            System.out.println(board);
            System.out.println("Solvable = " + solver.isSolvable());
            System.out.println("Moves = " + solver.moves());
            System.out.println("Solution:");
            for (Board b : solver.solution()) {
                System.out.println(b);
            }
        }
    }
}
