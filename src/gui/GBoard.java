package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.Arrays;

import entities.AIPlayer;

public class GBoard extends JFrame
{
	   private static final String TITLE = "tic-tac-toe";
	   private static final int WIDTH = 600;
	   private static final int HEIGHT = 600;
	   private static final int PLAYER_X = 1;
	   private static final int PLAYER_O = 2;
	   private static final int BLOCK_LEN = 180+20; //account for padding.
	   private static final String PLAYER_X_STR = "Choose a spot to place";
	   private static final String PLAYER_O_STR = "Choose a spot to place";

	   private char[] gameBoard;
	   private BufferedImage cross;
	   private BufferedImage naught;
	   private BufferedImage board;
	   private BufferedImage playerXIcon;
	   private BufferedImage playerOIcon;
	   private BufferedImage victoryCross;
	   private BufferedImage victoryNaught;
	   private JButton buttonOne;
	   private JButton buttonTwo;
	   private int activePlayer;
	   private boolean gameOver;
	   private ImageManager im;
	   private int winner;
	   private boolean active;
	   private AIPlayer ai;

	   public GBoard()
	   {
		   super();
		   setUpButtons();
		   activePlayer = PLAYER_X;
		   active = true;
		   gameBoard = new char[9];
		   Arrays.fill(gameBoard, '-');
		   gameOver = false;
		   setBounds(0,0, WIDTH, HEIGHT+250);
		   setLocationRelativeTo(null);
		   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   setTitle(TITLE);
		   setResizable(false);
		   importImages();
		   im = new ImageManager();
		   ai = new AIPlayer();
		   getContentPane().add(im);
	   }


	   public void setUpButtons()
	   {
		   buttonOne = new JButton("User Starts Over");
		   buttonTwo = new JButton("  AI Starts Over  ");
		   buttonOne.setBackground(Color.WHITE);
		   buttonOne.setForeground(Color.blue);
		   buttonTwo.setBackground(Color.WHITE);
		   buttonTwo.setForeground(Color.magenta);
			 buttonOne.setBorderPainted(false);
			 buttonOne.setFocusPainted(false);
			 buttonTwo.setFocusPainted(false);
			 buttonTwo.setBorderPainted(false);
		   Font menuFont = new Font("Arial", Font.BOLD, 30);
		   buttonOne.setFont(menuFont);
		   buttonTwo.setFont(menuFont);
		   buttonOne.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent evt)
	            {
	            	ai.setAIPlayer(PLAYER_O);
	            	im.setNotification(PLAYER_X);
	            	activePlayer = PLAYER_X; //user moves first
	            	Arrays.fill(gameBoard, '-');
	            	gameOver = false;
	            	winner = 0;
	            	active = true;
	            	repaint();
	            };
		   	});

		   buttonTwo.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent evt)
	            {
	            	im.setNotification(PLAYER_O);
	            	activePlayer = PLAYER_O; //AI moves first
	            	Arrays.fill(gameBoard, '-');
	            	gameOver = false;
	            	winner = 0;
	            	ai.setAIPlayer(PLAYER_X);
				    active = false;
	            	repaint();

	            };
		   	});
	   }


	   private void importImages()
	   {
		   try{
			   board = ImageIO.read(getClass().getResourceAsStream("/board.png"));
			   cross = ImageIO.read(getClass().getResourceAsStream("/cross.png"));
			   naught = ImageIO.read(getClass().getResourceAsStream("/naught.png"));
			   playerXIcon = ImageIO.read(getClass().getResourceAsStream("/small_cross.png"));
			   playerOIcon = ImageIO.read(getClass().getResourceAsStream("/small_naught.png"));
			   victoryCross = ImageIO.read(getClass().getResourceAsStream("/green_cross.png"));
			   victoryNaught = ImageIO.read(getClass().getResourceAsStream("/green_naught.png"));
		   }catch(IOException e)
		   {
			   e.printStackTrace();
		   }
	   }


	   private void render(Graphics g)
	   {
		   if(winner > 0)
			   im.setGameStatus(true);

		   winner = checkWin();

		   if(!active && winner==0)
		   {
			   ai.setGameBoard(gameBoard);
			   ai.callMiniMax(3-activePlayer);
			   int pos = ai.getBestMove();
			   gameBoard[pos] = ai.getSymbol();
			   active = true;
		   }

		   winner = checkWin();

		   for(int y = 0; y < gameBoard.length; y++ )
		   {
			   if(gameBoard[y] == 'X')
			   {
				   g.drawImage(
						   cross, (y % 3) * BLOCK_LEN + 10 * (y % 3),
						   (int) (y / 3) * BLOCK_LEN + 10 * (int) (y / 3), null
				    );
			   }
			   else if(gameBoard[y] == 'O')
			   {
				   g.drawImage(naught, (y % 3) * BLOCK_LEN + 10 * (y % 3),
						   (int) (y / 3) * BLOCK_LEN + 10 * (int) (y / 3), null
						   );
			   }
		   }


		   if(winner > 0)
		   {
			   im.resetPlayerIcons();

			   if(winner == 1)
				   im.setNotification("Game Over : Player1 wins.   ");
			   else if(winner == 2)
				   im.setNotification("Game Over : Player2 wins.   ");
			   else if(winner == 3)
				   im.setNotification("Game Over : Match Tied      ");

			   if(winner != 3)
			   {
				   int[] wl = winningLineIndex();

				   for(int i = 0; i < wl.length; i++)
				   {

					 int y = wl[i];
				     if(winner == 1)
				      {
					     g.drawImage(
							   victoryCross, (y % 3) * BLOCK_LEN + 10 * (y % 3),
							   (int) (y / 3) * BLOCK_LEN + 10 * (int) (y / 3), null
					      );
				      }else if(gameBoard[y] == 'O'){
					     g.drawImage(victoryNaught, (y % 3) * BLOCK_LEN + 10 * (y % 3),
							   (int) (y / 3) * BLOCK_LEN + 10 * (int) (y / 3), null
							   );
				      }

			        }
			   }
		   }
	   }


	   public char[] getGameBoard()
	   {
		   return gameBoard;
	   }

	   public void setGameBoard(char[] g)
	   {
		   gameBoard = g;
	   }

	   public void setActivePlayer(int i)
	   {
		   activePlayer = i;
	   }
		private int checkWin()
		{
			char winningChar = '-';
			int winningPlayer = 0;

			//Check for Horizontal win
			for(int i = 0; i < 9; i+=3)
			{
				if(gameBoard[i] == '-')
					continue;
				if((gameBoard[i] == gameBoard[i+1]) && (gameBoard[i]== gameBoard[i+2]))
					winningChar = gameBoard[i];
			}

			//Check for vertical win
			for(int i = 0; i < 3; i++)
			{
				if(gameBoard[i] == '-')
					continue;
				if((gameBoard[i] == gameBoard[i+3]) && (gameBoard[i] == gameBoard[i+2*3]))
					winningChar = gameBoard[i];
			}

			//Diagonal win check
			if((gameBoard[4] != '-') && ((gameBoard[4] == gameBoard[0] && gameBoard[4] == gameBoard[8]) || (gameBoard[4] == gameBoard[2] && gameBoard[4] == gameBoard[6])))
				    winningChar = gameBoard[4];

			if(winningChar == 'X')
				winningPlayer = 1;
			else if(winningChar == 'O')
				winningPlayer = 2;
			else if(boardFull())
				winningPlayer = 3;


			return winningPlayer;
		}




		private int[] winningLineIndex()
		{
			int[][] possWins = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {2,4,6}, {0,4,8}};

			for(int i = 0; i< possWins.length; i++)
			{
				if(gameBoard[possWins[i][0]] == '-')
					continue;
				if(gameBoard[possWins[i][0]] == gameBoard[possWins[i][1]] && gameBoard[possWins[i][1]] == gameBoard[possWins[i][2]])
					return possWins[i];
			}
			return null;
		}

		private boolean boardFull()
		{
			for(int i = 0; i<gameBoard.length; i++)
			{
				if(gameBoard[i] == '-')
					return false;
			}
			return true;
		}


	   private class ImageManager extends JPanel implements MouseListener
	   {

		   private static final long serialVersionUID = 1L;
		   private JLabel gameNotification;
		   private JLabel playerIconX;
		   private JLabel playerIconO;
		   private Font gameFont;


		   public ImageManager()
		   {
			   super();
			   setPreferredSize(new Dimension(WIDTH-10, HEIGHT+150));
			   setBackground(Color.DARK_GRAY);
			   requestFocus();
			   addMouseListener(this);
			   JLabel picLabel = new JLabel(new ImageIcon(board));
			   add(picLabel);
			   setUpGame();
			   add(gameNotification, 1);
			   add(playerIconX, 2);
			   add(buttonOne, 3);
			   add(buttonTwo, 4);
		   }

		   private void setUpGame()
		   {
			   playerIconX = new JLabel(new ImageIcon(playerXIcon));
			   playerIconO = new JLabel(new ImageIcon(playerOIcon));
			   if(activePlayer == PLAYER_X)
				   gameNotification = new JLabel(PLAYER_X_STR);
			   else
				   gameNotification = new JLabel(PLAYER_O_STR);
			   gameFont = new Font("Arial", Font.BOLD, 24);
			   gameNotification.setFont(gameFont);
			   gameNotification.setForeground(Color.PINK);
		   }

		   public void setNotification(int player)
		   {
			   resetPlayerIcons();
			   if(player == PLAYER_X)
			   {
				   gameNotification.setText(PLAYER_X_STR);
				   add(playerIconX, 2);
			   }
			   else
			   {
				   gameNotification.setText(PLAYER_O_STR);
				   add(playerIconO, 2);
			   }
		   }


		   public void setGameStatus(boolean status)
		   {
			   gameOver = status;
		   }

			@Override
			public void mouseClicked(MouseEvent me)
			{
				if(!gameOver && active)
				{
					setFocusable(true);
					requestFocus();
					setBackground(Color.DARK_GRAY);
					addMouseListener(this);
					int x = me.getX() / BLOCK_LEN;
					int y = me.getY() / BLOCK_LEN;

					y *= 3;
					int position = x+y;

					if(position < 0 || position > 8)
						return;

					if (gameBoard[position] == '-')
					{
						if (activePlayer == PLAYER_X)
						{
							gameBoard[position] = 'X';
							setNotification(PLAYER_O_STR);
						}else{
							gameBoard[position] = 'O';
							setNotification(PLAYER_X_STR);
						}
						active = false;
						repaint();
						Toolkit.getDefaultToolkit().sync();
					}
				}

			}


			public void resetPlayerIcons()
			{
				if(activePlayer == PLAYER_X)
				{
					remove(playerIconX);
				}else{
					remove(playerIconO);
				}
			}


		    public void setNotification(String notif)
			{
				gameNotification.setText(notif);
			}


			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				render(g);
				Toolkit.getDefaultToolkit().sync();
			}

			@Override
			public void mouseExited(MouseEvent me) {}

			@Override
			public void mousePressed(MouseEvent me) {}

			@Override
			public void mouseReleased(MouseEvent me) {}

			@Override
			public void mouseEntered(MouseEvent me) {}
		 }


	   	 public static void main(String[] args)
	   	 {

	   		 GBoard gb = new GBoard();
	   		 gb.setVisible(true);
	   	 }

}
