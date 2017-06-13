package it.polimi.ingsw.GC_26_actionsHandlers;



import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardImplementation;
import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.effects.TradeEffect;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


//Trade effect needs dialog with user: there is often choice and player can also decide not to trade!
/*Creation rules: 
* if a resource to be given can be traded in two different ways , this resource is inserted both in give1 and give2
* if  there is a choice in giving and only one possible outcome, the outcome is inserted both in receive1 that receive2. 
* If there is only one income and only one outcome  give2 and receive2 are left null; 
* 
* So if one of both give2 and receive2 is not null , the other one (receive2 or give2) is not null
* 
* 
* Choice rules: 0 stands for refuse trade , 2 stands for second choice if present, any other value for first choice. 
* If second choice is not present 2 stands for accepting first choice
* 
*/
public class TradeHandler {
	private GameElements gameElements;
	public HarvestAndProductionHandler harvestAndProductionHandler;

	public TradeHandler(GameElements gameElements, HarvestAndProductionHandler harvestAndProductionHandler) {
		this.gameElements =gameElements;
		this.harvestAndProductionHandler=harvestAndProductionHandler;
	}
	
	
	public boolean isPossible(Player player , int choice){  // could throw runtime exception: ClassCastException
		if(choice == 0){ //if player has refused the trade
			return true;}

		// we will need to recast  DevelopmentCard to  DevelopmentCardImplementation and to recast Effect to TradeEffect;
		DevelopmentCard temp=  player.getCardUsed();
		DevelopmentCardImplementation  card= (DevelopmentCardImplementation) temp;
		Effect effect= card.getPermanentEffect();
		TradeEffect tradeEffect = (TradeEffect) effect;
		
		if(choice==2  && tradeEffect.getGive2() != null){ //if 2nd trade exists and the player has chosen it. 
				boolean flag = player.getTestWarehouse().areResourcesEnough(tradeEffect.getGive2());
				if(!flag){
					player.notifyObservers(new Request(player.getStatus(),"cannot perform trade: not enough resources", new CardDescriber(player.getCardUsed())));
					return false;
		}	
				else return false;
		}
		// trade number 1 selected
		boolean flag = player.getTestWarehouse().areResourcesEnough(tradeEffect.getGive1());
		if(!flag){
			player.notifyObservers(new Request(player.getStatus(),"cannot perform trade: not enough resources", new CardDescriber(player.getCardUsed())));
			return false;
		}
		else return true;
		
	}
	
	public void perform(Player player , int choice){  // could throw runtime exception: ClassCastException
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
				player.getWarehouse().spendResources(receive);
				return;
			}	
		
		// trade number 1 selected
		ResourcesOrPoints receive = tradeEffect.getReceive1();
		ResourcesOrPoints give= tradeEffect.getGive1();
		player.getWarehouse().spendResources(give);
		player.getWarehouse().spendResources(receive);
		return;
		
		
	}

}
