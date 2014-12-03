import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class GamePlay.
 */
public class GamePlay {

	/** The score of the game. */
	private int score;

	/** The board. */
	private Board board;

	/** The level. */
	private Level level;

	/** The movements left. */
	private int movementsLeft;

	/** The rules. */
	private RuleEngine rules;

	private Position successfullSwapLog[];

	/**
	 * Instantiates a new game play.
	 *
	 * @param level
	 *            : The Level played
	 * @requires:
	 * @modifies:
	 * @ensures:
	 */
	public GamePlay(Level level) {
		rules = RuleEngine.getInstance();
		this.level = level;
		score = 0;
		movementsLeft = level.getPossibleMovements();
		board = level.getBoard();
		successfullSwapLog = new Position[2];
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the score.
	 *
	 * @requires: repOk
	 * @ensures: current score data is returned
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Gets the level id.
	 *
	 * @requires: level is non null
	 * @ensures: is of the played level is returned
	 * @return the level id
	 */
	public int getLevelId() {
		return level.getLevelId();
	}

	/**
	 * Gets the movements left.
	 *
	 * @requires movementsLeft exists and initialized
	 * @ensures: current movementsLeft field is returned
	 */
	public int getMovementsLeft() {
		return movementsLeft;
	}

	/**
	 * Tries to swap the objects in two cells specified by their coordinates. If
	 * swap becomes successful, returns true. Otherwise, returns false.
	 *
	 * @requires <ul>
	 *           <li>board field is non-null</li>
	 *           <li>rules field is non-null</li>
	 *           <li>RuleEngine has instance predicate methods
	 *           gameEndedByMovements(int) and isSwappable(Board, int, int,
	 *           int).
	 *           <li>board field is non-null, contains non-null cells with
	 *           non-null ChewyObjects</li>
	 *           <li>Board has an instance method fillCellAt(int, int,
	 *           ChewyObject)</li>
	 *           <li>integer field movementsLeft is initialized</li>
	 *           </ul>
	 * @modifies if returned true;
	 *           <ul>
	 *           <li>board field</li>
	 *           <li>level field</li>
	 *           <li>movementsLeft field</li>
	 *           </ul>
	 * @ensures If swap returns true, then ChewyObjects of the cells at the
	 *          board positions (x1, y1), (x2, y2) is exchanged.<br>
	 *          Otherwise, nothing will be changed.
	 */

	public boolean swap(int x1, int y1, int x2, int y2) {
		if (rules.gameEndedByMovements(movementsLeft)) {
			return false;
		}

		if (!rules.isSwappable(board, x1, y1, x2, y2)) {
			return false;
		}

		successfullSwapLog[0] = new Position(x1, y1);
		successfullSwapLog[1] = new Position(x2, y2);
		Cell cell1 = board.cellAt(x1, y1);
		Cell cell2 = board.cellAt(x2, y2);
		ChewyObject temp = cell1.getCurrentObject();
		board.fillCellAt(x1, y1, cell2.getCurrentObject());
		board.fillCellAt(x2, y2, temp);
		movementsLeft--;
		return true;
	}

	/**
	 * Returns the level played.
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 * 
	 * @requires GamePlay has a field named Level of type Level
	 * @modifies: level
	 * @param level
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/*
	 * Can: These code may be refactored to an updater class later
	 */
	/**
	 * @requires MatchingScaleInformer class exists and visible to GamePlay.
	 * @modifies <ul>
	 *           <li>board</li>
	 *           <li>level</li>
	 *           <li>score</li>
	 *           </ul>
	 * @ensures <ul>
	 *          <li>
	 */
	public void updateBoard() {
		// generate scaling matrix
		MatchingScaleInformer[][] scaleMatrix = generateScaleMatrix();

		// erase all matched cells
		eraseAllMatches(scaleMatrix);

		// update the score;
		score = calculateScore(scaleMatrix);

		// drop objects if necessary
		//dropAll(); ->update for special Lokums

		// Checking if the board is playable
		//fillAllNothingsRandomly();

	}

	/**
	 * Generate scale matrix.
	 *
	 * @return the matching scale informer[][]
	 */
	private MatchingScaleInformer[][] generateScaleMatrix() {

		MatchingScaleInformer[][] scaleMatrix = new MatchingScaleInformer[board
				.getHeight()][board.getWidth()];
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				scaleMatrix[j][i] = rules.getMatchingScaleInformer(board, i, j,
						board.cellAt(i, j).getCurrentObject());
			}
		}
		return scaleMatrix;
	}

	/**
	 * Erase all matches.
	 *
	 * @param scaleMatrix
	 *            the scale matrix
	 */
	private void eraseAllMatches(MatchingScaleInformer[][] scaleMatrix) {
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				MatchingScaleInformer currentMSI = scaleMatrix[j][i];

				ChewyObject currentObject = board.cellAt(i, j).getCurrentObject();
				if (currentObject instanceof SpecialLokum) {
					eraseForSpecial(currentMSI, i, j);
				} else {
					//System.err.println("update is here");
					eraseForNormal(currentMSI, i, j);
				}
			}
		}
	}

	private void eraseForNormal(MatchingScaleInformer currentMSI, int i, int j) {
		if (rules.shouldErased(currentMSI)) {
			board.fillCellAt(i, j, new Nothing());
		}
		/*create special object only at the position
		 * of the recently 
		 * swapped objects
		 */
		if (recentlySwapped(new Position(i, j))) {	
			int specialityCode = rules.getSpecialityCode(currentMSI);
//			System.err.println("update is here:" +specialityCode);
			if (rules.isSpecialCase(specialityCode)) {
				board.fillCellAt(i, j,
						rules.getRelevantSpecialObject(specialityCode));
			}
		}

	}
	
	private void eraseForSpecial(MatchingScaleInformer currentMSI, int i, int j) {
		//to be implemented
		
	}

	private boolean recentlySwapped(Position p) {
		// TODO Auto-generated method stub
		return p.isSamePlace(successfullSwapLog[0])
				&& p.isSamePlace(successfullSwapLog[1]);
	}

	private Position getCounterPartPositionOfMatrixIndex(int i, int j) {
		// TODO Auto-generated method stub
		return new Position(j, i);
	}

	

	private ChewyObject getCounterPartObjectOfMatrix(int row, int col) {
		return board.cellAt(col, row).getCurrentObject();
	}

	/**
	 * Calculate score.
	 *
	 * @param msi
	 *            the msi
	 * @return the int
	 */
	private int calculateScore(MatchingScaleInformer[][] msi) {
		int score = 0;
		for (int i = 0; i < msi.length; i++) {
			for (int j = 0; j < msi[0].length; j++) {
				score += rules.score(msi[i][j]);
			}
		}
		return score;
	}

	public void dropAll() {
		for (int i = board.getWidth() - 1; i > -1; i--) {
			for (int j = board.getHeight() - 1; j > -1; j--) {
				if (board.cellAt(i, j).getCurrentObject().getType()
						.equals("Empty")) {
					int temp = j;
					while (board.cellAt(i, temp).getCurrentObject().getType()
							.equals("Empty")
							&& temp > 0) {
						temp--;
					}
					if (!board.cellAt(i, temp).getCurrentObject().getType()
							.equals("Empty")) {
						String type = board.cellAt(i, temp).getCurrentObject()
								.getType();
						board.fillCellAt(i, temp, new Nothing());
						board.fillCellAt(i, j, new Lokum(type));
						temp = j;
					}
				}
			}
		}

	}

	private boolean isThereAvailableMove() {
		for (int i = 0; i < board.getWidth() - 1; i++) {
			for (int j = 0; j < board.getHeight() - 1; j++) {
				if (rules.isSwappable(board, i, j, i + 1, j))
					return true;
				if (rules.isSwappable(board, i, j, i + 1, j + 1))
					return true;
				if (rules.isSwappable(board, i, j, i, j + 1))
					return true;
			}
		}

		return false;
	}

	public boolean isThereNothing() {
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				if (board.cellAt(i, j).getCurrentObject().getType()
						.equalsIgnoreCase("empty")) {
					return true;
				}
			}
		}
		return false;
	}

	public void fillAllNothingsRandomly() {
		String str[] = Lokum.possibleTypes;
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				if (board.cellAt(i, j).getCurrentObject().getType()
						.equalsIgnoreCase("empty")) {
					Lokum currentLokum = new Lokum(
							str[new Random().nextInt(str.length)]);
					board.fillCellAt(i, j, currentLokum);
				}

			}

		}
	}

	/*
	 * private void erase() { boolean eraseMatrix[][] = new
	 * boolean[board.getHeight()][board.getWidth()];
	 * 
	 * for (int r = 0; r < eraseMatrix.length; r++) { for (int c = 0; c <
	 * eraseMatrix[r].length; c++) { eraseMatrix[r][c] = rules.check(board, c,
	 * r, board.cellAt(c, r)) != RuleEngine.NO_MATCH; } }
	 * 
	 * for (int r = 0; r < eraseMatrix.length; r++) { for (int c = 0; c <
	 * eraseMatrix[r].length; c++) { if (eraseMatrix[r][c]) {
	 * board.fillCellAt(c, r, new Nothing()); } } }
	 * 
	 * }
	 */

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "\nGAME INFO \n" + "Game Score: " + score + "\n" + "Level ID: "
				+ level.getLevelId() + "\n" + "Movements Left: "
				+ movementsLeft + "\n" + "is board ok? " + board.repOk();
	}

	/*
	 * Old matching code MatchingScaleInformer info = scalingMatrix[i][j];
	 * 
	 * if (info.horizontalMatchTotalScale() >= RuleEngine.MINIMUM_MATCH_REQUIRED
	 * || info.verticalMatchTotalScale() >= RuleEngine.MINIMUM_MATCH_REQUIRED) {
	 * board.fillCellAt(i, j, new Nothing()); }
	 * 
	 * //left for (int k = 1; k <= info.getLeftScale(); k++) {
	 * board.fillCellAt(i-k, j, new Nothing()); }
	 * 
	 * //right for (int k = 1; k <= info.getRightScale(); k++) {
	 * board.fillCellAt(i+k, j, new Nothing()); }
	 * 
	 * //up for (int k = 1; k <= info.getUpScale(); k++) { board.fillCellAt(i,
	 * j-k, new Nothing()); }
	 * 
	 * //down for (int k = 1; k <= info.getDownScale(); k++) {
	 * board.fillCellAt(i, j+k, new Nothing()); }
	 */
}
