package eg.edu.alexu.csd.oop.draw;

import java.awt.EventQueue;

import eg.edu.alexu.csd.oop.draw.cs02.MyDrawingEngine;

public class MVC {
    private static Controller control;

	public static void main(String[] args) {
	MyDrawingEngine model = new MyDrawingEngine();
	Paint view = new Paint ();
	control = new Controller(model, view);
	/**
	 * Launch the application.
	 */
	EventQueue.invokeLater(new Runnable() {
		@Override
		public void run() {
			try {
				view.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	
	
    }
}
