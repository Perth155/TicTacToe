import java.util.Scanner;
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
	private static final int RESUMEGAME = 0; 
	private static final int TIE = 1; 
	private static final int XWINS = 2; 
	private static final int YWINS = 3; 
	

	private Player player1;
	private Player player2;
	private char[][] boardStatus; // 2D array 
	private int playerSelect; // The integer input of the player.
	private int currentState; // an int that contains the current state of game, used to determine game over.
	
	
	/**
	* Default constructor, sets up the board for a new game.
	**/
	public TicTacToeBoard()
	{
		player1 = new Player();
		player2 = new Player("Player2", 'O');
		currentState = 0;
		boardStatus = new char[ROW][COL];
		for(int i = 0; i<ROW; i++)
		{ 
			Arrays.fill(boardStatus[i], DEFAULT );  // Initialize every single element of the rows of array as '-'.
		}
	}


	public Player getPlayer1()
	{
		return player1; // Invokes the copy constructor of the Player Class.
	}

	public Player getPlayer2()
	{
		return player2;
	}

	public int getCurrentState()
	{
		return currentState;
	}


	/**
	* A method that is used to get input from player from the console and 
	* check if the entered input is a valid integer and the slot is not
	* already occupied. 
	* @param the symbol of the current user 'X' or 'O'
	* @param the name of the player. Player1/ Player2.  
	*/
	public void makeSelection(char playerSymbol, String playerName)
	{
		boolean validSelection = false;
		System.out.println(playerName+"'s Turn.");
		System.out.println("Choose a free spot on the board from 0-9 to place a "+playerSymbol+".");
		Scanner in = new Scanner(System.in);
		playerSelect = in.nextInt();
		validSelection = checkValidityOfMove(playerSelect);
		while(!validSelection)
		{	
			System.out.println(playerSelect+" Was an invalid selection by " +playerName+".\nPlease Choose a FREE spot between 0 to 9 only.");
			playerSelect = in.nextInt();
			validSelection = checkValidityOfMove(playerSelect);
		}

		adjustBoardStatus(playerSelect, playerSymbol);
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
	* Checks if the game needs to be terminated.
	* I.e. if one player wins the game. 
	*/
	public void terminateGame()
	{
		char[] simpleArray = new char[9]; // An 1D array representation of the 2D boardStatus array to compare diagonal. 
		int count = 0; // Accumulating variable to populate simpleArray in the nested FOR loop.


		for(int i = 0; i<ROW; i++)
		{
			// Check if a player has won horizontally eg. O-O-O.
			if((boardStatus[i][0] == boardStatus[i][1]) && (boardStatus[i][0]== boardStatus[i][2]) && (boardStatus[i][0] != DEFAULT))
			{
				if(boardStatus[i][0] == 'X')
					currentState = XWINS;
				else
					currentState = YWINS;
			}
			// Check if a player has won vertically. 
			else if((boardStatus[0][i] == boardStatus[1][i]) && (boardStatus[0][i] == boardStatus[2][i]) && (boardStatus[0][i] != DEFAULT))
			{
				if(boardStatus[0][i] == 'X')
					currentState = XWINS;
				else
					currentState = YWINS;
			}
			// Populate simpleArray with the rows and cols of the 2D array used for the board.
			for(int j = 0; j<COL; j++)
			{
				simpleArray[count] = boardStatus[i][j];
				count++;
			}

		}

		diagonalWinCheck(simpleArray); // Check if a player won diagonally.
	}


	/* 
	* Prints out the final outcome of the game to console.
	*/
	public void endGameResult()
	{
		if(currentState == XWINS)
			System.out.println("**GAME OVER!\n**Player1 wins! Winner: "+player1.getSymbol());
		else if(currentState == YWINS)
			System.out.println("**GAME OVER!**\n**Player2 wins! Winner: "+player2.getSymbol());
		else
			System.out.println("**GAME OVER!**\n**Match Tied**");
	}



	public void diagonalWinCheck(char[] inArray)
	{
		if((inArray[4] != '-') && ((inArray[0] == inArray[4] && inArray[4] == inArray[8]) || (inArray[4] == inArray[2] && 
			inArray[4] == inArray[6])))
		{
			if(boardStatus[1][1] == 'X')
					currentState = XWINS;
			else
					currentState = YWINS;
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