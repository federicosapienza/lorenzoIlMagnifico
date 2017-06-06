package it.polimi.ingsw.GC_26_utilities.dices;

import static org.junit.Assert.*;

import org.junit.Test;

public class DicesTest {
	Dices dices = new Dices();

    @Test
    public void testIntervalBlackDice() {
        dices.rollDices();
        assertTrue(dices.readDice(Colour.BLACK)>=1 && dices.readDice(Colour.BLACK)<=6);
    }
    @Test
    public void testIntervalWhiteDice() {
        dices.rollDices();
        assertTrue(dices.readDice(Colour.WHITE)>=1 && dices.readDice(Colour.WHITE)<=6);
    }
    @Test
    public void testIntervalOrangeDice() {
        dices.rollDices();
        assertTrue(dices.readDice(Colour.ORANGE)>=1 && dices.readDice(Colour.ORANGE)<=6);
    }

}