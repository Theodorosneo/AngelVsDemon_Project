package hw2;

/**
 * This class implements a {@link Cell}.
 *  @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/03/2025
 */
public class Cell {

	/**
	 * The {@link Player} type that a {@link Cell} has.
	 */
	
	private char playerType;
	
	/**
	 * Default constructor of a {@link Cell} that sets it to Empty.
	 */
	public Cell() {  
		playerType='E';
	}
	/**
	 * Constructor of a {@link Cell} that creates a {@link Cell} with a {@link Player} type.
	 * @param p- A char value.
	 */
	public Cell(char p) {
		playerType=p;
	}
	
	/**
	 * Copy constructor of a {@link Cell}.
	 * @param c- A Cell type.
	 */
	public Cell(Cell c) {
		if(c== null) {
			System.out.println("Fatal Error. Gae has been terminated!");
			System.exit(0);
		}
		else 
		{
			this.playerType=c.playerType;
		}
	}
	
	/**
	 * This method sets the {@link Cell}'s {@link Player} type to p.
	 * @param p-A char value.
	 */
	public void setCell(char p) {  
		playerType=p;
	}
	
	/**
	 * This method returns the {@link Cell}'s {@link Player} type.
	 * @return A char value.
	 */
	public char getCellPlayerType() {  
		return playerType;
	}
	/**
	 * This method returns true if a {@link Cell} is Empty.
	 * @return A boolean value.
	 */
	public boolean isEmpty() {  
		return playerType=='E';
	}
	
	
	
	/**
	 * This method returns the {@link Cell} as a String.
	 * @return A String value.
	 */
	public String toString() {  
		return playerType+"";
	}
}
