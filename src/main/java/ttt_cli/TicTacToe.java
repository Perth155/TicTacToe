package ttt_cli;

import java.util.Scanner;
import org.apache.commons.cli.*;
import ttt_cli.agent.*;
import ttt_cli.util.*;

/**
* Contains the main method used to run this game.
* @author abraram (abrar.a.amin@gmail.com)
*/

public class TicTacToe {

	private static final int HARD_CODED_BOARD_DIMENSION = 3; //to do: use opt parse.

	/**
	 * Display the player selection option to console.
	 */
	private static void printSelectionScreen() {
		System.out.println("+------------------Select--------------------+");
		System.out.println("| 1. Human Player                            |");
		System.out.println("| 2. Random Agent                            |");
		System.out.println("| 3. MiniMax Agent [Default]                 |");
		System.out.println("+--------------------------------------------+\nPlayer Selection [Default = 3]:");
	}


	/**
	 * Set up each player that will be playing the game from the list of available agents
	 * via stdin
	 *
	 * @param scin         the Scanner passed from main method to read console input.
	 * @param playerNumber the player currently being set up, p1 or p2.
	 * @return The player object that has been successfully set up.
	 */
	private static TTTAgent setUpPlayer(Scanner scin, int playerNumber) {
		System.out.println("Select Player#" + playerNumber + " [1-4]:");
		TTTAgent outPlayer = null;
		printSelectionScreen();
		int playerOpt = 0;

		try {
			playerOpt = scin.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Input, try again: ");
		}

		if (playerOpt == 1) {
			outPlayer = new HumanPlayer();
		} else if (playerOpt == 4) {
			outPlayer = new RandomAgent();
		} else if (playerOpt == 3) {
			//outPlayer = new TTTNeuralNet();
		} else {
			//outPlayer = new MiniMaxAgent(playerNumber, 3);
		}

		if (playerNumber == 1) {
			outPlayer.setSymbol(Symbol.X);
		} else {
			outPlayer.setSymbol(Symbol.O);
		}
		return outPlayer;
	}


	private static void displayEndGameResult(TTTAgent p) {
		System.out.println("Game Over!\n");
		if (p == null)
			System.out.println("Match Tied.");
		else if (p.getSymbol() == Symbol.X)
			System.out.println("Player 1: " + p.getName() + " {X} wins!");
		else
			System.out.println("Player 2: " + p.getName() + " {O} wins!");
	}


	/**
	 * Display the final results to console, and check if the game should be reset.
	 *
	 * @param tb
	 * @param s1
	 * @return true if game is to be reset, false otherwise.
	 */
	private static boolean checkReset(TicTacToeBoard tb, Scanner s1) {
		System.out.println("Play Again? [Y/n]");
		String resetSelection = s1.nextLine();
		if (!(resetSelection.equals("N") || resetSelection.equals("n") || resetSelection.equals("No"))) {
			tb.resetBoard();
			tb.drawBoard(1);
			return true;
		}
		return false;
	}

	/**
	 * Check if the most recent move has won the game for a player in an n*n board.
	 * Uses restricted search space to only consider the row and col
	 * the move has been made in, Worst Case performance of O(n) for
	 * board size of n^2.
	 *
	 * @param gb   the gameboard at a given instance, a 2D array of symbols.
	 * @param move the move made by the player.
	 * @return The symbol of the winning player.
	 */
	private static Symbol checkWin(Symbol[][] gb, int move, int boardDimension) {
		int moveX = move % boardDimension;
		int moveY = move / boardDimension;

		//horizontal win.
		int matchCount = 0;
		for (int i = 0; i < boardDimension - 1; i++) {
			if ((gb[moveY][i] != gb[moveY][i + 1]) || gb[moveY][i] == Symbol.EMPTY)
				break;
			matchCount++;
		}
		if (matchCount == 2) {
			return gb[moveY][0];
		} else {
			matchCount = 0;
		}

		//vertical win.
		for (int j = 0; j < boardDimension - 1; j++) {
			if ((gb[j][moveX] != gb[j + 1][moveX]) || (gb[j][moveX] == Symbol.EMPTY))
				break;
			matchCount++;
		}
		if (matchCount == 2) {
			return gb[0][moveX];
		} else {
			matchCount = 0;
		}

		//diagonal win
		if (moveX == moveY) {
			for (int i = 0; i < boardDimension - 1; i++) {
				if ((gb[i][i] != gb[i + 1][i + 1]) || (gb[i][i] == Symbol.EMPTY))
					break;
				matchCount++;
			}
			if (matchCount == 2) {
				return gb[0][0];
			} else {
				matchCount = 0;
			}
		}

		//anti-diagonal win.
		if (moveX + moveY == boardDimension - 1) {
			for (int i = 0; i < boardDimension - 1; i++) {
				if ((gb[i][boardDimension - 1 - i] != gb[i + 1][boardDimension - 2 - i])
						|| gb[i][boardDimension - 1 - i] == Symbol.EMPTY)
					break;
				matchCount++;
			}
			if (matchCount == 2) {
				return gb[0][boardDimension - 1];
			}
		}
		return Symbol.EMPTY; //neither player x, nor player y won..
	}


	/**
	 * Calls the ,move method of Player class and uses the int to update the
	 * gameboard accordingly.
	 *
	 * @param player p1 or p2.
	 * @return the players selection
	 */
	private static int makeMove(TTTAgent player, TicTacToeBoard board) {
		System.out.println("Turn for " + player.getName() + " [" +player.getSymbol().toString() + "]");
		int spot = player.move(board.getBoardStatus()); // player1 makes a move.
		board.setBoard(spot, player.getSymbol()); // update game board with player's move.
		board.drawBoard(0);
		return spot;
	}


	private static void localGame()
	{
		TicTacToeBoard board = new TicTacToeBoard();
		Scanner scin = new Scanner(System.in);
		TTTAgent p1 = setUpPlayer(scin, 1);
		TTTAgent p2 = setUpPlayer(scin, 2);
		board.drawBoard(1);
		boolean playOn = true;
		int movesPlayed = 0;
		TTTAgent winner = null;

		while (playOn) {
			int spot = makeMove(p1, board);
			if (checkWin(board.getBoardStatus(), spot, HARD_CODED_BOARD_DIMENSION) == p1.getSymbol()) {
				winner = p1;
				playOn = false;
			} else if (movesPlayed == (HARD_CODED_BOARD_DIMENSION * HARD_CODED_BOARD_DIMENSION)-1 && board.isFull()) {
				playOn = false; // match tied.
			} else {
				movesPlayed++;
			}
			// player2 makes a move.
			if (playOn) {
				spot = makeMove(p2, board);
				if (checkWin(board.getBoardStatus(), spot, HARD_CODED_BOARD_DIMENSION) == p2.getSymbol()) {
					winner = p2;
					playOn = false;
				} else {
					movesPlayed++;
				}
			}
			if (playOn == false) {
				displayEndGameResult(winner);
				movesPlayed = 0;
				playOn = checkReset(board, scin);
			}
		}
	}


	public static void main(String[] args) {
		Options opt = new Options();
		// board size specified by user.
		Option boardSiz = new Option("s", "size", true, "TicTacToe board dimensions, 3-5");
		boardSiz.setRequired(false);
		opt.addOption(boardSiz);



		localGame();
		System.exit(0);
	}
}
