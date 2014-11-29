import java.util.Random;


public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		bug();
	}
	
	private static void bug(){
		
		Board b2 = new Board(3, 3);
		Level l2 = new Level(10, 10, b2, 3);
		GamePlay gp2 = new GamePlay(l2);
		
		b2.fillCellAt(0, 0, new Lokum("brown hazelnut"));
		b2.fillCellAt(1, 0, new Lokum("green pistachio"));
		b2.fillCellAt(2, 0, new Lokum("red rose"));
		b2.fillCellAt(0, 2, new Lokum("red rose"));
		b2.fillCellAt(1, 1, new Lokum("brown hazelnut"));
		b2.fillCellAt(2, 2, new Lokum("white coconut"));
		b2.fillCellAt(2, 1, new Lokum("white coconut"));
		//b2.fillCellAt(1, 2, new Lokum("green pistachio"));
		b2.fillCellAt(0, 1, new Lokum("red rose"));
		System.out.println(b2);
		gp2.updateBoard();
		System.out.println(b2);
		
		for (int i = 0; i < b2.getWidth()-1; i++) {
			for (int j = 0; j < b2.getHeight()-1; j++) {
				if(gp2.swap(i, j, i+1, j)){
					System.out.println(i+" "+j+"Swap is Successfull hor!\n");
					gp2.swap(i, j, i+1, j);}
				else
					System.out.println(i+" "+j+"Swap is Failed!");
				System.out.println(b2);
				if(gp2.swap(i, j, i+1, j+1)){
					//bu mu?
					System.out.println(i+" "+j+"Swap is Successfull cross!\n");
					gp2.swap(i, j, i+1, j+1);}
				else
					System.out.println(i+" "+j+"Swap is Failed!");
				System.out.println(b2);
				if(gp2.swap(i, j, i, j+1)){
					System.out.println(i+" "+j+"Swap is Successfull ver!\n");
					gp2.swap(i, j, i, j+1);}
				else
					System.out.println(i+" "+j+"Swap is Failed!");
				System.out.println(b2);
				System.out.println("\n\n\n\n");
			}
			
		}
		
	}
	
	private static void fillRandomlyTest(){
		Board b = new Board(5, 5);
		Level l = new Level(10, 10, b, 2);
		GamePlay gp = new GamePlay(l);
		while (isThereNothing(b)) {
			fillAllNothingsRandomly(b);
			gp.updateBoard();
		}
		System.out.println(b);
	}
	
	private static void dropTest(){
		Board b3 = new Board(3, 6);
		Level l3 = new Level(10, 10, b3, 4);
		GamePlay gp3 = new GamePlay(l3);
		b3.fillCellAt(0, 0, new Lokum("brown hazelnut"));
		b3.fillCellAt(1, 0, new Lokum("green pistachio"));
		b3.fillCellAt(2, 0, new Lokum("red rose"));
		b3.fillCellAt(2, 1, new Lokum("red rose"));
		b3.fillCellAt(1, 4, new Lokum("green pistachio"));
		System.out.println(b3);
		gp3.dropAll();
		System.out.println(b3);
		
	}
	
	
	
	
	private static boolean isThereNothing(Board b) {
		// TODO Auto-generated method stub
		for (int i = 0; i < b.getWidth(); i++) {
			for (int j = 0; j < b.getHeight(); j++) {
				if (b.cellAt(i, j).getCurrentObject().getType().equalsIgnoreCase("empty")) {
					return true;
				}
			}
		}
		return false;
	}

	private static void fillAllNothingsRandomly(Board b) {
		String str[] = Lokum.possibleTypes;
		for (int i = 0; i < b.getWidth(); i++) {
			for (int j = 0; j < b.getHeight(); j++) {
				if (b.cellAt(i, j).getCurrentObject().getType().equalsIgnoreCase("empty")) {
					Lokum currentLokum = new Lokum(str[new Random().nextInt(str.length)]);
					b.fillCellAt(i, j, currentLokum);
				}
				
			}
		}
		
		
		
	}

	public static void main2(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board(4, 3);
		b.fillCellAt(0, 0, new Lokum("green pistachio"));
		b.fillCellAt(0, 1, new Lokum("red rose"));
		b.fillCellAt(0, 2, new Lokum("green pistachio"));
		b.fillCellAt(1, 1, new Lokum("green pistachio"));
		b.fillCellAt(1, 2, new Lokum("green pistachio"));
		b.fillCellAt(2, 2, new Lokum("green pistachio"));
		b.fillCellAt(3, 2, new Lokum("green pistachio"));
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
		b.fillCellAt(2, 1, new Lokum("red rose"));
		b.fillCellAt(3, 1, new Lokum("red rose"));
		System.out.println(b);
		System.out.println("Score: " + gp.getScore());
		gp.updateBoard();
		System.out.println("Score: " + gp.getScore());
		System.out.println(b);
		
	}

}
