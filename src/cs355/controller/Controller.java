package cs355.controller;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.drawing.Rectangle;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Melanie on 04/05/2017.
 */
public class Controller implements CS355Controller {

    private Color color = new Color(255,255,255);
    private String tool;
    private Point2D.Double start;
    private Point2D.Double end;
    private int index;
    private ArrayList<Point2D.Double> triangle = new ArrayList<>();

    @Override
    public void colorButtonHit(Color c) {
        color = c;
        GUIFunctions.changeSelectedColor(c);
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

    }

    @Override
    public void doMoveBackward() {

    }

    @Override
    public void doSendToFront() {

    }

    @Override
    public void doSendtoBack() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GUIFunctions.printf("mouse clicked");
        if (tool.equals("triangle")) {
            triangle.add(new Point2D.Double(e.getPoint().x, e.getPoint().y));
        }
        if (triangle.size() == 3) {
            Triangle t = new Triangle(color, triangle.get(0), triangle.get(1), triangle.get(2));
            Drawing.inst().addShape(t);
            Drawing.inst().update();

            triangle.clear();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        GUIFunctions.printf("inside mousePressed");
        // Create a new shape
        start = new Point2D.Double(e.getPoint().x, e.getPoint().y);

        switch(tool) {
            case "line":
                Line l = new Line(color, start, start);
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
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
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
                l.setEnd(end);
                Drawing.inst().update();
                break;
            case "circle":
                upperLeft = getUpperLeftSquare(start, end, size);
                Circle c = (Circle) Drawing.inst().getShape(index);
                c.setCenter(calculateEllipseCenter(upperLeft, size, size));
                c.setRadius(size(start, end) / 2);
                Drawing.inst().update();
                break;
            case "ellipse":
                Ellipse el = (Ellipse) Drawing.inst().getShape(index);
                el.setHeight(height);
                el.setWidth(width);
                el.setCenter(calculateEllipseCenter(upperLeft, width, height));
                Drawing.inst().update();
                break;
            case "rectangle":
                Rectangle r = (Rectangle) Drawing.inst().getShape(index);
                r.setHeight(height);
                r.setWidth(width);
                r.setUpperLeft(upperLeft);
                Drawing.inst().update();
                break;
            case "square":
                upperLeft = getUpperLeftSquare(start, end, size);
                Square s = (Square) Drawing.inst().getShape(index);
                s.setSize(size);
                s.setUpperLeft(upperLeft);
                Drawing.inst().update();
                break;
            case "triangle":
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

    private Point2D.Double calculateCircleCenter(Point2D.Double start, Point2D.Double end) {
        Point2D.Double result;

        result = new Point2D.Double((start.getX() + size(start, end) / 2), (start.getY() + size(start, end) / 2));

        return result;
    }

    private Point2D.Double calculateEllipseCenter(Point2D.Double upperLeft, double width, double height) {

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
}
