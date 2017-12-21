package model.wall;

import java.util.ArrayList;

import model.squareobject.Hammer;
import model.squareobject.SquareObject;

public class BreakableWall extends Wall {

	public BreakableWall(boolean isHorizontal) {
		super(isHorizontal);
	}
	
	@Override
	public boolean canCross(ArrayList<SquareObject> possession) {
		for (SquareObject o : possession){
			if (o instanceof Hammer)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return isHorizontal ? "-b-" : "b";
	}

}
