package eg.edu.alexu.csd.oop.draw.cs02;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author Target
 *
 */
public class Circle extends MyShape {

	/**
	 *.
	 */
	private int counterC = 1;

	/**
	 * .
	 */
	public Circle() {
		prop = new HashMap<String, Double>();
		prop.put("radius", 0.0);
	}

	/* create a deep clone of the shape */
	@Override
	public Object clone() throws CloneNotSupportedException {
		final Shape circle = new Circle();
		circle.setColor(getColor());
		circle.setFillColor(getFillColor());
		circle.setPosition(getPosition());
		final Map<String, Double> newprop = new HashMap<>();
		for (final Map.Entry<String, Double> s : getProperties().entrySet()) {
			newprop.put(s.getKey().toString(), s.getValue());
		}
		circle.setProperties(newprop);
		return circle;
	}

	@Override
	public final void draw(final Graphics canvas) {
		double r;
		r = prop.get("radius");
		canvas.setColor(c);
		Graphics2D g = (Graphics2D) canvas;
		g.setStroke(new BasicStroke(2));
		Ellipse2D.Double shape = new Ellipse2D.Double(position.x - r / 2, position.y - r / 2, r, r);
		g.draw(shape);
		if (fillC != null) {
			g.setColor(fillC);
			g.fill(shape);
		}
	}

	@Override
	public final String getName() {
		return "Circle ";
	}

	@Override
	public final int counter() {
		return counterC;
	}
}
