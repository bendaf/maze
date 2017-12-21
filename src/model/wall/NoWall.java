package model.wall;

import java.util.ArrayList;

import model.squareobject.SquareObject;

public class NoWall extends Wall {

	public NoWall(boolean isHorizontal) {
		super(isHorizontal);
	}

	@Override
	public boolean canCross(ArrayList<SquareObject> possession) {
		return true;
	}

	@Override
	public String toString() {
		return isHorizontal ? "   " : " ";
	}

}
