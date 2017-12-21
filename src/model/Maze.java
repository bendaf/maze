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
		for (int i = squares.size()-1; i >= 0; i--) {
			for (int j = 0; j < squares.get(i).size(); j++) {
				mazeString.append('+');
				mazeString.append(squares.get(i).get(j).getNorthWall().toString());
			}
			mazeString.append("+");
			mazeString.append('\n');
			for (int j = 0; j < squares.get(i).size(); j++) {
				mazeString.append(squares.get(i).get(j).getWestWall().toString());
				mazeString.append(squares.get(i).get(j).toString());
			}
			if (squares.get(i).size() > 0) {
				mazeString.append(squares.get(i).get(squares.get(i).size() - 1).getEastWall().toString());
				mazeString.append('\n');
			}
		}
		if (squares.size() > 0) { 
			for (int i = 0; i < squares.get(0).size(); i++) {
				mazeString.append('+');
				mazeString.append(squares.get(0).get(i).getSouthWall().toString());
			}
			mazeString.append("+");
			mazeString.append('\n');
		}
		return mazeString.toString();
	}
}
