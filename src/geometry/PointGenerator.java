package geometry;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PointGenerator {

    private static final int fieldSize = 32768;

    public static void generateCircle(int size) throws IOException {
        int radius = fieldSize / 2;
        int radius2 = radius * radius;
        int step = fieldSize / size;
        FileWriter fstream = new FileWriter("circle_" + size + ".txt");
        try (BufferedWriter out = new BufferedWriter(fstream)) {
            out.write(String.valueOf((radius / step + 1) * 4) + "\n");
            for (int x = 0; x <= radius; x += step) {
                out.write((x + radius) + "   " + String.valueOf((int) (Math.sqrt(radius2 - x * x)) + radius) + "\n");
                out.write((-x + radius) + "   " + String.valueOf((int) (Math.sqrt(radius2 - x * x)) + radius) + "\n");
                out.write((x + radius) + "   " + String.valueOf((int) (-Math.sqrt(radius2 - x * x)) + radius) + "\n");
                out.write((-x + radius) + "   " + String.valueOf((int) (-Math.sqrt(radius2 - x * x)) + radius) + "\n");
            }
        }
    }

    public static void generateDiamond(int size) throws IOException {
        int step = fieldSize / size;
        FileWriter fstream = new FileWriter("diamond_" + size + ".txt");
        try (BufferedWriter out = new BufferedWriter(fstream)) {
            out.write(String.valueOf(size * 2) + "\n");
            int x = fieldSize / 2;
            int y = 0;
            for (; x < fieldSize; x += step, y += step) {
                out.write(x + " " + y + "\n");
            }
            for (; y < fieldSize; x -= step, y += step) {
                out.write(x + " " + y + "\n");
            }
            for (; x > 0; x -= step, y -= step) {
                out.write(x + " " + y + "\n");
            }
            for (; y > 0; x += step, y -= step) {
                out.write(x + " " + y + "\n");
            }
        }
    }

    public static void generateGrid(int size) throws IOException {
        FileWriter fstream = new FileWriter(size + "x" + size + ".txt");
        try (BufferedWriter out = new BufferedWriter(fstream)) {
            out.write(String.valueOf(size * size) + "\n");
            for (int i = 0; i < size; ++i) {
                String point = String.valueOf(i * fieldSize / size) + " ";
                for (int j = 0; j < size; ++j) {
                    out.write(point + String.valueOf(j * fieldSize / size) + "\n");
                }
            }
        }
    }

    public static void generateSquare(int size) throws IOException {
        int step = fieldSize / size;
        FileWriter fstream = new FileWriter("square_" + size + ".txt");
        try (BufferedWriter out = new BufferedWriter(fstream)) {
            out.write(String.valueOf(size * 4) + "\n");
            int x = 0;
            int y = 0;
            for (; x < fieldSize; x += step) {
                out.write(x + " " + y + "\n");
            }
            for (; y < fieldSize; y += step) {
                out.write(x + " " + y + "\n");
            }
            for (; x > 0; x -= step) {
                out.write(x + " " + y + "\n");
            }
            for (; y > 0; y -= step) {
                out.write(x + " " + y + "\n");
            }
        }
    }

    public static void generateTriangle(int size) throws IOException {
        int step = fieldSize / size;
        int halfFieldSize = fieldSize / 2;
        FileWriter fstream = new FileWriter("triangle_" + size + ".txt");
        try (BufferedWriter out = new BufferedWriter(fstream)) {
            out.write(String.valueOf(size * 3 + 1) + "\n");
            int x = halfFieldSize;
            int y = fieldSize;
            for (; y > 0; x += step / 2, y -= step) {
                out.write(x + " " + y + "\n");
                out.write((x - 2 * (x - fieldSize / 2)) + " " + y + "\n");
            }
            for (int i = 0; i <= fieldSize; i += step) {
                out.write(i + " " + 0 + "\n");
            }
        }
    }
}
