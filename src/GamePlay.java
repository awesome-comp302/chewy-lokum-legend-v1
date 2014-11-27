
public class GamePlay {
	private int score;
	private Board board;
	private Level level;
	private int movementsLeft;
	private RuleEngine rules;
	
	public GamePlay(Level level) {
		rules = RuleEngine.getInstance();
		this.level = level;
		score = 0;
		movementsLeft = level.getPossibleMovements();
		board = level.getBoard();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @requires repOk, 
	 * 			RuleEngine have predicates gameEndedByMovements(int) and
	 * 			isSwappable(Board b, int x1, int y1, int x2, int y2)
	 * @param x1
	 *            : x of the first cell (0...width-1)
	 * @param y1
	 *            : y coordinate of the first cell (0...height-1)
	 * @param x2
	 *            : x of the second cell(from 0...width-1)
	 * @param y2
	 *            : x
	 * @postconditions: swap operations should have done if returned true
	 * @return
	 */
	public boolean swap(int x1, int y1, int x2, int y2) {
		if (rules.gameEndedByMovements(movementsLeft)) {
			return false;
		}
		
		if (!rules.isSwappable(board, x1, y1, x2, y2)) {
			return false;
		}
		
		Cell cell1 = board.cellAt(x1, y1);
		Cell cell2 = board.cellAt(x2, y2);
		ChewyObject temp = cell1.getCurrentObject();
		board.fillCellAt(x1, y1, cell2.getCurrentObject());
		board.fillCellAt(x2, y2, temp);
		movementsLeft--;
		return true;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	/*
	 * Can:
	 * These code may be refactored to an updater class later
	 */
	public void updateBoard() {
		erase();
	}

	
	private void erase() {
		MatchingScaleInformer[][] scalingMatrix = new MatchingScaleInformer[board.getHeight()][board.getWidth()];
		
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				scalingMatrix[j][i]= rules.getMatchingScaleInformer(board, i, j, board.cellAt(i, j).getCurrentObject());
				System.err.println("x:" + i + " y:" + j + " lokum: " + board.cellAt(i, j));
				/*System.err.println(info);
				if (info.horizontalMatchTotalScale() >= RuleEngine.MINIMUM_MATCH_REQUIRED 
						|| info.verticalMatchTotalScale() >= RuleEngine.MINIMUM_MATCH_REQUIRED) {
					board.fillCellAt(i, j, new Nothing());
				}
				
				//left
				for (int k = 1; k <= info.getLeftScale(); k++) {
					board.fillCellAt(i-k, j, new Nothing());
				}
				
				//right
				for (int k = 1; k <= info.getRightScale(); k++) {
					board.fillCellAt(i+k, j, new Nothing());
				}
				
				//up
				for (int k = 1; k <= info.getUpScale(); k++) {
					board.fillCellAt(i, j-k, new Nothing());
				}
				
				//down
				for (int k = 1; k <= info.getDownScale(); k++) {
					board.fillCellAt(i, j+k, new Nothing());
				}*/
				
			}
		}
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				System.out.print(scalingMatrix[i][j] + "   ");
			}
			System.out.println();
		}
		
	}

	private void drop(int checkCode, int i, int j) {
		//find emptied place
		//drop the ones above
	}
	
	

	/*private void erase() {
		boolean eraseMatrix[][] = new boolean[board.getHeight()][board.getWidth()];
		
		for (int r = 0; r < eraseMatrix.length; r++) {
			for (int c = 0; c < eraseMatrix[r].length; c++) {
				eraseMatrix[r][c] = rules.check(board, c, r, board.cellAt(c, r)) != RuleEngine.NO_MATCH;
			}
		}
		
		for (int r = 0; r < eraseMatrix.length; r++) {
			for (int c = 0; c < eraseMatrix[r].length; c++) {
				if (eraseMatrix[r][c]) {
					board.fillCellAt(c, r, new Nothing());
				}
			}
		}
		
	}*/

	public boolean repOk() {
		// inspects whether the constructor did its job
		if (movementsLeft < 0)
			return false;

		if (level == null || !level.repOk())
			return false;

		if (board == null || !board.repOk())
			return false;

		return true;
	}

	public String movementsLeft() {
		return "Movements Left: " + movementsLeft;
	}

	@Override
	public String toString() {

		return "\nGAME INFO \n" + "Game Score: " + score + "\n" + "Level ID: "
				+ level.getLevelId() + "\n" + "Movements Left: "
				+ movementsLeft + "\n" + "is board ok? " + board.repOk();
	}

}
