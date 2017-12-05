package ttt_cli.agent;

import ttt_cli.util.Symbol;
import java.util.Scanner;

/**
* An Player class that takes in command line input through stdio from human user.  
*/
public class HumanPlayer extends TTTAgent
{
	private Scanner sc;

	public HumanPlayer()
	{
		super("HumanPlayer");
		sc = new Scanner(System.in);
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
		System.out.print("[*] Select an unoccupied slot [0-8]: ");
		int movement = -1;

		while(true) {
			try {
				movement = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid Input! Select an unoccupied slot [0-8]: ");
				e.printStackTrace();
			}
			return movement;
		}
	}

	/**
	 * Method invoked end of the game to close Scanner.
	 */
	@Override
	public void destroy()
	{
		sc.close();
	}
}
