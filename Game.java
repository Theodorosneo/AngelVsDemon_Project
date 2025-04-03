package hw2;
import java.util.Scanner;



/**
 * This class implements a {@link Game}.It asks the user if he wants the game to be played manually or automatic as well as the size of the {@link Board} and power of the {@link Angel}.
 * The program makes the necessary checks and if needed shows error messages.
 * The automated version of the {@link Angel} finds the best move for the {@link Angel} by adding double values to each position based on their distance from the perimeter as well as the numbers of available cells from each position.
 * The {@link Demon} uses the same functions because he tries to stop the {@link Angel} to reach the end of the {@link Board} so he checks and chooses the best next move that the {@link Angel} can make and makes it himself so he can block that exact move.
 * @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/03/2025
 * 
 */
public abstract class Game  {
	
	/**
	 * Two {@link Player} types: One for the {@link Angel} and on for the {@link Demon}.
	 * One {@link Board} that the game is going to be played on and size is the size of the {@link Board} the user will provide.
	 */
	
	protected Angel Angel;
	protected Demon Demon;
	protected Board board;
	
	/**
	 * Constructor of the Game that will be used as super from the derived classes as the class {@link Game} is an abstract type.
	 * @param size- An integer value.
	 * @param K-An integer value. (The power of the {@link Angel}).
	 */
	public Game(int size,int K) {
		if(size<0){
			System.out.println("Fatal Error! The Game has been terminated!");
			System.exit(0);
		}
		else {
			board=new Board(size);
			Angel=new Angel(board,K);
			Demon=new Demon(board,K);
		}
	}
	
	/**
	 * Abstract play method that will be implemented by the derived classes.
	 */
	public abstract void play();
	
	
	/**
	 * This is the main function of the {@link Game}.
	 * It gets the {@link Game} mode along with the {@link Board} size and the power of the {@link Angel} and starts a {@link Game } based on the decisions given by the user.    
	 * @param args- The arguments of the main.
	 */
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println(" Welcome to the Angel  game!\n Choose a way to play: \n 1 - You play versus another user. \n 2 - Computer plays versus computer. \n Enter a category: ");
		int gamemode=scan.nextInt();
		while(gamemode<1 || gamemode>2) {
				if(gamemode<0) {
					System.out.println(" Fatal Error.Game has been terminated!");
					System.exit(0);
				}
				else {
					System.out.println(" Wrong game mode chosen! Choose gamemode :");
					gamemode=scan.nextInt();
				}
		}
		System.out.println(" Size of the board ?");
		int size=scan.nextInt();
		while(size<=4 ) {
			if(size<0) {
				System.out.println(" Fatal Error.Game has been terminated!");
				System.exit(0);
			}
			else {
				System.out.println(" Wrong board size! \n Size of the board ?");
				size=scan.nextInt();
			}
		}
		System.out.println(" Power of the Angel ?");
		int power=scan.nextInt();
		int size_adaption;
		if(size%2==0) {
			size_adaption=(size/2)-2;
		}
		else {
			size_adaption=(size/2)-1;
		}
		while(power<=0 ||  power>size_adaption) {
			if(power<0 ) {
				System.out.println("Fatal Error.Game has been terminated!");
				System.exit(0);
			}
			else {
				System.out.println(" Wrong power ! \n Power of the Angel ?");
				power=scan.nextInt();
			}
		}
		System.out.println("Begin the game..."+"\n");
		if(gamemode==1) {
			ManualGame game=new ManualGame(size,power);
			game.play();
		}
		else if(gamemode==2) {
			AutoGame game = new AutoGame(size,power);
			game.play();
		}
		scan.close();
	}
}
