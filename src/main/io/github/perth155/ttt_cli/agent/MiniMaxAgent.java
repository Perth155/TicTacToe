package io.github.perth155.ttt_cli.agent;

import io.github.perth155.ttt_cli.util.Symbol;
import java.util.List;
import java.util.LinkedList;


/**
 * An AI for TicTacToe written using a MiniMax algorithm with Alpha-Beta pruning.
 * TicTacToe is a perfect information, deterministic 2-Player game which makes it easy to
 * traverse through all plausible nodes of the game tree and come up with the best possible
 * move. This makes the MiniMax algorithm ideal for writing TicTacToe agents.
 * @author abraram (abrar.a.amin@gmail.com)
 */
public class MiniMaxAgent extends TTTAgent
{
	private static final int PLAYER_X = 1;
	private static final int PLAYER_O = 2;

	/**
	 * A class for storing Move-Score pair evaluated by
	 * the MiniMax algorithm.
	 */
	private class MoveScore
	{
		private int score;
		private int move;
		public MoveScore(int m, int s)
		{
			this.score = s;
			this.move = m;
		}
	}

	private int boardDimension; // The dimension of the 2D board, n.
	private Symbol[][] gameBoard; // Current game board, an 1D array.
	private List<MoveScore> childNodeMoveScores;
	private int aiPlayer;
	private int opponent;

	/**
	 * Initiating minimax call by defining the player's symbol
	 * @param inPlayer 1 for X, 2 for O.
	 */
	public MiniMaxAgent(int inPlayer, int inBoardSize)
	{
		super("MiniMaxAgent [+A-B pruning]");
		this.aiPlayer = inPlayer;
		if(inPlayer == PLAYER_X) {
			this.opponent = PLAYER_O;
		}else{
			this.opponent = PLAYER_X;
		}
		this.boardDimension = inBoardSize;
	}

	/**
	 * Check to see if the game board is full, i.e. game reached terminal node.
	 * @param numberOfTurns the number of moves already played.
	 * @return true if full, false otherwise.
	 */
	private boolean boardFull(int numberOfTurns)
	{
		if(numberOfTurns == boardDimension*boardDimension)
			return true;
		return false;
	}


	/**
	 * Check if the most recent move has won the game for a player in an n*n board.
	 * Uses restricted search space to only consider the row and col
	 * the move has been made in, Worst Case performance of O(n) for
	 * board size of n^2.
	 * @param gb the gameboard at a given instance, a 2D array of symbols.
	 * @param move the move made by the player.
	 * @return The symbol of the winning player.
	 */
	private Symbol checkWin(Symbol[][] gb, int move)
	{
		Symbol winningSym = Symbol.EMPTY;
		int winningPlayer = 0;

		int moveX = move%this.boardDimension;
		int moveY = move/this.boardDimension;

		//horizontal win.
		int matchCount = 0;
		for(int i = 0; i < this.boardDimension-1; i++)
		{
			if((gb[moveY][i] != gb[moveY][i+1]) || gb[moveY][i] == Symbol.EMPTY)
				break;
			matchCount++;
		}
		if(matchCount == 2){return gb[moveY][0];}
		else {matchCount = 0;}

		//vertical win.
		for(int j = 0; j < this.boardDimension-1; j++)
		{
			if((gb[j][moveX] != gb[j+1][moveX]) || (gb[j][moveX] == Symbol.EMPTY ))
				break;
			matchCount++;
		}
		if(matchCount == 2){return gb[0][moveX];}
		else{matchCount = 0;}

		//diagonal win
		if(moveX == moveY)
		{
			for(int i = 0; i < this.boardDimension-1; i++)
			{
				if((gb[i][i] != gb[i+1][i+1]) || (gb[i][i] == Symbol.EMPTY))
					break;
				matchCount++;
			}
			if(matchCount == 2) {return gb[0][0];}
			else {matchCount = 0;}
		}

		//anti-diagonal win.
		if(moveX+moveY == this.boardDimension-1)
		{
			for(int i = 0; i < this.boardDimension-1; i++)
			{
				if((gb[i][this.boardDimension-1-i] != gb[i+1][this.boardDimension-2-i])
						|| gb[i][this.boardDimension-1-i] == Symbol.EMPTY)
					break;
				matchCount++;
			}
			if(matchCount == 2){return gb[0][this.boardDimension-1];}
		}
		return Symbol.EMPTY; //neither player x, nor player y won..
	}


	public int miniMax(Symbol[][] node, int playerNum, int depth)
	{
		//Todo
		return Integer.MIN_VALUE;
	}


	public void setAIPlayer(int i)
	{
		if(i == PLAYER_X)
		{
			this.aiPlayer = PLAYER_X;
			this.opponent = PLAYER_O;
			this.setSymbol(Symbol.X);
		}else {
			this.aiPlayer = PLAYER_O;
			this.opponent = PLAYER_X;
			this.setSymbol(Symbol.O);
		}
	}


	/**
	 * Call minimax algorithm to get the best possible move of the player in the provided provided
	 * game board.
	 * @param gameBoard the current game board.
	 * @return an int for the best movement in a 1D board.
	 */
	@Override
	public int move(Symbol gameBoard[][])
	{
		this.childNodeMoveScores = new LinkedList<MoveScore>();
		return miniMax(this.gameBoard, this.aiPlayer, 0);
	}

	@Override
	public void destroy()
	{
		// Not required to do anything.
	}

}
