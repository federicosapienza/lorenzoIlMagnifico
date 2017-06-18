package it.polimi.ingsw.GC_26_actionsHandlers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_26_board.Board;
import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_readJson.BonusImplementation;
import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.CardsImplementation;
import it.polimi.ingsw.GC_26_readJson.ReadAll;
import it.polimi.ingsw.GC_26_readJson.TimerValueImplementation;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26_cards.payments.Payment;
import it.polimi.ingsw.GC_26_cards.payments.ResourcesPayment;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.Game;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
public class FirstActionHandlerTest {
	
	
					
	
	@Test
	public void testIsPossibleEnoughServants() {
		BonusInterface bonus = new BonusImplementation();
		Cards cards = new CardsImplementation();
		TimerValuesInterface times = new TimerValueImplementation();
		Game game = new Game(cards,bonus,times);
		
		ResourcesOrPoints startingresourcesOrPoints = ResourcesOrPoints.newResources(10, 10, 10, 10);
		ResourcesOrPoints allBoardresourcesOrPoints = ResourcesOrPoints.newResources(1, 1, 1, 1);
		Player player = new Player("Leon", startingresourcesOrPoints);
		Player player2 = new Player("Leon2", startingresourcesOrPoints);
		List<Player> players = new ArrayList<Player>();
		players.add(player);
		players.add(player2);
		
		//devo fare degli array da 4 di ResourcesOrPoints
		ResourcesOrPoints[] resourcesOrPointsArray = new ResourcesOrPoints[4];
		for(int i=0;i <4 ;i++){
			resourcesOrPointsArray[i] = allBoardresourcesOrPoints; 
		}
		List<ResourcesOrPoints[]> resourcesOrPointsList = new ArrayList<ResourcesOrPoints[]>();
		resourcesOrPointsList.add(resourcesOrPointsArray);
		resourcesOrPointsList.add(resourcesOrPointsArray);
		resourcesOrPointsList.add(resourcesOrPointsArray);
		resourcesOrPointsList.add(resourcesOrPointsArray);
		resourcesOrPointsList.add(resourcesOrPointsArray);
		resourcesOrPointsList.add(resourcesOrPointsArray);
		
		Map<Integer, Integer> faithTrack = new HashMap<>();
		for(int i = 0; i<15 ;i++){
			faithTrack.put(0, 0);
		}
		
		GameElements gameElements = new GameElements(game, players, 2,resourcesOrPointsList, faithTrack);
		HarvestAndProductionHandler handler = new HarvestAndProductionHandler(gameElements);
		FirstActionHandler firstActionHandler = new FirstActionHandler(gameElements, handler);
		//Creo le risorse che servono per creare il pagamento
		ResourcesOrPoints resourcesOrPointsPayment = ResourcesOrPoints.newResourcesOrPoints(0, 2, 0, 0, 0, 0, 0, 0);
		//Creo le risorse che servono per creare l'effetto (ho scelto due effetti a caso che tanto non mi serve averene uno specifico)
		ResourcesOrPoints resourcesOrPointsImmediateEffect = ResourcesOrPoints.newResourcesOrPoints(0, 5, 0, 0, 0, 0, 0, 0);
		//Creo le risorse che servono per creare l'effetto (ho scelto due effetti a caso che tanto non mi serve averene uno specifico)
		ResourcesOrPoints resourcesOrPointsPermanentEffect = ResourcesOrPoints.newResourcesOrPoints(0, 5, 0, 0, 0, 0, 0, 0);
		//creo il pagamento
		ResourcesPayment payment = new ResourcesPayment(resourcesOrPointsPayment);
		//creo due effetti random per creare la carta
		ReceiveResourcesOrPointsEffect immediate = new ReceiveResourcesOrPointsEffect(resourcesOrPointsImmediateEffect);
		ReceiveResourcesOrPointsEffect permanent = new ReceiveResourcesOrPointsEffect(resourcesOrPointsPermanentEffect);
		//creo una carta di tipo character
		DevelopmentCard developmentCard = DevelopmentCardImplementation.characterCard("carta", 1, payment, immediate, permanent);
		//creo la Board;
		Board board = new Board(2, resourcesOrPointsList); 
		//creo familyMember
		FamilyMember familyMember = new FamilyMember(Colour.BLACK, player);
		List<DevelopmentCard> devCardList = new ArrayList<DevelopmentCard>();
		devCardList.add(developmentCard);
		devCardList.add(developmentCard);
		devCardList.add(developmentCard);
		devCardList.add(developmentCard);
		board.getTower(BoardZone.TERRITORYTOWER).setCardsForThisRound(devCardList);
		//creo l'azione
		Action action = new Action(BoardZone.CHARACTERTOWER, 1, Colour.BLACK, 0);
		assertTrue(firstActionHandler.isPossible(player, action));
	}

	@Test
	public void testPerform() {
		fail("Not yet implemented");
	}

}
