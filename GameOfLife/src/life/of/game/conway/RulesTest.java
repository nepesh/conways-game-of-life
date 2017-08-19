package life.of.game.conway;

import static org.junit.Assert.*;
import org.junit.Test;
import life.of.game.conway.GameRules;

public class RulesTest {
	
	boolean[][] currentState;
	
	
    int x, y, xcellsize, ycellsize;
	int[][] neighbours;
	
	@Test
	public void testGameRuleOne3LiveCellsState() {
		GameRules gameRule = new GameRules();
		currentState = new boolean[][]{
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 	      { false, false, false, false, false, true, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  }
	 		};
		x=5;
		y=5;
		xcellsize = 10;
		ycellsize = 10;
		gameRule.setCurrentStateList(currentState);
		neighbours = gameRule.calculateNeighbour(x, y, xcellsize, ycellsize);
		gameRule.countLiveCells(neighbours);
		assertEquals(true,gameRule.gameRuleOne());
	}
	@Test
	public void testGameRuleOne2LiveCellState() {
		GameRules gameRule = new GameRules();
		currentState = new boolean[][]{
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, false, false, false, false, false  },
	 		  { false, false, false, false, true, false, false, false, false, false  },
	 	      { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  }
	 		};
		x=5;
		y=5;
		xcellsize = 10;
		ycellsize = 10;
		gameRule.setCurrentStateList(currentState);
		neighbours = gameRule.calculateNeighbour(x, y, xcellsize, ycellsize);
		gameRule.countLiveCells(neighbours);
		assertEquals(false,gameRule.gameRuleOne());
	}
	
	@Test
	public void testGameRuleTwoAndThree2LiveCellState() {
		GameRules gameRule = new GameRules();
		currentState = new boolean[][]{
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 	      { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  }
	 		};
		x=5;
		y=5;
		xcellsize = 10;
		ycellsize = 10;
		gameRule.setCurrentStateList(currentState);
		neighbours = gameRule.calculateNeighbour(x, y, xcellsize, ycellsize);
		gameRule.countLiveCells(neighbours);
		assertEquals(true,gameRule.gameRuleTwoAndThree());
	}
	@Test
	public void testGameRuleFour3LiveCellState() {
		GameRules gameRule = new GameRules();
		currentState = new boolean[][]{
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, true, true, true, true, false, false, false, false  },
	 	      { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  }
	 		};
		x=5;
		y=5;
		xcellsize = 10;
		ycellsize = 10;
		gameRule.setCurrentStateList(currentState);
		neighbours = gameRule.calculateNeighbour(x, y, xcellsize, ycellsize);
		gameRule.countLiveCells(neighbours);
		assertEquals(true,gameRule.gameRuleTwoAndThree());
	}
	
	@Test
	public void testGetFutureCellStateCurrentStateFalse() {
		GameRules gameRule = new GameRules();
		currentState = new boolean[][]{
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, true, false, true, true, false, false, false, false  },
	 	      { false, false, false, false, false, true, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  }
	 		};
		x=5;
		y=5;
		xcellsize = 10;
		ycellsize = 10;
		gameRule.setCurrentStateList(currentState);
		neighbours = gameRule.calculateNeighbour(x, y, xcellsize, ycellsize);
		gameRule.setpossibleFutureStateListSize(xcellsize, ycellsize);
		gameRule.countLiveCells(neighbours);
		gameRule.calculateFutureCellState(gameRule, x, y);
		assertEquals(true,gameRule.getPossibleFutureState(x, y));
	}
	@Test
	public void testGetFutureCellStateCurrentStateTrue() {
		GameRules gameRule = new GameRules();
		currentState = new boolean[][]{
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, true, false, true, true, false, false, false, false  },
	 	      { false, false, false, false, true, true, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  }
	 		};
		x=5;
		y=5;
		xcellsize = 10;
		ycellsize = 10;
		gameRule.setCurrentStateList(currentState);
		neighbours = gameRule.calculateNeighbour(x, y, xcellsize, ycellsize);
		gameRule.setpossibleFutureStateListSize(xcellsize, ycellsize);
		gameRule.countLiveCells(neighbours);
		gameRule.calculateFutureCellState(gameRule, x, y);
		assertEquals(true,gameRule.getPossibleFutureState(x, y));
	}
	@Test
	public void testGetFutureCellStateCurrentStateTrueFutureStateFalse() {
		GameRules gameRule = new GameRules();
		currentState = new boolean[][]{
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, true, false, true, true, false, false, false, false  },
	 	      { false, false, false, false, true, true, false, false, false, false },
	 		  { false, false, false, false, false, true, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  }
	 		};
		x=5;
		y=5;
		xcellsize = 10;
		ycellsize = 10;
		gameRule.setCurrentStateList(currentState);
		neighbours = gameRule.calculateNeighbour(x, y, xcellsize, ycellsize);
		gameRule.setpossibleFutureStateListSize(xcellsize, ycellsize);
		gameRule.countLiveCells(neighbours);
		gameRule.calculateFutureCellState(gameRule, x, y);
		assertEquals(false,gameRule.getPossibleFutureState(x, y));
	}
	@Test
	public void testGetFutureCellStateCurrentStateFalseFutureStateFalse() {
		GameRules gameRule = new GameRules();
		currentState = new boolean[][]{
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, true, false, true, true, false, false, false, false  },
	 	      { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, false, false, false, false, false, false, false },
	 		  { false, false, false, true, false, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  },
	 		  { false, false, false, false, true, true, false, false, false, false  }
	 		};
		x=5;
		y=5;
		xcellsize = 10;
		ycellsize = 10;
		gameRule.setCurrentStateList(currentState);
		neighbours = gameRule.calculateNeighbour(x, y, xcellsize, ycellsize);
		gameRule.setpossibleFutureStateListSize(xcellsize, ycellsize);
		gameRule.countLiveCells(neighbours);
		gameRule.calculateFutureCellState(gameRule, x, y);
		assertEquals(false,gameRule.getPossibleFutureState(x, y));
	}
}
