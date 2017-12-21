package r0693017_maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import model.BreakableWall;
import model.Door;
import model.End;
import model.FakeWall;
import model.Hammer;
import model.Key;
import model.Maze;
import model.NoWall;
import model.Square;
import model.SquareObject;
import model.Start;
import model.Wall;

public class MazeSerializer {
	public Maze deserializeMaze(BufferedReader mazeReader) throws IOException {
		String line = mazeReader.readLine();
		ArrayList<ArrayList<Square>> newSquares = new ArrayList<>();
		while ((line = mazeReader.readLine()) != null) {
			String[] splitted = line.split(",");
			int xCoord = Integer.parseInt(splitted[0]);
			int yCoord = Integer.parseInt(splitted[1]);
			Square newSquare = new Square(getWall(splitted[2], true), getWall(splitted[3], true),
					getWall(splitted[4], false), getWall(splitted[5], false), getObject(splitted[6]));

			while (newSquares.size() <= xCoord) {
				ArrayList<Square> newRow = new ArrayList<>();
				newSquares.add(newRow);
			}
			while (newSquares.get(xCoord).size() <= yCoord) {
				newSquares.get(xCoord).add(new Square());
			}

			newSquares.get(xCoord).set(yCoord, newSquare);

		}
		Maze newMaze = new Maze(newSquares);
		return newMaze;
	}

	private Wall getWall(String wallType, Boolean isHorizontal) {
		if (wallType.equals("wall"))
			return new Wall(isHorizontal);
		else if (wallType.equals("breakable")) {
			return new BreakableWall(isHorizontal);
		} else if (wallType.equals("fake")) {
			return new FakeWall(isHorizontal);
		} else if (wallType.equals("door")) {
			return new Door(isHorizontal);
		}
		return new NoWall(isHorizontal);
	}

	private SquareObject getObject(String objectType) {
		if (objectType.equals("S") || objectType.equals("start")) {
			return new Start();
		} else if (objectType.equals("E") || objectType.equals("end")) {
			return new End();
		} else if (objectType.equals("key")) {
			return new Key();
		} else if (objectType.equals("hammer")) {
			return new Hammer();
		}
		return null;
	}
}
