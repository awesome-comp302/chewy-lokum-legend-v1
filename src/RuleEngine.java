
public class RuleEngine {
	
	private static RuleEngine instance;
	public static final int
			NO_MATCH = 0,
			VERTICAL_MATCH = 1,
			HORIZONTAL_MATCH = 2,
			VH_MATCH = VERTICAL_MATCH + HORIZONTAL_MATCH;
			//CROSS_MATCH = 3,
			
			//HC_MATCH = HORIZONTAL_MATCH + CROSS_MATCH,//HORIZONTAL-CROSS
			//VH_MATCH = VERTICAL_MATCH + HORIZONTAL_MATCH,//VERTICAL-HORIZONTAL
			//CV_MATCH = CROSS_MATCH + VERTICAL_MATCH,//CROSS-VERTICAL MARCH
			//HCV_MATCH = HORIZONTAL_MATCH + CV_MATCH;
			
	
			
	
		
	public static final int MINIMUM_MATCH_REQUIRED = 3;
	
	private RuleEngine() {
		
	}
	
	public static RuleEngine getInstance() {
		if (instance == null) {
			instance = new RuleEngine();
		}
		return instance;
	}
	
	/*
	 verification takes a board and coordinates of 2 cells as argument
	 and return a map to cell no -> verification constant
	 which are publicly accessible constants defined in RuleEngine Class. 
	 
	 */
	
	/*public boolean isVerified(int x1, int y1, int x2, int y2) {
		return 
	}*/
	
	/*public HashMap<Integer, Integer> getVerificationMap(int x1, int y1, int x2, int y2, Board board) {
		int first = check(board, x1, y1, board.cellAt(x2, y2));
		int second = check(board, x2, y2, board.cellAt(x1, y1));
		HashMap<Integer, Integer> verificationMap = new HashMap<Integer, Integer>();
		verificationMap.put(1, first);
		verificationMap.put(2, second);
		return verificationMap;
	}*/

	
	/**
	 * @requires: board and cellAt is non null and NO_MATCH should be 0
	 * @ensures: returns the value of the correct constant
	 * @param board
	 * @param x1
	 * @param y1
	 * @param cellAt
	 * @return
	 */
	
	
	public int check(Board board, int x1, int y1, Cell cell) {
		// TODO Auto-generated method stub
	
		if (! (cell.isExchangable())) {
			return NO_MATCH;
		}
		int vert = countVert(board, x1, y1, (Matchable)cell.getCurrentObject());
		int hor = countHor(board, x1, y1, (Matchable)cell.getCurrentObject());
		
		System.out.println("vert: "+vert);
		System.out.println("hor: "+hor);
		
		
		int result = NO_MATCH;
		
		if (vert >= MINIMUM_MATCH_REQUIRED) {
			result = VERTICAL_MATCH;
		}
		
		if (hor >= MINIMUM_MATCH_REQUIRED) {
			result += HORIZONTAL_MATCH;
		}
		System.out.println(result);
		
		return result;
		
	}
	
	public MatchingScaleInformer getMatchingScaleInformer(Board board, int x1, int y1, ChewyObject object) {
		
		MatchingScaleInformer info = new MatchingScaleInformer();
		if (!(object instanceof Matchable)) {
			return info;
		}
		
		Matchable m = (Matchable)object;
		
		if (countVert(board, x1, y1, m) < MINIMUM_MATCH_REQUIRED &&
				countHor(board, x1, y1, m) < MINIMUM_MATCH_REQUIRED)
			return info;
		
		int up = countTop(board, x1, y1, m);
		int down = countBottom(board, x1, y1, m);
		int right = countRigth(board, x1, y1, m);
		int left = countLeft(board, x1, y1, m);
		//System.err.println("up is" + up);
		
		info.setUpScale(up);
		info.setDownScale(down);
		info.setRightScale(right);
		info.setLeftScale(left);

		return info;
	}
	
	
	
	public boolean isSwappable(Board board, int x1, int y1,int x2, int y2) {
		
		if (!(board.inBoard(x1, y1) && board.inBoard(x2, y2))) {
			return false;
		}
		
		if (!(isConsecutive(x1, y1, x2, y2))) {
			return false;
		}
		
		
		MatchingScaleInformer msi1 = getMatchingScaleInformer(board, x1, y1, board.cellAt(x2, y2).getCurrentObject());
		MatchingScaleInformer msi2 = getMatchingScaleInformer(board, x2, y2, board.cellAt(x1, y1).getCurrentObject());
		if (msi1.horizontalMatchTotalScale() < MINIMUM_MATCH_REQUIRED
				||
				msi2.verticalMatchTotalScale() < MINIMUM_MATCH_REQUIRED) {
			return false;
			
		}
		
		return true;
		
	}

	private boolean isConsecutive(int x1, int y1, int x2, int y2) {
		int xdif = Math.abs(x1 - x2);
		int ydif = Math.abs(y2 - y1);
		int totalDif = xdif + ydif;
		return totalDif >= 0 && totalDif <= 2;
	}

	/**
	 * 
	 * @param board
	 * @param x1
	 * @param y1
	 * @param candidate
	 * @return
	 */
	/*private boolean countCross(Board board, int x1, int y1, Matchable candidate) {
		// TODO Auto-generated method stub
		int sum = 0;
		
		int x_count = x1+1, y_count = y1+1;
		while( x_count < board.getWidth() && y_count < board.getHeight()) {
			ChewyObject current = board.cellAt(x_count, y_count).getCurrentObject();
			if ((current instanceof Matchable) && 
					(candidate.isMatched((Matchable)current))) {
				x1++;
				y1++;
				sum++;
			} else {
				break;
			}
		}
		x_count =x1-1;
		y_count = y1-1;
		while( x_count >= 0 && y_count >= 0) {
			ChewyObject current = board.cellAt(x_count, y_count).getCurrentObject();
			if ((current instanceof Matchable) && 
					(candidate.isMatched((Matchable)current))) {
				x1--;
				y1--;
				sum++;
			} else {
				break;
			}
		}
		return sum >= MINIMUM_MATCH_REQUIRED;
	}*/

	private int countHor(Board board, int x1, int y1, Matchable candidate) {
		// TODO Auto-generated method stub
		return 1 + countRigth(board, x1, y1, candidate) + countLeft(board, x1, y1, candidate);
	}
	
	private int countRigth(Board board, int x1, int y1, Matchable candidate) {
		int sum = 0;
		
		int x_count = x1+1;
		while( board.inBoard(x_count, y1) ) {
			ChewyObject current = board.cellAt(x_count, y1).getCurrentObject();
			if ((current instanceof Matchable) && 
					(candidate.isMatched((Matchable)current))) {
				x_count++;
				sum++;
			} else {
				break;
			}
		}
		
		return sum;
	}
	
	
	private int countLeft(Board board, int x1, int y1, Matchable candidate) {
		int sum = 0;
		int x_count =x1-1;
		while( board.inBoard(x_count, y1) ) {
			ChewyObject current = board.cellAt(x_count, y1).getCurrentObject();
			if ((current instanceof Matchable) && 
					(candidate.isMatched((Matchable)current))) {
				x_count--;
				sum++;
			} else {
				break;
			}
		}
		return sum;
	}
	
	
	private int countVert(Board board, int x1, int y1,Matchable candidate) {
		return 1 + countTop(board, x1, y1, candidate) + countBottom(board, x1, y1, candidate);	
	}
	
	private int countBottom(Board board, int x1, int y1,Matchable candidate) {
		int sum = 0;
		
		int y_count = y1+1;
		while( board.inBoard(x1, y_count) ) {
			ChewyObject current = board.cellAt(x1, y_count).getCurrentObject();
			System.out.println("bottom"
					+ "current: "+current.getType());
			System.out.println("candidate: "+ ((Lokum)candidate).getType());
			if ((current instanceof Matchable) && 
					(candidate.isMatched((Matchable)current))) {
				y_count++;
				sum++;
			} else {
				break;
			}
		}
		return sum;
		
	}
	
	private int countTop(Board board, int x1, int y1,Matchable candidate) {
		int sum = 0;
		int y_count =y1-1;
		while( board.inBoard(x1, y_count)) {
			ChewyObject current = board.cellAt(x1, y_count).getCurrentObject();
			System.out.println("topcurrent: "+current.getType());
			System.out.println("candidate: "+ ((Lokum)candidate).getType());
			if ((current instanceof Matchable) && 
					(candidate.isMatched((Matchable)current))) {
				y_count--;
				sum++;
			} else {
				break;
			}
		}
		return sum;
	}

	public boolean shouldErased(MatchingScaleInformer msi) {
		if (msi.horizontalMatchTotalScale() >= MINIMUM_MATCH_REQUIRED
				||
				msi.verticalMatchTotalScale() >= MINIMUM_MATCH_REQUIRED)
			return true;
		return false;
	}

	public boolean gameEndedByMovements(int movementsLeft) {
		// TODO Auto-generated method stub
		if (movementsLeft > 0) {
			return false;
		}
		return true;
	}

	/*
	 * Left and up scale should only stand for checking whether the cell was matched previously
	 * check always occurs from left to right and up to down
	 */
	public int score(MatchingScaleInformer msi)
	{
		int score = 0;
		if (msi.getLeftScale() == 0) {
			
			int rightScale = msi.getRightScale();
			if (rightScale >= MINIMUM_MATCH_REQUIRED-1) {
				score += rightScale * 20;
			}
		}
		if (msi.getUpScale() == 0) {
			int downScale = msi.getDownScale();
			if (downScale>= MINIMUM_MATCH_REQUIRED-1) {
				score += downScale * 20;
			}
		}
		
		return score;
	}
	
	
}
