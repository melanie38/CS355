package cs355.model.drawing;

import cs355.GUIFunctions;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your line code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Line extends Shape {

	// The ending point of the line.
	private Point2D.Double end;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param start the starting point.
	 * @param end the ending point.
	 */
	public Line(Color color, Point2D.Double start, Point2D.Double end) {

		// Initialize the superclass.
		super(color, start);

		// Set the field.
		this.end = end;
	}

	/**
	 * Getter for this Line's ending point.
	 * @return the ending point as a Java point.
	 */
	public Point2D.Double getEnd() {
		return end;
	}

	/**
	 * Setter for this Line's ending point.
	 * @param end the new ending point for the Line.
	 */
	public void setEnd(Point2D.Double end) {
		this.end = end;
	}

	/**
	 * Add your code to do an intersection test
	 * here. You <i>will</i> need the tolerance.
	 * @param pt = the point to test against.
	 * @param tolerance = the allowable tolerance.
	 * @return true if pt is in the shape,
	 *		   false otherwise.
	 */
	@Override
	public boolean pointInShape(Point2D.Double pt, double tolerance) {

		// calculate n^
		Point2D.Double ndiff = new Point2D.Double(getEnd().getX(), getEnd().getY());
		Point2D.Double n = new Point2D.Double(-ndiff.getY(), ndiff.getX());
		double norm = Math.sqrt(Math.pow(ndiff.getX(), 2) + Math.pow(ndiff.getY(), 2));
		double nHatX = n.getX() / norm;
		double nHatY = n.getY() / norm;
		// calculate d
		double dX = getEnd().getX() * nHatX + getEnd().getY() * nHatY;
		// |q.n^ - d| is the distance of the point - line
		double distance = Math.abs(((pt.getX() * nHatX) + (pt.getY() * nHatY)) - dX);

		if (distance <= tolerance) {
			// check if point is withing endpoints of the line
			double lineNorm = Math.sqrt(Math.pow(getEnd().getX(), 2) + Math.pow(getEnd().getY(), 2));
			double normQP1 = Math.sqrt(Math.pow(pt.getX(), 2) + Math.pow(pt.getY(), 2));
			double normQP2 = Math.sqrt(Math.pow(pt.getX() - getEnd().getX(), 2) + Math.pow(pt.getY() - getEnd().getY(), 2));

			if (normQP1 <= lineNorm && normQP2 <= lineNorm) {
				GUIFunctions.printf("line selected");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isOnHandle(Point2D.Double pt, double tolerance) {
		return false;
	}
}
