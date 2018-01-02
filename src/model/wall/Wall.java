package model.wall;

import java.util.ArrayList;

import model.squareobject.Pickable;

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
