package cs355.model.drawing;

import cs355.GUIFunctions;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your square code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Square extends Shape {

	// The size of this Square.
	private double size;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param center the center of the new shape.
	 * @param size the size of the new shape.
	 */
	public Square(Color color, Point2D.Double center, double size) {

		// Initialize the superclass.
		super(color, center);

		// Set the field.
		this.size = size;
	}

	/**
	 * Getter for this Square's size.
	 * @return the size as a double.
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Setter for this Square's size.
	 * @param size the new size.
	 */
	public void setSize(double size) {
		this.size = size;
	}

	/**
	 * Add your code to do an intersection test
	 * here. You shouldn't need the tolerance.
	 * @param pt = the point to test against.
	 * @param tolerance = the allowable tolerance.
	 * @return true if pt is in the shape,
	 *		   false otherwise.
	 */
	@Override
	public boolean pointInShape(Point2D.Double pt, double tolerance) {
		double x = Math.abs(pt.getX());
		double y = Math.abs(pt.getY());
		double a = getSize() / 2.0;
		double b = getSize() / 2.0;

		if (x <= a && y <= b) {
			GUIFunctions.printf("square selected");
			return true;
		}

		return false;
	}

    @Override
    public boolean isOnHandle(Point2D.Double pt, double tolerance) {
        if (pt.getX() > -6 && pt.getX() < 6) {
            if (pt.getY() < -size/2 - 12 && pt.getY() > -size/2 - 20) {
                return true;
            }
        }
        return false;
    }

}
