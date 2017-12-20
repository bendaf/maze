package model;

import java.util.ArrayList;

public class Maze {
	private ArrayList<ArrayList<Square>> squares = new ArrayList<>();

	public Maze(ArrayList<ArrayList<Square>> newSquares) {
		this.squares = newSquares;
	}

	@Override
	public String toString() {
		StringBuffer mazeString = new StringBuffer();
		for(ArrayList<Square> row : squares){
			for(Square square : row){
				mazeString.append(square.toString());
			}
		}
		mazeString.append(super.toString());
		return mazeString.toString();
	}
}
