package entities;

/**
* A class that manages the Player Info.
*/
public class Player
{
		private String name; // Name chosen by the player.
		private int points;  // The final points of the player. Each win increments point by 1.
		protected char symbol;  // 'X' or 'O' - what the player chooses.
		protected char oppSymbol;

		//Constructors
		public Player()
		{
				name = "Player1";
				points = 0;
				symbol = 'X';
		}

		public Player(String inName, char inSymbol)
		{
				setPlayer(inName, inSymbol);
				points = 0;
				if(this.symbol == 'X')
					oppSymbol = 'O';
				else
					oppSymbol = 'X';
		}

		public Player(Player inPlayer)
		{
				name = inPlayer.getName();
				symbol = inPlayer.getSymbol();
				points = inPlayer.getPoints();
		}



		public void setPlayer(String inName, char inSymbol)
		{
				name = inName;
				if(inSymbol=='X' || inSymbol == 'O')
						symbol = inSymbol;
				else
						System.err.println("Invalid Symbol Selection. Must choose a 'X' or a 'O'.");
		}

		public void setPoint(int inPoint)
		{
				points = inPoint;
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
