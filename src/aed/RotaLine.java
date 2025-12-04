package aed;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;


public class RotaLine extends MapPolygonImpl {

    public RotaLine(List<Coordinate> points) {
        super(points);
    }

    @Override
    public void paint(Graphics g, List<Point> points) {
        Graphics2D g2 = (Graphics2D) g.create();
        
        g2.setColor(getColor());
        g2.setStroke(getStroke());

        int[] x = new int[points.size()];
        int[] y = new int[points.size()];
        
        for (int i = 0; i < points.size(); i++) {
            x[i] = points.get(i).x;
            y[i] = points.get(i).y;
        }

        // Usamos drawPolyline (linha aberta) 
        if (points.size() > 0) {
            g2.drawPolyline(x, y, points.size());
        }
        
        g2.dispose();
    }
}