package main;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import entities.AIPlayer;
import entities.Player;

/**
* Contains the main method used to run this game.
* @author Abrar Amin
*/

public class TicTacToe
{

			public static void main(String[] args)
			{
					GamePlay game = new GamePlay();
					game.getBoard().drawBoard(2);
					Scanner s1 = new Scanner(System.in);
					int mode = newGameStarter(game, s1);
					boolean playOn = true;
					if (mode>2)
						playOn = false;

					while(playOn)
					{
							int playerOneSelection = movement(game, 1, s1);
							if(game.getBoard().isFull() || game.getCurrentState() != 0) // Check if game is over.
							{
								playOn = checkReset(game, s1); // Call reset option
								if(playOn)
									playerOneSelection = -1;
							}
							if(!playOn) //Break out of the while loop if playOn is no longer true.
								break;
							if(mode == 2)//If the game is player vs computer.
							{
								aiMove(game);
							}
							else
								movement(game, 2, s1);

							if(game.getBoard().isFull() || game.getCurrentState() != 0) // Check if game is over.
								playOn = checkReset(game, s1); // Call reset option
					}
					System.exit(0);
			}
			
			

			private static boolean checkReset(GamePlay game, Scanner s1)
			{
					game.endGameResult();
					System.out.println("Play Again? [Y/n]");
					String resetSelection = s1.nextLine();

					if(!(resetSelection.equals("N")|| resetSelection.equals("n") || resetSelection.equals("No")))
					{
						game.resetGame();
						game.getBoard().drawBoard(1);
						return true;
					}
					return false;
			}

			/**
			 * Ensures valid movement is made by human player. Updates the board if so.
			 * Checks if the game should be terminated.
			 * @param game, GamePlay class object.
			 * @param i, an integer that determines the player number (i.e. 1 or 2).
			 * @param inScan, Scanner class object to read console inputs from the player.
			 * @return select - the selection made by the player.
			 */
			private static int movement(GamePlay game, int i, Scanner inScan)
			{
				int select = getSelection(game, inScan, game.getPlayer(i));
				while(!(game.getBoard().checkValidityOfMove(select)))
				{
					System.out.println("Invalid Selection.");
					game.getBoard().drawBoard(2);
					select = getSelection(game, inScan, game.getPlayer(i));
				}
				game.updateBoard(select, game.getPlayer(i));
				game.getBoard().drawBoard(1);
				game.terminateGameCheck();

				return select;
			}


			
			private static void aiMove(GamePlay game)
			{
				System.out.println("AI's Turn...");
				try
				{
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				((AIPlayer)game.getPlayer(2)).setGameBoard(game.getBoard().getBoardStatus());
				((AIPlayer)game.getPlayer(2)).callMiniMax(2);
				game.updateBoard(((AIPlayer) game.getPlayer(2)).getBestMove(), game.getPlayer(2));
				game.getBoard().drawBoard(1);
				game.terminateGameCheck();
			}
			
			
			private static void printSelectionScreen()
			{
				System.out.println("+------------------Select--------------------+");
				System.out.println("| 1. Player1 vs Player2                      |");
				System.out.println("| 2. Player1 vs AI [Impossible Mode]         |");
				System.out.println("| 3. About                                   |");
				System.out.println("| 4. Quit                                    |");
				System.out.println("+--------------------------------------------+\nSelection:");
			}
			
			private static void printAboutScreen()
			{
				System.out.println("+------------------About---------------------+");
				System.out.println("|This is a simple CLI based TicTacToe game   |");
				System.out.println("|written in Java.                            |");
				System.out.println("|If versing AI, it is unbeatable. Player can |");
				System.out.println("|tie a game if playing perfectly, otherwise  |");
				System.out.println("|game cannot be won.                         |");
				System.out.println("|AI is implemented using MiniMax algorithm   |");
				System.out.println("|Read: https://en.wikipedia.org/wiki/Minimax |");
				System.out.println("|Author: Perth155 (abrar.a.amin@gmail.com)   |");
				System.out.println("|URL: https://github.com/Perth155/tictactoe  |");
				System.out.println("+--------------------------------------------+");
			}

			/**
			 * Prints introduction, handles names of players and selection of modes.
			 * @param Scanner for console input for player names and mode.
			 * @param game, a GamePlay class object that handles the two players.
			 */
			private static int newGameStarter(GamePlay game, Scanner inScan)
			{
				printSelectionScreen();
				int mode;
				try
				{
						mode = inScan.nextInt();
				}catch(Exception E)
				{
						System.err.println("Invalid Selection.");
						mode = 4;
				}
				inScan.nextLine();
				
				String name1 = "";
				if(mode < 3 && mode > 0)
				{
					System.out.println("Player 1's name: ");
					name1 = inScan.nextLine();

					if(name1.equals(""))
					{
						name1 = "player1"; //Prevent "" named players to avoid confusion.
						System.out.println("Player 1's name was set to "+name1+".");
					}
				}
				String name2;
				if(mode == 1) // Player1 vs Player2
				{
					System.out.println("Player 2's name: ");
					name2 = inScan.nextLine();
					if(name2.equals(""))
					{
						name2 = "player2";  //Handles empty string names.
						System.out.println("Player 2's name was set to " + name2+".");
					}
					game.setUpGame(name1, 'X', name2, 'O', 1);
				}
				else if(mode == 2)
				{
					System.out.println("Opponent's name was set to : 'AI'");
					game.setUpGame(name1, 'X', "AI", 'O', 2);
					
				}
				else if(mode == 3)
					printAboutScreen();
				else
					mode = 4;
				return mode;
			}


			/**
			 * Get the selection as an int between 1 to 8, to be passed to GamePlay object to validate.
			 * @param game
			 * @param inScan
			 */
			private static int getSelection(GamePlay game, Scanner inScan, Player currentPlayer)
			{
				int selection;
				System.out.println(currentPlayer.getName()+"'s Turn.");
				System.out.println("Enter an unoccupied slot between 0 and 8, or any other key to see the positions.");



				String select = inScan.nextLine();

				while(select.equals("h") || select.equals("H"))
				{
					System.out.println("-Help Menu Invoked-");
					game.getBoard().drawBoard(1);
					System.out.println("Enter an unoccupied slot between 0 and 8, or h for help.");
					select = inScan.nextLine();
				}

				try
				{
					selection = Integer.parseInt(select); // Now we need to check if the selection by the player is valid. Note str to int.
				}
				catch (NumberFormatException n)
				{
					selection = 100;
				}

				return  selection;
			}
}
