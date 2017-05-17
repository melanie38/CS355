package cs355.view;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.utilities.Converter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Observable;

/**
 * Created by rental on 5/9/17.
 */
public class View implements ViewRefresher {

    private Drawing model;
    private static int indexSelected = -1;

    public View(){
        model = Drawing.inst();
        model.addObserver(this);
    }

    public static void setIndexSelected(int indexSelected) {
        View.indexSelected = indexSelected;
    }

    @Override
    public void refreshView(Graphics2D g2d) {
        for (Shape s : model.getShapes()) {

            Color color = s.getColor();
            g2d.setColor(color);
            Point2D.Double center = s.getCenter();

            AffineTransform objToWorld = new AffineTransform();
            objToWorld.translate((int) center.getX(), (int) center.getY());
            objToWorld.rotate((int) s.getRotation());

            g2d.setTransform(objToWorld);

            if (s instanceof Line) {

                int x2 = (int) ((Line) s).getEnd().getX();
                int y2 = (int) ((Line) s).getEnd().getY();

                g2d.drawLine(0, 0, x2, y2);
            }
            else if (s instanceof Circle) {

                int radius = (int) ((Circle) s).getRadius();

                g2d.fillOval(-radius, -radius, 2 * radius, 2 * radius);

            }
            else if (s instanceof Ellipse) {

                int width = (int) ((Ellipse) s).getWidth();
                int height = (int) ((Ellipse) s).getHeight();

                Point2D.Double upperLeft = getUpperLeft(center, width, height);

                int x = (int) upperLeft.getX();
                int y = (int) upperLeft.getY();

                g2d.fillOval((int)-width/2, (int)-height/2, width, height);

            }
            else if (s instanceof Rectangle) {

                int width = (int) ((Rectangle) s).getWidth();
                int height = (int) ((Rectangle) s).getHeight();

                g2d.fillRect(-width/2, -height/2, width, height);

            }
            else if (s instanceof Square) {
                int size = (int) ((Square) s).getSize();

                g2d.fillRect((int) (-size/2), (int) (-size/2), (int) size, (int) size);

            }
            else if (s instanceof Triangle) {

                Point2D.Double A = new Point2D.Double();
                Point2D.Double B = new Point2D.Double();
                Point2D.Double C = new Point2D.Double();

                // format world coordinates to draw
                int [] x = { (int) ((Triangle) s).getA().getX(), (int) ((Triangle) s).getB().getX(), (int) ((Triangle) s).getC().getX()};
                int [] y = { (int) ((Triangle) s).getA().getY(), (int) ((Triangle) s).getB().getY(), (int) ((Triangle) s).getC().getY()};

                g2d.fillPolygon(x, y, 3);

            }
        }

        // draw selection boxes
        if (indexSelected != -1) {
            Shape s = model.getShape(indexSelected);

            Point2D.Double center = s.getCenter();

            AffineTransform objToWorld = new AffineTransform();
            objToWorld.translate((int) center.getX(), (int) center.getY());
            objToWorld.rotate((int) s.getRotation());

            g2d.setTransform(objToWorld);
            g2d.setColor(Color.BLUE);

            if (s instanceof Line) {
                    int x2 = (int) ((Line) s).getEnd().getX();
                    int y2 = (int) ((Line) s).getEnd().getY();

                    g2d.drawOval(-4, -4, 8, 8);
                    g2d.drawOval(x2 - 4, y2 - 4, 8, 8);


            } else if (s instanceof Circle) {
                    int radius = (int) ((Circle) s).getRadius();
                    g2d.drawRect(-radius, -radius, 2 * radius, 2 * radius);
                    g2d.drawOval(-4, -radius - 20, 8, 8);

            } else if (s instanceof Ellipse) {
                    int width = (int) ((Ellipse) s).getWidth();
                    int height = (int) ((Ellipse) s).getHeight();
                    g2d.drawRect(-width / 2, -height / 2, width, height);
                    g2d.drawOval(-4, -height / 2 - 20, 8, 8);

            } else if (s instanceof Rectangle) {
                    int width = (int) ((Rectangle) s).getWidth();
                    int height = (int) ((Rectangle) s).getHeight();
                    g2d.drawRect(-width / 2, -height / 2, width, height);
                    g2d.drawOval(-4, -height / 2 - 20, 8, 8);

            } else if (s instanceof Square) {
                    int size = (int) ((Square) s).getSize();
                    g2d.drawRect(-size / 2, -size / 2, size, size);
                    g2d.drawOval(-4, -size / 2 - 20, 8, 8);

            } else if (s instanceof Triangle) {

                    // format world coordinates to draw
                    int[] x = {(int) ((Triangle) s).getA().getX(), (int) ((Triangle) s).getB().getX(), (int) ((Triangle) s).getC().getX()};
                    int[] y = {(int) ((Triangle) s).getA().getY(), (int) ((Triangle) s).getB().getY(), (int) ((Triangle) s).getC().getY()};
                    g2d.drawPolygon(x, y, 3);

                    int dist = getLongestDistance(((Triangle) s).getA(), ((Triangle) s).getB(), ((Triangle) s).getC());
                    g2d.drawOval(-4, -dist-20, 8, 8);

            }
        }

    }

    private Point2D.Double getUpperLeft(Point2D.Double center, double width, double height) {
        double x = center.getX() - (width / 2);
        double y = center.getY() - (height / 2);
        return new Point2D.Double(x, y);
    }

    private int getLongestDistance(Point2D.Double A, Point2D.Double B, Point2D.Double C) {
        double a = Math.sqrt(Math.pow(A.getX(), 2) + Math.pow(A.getY(), 2));
        double b = Math.sqrt(Math.pow(B.getX(), 2) + Math.pow(B.getY(), 2));
        double c = Math.sqrt(Math.pow(C.getX(), 2) + Math.pow(C.getY(), 2));

        return (int) Math.max(Math.max(a, b), c);
    }

    @Override
    public void update(Observable o, Object arg) {
        GUIFunctions.refresh();
    }
}
