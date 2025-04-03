package hw2;

/**
 * This class implements the {@link AutoGame}.
 *  @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/03/2025
 */
public class AutoGame extends Game{

	/**
	 * The constructor of the {@link AutoGame}.
	 * @param size-An integer value.
	 * @param K-An integer value.
	 */
	public AutoGame(int size, int K) {
		super(size,K);
	}
	
	/**
	 * This method plays the {@link Game} with the {@link Angel} and {@link Demon} playing one after the other until one wins.
	 * This is the automated version of the method because the {@link Angel} and {@link Demon} are making automated moves without any human interfering.
	 */
	
	public void play() {
		int turn=0;
		System.out.println(board.toString());
		do {
			if(turn==1) {
				Demon.autoPlaceDemon();
			}
			else if(turn%2==0) {
				Angel.moveAutoPlayer();
			}
			else {
				Demon.moveAutoPlayer();
			}
			turn++;
		}while(!Angel.losingState());
		System.out.println("Devil wins!/n End of the game!");
	}
	
}
