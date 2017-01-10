import java.util.ArrayList;

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
	private ArrayList<Integer> ComputerMovement;

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

	public Player(String inName, char inSymbol, boolean inIsComputer)
	{
		setPlayer(inName, inSymbol, inIsComputer);
		points = 0;
		isComputer = inIsComputer;
		ComputerMovement = new ArrayList<Integer>();
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


	
	public void setPlayer(String inName, char inSymbol, boolean isAI)
	{
		name = inName;

		if(inSymbol=='X' || inSymbol == 'O')
			symbol = inSymbol;
		else
			System.out.println("Invalid Symbol Selection. Must choose a 'X' or a 'O'.");
		isComputer = isAI;
		
		if(isAI)
			populateCompMovementArray();
	}
	
	public void setPoint(int inPoint)
	{
		points = inPoint;
	}
	

	public void populateCompMovementArray()
	{
		for(int i = 0; i<9; i++)
		{
			ComputerMovement.add(i);
		}
		
		System.out.println("Size of ArrayList= "+ComputerMovement.size());
			
	}
	
	/**
	 * A mutator that removes the specified number from the movement array because player made their move on that position, can't be repeated.
	 * @param i the integer to be removed. 
	 */
	public void setCompArray(int i)
	{
		int removeIndex = ComputerMovement.indexOf(i);
		ComputerMovement.remove(removeIndex);
		System.out.println(i+" was removed from movement index. Current size of arraylist = "+ComputerMovement.size());
		
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
	
	public boolean getIsComputer()
	{
		return this.isComputer;
	}
	
	public ArrayList<Integer> getCompArray()
	{
		return this.ComputerMovement;
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
