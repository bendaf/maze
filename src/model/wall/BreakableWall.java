package model.wall;

import java.util.ArrayList;

import model.squareobject.Hammer;
import model.squareobject.Pickable;

public class BreakableWall extends Wall {

	public BreakableWall(boolean isHorizontal) {
		super(isHorizontal);
	}
	
	@Override
	public boolean canCross(ArrayList<Pickable> possession) {
		for (Pickable o : possession){
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
