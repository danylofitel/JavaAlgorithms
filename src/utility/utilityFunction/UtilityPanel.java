package utility.utilityFunction;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Map;

import javax.swing.JPanel;

public class UtilityPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Map<Double, Double> points;
    private final double xSize;
    private final double ySize;
    private final double xScale;
    private final double yScale;

    public UtilityPanel(Map<Double, Double> sorted, int sizeX, int sizeY, double scaleX, double scaleY) {
        points = sorted;
        xSize = sizeX;
        ySize = sizeY;
        xScale = scaleX;
        yScale = scaleY;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        double previousX = 0;
        double previousY = 0;
        for (double key : points.keySet()) {
            if (key != 0) {
                g2D.drawOval((int) previousX - 5, (int) (ySize - previousY - 5), 10, 10);
                g2D.draw(new Line2D.Double(previousX, ySize - previousY, key * xSize / xScale, ySize - points.get(key) * ySize / yScale));
                previousX = key * xSize / xScale;
                previousY = points.get(key) * ySize / yScale;
            }
        }
    }
}
