package eg.edu.alexu.csd.oop.draw.cs02;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author Target
 *
 */
public class XML {
	 /**.
	 *.
	 */
	public XML() {
	}
	/**.
	 * @param path.
	 * @param myShapes.
	 * @throws IOException.
	 */
	public void serializeToXML(final String path,
			final ArrayList<Shape> myShapes) throws IOException {
	        FileOutputStream fos = new FileOutputStream(path);
			BufferedOutputStream bos = new
					BufferedOutputStream(fos);
	        XMLEncoder encoder = new XMLEncoder(bos);
	        encoder.writeObject(myShapes);
	        encoder.close();
	        fos.close();
	    }
	    public  ArrayList<Shape> deserializeFromXML(final String path) throws IOException {
	        FileInputStream fis = new FileInputStream(path);
	        BufferedInputStream bos = new
					BufferedInputStream(fis);
	        XMLDecoder decoder = new XMLDecoder(bos);
	        ArrayList<Shape> decodedSettings = (ArrayList<Shape>)
	        		decoder.readObject();
	        decoder.close();
	        fis.close();
	        return decodedSettings;
	    }
}

