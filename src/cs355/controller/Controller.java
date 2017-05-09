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
    @Override
    public void colorButtonHit(Color c) {
        //GUIFunctions.printf("inside colorButtonHit");
        Drawing.inst().setColor(c);
        GUIFunctions.changeSelectedColor(c);
    }

    @Override
    public void lineButtonHit() {

        Line line = new Line(Drawing.inst().getColor(), new Point2D.Double(0, 0), new Point2D.Double(0, 0));
        GUIFunctions.printf("new line created");
    }

    @Override
    public void squareButtonHit() {

    }

    @Override
    public void rectangleButtonHit() {

    }

    @Override
    public void circleButtonHit() {

    }

    @Override
    public void ellipseButtonHit() {

    }

    @Override
    public void triangleButtonHit() {

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

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
