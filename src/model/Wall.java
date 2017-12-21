package model;

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

}
