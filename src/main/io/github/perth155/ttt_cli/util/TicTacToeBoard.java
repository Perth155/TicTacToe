package io.github.perth155.ttt_cli.util;

import java.util.Arrays;

/**
* A class that manages the tic-tac-toe board.
* @author abraram (abrar.a.amin@gmail.com)
**/

public class TicTacToeBoard
{
	//class-fields
	private Symbol boardStatus[][]; // 2D array of chars.
	private int row;
	private int col;
		
	/**
	* Constructor, sets up an empty board for a new game.
	**/
	public TicTacToeBoard(int inRow, int inCol)
	{
		System.out.println("board size = " + inRow + " " + inCol);
		this.row = inRow;
		this.col = inCol;
		boardStatus = new Symbol[row][col];
		for(int i = 0; i<row; i++)
		{
			Arrays.fill(boardStatus[i], Symbol.EMPTY );  // Initialize every single element of the rows of array as '-'.
		}
	}

	/**
	 * Resets the game by setting every occupied slots in the 2D array to defaults.
	 */
	public void resetBoard()
	{
		for(int i = 0; i<row; i++)
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
		return((x>=0 && x<9) && (boardStatus[x/row][x%row] == Symbol.EMPTY));
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
		System.out.println("\n -----tic-tac-toe-----\n");  // Prints title of the game.

		for(int i = 0; i<boardStatus.length; i++)
		{
			for(int j = 0; j<boardStatus[i].length; j++)
			{
				if(j == 0)
					System.out.print("  ");
				if(boardStatus[i][j] == Symbol.EMPTY)
				{	
					if(mode == 1)
						System.out.print("       ");
					else
						System.out.print("  ["+count+"]  ");
				}
						
				else if(boardStatus[i][j] == Symbol.X)
				{
					System.out.print(Color.BLACK+Color.WHITE_BG+"   X   "+Color.RESET);
				}

				else
				{
					System.out.print(Color.WHITE+Color.BLACK_BG+"   O   "+Color.RESET);
				}
				count++;
				
				if(j < boardStatus.length-1)
					System.out.print("| ");
				else
					System.out.print("  ");
			}
			if(i != boardStatus.length-1)
			{
				System.out.print("\n ");
				for(int k = 0; k < boardStatus.length-1; k++)
				{
					System.out.print("--------+");
				}
				System.out.print("--------\n");
			}
			else
				System.out.println("\n");
		}
	}



	/**
	* Converts an integer input from the user, an int between 0 to 8
	* to a valid position in the n*n 2D array, boardStatus 
	* .e. if user enters 5, gets interpret as 1,2 in 2D array.
	* @param x,  the integer that was entered.
	* @param y, the character representing either 'X' or 'O' - symbol the player is using.
	*/
	public void setBoard(int x, Symbol y)
	{
		boardStatus[x/row][x%row] = y;
	}


	/**
	 * Check if entered position is occupied 
	 * @param move the player's move
	 * @return true if free, false otherwise.
	 */
	public boolean checkIfOccupied(int move)
	{
		if(boardStatus[move/row][move%col] == Symbol.EMPTY)
			return true;
		return false;
	}


	/**
	* A method that checks if the board is full.
	* @return True if board is full, false otherwise.
	*/
	public boolean isFull()
	{
		for(int i = 0; i<row; i++)
		{
			for(int j = 0; j<col; j++)
			{
				if(boardStatus[i][j] == Symbol.EMPTY)
				{
					return false;
				}
			}
		}

		return true;
	}

}
