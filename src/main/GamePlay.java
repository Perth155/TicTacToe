package main;

import entities.Player;
import entities.Enemy;
import entities.TicTacToeBoard;

/**
 * A class that controls the game play mechanics of the tic-tac-toe game.
 * @author Abrar Amin
 */
public class GamePlay
{
	//Constants
	private static final char DEFAULT = '-'; // Default value to be stored in the array of chars.
	private static final int RESUMEGAME = 0;
	private static final int XWINS = 2;
	private static final int OWINS = 3;

	// Class fields
	private Player p1;
	private Enemy p2;
	private TicTacToeBoard board;
	private int currentState; // Describes the state of the current game, compare with constants.


	public GamePlay()
	{
			p1 = new Player();
			p2 = new Enemy(1,"Player2", 'X');
			board = new TicTacToeBoard();
			currentState = RESUMEGAME;
	}

	// Accessors
	public int getCurrentState()
	{
			return currentState;
	}

	public Player getPlayer(int i)
	{
			if(i == 1)
				return p1;
			else
				return p2;
	}
	

	public TicTacToeBoard getBoard()
	{
			return board;
	}

	public void resetGame()
	{
			board.resetBoard();
			currentState = RESUMEGAME;
			if(p2.getMode()==2)
					p2.setCompRandArray(); //Reset the computer movement array.
	}

	/**
	 * Initialize the user names and the char they will use (i.e. X or O)
	 * @param inName1 the name of player 1 as a String
	 * @param inSymbol1 the symbol that player 1 input.
	 * @param inName2 the name of player 2 as a String
	 * @param inSymbol2 the symbol of player 2, set by default after player 1's choice.
	 */
		public void setUpGame(String inName1, char inSymbol1, String inName2, char inSymbol2, int inMode)
		{
				p1.setPlayer(inName1, inSymbol1);
				p2.setPlayer(inName2, inSymbol2);
		}



	/**
	* A method checks if the entered input is a valid integer and the slot is not
	* already occupied.
	* @param Player class object - the Player object that has made the move.
	*/
	public void updateBoard(int selection, Player inPlayer)
	{
	 		board.adjustBoardStatus(selection, inPlayer.getSymbol());
	}


	/**
	* Checks if the game needs to be terminated.
	* I.e. if one player wins the game.
	*/
	public void terminateGameCheck()
	{
		char[] simpleArray = new char[9]; // An 1D array representation of the 2D boardStatus array to compare diagonal.
		int count = 0; // Accumulating variable to populate simpleArray in the nested FOR loop.
		char[][] boardArray = board.getBoardStatus();

		for(int i = 0; i<3; i++)
		{

			// Check if a player has won horizontally eg. O-O-O.
			if((boardArray[i][0] == boardArray[i][1]) && (boardArray[i][0]== boardArray[i][2]) && (boardArray[i][0] != DEFAULT))
			{
				if(boardArray[i][0] == 'X')
					currentState = XWINS;
				else
					currentState = OWINS;
			}
			// Check if a player has won vertically.
			else if((boardArray[0][i] == boardArray[1][i]) && (boardArray[0][i] == boardArray[2][i]) && (boardArray[0][i] != DEFAULT))
			{
				if(boardArray[0][i] == 'X')
					currentState = XWINS;
				else
					currentState = OWINS;
			}
			// Populate simpleArray with the rows and cols of the 2D array used for the board.
			for(int j = 0; j<3; j++)
			{
				simpleArray[count] = boardArray[i][j];
				count++;
			}

		}

		diagonalWinCheck(simpleArray, boardArray); // Check if a player wins diagonally.
	}


	private void diagonalWinCheck(char[] inArray, char[][] in2DArray)
	{
		if((inArray[4] != '-') && ((inArray[0] == inArray[4] && inArray[4] == inArray[8]) || (inArray[4] == inArray[2] &&
			inArray[4] == inArray[6])))
		{
			if(in2DArray[1][1] == 'X')
					currentState = XWINS;
			else
					currentState = OWINS;
		}
	}


	public void endGameResult()
	{
		if(currentState == XWINS)
		{
			p1.setPoint(p1.getPoints()+1);
			System.out.println("GAME OVER!\nPlayer1 wins!  : "+p1.getName()+" "+p1.getSymbol()+" Points: "+p1.getPoints());
			System.out.println("GAME OVER!\nPlayer2 loses! : "+p2.getName()+" "+p2.getSymbol()+" Points: "+p2.getPoints());
		}
		else if(currentState == OWINS)
		{
			p2.setPoint(p2.getPoints()+1);
			System.out.println("GAME OVER!\nPlayer2 wins!  : "+p2.getName() + " " +p2.getSymbol()+" Points: "+p2.getPoints());
			System.out.println("GAME OVER!\nPlayer1 loses! : "+p1.getName()+" "+p1.getSymbol()+" Points: "+p1.getPoints());
		}
		else
			System.out.println("**GAME OVER!**\n**Match Tied**");
	}



}
