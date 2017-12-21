package r0693017_maze;

import java.io.IOException;

import model.Maze;

public class HumanPlayer extends Player {

	public char getStep(Maze state) {
		char c = 'q';
		try {
			System.out.println(state.toString());
			do {
				if(c != '\n' && c != '\r'){
					System.out.println("Please select w (up), a(left), s(down), d(right), b(back), q(quit)."
							+ " Multiple steps are possible.");
				}
				c = (char) System.in.read();
			} while (c != 'w' && c != 'a' && c != 's' && c != 'd' && c != 'q' && c != 'b');
			System.out.println(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return c;
	}
}
