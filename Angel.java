package hw2;
import java.util.Scanner;

/**
 * This class implements the {@link Angel}.
  * @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/03/2025
 */
public class Angel extends Player{

	
	
	/**
	 * The Constructor of the  {@link Angel}.
	 * @param power- An integer value.
	 * @param board- A{ @link Board} type.
	 */
	public Angel(Board board,int power) {
		super(board,'A',power);
		int size=board.getSize();
		p= new Position(size/2,size/2);
	}
/**
 * This method moves the {@link Angel} to the position that the user provides.The method checks if the position provided by the user is valid and if not returns error message and asks for a new position.
 * This method also checks if the {@link Demon} or the {@link Angel} are in a winning position and stops the game otherwise it moves the {@link Angel} to the position provided by the user.
 */
	public void movePlayer(Scanner c) {
		Scanner scan=c;
		boolean correct_pos;
		System.out.println("x ? Angel =");
		int x=scan.nextInt();
		System.out.println("y ? Angel =");
		int y=scan.nextInt();
		x--;
		y--;
		Position pos=new Position(x,y);
		do {
			correct_pos=true;
			if(pos.getRow()<=-2 || pos.getCollumn()<=-2) {
				System.out.println("Game has been terminated!");
				System.exit(0);
			}
			while(pos.getRow()>=this.board.getSize() || pos.getCollumn()>=this.board.getSize() || Math.abs(pos.getRow()-this.p.getRow())>K || Math.abs(pos.getCollumn()-this.p.getCollumn())>K) {
				System.out.println("Incorrect position! You cannot move to that spot!Give new coordinates! ");
				System.out.println("x ? Angel =");
				x=scan.nextInt();
				System.out.println("y ? Angel =");
				y=scan.nextInt();
				x--;
				y--;
				pos=new Position(x,y);
			}
			if(! board.getCell(pos).isEmpty() ) {
				correct_pos=false;
				System.out.println("Incorrect position! You cannot move to that spot!Give new coordinates!");
				System.out.println("x ? Angel =");
				x=scan.nextInt();
				System.out.println("x ? Angel =");
				y=scan.nextInt();
				x--;
				y--;
				pos=new Position(x,y);
			}
		}while(!correct_pos);
		if(correct_pos) {
			if(x==0 || x+1==board.getSize() || y==0 || y+1==board.getSize()) {
				board.setCell(this.p, 'E');
				board.setCell(pos, 'A');
				System.out.println("x= "+(x+1)+"  y= "+(y+1));
				p=new Position(pos);
				System.out.println(board.toString());
				System.out.println("Angel wins! \n End of the game.");
				System.exit(0);
			}
			else {
				board.setCell(this.p, 'E');
				board.setCell(pos, 'A');
				System.out.println("x= "+(x+1)+"  y= "+(y+1));
				p=new Position(pos);
				System.out.println(board.toString());
			}
		}
	}
	/**
	 * This method returns false if there is any available moves for the {@link Angel} by checking the area that it's power can reach. If the{@link Angel} does not have any available moves in his area then the losing state returns true. 
	 * @return A boolean value.
	 */
	protected boolean losingState() {
		int up=this.p.getCollumn()-K;
		int down=this.p.getCollumn()+K;
		int left=this.p.getRow()-K;
		int right=this.p.getRow()+K;
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
					return false;
				}
			}
		}	
		return true;
	}

	
	/**
	 * This method moves the {@link Angel} on its own.It makes decisions based on the players distance from the winning cells and how many blocked cells that area has.
	 * If there is only one available move then it finds that specific move and executes it. If there is more more that one then it calculates the winning rate of each move and returns the best one available.
	 */
	public void moveAutoPlayer() {
		System.out.println("Angel");
		Position next_move=null;
		if(this.availableMoves(this.p)==1) {
			next_move=this.findOnlyAvailableMove(this.p);
		}
		else {
			next_move=this.findBiggestPosibility(this.p);
		}
		System.out.println("x ="+(next_move.getRow()+1)+"  y = "+(next_move.getCollumn()+1));
		board.setCell(this.p,'E');
		board.setCell(next_move, 'A');
		this.p=new Position(next_move);
		System.out.println(board.toString());
		if(next_move.getRow()==0 ||next_move.getRow()==board.getSize()-1 || next_move.getCollumn()==0 || next_move.getCollumn()== board.getSize()-1) {
			System.out.println("Angel wins!\nEnd of Game!");
			System.exit(0);
		}
	}
}
