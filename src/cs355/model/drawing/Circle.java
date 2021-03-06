package cs355.model.drawing;

import cs355.GUIFunctions;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your circle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Circle extends Shape {

	// The radius.
	private double radius;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param center the center of the new shape.
	 * @param radius the radius of the new shape.
	 */
	public Circle(Color color, Point2D.Double center, double radius) {

		// Initialize the superclass.
		super(color, center);

		// Set the field.
		this.radius = radius;
	}

	/**
	 * Getter for this Circle's radius.
	 * @return the radius of this Circle as a double.
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Setter for this Circle's radius.
	 * @param radius the new radius of this Circle.
	 */
	public void setRadius(double radius) {
		this.radius = radius;
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

		// check if norm of p - c is smaller than the radius to the square
		double test = testCircle(pt);
		double radius = getRadius();

		if (test <= Math.pow(radius, 2)) {
			GUIFunctions.printf("circle selected");
			return true;
		}

		return false;
	}

	private double testCircle(Point2D.Double point) {

		return Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2);

	}

	@Override
	public boolean isOnHandle(Point2D.Double pt, double tolerance) {
		if (pt.getX() > -6 && pt.getX() < 6) {
			if (pt.getY() < -radius - 12 && pt.getY() > -radius - 20) {
				return true;
			}
		}
		return false;
	}

}
