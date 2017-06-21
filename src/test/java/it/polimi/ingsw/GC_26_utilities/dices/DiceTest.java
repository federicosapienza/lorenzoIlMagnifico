package it.polimi.ingsw.GC_26_utilities.dices;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	@Test (expected = IllegalArgumentException.class)
	public void testWrongNrFaces() {
		Colour colour = Colour.BLACK;
		int nrFaces = -2; 
		Dice dice = new Dice(nrFaces, colour);
		dice.rollDice();
	}
	@Test (expected = NullPointerException.class)
	public void testNullColour() {
		Colour colour = null; 
		int nrFaces = 3; 
		Dice dice = new Dice(nrFaces, colour);
		dice.rollDice();
	}
	
	@Test
	public void testCorrectRoll(){
		Dice dice = new Dice(20, Colour.ORANGE);
		dice.rollDice(); 
		Dice dice2 = new Dice();
		dice2.rollDice();
		assertTrue(dice.getValue() >= 1 && dice.getValue() <= 20 && dice2.getValue() >= 1 && dice2.getValue() <= 6);
	}

}
