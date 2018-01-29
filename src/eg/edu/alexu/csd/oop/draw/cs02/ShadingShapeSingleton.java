package eg.edu.alexu.csd.oop.draw.cs02;

import eg.edu.alexu.csd.oop.draw.Shape;

public class ShadingShapeSingleton {
    	 public static Shape shadingShape= null;
    	//Created this singleton in order not to change the selected shape to be highlighted by autp repainting
    	public static Shape shadingShape() {
	    return shadingShape;
    	}
}
