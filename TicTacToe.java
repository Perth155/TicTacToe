import java.util.Scanner;
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
		int select;
		
		newGameStarter(game, s1);
		
		while(game.getCurrentState() == 0)
		{
			
			select = getSelection(game, s1, game.getPlayer1());
			System.out.println(game.getBoard().checkValidityOfMove(select));
			while(!(game.getBoard().checkValidityOfMove(select)))
			{
				System.out.println("** Invalid selection by "+game.getPlayer1().getName()+".");
				game.getBoard().drawExpandedBoard();
				select = getSelection(game, s1, game.getPlayer1());
			}
			game.updateBoard(select, game.getPlayer1());
			game.getBoard().drawBoard();
			game.terminateGameCheck();

			
			if(game.getBoard().isFull() || game.getCurrentState() != 0) //break out of the while loop only if the game is over. 
				break;

			
			select = getSelection(game, s1, game.getPlayer2());
			
			while(! game.getBoard().checkValidityOfMove(select))
			{
				System.out.println("** Invalid selection by "+game.getPlayer2().getName()+".");
				game.getBoard().drawExpandedBoard();
				select = getSelection(game, s1, game.getPlayer2());
			}
			
			game.updateBoard(select, game.getPlayer2());
			game.getBoard().drawBoard();
			game.terminateGameCheck();

		}


		game.endGameResult();
		System.exit(0);
	}
	
	
	private static void newGameStarter(GamePlay game, Scanner inScan) 
	{
		System.out.println("Player 1's name: ");
		String name1 = inScan.nextLine();
		System.out.println("Player 2's name: ");
		String name2 = inScan.nextLine();
		
		if(name1.equals(""))
			name1 = "player1";
		if(name2.equals(""))
			name2 = "player2";
		
		game.setUpGame(name1, 'X', name2, 'O');
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
