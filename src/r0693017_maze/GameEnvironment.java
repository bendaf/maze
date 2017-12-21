package r0693017_maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import r0693017_maze.HumanPlayer;

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
		Maze baseMaze = readMaze("MidiMaze.txt");
		ArrayList<Maze> history = new ArrayList<>();
		Player p = new HumanPlayer();
		try {
			while(!baseMaze.isEndReached()){
				char step = 'q';
				step = p.getStep(baseMaze);
				if(step == 'q') break;
				if(step == 'b'){
					baseMaze = history.remove(history.size()-1);
				}else{
					history.add(new Maze(baseMaze));
					baseMaze.makeStep(step);
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace(System.out);
		}
	}

}
