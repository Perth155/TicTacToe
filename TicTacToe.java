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
			newBoard.makeSelection(newBoard.getPlayer1().getSymbol(), newBoard.getPlayer1().getName());
			newBoard.drawBoard();
			newBoard.terminateGame();

			if(newBoard.isFull() || newBoard.getCurrentState() != 0) //break out of the while loop only if the game is over. 
				break;

			newBoard.makeSelection(newBoard.getPlayer2().getSymbol(), newBoard.getPlayer2().getName());
			newBoard.drawBoard();
			newBoard.terminateGame();

		}


		newBoard.endGameResult();
	}

}