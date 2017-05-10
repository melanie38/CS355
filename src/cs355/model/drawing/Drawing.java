package cs355.model.drawing;

import cs355.controller.CS355Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Melanie on 06/05/2017.
 */
public class Drawing extends CS355Drawing {

    private static List<Shape> listOfShapes;

    private static Drawing instance;

    public static Drawing inst() {
        if (instance == null) {
            instance = new Drawing();
            listOfShapes = new ArrayList<>();
        }
        return instance;
    }

    @Override
    public Shape getShape(int index) {

        return listOfShapes.get(index);
    }

    @Override
    public int addShape(Shape s) {

        listOfShapes.add(s);
        update();

        return listOfShapes.size() - 1;
    }

    @Override
    public void deleteShape(int index) {
        listOfShapes.remove(index);
        update();
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
        return listOfShapes;
    }

    @Override
    public List<Shape> getShapesReversed() {
        return null;
    }

    @Override
    public void setShapes(List<Shape> shapes) {
        listOfShapes = shapes;
    }

    public void update() {
        setChanged();
        notifyObservers();
    }
}
