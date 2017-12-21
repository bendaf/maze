package model.wall;

import java.util.ArrayList;

import model.squareobject.Key;
import model.squareobject.SquareObject;

public class Door extends Wall {

	public Door(Boolean isHorizontal) {
		super(isHorizontal);
	}
	
	@Override
	public boolean canCross(ArrayList<SquareObject> possession) {
		for(SquareObject o : possession){
			if(o instanceof Key) 
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return isHorizontal ? "-d-" : "d";
	}

}
