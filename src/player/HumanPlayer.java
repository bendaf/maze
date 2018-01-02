package player;

import java.util.Scanner;

import model.Maze;

public class HumanPlayer implements PlayerInterface {
	// The name of the player
	String playername;
	
	// The scanner which the player will use
	Scanner scanner;

	public HumanPlayer(Scanner s) {
		this.scanner = s;
		while (this.playername == null) {
			System.out.println("Please type your name: ");
			this.playername = s.nextLine();
		}
	}

	@Override
	public char getStep(Maze state) {
		
		// Show the maze
		System.out.println(state.toString());
		
		// Read in a possible step with the scanner
		Character step = null;
		while (step == null) {
			System.out.println("Please select w (up), a(left), s(down), d(right), b(back), q(quit).");
			String input = scanner.nextLine();
			for (char c : input.toCharArray()) {
				if (c == 'w' || c == 'a' || c == 's' || c == 'd' || c == 'q' || c == 'b') {
					step = c;
					break;
				}
			}
		}
		
		// Confirm the step
		System.out.println(step);

		return step;
	}

	@Override
	public String getName() {
		return playername;
	}
}
