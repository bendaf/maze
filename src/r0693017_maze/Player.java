package r0693017_maze;

import model.Maze;

public interface Player {
	char getStep(Maze state);
	String getName();
}
