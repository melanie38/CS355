package cs355.model.drawing;

import cs355.GUIFunctions;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * Add your triangle code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Triangle extends Shape {

	// The three points of the triangle.
	private Point2D.Double a;
	private Point2D.Double b;
	private Point2D.Double c;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param center the center of the new shape.
	 * @param a the first point, relative to the center.
	 * @param b the second point, relative to the center.
	 * @param c the third point, relative to the center.
	 */
	public Triangle(Color color, Point2D.Double center, Point2D.Double a,
					Point2D.Double b, Point2D.Double c)
	{

		// Initialize the superclass.
		super(color, center);

		// Set fields.
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Getter for the first point.
	 * @return the first point as a Java point.
	 */
	public Point2D.Double getA() {
		return a;
	}

	/**
	 * Setter for the first point.
	 * @param a the new first point.
	 */
	public void setA(Point2D.Double a) {
		this.a = a;
	}

	/**
	 * Getter for the second point.
	 * @return the second point as a Java point.
	 */
	public Point2D.Double getB() {
		return b;
	}

	/**
	 * Setter for the second point.
	 * @param b the new second point.
	 */
	public void setB(Point2D.Double b) {
		this.b = b;
	}

	/**
	 * Getter for the third point.
	 * @return the third point as a Java point.
	 */
	public Point2D.Double getC() {
		return c;
	}

	/**
	 * Setter for the third point.
	 * @param c the new third point.
	 */
	public void setC(Point2D.Double c) {
		this.c = c;
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
		Point2D.Double A = getA();
		Point2D.Double B = getB();
		Point2D.Double C = getC();

		Point2D.Double edgeAQ = new Point2D.Double(pt.getX() - A.getX(), pt.getY() - A.getY());
		Point2D.Double edgeBQ = new Point2D.Double(pt.getX() - B.getX(), pt.getY() - B.getY());
		Point2D.Double edgeCQ = new Point2D.Double(pt.getX() - C.getX(), pt.getY() - C.getY());

		Point2D.Double edgeAB = new Point2D.Double(B.getX() - A.getX(), B.getY() - A.getY());
		Point2D.Double edgeBC = new Point2D.Double(C.getX() - B.getX(), C.getY() - B.getY());
		Point2D.Double edgeCA = new Point2D.Double(A.getX() - C.getX(), A.getY() - C.getY());

		double testA = getDotProduct(edgeAQ, new Point2D.Double(-edgeAB.getY(), edgeAB.getX()));
		double testB = getDotProduct(edgeBQ, new Point2D.Double(-edgeBC.getY(), edgeBC.getX()));
		double testC = getDotProduct(edgeCQ, new Point2D.Double(-edgeCA.getY(), edgeCA.getX()));

		if (sameSign(testA, testB, testC)) {
			GUIFunctions.printf("triangle selected");
			return true;
		}
		return false;
	}

	@Override
	public boolean isOnHandle(Point2D.Double pt, double tolerance) {
		if (pt.getX() > -6 && pt.getX() < 6) {
			//if (pt.getY())
			return true;
		}
		return false;
	}

	private double getDotProduct(Point2D.Double point, Point2D.Double edge) {
		return (point.getX() * edge.getX()) + (point.getY() * edge.getY());
	}

	private boolean sameSign(double a, double b, double c) {
		if (a < 0 && b < 0 && c < 0) {
			return true;
		}
		else if (a > 0 && b > 0 && c > 0) {
			return true;
		}
		return false;
	}

}
