package io.github.perth155.ttt_cli.agent;

import io.github.perth155.ttt_cli.util.Symbol;
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
		int playLimit = (gameBoard.length*gameBoard[0].length)-1;
		System.out.print("[*] Select an unoccupied slot [0-"+playLimit+"]: ");
		int movement = sc.nextInt();

		while(movement < 0 || movement > playLimit) {
			try {
				System.out.println("Invalid input! try again.");
				movement = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid Input! Select an unoccupied slot [0-"+playLimit+"]: ");
			}
		}
		return movement;
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
