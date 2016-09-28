/**
* A class that manages the Player Info.
* @author Abrar Amin
*/
public class Player
{
	private String name; // Name chosen by the player.
	private int points;  // The final points of the player. Each win increments point by 1.
	private char symbol;  // 'X' or 'O' - what the player chooses.
	private boolean isComputer;


	/**
	* Default Constructor.
	*/
	public Player()
	{
		name = "Player1";
		points = 0;
		symbol = 'X';
		isComputer = false;
	}

	public Player(String inName, char inSymbol)
	{
		setPlayer(inName, inSymbol);
		points = 0;
	}
	
	/**
	* Copy Constructor.
	*/
	public Player(Player inPlayer)
	{
		name = inPlayer.getName();
		symbol = inPlayer.getSymbol();
		points = inPlayer.getPoints();
	}


	public void setPlayer(String inName, char inSymbol)
	{
		name = inName;

		if(inSymbol=='X' || inSymbol == 'O')
			symbol = inSymbol;
		else
			System.out.println("Invalid Symbol Selection. Must choose a 'X' or a 'O'.");
	}


	public String getName()
	{
		return this.name;
	}

	public int getPoints()
	{
		return this.points;
	}

	public char getSymbol()
	{
		return this.symbol;
	}

	/**
	* Method is used to give a string output of the information of player.
	* @return String containing information of the player. 
	*/
	public String toString()
	{
		String outString = ("Player's name: "+name+" Points: "+points+" Symbol: "+symbol+".");
		return outString;
	}

}
