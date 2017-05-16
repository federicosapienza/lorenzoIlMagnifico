package it.polimi.ingsw.GC_26_utilities.dices;
import java.util.HashMap;
import java.util.Map;
public class DicesSet {
	static final int nrFaces = 6;
	Dice whiteDice = new Dice(nrFaces, Colour.WHITE);
	Dice blackDice = new Dice(nrFaces, Colour.BLACK);
	Dice orangeDice = new Dice(nrFaces, Colour.ORANGE);
	int whiteValue = whiteDice.throwDice();
	int blackValue = blackDice.throwDice();
	int orangeValue = orangeDice.throwDice();
	/* ho unito tutto in un unico metodo throwdices che ritorna la mappa*/ 
	public Map<Integer, Colour> throwDices(Dice whiteDice, Dice blackDice, Dice orangeDice) {
		Map<Integer, Colour> readDices = new HashMap<>();
		readDices.put(whiteValue, Colour.WHITE);
		readDices.put(orangeDice.throwDice(), Colour.ORANGE);
		readDices.put(blackDice.throwDice(), Colour.BLACK);
		return readDices;
	}
}
