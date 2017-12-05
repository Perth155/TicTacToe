package ttt_cli.util;

import java.util.Arrays;

/**
* A class that manages the tic-tac-toe board.
* @author abraram (abrar.a.amin@gmail.com)
**/

public class TicTacToeBoard
{
	//constants
	private static final int ROW = 3; // Num of rows used in the 3*3 array of chars.
	private static final int COL = 3; // Num of cols used in the 3*3 array of characters.
	private static final Symbol DEFAULT = Symbol.EMPTY; // Default value to be stored in the array of chars.

	//class-fields
	private Symbol boardStatus[][]; // 2D array of chars.
		
	/**
	* Constructor, sets up an empty board for a new game.
	**/
	public TicTacToeBoard()
	{
		boardStatus = new Symbol[ROW][COL];
		for(int i = 0; i<ROW; i++)
		{
			Arrays.fill(boardStatus[i], Symbol.EMPTY );  // Initialize every single element of the rows of array as '-'.
		}
	}

	/**
	 * Resets the game by setting every occupied slots in the 2D array to defaults.
	 */
	public void resetBoard()
	{
		for(int i = 0; i<ROW; i++)
		{
			Arrays.fill(boardStatus[i], Symbol.EMPTY );  // Initialize every single element of the rows of array as '-'.
		}
	}

	

	/**
	 * Get the current instance of the game board. 
	 * @return a 2D array of Symbols representing the board status.
	 */
	public Symbol[][] getBoardStatus()
	{
		return boardStatus;
	}

	/**
	* Return true if the move in the board was a valid move.
	* False otherwise.
	* @return true only if player made a valid selection (0-9) and the slot is not occupied already.
	*/
	public boolean checkValidityOfMove(int x)
	{
		return((x>=0 && x<9) && (boardStatus[x/3][x%3] == Symbol.EMPTY));
	}


	/**
	* Prints out the current state of the tic-tac-toe board to the console
	* Using nested FOR loops to iterate through the 2D array containing the
	* current boardStatus.
	* @param mode, an integer that specifies whether to draw normal board or expanded board.
	*/
	public void drawBoard(int mode)
	{
		int count = 0;
		System.out.println("\n ----tic-tac-toe-----\n");  // Prints title of the game.

		for(int i = 0; i<boardStatus.length; i++)
		{
			for(int j = 0; j<boardStatus[i].length; j++)
			{
				if(j == 0)
					System.out.print("  ");
				if(boardStatus[i][j] == DEFAULT)
				{	
					if(mode == 1)
						System.out.print("     ");
					else
						System.out.print(" ["+count+"] ");
				}
						
				else if(boardStatus[i][j] == Symbol.X)
				{
					System.out.print("  "+'X'+"  ");
				}

				else
				{
					System.out.print("  "+'O'+"  ");
				}
				count++;
				
				if(j < 2)
					System.out.print("| ");
				else
					System.out.print("  ");
			}
			if(i != boardStatus.length-1)
				System.out.println("\n ------+------+------");
			else
				System.out.println("\n");
		}

	}

	
	/**
	 * A function that erases the drawn TicTacToe board to be called once a new turn 
	 * has been played and the board is to be re-rendered.
	 */
	public void eraseBoard()
	{
		int count = 9;
		for(int i = 0; i < count; i++)
		{
			System.out.print(String.format("\033[%dA", 1)); // Move up
			System.out.print("\033[2K"); // Erase line content
		}
	}


	/**
	* Converts an integer input from the user, an int between 0 to 8
	* to a valid position in the 3*3 2D array, boardStatus 
	* .e. if user enters 5, gets interpret as 1,2 in 2D array.
	* @param x,  the integer that was entered.
	* @param y, the character representing either 'X' or 'O' - symbol the player is using.
	*/
	public void setBoard(int x, Symbol y)
	{
		boardStatus[x/3][x%3] = y;
	}


	/**
	* A method that checks if the board is full.
	* @return True if board is full, false otherwise.
	*/
	public boolean isFull()
	{
		for(int i = 0; i<ROW; i++)
		{
			for(int j = 0; j<COL; j++)
			{
				if(boardStatus[i][j] == DEFAULT)
				{
					return false;
				}
			}
		}

		return true;
	}

}