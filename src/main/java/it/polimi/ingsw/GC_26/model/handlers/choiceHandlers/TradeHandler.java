package it.polimi.ingsw.GC_26.model.handlers.choiceHandlers;



import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.Effect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.effects.TradeEffect;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the handler for the trades. Trades need dialogue with user: they're usually based on choices and player 
 * can also decide not to trade!
 * Creation rules: 
 * If a resource to be given can be traded in two different ways, this resource is inserted both in give1 and give2
 * If there is a choice in giving and only one possible outcome, the outcome is inserted both in receive1 and receive2. 
 * If there is only one income and only one outcome give2 and receive2 are set as null; 
 * So there's one between give2 and receive2 that is not null, the other one (receive2 or give2) is not null too.
 * 
 * Choice rules: 0 means refuse trade, 2 means second choice if present, any other value for first choice. 
 * If second choice is not present 2 stands for accepting first choice
 * When trade is done, if the action has not ended yet, production is restarted from where it was interrupted
 */

public class TradeHandler {
	
	/**
	 * Method that checks if the trade chosen by the player is possible 
	 * @param player It's the player who selects the trade
	 * @param choice It's the choice selected by the player
	 * @return true if the trade is possible; false if it isn't possible.
	 */
	public boolean isPossible(Player player, int choice){  // could throw runtime exception: ClassCastException
		if(choice == 0){ //if player has refused the trade
			return true;}

		// we will need to recast  DevelopmentCard to  DevelopmentCardImplementation and to recast Effect to TradeEffect;
		DevelopmentCard temp=  player.getCardUsed();
		DevelopmentCardImplementation  card= (DevelopmentCardImplementation) temp;
		Effect effect= card.getPermanentEffect();
		TradeEffect tradeEffect = (TradeEffect) effect;
		
		if(choice==2  && tradeEffect.getGive2() != null){ //if 2nd trade exists and the player has chosen it. 
				boolean flag = player.getTestWarehouse().areResourcesEnough(tradeEffect.getGive2())&&player.getWarehouse().areResourcesEnough(tradeEffect.getGive2());
				if(!flag){
					player.notifyObservers(new Request(player.getStatus(),"cannot perform trade: not enough resources", new CardDescriber(player.getCardUsed())));
					return false;
		}	
				else return true;
		}
		// trade number 1 selected
		boolean flag = player.getTestWarehouse().areResourcesEnough(tradeEffect.getGive1())&& player.getWarehouse().areResourcesEnough(tradeEffect.getGive1());
		if(!flag){
			player.notifyObservers(new Request(player.getStatus(),"can not perform trade: not enough resources", new CardDescriber(player.getCardUsed())));
			return false;
		}
		else return true;
		
	}
	
	/**
	 * Method called to perform the trade that corresponds to the choice selected by the player
	 * @param player It's the player who selects the trade
	 * @param choice It's the choice selected by the player
	 */
	public void perform(Player player, int choice){  // could throw runtime exception: ClassCastException
		if(choice == 0) //if player has refused the trade
			return;
		
		// we will need to recast  DevelopmentCard to  DevelopmentCardImplementation and to recast Effect to TradeEffect;
		DevelopmentCard temp=  player.getCardUsed();
		DevelopmentCardImplementation  card= (DevelopmentCardImplementation) temp;
		Effect effect= card.getPermanentEffect();
		TradeEffect tradeEffect = (TradeEffect) effect;
		
		if (choice == 2 && tradeEffect.getGive2()!=null){ //if 2nd trade exists and the player has chosen it. 
				ResourcesOrPoints receive = tradeEffect.getReceive2();
				ResourcesOrPoints give= tradeEffect.getGive2();
				player.getWarehouse().spendResources(give);
				player.getWarehouse().add(receive);
				return;
			}	
		
		// trade number 1 selected
		ResourcesOrPoints receive = tradeEffect.getReceive1();
		ResourcesOrPoints give= tradeEffect.getGive1();
		player.getWarehouse().spendResources(give);
		player.getWarehouse().add(receive);
		return;
		
		
	}

}
