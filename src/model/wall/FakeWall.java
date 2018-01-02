package model.wall;

import java.util.ArrayList;

import model.squareobject.Pickable;

public class FakeWall extends Wall {

	public FakeWall(boolean isHorizontal) {
		super(isHorizontal);
	}
	
	@Override
	public boolean canCross(ArrayList<Pickable> possession) {
		return true;
	}
}
