package it.polimi.ingsw.GC_26_gameLogic;



import java.net.NetworkInterface;
import java.util.List;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_personalBoard.PersonalBoard;
import it.polimi.ingsw.GC_26_player.PermanentModifiers;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Info;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

public class EndGameLogic {
	private final GameElements gameElements;
	private final int territoryBonus[]= new  int[]{0,0,1,4,10,20};
	private final int charactersBonus[]= new  int[]{1,3,6,10,15,21};
	private final int resourcesBonus= 5;
	private final ResourcesOrPoints militaryStrenghtFirstReward= ResourcesOrPoints.newPoints(5, 0, 0, 0);
	private final ResourcesOrPoints militaryStrenghtSecondReward= ResourcesOrPoints.newPoints(2, 0, 0, 0);;

	
	public EndGameLogic(GameElements gameElements) {
		this.gameElements=gameElements;
	}
	
	public void start(){
		for(Player player: gameElements.getPlayers()){
			PermanentModifiers modifiers =player.getPermanentModifiers();
			PersonalBoard personalBoard= player.getPersonalBoard();
			Warehouse warehouse= player.getWarehouse();
			int temp=0;
			
			temp=warehouse.getVictoryPoints()/modifiers.getVictoryPointsReducer();
			// player can lose victory points if permanent effect are activated : if his victory points go below zero they are set to zero.
			temp = modifiers.howManyVictoryPointLess();
			if(warehouse.getVictoryPoints()<temp)
				warehouse.spendResources(ResourcesOrPoints.newPoints(warehouse.getVictoryPoints(), 0, 0, 0));
			else warehouse.spendResources(ResourcesOrPoints.newPoints(temp, 0, 0, 0));

			
			//conquered territories
			if(!modifiers.pointsForThisCardType(DevelopmentCardTypes.TERRITORYCARD)){
				temp=personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.TERRITORYCARD);
				warehouse.add(ResourcesOrPoints.newPoints(territoryBonus[temp-1], 0, 0, 0));
			}
			//character cards owned
			if(!modifiers.pointsForThisCardType(DevelopmentCardTypes.CHARACTERCARD)){
				temp=personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.CHARACTERCARD);
				warehouse.add(ResourcesOrPoints.newPoints(charactersBonus[temp-1], 0, 0, 0));
			}
			//venture cards owned
			if(!modifiers.pointsForThisCardType(DevelopmentCardTypes.VENTURECARD)){
				temp=personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.VENTURECARD);
				List<DevelopmentCard> list = personalBoard.getCurrentCards(DevelopmentCardTypes.VENTURECARD);
				//activating permanent effect of character cards in order to give them victory points
				for(DevelopmentCard card: list){ 
					card.runPermanentEffect(player);
				}
			}
			temp= warehouse.getCoins()+warehouse.getServants()+warehouse.getStone()+warehouse.getWood();
			warehouse.add(ResourcesOrPoints.newPoints(temp/resourcesBonus, 0, 0, 0));
			// player can lose victory points if permanent effect are activated : if his victory points go below zero they are set to zero.
			temp = modifiers.howManyVictoryPointLess();
			if(warehouse.getVictoryPoints()<temp)
				warehouse.spendResources(ResourcesOrPoints.newPoints(warehouse.getVictoryPoints(), 0, 0, 0));
			else warehouse.spendResources(ResourcesOrPoints.newPoints(temp, 0, 0, 0));

		}
		//military strenght:
		int bestValue=0;
		int secondValue=0;
		//searching military strength best values
		for(Player p: gameElements.getPlayers()){
			int temp=p.getWarehouse().getMilitaryPoints();
			if(temp>bestValue){
				secondValue=bestValue;
				bestValue=temp;
				continue;
			}
			if(temp>secondValue){
				secondValue=temp;
				continue;
			}
		}
		
		//rewarding the players with more military points
		for(Player player: gameElements.getPlayers()){
			int  temp= player.getWarehouse().getMilitaryPoints();
			if(temp==bestValue){
				player.getWarehouse().add(militaryStrenghtFirstReward);
				continue;
			}
			if(bestValue != secondValue &&temp==secondValue){
				player.getWarehouse().add(militaryStrenghtSecondReward);

			}
				
		}	
		//finding the winner
		bestValue=0;
		for(Player p: gameElements.getPlayers()){
			if( bestValue> p.getWarehouse().getVictoryPoints())
				bestValue= p.getWarehouse().getVictoryPoints();

		}
		// notifying of the winner: the player list is ordered so it ' s guaranteed that
		//if there is a parity the first in round order will win, as in game rules
		for(Player p: gameElements.getPlayers()){
			if( bestValue== p.getWarehouse().getVictoryPoints())
				gameElements.notifyPlayers(new Info(GameStatus.ENDING, p.getName(), p.getName() +" has won!!!!!"));
		}
		
		
		
	}
}
