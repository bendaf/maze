package player;

import model.Maze;
import r0693017_maze.GameEnvironment;

/**
 * An interface for the players. Only Players which implement this interface can
 * play in the {@link GameEnvironment}
 * 
 * @author Felicián
 *
 */
public interface PlayerInterface {
	
	/**
	 * Makes a step in the  {@link Maze}. 
	 * @param state The {@link Maze} where the next step is made.
	 * @return The next step. It can be: 
	 * 		w - up
	 * 		a - left
	 * 		s - down
	 * 		d - right
	 * 		b - back
	 * 		q - quit
	 */
	char getStep(Maze state);

	/**
	 * 
	 * @return the name of the Player.
	 */
	String getName();
}
