import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// Modified package from stackoverflow.com

public class DrawGraph extends JPanel {
    private static final int PREF_W = 800;
    private static final int PREF_H = 650;
    private static final int BORDER_GAP = 20;
    private static final Color GRAPH_COLOR = Color.green;
    private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private static final Color GRAPH_GRID_COLOR = new Color(186, 186, 186, 180);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private static final int GRAPH_POINT_WIDTH = 6;
    private double[] points;
    private int fieldDim;
    final static float dash1[] = {5.0f};
    final static BasicStroke dashed =
            new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, dash1, 0.0f);

    public DrawGraph(double[] points, int fieldDim) {
        this.points = points;
        this.fieldDim = fieldDim;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (fieldDim + 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (fieldDim + 1);

        List<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < points.length; i++) {
            int x1 = (int) (((int)points[i]) * xScale + BORDER_GAP);
            int y1 = (int) ((fieldDim + 1 - ((int)((points[i]-((int)points[i]))*100))) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }
        graphPoints.add(graphPoints.get(0));

        // create x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        g2.setStroke(dashed);
        g2.setPaint(GRAPH_GRID_COLOR);
        // create hatch marks for y axis.
        for (int i = 0; i < fieldDim + 1; i++) {
            int x0 = BORDER_GAP;
            int x1 = PREF_W - BORDER_GAP;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / (fieldDim + 1) + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < fieldDim + 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (fieldDim + 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = BORDER_GAP;
            g2.drawLine(x0, y0, x1, y1);
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    public static void createAndShowGui(double[] points, int fieldDim) {
        DrawGraph mainPanel = new DrawGraph(points, fieldDim);
        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}