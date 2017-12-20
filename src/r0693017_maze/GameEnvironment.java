package r0693017_maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.Maze;

public class GameEnvironment {

	public static Maze readMaze(String fileName) {
		try (BufferedReader is = new BufferedReader(new FileReader(new File(fileName)))) {
			return new MazeSerializer().deserializeMaze(is);
		} catch (IOException e) {
			return null;
		}
	}

	public static void main(String[] args) {
		Maze baseMaze = readMaze("BaseMaze.txt");
		try{
			System.out.println(baseMaze.toString());
		}catch (Exception e) {
			System.out.println("Error");
		}
	}

}
