import java.util.Random;


public class Test {
	
	private static Board bRandom;
	private static Board bPrepared = new Board(3, 3);;
	private static int width=3;
	private static int height =3;
	private static Level testLevel;
	private static GamePlay testGame;
	

	//Class Board, method Board, testing legal constructor values
	public static void LegalBoardConstructor(){
		try{
			Board b = new Board(10,10);
			System.out.println("LegalBoardConstructor passsed.");
		} catch (Exception e){
			System.out.println("LegalBoardConstructor failed: " + e.getMessage());
		}
	}

	//Class Board, method Board, testing illegal constructor values
	public static void IllegalBoardConstructor(){
		try{
			Board b = new Board(0,0);
			System.out.println("IllegalBoardConstructor 1 failed. IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e){
			System.out.println("IllegalBoardConstructor 1 passed : " + e.getMessage());
		}
		try{
			Board b = new Board(1,0);
			System.out.println("IllegalBoardConstructor 2 failed. IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e){
			System.out.println("IllegalBoardConstructor 2 passed : " + e.getMessage());
		}
		try{
			Board b = new Board(0,1);
			System.out.println("IllegalBoardConstructor 3 failed. IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e){
			System.out.println("IllegalBoardConstructor 3 passed : " + e.getMessage());
		}
	}
	
	//Class Level, method Level, testing legal constructor values
	public static void LegalLevelConstructor(){
		int passingScore = 1;
		int possibleMovements = 1;
		Board b = new Board(10,10);
		int levelId = 0;
		try{
			Level l = new Level(passingScore,possibleMovements,b,levelId);
			System.out.println("IllegalLevelConstructor 1 passed");
		} catch(IllegalArgumentException e){
			System.out.println("IllegalLevelConstructor 1 failed : " + e.getMessage());
		}
	}

	//Class Level, method Level, testing illegal constructor values
	public static void IllegalLevelConstructor(){
		int passingScore = 0;
		int possibleMovements = 1;
		Board b = new Board(10,10);
		int levelId = 0;
		try{
			Level l = new Level(passingScore,possibleMovements,b,levelId);
			System.out.println("IllegalLevelConstructor 1 failed : IllegalArgumentException not thrown.");
		} catch(IllegalArgumentException e){
			System.out.println("IllegalLevelConstructor 1 passed : " + e.getMessage());
		}
	}
	
	//Class Board, method cellAt, testing accessing a legal position of the board
	public static void LegalcellAt(){
		try{
			Board b = new Board(5, 5);
			Cell c = b.cellAt(2, 2);
			System.out.println("LegalCellAt passed");
		} catch(IllegalArgumentException e){
			System.out.println("LegalCellAt failed : " + e.getMessage());
		}
	}

	//Class Board, method CellAt, testing trying to access cells of illegal positions
	public static void IllegalcellAt(){
		Board b = new Board(10,10);
		try {
			Cell c = b.cellAt(20, 20);
			System.out.println("IllegalCellAt 1 failed : IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e){
			System.out.println("IllegalCellAt 1 passed.");
		}
		
		try {
			Cell c = b.cellAt(-10, 2);
			System.out.println("IllegalCellAt 1 failed : IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e){
			System.out.println("IllegalCellAt 1 passed.");
		}
		
		try {
			Cell c = b.cellAt(2, -10);
			System.out.println("IllegalCellAt 1 failed : IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e){
			System.out.println("IllegalCellAt 1 passed.");
		}
	}
	
	//Class Board, method fillCellAt, testing filling board with legal ChewyObject and position
	public static void LegalfillCellAt(){
		Board b = new Board(10,10);
		try {
			Lokum l = new Lokum("A");
			b.fillCellAt(5, 5, l);
			System.out.println("LegalfillCellAt passed");
		} catch (Exception e){
			System.out.println("LegalfillCellAt failed : " + e.getMessage());
		}
	}

	//Class Board, method fillCellAt, testing filling board with illegal ChewyObject and position
	public static void IllegalfillCellAt(){
		Board b = new Board(10,10);
		try {
			Lokum l = null;
			b.fillCellAt(5, 5, (Lokum)l);
			System.out.println("IllegalfillCellAt 1 failed : Exception about null lokum not thrown.");
		} catch (Exception e){
			System.out.println("IllegalfillCellAt 1 passed.");
		}
		
		try {
			Lokum l = new Lokum("A");
			b.fillCellAt(15, 5, l);
			System.out.println("IllegalfillCellAt 1 failed : IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e){
			System.out.println("IllegalfillCellAt 1 passed.");
		}
		
		try {
			Lokum l = new Lokum("A");
			b.fillCellAt(5, 15, l);
			System.out.println("IllegalfillCellAt 1 failed : IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e){
			System.out.println("IllegalfillCellAt 1 passed.");
		}
	}
	
	//Class Board, method constructor, testing creating and random initialization of new board with lokums
	public static void boardInitRandomly(int width, int height) {
		ChewyObject initObjects[][] = new ChewyObject[width][height];
		Random rand = new Random();
		int sel;
		for (int i = 0; i < initObjects.length; i++) {
			for (int j = 0; j < initObjects[0].length; j++) {
				sel = rand.nextInt(3);
				sel %= 3;
				switch (sel) {
				case 0:
					initObjects[i][j] = new Lokum("A");
					break;
				case 1:
					initObjects[i][j] = new Lokum("B");
					break;
				case 2:
					initObjects[i][j] = new Lokum("C");
					break;
				default:
					System.err.println("Do something to handle this error.");
				}
			}
		}
		
		bRandom = new Board(width, height);
		for (int i = 0; i < bRandom.getWidth(); i++) {
			for (int j = 0; j < bRandom.getHeight(); j++) {
				bRandom.fillCellAt(i, j, initObjects[i][j]);
			}
		}
		
		if(bRandom.repOk()){
			System.out.println("Successfull!! Board created and filled randomly.");
			System.out.println(bRandom.status());
			System.out.println(bRandom);
		}
		else
			System.out.println("Fail!! Board couldn't have created or filled randomly.");
	}

	//Class Board, method fillCellAt, Class Lokum, method Lokum, testing filling certain spots on board with new Lokums
	public static void prapareBoardForFurtherOp(){
		bPrepared.fillCellAt(0, 0, new Lokum("A"));
		bPrepared.fillCellAt(0, 1, new Lokum("B"));
		bPrepared.fillCellAt(0, 2, new Lokum("A"));
		bPrepared.fillCellAt(1, 0, new Lokum("B"));
		bPrepared.fillCellAt(1, 1, new Lokum("A"));
		bPrepared.fillCellAt(1, 2, new Lokum("C"));
		bPrepared.fillCellAt(2, 0, new Lokum("A"));
		bPrepared.fillCellAt(2, 1, new Lokum("A"));
		bPrepared.fillCellAt(2, 2, new Lokum("C"));
		
		if(bPrepared.repOk()){
			System.out.println("Successfull!! Prepared Board is ready to further tests.");
		}
		else
			System.out.println("Fail!! Prepared Board is not ready to further tests.");
		
	}
	
	//Class Level, method repOK, testing RepOK of normal constructor
	public static void initLevel(){
		testLevel = new Level(20, 5, bPrepared, 1);
		
		if(testLevel.repOk()){
			System.out.println("Successfull!! Test level is created.");
			System.out.println(testLevel);
		}
		else
			System.out.println("Fail!! Test level is not created.");
			
		
	}
	
	//Class GamePlay, method GamePlay, testing constructor with legal value
	public static void initGame(){	
		testGame = new GamePlay(testLevel);
		
		if(testGame.repOk()){
			System.out.println("Successfull!! Test game is created.");
			System.out.println(testGame);
		}
		else
			System.out.println("Fail!! Test game is not created.");
		
	}
	
	//Class GamePlay, method swap, testing legal horizontal swap
	public static void successfulHorSwap(){
		System.out.println("Horizontal Swap Operation is started.");
		System.out.println("Before");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
		System.out.println("Swapping (0,1) and (1,1)");
		if(testGame.swap(0, 1, 1, 1))
			System.out.println("Horizontal Swap is Successfull!\n");
		else
			System.out.println("Horizontal Swap is Failed!");
		System.out.println("After");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
	}

	//Class GamePlay, method swap, testing illegal horizontal swap
	public static void failedHorSwap(){
		System.out.println("Horizontal Swap Operation is started.");
		System.out.println("Before");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
		System.out.println("Swapping (2,1) and (1,1)");
		if(testGame.swap(2, 1, 1, 1))
			System.out.println("Horizontal Swap is Successfull!\n");
		else
			System.out.println("Horizontal Swap is Failed!");
		System.out.println("After");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
	}

	//Class GamePlay, method swap, testing legal vertical swap
	public static void successfulVerSwap(){
		System.out.println("Vercital Swap Operation is started.");
		System.out.println("Before");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
		System.out.println("Swapping (1,0) and (1,1)");
		if(testGame.swap(1, 0, 1, 1))
			System.out.println("Vercital Swap is Successfull!\n");
		else
			System.out.println("Vercital Swap is Failed!");
		System.out.println("After");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
	}

	//Class GamePlay, method swap, testing illegal vertical swap
	public static void FailedVerSwap(){
		System.out.println("Vercital Swap Operation is started.");
		System.out.println("Before");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
		System.out.println("Swapping (1,2) and (1,1)");
		if(testGame.swap(1, 2, 1, 1))
			System.out.println("Vercital Swap is Successfull!\n");
		else
			System.out.println("Vercital Swap is Failed!");
		System.out.println("After");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
	}

	//Class GamePlay, method swap, testing legal cross swap
	public static void successfulCrossSwap(){
		System.out.println("Cross Swap Operation is started.");
		System.out.println("Before");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
		System.out.println("Swapping (2,2) and (1,1)");
		if(testGame.swap(2, 2, 1, 1))
			System.out.println("Cross Swap is Successfull!\n");
		else
			System.out.println("Cross Swap is Failed!");
		System.out.println("After");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
	}

	//Class GamePlay, method swap, testing illegal cross swap
	public static void FailedCrossSwap(){
		System.out.println("Cross Swap Operation is started.");
		System.out.println("Before");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
		System.out.println("Swapping (1,2) and (0,1)");
		if(testGame.swap(1, 2, 0, 1))
			System.out.println("Cross Swap is Successfull!\n");
		else
			System.out.println("Cross Swap is Failed!");
		System.out.println("After");
		System.out.println(bPrepared);
		System.out.println(testGame.movementsLeft());
	}
		
	public static void main(String[] args) {
		LegalBoardConstructor();
		IllegalBoardConstructor();
		LegalLevelConstructor();
		IllegalLevelConstructor();
		LegalcellAt();
		IllegalcellAt();
		LegalfillCellAt();
		IllegalfillCellAt();
		
		System.out.println(testSplitter());
		boardInitRandomly(width,height);
		
		System.out.println(testSplitter());
		prapareBoardForFurtherOp();
		System.out.println(bPrepared.status());
		System.out.println(bPrepared);
		
		System.out.println(testSplitter());
		initLevel();
		
		System.out.println(testSplitter());
		initGame();
		
		System.out.println(testSplitter());	
		successfulHorSwap();
		System.out.println("Resetting the prepared board.");
		prapareBoardForFurtherOp();
		
		System.out.println(testSplitter());
		successfulVerSwap();
		System.out.println("Resetting the prepared board.");
		prapareBoardForFurtherOp();
		
		System.out.println(testSplitter());
		successfulCrossSwap();
		System.out.println("Resetting the prepared board.");
		prapareBoardForFurtherOp();
		
		System.out.println(testSplitter());
		failedHorSwap();
		
		System.out.println(testSplitter());
		FailedVerSwap();
		
		System.out.println(testSplitter());
		FailedCrossSwap();

	}
	
	private static String testSplitter(){
		return "\n----------------\n" +
	           "  Next Test  \n" +
		       "----------------\n";
	}
	
	//-------------- **Old Stuff** -----------------
	
	/*// TODO Auto-generated method stub
	Board b = new Board(3, 3);
	Random rand = new Random();
	int sel = 0;
	for (int i = 0; i < b.getWidth(); i++) {
		for (int j = 0; j < b.getHeight(); j++) {
			sel %= 3;
			switch (sel) {
			case 0:
				b.fillCellAt(i, j, new Lokum("A"));
				break;
			case 1:
				b.fillCellAt(i, j, new Lokum("B"));
				break;
			case 2:
				b.fillCellAt(i, j, new Lokum("C"));
				break;
			default:
				System.err.println("Sıçtık");
			}
		}
		sel++;
	}
	b.fillCellAt(0, 1, new Lokum("B"));
	b.fillCellAt(1, 1, new Lokum("A"));
	GamePlay gp = new GamePlay(new Level(9, b, 1));
	System.out.println(b);
	System.out.println(gp.swap(0, 1, 1, 1));
	
	System.out.println(b);*/	
			
			
	

}
