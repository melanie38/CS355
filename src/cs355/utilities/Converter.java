package cs355.utilities;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.security.PrivateKey;
import java.util.ArrayList;

/**
 * Created by rental on 5/10/17.
 */
public class Converter {

    private AffineTransform modif = new AffineTransform();

    public static ArrayList<Point2D.Double> worldToObject(ArrayList<Point2D.Double> points, Point2D.Double center) {
        ArrayList<Point2D.Double> results = new ArrayList<>();

        //add the rotation
        AffineTransform wto = new AffineTransform();
        wto.translate(center.getX(), center.getY());

        for (Point2D.Double point : points) {
            Point2D.Double newPoint = new Point2D.Double();
            wto.transform(point, newPoint);
            results.add(newPoint);
        }

        return results;
    }

    public static Point2D.Double worldToObject(Point2D.Double point, Point2D.Double center) {
        Point2D.Double result = new Point2D.Double();

        //add the rotation
        AffineTransform wto = new AffineTransform();
        wto.translate(-center.getX(), -center.getY());

        wto.transform(point, result);

        return result;
    }

    public static ArrayList<Point2D.Double> objectToWorld (ArrayList<Point2D.Double> points, Point2D.Double center) {
        ArrayList<Point2D.Double> results = new ArrayList<>();

        AffineTransform wto = new AffineTransform();
        wto.translate(-center.getX(), -center.getY());

        for (Point2D.Double point : points) {
            Point2D.Double newPoint = new Point2D.Double();
            wto.transform(point, newPoint);
            results.add(newPoint);
        }

        return results;
    }
}
