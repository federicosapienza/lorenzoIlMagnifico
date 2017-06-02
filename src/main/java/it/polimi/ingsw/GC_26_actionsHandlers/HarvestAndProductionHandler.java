package it.polimi.ingsw.GC_26_actionsHandlers;

import java.util.Set;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class HarvestAndProductionHandler {
	GameElements gameElements;
	
	public HarvestAndProductionHandler(GameElements gameElements) {
		this.gameElements=gameElements;
	}
	
	public void startHarvest(Player player, int value){
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
			if(card.getActionValue()>=value){
				card.runPermanentEffect(player);
				while(player.getStatus()==PlayerStatus.TRADING){ 
		//if the effect of the card is trading, the execution is suspended, waiting for the choice of player (see TradeHandling).
					try {
						synchronized (this) {
							wait(); 	
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			//	if(player.getStatus()!= PlayerStatus.PLAYING && player.getStatus() != PlayerStatus.SECONDPLAY )  
				
				//if the player is no more doing harvest or production (for example if timeout happens)
				//	break;
			}
			player.setCardUsed(null);
		}
	}
	

}
