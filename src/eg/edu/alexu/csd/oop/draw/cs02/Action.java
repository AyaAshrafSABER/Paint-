package eg.edu.alexu.csd.oop.draw.cs02;

import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * .
 * 
 * @author Target. .
 */
public class Action {
	/**
	 * . .
	 */
	private final String operation;
	/**
	 * .
	 */
	private final Shape oldShape;
	/**
	 * 
	 */
	private final Shape newShape;
	/**
	 * .
	 */
	private final int indexOfDeletedShape;

	/**
	 * @return
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @return old shape.
	 */
	public Shape getOldShape() {
		return oldShape;
	}

	/**
	 * @return
	 */
	public Shape getNewShape() {
		return newShape;
	}

	/**
	 * @return.
	 */
	public int getIndex() {
		return this.indexOfDeletedShape;
	}

	/**.
	 * @param operation.
	 * @param oldShape.
	 * @param newShape.
	 * @param indexOfDeletedShape.
	 */
	public Action(final String operation, final Shape oldShape,
			final Shape newShape, final int indexOfDeletedShape) {
		this.operation = operation;
		this.oldShape = oldShape;
		this.newShape = newShape;
		this.indexOfDeletedShape = indexOfDeletedShape;
	}

}
