import java.util.Scanner;
import java.util.concurrent.TimeUnit;
/**
* Contains the main method used to run this game.
* @author Abrar Amin
*/

public class TicTacToe
{	

	public static void main(String[] args) 
	{
		GamePlay game = new GamePlay();
		game.getBoard().drawExpandedBoard();
		Scanner s1 = new Scanner(System.in);
		newGameStarter(game, s1);
		boolean playOn = true;
		
		while(playOn)
		{
			int playerOneSelection = movement(game, 1, s1);

			if(game.getBoard().isFull() || game.getCurrentState() != 0) // Check if game is over. 
				playOn = checkReset(game, s1); // Call reset option
			if(!playOn) //Break out of the while loop if playOn is no longer true. 
				break;
			if(game.getPlayer(2).getIsComputer()) //If the game is player vs computer.
				compMovement(game, s1, playerOneSelection);
			else
				movement(game, 2, s1);
			
			if(game.getBoard().isFull() || game.getCurrentState() != 0) // Check if game is over. 
				playOn = checkReset(game, s1); // Call reset option
		}
		System.exit(0);
	}
	
	private static boolean checkReset(GamePlay game, Scanner s1) 
	{
		game.endGameResult();
		System.out.println("Play Again? [Y/n]");
		String resetSelection = s1.nextLine();
			
		if(!(resetSelection.equals("N")|| resetSelection.equals("n") || resetSelection.equals("No")))
		{
			game.resetGame();
			game.getBoard().drawBoard();
			return true;
		}
		
		return false;
	}

	/**
	 * Ensures valid movement is made by human player. Updates the board if so. 
	 * Checks if the game should be terminated. 
	 * @param game, GamePlay class object. 
	 * @param i, an integer that determines the player number (i.e. 1 or 2).
	 * @param inScan, Scanner class object to read console inputs from the player.  
	 * @return select - the selection made by the player. 
	 */
	private static int movement(GamePlay game, int i, Scanner inScan) 
	{
		int select = getSelection(game, inScan, game.getPlayer(i));
		
		while(!(game.getBoard().checkValidityOfMove(select)))
		{
			System.out.println("** Invalid selection by "+game.getPlayer(i).getName()+".");
			game.getBoard().drawExpandedBoard();
			select = getSelection(game, inScan, game.getPlayer(i));
		} 
		game.updateBoard(select, game.getPlayer(i));
		game.getBoard().drawBoard();
		game.terminateGameCheck();		
		
		return select;
	}
	
	
	public static void compMovement(GamePlay game, Scanner inScan, int selection) 
	{
		try 
		{
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) 
		{
			//
		}
		game.getPlayer(2).setCompArray(selection);
		int compIndex = ((int)(Math.random()*100.0))%(game.getPlayer(2).getCompArray().size()); // Returns a random number within the index bounds of ArrayList.
																					    // Used for computer's movement. 
		System.out.println(compIndex+"");
		int playerTwoSelection = (int)game.getPlayer(2).getCompArray().get(compIndex);
		System.out.println(playerTwoSelection+"");
		game.updateBoard(playerTwoSelection, game.getPlayer(2));
		game.getBoard().drawBoard();
		game.getPlayer(2).setCompArray(playerTwoSelection); //Remove the selection that was made from the computer movement array.
		game.terminateGameCheck();
	}
	
	
	/**
	 * Prints introduction, handles names of players and selection of modes.
	 * @param Scanner for console input for player names and mode.
	 * @param game, a GamePlay class object that handles the two players. 
	 */
	private static void newGameStarter(GamePlay game, Scanner inScan) 
	{
		System.out.println("---------------Select [1/2]------------------");
		System.out.println("1. Player1 vs Computer (Randomised)");
		System.out.println("2. Player1 vs Player2");
		System.out.println("---------------------------------------------\nSelection:");
		int mode = inScan.nextInt();
		inScan.nextLine();
		
		System.out.println("Player 1's name: ");
		String name1 = inScan.nextLine();
	
		if(name1.equals(""))
			name1 = "player1"; //Prevent "" named players to avoid confusion.
	
		String name2;
		if(mode != 1) // Player1 vs Player2
		{
			System.out.println("Player 2's name: ");
			name2 = inScan.nextLine();
			if(name2.equals(""))
				name2 = "player2";  //Handles empty string names.
			game.setUpGame(name1, 'X', name2, 'O', false);
		} 
		else 
		{
			name2 = "Computer"; //Player1 vs PC
			System.out.println("Opponent's name was set to : 'Computer'");
			game.setUpGame(name1, 'X', name2, 'O', true);
		}
		
	}


	/**
	 * Get the selection as an int between 1 to 8, to be passed to GamePlay object to validate. 
	 * @param game
	 * @param inScan
	 */
	private static int getSelection(GamePlay game, Scanner inScan, Player currentPlayer) 
	{
		int selection;
		System.out.println(currentPlayer.getName()+"'s Turn.");
		System.out.println("Enter an unoccupied slot between 0 and 8, or h for help.");
		
		
		
		String select = inScan.nextLine();
		
		while(select.equals("h") || select.equals("H"))
		{
			System.out.println("-Help Menu Invoked-");
			game.getBoard().drawExpandedBoard();
			System.out.println("Enter an unoccupied slot between 0 and 8, or h for help.");
			select = inScan.nextLine();
		}
		
		try
		{
			selection = Integer.parseInt(select); // Now we need to check if the selection by the player is valid. Note str to int.
		}
		catch (NumberFormatException n)
		{
			selection = 100;
		}
		
		return  selection;	 
	}




}
