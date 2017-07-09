package it.polimi.ingsw.gc_26.model.game.gameComponents.dices;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.gc_26.model.game.game_components.dices.Colour;
import it.polimi.ingsw.gc_26.model.game.game_components.dices.Dices;

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

    @Test (expected = IllegalArgumentException.class)
    public void testExceptionWhenReadingNeutralColour() {
    		dices.rollDices();
    		dices.readDice(Colour.NEUTRAL);
    }
    
    @Test (expected = NullPointerException.class)
    public void testExceptionWhenReadingNullColour() {
    		dices.rollDices();
    		dices.readDice(null);
    }
}
