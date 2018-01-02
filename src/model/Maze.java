package model;

import java.util.ArrayList;

import model.squareobject.End;
import model.squareobject.Pickable;
import model.squareobject.Start;

/**
 * This class represents the maze.
 * @author Felicián
 *
 */
public class Maze {
	// This double list represents the squares of the maze
	private ArrayList<ArrayList<Square>> squares = new ArrayList<>();
	
	// The position of the player
	private int playerX = 0;
	private int playerY = 0;
	
	// The steps taken by the player
	private int playerSteps = 0;
	
	// The possession of the player
	private ArrayList<Pickable> possession = new ArrayList<>();
	
	// The name of the maze
	private String mazeName;

	public Maze(String mazeName, ArrayList<ArrayList<Square>> newSquares) {
		this.mazeName = mazeName;
		this.squares = newSquares;
		for (int i = 0; i < squares.size(); i++) {
			for (int j = 0; j < squares.get(i).size(); j++) {
				if (squares.get(i).get(j).getObject() instanceof Start) {
					playerX = i;
					playerY = j;
				}
			}
		}
	}

	public Maze(Maze original) {
		this.playerX = original.playerX;
		this.playerY = original.playerY;
		this.squares = original.squares;
		this.playerSteps = original.playerSteps;
		this.possession = new ArrayList<>(original.possession);
	}

	/**
	 * Makes a step with the player in the maze.
	 * @param step the step of the player. Can be: 
	 * 		w - up
	 * 		a - left
	 * 		s - down
	 * 		d - right
	 *  If other character is given than no steps will be made. 
	 *	
	 */
	public void makeStep(char step) {
		playerSteps++;
		switch (step) {
		case 'w':
			if (squares.get(playerX).get(playerY).northWall.canCross(possession)) {
				playerX++;
			}
			break;
		case 'a':
			if (squares.get(playerX).get(playerY).westWall.canCross(possession)) {
				playerY--;
			}
			break;
		case 's':
			if (squares.get(playerX).get(playerY).southWall.canCross(possession)) {
				playerX--;
			}
			break;
		case 'd':
			if (squares.get(playerX).get(playerY).eastWall.canCross(possession)) {
				playerY++;
			}
			break;
		}
		if (squares.get(playerX).get(playerY).getObject() != null
				&& squares.get(playerX).get(playerY).getObject() instanceof Pickable) {
			possession.add((Pickable) squares.get(playerX).get(playerY).getObject());
		}
	}

	/**
	 * 
	 * @return true if the player have reached the end of the {@link Maze}
	 */
	public boolean isEndReached() {
		if (squares.get(playerX).get(playerY).getObject() instanceof End) {
			return true;
		}
		return false;
	}

	public int getPlayerSteps() {
		return playerSteps;
	}
	
	public String getMazeName() {
		return mazeName;
	}

	@Override
	public String toString() {
		StringBuffer mazeString = new StringBuffer();
		mazeString.append('\n');
		for (int i = squares.size() - 1; i >= 0; i--) {
			for (int j = 0; j < squares.get(i).size(); j++) {
				mazeString.append('+');
				mazeString.append(squares.get(i).get(j).getNorthWall().toString());
			}
			mazeString.append("+");
			mazeString.append('\n');
			for (int j = 0; j < squares.get(i).size(); j++) {
				mazeString.append(squares.get(i).get(j).getWestWall().toString());
				if (i == playerX && j == playerY) {
					mazeString.append(" P ");
				} else {
					mazeString.append(squares.get(i).get(j).toString());
				}
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
		}
		mazeString.append('\n');
		mazeString.append("Your possession contains: ");
		for (Pickable o : possession) {
			mazeString.append(o.getName() + ", ");
		}
		if (!possession.isEmpty())
			mazeString.delete(mazeString.length() - 2, mazeString.length());
		mazeString.append('\n');
		mazeString.append("Steps: ");
		mazeString.append(playerSteps);
		return mazeString.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Maze)) {
			return false;
		}
		return (playerX == ((Maze) obj).playerX && playerY == ((Maze) obj).playerY);
	}

}
