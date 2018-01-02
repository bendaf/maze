package model.wall;

import java.util.ArrayList;

import model.Maze;
import model.squareobject.Pickable;

/**
 * Only classes extended from {@link Wall} can be walls of the {@link Maze}.
 * @author Felicián
 *
 */
public class Wall {
	boolean isHorizontal;

	public Wall(boolean isHorizontal) {
		super();
		this.isHorizontal = isHorizontal;
	}

	@Override
	public String toString() {
		return isHorizontal ? "---" : "|";
	}

	public boolean canCross(ArrayList<Pickable> possession) {
		return false;
	}

}
