package model;

public class FakeWall extends Wall {

	public FakeWall(boolean isHorizontal) {
		super(isHorizontal);
	}

	@Override
	public String toString() {
		return isHorizontal ? "-f-" : "f";
	}

}
