package algorithms.collision;

import java.awt.Color;
import lib.StdDraw;

public class Particle {

    // Infinity value
    private final static double INFINITY = Double.POSITIVE_INFINITY;
    // Drawing scale
    private static double scaleX = 1.0, scaleY = 1.0;
    // Position
    private double rx, ry;
    // Velocity
    private double vx, vy;
    // Radius
    private final double radius;
    // Mass
    private final double mass;
    // Number of collisions
    private int collisions;
    // Color
    private Color color;

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

    public Particle(double posX, double posY, double velX, double velY, double radius, double mass, Color color) {
        this.rx = posX;
        this.ry = posY;
        this.vx = velX;
        this.vy = velY;
        this.radius = radius;
        this.mass = mass;
        this.color = color;
    }

    public Particle(double posX, double posY, double velX, double velY, double radius, double mass) {
        this(posX, posY, velX, velY, radius, mass, Color.black);
    }

    public double x() {
        return rx;
    }

    public double y() {
        return ry;
    }

    public double radius() {
        return radius;
    }

    public double mass() {
        return mass;
    }

    public double velocity() {
        return Math.sqrt(vx * vx + vy * vy);
    }

    public int collisions() {
        return collisions;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    public void draw() {
        Color previous = StdDraw.getPenColor();
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
        StdDraw.setPenColor(previous);
    }

    public double timeToHit(Particle that) {
        if (this == that) {
            return INFINITY;
        }

        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;

        if (dvdr > 0) {
            return INFINITY;
        }

        double dvdv = dvx * dvx + dvy * dvy;
        double drdr = dx * dx + dy * dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);

        if (d < 0) {
            return INFINITY;
        }

        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitVerticalWall() {
        if (vx == 0) {
            return INFINITY;
        } else {
            return vx > 0 ? (scaleX - radius - rx) / vx : (radius - rx) / vx;
        }
    }

    public double timeToHitHorizontalWall() {
        if (vy == 0) {
            return INFINITY;
        } else {
            return vy > 0 ? (scaleY - radius - ry) / vy : (radius - ry) / vy;
        }
    }

    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx;
        double dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;

        double dist = this.radius + that.radius;

        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;

        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        that.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;

        ++this.collisions;
        ++that.collisions;
    }

    public void bounceOffVerticalWall() {
        vx = -vx;
        ++collisions;
    }

    public void bounceOffHorizontalWall() {
        vy = -vy;
        ++collisions;
    }

    public double kineticEnergy() {
        return 0.5 * mass * (vx * vx + vy * vy);
    }
}
