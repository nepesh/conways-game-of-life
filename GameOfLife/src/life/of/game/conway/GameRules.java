package life.of.game.conway;

public class GameRules{
	
	boolean[][] possibleFutureState = new boolean[10][10];
	boolean[][] currentStateList = new boolean[10][10];
	int[][] neighbour = new int[8][2];
	int liveCellCount = 0;
	
	public GameRules() {
		
		super();
	}
	
    void countLiveCells(int[][] neighbour) {
    	    liveCellCount = 0;
    	    int count = 0;
    	    do {
    	    	  if (getNeigbourCurrentState(neighbour[count][0],neighbour[count][1])) {
    	    		 liveCellCount++; 
    	    	  }
    	    	  count++;
    	    }while(count<=7);
    	        	
    }
    
	boolean gameRuleOne() {
        if(liveCellCount<2) {
        	  return false;
        }
		return true;

	}
	
	boolean gameRuleTwoAndThree() {
        if(liveCellCount==2 || liveCellCount ==3) {
        	  return true;
        }
		return false;

	}
	
	boolean gameRuleFour() {
	
        if(liveCellCount == 3) {
        	  return true;
        }
		return false;

	}
	int[][] calculateNeighbour(int x, int y) {
		int[][] neighbours = new int[8][2]; 
		int countx = 0;
		
		for (int neighbourx = x-1; neighbourx <= x+ 1; neighbourx++) {
			for(int neighboury = y-1; neighboury <= y + 1; neighboury++) {
				if(neighbourx != x || neighboury != y) {
					if(neighbourx<0) {
						neighbours[countx][0] = 9;	
					}else if(neighbourx>9){
						neighbours[countx][0] = 0;	
					}else {
						neighbours[countx][0] = neighbourx;	
					}
					if(neighboury<0) {
						neighbours[countx][1] = 9;
					}else if(neighboury>9){
						neighbours[countx][1] = 0;
					}else {
						neighbours[countx][1] = neighboury;	
					}
					
					countx++;
				}
				
			}
		}
		
		return neighbours;

	}

	boolean getNeigbourCurrentState(int x, int y) {

		return currentStateList[x][y];

	}
	boolean getCellsCurrentState(int x, int y) {

		return getNeigbourCurrentState(x, y);

	}
	
	
	void setPossibleFutureState(int x, int y, boolean state){
		
		possibleFutureState[x][y] = state;
		
	}
	boolean[][] getPossibleFutureList(){
		
		return possibleFutureState;
		
	}
	void setCurrentStateList(boolean[][] currentState){
			
			currentStateList = currentState;
			
		}
}
