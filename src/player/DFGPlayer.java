package player;

import java.util.ArrayList;

import model.Maze;

public class DFGPlayer implements PlayerInterface {
	// Previous steps of the player
	ArrayList<Maze> previousStates = new ArrayList<>();
	
	// The possible list of directions in which the player can move.
	public static final Character[] STEPLIST = { 'w', 'a', 's', 'd' };

	@Override
	public char getStep(Maze state) {
		Character finalStep = null;
		
		// Adds the current state to the visited locations.
		previousStates.add(new Maze(state));
		
		// Copy the current state so it will be able to modify it.
		Maze TestState = new Maze(state);
		
		// Searches the possible moves which were not made yet.
		for (char step : STEPLIST) {
			TestState = new Maze(state);
			TestState.makeStep(step);
			if (!previousStates.contains(TestState)) {
				finalStep = step;
				break;
			}
		}
		
		// If there is no more possible step from the position go back.
		if (finalStep == null) {
			finalStep = 'b';
			
			// If we are in the starting position give up.
			if (state.equals(previousStates.get(0))) {
				finalStep = 'q';
			}
		}
		return finalStep;
	}

	@Override
	public String getName() {
		return "Deep First Going Player";
	}

}
