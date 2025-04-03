package hw2;
import java.util.Scanner;

/**
 * This class implements a {@link ManualGame}.
 *  @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/03/2025
 */
public class ManualGame extends Game{

	/**
	 * The constructor of the {@link ManualGame}.
	 * @param size-An integer value.
	 * @param K-An integer value.
	 */
	public ManualGame(int size,int K) {
		super(size,K);
	}
	
	/**
	 * This method plays the {@link Game} with the {@link Angel} and {@link Demon} playing one after the other until one wins.
	 */
	public void play() {
		int turn=0;
		Scanner scan=new Scanner(System.in);
		System.out.println(board.toString());
		do {
			if(turn==1) {
				Demon.placeDemon(scan);
			}
			else if(turn%2==0) {
				Angel.movePlayer(scan);
			}
			else {
				Demon.movePlayer(scan);
			}
			turn++;
		}while(!Angel.losingState());
		System.out.println("Devil wins! \n End of the game!");
		
	}
}
