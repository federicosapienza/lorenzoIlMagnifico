package it.polimi.ingsw.GC_26_actionsHandlers;

import java.util.Set;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class HarvestAndProductionHandler {
	GameElements gameElements;
	
	public HarvestAndProductionHandler(GameElements gameElements) {
		this.gameElements=gameElements;
	}
	
	public void startHarvest(Player player, int value){
		Set<DevelopmentCard>  set = player.getPersonalBoard().getCurrentCards(DevelopmentCardTypes.TERRITORYCARD);
		perform(player, set , value);
	}
	
	public void startProduction(Player player, int value){
		Set<DevelopmentCard>  set = player.getPersonalBoard().getCurrentCards(DevelopmentCardTypes.BUILDINGCARD);
		perform(player, set , value);
	}

	private void perform(Player player, Set<DevelopmentCard> set, int value) {
		for(DevelopmentCard card: set){
			if(card.getActionValue()>=value){
				card.runPermanentEffect(player);
				while(player.getStatus()==PlayerStatus.TRADING){ 
		//if the effect of the card is trading, the execution is suspended, waiting for the choice of player (see TradeHandling).
					try {
						wait();  //TODO mettere un notifyAll adeguato
					} catch (InterruptedException e) {
						player.setStatus(PlayerStatus.ACTIONPERFORMED);  //ends the production
						e.printStackTrace();
					}
				}
				if(player.getStatus()!= PlayerStatus.PLAYING && player.getStatus() != PlayerStatus.SECONDPLAY )  
					//if the player is no more doing harvest or production (for example if timeout happens)
					break;
			}
			
		}
	}

}
