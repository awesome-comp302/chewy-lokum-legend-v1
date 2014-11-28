import java.util.Arrays;


public class Lokum extends ChewyObject implements Matchable{
	
	
	public static final String[] possibleTypes = {
		
		"brown hazelnut",
		"green pistachio",
		"red rose",		
		"white coconut"
	};
	
	/*
	 * 
	 */
	public Lokum(String type) throws IllegalArgumentException{
		if(isValid(type)) {
			this.type = type;
		} else {
			throw new IllegalArgumentException("Type: "+ type + " is unknown.");
		}
	}
	
	

	private boolean isValid(String type) {
		return Arrays.binarySearch(possibleTypes, type) >= 0;
	}

	@Override
	public boolean isMatched(Matchable l) {
		
		if (l instanceof Lokum) {
			if (type.equalsIgnoreCase(((Lokum) l).getType())) {
				return true;
			}
		} 
		return false;
	}
}
