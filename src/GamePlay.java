
// TODO: Auto-generated Javadoc
/**
 * The Class GamePlay.
 */
public class GamePlay {
	
	/** The score. */
	private int score;
	
	/** The board. */
	private Board board;
	
	/** The level. */
	private Level level;
	
	/** The movements left. */
	private int movementsLeft;
	
	/** The rules. */
	private RuleEngine rules;
	
	/**
	 * Instantiates a new game play.
	 *
	 * @param level the level
	 */
	public GamePlay(Level level) {
		rules = RuleEngine.getInstance();
		this.level = level;
		score = 0;
		movementsLeft = level.getPossibleMovements();
		board = level.getBoard();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Gets the level id.
	 *
	 * @return the level id
	 */
	public int getLevelId() {
		return level.getLevelId();
	}
	
	/**
	 * Gets the movements left.
	 *
	 * @return the movements left
	 */
	public int getMovementsLeft() {
		return movementsLeft;
	}
	/*
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
/**
	 * Tries to swap the objects in two cells specified by their coordinates.
	 * If swap becomes successful, returns true. Otherwise, returns false
	 *
	 * @author cgumeli
	 * @param x1 the x1
	 * @param y1 the y1
	 * @param x2 the x2
	 * @param y2 the y2
	 * @return true, if successful
	 * @custom.requires board field is non-null,rules field is nonnull, RuleEngine responds correctly.
	 * @ensures if swap is verified, then cells in the board object is updated
	 * returned true if swap is successful, false otherwise.
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

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/*
	 * Can:
	 * These code may be refactored to an updater class later
	 */
	/**
	 * Update board.
	 */
	public void updateBoard() {
		//generate scaling matrix
		MatchingScaleInformer[][] scaleMatrix = generateScaleMatrix();
		
		//erase all matched cells
		eraseAllMatches(scaleMatrix);
		
		//update the score;
		score = calculateScore(scaleMatrix);
		
		//Checking if the board is playable
		if(isThereAvailableMove()) System.out.println("\nBoard is playable"); else System.out.println("\nBoard is NOT playable");
		
	}

	/**
	 * Generate scale matrix.
	 *
	 * @return the matching scale informer[][]
	 */
	private MatchingScaleInformer[][] generateScaleMatrix() {
		
		MatchingScaleInformer[][] scaleMatrix = new MatchingScaleInformer[board.getHeight()][board.getWidth()];
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				scaleMatrix[j][i]= rules.getMatchingScaleInformer(board, i, j, board.cellAt(i, j).getCurrentObject());	
			}
		}
		return scaleMatrix;
	}
	
	/**
	 * Erase all matches.
	 *
	 * @param scaleMatrix the scale matrix
	 */
	private void eraseAllMatches(MatchingScaleInformer[][] scaleMatrix) {
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				if (rules.shouldErased(scaleMatrix[j][i])) {
					board.fillCellAt(i, j, new Nothing());
				}
			}
		}
	}
	
	/**
	 * Calculate score.
	 *
	 * @param msi the msi
	 * @return the int
	 */
	private int calculateScore(MatchingScaleInformer[][] msi)
	{
		int score = 0;
		for (int i = 0; i < msi.length; i++) {
			for (int j = 0; j < msi[0].length; j++) {
				score += rules.score(msi[i][j]);
			}
		}
		return score;
	}

	/**
	 * Drop.
	 *
	 * @param checkCode the check code
	 * @param i the i
	 * @param j the j
	 */
	public void dropAll() {
		for (int i = board.getWidth()-1; i > -1; i--) {
			for (int j = board.getHeight()-1; j > -1; j--) {
				if(board.cellAt(i, j).getCurrentObject().getType().equals("Empty")){
					int temp = j;
					while(board.cellAt(i, temp).getCurrentObject().getType().equals("Empty") && temp > 0){
						temp--;	
					}
					if(!board.cellAt(i, temp).getCurrentObject().getType().equals("Empty")){
						String type = board.cellAt(i, temp).getCurrentObject().getType();
						board.fillCellAt(i, temp, new Nothing());
						board.fillCellAt(i, j, new Lokum(type));
						temp =j;
					}
				}
			}
		}

	}

	private boolean isThereAvailableMove() {
		for (int i = 0; i < board.getWidth()-1; i++) {
			for (int j = 0; j < board.getHeight()-1; j++) {
				if(rules.isSwappable(board, i, j,i+1, j)) return true;
				if(rules.isSwappable(board, i, j,i+1, j+1)) return true;
				if(rules.isSwappable(board, i, j,i, j+1)) return true;
			}
		}
		
		return false;
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

	/**
	 * Rep ok.
	 *
	 * @return true, if successful
	 */
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

	/**
	 * Movements left.
	 *
	 * @return the string
	 */
	public String movementsLeft() {
		return "Movements Left: " + movementsLeft;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "\nGAME INFO \n" + "Game Score: " + score + "\n" + "Level ID: "
				+ level.getLevelId() + "\n" + "Movements Left: "
				+ movementsLeft + "\n" + "is board ok? " + board.repOk();
	}
	
	/*Old matching code
	 * MatchingScaleInformer info = scalingMatrix[i][j];
	
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
