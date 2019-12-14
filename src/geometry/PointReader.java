package geometry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PointReader {

    public static Point2D[] readFromFile(String filename) throws FileNotFoundException, IOException {
        Point2D[] points;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine().trim();

            int size = Integer.valueOf(line);
            points = new Point2D[size];
            int pointsInFile = 0;

            for (int i = 0; i < size; ++i) {
                String[] point = br.readLine().trim().split("\\s+");
                int x = Integer.valueOf(point[0]);
                int y = Integer.valueOf(point[1]);
                points[pointsInFile++] = new Point2D(x, y);
            }
        }

        return points;
    }
}
