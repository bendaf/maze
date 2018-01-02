package model.squareobject;

import model.Maze;

/**
 * Only classes extended from {@link SquareObject} can be contained in a {@link Square} of the {@link Maze}. 
 * @author Felicián
 *
 */
public abstract class SquareObject {
	
	@Override
	public String toString() {
		return "   ";
	}
}
