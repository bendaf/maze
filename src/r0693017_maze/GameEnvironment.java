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
import player.Player;
import player.RandomPlayer;

public class GameEnvironment {

	public static void main(String[] args) {
		System.out.println("Hi, this is the MazeGame!");

		Scanner scanner = new Scanner(System.in);
		// Select player

		Player player = getPlayer(scanner);

		newGameWithPlayer(player, scanner);
		scanner.close();
	}

	public static void newGameWithPlayer(Player player, Scanner scanner) {
		// Read the maze name
		String mazeName = getMazeName(scanner);

		// Initialization
		Maze maze = readMaze(mazeName + ".txt");
		ArrayList<Maze> history = new ArrayList<>();

		// Game
		char step = 'q';
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

		// Write to high score
		writeHighScore(mazeName, player, maze);

		// End
		if (step != 'q') {

			System.out.println("Thank you for playing " + player.getName() + "." + " Your score is "
					+ maze.getPlayerSteps() + ".");
			System.out.println("Would you like to play an another game? (y - yes, else - no)");
			String in = scanner.nextLine();
			if (in.equals("y") || in.equals("yes")) {
				newGameWithPlayer(player, scanner);
			}
		}
	}

	public static Maze readMaze(String fileName) {
		try (BufferedReader is = new BufferedReader(new FileReader(new File(fileName)))) {
			return new MazeSerializer().deserializeMaze(is);
		} catch (IOException e) {
			return null;
		}
	}

	public static Player getPlayer(Scanner scanner) {
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

	public static String getMazeName(Scanner scanner) {
		System.out.println("In which maze would you like to play?");
		System.out.println("1 - BaseMaze" + System.lineSeparator() + "2 - MidiMaze");
		Integer in = 0;
		while (in < 1 || in > 2) {
			try {
				in = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please write 1 or 2 and press the enter");
			}
			scanner.nextLine();
		}
		if (in == 1) {
			return "BaseMaze";
		}
		return "MidiMaze";
	}

	private static void writeHighScore(String mazeName, Player player, Maze maze) {
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
				out.append(player.getName() + "," + mazeName + "," + maze.getPlayerSteps() + System.lineSeparator());
				out.close();
			}
		} catch (IOException e) {
			System.out.println("High Score log error");
		}
	}
}
