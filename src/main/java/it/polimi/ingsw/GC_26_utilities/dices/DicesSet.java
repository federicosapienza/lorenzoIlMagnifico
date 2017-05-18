package it.polimi.ingsw.GC_26_utilities.dices;
import java.util.HashMap;
import java.util.Map;
public class DicesSet {
	static final int nrFaces = 6;
	Dice whiteDice = new Dice(nrFaces, Colour.WHITE);
	Dice blackDice = new Dice(nrFaces, Colour.BLACK);
	Dice orangeDice = new Dice(nrFaces, Colour.ORANGE);
	
	/* ho unito tutto in un unico metodo throwdices che ritorna la mappa*/ 
	public Map<Colour, Integer> throwDices() {
		Map<Colour, Integer> readDices = new HashMap<>();
		int whiteValue = whiteDice.throwDice();
		int blackValue = blackDice.throwDice();
		int orangeValue = orangeDice.throwDice();
		readDices.put(Colour.WHITE, whiteValue);
		readDices.put(Colour.ORANGE, orangeValue);
		readDices.put(Colour.BLACK, blackValue);
		return readDices;
	}
}
