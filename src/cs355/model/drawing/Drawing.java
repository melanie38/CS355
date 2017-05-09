package cs355.model.drawing;

import cs355.controller.CS355Controller;

import java.awt.Color;
import java.util.List;

/**
 * Created by Melanie on 06/05/2017.
 */
public class Drawing extends CS355Drawing {

    private static Color selectedColor = null;

    private static Drawing instance;

    public static Drawing inst() {
        if (instance == null) {
            instance = new Drawing();
        }
        return instance;
    }

    public static Color getColor() {
        return selectedColor;
    }

    public void setColor(Color c) {
        selectedColor = c;
    }

    @Override
    public Shape getShape(int index) {
        return null;
    }

    @Override
    public int addShape(Shape s) {
        return 0;
    }

    @Override
    public void deleteShape(int index) {

    }

    @Override
    public void moveToFront(int index) {

    }

    @Override
    public void movetoBack(int index) {

    }

    @Override
    public void moveForward(int index) {

    }

    @Override
    public void moveBackward(int index) {

    }

    @Override
    public List<Shape> getShapes() {
        return null;
    }

    @Override
    public List<Shape> getShapesReversed() {
        return null;
    }

    @Override
    public void setShapes(List<Shape> shapes) {

    }
}
