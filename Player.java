package hw2;
import java.util.Scanner;

/**
 * This class implements a {@link Player}.
 *  @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/03/2025
 */
public abstract class Player {

		/**
		 * The fields of the {@link Player} along with the {@link Angel}'s power.
		 */
	
		final int K;
		Position p;
		Board board;
		private char playerType;
		
		/**
		 * The Constructor of the {@link Player} that will be used only as super from the derived classes.
		 * @param power- An integer value. The power of the {@link Angel}.
		 * @param board- A {@link Board} value.
		 * @param player-A char value. ('A' for Angel, 'D' for Demon).
		 */
		
		public Player(Board board,char player,int power) { 
			playerType=player;
		    this.board=board;
		    K=power;
		}
		
		/**
		 * This method moves the {@link Player} manually. Will be overridden from the derived classes. 
		 * @param c -A Scanner value.
		 */
		public abstract void movePlayer(Scanner c);
			
		/**
		 * This method moves the {@link Player} automatically. Will be overridden from the derived classes. 
		 */
		public abstract void moveAutoPlayer();
		
		/**
		 * This method finds the number of available moves that the {@link Angel} has.
		 * This method can be used from both the {@link Angel} and {@link Demon} to find how many ways the {@link Angel} can move and for the {@link Demon} to prevent it and block it.
		 * @return- An integer value.
		 */
		
		int availableMoves(Position p) {
			int counter=0;
			int up=p.getCollumn()-K;
			int down=p.getCollumn()+K;
			int left=p.getRow()-K;
			int right=p.getRow()+K;
			int startx;
			int starty;
			int finishx;
			int finishy;
			if(up<=0) {
				starty=0;
			}
			else starty=up;
			if(down>=board.getSize()) {
				finishy=board.getSize();
			}
			else finishy=down+1;
			if(left<=0) {
				startx=0;
			}
			else startx=left;
			if(right>=board.getSize()) {
				finishx=board.getSize();
			}
			else finishx=right+1;
			for(int i=startx;i<finishx;i++) {
				for(int j=starty;j<finishy;j++) {
					if(board.getCell(new Position(i,j)).isEmpty()) {
						counter++;
					}
				}
			}	
			return counter;
		}
		
		/**
		 * This method returns the only available {@link Position} that the {@link Angel} can play.
		 * Can be used by both the {@link Angel} and {@link Demon} so the one can win and the other can block the only {@link Cell} that the {@link Angel} can move to.
		 * @return A {@link Position} value.
		 */
		 Position findOnlyAvailableMove(Position p) {
				int x=0;
				int y=0;
				Board b=PositionArea(p);
				for(int i=0;i<b.getSize();i++) {
					for(int j=0;j<b.getNumofCollumns();j++) {
						if(b.getCell(new Position(i,j)).isEmpty()) {
							if(i!=b.getSize()/2) {
								 x=p.getRow()+i-((b.getSize()/2));
							 }
							 else {
								 x=p.getRow();
							 }
							 if(j!=b.getNumofCollumns()/2) {
								 y=p.getCollumn()+i-((b.getNumofCollumns()/2));
							 }
							 else {
								 y=p.getRow();
							 }
						}
					}
				}
				return new Position(x,y);
			
		}
		
		/**
		 * This method finds the {@link Angel}'s {@link Position} on the {@link Board}.
		 * @return A {@link Position} value.
		 */
		 Position findAngelPosition() {
			int x=0;
			int y=0;
			for(int i=0;i<board.getSize();i++) {
				for(int j=0;j<board.getSize();j++) {
					if(board.getCell(new Position(i,j)).getCellPlayerType()=='A') {
						x=i;
						y=j;
					}
				}
			}
			return new Position(x,y);
		}
		
		/**
		 * This method finds the area of the moves that the {@link Angel} can do and represents the winning rates of each block so the {@link Angel} can find the best route to the win.
		 * @return- A double[][] value.
		 */
		private double[][] PosibilitiesOnAngelsArea(Position p){
			 double [][] posibilities=new double[board.getSize()][board.getSize()];
			 for(int i=0;i<posibilities.length;i++) {
				 for(int j=0;j<posibilities[i].length;j++) {
					 posibilities[i][j]=GeneratePosibility(new Position(i,j));
				 }
			 }
			 return posibilities;
		 }
		 
		 /**
		  * This method finds the {@link Angel}'s area of moves and represents it as a Board so the {@link Angel} can get the best move possible.
		  * @param p-The {@link Position} of the {@link Angel} on the {@link Board}.
		  * @return A Board value.
		  */
		 private Board PositionArea(Position pos ) {
				int up=pos.getCollumn()-K;
				int down=pos.getCollumn()+K;
				int left=pos.getRow()-K;
				int right=pos.getRow()+K;
				int startx;
				int starty;
				int finishx;
				int finishy;
				if(up<=0) {
					starty=0;
				}
				else starty=up;
				if(down>=board.getSize()) {
					finishy=board.getSize();
				}
				else finishy=down+1;
				if(left<=0) {
					startx=0;
				}
				else startx=left;
				if(right>=board.getSize()) {
					finishx=board.getSize();
				}
				else finishx=right+1;
				return new Board(this.board,startx,finishx,starty,finishy);
		 }
		 
		 /**
		  * This method calculates the winning rates of each possible move the {@link Angel} can perform (0-100) with 0 being the worst and 100 being the best move.
		  * @param p-The {@link Position} of the {@link Angel} on the {@link Board}.
		  * @return A double value.
		  */
		 private double GeneratePosibility(Position pos) {
			 double posibility=0.0;
			 int numOfAvailableMoves=availableMoves(pos);
			 Position angels_pos=findAngelPosition();
			 int numOfMoves=((2*K)+1)*((2*K)+1);
			 int maxDistanceFromPerimeter=board.getSize();
			 int distanceFromUpWin=pos.getRow();
			 int distanceFromDownWin=board.getSize()-pos.getRow()-1;
			 int distanceFromLeftWin=pos.getCollumn();
			 int distanceFromRightWin=board.getNumofCollumns()-pos.getCollumn()-1;
			 int smallestDistance=minimumDistance(distanceFromUpWin,distanceFromDownWin,distanceFromLeftWin,distanceFromRightWin);
			 if(board.getCell(pos).isEmpty() && ((Math.abs(pos.getRow()-angels_pos.getRow()))<=K )&& Math.abs(pos.getCollumn()-angels_pos.getCollumn())<=K && smallestDistance!=0){
				 double Moves=(numOfAvailableMoves/numOfMoves)*100;
				 double Distance=(1-smallestDistance/maxDistanceFromPerimeter)*100;
				 double weightOfMoves=0.4;
				 double weightOfDistance=0.6;
				 double Posibility=weightOfMoves*Moves+weightOfDistance*Distance;
				 posibility= Math.min(100, Posibility);
			 }
			 else if((!board.getCell(pos).isEmpty() )||((Math.abs(pos.getRow()-angels_pos.getRow()))>K && (Math.abs(pos.getCollumn()-angels_pos.getCollumn()))>K)){
				 posibility=0.0;
			}
			 else if((pos.getRow()==0 || pos.getCollumn()==0 || pos.getRow()==board.getSize()-1 || pos.getRow()==board.getSize()-1) && (Math.abs(pos.getRow()-angels_pos.getRow()))<=K && (Math.abs(pos.getCollumn()-angels_pos.getCollumn()))<=K && board.getCell(pos).isEmpty()) {
				 posibility=100.0;
			 }
			 return posibility;
		 }
		/**
		 * This method returns the minimum distance of the 4 provided.
		 * @param x-An integer value.
		 * @param y-An integer value.
		 * @param z-An integer value.
		 * @param w-An integer value.
		 * @return An integer value.
		 */
		 private int minimumDistance(int x,int y,int z,int w) {
			 int smallest=x;
			 if(y<smallest) {
				 smallest=y;
			 }
			 if(z<smallest) {
				 smallest=z;
			 }
			 if(w<smallest) {
				 smallest=w;
			 }
			 return smallest;
		 }
		
		 /**
		  * This method iterates through the two dimension array provided via the PosibilitiesOnAngelsArea() method and finds the biggest chance the {@link Angel} has got to win and returns the {@link Position} of the {@link Angel}'s best next move.
		  * @return A {@link Position} value.
		  */
		 Position findBiggestPosibility(Position pos) {
			 double[][] arr=PosibilitiesOnAngelsArea(pos);
			 double max_value=0.0;
			 int max_row=0;
			 int max_col=0;
			 for(int i=0;i<arr.length;i++) {
				 for(int j=0;j<arr[i].length;j++) {
					 if(max_value<arr[i][j]) {
						 max_value=arr[i][j];
						 max_row=i;
						 max_col=j;
					 }
				 }
			 }
			 return new Position(max_row,max_col);
			 
			 
		 }
				
		/**
		 * This method returns the {@link Player} as a String.
		 */
		public String toString() { 
			return playerType+" at: ("+p.getRow()+ ", "+p.getCollumn()+")";
		}
}
