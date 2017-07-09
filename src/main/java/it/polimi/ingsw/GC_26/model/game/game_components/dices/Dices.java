package it.polimi.ingsw.GC_26.model.game.game_components.dices;


/** 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the three dices of the game.
 */
public class Dices {
	
	// the game is based on basic dices with 6 faces.
	private static final int nrFaces = 6;
	
	/**
	 * The following 3 lines of code create the 3 dices of the game: they're white, black and orange.
	 */
	Dice whiteDice = new Dice(nrFaces, Colour.WHITE);
	Dice blackDice = new Dice(nrFaces, Colour.BLACK);
	Dice orangeDice = new Dice(nrFaces, Colour.ORANGE);
	
	/**
	 * This is the method that rolls the dices
	 */
	public void rollDices() {
		whiteDice.rollDice();
		blackDice.rollDice();
		orangeDice.rollDice();
	}
	
	/**
	 * This is the method that returns the result of the roll for the desired colour.
	 * @param colour is the colour of the dice that I want to read.
	 * @return value is the value of the dice that I want to read.
	 */
	public int readDice(Colour colour) {
		if(colour==null)
			throw new NullPointerException(); 
		if (colour == Colour.BLACK) {
			return  blackDice.getValue();
		}
		else if(colour == Colour.WHITE) {
			return whiteDice.getValue();
		}
		else if(colour == Colour.ORANGE) {
			return  orangeDice.getValue();
		}
		else throw new IllegalArgumentException();

	}
}
