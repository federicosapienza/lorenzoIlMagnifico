package it.polimi.ingsw.GC_26_actionsHandlers;

import java.util.Set;


import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class HarvestAndProductionHandler {
	private GameElements gameElements;
	private int actionValue; //updated when player is interrupted due to trading , no need to reset at the end of the turn
	
	public HarvestAndProductionHandler(GameElements gameElements) {
		this.gameElements=gameElements;
	}
	
	public void startHarvest(Player player, int value){
		if(value<1)
			throw new IllegalArgumentException();
		//taking the cards
		Set<DevelopmentCard>  set = player.getPersonalBoard().getCurrentCards(DevelopmentCardTypes.TERRITORYCARD);
		
		//giving to the player the bonus of personal board tile
		PersonalBoardTile tile = player.getPersonalBoard().getPersonalBoardTile();
		if(value>= tile.getValue())
			player.getWarehouse().add(tile.getResourcesOrPointsHarvest());
		
		//launching harvest
		perform(player, set , value);
	}
	
	public void startProduction(Player player, int value){
		if(value<1)
			throw new IllegalArgumentException();
		
		//taking the cards
		Set<DevelopmentCard>  set = player.getPersonalBoard().getCurrentCards(DevelopmentCardTypes.BUILDINGCARD);
		
		//giving to the player the bonus of personal board tile
				PersonalBoardTile tile = player.getPersonalBoard().getPersonalBoardTile();
				if(value>= tile.getValue())
					player.getWarehouse().add(tile.getResourcesOrPointsProduction());
				
		//launching production
		perform(player, set , value);
	}

	private void perform(Player player, Set<DevelopmentCard> set, int value) {
		//setting TestWarehouse: it is used in trades: we must ensure the player is not using resources just earned in trading.
				player.setTemporaryWarehouse();
		
		for(DevelopmentCard card: set){
			player.setCardUsed(card);  //  pointer to the card used necessary if action is suspended due to trading
			performTool(card, value, player);
			player.setCardUsed(null);
		}
	}
	
	//called after trades
	public void continuePerforming(Player player){
		//retaking the right set of card at the right position
		 Set<DevelopmentCard> set =player.getPersonalBoard().getCurrentCards(player.getCardUsed().getType()) ;
		 boolean found=false;
		 for(DevelopmentCard card: set){
			 if(!found && card.equals(player.getCardUsed())) //
					 found=true;
				 
			 
		 //once card has been found we continue the action performing
		 performTool(card, actionValue, player);
	}
	}
	
	
	private void performTool(DevelopmentCard card, int value, Player player){
		if(card.getActionValue()>=value){
			card.runPermanentEffect(player);
			//calling the card. Going back we must be sure turn is not finished or player has been asked for choice
			synchronized (player) {
				if(player.getStatus()== PlayerStatus.TRADING){
					actionValue=value;
					return;
				}
				
			}
			
		}
		
	}

	
	
}
