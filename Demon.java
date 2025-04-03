package hw2;
import java.util.Scanner;

/**
 * This class implements the {@link Demon}.
 *  @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/03/2025
 */
public class Demon extends Player{

	
	/**
	 * The constructor of the {@link Demon}.
	 * @param board-A {@link Board} type.
	 * @param power- An integer value.
	 */
	public Demon(Board board,int power) {
		super(board,'D',power);
	}
	
	/**
	 * This method places the {@link Demon} on the {@link Board} with the user giving the {@link Position}.
	 * @param c- A Scanner value.
	 */
	public void placeDemon(Scanner c) {
		Scanner scan=c;
		boolean correct_pos;
		System.out.println("x ? Demon =");
		int x=scan.nextInt();
		System.out.println("y ? Demon =");
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
			while(pos.getRow()>=this.board.getSize() || pos.getCollumn()>=this.board.getSize() ) {
				System.out.println("Incorrect position! You cannot move to that spot!Give new coordinates! ");
				System.out.println("x ? Demon =");
				x=scan.nextInt();
				System.out.println("y ? Demon =");
				y=scan.nextInt();
				x--;
				y--;
				pos=new Position(x,y);
			}
			if(! board.getCell(pos).isEmpty() ) {
				correct_pos=false;
				System.out.println("Incorrect position! You cannot move to that spot!Give new coordinates!");
				System.out.println("x ? Demon =");
				x=scan.nextInt();
				System.out.println("y ? Demon =");
				y=scan.nextInt();
				x--;
				y--;
				pos=new Position(x,y);
			}
		}while(!correct_pos);
		if(correct_pos) {
			board.setCell(pos, 'D');
			System.out.println("x= "+(x+1)+"  y= "+(y+1));
			this.p=new Position(pos);
			System.out.println(board.toString());
		}
	}
	
	/**
	 * This method moves the {@link Demon} manually.It asks the user to give the coordinates to put the {@link Demon}.
	 * If the coordinates are invalid then asks the user to give different coordinates and checks for validation again.
	 */
	
	public void movePlayer(Scanner c) {
		Scanner scan=c;
		boolean correct_pos;
		System.out.println("x ? Demon =");
		int x=scan.nextInt();
		System.out.println("y ? Demon =");
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
			while(pos.getRow()>=this.board.getSize() || pos.getCollumn()>=this.board.getSize() ) {
				System.out.println("Incorrect position! You cannot move to that spot!Give new coordinates! ");
				System.out.println("x ? Demon =");
				x=scan.nextInt();
				System.out.println("y ? Demon =");
				y=scan.nextInt();
				x--;
				y--;
				pos=new Position(x,y);
			}
			if(! board.getCell(pos).isEmpty() ) {
				correct_pos=false;
				System.out.println("Incorrect position! You cannot move to that spot!Give new coordinates!");
				System.out.println("x ? Demon =");
				x=scan.nextInt();
				System.out.println("y ? Demon =");
				y=scan.nextInt();
				x--;
				y--;
				pos=new Position(x,y);
			}
		}while(!correct_pos);
		if(correct_pos) {
			board.setCell(this.p, 'X');
			board.setCell(pos, 'D');
			System.out.println("x= "+(x+1)+"  y= "+(y+1));
			this.p=new Position(pos);
			System.out.println(board.toString());
		}
		
	}
	
	/**
	 * This method moves the {@link Demon} on its own. It calculates the best move for the {@link Angel} and executes it leaving the {@link Angel} with a worst choice or not even one at all.
	 * If there is only one available move for the {@link Angel} then the {@link Demon} moves to that exact {@link Cell}.
	 */
	public void moveAutoPlayer() {
		System.out.println("Demon");
		Position angels_pos=findAngelPosition();
		Position next_move=null;
		if(this.availableMoves(angels_pos)==1) {
			next_move=this.findOnlyAvailableMove(angels_pos);
		}
		else {
			next_move=this.findBiggestPosibility(angels_pos);
		}
		System.out.println("x ="+(next_move.getRow()+1)+"  y = "+(next_move.getCollumn()+1));
		board.setCell(this.p,'X');
		board.setCell(next_move, 'D');
		this.p=new Position(next_move);
		System.out.println(board.toString());
	}
	
	
	/**
	 * This method moves the {@link Demon} on its own. It calculates the best move for the {@link Angel} and executes it leaving the {@link Angel} with a worst choice or not even one at all.
	 * If there is only one available move for the {@link Angel} then the {@link Demon} moves to that exact {@link Cell}.
	 * The only difference with the moveAutoPlayer method is that this one places the {@link Demon} on the board where the other method moves the {@link Demon} and makes it's previous {@link Cell} blocked.
	 */
	public void autoPlaceDemon() {
		System.out.println("Demon");
		Position angels_pos=findAngelPosition();
		Position next_move=null;
		if(this.availableMoves(angels_pos)==1) {
			next_move=this.findOnlyAvailableMove(angels_pos);
		}
		else {
			next_move=this.findBiggestPosibility(angels_pos);
		}
		System.out.println("x ="+(next_move.getRow()+1)+"  y = "+(next_move.getCollumn()+1));
		board.setCell(next_move, 'D');
		this.p=new Position(next_move);
		System.out.println(board.toString());
	}
}
