package eg.edu.alexu.csd.oop.draw.cs02;

/**
 * @author Target
 *
 */
public class DrawingEngineSinglton {
    /**
     * 
     */
    static MyDrawingEngine drawingEngine = new MyDrawingEngine();

    /**
     * @return.
     */
    public static MyDrawingEngine drawingEngine() {
    	return drawingEngine;
    }
}
