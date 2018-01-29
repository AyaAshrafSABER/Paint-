package eg.edu.alexu.csd.oop.draw.cs02;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Rectangle extends MyShape {
    private int counterR = 1;

    public Rectangle() {
	prop= new HashMap<String, Double>();
	prop.put("length", 0.0);
	prop.put("width", 0.0);
    }

    /* create a deep clone of the shape */
    @Override
    public Object clone() throws CloneNotSupportedException {
	final Shape rect = new Rectangle();
	rect.setColor(getColor());
	rect.setFillColor(getFillColor());
	rect.setPosition(getPosition());
	final Map<String, Double> newprop = new HashMap<>();
	for (final Map.Entry<String, Double> s : getProperties().entrySet()) {
	    newprop.put(s.getKey().toString(), s.getValue());
	}
	rect.setProperties(newprop);
	return rect;
    }

    @Override
    public void draw(Graphics canvas) {
	// TODO Auto-generated method stub
	double l = prop.get("length");
	double w = prop.get("width");
	canvas.setColor(c);
	Graphics2D g = (Graphics2D) canvas;
	g.setStroke(new BasicStroke(2));
	Rectangle2D.Double shape = new Rectangle2D.Double(position.x,
		position.y, l, w);
	g.draw(shape);
	if (fillC != null) {
	    g.setColor(fillC);
	    g.fill(shape);
	}
    }

    @Override
	public final String getName() {
	return "Rectangle ";

    }

    @Override
    public int counter() {
	return counterR;
    }
}
