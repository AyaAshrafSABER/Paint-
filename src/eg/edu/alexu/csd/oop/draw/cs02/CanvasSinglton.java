package eg.edu.alexu.csd.oop.draw.cs02;

import java.awt.Canvas;

/**
 * @author Target
 *
 */
public class CanvasSinglton {
	/**
	 * 
	 */
	public static Canvas canvas = new Canvas();

	/**
	 * @return
	 */
	public static Canvas Canvas() {
	    return canvas;
    	}
}
