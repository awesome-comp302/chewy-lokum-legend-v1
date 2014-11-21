import java.util.HashMap;




public class GamePlay {
	private int score;
	private Board board;
	private Level level;
	private int movementsLeft;
	private HashMap<Integer, Integer> swapVerificationMap;
	
	public GamePlay(Level level) {
		this.level = level;
		score = 0;
		movementsLeft = level.getPossibleMovements();
		board = level.getBoard();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param x1 : x of the first cell (0...width-1)
	 * @param y1: y coordinate of the first cell (0...height-1)
	 * @param x2: x of the second cell(from 0...width-1)
	 * @param y2: x
	 * @return
	 */
	public boolean swap(int x1, int y1, int x2, int y2) {
		RuleEngine rules = RuleEngine.getInstance();
		swapVerificationMap = rules.getVerificationMap(x1, y1, x2, y2, board);
		if (!rules.isVerified(swapVerificationMap)) {
			return false;
		}
		Cell cell1 = board.cellAt(x1, y1);
		Cell cell2 = board.cellAt(x2, y2);
		board.fillCellAt(x1, y1, cell2.getCurrentObject());
		board.fillCellAt(x2, y2, cell1.getPrevious());
		movementsLeft--;
		return true;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	
	public void updateBoard() {
		
	}
	
	public boolean repOk() {
		//inspects whether the constructor did its job
		if(movementsLeft < 0)
			return false;
		
		if(level == null || !level.repOk())
			return false;
		
		if(board == null || !board.repOk())
			return false;
		
		
		return true;
	}
	
	public String movementsLeft(){
		return "Movements Left: " +movementsLeft;
	}
	
	@Override
	public String toString() {

		return  "\nGAME INFO \n"+
				"Game Score: " +score+ "\n"+
				"Level ID: " +level.getLevelId()+ "\n"+
				"Movements Left: " +movementsLeft+ "\n"+
				"is board ok? "+board.repOk();
	}

}
