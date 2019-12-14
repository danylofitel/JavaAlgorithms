package utility.utilityGame;

import java.io.IOException;
import java.util.Scanner;

public class UtilityGameTest {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Left bound");
        System.out.print("->");
        double a = scanner.nextDouble();

        double b;

        do {
            System.out.println("Left bound");
            System.out.print("->");
            b = scanner.nextDouble();
        } while (b < a);

        UtilityGame ug = new UtilityGame(a, b);

        while (true) {
            System.out.println("Please type x");
            System.out.print("->");
            double x = scanner.nextDouble();
            System.out.println("Revenue = " + ug.play(x));
        }
    }
}
