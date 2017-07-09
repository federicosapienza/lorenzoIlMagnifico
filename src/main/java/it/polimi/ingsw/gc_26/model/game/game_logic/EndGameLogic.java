package it.polimi.ingsw.gc_26.model.game.game_logic;



import java.util.List;

import it.polimi.ingsw.gc_26.messages.Info;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.gc_26.model.game.game_components.personalBoard.PersonalBoard;
import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.gc_26.model.game.game_components.resourcesAndPoints.Warehouse;
import it.polimi.ingsw.gc_26.model.player.PermanentModifiers;
import it.polimi.ingsw.gc_26.model.player.Player;

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
	private final static int[] TERRITORYBONUS = new  int[]{0,0,1,4,10,20};
	private final static int[] CHARACTERBONUS = new  int[]{1,3,6,10,15,21};
	private final static int RESOURCESBONUS = 5;
	
	private final ResourcesOrPoints militaryStrengthFirstReward = ResourcesOrPoints.newPoints(5, 0, 0, 0);
	private final ResourcesOrPoints militaryStrengthSecondReward = ResourcesOrPoints.newPoints(2, 0, 0, 0);

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
		
		calculatingPoints();
		
		elaboratingMilitaryPointResult();
		
		elaboratingResult();
		
	}
		
	/**
	 * Method that calculates the Victory Points of each player
	 */
	private void calculatingPoints(){
		for(Player player: gameElements.getPlayers()){
			PermanentModifiers modifiers =player.getPermanentModifiers();
			PersonalBoard personalBoard= player.getPersonalBoard();
			Warehouse warehouse= player.getWarehouse();
			int temp;
			
			temp= warehouse.getVictoryPoints()/modifiers.getVictoryPointsReducer();  
			//we obtain a value always smaller than player.getVictoryPoints(): no risk in spending
			warehouse.spendResources(ResourcesOrPoints.newPoints(temp, 0, 0, 0)) ;
			// player can lose victory points if permanent effect are activated : if his victory points go below zero they are set to zero.
			temp = modifiers.howManyVictoryPointLess();
			if(warehouse.getVictoryPoints()<temp)
				warehouse.spendResources(ResourcesOrPoints.newPoints(warehouse.getVictoryPoints(), 0, 0, 0));
			else warehouse.spendResources(ResourcesOrPoints.newPoints(temp, 0, 0, 0));

			
			//conquered territories
			if(!modifiers.pointsForThisCardType(DevelopmentCardTypes.TERRITORYCARD) && personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.TERRITORYCARD)>0){
				temp=personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.TERRITORYCARD);
				warehouse.add(ResourcesOrPoints.newPoints(TERRITORYBONUS[temp-1], 0, 0, 0));
			}
			//character cards owned
			if(!modifiers.pointsForThisCardType(DevelopmentCardTypes.CHARACTERCARD)&& personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.CHARACTERCARD)>0){
				temp=personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.CHARACTERCARD);
				warehouse.add(ResourcesOrPoints.newPoints(CHARACTERBONUS[temp-1], 0, 0, 0));
			}
			//venture cards owned
			if(!modifiers.pointsForThisCardType(DevelopmentCardTypes.VENTURECARD)&& personalBoard.getNumberOfCardPerType(DevelopmentCardTypes.VENTURECARD)>0){
				List<DevelopmentCard> list = personalBoard.getCurrentCards(DevelopmentCardTypes.VENTURECARD);
				//activating permanent effect of character cards in order to give them victory points
				for(DevelopmentCard card: list){ 
					card.runPermanentEffect(player);
				}
			}
			temp= warehouse.getCoins()+warehouse.getServants()+warehouse.getStone()+warehouse.getWood();
			warehouse.add(ResourcesOrPoints.newPoints(temp/RESOURCESBONUS, 0, 0, 0));
		}

	}
	
	/**
	 * Method that calculates the Military Points of each player and rewards the first two players with the greatest amount of Military 
	 * Points
	 */
	private void elaboratingMilitaryPointResult(){
		// starting elaborating results
		//military strength:
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
			int temp= player.getWarehouse().getMilitaryPoints();
			if(temp==bestValue){
				player.getWarehouse().add(militaryStrengthFirstReward);
				continue;
			}
			if(bestValue != secondValue &&temp==secondValue){
				player.getWarehouse().add(militaryStrengthSecondReward);

			}
				
		}	
		
	}
	
	/**
	 * Method that elaborates the results obtained through the methods which calculate the Victory and Military points in order to
	 * determine the winner and notify the players who the winner is
	 */
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
