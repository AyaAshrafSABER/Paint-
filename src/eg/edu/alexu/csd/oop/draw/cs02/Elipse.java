package eg.edu.alexu.csd.oop.draw.cs02;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Elipse extends MyShape {
    /**
     *. 
     */
    private  int counterE = 1;

    /**
     * 
     */
    public Elipse() {
    prop = new HashMap<String, Double>();
	prop.put("a", 0.0);
	prop.put("b", 0.0);
    }

    /* create a deep clone of the shape */
    @Override
    public Object clone() throws CloneNotSupportedException {
	final Shape ellipse = new Elipse();
	ellipse.setColor(getColor());
	ellipse.setFillColor(getFillColor());
	ellipse.setPosition(getPosition());
	final Map<String, Double> newprop = new HashMap<>();
	for (final Map.Entry<String, Double> s : getProperties().entrySet()) {
	    newprop.put(s.getKey().toString(), s.getValue());
	}
	ellipse.setProperties(newprop);
	return ellipse;

    }

    @Override
	public final void draw(final Graphics canvas) {
	double a = prop.get("a");
	double b = prop.get("b");
	canvas.setColor(c);
	Graphics2D g = (Graphics2D) canvas;
	g.setStroke(new BasicStroke(2));
	Ellipse2D.Double shape = new Ellipse2D.Double(position.x - a / 2,
		position.y - b / 2, a, b);
	g.draw(shape);
	if (fillC != null) {
	    g.setColor(fillC);
	    g.fill(shape);
	}
    }

    @Override
	public final String getName() {
	return "Ellipse ";

    }

    @Override
	public final int counter() {
	return counterE;
    }
}
