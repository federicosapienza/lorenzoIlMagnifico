package it.polimi.ingsw.GC_26_utilities.dices;
import java.util.Random;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This is the class that represents the single dice.
 */
public class Dice {
	private Random gen;
	
	// this is the number of faces of the dice.
	private int nrFaces;
	
	// this is the colour of the dice.
	private Colour colour;
	
	// this is the value of the superior face of the dice.
	private int value;
	
	/**
	 * Constructor of the dice with the number of faces denoted by @param nrFaces and with colour denoted by @param colour.
	 * @param nrFaces It's the number of faces of the dice
	 * @param colour It's the colour of the dice
	 */
	public Dice(int nrFaces, Colour colour)
	{
		if(nrFaces<1) {
			throw new IllegalArgumentException("nrFaces must be between 1 and 6"); 
		}
		if(colour==null) 
			throw new NullPointerException(); 
		this.colour = colour;
		this.nrFaces = nrFaces;
		
		// this generates random numbers 
		gen = new Random(); 
	}
	
	/**
	 * Constructor of the classic dice with 6 faces.
	 */
	public Dice() 
	{
		nrFaces = 6;
		
		// this generates random numbers 
		gen = new Random(); 
	}
	
	/**
	 * Method that rolls the dice.
	 * @return the result of the roll, i.e. the value of the superior face of the dice after the roll.
	 */
	public int rollDice() 
	{
		return value = 1 + gen.nextInt(nrFaces);
	}
	
	/**
	 * Method that returns the value of the superior face of the dice
	 * @return value It returns the value of the superior face of the dice
	 */
	public int getValue() { 
		return value;
	}
	
	/**
	 * Method that returns the colour of the dice.
	 * @return
	 */
	public Colour getColour() {
		return colour;
	}
}
