package entities;

import java.util.ArrayList;
import java.util.Collections;

public class Enemy extends Player
{
    private ArrayList<Integer> computerMovementRandom;
    private int mode;

    public Enemy(int inMode, String name, char symbol)
    {
        super(name, symbol);
        setMode(inMode);
    }

    /**
     * Populate the randomised computer arraylist at a newgame or when game is reset.
     */
    public void setCompRandArray()
    {
    	computerMovementRandom.clear();
        for(int i = 0; i<9; i++)
        {
           computerMovementRandom.add(i);
        }
        Collections.shuffle(computerMovementRandom); //Randomise computer movement.
    }

    /**
    * A mutator that removes the specified number from the movement array because player made their move on that position, can't be repeated.
    * @param i the integer to be removed.
    */
    public void setCompArrayElement(int i)
    {
        int removeIndex = computerMovementRandom.indexOf(i);
        computerMovementRandom.remove(removeIndex);
    }


    public ArrayList<Integer> getCompRandomArray()
    {
        return this.computerMovementRandom;
    }

    public int getMode()
    {
        return this.mode;
    }
    
    
    public int movement(Integer playersMove)
    {
    	System.out.println("PRE"+computerMovementRandom.toString());
    	if(playersMove == -1)
    	{
    		System.out.println(computerMovementRandom.toString());
    		return computerMovementRandom.remove(0);
    	}
    	    	
    	computerMovementRandom.remove(playersMove);
    	int n = computerMovementRandom.remove(0);
    	System.out.println(n); //TEST
    	System.out.println(computerMovementRandom.toString()); //TEST
    	return n;
    }

	public void setMode(int i) {
		this.mode = i;
		if(i == 2)
		{
			computerMovementRandom = new ArrayList<Integer>();
			setCompRandArray();
		}
	}

}
