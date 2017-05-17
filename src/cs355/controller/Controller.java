package cs355.controller;

import a.e.D;
import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.drawing.Rectangle;
import cs355.model.drawing.Shape;
import cs355.view.View;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * Created by Melanie on 04/05/2017.
 */
public class Controller implements CS355Controller {

    private Color color = new Color(255,255,255);
    private String tool;
    private Point2D.Double start;
    private Point2D.Double end;
    private int index;
    private int selectedIndex;
    private ArrayList<Point2D.Double> triangle = new ArrayList<>();
    private boolean handleSelected = false;

    @Override
    public void colorButtonHit(Color c) {
        color = c;
        GUIFunctions.changeSelectedColor(c);

        if (selectedIndex != -1) {
            Drawing.inst().getShape(selectedIndex).setColor(c);
        }

    }

    @Override
    public void lineButtonHit() {
        GUIFunctions.printf("draw line");
        tool = "line";
    }

    @Override
    public void squareButtonHit() {
        tool = "square";
    }

    @Override
    public void rectangleButtonHit() {
        tool = "rectangle";
    }

    @Override
    public void circleButtonHit() {
        GUIFunctions.printf("draw circle");
        tool = "circle";
    }

    @Override
    public void ellipseButtonHit() {
        tool = "ellipse";
    }

    @Override
    public void triangleButtonHit() {
        tool = "triangle";
    }

    @Override
    public void selectButtonHit() {
        tool = "select";
    }

    @Override
    public void zoomInButtonHit() {

    }

    @Override
    public void zoomOutButtonHit() {

    }

    @Override
    public void hScrollbarChanged(int value) {

    }

    @Override
    public void vScrollbarChanged(int value) {

    }

    @Override
    public void openScene(File file) {

    }

    @Override
    public void toggle3DModelDisplay() {

    }

    @Override
    public void keyPressed(Iterator<Integer> iterator) {

    }

    @Override
    public void openImage(File file) {

    }

    @Override
    public void saveImage(File file) {

    }

    @Override
    public void toggleBackgroundDisplay() {

    }

    @Override
    public void saveDrawing(File file) {
        Drawing.inst().save(file);
    }

    @Override
    public void openDrawing(File file) {
        Drawing.inst().open(file);
    }

    @Override
    public void doDeleteShape() {
        if (selectedIndex != -1) {
            Drawing.inst().deleteShape(selectedIndex);
            selectedIndex = -1;
            View.setIndexSelected(selectedIndex);
            Drawing.inst().update();
        }
        else {
            GUIFunctions.printf("no shape selected");
        }
    }

    @Override
    public void doEdgeDetection() {

    }

    @Override
    public void doSharpen() {

    }

    @Override
    public void doMedianBlur() {

    }

    @Override
    public void doUniformBlur() {

    }

    @Override
    public void doGrayscale() {

    }

    @Override
    public void doChangeContrast(int contrastAmountNum) {

    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum) {

    }

    @Override
    public void doMoveForward() {
        if (selectedIndex != -1) {
            if (selectedIndex < Drawing.inst().getShapes().size() - 1) {
                Drawing.inst().moveForward(selectedIndex);
                selectedIndex++;
                View.setIndexSelected(selectedIndex);
                Drawing.inst().update();
                GUIFunctions.printf("position: %d / %d", selectedIndex + 1, Drawing.inst().getShapes().size());
            }
            else {
                GUIFunctions.printf("already in front position");
            }
        }
        else {
            GUIFunctions.printf("no shape selected");
        }
    }

    @Override
    public void doMoveBackward() {
        if (selectedIndex != -1) {
            if (selectedIndex > 0) {
                Drawing.inst().moveBackward(selectedIndex);
                selectedIndex--;
                View.setIndexSelected(selectedIndex);
                Drawing.inst().update();
                GUIFunctions.printf("position: %d / %d", selectedIndex + 1, Drawing.inst().getShapes().size());
            }
            else {
                GUIFunctions.printf("already in back position");
            }
        }
        else {
            GUIFunctions.printf("no shape selected");
        }
    }

    @Override
    public void doSendToFront() {
        if (selectedIndex != -1) {
            if (selectedIndex < Drawing.inst().getShapes().size() - 1) {
                Drawing.inst().moveToFront(selectedIndex);
                selectedIndex = Drawing.inst().getShapes().size() - 1;
                View.setIndexSelected(selectedIndex);
                Drawing.inst().update();
                GUIFunctions.printf("position: %d / %d", selectedIndex + 1, Drawing.inst().getShapes().size());
            }
            else {
                GUIFunctions.printf("already in front position");
            }
        }
        else {
            GUIFunctions.printf("no shape selected");
        }
    }

    @Override
    public void doSendtoBack() {
        if (selectedIndex != -1) {
            if (selectedIndex > 0) {
                Drawing.inst().movetoBack(selectedIndex);
                selectedIndex = 0;
                View.setIndexSelected(selectedIndex);
                Drawing.inst().update();
                GUIFunctions.printf("position: %d / %d", selectedIndex + 1, Drawing.inst().getShapes().size());
            }
            else {
                GUIFunctions.printf("already in back position");
            }
        }
        else {
            GUIFunctions.printf("no shape selected");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        GUIFunctions.printf("inside mouse clicked");

        if (tool.equals("triangle")) {
            triangle.add(new Point2D.Double(e.getPoint().x, e.getPoint().y));
            if (triangle.size() == 3) {

                ArrayList<Point2D.Double> localPoints = new ArrayList<>();

                Point2D.Double center = average(triangle);

                AffineTransform worldToObj = new AffineTransform();
                worldToObj.translate(-center.getX(), -center.getY());

                for (Point2D.Double point : triangle) {
                    Point2D.Double toStore = new Point2D.Double();
                    worldToObj.transform(point, toStore);
                    localPoints.add(toStore);
                }

                Triangle t = new Triangle(color, center, localPoints.get(0), localPoints.get(1), localPoints.get(2));
                Drawing.inst().addShape(t);
                Drawing.inst().update();

                triangle.clear();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        GUIFunctions.printf("inside mousePressed");

        if (!tool.equals("select")) {
            selectedIndex = -1;
            View.setIndexSelected(selectedIndex);
            GUIFunctions.refresh();
        }

        // Create a new shape
        start = new Point2D.Double(e.getPoint().x, e.getPoint().y);

        switch(tool) {
            case "line":
                Line l = new Line(color, start, new Point2D.Double(0,0));
                index = Drawing.inst().addShape(l);
                Drawing.inst().update();
                break;
            case "circle":
                Circle c = new Circle(color, start, 0);
                index = Drawing.inst().addShape(c);
                Drawing.inst().update();
                break;
            case "ellipse":
                Ellipse el = new Ellipse(color, start, 0, 0);
                index = Drawing.inst().addShape(el);
                Drawing.inst().update();
                break;
            case "rectangle":
                Rectangle r = new Rectangle(color, start, 0, 0);
                index = Drawing.inst().addShape(r);
                Drawing.inst().update();
                break;
            case "square":
                Square s = new Square(color, start, 0);
                index = Drawing.inst().addShape(s);
                Drawing.inst().update();
                break;
            case "select":
                //selectedIndex = -1;

                Point2D.Double selectPoint = new Point2D.Double(e.getPoint().getX(), e.getY());
                Point2D.Double transPoint = new Point2D.Double();
                List<Shape> listOfShape = Drawing.inst().getShapes();

                for (int i = listOfShape.size() - 1; i >= 0; i--) {
                    Shape shape = listOfShape.get(i);

                    AffineTransform worldToObj = new AffineTransform();
                    worldToObj.translate((int) -shape.getCenter().getX(), (int) -shape.getCenter().getY());
                    worldToObj.transform(selectPoint, transPoint);

                    if (i == selectedIndex) {
                        if (shape.isOnHandle(transPoint, 4)) {
                            GUIFunctions.printf("handle selected");
                            handleSelected = true;
                            break;
                        }
                    }

                    if (shape.pointInShape(transPoint, 4)) {
                        selectedIndex = i;
                        break;
                    }
                    if (i == 0){
                        selectedIndex = -1;
                    }
                }
                View.setIndexSelected(selectedIndex);
                GUIFunctions.refresh();

                if (selectedIndex != -1) {
                    color = Drawing.inst().getShape(selectedIndex).getColor();
                    GUIFunctions.changeSelectedColor(color);

                    View.setIndexSelected(selectedIndex);
                    GUIFunctions.refresh();
                }
                break;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        handleSelected = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (tool.equals("triangle")) {
            triangle.clear();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        GUIFunctions.printf("inside mouseDragged");
        // Update the end coordinate of the shape
        end = new Point2D.Double(e.getPoint().x, e.getPoint().y);

        double height = Math.abs(start.getY() - end.getY()); // y
        double width = Math.abs(start.getX() - end.getX()); // x
        double size = size(start, end);
        Point2D.Double upperLeft = getUpperLeft(start, end);

        switch (tool) {
            case "line":
                Line l = (Line) Drawing.inst().getShape(index);
                Point2D.Double endTrans = new Point2D.Double();
                AffineTransform endOfLine = new AffineTransform();
                endOfLine.translate(-l.getCenter().getX(), -l.getCenter().getY());
                endOfLine.transform(end, endTrans);
                l.setEnd(endTrans);
                Drawing.inst().update();
                break;
            case "circle":
                upperLeft = getUpperLeftSquare(start, end, size);
                Circle c = (Circle) Drawing.inst().getShape(index);
                c.setCenter(calculateCenter(upperLeft, size, size));
                c.setRadius(size(start, end) / 2);
                Drawing.inst().update();
                break;
            case "ellipse":
                Ellipse el = (Ellipse) Drawing.inst().getShape(index);
                el.setHeight(height);
                el.setWidth(width);
                el.setCenter(calculateCenter(upperLeft, width, height));
                Drawing.inst().update();
                break;
            case "rectangle":
                Rectangle r = (Rectangle) Drawing.inst().getShape(index);
                r.setHeight(height);
                r.setWidth(width);
                r.setCenter(calculateCenter(upperLeft, width, height));
                Drawing.inst().update();
                break;
            case "square":
                upperLeft = getUpperLeftSquare(start, end, size);
                Square s = (Square) Drawing.inst().getShape(index);
                s.setSize(size);
                s.setCenter(calculateCenter(upperLeft, size, size));
                Drawing.inst().update();
                break;
            case "select":
                if (selectedIndex != -1) {
                    Shape shape = Drawing.inst().getShape(selectedIndex);

                    if (handleSelected) {
                        Point2D.Double pt = new Point2D.Double();
                        AffineTransform af = new AffineTransform();
                        af.translate(-shape.getCenter().getX(), -shape.getCenter().getY());
                        af.transform(end, pt);

                        shape.setRotation(calculateRotation(shape, pt));
                        Drawing.inst().update();
                    }
                    else {
                        Point2D.Double offset = calculateOffset(end, start);
                        shape.setCenter(new Point2D.Double(shape.getCenter().getX() + offset.getX(), shape.getCenter().getY() + offset.getY()));
                        start = end;
                    }
                    Drawing.inst().update();
                }
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private double distance(Point2D.Double start, Point2D.Double end) {
        double result;
        double inter;

        inter = Math.pow((end.getX() - start.getX()), 2) + Math.pow((end.getY() - start.getY()), 2);

        result = Math.sqrt(inter);

        return result;
    }

    private double size(Point2D.Double start, Point2D.Double end) {
        double xDiff;
        double yDiff;

        xDiff = Math.abs(start.getX() - end.getX());
        yDiff = Math.abs(start.getY() - end.getY());

        return Math.min(xDiff, yDiff);
    }

    private Point2D.Double calculateCenter(Point2D.Double upperLeft, double width, double height) {

        double x = upperLeft.getX() + width / 2;
        double y = upperLeft.getY() + height / 2;
        return new Point2D.Double(x,y);
    }

    private Point2D.Double getUpperLeft(Point2D.Double start, Point2D.Double end) {

        double x = Math.min(start.getX(), end.getX());
        double y = Math.min(start.getY(), end.getY());

        return new Point2D.Double(x, y);
    }

    private Point2D.Double getUpperLeftSquare(Point2D.Double start, Point2D.Double end, double size) {
        double x = Math.min(start.getX(), Math.max(end.getX(), (start.getX() - size)));
        double y = Math.min(start.getY(), Math.max(end.getY(), (start.getY() - size)));

        return new Point2D.Double(x, y);
    }

    private Point2D.Double average(ArrayList<Point2D.Double> triangle) {
        int x = 0;
        int y = 0;

        for (Point2D.Double point : triangle) {
            x += point.getX();
            y += point.getY();
        }

        return new Point2D.Double(x / triangle.size(), y / triangle.size());
    }

    private Point2D.Double calculateOffset(Point2D.Double end, Point2D.Double start) {
        double x = end.getX() - start.getX();
        double y = end.getY() - start.getY();

        return new Point2D.Double(x, y);
    }

    private double calculateRotation(Shape shape, Point2D.Double end) {

        double y = 0; // x tjs egal a zero

        if (shape instanceof Circle) {
            y = - ((Circle) shape).getRadius() - 16;
        }
        else if (shape instanceof Ellipse) {
            y = - ((Ellipse) shape).getHeight() / 2 - 16;
        }
        else if (shape instanceof Rectangle) {
            y = - ((Rectangle) shape).getHeight() / 2 - 16;
        }
        else if (shape instanceof Square) {
            y = -((Square) shape).getSize() / 2 - 16;
        }
        /*else if (shape instanceof Triangle) {

        }
        */

        Point2D.Double a = new Point2D.Double(0, y); // handle
        Point2D.Double b = new Point2D.Double(0,0); // center
        Point2D.Double c = end;

        Point2D.Double v1 = new Point2D.Double(a.getX() - b.getX(), a.getY() - b.getY());
        Point2D.Double v2 = new Point2D.Double(c.getX() - b.getX(), c.getY() - b.getY());

        double result = Math.PI - Math.atan2(v2.getX(), v2.getY());// - Math.atan2(v1.getY(), v1.getX());

        if (result < 0) {
            return result += 2 * Math.PI;
        }
        else {
            return result;
        }

    }
}

/** rotation, calculate angle
 * tan theta = length of (p2 - p1) / length of (p0 - p1) with p0 the center of rotation
 * function to use: atan2()
 *
 * Object to world --> rotate then translate
 * world to object --> translate then rotate
 *
 * SELECTION GEOMETRY do the test in object space
 * point in shape
 * point near shape
 *
 * p.^n = d implicit representation
 * p0 + t d parametric representation
 *
 * use bounding boxes. if in the bounding box, then test against actual object
 */
