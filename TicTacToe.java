/**
* Contains the main method used to run this game.
* @author Abrar Amin
*/

public class TicTacToe
{

	public static void main(String[] args)
	{

		TicTacToeBoard newBoard = new TicTacToeBoard();
		newBoard.drawBoard(); 
		
		while(newBoard.getCurrentState() == 0)
		{
			newBoard.validateSelection(newBoard.getPlayer1());
			newBoard.drawBoard();
			newBoard.terminateGame();

			if(newBoard.isFull() || newBoard.getCurrentState() != 0) //break out of the while loop only if the game is over. 
				break;

			newBoard.validateSelection(newBoard.getPlayer2());
			newBoard.drawBoard();
			newBoard.terminateGame();

		}


		newBoard.endGameResult();
	}

}
