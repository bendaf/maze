package player;

import java.util.Random;

import model.Maze;

public class RandomPlayer implements Player {

	private Random rand;

	public RandomPlayer() {
		this.rand = new Random(System.currentTimeMillis());
	}

	@Override
	public char getStep(Maze state) {

		switch (rand.nextInt(4)) {
		case 0:
			return 'w';
		case 1:
			return 'a';
		case 2:
			return 's';
		case 3:
			return 'd';
		}
		return 'q';
	}

	@Override
	public String getName() {
		return "RandomPlayer";
	}

}
