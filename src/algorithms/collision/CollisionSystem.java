package algorithms.collision;

import collections.MinPriorityQueue;
import collections.Queue;
import java.util.Arrays;
import lib.StdDraw;

public class CollisionSystem {

    // Time to redraw
    private final static int drawInterval = 10;
    // Time between redraw events
    private final static double redrawTime = 0.0005;
    // Priority queue
    private Queue<Event> pq;
    // Simulation clock time
    private double t;
    // Simulation time limit
    private double limit;
    // Array of particles
    private final Particle[] particles;
    // Greatest initial velocity value
    private double velocityLimit = 0.0;

    public CollisionSystem(Particle[] particles) {
        this.pq = null;
        this.t = 0.0;
        this.limit = 0.0;
        this.particles = Arrays.copyOf(particles, particles.length);
        this.velocityLimit = 0.0;
    }

    private void predict(Particle a) {
        if (a == null) {
            return;
        }

        for (int i = 0; i < particles.length; ++i) {
            double dt = a.timeToHit(particles[i]);
            if (t + dt < limit) {
                pq.pushBack(new Event(t + dt, a, particles[i]));
            }
        }

        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtX < limit) {
            pq.pushBack(new Event(t + dtX, a, null));
        }
        if (t + dtY < limit) {
            pq.pushBack(new Event(t + dtY, null, a));
        }
    }

    private void redraw(boolean changeColor) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; ++i) {
            if (changeColor) {
                particles[i].setColor(Colors.speedColor(particles[i].velocity(), velocityLimit));
            }
            particles[i].draw();
        }
        StdDraw.show(drawInterval);
        if (t < limit) {
            pq.pushBack(new Event(t + redrawTime, null, null));
        }
    }

    public void simulate(double timeLimit, boolean velocityImpactsColor) {
        limit = timeLimit;
        pq = new MinPriorityQueue<>();

        for (int i = 0; i < particles.length; ++i) {
            double velocity = particles[i].velocity();

            if (velocity > velocityLimit) {
                velocityLimit = velocity;
            }

            predict(particles[i]);
        }

        pq.pushBack(new Event(0, null, null));

        while (!pq.isEmpty()) {
            Event event = pq.popFront();
            if (!event.isValid()) {
                continue;
            }

            Particle a = event.a;
            Particle b = event.b;

            double dt = event.time - t;
            for (int i = 0; i < particles.length; ++i) {
                particles[i].move(dt);
            }

            t = event.time;

            if (a != null && b != null) {
                a.bounceOff(b);
            } else if (a != null && b == null) {
                a.bounceOffVerticalWall();
            } else if (a == null && b != null) {
                b.bounceOffHorizontalWall();
            } else if (a == null && b == null) {
                redraw(velocityImpactsColor);
            }

            predict(a);
            predict(b);
        }
    }

    private class Event implements Comparable<Event> {

        // Time of event
        private final double time;
        // Particles involved in event
        private final Particle a, b;
        // Collision counts for a and b
        private final int countA, countB;

        public Event(double time, Particle a, Particle b) {
            this.time = time;
            this.a = a;
            this.b = b;

            this.countA = a != null ? a.collisions() : -1;
            this.countB = b != null ? b.collisions() : -1;
        }

        @Override
        public int compareTo(Event that) {
            return Double.compare(this.time, that.time);
        }

        public boolean isValid() {
            if (a != null && a.collisions() != countA) {
                return false;
            } else if (b != null && b.collisions() != countB) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static void main(String[] args) {
        final int n = 10;
        final int N = n * n;
        final double radius = 0.025;
        final double velocity = 25.0;
        final double mass = 10.0;
        final double scale = 1.0;
        final double distance = scale / n;

        Particle[] particles = new Particle[N];

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

                double significance = Math.random() + 0.1;
                particles[i * n + j] = new Particle(
                        x,
                        y,
                        velocity * (Math.random() - 0.5),
                        velocity * (Math.random() - 0.5),
                        radius * Math.sqrt(significance),
                        mass * significance,
                        Colors.randomColor());
            }
        }

        StdDraw.setXscale(0, scale);
        StdDraw.setYscale(0, scale);

        CollisionSystem cs = new CollisionSystem(particles);
        cs.simulate(Double.POSITIVE_INFINITY, true);
    }
}
