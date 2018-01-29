
package eg.edu.alexu.csd.oop.draw.cs02;

import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class MyDrawingEngine implements DrawingEngine {
	/**
	 * 
	 */
	public ArrayList<Shape> shapes = new ArrayList<Shape>();
	/**
	 * 
	 */
	int undoCounter = 0;

	/**
	 * 
	 */
	Deque<Action> undo = new LinkedList<Action>();
	/**
	 * 
	 */
	Deque<Action> redo = new LinkedList<Action>();
	/**
	 * 
	 */
	private final int maxNumOfUndo = 20;

	/**
	 * 
	 */
	LinkedList<Class<? extends Shape>> supportedShapes = new LinkedList<Class<? extends Shape>>();

	@Override
	public final void refresh(final Graphics canvas) {
		int len = shapes.size();
		for (int i = 0; i < len; i++) {
			shapes.get(i).draw(canvas);
		}
	}

	@Override
	public final void addShape(final Shape shape) {
		shapes.add(shape);
		Action action = new Action("Add", shape, null,
				shapes.size() - 1);
		if (undo.size() >= maxNumOfUndo) {
			undo.removeLast();
		}
		redo.clear();
		undo.push(action);
	}

	@Override
	public final void removeShape(final Shape shape) {
		int index = shapes.indexOf(shape);
		shapes.remove(shape);
		Action action = new Action("Delete", shape, null, index);
		if (undo.size() >= maxNumOfUndo) {
			undo.removeLast();
		}
		redo.clear();
		undo.push(action);
	}

	@Override
	public final void updateShape(final Shape oldShape,
			final Shape newShape) {
		int place = 0;
		if (shapes.contains(oldShape)) {
			place = shapes.indexOf(oldShape);
			shapes.remove(oldShape);
			shapes.add(place, newShape);
			
			Action action = new Action("Update",
					oldShape,newShape, -10);
			if (undo.size() >= maxNumOfUndo) {
				undo.removeLast();
			}
			redo.clear();
			((MyShape) newShape).Counter =
					((MyShape)oldShape).Counter;
			undo.push(action);
		}

	}

	@Override
	public final Shape[] getShapes() {
		int length = shapes.size();
		Shape[] retArr = new Shape[length];
		retArr = shapes.toArray(retArr);
		return retArr;
	}

	@Override
	public final List<Class<? extends Shape>> getSupportedShapes() {
		if (supportedShapes.isEmpty()) {
			supportedShapes.add(Circle.class);
			supportedShapes.add(Rectangle.class);
			supportedShapes.add(Elipse.class);
			supportedShapes.add(Triangle.class);
			supportedShapes.add(Square.class);
			supportedShapes.add(Line.class);
		}
		return supportedShapes;
	}

	@Override
	public final void undo() {
		if (undo.isEmpty()) {
			return;
		}

		final Action lastOperation = this.undo.pop();
		if (lastOperation.getOperation() == "Add") {
			int index = lastOperation.getIndex();
			this.shapes.remove(lastOperation.getOldShape());
			redo.push(new Action("Delete",
					lastOperation.getOldShape(), null, index));
		} else if (lastOperation.getOperation() == "Delete") {
			this.shapes.add(lastOperation.getIndex(),
					lastOperation.getOldShape());
			redo.push(new Action("Add", lastOperation.getOldShape(),
					null, lastOperation.getIndex()));
		} else if (lastOperation.getOperation() == "Update") {
			int indexOfOldShape = this.shapes.
					indexOf(lastOperation.getNewShape());
			this.shapes.remove(lastOperation.getNewShape());
			this.shapes.add(indexOfOldShape,
					lastOperation.getOldShape());
			redo.push(new Action("Update", lastOperation.getNewShape(),
					lastOperation.getOldShape(), -1));
		}

	}

	@Override
	public void redo() {
		if (redo.isEmpty()) {
			return;
		}
		final Action lastOperation = this.redo.pop();
		if (lastOperation.getOperation() == "Add") {
			final int index = lastOperation.getIndex();
			this.shapes.remove(lastOperation.getOldShape());
			undo.push(new Action("Delete",
					lastOperation.getOldShape(), null, index));
		} else if (lastOperation.getOperation() == "Delete") {
			this.shapes.add(lastOperation.getIndex(),
					lastOperation.getOldShape());
			undo.push(new Action("Add", lastOperation.getOldShape()
					, null, lastOperation.getIndex()));
		} else if (lastOperation.getOperation() == "Update") {
			final int indexOfOldShape = this.shapes.
					indexOf(lastOperation.getNewShape());
			this.shapes.remove(lastOperation.getNewShape());
			this.shapes.add(indexOfOldShape, lastOperation.getOldShape());
			undo.push(new Action("Update", lastOperation.getNewShape(),
					lastOperation.getOldShape(), -1));
		}

	}

	@Override
	public final void save(final String path) {
		if (path.contains(".xml")) {
			XML xml = new XML();
			try {
				xml.serializeToXML(path, this.shapes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			//SaveAndLoad save = new SaveAndLoad();
			//save.save(path, this.shapes);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public final void load(final String path) {
		ArrayList<Shape> loadedShapes = new ArrayList<Shape>();
		if (path.contains(".xml")) {
			XML xml = new XML();
			try {
				loadedShapes = xml.deserializeFromXML(path);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			//SaveAndLoad load = new SaveAndLoad();
			//loadedShapes = (ArrayList<Shape>) load.load(path);
		}
		if (loadedShapes == null) {
			
		} else {this.shapes = loadedShapes;}
		redo.clear();
		undo.clear();
	}

	public final void reflect(String path) {
		final String cN = path.
				substring(path.lastIndexOf('\\') + 1, path.length());
		path = path.substring(0, path.lastIndexOf('\\'));
		File operatorFile = new File(path);

		ClassLoader operatorsLoader = null;
		try {
			operatorsLoader = new 
					URLClassLoader(new URL[] { operatorFile.toURI().toURL() });
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		File[] files = operatorFile.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".class") && name.equals(cN);
			}
		});
		System.out.println(files.length);
		if (files.length == 0) {
			throw new RuntimeException
			("File is not found in the destination or not supported format.");
		}
		for (File file : files) {
			String className = file.getName().
					substring(0, file.getName().length() - 6);
			System.out.println(className);
			try {
				@SuppressWarnings("unchecked")
				Class<? extends Shape> newClass =
				(Class<? extends Shape>) operatorsLoader.loadClass(className);
				this.supportedShapes.add(newClass);
				System.out.println(newClass.getName());
				System.out.println(supportedShapes.get(0).getName());

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

	@SuppressWarnings("unchecked")
	public final void reflectJarFile(final String path) {
		ArrayList<Class> classes = new ArrayList<Class>();
		String jarName = new String(path);
		try {
			@SuppressWarnings("resource")
			JarInputStream jarFile = new 
			JarInputStream(new FileInputStream(jarName));
			File myJar = new File(jarName);
			URL url = myJar.toURI().toURL();
			Class[] parameters = new Class[] { URL.class };
			URLClassLoader sysLoader = 
					(URLClassLoader) ClassLoader.getSystemClassLoader();
			Class<URLClassLoader> sysClass = URLClassLoader.class;
			Method method = sysClass.
					getDeclaredMethod("addURL", parameters);
			method.setAccessible(true);
			method.invoke(sysLoader, new Object[] {url});

			JarEntry jarEntry;
			while (true) {
				jarEntry = jarFile.getNextJarEntry();
				if (jarEntry == null) {
					break;
				}
				if (jarEntry.getName().endsWith(".class")) {
					System.out.println(jarEntry.getName().replaceAll("/", "\\."));
					String name = jarEntry.getName().replaceAll("/", "\\.").replace(".class", "");
					supportedShapes.add((Class<? extends Shape>) 
							ClassLoader.getSystemClassLoader().loadClass(name));
					System.out.println(supportedShapes.size() + "sna ");
					System.out.println(supportedShapes.get(supportedShapes.size() - 1));
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
