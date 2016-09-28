import java.util.Arrays;

/**
* A class that manages the tic-tac-toe board
* @author Abrar Amin
**/


public class TicTacToeBoard
{

	private static final String HEADER = "\n ______________________\n";   // Used to Print the board header when the draw() method is invoked. 
	private static final int ROW = 3; // Num of rows used in the 3*3 array of chars.
	private static final int COL = 3; // Num of cols used in the 3*3 array of characters.
	private static final char DEFAULT = '-'; // Default value to be stored in the array of chars.
	
	private char[][] boardStatus; // 2D array 
	private int playerSelect; // The integer input of the player.
	
	
	/**
	* Default constructor, sets up the board for a new game.
	**/
	public TicTacToeBoard()
	{
		boardStatus = new char[ROW][COL];
		for(int i = 0; i<ROW; i++)
		{ 
			Arrays.fill(boardStatus[i], DEFAULT );  // Initialize every single element of the rows of array as '-'.
		}
	}

	// Accessor. 
	public char[][] getBoardStatus() 
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
		return((x>=0 && x<9) && (boardStatus[x/3][x%3] == '-')); 
	}


	/**
	* Prints out the current state of the tic-tac-toe board to the console.
	* Using nexted FOR loops to iterate through the 2D array containing the 
	* current boardStatus. 
	*/
	public void drawBoard()
	{
		//refresh(); // Clears the console
		int count = 0;

		System.out.print("\n ------tic-tac-toe------");  // Prints title of the game.
		System.out.println(HEADER);

		for(int i = 0; i<boardStatus.length; i++)
		{
			System.out.print(" | ");
			for(int j = 0; j<boardStatus[i].length; j++)
			{
				if(boardStatus[i][j] == DEFAULT)
				{
					System.out.print("     | ");
				}

				else if(boardStatus[i][j] == 'X')
				{
					System.out.print("  "+'X'+"  | ");
				}

				else
				{
					System.out.print("  "+'O'+"  | ");
				}
				count++;
			}
			
			System.out.println(HEADER);
		}
	}
	
	
	public void drawExpandedBoard()
	{
		//refresh(); // Clears the console
		int count = 0;

		System.out.print("\n ------tic-tac-toe------");  // Prints title of the game.
		System.out.println(HEADER);

		for(int i = 0; i<boardStatus.length; i++)
		{
			System.out.print(" | ");
			for(int j = 0; j<boardStatus[i].length; j++)
			{
				if(boardStatus[i][j] == DEFAULT)
				{
					System.out.print(" <"+count+"> | ");
				}

				else if(boardStatus[i][j] == 'X')
				{
					System.out.print("  "+'X'+"  | ");
				}

				else
				{
					System.out.print("  "+'O'+"  | ");
				}
				count++;
			}
			
			System.out.println(HEADER);
		}
	}



	/**
	* Converts the console input from the user- an int between 0 to 9
	* to a valid position in out 2D array - boardStatus. 
	*/
	public void adjustBoardStatus(int x, char y)
	{
		boardStatus[x/3][x%3] = y;
	}


	/**
	* A method that returns true only if the board is now full, false otherwise. 
	* @return True if board is full.
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
