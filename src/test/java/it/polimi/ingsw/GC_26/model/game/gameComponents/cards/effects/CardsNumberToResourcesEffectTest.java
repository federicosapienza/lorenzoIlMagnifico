package it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.CardsNumberToResourcesEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.Effect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.MilitaryPointPayment;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.Payment;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.ResourcesPayment;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.payments.TwoOrPayments;
import it.polimi.ingsw.GC_26.model.game.gameComponents.personalBoard.PersonalBoard;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

public class CardsNumberToResourcesEffectTest {

	ResourcesOrPoints startingResources = ResourcesOrPoints.newResources(5, 3, 2, 2);
	Player player = new Player("David", startingResources);
	CardsNumberToResourcesEffect effect = new CardsNumberToResourcesEffect(DevelopmentCardTypes.VENTURECARD, ResourcesOrPoints.newResources(1, 2, 3, 4));
	PersonalBoard playerBoard = new PersonalBoard();
	Payment payment1 = new MilitaryPointPayment(2, 4);
	ResourcesOrPoints price = ResourcesOrPoints.newResources(2, 0, 1, 1);
	Payment payment2 = new ResourcesPayment(price);
	Payment payment = new TwoOrPayments(payment1, payment2);
	ResourcesOrPoints immediateRes = ResourcesOrPoints.newPoints(0, 0, 3, 0);
	Effect immediateAndPerm = new ReceiveResourcesOrPointsEffect(immediateRes);
	DevelopmentCard developmentCard = DevelopmentCardImplementation.ventureCard("Support to the Bishop", 1, payment, immediateAndPerm, immediateAndPerm);
	boolean immediate = true;
	
	@Test
	public void testDoEffect() {
		player.getPersonalBoard().add(developmentCard);
		effect.doEffect(player, immediate);
		assertEquals(5, player.getWarehouse().getServants());
	}

}
