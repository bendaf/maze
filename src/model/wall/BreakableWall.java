package model.wall;

public class BreakableWall extends Wall {

	public BreakableWall(boolean isHorizontal) {
		super(isHorizontal);
	}

	@Override
	public String toString() {
		return isHorizontal ? "-b-" : "b";
	}

}
