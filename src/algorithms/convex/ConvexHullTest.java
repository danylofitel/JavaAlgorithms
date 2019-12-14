package algorithms.convex;

import geometry.PointReader;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import lib.StdDraw;

public class ConvexHullTest {

    private static void runConvexTest(
            String filename,
            boolean includePointsBetweenEdjes,
            Color pointColor,
            Color convexHullColor) throws FileNotFoundException, IOException {
        ConvexHull convex = new ConvexHull();
        convex.setPointsColor(pointColor);
        convex.setConvexHullColor(convexHullColor);
        convex.build(PointReader.readFromFile(filename), includePointsBetweenEdjes);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        runConvexTest("diamond_1024.txt", true, Color.blue, Color.red);
    }
}
