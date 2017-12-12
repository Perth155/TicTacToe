package io.github.perth155.ttt_cli.agent;

import io.github.perth155.ttt_cli.util.Symbol;

/**
* An abstract class for Player, any implementations of Agent must 
* extend this superclass. 
* @author: abraram (abrar.a.amin@gmail.com) 
*/

public abstract class TTTAgent
{

	private String name; // A name that must be hard-coded by each agent implementation. 
	private int points;  // The final points of the player. Each win increments point by 1.
	private Symbol symbol;  // 'X' or 'O' - what the player chooses.
	private Symbol oppSymbol; // 'X' or 'O' - what the opponent chooses.

	//Constructor
	public TTTAgent(String inName)
	{
		this.name = inName;
		this.points = 0;
		this.symbol = Symbol.X;
		this.oppSymbol = Symbol.O;
	}


	/**
	 * Update the number of points the player has. 
	 * @param inPoint the new value of point.
	 */
	public void setPoint(int inPoint)
	{
		this.points = inPoint;
	}

	public void setSymbol(Symbol s)
	{
		this.symbol = s;
		this.oppSymbol = Symbol.oppositeSymbol(s);
	}


	public String getName()
	{
		return this.name;
	}

	public int getPoints()
	{
		return this.points;
	}

	public Symbol getSymbol()
	{
		return this.symbol;
	}

	public Symbol getOppositeSymbol()
	{
		return this.oppSymbol;
	}


	/**
	* Return the movement of the player, to be implemented by the agent
	* overriding this method, this method will be called for each players turn. 
	* @param gb current state of game board given by a 2D array of size n*n.
	* @return the movement of the player, an integer from 0 to 8.
	*/
	public abstract int move(Symbol[][] gb);

	public abstract void destroy();

	/**
	* Method is used to give a string output of the information of player.
	* @return String containing information of the player.
	*/
	public String toString()
	{
		String outString = ("Player: "+name+" Points: "+points+" Symbol: "+symbol+".");
		return outString;
	}

}
