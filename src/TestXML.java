
public class TestXML {
	private static String pID;
	private static String pName;
	private static Board board;
	private static Level level;
	private static GamePlay gp;

	public static void main(String[] args) {
		pID = "1";
		pName = "Berk";
		board = new Board(5, 5);
		level = new Level(5, 10,board, 1);
		gp = new GamePlay(level);
		
		gp.updateBoardGC();

	}

}
