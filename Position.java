package hw2;

/**
 * This class implements a {@link Position}.
 *  @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/03/2025
 */
public class Position {

	/**
	 * The row and column of the {@link Position}.
	 */
	
	private int row;
	private int collumn;
	
	/**
	 * The constructor of the {@link Position}.
	 * @param x- An integer value.
	 * @param y-An integer value.
	 */
	public Position(int x,int y) { 
		row=x;
		collumn=y;
	}
	
	/**
	 * Copy constructor of the {@link Position}.
	 * @param p- A {@link Position} value.
	 */
	public Position(Position p) {
		if(p==null) {
			System.out.println("Fatal Error! Game has been terminated!");
			System.exit(0);
		}
		else {
			row=p.getRow();
			collumn=p.getCollumn();
		}
	}
	
	/**
	 * Row getter.
	 * @return  An integer value.
	 */
	public int getRow() {  
		return row;
	}
	
	/**
	 * Column getter.
	 * @return An integer value.
	 */
	public int getCollumn() {  
		return collumn;
	}
	
	/**
	 * Row setter.
	 * @param r- An integer value.
	 */
	public void setRow(int r) {
		row=r;
	}
	
	/**
	 * Column setter.
	 * @param c- An integer value.
	 */
	public void setCollumn(int c) {
		collumn=c;
	}
	
	/**
	 * Returns the row and column of the {@link Position} object as a String.
	 */
	public String toString() {  
		return "x= "+row+"  y= "+collumn;
	}
	
}