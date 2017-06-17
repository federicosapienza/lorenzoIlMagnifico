package it.polimi.ingsw.GC_26_utilities.dices;

import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	@Test
	public void testWrongNrFaces() {
		Colour colour = Colour.BLACK;
		int nrFaces = -2; 
		boolean thrown = false; 
		try {
			Dice dice = new Dice(nrFaces, colour); 
		} catch(IllegalArgumentException e) {
			thrown = true; 
		}
		assertTrue(thrown);
	}
	@Test
	public void testNullColour() {
		Colour colour = null; 
		int nrFaces = 3; 
		boolean thrown = false; 
		try {
			Dice dice = new Dice(nrFaces, colour); 
		} catch(NullPointerException e) {
			thrown = true; 
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testCorrectConstructor() {
		Dice dice = new Dice(6, Colour.BLACK); 
		Dice dice2 = new Dice(); 
		assertNotNull(dice);
		assertNotNull(dice2);
	}
	
	@Test
	public void testCorrectRoll(){
		Dice dice = new Dice(6, Colour.ORANGE);
		int x = dice.rollDice(); 
		assertTrue(x>=1 && x<=6);
		
	}

}
