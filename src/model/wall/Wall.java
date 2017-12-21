package model.wall;

import java.util.ArrayList;

import model.squareobject.SquareObject;

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

	public boolean canCross(ArrayList<SquareObject> possession) {
		return false;
	}

}
