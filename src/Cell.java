
public class Cell implements Clonable{
	
	private ChewyObject currentObject;
	
	
	
	public Cell(ChewyObject chewyObject) {
		currentObject = chewyObject;
	}
	
	@Override
	 public Cell clone(){
		// TODO Auto-generated method stub
		return new Cell(currentObject);
	}

	public boolean repOk() {
		// Checks if the cell has a chewy object component.
		if(!(currentObject == null)) return false;
		return true;
	}
	
	/**
	 * Exchangable means object can match with any other
	 * @return if object is matchable
	 */
	public boolean isExchangable() {
		return currentObject instanceof Matchable;
	}
	//this implementation maybe connected to the rule engine
	
	
	/**
	 * 
	 * @return Current object of cell
	 */
	public ChewyObject getCurrentObject() {
		return currentObject.clone();
	}

	/**
	 * 
	 * @return Previous object of cell.
	 * 
	 * -- This is required for swapping action. --
	 */
	
	
	/**
	 * @modifies: currentObject and previous
	 * @param Object: object for filling the cell
	 */
	public void setCurrentObject(ChewyObject object) {
		
		this.currentObject = object;
		
	}

	@Override
	public String toString() {
		return  currentObject.getType();
	}
	
	public String status() {
		return "Cell type: " +currentObject.getType();
	}
	
	

}
