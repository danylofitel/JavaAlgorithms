package algorithms.collision;

import lib.StdDraw;

public class BouncingBalls {

    private static final double scale = 1.0;
    private static final double drawInterval = 0.001;
    private static final int drawTime = 10;

    public static void main(String[] args) {
        final int n = 20;
        final int N = n * n;
        final double radius = 0.01;
        final double velocity = 6.0;
        final double distance = scale / n;

        Ball[] balls = new Ball[N];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; ++j) {
                double x = i * distance;
                if (x < radius) {
                    x += radius;
                } else if (x > scale - radius) {
                    x -= radius;
                }

                double y = j * distance;
                if (y < radius) {
                    y += radius;
                } else if (y > scale - radius) {
                    y -= radius;
                }

                balls[i * n + j] = new Ball(
                        x,
                        y,
                        velocity * (Math.random() - 0.5),
                        velocity * (Math.random() - 0.5),
                        radius,
                        Colors.randomColor());
            }
        }

        StdDraw.setXscale(0, scale);
        StdDraw.setYscale(0, scale);

        while (true) {
            StdDraw.clear();
            for (int i = 0; i < N; i++) {
                balls[i].move(drawInterval);
                balls[i].draw();
            }
            StdDraw.show(drawTime);
        }
    }
}
