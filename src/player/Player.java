package player;

import model.Maze;

public interface Player {
	char getStep(Maze state);
	String getName();
}
