
public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board(4, 3);
		b.fillCellAt(0, 0, new Lokum("green pistachio"));
		b.fillCellAt(0, 1, new Lokum("red rose"));
		b.fillCellAt(0, 2, new Lokum("green pistachio"));
		b.fillCellAt(1, 1, new Lokum("green pistachio"));
		b.fillCellAt(1, 2, new Lokum("green pistachio"));
		b.fillCellAt(2, 2, new Lokum("green pistachio"));
		Level level = new Level(20, 10, b, 1);
		GamePlay gp = new GamePlay(level);
		/*System.out.println(RuleEngine.getInstance().gameEndedByMovements(1));
		System.out.println(b);
		Level level = new Level(20, 10, b, 1);
		GamePlay gp = new GamePlay(level);
		System.out.println(gp.swap(0, 1, 1, 1));
		System.out.println(b);
		
		for (int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				RuleEngine rules = RuleEngine.getInstance();
				System.out.print(rules.check(b, j, i, b.cellAt(j, i)));
			}
			System.out.println();
			
		}
		
		Cell c = b.cellAt(0, 1);
		System.out.println(c);
		c.setCurrentObject(new Lokum("white coconut"));*/
		System.out.println(gp.swap(0, 1, 1, 1));
		System.out.println(b);
		gp.updateBoard();
		
		
		System.out.println(b);
		
	}

}
