import java.util.Arrays;


public class Obstacke extends ChewyObject{
	private static final String types[] = {"Standard Obstackle"};
	public Obstacke(String type) throws IllegalArgumentException{
		if (isValid(type)) {
			this.type = type;
		}
		else {
			throw new IllegalArgumentException("Illegal obstacle type");
		}
		// TODO Auto-generated constructor stub
	}
	private boolean isValid(String type) {
		// TODO Auto-generated method stub
		return Arrays.binarySearch(types, type) >= 0;
	}
	

}
