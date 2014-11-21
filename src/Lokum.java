import java.util.Arrays;


public class Lokum extends ChewyObject implements Matchable{
	
	private static final String[] possibleTypes = {
		" ", "A", "B", "C"
	};
	
	public Lokum(String type) throws IllegalArgumentException{
		if(isValid(type)) {
			this.type = type;
		} else {
			throw new IllegalArgumentException("Type: "+ type + " is unknown.");
		}
	}

	private boolean isValid(String type) {
		return Arrays.binarySearch(possibleTypes, type) >= 0;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isMatched(Matchable l) {
		// TODO Auto-generated method stub
		if (l instanceof Lokum) {
			if (type.equals(((Lokum) l).getType())) {
				return true;
			}
		} 
		return false;
	}

}
