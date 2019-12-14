package algorithms.collision;

import java.awt.Color;
import lib.StdDraw;

public class Ball {

    // Field size
    private static double scaleX = 1.0;
    private static double scaleY = 1.0;
    // Position
    private double rx, ry;
    // Velocity
    private double vx, vy;
    // Radius
    private final double radius;
    // Color
    private final Color color;

    public static double getScaleX() {
        return scaleX;
    }

    public static void setScaleX(double scaleValue) {
        if (scaleValue <= 0.0) {
            throw new IllegalArgumentException("Invalid scale value " + scaleValue);
        }
        scaleX = scaleValue;
        StdDraw.setXscale(0, scaleX);
    }

    public static double getScaleY() {
        return scaleY;
    }

    public static void setScaleY(double scaleValue) {
        if (scaleValue <= 0.0) {
            throw new IllegalArgumentException("Invalid scale value " + scaleValue);
        }
        scaleY = scaleValue;
        StdDraw.setYscale(0, scaleY);
    }

    public Ball(double posX, double posY, double velX, double velY, double radius, Color color) {
        this.rx = posX;
        this.ry = posY;
        this.vx = velX;
        this.vy = velY;
        this.radius = radius;
        this.color = color;
    }

    public Ball(double posX, double posY, double velX, double velY, double radius) {
        this(posX, posY, velX, velY, radius, Color.black);
    }

    public void move(double dt) {
        if ((rx + vx * dt < radius) || (rx + vx * dt > scaleX - radius)) {
            vx = -vx;
        }

        if ((ry + vy * dt < radius) || (ry + vy * dt > scaleY - radius)) {
            vy = -vy;
        }

        rx = rx + vx * dt;
        ry = ry + vy * dt;
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }
}
