package entities;

import java.util.List;
import java.util.LinkedList;


public class AIPlayer extends Player
{
	private char[] gameBoard; // Current state of the game.
	private List<MoveScore> childNodeMoveScores;

	public AIPlayer()
	{
		super("AI", 'O');
		gameBoard = new char[9];
	}

	private boolean boardFull(char[] gb)
	{
		for(int i = 0; i<gb.length; i++)
		{
			if(gb[i] == '-')
				return false;
		}
		return true;
	}


	/*
	 * Check if the game has been won, return an integer corresponding to the winner.
	 * @param gb, the current game board as an array of chars.
	 * @return winningPlayer, an integer corresponding to a player, 3 if game is a draw.
	 */
	private int checkWin(char[] gb)
	{
		char winningChar = '-';
		int winningPlayer = 0;

		//Check for Horizontal win
		for(int i = 0; i < 9; i+=3)
		{
			if(gb[i] == '-')
				continue;
			if((gb[i] == gb[i+1]) && (gb[i]== gb[i+2]))
				winningChar = gb[i];
		}

		//Check for vertical win
		for(int i = 0; i < 3; i++)
		{
			if(gb[i] == '-')
				continue;
			if((gb[i] == gb[i+3]) && (gb[i] == gb[i+2*3]))
				winningChar = gb[i];
		}

		//Diagonal win check
		if((gb[4] != '-') && ((gb[4] == gb[0] && gb[4] == gb[8]) || (gb[4] == gb[2] && gb[4] == gb[6])))
			    winningChar = gb[4];

		if(winningChar == this.oppSymbol)
			winningPlayer = 1;
		else if(winningChar == this.symbol)
			winningPlayer = 2;
		else if(boardFull(gb))
			winningPlayer = 3;

		return winningPlayer;
	}


	public void setGameBoard(char[][] gb)
	{
		int count = 0;
		for(int i = 0; i < gb.length; i++)
		{
			for(int j = 0; j<gb[i].length; j++)
			{
				this.gameBoard[count] = gb[i][j];
				count++;
			}
		}
	}



	private char getSymbol(int p)
	{
		if(p == 1)
			return 'X';
		return 'O';
	}



	public int miniMax(char[] node, int playerNum, int depth)
	{
		int victor = checkWin(node); // returns 0 if game is ongoing, 1 for p1, 2 for p2, 3 for tie.
		if(victor == 1) //game over .
			return -10;
		else if(victor == 2)
			return 10;
		else if(boardFull(node))
			return 0;

		int bestVal = 1;

		if(playerNum == 2) //AI
		{
			bestVal = Integer.MIN_VALUE;
			for(int i = 0; i < node.length; i++)
			{
				if(node[i] != '-')
					continue;
				node[i] = getSymbol(playerNum);
				int value = miniMax(node, 1, depth+1);
				if(value > bestVal)
				{
					bestVal = value;
				}
				if(depth == 0)
					this.childNodeMoveScores.add(new MoveScore(i, value));

				if(depth == 0)
					System.out.println("Score for position "+(i)+" = "+value);
				node[i] = '-';
			}
		}
		else if(playerNum == 1)
		{
			bestVal = Integer.MAX_VALUE;
			for(int i = 0; i < node.length; i++)
			{
				if(node[i] != '-')
					continue;
				node[i] = getSymbol(playerNum);
				int value = miniMax(node, 2, depth+1);
				if(value < bestVal)
				{
					bestVal = value;
				}
				node[i] = '-';
			}
		}
		return bestVal;
	}



	public void callMiniMax(int player)
	{
		this.childNodeMoveScores = new LinkedList<MoveScore>();
		miniMax(this.gameBoard, player, 0);
	}


	public List<MoveScore> getChildNodes()
	{
		return this.childNodeMoveScores;
	}


	public int getBestMove()
	{
		int highestScore = Integer.MIN_VALUE;
		int bestMove = 0;

		System.out.println(childNodeMoveScores.size());
		for(int i = 0; i < this.childNodeMoveScores.size(); i++)
		{
			if(highestScore < this.childNodeMoveScores.get(i).score)
			{
				highestScore = childNodeMoveScores.get(i).score;
				bestMove = i;
			}
		}

		return childNodeMoveScores.get(bestMove).move;
	}


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


}
