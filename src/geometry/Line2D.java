package geometry;

public class Line2D {

    private static final int significantDigits = 5;
    private static final int significanceOrder = (int) Math.pow(10, significantDigits);
    private static final double precision = Math.pow(10, -significantDigits);
    private final double slope;
    private final double displacement;

    public Line2D(Point2D p, Point2D q) {
        slope = p.slopeTo(q);

        if (Math.abs(slope) < precision) {
            displacement = p.y();
        } else if (slope == Double.POSITIVE_INFINITY) {
            displacement = p.x();
        } else if (slope == Double.NEGATIVE_INFINITY) {
            displacement = 0.0;
        } else {
            displacement = p.y() - slope * p.x();
        }
    }

    public double slope() {
        return slope;
    }

    public double displacement() {
        return displacement;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (this.getClass() != other.getClass()) {
            return false;
        } else if (this == other) {
            return true;
        } else {
            return (slope == ((Line2D) other).slope
                    || Math.abs(slope - ((Line2D) other).slope) < precision)
                    && (displacement == ((Line2D) other).displacement
                    || Math.abs(displacement - ((Line2D) other).displacement) < precision);
        }
    }

    @Override
    public int hashCode() {
        double slopeValue = Math.round(slope * significanceOrder) / significanceOrder;
        double displacementValue = Math.round(displacement * significanceOrder) / significanceOrder;

        int hash = 7;
        hash = 89 * hash + (int) (Double.doubleToLongBits(slopeValue) ^ (Double.doubleToLongBits(slopeValue) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(displacementValue) ^ (Double.doubleToLongBits(displacementValue) >>> 32));

        return hash;
    }

    @Override
    public String toString() {
        return slope + " * x + " + displacement;
    }
}
