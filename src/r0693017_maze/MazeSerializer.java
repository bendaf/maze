package r0693017_maze;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Maze;
import model.Square;
import model.squareobject.End;
import model.squareobject.Hammer;
import model.squareobject.Key;
import model.squareobject.SquareObject;
import model.squareobject.Start;
import model.wall.BreakableWall;
import model.wall.Door;
import model.wall.FakeWall;
import model.wall.NoWall;
import model.wall.Wall;

/**
 * This class can deserialize the {@link Maze}
 * 
 * @author Felicián
 *
 */
public class MazeSerializer {

	/**
	 * Deserialize the {@link Maze} from an input {@link BufferedReader}.
	 * 
	 * @param mazeName
	 *            The name of the maze which will be serialized.
	 * @param mazeReader
	 *            A {@link BufferedReader} which will read in the {@link Maze}
	 * @return The initialized {@link Maze} object.
	 * @throws IOException
	 */
	public Maze deserializeMaze(String mazeName, BufferedReader mazeReader) throws IOException {
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
		Maze newMaze = new Maze(mazeName, newSquares);
		return newMaze;
	}

	/**
	 * Initializes a {@link Wall} object from the wallType string and the type
	 * of the wall. In default it returns a horizontal {@link NoWall}.
	 * 
	 * @param wallType
	 *            {@link String} which can be "wall", "breakable", "fake",
	 *            "door" or "no".
	 * @param isHorizontal weather the wall is a horizontal or vertical {@link Wall}
	 * @return a {@link Wall} object.
	 */
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

	/**
	 * Initializes a {@link SquareObject} from the square type string. If the square is empty it returns null
	 * @param objectType {@link String} which can be "S", "start", "E", "end", "key" and "hammer". 
	 * @return an initialized {@link SquareObject}
	 */
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
