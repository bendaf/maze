package model.wall;

public class NoWall extends Wall {

	public NoWall(boolean isHorizontal) {
		super(isHorizontal);
	}

	@Override
	public String toString() {
		return isHorizontal ? "   " : " ";
	}

}
