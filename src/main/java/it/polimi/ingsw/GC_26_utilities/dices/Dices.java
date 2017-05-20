package it.polimi.ingsw.GC_26_utilities.dices;

public class Dices {
	private final int nrFaces = 6;
	private int value;
	Dice whiteDice = new Dice(nrFaces, Colour.WHITE);
	Dice blackDice = new Dice(nrFaces, Colour.BLACK);
	Dice orangeDice = new Dice(nrFaces, Colour.ORANGE);
	
	public void throwDices() {
		whiteDice.throwDice();
		blackDice.throwDice();
		orangeDice.throwDice();
	}
	
	public int readDice(Colour colour) {
		
		
		if (colour == Colour.BLACK) {
			value = blackDice.getValue();
		}
		else if(colour == Colour.WHITE) {
			value = whiteDice.getValue();
		}
		else if(colour == Colour.ORANGE) {
			value = orangeDice.getValue();
		}
		return value;
	}
}
