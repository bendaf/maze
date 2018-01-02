package model.wall;

import java.util.ArrayList;

import model.squareobject.Pickable;

public class NoWall extends Wall {

	public NoWall(boolean isHorizontal) {
		super(isHorizontal);
	}

	@Override
	public boolean canCross(ArrayList<Pickable> possession) {
		return true;
	}

	@Override
	public String toString() {
		return isHorizontal ? "   " : " ";
	}

}
