package cs355.model.drawing;

import com.sun.jna.platform.win32.WinDef;
import cs355.GUIFunctions;
import cs355.controller.CS355Controller;
import cs355.utilities.Converter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        Shape temp = listOfShapes.get(index);
        listOfShapes.remove(index);
        listOfShapes.add(temp);
    }

    @Override
    public void movetoBack(int index) {
        Shape temp = listOfShapes.get(index);
        listOfShapes.remove(index);
        listOfShapes.add(0, temp);
    }

    @Override
    public void moveForward(int index) {
        Collections.swap(listOfShapes, index, index + 1);
    }

    @Override
    public void moveBackward(int index) {
        Collections.swap(listOfShapes, index, index - 1);
    }

    @Override
    public List<Shape> getShapes() {
        return listOfShapes;
    }

    @Override
    public List<Shape> getShapesReversed() {
        List<Shape> result = new ArrayList<>();
        for (int i = listOfShapes.size() - 1; i > -1; i--) {
            result.add(listOfShapes.get(i));
        }
        return result;
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
