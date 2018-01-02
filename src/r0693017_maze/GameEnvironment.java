package r0693017_maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Maze;
import player.DFGPlayer;
import player.HumanPlayer;
import player.PlayerInterface;
import player.RandomPlayer;

/**
 * @author Felicián
 * This is the main class running the game
 */
public class GameEnvironment {

	public static void main(String[] args) {
		System.out.println("Hi, this is the MazeGame!");
		Scanner scanner = new Scanner(System.in);

		newGame(scanner);
		
		scanner.close();
	}
	
	/**
	 * This method is for creating a new game.
	 * @param scanner is an opened scanner which supplies the input for the game.
	 */
	public static void newGame(Scanner scanner) {
		// Select player
		PlayerInterface player = getPlayer(scanner);

		// Get maze 
		Maze maze = getMaze(scanner);
		
		// Initialization
		ArrayList<Maze> history = new ArrayList<>();
		char step = 'b';
		
		if (maze != null) {
			while (!maze.isEndReached()) {
				step = player.getStep(new Maze(maze));
				if (step == 'q')
					break;
				if (step == 'b') {
					try {
						maze = history.remove(history.size() - 1);
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("You cannot go back from the start position!");
					}
				} else {
					history.add(new Maze(maze));
					maze.makeStep(step);
				}
			}
		} else {
			System.out.println("Something went wrong during the maze loading.");
		}

		// Write to high score
		writeHighScore(maze.getMazeName(), player, maze);

		// End
		System.out.println(
				"Thank you for playing " + player.getName() + "." + " Your score is " + maze.getPlayerSteps() + ".");
		if (step != 'q') {

			System.out.println("Would you like to play an another game? (y - yes, else - no)");
			String in = scanner.nextLine();
			if (in.equals("y") || in.equals("yes")) {
				newGame(scanner);
			}
		}
	}

	/**
	 * This file asks for which maze should be used in the game and reads in the maze.
	 * @param scanner is an opened scanner which supplies the input for the game.
	 * @return The initialized {@link Maze} object.
	 */
	public static Maze getMaze(Scanner scanner) {
		String fileName = getMazeName(scanner);
		try (BufferedReader is = new BufferedReader(new FileReader(new File(fileName)))) {
			return (new MazeSerializer()).deserializeMaze(fileName.substring(0,fileName.lastIndexOf(".")), is);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Selects which player should be used.
	 * @param scanner is an opened scanner which supplies the input for the game.
	 * @return The initialized player.
	 */
	public static PlayerInterface getPlayer(Scanner scanner) {
		System.out.println("With which player would you like to play?");
		System.out.println("1 - HumanPlayer" + System.lineSeparator() + "2 - RandomPlayer" + System.lineSeparator()
				+ "3 - DepthGoingPlayer");
		Integer in = 0;
		while (in < 1 || in > 3) {
			try {
				in = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please write 1 or 2 and press the enter");
			}
			scanner.nextLine();
		}
		if (in == 1) {
			return new HumanPlayer(scanner);
		} else if (in == 2) {
			return new RandomPlayer();
		}
		return new DFGPlayer();
	}

	/**	
	 * Selects the maze which will be used during the game.
	 * @param scanner is an opened scanner which supplies the input for the game.
	 * @return The name of the maze file.
	 */
	public static String getMazeName(Scanner scanner) {
		System.out.println("In which maze would you like to play?");
		System.out.println("1 - BaseMaze" + System.lineSeparator() + "2 - MidiMaze" + System.lineSeparator()
				+ "or type your maze name with the extension");
		Integer in = 0;
		String mazeName;
		while (in < 1 || in > 2) {
			try {
				in = scanner.nextInt();
			} catch (InputMismatchException e) {
				mazeName = scanner.nextLine();
				File f = new File(mazeName);
				if (!f.exists() || f.isDirectory()) {
					System.out.println("The file does not exists, please try again.");
				} else {
					return mazeName;
				}
			}
			if (in != 0) {
				scanner.nextLine();
			}
		}
		if (in == 1) {
			return "BaseMaze.txt";
		}
		return "MidiMaze.txt";
	}

	/**
	 * Write the high score to the "High Score.txt" file.
	 * @param mazeName The maze which were played.
	 * @param player The player who played.
	 * @param maze The {@link Maze} object.
	 */
	private static void writeHighScore(String mazeName, PlayerInterface player, Maze maze) {
		try {
			if (maze.isEndReached()) {
				String savestr = "High Score.txt";
				File f = new File(savestr);

				FileWriter out = null;
				if (f.exists() && !f.isDirectory()) {
					out = new FileWriter(savestr, true);
				} else {
					out = new FileWriter(savestr);
					out.append("PLAYERNAME,MAZENAME,NUMBER_OF_STEPS_SOLVED" + System.lineSeparator());

				}
				out.append(player.getName() + "," + mazeName + ","
						+ maze.getPlayerSteps() + System.lineSeparator());
				out.close();
			}
		} catch (IOException e) {
			System.out.println("High Score log error");
		}
	}
}
