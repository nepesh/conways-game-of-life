package life.of.game.conway;

import java.util.Arrays;

public class MainController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] neighbourList = new int[8][2];
		boolean[][] futureState = new boolean[10][10];
		boolean[][] currentState = new boolean[][]{
			  { true, true, false, true, false, false, true, true, true, false },
			  { true, false, false, false, false, true, true, true, true, false },
			  { false, false, false, true, true, true, true, true, true, false  },
			  { false, false, false, true, false, false, true, true, true, false  },
			  { true, true, false, true, false, false, true, true, true, false  },
			  { true, true, false, true, false, false, true, true, true, false },
			  { true, false, false, false, false, true, true, true, true, false },
			  { false, false, false, true, true, true, true, true, true, false  },
			  { false, false, false, true, false, false, true, true, true, false  },
			  { true, true, false, true, false, false, true, true, true, false  }
			};
		
		GameRules game = new GameRules();
		game.setCurrentStateList(currentState);
		for (int x=0;x<=9;x++) {
			for (int y=0;y<=9;y++) {
				neighbourList = game.calculateNeighbour(x, y);
				game.countLiveCells(neighbourList);
				if (game.getNeigbourCurrentState(x, y)) {
					if (game.gameRuleOne() && game.gameRuleTwoAndThree()) {
						game.setPossibleFutureState(x, y, true);
					} else {
						game.setPossibleFutureState(x, y, false);
					}
				} else {
					if (game.gameRuleFour()) {
						game.setPossibleFutureState(x, y, true);
					} else {
						game.setPossibleFutureState(x, y, false);
					}
				}
				y++;
			}
			x++;
		}

		futureState = game.getPossibleFutureList();
		System.out.println(Arrays.deepToString(currentState));
		currentState = futureState;
		System.out.println(Arrays.deepToString(currentState));

	}

	

}
