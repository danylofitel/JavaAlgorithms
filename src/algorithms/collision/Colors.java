package algorithms.collision;

import java.awt.Color;

public class Colors {

    public static Color randomBaseColor() {
        final int colorsAvailable = 6;

        int randomIndex = (int) (10000.0 * Math.random() % colorsAvailable);
        Color result;
        switch (randomIndex) {
            case 0:
                result = Color.red;
                break;
            case 1:
                result = Color.green;
                break;
            case 2:
                result = Color.blue;
                break;
            case 3:
                result = Color.cyan;
                break;
            case 4:
                result = Color.yellow;
                break;
            case 5:
                result = Color.magenta;
                break;
            default:
                result = Color.black;
                break;
        }

        return result;
    }

    public static Color randomColor() {
        float r = (float) Math.random();
        float g = (float) Math.random();
        float b = (float) Math.random();
        return new Color(r, g, b);
    }

    public static Color speedColor(double velocity, double maxVelocity) {
        final float minSat = 0f;
        final float maxSat = 0.8f;
        float normalizedVelocity = Math.abs(maxSat - (float) (Math.min(1, velocity / maxVelocity)) * (maxSat - minSat));
        return Color.getHSBColor(normalizedVelocity, 1f, 1f);
    }
}
