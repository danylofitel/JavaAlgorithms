package algorithms.game8;

import collections.Bag;
import collections.BagOnList;
import java.util.Arrays;

public class Board {

    private final int[][] blocks;
    private final int count;

    public Board(int[][] blocks) {
        // Check if the dimensions are valid
        for (int i = 0; i < blocks.length; ++i) {
            assert blocks.length == blocks[i].length;
        }

        this.blocks = blocks;
        this.count = blocks.length * blocks.length - 1;
    }

    public Board(Board other) {
        blocks = deepCopy(other.blocks);
        count = other.count;
    }

    public int get(int i, int j) {
        return blocks[i][j];
    }

    public int dimension() {
        return blocks.length;
    }

    public int blocks() {
        return count;
    }

    public int hamming() {
        int wrong = 0;

        for (int i = 0; i < count; ++i) {
            if (blocks[i / blocks.length][i % blocks.length] != i + 1) {
                ++wrong;
            }
        }

        return wrong;
    }

    public int manhattan() {
        int distance = 0;

        for (int i = 0; i < count; ++i) {
            // Coordinates of current cell
            int curX = i / blocks.length;
            int curY = i % blocks.length;

            // Value of current cell
            int current = blocks[curX][curY] - 1;

            // Goal coordinates of current value
            int goalX = current / blocks.length;
            int goalY = current % blocks.length;

            distance += (Math.abs(curX - goalX) + Math.abs(curY - goalY));
        }

        return distance;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other.getClass() != this.getClass()) {
            return false;
        } else {
            for (int i = 0; i < blocks.length; ++i) {
                for (int j = 0; j < blocks[i].length; ++j) {
                    if (blocks[i][j] != ((Board) other).blocks[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Arrays.deepHashCode(this.blocks);
        return hash;
    }

    public Iterable<Board> neighbors() {
        Bag<Board> neighbors = new BagOnList<>();

        // Find position of the empty block
        int emptyBlock = emptyBlock();
        int emptyX = emptyBlock / blocks.length;
        int emptyY = emptyBlock % blocks.length;

        // Left shift
        if (emptyX > 0) {
            rotate(emptyX, emptyY, emptyX - 1, emptyY);
            neighbors.add(new Board(deepCopy(blocks)));
            rotate(emptyX, emptyY, emptyX - 1, emptyY);
        }

        // Right shift
        if (emptyX < blocks.length - 1) {
            rotate(emptyX, emptyY, emptyX + 1, emptyY);
            neighbors.add(new Board(deepCopy(blocks)));
            rotate(emptyX, emptyY, emptyX + 1, emptyY);
        }

        // Up shift
        if (emptyY > 0) {
            rotate(emptyX, emptyY, emptyX, emptyY - 1);
            neighbors.add(new Board(deepCopy(blocks)));
            rotate(emptyX, emptyY, emptyX, emptyY - 1);
        }

        // Down shift
        if (emptyY < blocks.length - 1) {
            rotate(emptyX, emptyY, emptyX, emptyY + 1);
            neighbors.add(new Board(deepCopy(blocks)));
            rotate(emptyX, emptyY, emptyX, emptyY + 1);
        }

        return neighbors;
    }

    public void randomShuffle() {
        // Find position of the empty block
        int emptyBlock = emptyBlock();
        int emptyX = emptyBlock / blocks.length;
        int emptyY = emptyBlock % blocks.length;

        // Number of permutations
        int permutations = (int) Math.pow(blocks.length, 2 * blocks.length);

        for (int i = 0; i < permutations; ++i) {
            int direction = (int) (Math.random() * 1000) % 4;
            switch (direction) {
                case 0:
                    if (emptyX > 0) {
                        rotate(emptyX, emptyY, emptyX - 1, emptyY);
                        --emptyX;
                    }
                    break;
                case 1:
                    if (emptyX < blocks.length - 1) {
                        rotate(emptyX, emptyY, emptyX + 1, emptyY);
                        ++emptyX;
                    }
                    break;
                case 2:
                    if (emptyY > 0) {
                        rotate(emptyX, emptyY, emptyX, emptyY - 1);
                        --emptyY;
                    }
                    break;
                case 3:
                    if (emptyY < blocks.length - 1) {
                        rotate(emptyX, emptyY, emptyX, emptyY + 1);
                        ++emptyY;
                    }
                    break;
            }
        }
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < blocks.length; ++i) {
            for (int j = 0; j < blocks[i].length; ++j) {
                str += (blocks[i][j] == 0 ? " " : blocks[i][j]) + "\t";
            }

            if (i < blocks.length - 1) {
                str += "\n";
            }
        }

        return str;
    }

    private int emptyBlock() {
        for (int i = 0; i < blocks.length; ++i) {
            for (int j = 0; j < blocks[i].length; ++j) {
                if (blocks[i][j] == 0) {
                    return i * blocks.length + j;
                }
            }
        }

        return -1;
    }

    private void rotate(int aX, int aY, int bX, int bY) {
        int temp = blocks[aX][aY];
        blocks[aX][aY] = blocks[bX][bY];
        blocks[bX][bY] = temp;
    }

    private static int[][] deepCopy(int[][] board) {
        int[][] copy = new int[board.length][];
        for (int i = 0; i < board.length; ++i) {
            copy[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return copy;
    }
}
