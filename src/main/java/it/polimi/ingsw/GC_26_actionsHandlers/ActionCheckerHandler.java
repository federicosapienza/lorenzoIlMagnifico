package it.polimi.ingsw.GC_26_actionsHandlers;



import javax.net.ssl.ExtendedSSLSession;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_board.MultiplePosition;

import it.polimi.ingsw.GC_26_board.SinglePosition;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the handler that checks whether the action chosen by a player is possible or not and 
 *
 */
public class ActionCheckerHandler {
	
	/**
	 * Constructor: it creates a handler that checks the regularity and possibility of an action, by the evaluation of the
	 * parameters expressed by gameElements and harvestAndProductionHandler 
	 * @param gameElements the game elements that the handler will evaluate to check the action
	 * @param harvestAndProductionHandler the handler for harvest and production
	 */

	
	/**
	 * Validation of the action that has been sent
	 */
	public boolean towerActionValidation(Player player, Action action){
		if (action.getPosition()<=0 || action.getPosition()>GameParameters.getTowerFloorsNumber()){
			player.notifyObservers(new Request(player.getStatus(),"Position not valid", null));
			return false;
		}
		return true;
	}
	
	 /**
	  * The number of the positions in the market depends on the number of players, with standard rules:
	  * if (numPlayers<4 && position>2)||(numPlayers=4 && position>4) an exception has to be thrown.
	  */
	public boolean marketActionValidation(Player player, Action action, int  numOfPlayers){
	 if(action.getPosition()<=0 ){
			player.notifyObservers(new Request(player.getStatus(),"Position not valid", null));
	 		return false;
	 }
	if((numOfPlayers<GameParameters.getNumPlayerforCompleteMarketActivation() 
			&& action.getPosition()>GameParameters.getMinMarketZones())||
			(numOfPlayers>=GameParameters.getNumPlayerforCompleteMarketActivation() 
			&& action.getPosition()>GameParameters.getMinMarketZones())){
			player.notifyObservers(new Request(player.getStatus(),"Position not valid", null));
			return false;
	 }
	 else return true;
	}

	 
	 /**
	  * Validating the action.
	  * The number of positions in the Harvest and Production zone depends on the number of players 
	  * with standard rules: if (numPlayers<3 && position>1)||(numPlayers>=3 && position>2) throws exception.
	  */
	
	public boolean productionAndHarvestValidation(Player player, Action action, int  numOfPlayers){
		if(action.getPosition()<=0 ){
			player.notifyObservers(new Request(player.getStatus(),"Position not valid", null));
	 		return false;
		}
	 if ( (numOfPlayers<GameParameters.getNumPlayersForMultipleZones() 
						&& action.getPosition()>GameParameters.getSingleHarvestOrProductionZones())||
				( numOfPlayers>=GameParameters.getNumPlayersForMultipleZones() 
						&& action.getPosition()>GameParameters.getMultipleHarvestOrProductionZones())){
			player.notifyObservers(new Request(player.getStatus(),"Position not valid", null));
			return false;
	 }
	 else return true;
	}
	
	
	
	//check if the player has enough servants compared to those he asked to use in action
	public boolean areServantsEnough(Player player, Action action){
	//enough servants?
			if(!player.getTestWarehouse().areResourcesEnough(ResourcesOrPoints.newResources(0,action.getServantsUsed(),0,0))){
				player.notifyObservers(new Request(player.getStatus(),"Not enough servants", null));
				return false;}
			else return true;
	}
	
	public boolean isFamilyMemberFree(FamilyMember familyMember, Player player){
		if(!familyMember.isFree()){
			player.notifyObservers(new Request(player.getStatus(),"Family member not free", null));
			return false;
		}
		else return true;
	}
	
	
	
	/**
	 * Checks if the player is not already owning the maximum number of cards allowed
	 */
	public boolean checkMaximumNumberOfCardsNotReached(Player player, Action action){
	if(player.getPersonalBoard().getNumberOfCardPerType(convertZoneInCard(action.getZone()))
								>= GameParameters.getMaxNumOfCards()){
				player.notifyObservers(new Request(player.getStatus(),"maximum number of card already reached", null));
				return false;
								}
	else return true;
	}
	
	

	
	 

	 
	 
	 /**
	  * 
	  * 
	  * This method checks if the position if free and if the family member's value is enough to perform the action
	  * @param position It's the position that has to be checked
	  * @param player It's the player that wants to perform the action
	  * @param familyMember It's the family member involved in the action: its value has to be checked
	  * @param action It's the action that the player wants to perform
	  * @return true if the action can be performed according to the rules; false if it cannot be performed
	  */
	 
	 public boolean canMemberGoToPosition(SinglePosition position, Player player, FamilyMember familyMember, Action action) {
		 /**
		  * Ludovico Ariosto's effect
		  */
		 if(player.getPermanentModifiers().isGoingInOccupiedPositionsAllowed()) 
			 return true;
		 
		 if(!position.IsPositionFree()){
				player.notifyObservers(new Request(player.getStatus(),"position not free", null));
				return false;
			}
		 
		 int servants =action.getServantsUsed();
		 /**
		  * handling permanent effect that doubles the servants needed:
		  */
		 if(servants!=0 && player.getPermanentModifiers().isDoubleServantsOn())
			 servants = servants/2;
			 
		 if(  (familyMember != null && familyMember.getValue() + servants+ 
				 	player.getPermanentModifiers().getActionModifier(action.getZone()) < position.getValueOfPosition())){  //first action
					player.notifyObservers(new Request(player.getStatus(),"Family member's value and servants used not enough", null));

				return false; }
		 else if (familyMember==null && player.getSecondactionValue()+action.getServantsUsed()+ 
				 player.getPermanentModifiers().getActionModifier(action.getZone())
				 								<position.getValueOfPosition()){  // second action: those determined by cards
				player.notifyObservers(new Request(player.getStatus()," servants used not enough", null));
				return false;
				}
		 else return true;
	}
	 /**
	  * Method that checks the possibility of an action that involves multiple positions
	  * @param position It's the multiple position involved in the action
	  * @param player It's the player who wants to perform the action
	  * @param familyMember It's the player's family member involved in the action
	  * @param action It's the action that the player wants to perform
	  * @return true if the action can be performed according to the rules; false if it can't be performed
	  */

	 public boolean canMemberGoToPosition(MultiplePosition position, Player player, FamilyMember familyMember, Action action){
		 
		 int servants =action.getServantsUsed();
		 /**
		  * Handling the permanent effect that doubles the servants needed:
		  */
		 if(servants!=0 && player.getPermanentModifiers().isDoubleServantsOn())
			 servants = servants/2;
		 
		 /**
		  * Checking if the value of the family member is enough to perform the action or not
		  */
		 if((familyMember != null && familyMember.getValue() + servants+ 
				 	player.getPermanentModifiers().getActionModifier(action.getZone()) < position.getValueOfPosition())){  //first action
					player.notifyObservers(new Request(player.getStatus(),"Family member's value and servants used not enough", null));
					return false; 
					}
		 else if (familyMember==null && player.getSecondactionValue()+action.getServantsUsed()+  // second action: those determined by cards
				 player.getPermanentModifiers().getActionModifier(action.getZone())
				 								<position.getValueOfPosition()){  
			 
				player.notifyObservers(new Request(player.getStatus()," servants used not enough", null));
				return false;
				}
		 else return true;
	 }
	
	 /**
	  * Method that converts the board zone expressed in the parameter to the corresponding type of development card
	  * @param zone It's the board zone that has to be converted to a development card
	  * @return the type of development card that matches with the board zone expressed in the parameter
	  */
		protected DevelopmentCardTypes convertZoneInCard(BoardZone zone){
			switch (zone) {
			case TERRITORYTOWER: 
				return DevelopmentCardTypes.TERRITORYCARD;
			case BUILDINGTOWER: 
				return DevelopmentCardTypes.BUILDINGCARD;
			case CHARACTERTOWER:
				return DevelopmentCardTypes.CHARACTERCARD;
			case VENTURETOWER:
				return DevelopmentCardTypes.VENTURECARD;
			default:
				throw new IllegalArgumentException();
			}
	 
		}
	
}
