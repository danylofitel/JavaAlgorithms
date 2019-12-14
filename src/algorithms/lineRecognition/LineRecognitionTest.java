package algorithms.lineRecognition;

import geometry.PointReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import lib.StdDraw;

public class LineRecognitionTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        double precision = 0.0000001;

        StdDraw.clear();
        LineRecognition bruteSlow = new BruteSlow(precision);
        bruteSlow.processData(PointReader.readFromFile("4x4.txt"), 4);

        StdDraw.clear();
        LineRecognition brute = new Brute(precision);
        brute.processData(PointReader.readFromFile("rs1423.txt"), 4);

        StdDraw.clear();
        Brute4 brute4 = new Brute4(precision);
        brute4.processData(PointReader.readFromFile("rs1423.txt"));

        StdDraw.clear();
        LineRecognition fast = new Fast(precision);
        fast.processData(PointReader.readFromFile("rs1423.txt"), 4);

        StdDraw.clear();
        LineRecognition fastest = new Smart();
        fastest.processData(PointReader.readFromFile("rs1423.txt"), 4);
    }
}
