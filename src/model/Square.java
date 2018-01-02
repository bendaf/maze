package model;

import model.squareobject.SquareObject;
import model.wall.Wall;

/**
 * This class represents a square of the {@link Maze}. A {@link Square} have
 * four {@link Walls} and one {@link SquareObject}
 * 
 * @author Felicián
 *
 */
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
		this.northWall = new Wall(true);
		this.southWall = new Wall(true);
		this.eastWall = new Wall(false);
		this.westWall = new Wall(false);
	}

	public Wall getNorthWall() {
		return northWall;
	}

	public Wall getSouthWall() {
		return southWall;
	}

	public Wall getEastWall() {
		return eastWall;
	}

	public Wall getWestWall() {
		return westWall;
	}

	public SquareObject getObject() {
		return this.object;
	}

	@Override
	public String toString() {
		try {
			return object.toString();
		} catch (NullPointerException e) {
			return "   ";
		}
	}
}
