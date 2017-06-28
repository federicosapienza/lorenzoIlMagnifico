package it.polimi.ingsw.GC_26_gameLogic;



import java.util.List;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_personalBoard.PersonalBoard;
import it.polimi.ingsw.GC_26_player.PermanentModifiers;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Info;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the logic followed at the end of the game to determine the winner.
 *
 */
public class EndGameLogic {
	private final GameElements gameElements;
	private final static int[] territoryBonus = new  int[]{0,0,1,4,10,20};
	private final static int[] charactersBonus = new  int[]{1,3,6,10,15,21};
	private final static int resourcesBonus = 5;
	private final ResourcesOrPoints militaryStrenghtFirstReward = ResourcesOrPoints.newPoints(5, 0, 0, 0);
	private final ResourcesOrPoints militaryStrenghtSecondReward = ResourcesOrPoints.newPoints(2, 0, 0, 0);

	/**
	 * Constructor: it allows the game to analyze, based on the game elements, all the information about the game
	 * and determine the winner
	 * @param gameElements the game elements of the current game
	 */
	public EndGameLogic(GameElements gameElements) {
		this.gameElements=gameElements;
	}
	
	/**
	 * Method used to start the analysis of the game
	 */
	public void start(){
		
		calcultatingPoints();
		
		elaboratingMilitaryPointResult();
		
		elaboratingResult();
		
	}
		
		
		
		
	

	private void calcultatingPoints(){
		for(Player player: gameElements.getPlayers()){
			PermanentModifiers modifiers =player.getPermanentModifiers();
			PersonalBoard personalBoard= player.getPersonalBoard();
			Warehouse warehouse= player.getWarehouse();
			int temp;
			
			temp= warehouse.getVictoryPoints()-warehouse.getVictoryPoints()/modifiers.getVictoryPointsReducer();
			warehouse.spendResources(ResourcesOrPoints.newPoints(temp, 0, 0, 0)) ;
			// player can lose victory points if permanent effect are activated : if his victory points go below zero they are set to zero.
			temp = modifiers.howManyVictoryPointLess();
			if(warehouse.getVictoryPoints()<temp)
				warehouse.spendResources(ResourcesOrPoints.newPoints(warehouse.getVictoryPoints(), 0, 0, 0));
			else warehouse.spendResources(ResourcesOrPoints.newPoints(temp, 0, 0, 0));

			
			//conquered territories
			if(!modifiers.pointsForThisCardType(DevelopmentCardTypes.TERRITORYCARD)){
				temp=personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.TERRITORYCARD);
				warehouse.add(ResourcesOrPoints.newPoints(territoryBonus[temp], 0, 0, 0));
			}
			//character cards owned
			if(!modifiers.pointsForThisCardType(DevelopmentCardTypes.CHARACTERCARD)){
				temp=personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.CHARACTERCARD);
				warehouse.add(ResourcesOrPoints.newPoints(charactersBonus[temp], 0, 0, 0));
			}
			//venture cards owned
			if(!modifiers.pointsForThisCardType(DevelopmentCardTypes.VENTURECARD)){
				temp=personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.VENTURECARD);
				warehouse.add(ResourcesOrPoints.newPoints(charactersBonus[temp], 0, 0, 0));
				List<DevelopmentCard> list = personalBoard.getCurrentCards(DevelopmentCardTypes.VENTURECARD);
				//activating permanent effect of character cards in order to give them victory points
				for(DevelopmentCard card: list){ 
					card.runPermanentEffect(player);
				}
			}
			temp= warehouse.getCoins()+warehouse.getServants()+warehouse.getStone()+warehouse.getWood();
			warehouse.add(ResourcesOrPoints.newPoints(temp/resourcesBonus, 0, 0, 0));
		}

	}
	
	
	private void elaboratingMilitaryPointResult(){
// starting elaborating results
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
			else if(temp>secondValue){
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
		
	}
	
	private void elaboratingResult(){
		//finding the winner
			int bestValue=0;
			for(Player p: gameElements.getPlayers()){
				if( p.getWarehouse().getVictoryPoints() > bestValue )
					bestValue = p.getWarehouse().getVictoryPoints();

			}
			// notifying of the winner: the player list is ordered so it ' s guaranteed that
			//if there is a parity the first in round order will win, as in game rules
			for(Player p: gameElements.getPlayers()){
				if( bestValue== p.getWarehouse().getVictoryPoints()){
					gameElements.notifyPlayers(new Info(GameStatus.ENDING, p.getName(), p.getName() +" has won!!!!!"));
					break;
				}
					
			}
				
	}
	
	
}
