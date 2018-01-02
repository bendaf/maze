package player;

import java.util.ArrayList;

import model.Maze;

public class DFGPlayer implements Player {
	ArrayList<Maze> previousStates = new ArrayList<>();
	public static final Character[] STEPLIST = { 'w', 'a', 's', 'd' };

	@Override
	public char getStep(Maze state) {
		previousStates.add(new Maze(state));
		Character finalStep = null;
		Maze TestState = new Maze(state);
		for (char step : STEPLIST) {
			TestState = new Maze(state);
			TestState.makeStep(step);
			if (!previousStates.contains(TestState)) {
				finalStep = step;
				break;
			}
		}
		if (finalStep == null) {
			finalStep = 'b';
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
