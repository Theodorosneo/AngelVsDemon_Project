package hw2;

/**
 * This class implements the {@link Board} the game will be played on.
 *  @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/03/2025
 */
public class Board {

	/**
	 * A two dimension array of {@link Cell}s to create a {@link Board}.
	 */
	private  Cell [][] board; 
	
	/**
	 * This method builds the {@link Board}.
	 * @param size- An integer value.
	 */
	public Board(int size) { 
	    board=new Cell[size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++) {
				if(i==size/2 && j==size/2 )
					board[i][j]=new Cell('A');
				else board[i][j]=new Cell();
			}
	}
	
	/**
	 * Copy Constructor of a {@link Board}.
	 * @param b- A {@link Board} value.
	 */
	public Board(Board b) {
		if(b==null) {
			System.out.println("Fatal Error. Game has been terminated! ");
			System.exit(0);
		}
		else {
			board=new Cell[b.getSize()][b.getSize()];
			for(int i=0;i<b.getSize();i++)
				for(int j=0;j<b.getSize();j++) {
					board[i][j]=new Cell(b.getCell(new Position(i,j)));
				}
		}
	}
	 /**
	  * This constructor takes a {@link Board} value as a parameter and a specific area on that {@link Board} and returns a new {@link Board} that represents that specific area of the provided {@link Board}. 
	  * @param b- A {@link Board} value.
	  * @param x0- An integer value.
	  * @param x1- An integer value.
	  * @param y0- An integer value.
	  * @param y1- An integer value.
	  */
	public Board(Board b,int x0,int x1,int y0,int y1) {
		board=new Cell[x1-x0][y1-y0];
		if(b==null) {
			System.out.println("Fatal Error! The program has been terminated.");
			System.exit(0);
		}
		else {
			for(int i=0;i<board.length;i++) {
				for(int j=0;j<board.length;j++) {
					board[i][j]=new Cell(b.getCell(new Position(x0+i,y0+j)).getCellPlayerType());
				}
			}
		}
	}
	
	/**
	 * This method returns the size of the {@link Board}.
	 * @return - An integer value.
	 */
	public int getSize() { 
		return board.length;
	}
	
	/**
	 * This method returns the value of the {@link Cell} in the (x,y) position of the {@link Board}.
	 * @param p- The {@link Position} of the {@link Cell} we want to get the {@link Player} type of.
	 * @return A char value.
	 */
	public Cell getCell(Position p) { 
		return new Cell(board[p.getRow()][p.getCollumn()].getCellPlayerType());
	}
	
	/**
	 * This method returns true if the {@link Cell} in the(x,y) position of the {@link Board} is Empty.
	 * @param p- The {@link Position} of the {@link Cell} we want to check.
	 * @return A boolean value.
	 */
	public boolean isEmpty(Position p) {
		return (board[p.getRow()][p.getCollumn()].isEmpty());
	}
	
	/**
	 * This method sets the {@link Cell} in the (x,y) position of the {@link Board} to the provided {@link Player} type.
	 * @param p- The {@link Position} of the {@link Cell} we want to set.
	 * @param playerType- A char value.
	 */
	public void setCell(Position p, char playerType) {  
		board[p.getRow()][p.getCollumn()].setCell(playerType);
	}
	
	
	
	/**
	 * This method returns the number of columns the {@link  Board } has.
	 * @return An integer value.
	 */
	public int getNumofCollumns() {
		return board[0].length;
	}
	
	/**
	 * This method returns the String version of the {@link Board}.
	 * @return A String value.
	 */
	public String toString() { 
		String st="";  
		String divider=""; 
		String numbers="";
		if(board.length<=10) {
			for(int i=1;i<=board.length;i++) {
					numbers+=" "+i+ "  ";
			}
		}
		else if(board.length>10 && board.length<=100) {
			for(int i=1;i<10;i++)
				numbers+=" "+i+ "  ";
			for(int i=10;i<=board.length;i++)
				numbers+=" "+i+ " ";
		}
		else {
			for(int i=1;i<10;i++)
				numbers+=" "+i+ "  ";
			for(int i=10;i<100;i++)
				numbers+=" "+i+ " ";
			for(int i=100;i<=board.length;i++)
				numbers+=" "+i;
		}
		for(int i=0;i<4*board.length+1;i++) {  
			divider+="-";
		}
		numbers="   "+numbers+"\n";
		st+=numbers;
		divider="   "+divider+"\n";  
		st+=divider;
		for(int i=0;i<board.length;i++) {
			if(i<9) {
				st+=i+1+"  ";
			}
			else if(i>=9 && i<99) {
				st+=i+1+" ";
			}
			else {
				st+=i+1;
			}
			for(int j=0;j<board.length;j++) {
				if((board[i][j].isEmpty())) {  
					st+="|   ";
				}
				else {
				st+="| "+board[i][j].toString()+" "	;  
				}
			}	
			st+="| "+(i+1)+"\n"; 
			st+=" "+divider;
		}
		
		st+=" "+numbers;
		return st; 
	}
	
	
	
	}

