package it.polimi.ingsw.gc_26.model.handlers.action_handlers;

import java.util.List;

import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.gc_26.model.game.game_components.personal_board.PersonalBoardTile;
import it.polimi.ingsw.gc_26.model.player.Player;
import it.polimi.ingsw.gc_26.model.player.PlayerStatus;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the handler for Harvest and Production actions. 
 */
public class HarvestAndProductionHandler {
	//updated when player is interrupted due to trading, no need to reset at the end of the turn
	private int actionValue; 
	
	
	/**
	 * Method called to start the Harvest action
	 * @param player It's the player who is performing the Harvest action
	 * @param value It's the value of the Family Member which the player is using for the Harvest action
	 */
	public void startHarvest(Player player, int value){
		if(value<1){
			return;
		}
			
		//taking the cards
		List<DevelopmentCard> list = player.getPersonalBoard().getCurrentCards(DevelopmentCardTypes.TERRITORYCARD);
		
		//giving to the player the bonus of personal board tile
		PersonalBoardTile tile = player.getPersonalBoard().getPersonalBoardTile();
		if(value>= tile.getValue())
			player.getWarehouse().add(tile.getResourcesOrPointsHarvest());
		
		//launching harvest
		perform(player, list, value);
	}
	
	/**
	 * Method called to start the Production action
	 * @param player It's the player who is performing the Production action
	 * @param value It's the value of the Family Member which the player is using for the Production action
	 */
	public void startProduction(Player player, int value){
		if(value<1)
			return;
		
		//taking the cards
		List<DevelopmentCard> list = player.getPersonalBoard().getCurrentCards(DevelopmentCardTypes.BUILDINGCARD);
		
		//giving to the player the bonus of personal board tile
		PersonalBoardTile tile = player.getPersonalBoard().getPersonalBoardTile();
		if(value>= tile.getValue())
			player.getWarehouse().add(tile.getResourcesOrPointsProduction());
				
		//launching production
		perform(player, list, value);
	}

	/**
	 * Method called to perform the Harvest or Production action
	 * @param player It's the player who is performing the action
	 * @param set It's the list of Development Cards present on the player's Personal Board
	 * @param value It's the value of the Family Member which the player is using for the Harvest action
	 */
	private void perform(Player player, List<DevelopmentCard> set, int value) {
		//setting TestWarehouse: it is used in trades: we must ensure the player is not using resources just earned in trading.
		player.setTemporaryWarehouse();
		
		for(DevelopmentCard card: set){
			player.setCardUsed(card);  //  pointer to the card used necessary if action is suspended due to trading
			boolean actionCompleted = performTool(card, value, player);
			if(!actionCompleted)
				break;
			player.setCardUsed(null);
		}
	}
	
	/**
	 * Method called after trades, if they occurred: it continues to perform the Harvest or Production action
	 * @param player It's the player who is performing the action
	 */
	public void continuePerforming(Player player){
		//taking again the right set of card at the right position
		 List<DevelopmentCard> set =player.getPersonalBoard().getCurrentCards(player.getCardUsed().getType()) ;
		 boolean found=false;
		 DevelopmentCard cardTemp=null;
		 for(DevelopmentCard card: set){
			 if(!found && card.equals(player.getCardUsed())){ //
				 found=true;
				 cardTemp= player.getCardUsed(); //the card from which production restart after break
			 }
		 //once card has been found we continue the action performing
			 if(found && !card.equals(cardTemp)){
				 boolean actionCompleted =performTool(card, actionValue, player);
				 if(!actionCompleted)
					break;
			 }
		 }
	}
	
	/**
	 * Method that checks if the value of the Family Member used by the player if enough to run the permanent effects of the cards
	 * present in the Harvest or Production zone and if the check is positive, runs the permanent effect and if the player is trading, 
	 * interrupts the performance of the action, sets the actionValue to the value of the Family Member used by the player and 
	 * sets the flag to false
	 * @param card It's the Development Card which could affect the player with its permanent effect
	 * @param value It's the value of the Family Member used by the player contained in the parameter
	 * @param player It's the player who is performing the action
	 * @return false if the player is trading; else true.
	 */
	//if false action is suspended
	private boolean performTool(DevelopmentCard card, int value, Player player){
		boolean flag=true;
		if(value>=card.getActionValue()){
			card.runPermanentEffect(player);
			//calling the card. Going back we must be sure turn is not finished or player has been asked for choice
			
			
		if(player.getStatus()== PlayerStatus.TRADING){
			actionValue=value;
			flag =false;
				
			}	
		}
		return flag;
	}

	
	
}
