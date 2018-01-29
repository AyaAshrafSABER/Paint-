package eg.edu.alexu.csd.oop.draw.cs02;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Line extends MyShape {

    /**
     * 
     */
    private int counterL = 1;
    /**
     * 
     */
    private int Stroke = 2;

    public Line() {
    prop = new HashMap<>();
    prop.put("x2", 0.0);
	prop.put("y2", 0.0);
    }

    @Override
	public final void draw(final Graphics canvas) {
	int x2 = prop.get("x2").intValue();
	int y2 = prop.get("y2").intValue();
	Graphics2D g = (Graphics2D) canvas;
	g.setColor(getFillColor());
	g.setStroke(new BasicStroke(Stroke));
	g.setColor(getColor());
	g.drawLine((int) getPosition().getX(), (int) getPosition().getY(), x2,
		y2);
    }

    @Override
	public final Object clone() throws CloneNotSupportedException {
	final Shape line = new Line();
	line.setColor(getColor());
	line.setFillColor(getFillColor());
	line.setPosition(getPosition());
	final Map<String, Double> newprop = new HashMap<>();
	for (final Map.Entry<String, Double> s : getProperties().entrySet()) {
	    newprop.put(s.getKey(), s.getValue());
	}
	line.setProperties(newprop);
	return line;
    }

    @Override
	public final String getName() {
	return "Line ";
    }

    @Override
	public final int counter() {
	return counterL;
    }
    public final void setStroke(final int value) {
	Stroke = value;
    }
}
