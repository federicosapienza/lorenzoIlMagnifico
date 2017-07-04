package it.polimi.ingsw.GC_26.model.actionHandlers;

import java.util.List;

import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.gameComponents.personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;

public class HarvestAndProductionHandler {
	private int actionValue; //updated when player is interrupted due to trading , no need to reset at the end of the turn
	
	
	
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
	
	//called after trades
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
	
	//if false action is been suspended
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
