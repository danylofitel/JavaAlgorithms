package utility.utilityFunction;

import java.util.Map;

import javax.swing.JFrame;

public class UtilityFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int sizeX = 1000;
    private static final int sizeY = 500;

    public UtilityFrame(Map<Double, Double> sorted, double scaleX, double scaleY) {
        setTitle("Utility Function Plot");
        setSize(sizeX, sizeY);
        add(new UtilityPanel(sorted, sizeX, sizeY, scaleX, scaleY));
    }
}
