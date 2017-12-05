package ttt_cli.agent;

import ttt_cli.util.Symbol;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
* An Player class that takes in command line input through stdio from human user.
*/
public class RandomAgent extends TTTAgent
{

	public RandomAgent()
	{
		super("RandomAgent");
	}


	/**
	 * In the 2D array, return the indices of the free slots on the game board,
	 * in 1D form. Subsequently a random selection will be made from this list.
	 * @param g the gameboard
	 * @return a list containing 1D index of available positions.
	 */
	private List<Integer> getFreeSpot(Symbol g[][])
	{
		List<Integer> availablePos = new LinkedList<Integer>();
		for(int i = 0; i < g.length; i++)
		{
			for(int j = 0; j < g[0].length; j++)
			{
				if(g[i][j] == Symbol.EMPTY) // Empty slot, include the 1D index.
					availablePos.add(g[0].length*i+j);
			}
		}
		return availablePos;
	}

	/**
	 * Return the movement of the player, to be implemented by the agent
	 * overriding this method, this method will be called for each players turn
	 * Console input will be read using a Scanner object.  
	 * @return the movement of the player, an integer from 0 to 8.
	 */
	@Override 
	public int move(Symbol gameBoard[][])
	{
		Random rand = new Random();
		List<Integer> playableSlotList = getFreeSpot(gameBoard);
		int r = rand.nextInt(playableSlotList.size());
		return playableSlotList.remove(r);
	}

	@Override
	public void destroy()
	{
		// Not required to do anything.
	}
}
