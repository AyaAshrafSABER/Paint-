package eg.edu.alexu.csd.oop.draw.cs02;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Square extends MyShape {
    private int counterS = 1;

    public Square() {
	prop = new HashMap<String, Double>();
	prop.put("length", 0.0);
    }

    /* create a deep clone of the shape */
    @Override
    public Object clone() throws CloneNotSupportedException {
	final Shape square = new Square();
	square.setColor(getColor());
	square.setFillColor(getFillColor());
	square.setPosition(getPosition());
	final Map<String, Double> newprop = new HashMap<>();
	for (final Map.Entry<String, Double> s : getProperties().entrySet()) {
	    newprop.put(s.getKey().toString(), s.getValue());
	}
	square.setProperties(newprop);
	return square;

    }

    @Override
    public void draw(Graphics canvas) {
	// TODO Auto-generated method stub
	double l = prop.get("length");
	canvas.setColor(c);
	Graphics2D g = (Graphics2D) canvas;
	g.setStroke(new BasicStroke(2));
	Rectangle2D.Double shape = new Rectangle2D.Double(position.x,
		position.y, l, l);
	g.draw(shape);
	if (fillC != null) {
	    g.setColor(fillC);
	    g.fill(shape);
	}
    }

    @Override
    public String getName() {
	return "Square ";

    }

    @Override
    public int counter() {
	return counterS;
    }
}
