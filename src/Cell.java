
public class Cell {
	
	private ChewyObject currentObject;
	private ChewyObject previous;
	
	
	
	public Cell(ChewyObject chewyObject) {
		currentObject = chewyObject;
		previous = null;
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
		return currentObject;
	}

	/**
	 * 
	 * @return Previous object of cell.
	 * 
	 * -- This is required for swapping action. --
	 */
	public ChewyObject getPrevious() 
	{
		return previous;
	}
	
	/**
	 * @modifies: currentObject and previous
	 * @param Object: object for filling the cell
	 */
	public void setCurrentObject(ChewyObject object) {
		
		this.previous = this.currentObject;
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
