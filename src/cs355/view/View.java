package cs355.view;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Observable;

/**
 * Created by rental on 5/9/17.
 */
public class View implements ViewRefresher {

    private Drawing model;

    public View(){
        model = Drawing.inst();
        model.addObserver(this);
    }

    @Override
    public void refreshView(Graphics2D g2d) {
        for (Shape s : model.getShapes()) {

            Color color = s.getColor();
            g2d.setColor(color);

            if (s instanceof Line) {
                int x1 = (int) ((Line) s).getStart().getX();
                int y1 = (int) ((Line) s).getStart().getY();
                int x2 = (int) ((Line) s).getEnd().getX();
                int y2 = (int) ((Line) s).getEnd().getY();

                g2d.drawLine(x1, y1, x2, y2);
            }
            else if (s instanceof Circle) {
                Point2D.Double center = ((Circle) s).getCenter();

                int radius = (int) ((Circle) s).getRadius();

                Point2D.Double upperLeft = getUpperLeft(center, radius, radius);

                int x = (int) upperLeft.getX();
                int y = (int) upperLeft.getY();

                g2d.fillOval(x, y, 2 * radius, 2 * radius);
            }
            else if (s instanceof Ellipse) {
                Point2D.Double center = ((Ellipse) s).getCenter();
                int width = (int) ((Ellipse) s).getWidth();
                int height = (int) ((Ellipse) s).getHeight();

                Point2D.Double upperLeft = getUpperLeft(center, width, height);

                int x = (int) upperLeft.getX();
                int y = (int) upperLeft.getY();

                g2d.fillOval(x, y, width, height);
            }
            else if (s instanceof Rectangle) {
                int x = (int) ((Rectangle) s).getUpperLeft().getX();
                int y = (int) ((Rectangle) s).getUpperLeft().getY();
                int width = (int) ((Rectangle) s).getWidth();
                int height = (int) ((Rectangle) s).getHeight();

                g2d.fillRect(x, y, width, height);
            }
            else if (s instanceof Square) {
                int x = (int) ((Square) s).getUpperLeft().getX();
                int y = (int) ((Square) s).getUpperLeft().getY();
                int size = (int) ((Square) s).getSize();

                g2d.fillRect(x, y, size, size);
            }
            else if (s instanceof Triangle) {
                int [] x = { (int) ((Triangle) s).getA().getX(), (int) ((Triangle) s).getB().getX(), (int) ((Triangle) s).getC().getX()};
                int [] y = { (int) ((Triangle) s).getA().getY(), (int) ((Triangle) s).getB().getY(), (int) ((Triangle) s).getC().getY()};

                g2d.fillPolygon(x, y, 3);
            }
        }
    }

    private Point2D.Double getUpperLeft(Point2D.Double center, double width, double height) {
        double x = center.getX() - (width / 2);
        double y = center.getY() - (height / 2);
        return new Point2D.Double(x, y);
    }

    @Override
    public void update(Observable o, Object arg) {
        GUIFunctions.refresh();
    }
}
