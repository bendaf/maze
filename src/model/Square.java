package model;

public class Square {
	Wall northWall;
	Wall southWall;
	Wall eastWall;
	Wall westWall;
	SquareObject object;
	
	public Square(Wall northWall, Wall southWall, Wall eastWall, Wall westWall, SquareObject object) {
		super();
		this.northWall = northWall;
		this.southWall = southWall;
		this.eastWall = eastWall;
		this.westWall = westWall;
		this.object = object;
	}

	public Square() {
		super();
		this.northWall = new Wall();
		this.southWall = new Wall();
		this.eastWall = new Wall();
		this.westWall = new Wall();
	}
}
