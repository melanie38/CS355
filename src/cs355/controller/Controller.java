package cs355.controller;

import cs355.GUIFunctions;
import cs355.model.drawing.Drawing;
import cs355.model.drawing.Line;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.Iterator;

/**
 * Created by Melanie on 04/05/2017.
 */
public class Controller implements CS355Controller {

    private Color color;
    private String tool;
    private Point2D.Double start;
    private Point2D.Double end;
    private int index;

    @Override
    public void colorButtonHit(Color c) {

        color = c;
        GUIFunctions.changeSelectedColor(c);
    }

    @Override
    public void lineButtonHit() {

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

    }

    @Override
    public void openDrawing(File file) {

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
                break;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        GUIFunctions.printf("inside mouseReleased");
        // Add the shape to the model
        end = new Point2D.Double(e.getPoint().x, e.getPoint().y);

        switch (tool) {
            case "line":
                Line l = (Line) Drawing.inst().getShape(index);
                l.setEnd(end);
                break;
        }

        GUIFunctions.printf("end coordinates = %d, %d\n", end.getX(), end.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        GUIFunctions.printf("inside mouseDragged");
        // Update the end coordinate of the shape
        end = new Point2D.Double(e.getPoint().x, e.getPoint().y);

        switch (tool) {
            case "line":
                Line l = (Line) Drawing.inst().getShape(index);
                l.setEnd(end);
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
