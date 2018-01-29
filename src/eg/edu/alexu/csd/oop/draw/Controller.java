package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import eg.edu.alexu.csd.oop.draw.cs02.MyDrawingEngine;
import eg.edu.alexu.csd.oop.draw.cs02.MyShape;

public class Controller {
	private MyDrawingEngine model = new MyDrawingEngine();
	private Paint view = new Paint();
	int flag = 0;
	int x = 0;
	int y = 0;
	int X2, Y2;
	int length = 0;
	MyShape currentShape;
	double dragLength = 0;
	double dragWidth = 0;
	Color color = Color.BLACK, fillC = null;
	ArrayList<Integer> trianglePoints = new ArrayList<Integer>();
	ArrayList<Integer> linePoints = new ArrayList<Integer>();
	int index = 0;
	public JFrame frame;
	JLabel currentShapeLabel;
	public JComboBox<Object> comboBox;
	int counterT = 1;
	Shape draggedShape = null;
	int selectionFlag = 0;
	Double r;
	int x1 = 0;
	int y1 = 0;

	public Controller(MyDrawingEngine modle, Paint view) {
		this.model = modle;
		this.view = view;
		//this.view.canvas.addPainterListener(new listenForCanvas());
		//this.view.canvas.addPainterDragListener(new MovingCanvas());
	}

	

	//public class listenForCanvas extends MouseAdapter {
//		@Override
//		public void mousePressed(MouseEvent e) {
//			if (view.currentShapeLabel.getText().equals("Copy")) {
//				view.x = e.getX();
//				view.y = e.getY();
//				try {
//					MyShape copied = (MyShape) model.shapes.get(view.comboBox.getSelectedIndex()).clone();
//					copied.setPosition(new Point(x, y));
//					for (int j = model.shapes.size() - 1; j >= 0; j--) {
//						if (copied.getClass().getSimpleName().equals(model.shapes.get(j).getClass().getSimpleName())) {
//							copied.Counter = ((MyShape) model.shapes.get(j)).Counter + 1;
//							break;
//						}
//					}
//					model.addShape(copied);
//					view.canvas.repaint();
//				} catch (CloneNotSupportedException ef) {
//					ef.printStackTrace();
//				}
//			}
//			if (currentShapeLabel.getText().equals("triangle")) {
//				x = e.getX();
//				y = e.getY();
//				trianglePoints.add(x);
//				trianglePoints.add(y);
//				Circle c = new Circle();
//				Map<String, java.lang.Double> propertiess = new HashMap<String, java.lang.Double>();
//				propertiess.put("radius", 8.0);
//				c.setProperties(propertiess);
//				c.setColor(Color.BLACK);
//				c.setFillColor(null);
//				Point position1 = new Point(x, y);
//				c.setPosition(position1);
//				c.draw(view.canvas.getGraphics());
//				if (trianglePoints.size() == 6) {
//					Triangle t = new Triangle();
//					t.setColor(color); // setting Fill color
//					t.setFillColor(fillC); // setting position
//					Point position = new Point(trianglePoints.get(0), trianglePoints.get(1));
//					t.setPosition(position); // setting properties
//					Map<String, java.lang.Double> properties = new HashMap<String, java.lang.Double>();
//					properties.put("secondX", (double) trianglePoints.get(2));
//					properties.put("secondY", (double) trianglePoints.get(3));
//					properties.put("thirdX", (double) trianglePoints.get(4));
//					properties.put("thirdY", (double) trianglePoints.get(5));
//					t.setProperties(properties);
//					c.draw(view.canvas.getGraphics());
//					t.Counter = counterT++;
//					model.addShape(t);
//					view.canvas.repaint();
//					view.prepareComboBox(comboBox, DrawingEngineSinglton.drawingEngine());
//					currentShapeLabel.setText("None");
//					trianglePoints.clear();
//				}
//			}
//			for (int i = 0; i < model.getSupportedShapes().size(); i++) {
//				if (currentShapeLabel.getText().equals(model.getSupportedShapes().get(i).getSimpleName())) {
//					x = e.getX();
//					y = e.getY();
//					Constructor<?> constructor = null;
//					try {
//						constructor = model.getSupportedShapes().get(i).getConstructor();
//					} catch (NoSuchMethodException e1) {
//						e1.printStackTrace();
//					} catch (SecurityException e1) {
//						e1.printStackTrace();
//					}
//					MyShape shapeObject = null;
//					try {
//						shapeObject = (MyShape) constructor.newInstance();
//					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//							| InvocationTargetException e1) {
//						e1.printStackTrace();
//					}
//					int dummii;
//					if (currentShapeLabel.getText().equals("Square")) {
//						dummii = 1;
//					}
//					view.canvas.repaint();
//					view.setProperties(shapeObject);
//					Map<String, java.lang.Double> properties = shapeObject.getProperties();
//					String name = model.getSupportedShapes().get(i).getSimpleName();
//					int counter2 = 0;
//					if (!(view.textFields.get(counter2).getText()).equals("")) {
//						for (Map.Entry<String, Double> entry : properties.entrySet()) {
//
//							properties.put(view.labels.get(counter2).getText(),
//									Double.valueOf(view.textFields.get(counter2).getText()));
//							counter2++;
//
//						}
//						shapeObject.setProperties(properties);
//						for (int j = model.shapes.size() - 1; j >= 0; j--) {
//							if (model.getSupportedShapes().get(i).getSimpleName()
//									.equals(model.shapes.get(j).getClass().getSimpleName())) {
//								shapeObject.Counter = ((MyShape) model.shapes.get(j)).Counter + 1;
//								break;
//							}
//						}
//						model.addShape(shapeObject);
//						view.canvas.repaint();
//						view.prepareComboBox(comboBox, DrawingEngineSinglton.drawingEngine());
//						currentShapeLabel.setText("None");
//					}
//				}
//			}
//
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// TODO Auto-generated method stub
//			if (currentShapeLabel.getText().equals("Circle") && flag == 1) {
//				flag = 0;
//				Circle c = new Circle();
//				c.setColor(color);
//				c.setFillColor(fillC);
//				c.setPosition(new Point(x1, y1));
//				Map<String, java.lang.Double> prop = new HashMap<String, java.lang.Double>();
//				prop.put("radius", r);
//				c.setProperties(prop);
//				for (int j = model.shapes.size() - 1; j >= 0; j--) {
//					if ("Circle".equals(model.shapes.get(j).getClass().getSimpleName())) {
//						c.Counter = ((MyShape) model.shapes.get(j)).Counter + 1;
//						break;
//					}
//				}
//				model.addShape(c);
//				view.prepareComboBox(comboBox, model);
//				view.canvas.repaint();
//			}
//			if (currentShapeLabel.getText().equals("Square") && flag == 1) {
//				flag = 0;
//				Square sq = new Square();
//				sq.setColor(color);
//				sq.setFillColor(fillC);
//				sq.setPosition(new Point(x1, y1));
//				Map<String, java.lang.Double> prop = new HashMap<String, java.lang.Double>();
//				prop.put("length", dragLength);
//				sq.setProperties(prop);
//
//				for (int j = model.shapes.size() - 1; j >= 0; j--) {
//					if ("Square".equals(model.shapes.get(j).getClass().getSimpleName())) {
//						sq.Counter = ((MyShape) model.shapes.get(j)).Counter + 1;
//						break;
//					}
//				}
//				model.addShape(sq);
//				view.prepareComboBox(comboBox, DrawingEngineSinglton.drawingEngine());
//				view.canvas.repaint();
//			}
//			if (currentShapeLabel.getText().equals("Rectangle") && flag == 1) {
//				flag = 0;
//				Rectangle rec = new Rectangle();
//				rec.setColor(color);
//				rec.setFillColor(fillC);
//				rec.setPosition(new Point(x1, y1));
//				Map<String, java.lang.Double> prop = new HashMap<String, java.lang.Double>();
//				prop.put("length", dragLength);
//				prop.put("width", dragWidth);
//				rec.setProperties(prop);
//
//				for (int j = model.shapes.size() - 1; j >= 0; j--) {
//					if ("Rectangle".equals(model.shapes.get(j).getClass().getSimpleName())) {
//						rec.Counter = ((MyShape) model.shapes.get(j)).Counter + 1;
//						break;
//					}
//				}
//				model.addShape(rec);
//				view.prepareComboBox(comboBox, model);
//				view.canvas.repaint();
//			}
//			if (currentShapeLabel.getText().equals("Elipse") && flag == 1) {
//				flag = 0;
//				Elipse elps = new Elipse();
//				elps.setColor(color);
//				elps.setFillColor(fillC);
//				elps.setPosition(new Point(x1, y1));
//				Map<String, java.lang.Double> prop = new HashMap<String, java.lang.Double>();
//				prop.put("a", dragLength);
//				prop.put("b", dragWidth);
//				elps.setProperties(prop);
//
//				for (int j = model.shapes.size() - 1; j >= 0; j--) {
//					if ("Elipse".equals(model.shapes.get(j).getClass().getSimpleName())) {
//						elps.Counter = ((MyShape) model.shapes.get(j)).Counter + 1;
//						break;
//					}
//				}
//				model.addShape(elps);
//				view.prepareComboBox(comboBox, DrawingEngineSinglton.drawingEngine());
//				view.canvas.repaint();
//			}
//			if (currentShapeLabel.getText().equals("Line") && flag == 1) {
//				flag = 0;
//				Line l = new Line();
//				l.setColor(color);
//				l.setFillColor(fillC);
//				l.setPosition(new Point(x1, y1));
//				Map<String, java.lang.Double> prop = new HashMap<String, java.lang.Double>();
//				prop.put("x2", (double) X2);
//				prop.put("y2", (double) Y2);
//				l.setProperties(prop);
//
//				for (int j = model.shapes.size() - 1; j >= 0; j--) {
//					if ("Line".equals(model.shapes.get(j).getClass().getSimpleName())) {
//						l.Counter = ((MyShape) model.shapes.get(j)).Counter + 1;
//						break;
//					}
//				}
//				model.addShape(l);
//				view.prepareComboBox(comboBox, DrawingEngineSinglton.drawingEngine());
//				view.canvas.repaint();
//			}
//		}
//
//	}
//
	
}
