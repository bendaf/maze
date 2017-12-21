package model;

public class Door extends Wall {

	public Door(Boolean isHorizontal) {
		super(isHorizontal);
	}

	@Override
	public String toString() {
		return isHorizontal ? "-d-" : "d";
	}

}
