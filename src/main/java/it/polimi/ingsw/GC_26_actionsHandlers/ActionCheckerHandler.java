package it.polimi.ingsw.GC_26_actionsHandlers;

import java.awt.Checkbox;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_board.CouncilPalace;
import it.polimi.ingsw.GC_26_board.HarvestZone;
import it.polimi.ingsw.GC_26_board.MarketPosition;
import it.polimi.ingsw.GC_26_board.MultipleHarvest;
import it.polimi.ingsw.GC_26_board.MultiplePosition;
import it.polimi.ingsw.GC_26_board.MultipleProduction;
import it.polimi.ingsw.GC_26_board.ProductionZone;
import it.polimi.ingsw.GC_26_board.SingleHarvest;
import it.polimi.ingsw.GC_26_board.SinglePosition;
import it.polimi.ingsw.GC_26_board.SingleProduction;
import it.polimi.ingsw.GC_26_board.Tower;
import it.polimi.ingsw.GC_26_board.TowerPosition;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
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
	private GameElements gameElements;
	protected HarvestAndProductionHandler harvestAndProductionHandler;
	
	/**
	 * Constructor: it creates a handler that checks the regularity and possibility of an action, by the evaluation of the
	 * parameters expressed by gameElements and harvestAndProductionHandler 
	 * @param gameElements the game elements that the handler will evaluate to check the action
	 * @param harvestAndProductionHandler the handler for harvest and production
	 */
	public ActionCheckerHandler(GameElements gameElements, HarvestAndProductionHandler harvestAndProductionHandler){
		this.gameElements =gameElements;
		this.harvestAndProductionHandler=harvestAndProductionHandler;
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
								== GameParameters.getMaxNumOfCards()){
				player.notifyObservers(new Request(player.getStatus(),"maximum number of card already reached", null));
				return false;
								}
	else return true;
	}
	
	
	
	/**
	 * Method that checks if the action that the player wants to perform in the market is possible
	 * @param player It's the player that wants to perform the action
	 * @param familyMember It's the family member involved in the action
	 * @param action It's the action that the player wants to perform
	 * @return true if the action is possible according to the rules of the game; false if it is not possible 
	 */
	 protected boolean marketIsPossible(Player player, FamilyMember familyMember, Action action){
		 /**
		  * Validating action
		  */
		 if(player.getPermanentModifiers().getMarketBanFlag()){
			player.notifyObservers(new Request(player.getStatus(),"player is banned from the Market!", null));
			 return false;
		 }
		 
		 /**
		  * The number of the positions in the market depends on the number of players, with standard rules:
		  * if (numPlayers<4 && position>2)||(numPlayers=4 && position>4) an exception has to be thrown.
		  */
		 if(action.getPosition()<=0 || 
					(gameElements.getNumberOfPlayers()<GameParameters.getNumPlayerforCompleteMarketActivation() 
							&& action.getPosition()>GameParameters.getMinMarketZones())||
					(gameElements.getNumberOfPlayers()>=GameParameters.getNumPlayerforCompleteMarketActivation() 
							&& action.getPosition()>GameParameters.getMinMarketZones()))
				throw new IllegalArgumentException();
		 
		 MarketPosition position = gameElements.getBoard().getMarket().getPosition(action.getPosition());
		 return canMemberGoToPosition(position, player, familyMember, action);
	 }

	 /**
	  * Method that checks if the action that the player wants to perform in the Council Palace is possible
	  * @param player It's the player that wants to perform the action
	  * @param familyMember It's the family member involved in the action
	  * @param action It's the action that the player wants to perform
	  * @return true if the action is possible according to the rules of the game; false if it is not possible 
	  */
	 protected boolean councilPalaceIsPossible(Player player, FamilyMember familyMember, Action action){
		 
		 CouncilPalace position = gameElements.getBoard().getCouncilPalace();
				return canMemberGoToPosition(position, player, familyMember, action);
			}
	 
	 /**
	  * Method that checks if the action that the player wants to perform in the Harvest zone is possible
	  * @param player It's the player that wants to perform the action
	  * @param familyMember It's the family member involved in the action
	  * @param action It's the action that the player wants to perform
	  * @return true if the action is possible according to the rules of the game; false if it is not possible 
	  */
	 protected boolean harvestIsPossible(Player player, FamilyMember familyMember, Action action){
		 /**
		  * Validating the action.
		  * The number of positions in the Harvest zone depends on the number of players 
		  * with standard rules: if (numPlayers<3 && position>1)||(numPlayers>=3 && position>2) throws exception.
		  */
		 if(action.getPosition()<=0 ||  
					(gameElements.getNumberOfPlayers()<GameParameters.getNumPlayersForMultipleZones() 
							&& action.getPosition()>GameParameters.getSingleHarvestOrProductionZones())||
					(gameElements.getNumberOfPlayers()>=GameParameters.getNumPlayerforCompleteMarketActivation() 
							&& action.getPosition()>GameParameters.getMultipleHarvestOrProductionZones()))
				throw new IllegalArgumentException();
		 /**
		  * Checking
		  */
		 HarvestZone zone =gameElements.getBoard().getHarvestZone();
		 if(zone.playerAlreadyHere(familyMember)){
				player.notifyObservers(new Request(player.getStatus(),"Already used a coloured member in harvest", null));
			 return false;
		 }

		 /**
		  * Single position case
		  */
		 if(action.getPosition()==1){ 
			SingleHarvest position = zone.getSingleHarvest();
			return canMemberGoToPosition(position, player, familyMember, action);}
		 
		 /**
		  * Multiple position case
		  */
		  if(action.getPosition()==2){ 
			 MultipleHarvest position = gameElements.getBoard().getHarvestZone().getMultipleHarvest();
			 return canMemberGoToPosition(position, player, familyMember, action);}
		  throw new IllegalArgumentException();
	 }
	 
	 /**
	  * Method that checks if the action that the player wants to perform in the Council Palace is possible
	  * @param player It's the player that wants to perform the action
	  * @param familyMember It's the family member involved in the action
	  * @param action It's the action that the player wants to perform
	  * @return true if the action is possible according to the rules of the game; false if it is not possible 
	  */
	 protected boolean productionIsPossible(Player player, FamilyMember familyMember, Action action){
		 /**
		  * Validating action. 
		  * The number of positions in the Production zone depends on the number of players 
		  * with standard rules: if (numPlayers<3 && position>1)||(numPlayers>=3 && position>2) throws exception.
		  */
		 if(action.getPosition()<=0 || 
					(gameElements.getNumberOfPlayers()<GameParameters.getNumPlayersForMultipleZones() 
							&& action.getPosition()>GameParameters.getSingleHarvestOrProductionZones())||
					(gameElements.getNumberOfPlayers()>=GameParameters.getNumPlayerforCompleteMarketActivation() 
							&& action.getPosition()>GameParameters.getMultipleHarvestOrProductionZones()))
				throw new IllegalArgumentException();
	
		 /**
		  * Checking
		  */
		 ProductionZone zone =gameElements.getBoard().getProductionZone();
		 if(zone.playerAlreadyHere(familyMember)){
			 player.notifyObservers(new Request(player.getStatus(),"Already used a coloured member in production", null));
			 return false;
		 }
			 
		 if(action.getPosition()==1){ //single position
			SingleProduction position = zone.getSingleProduction();
		 	return canMemberGoToPosition(position, player, familyMember, action);	
		 }
		 else  if(action.getPosition()==2){ //multiple position
			 MultipleProduction position = gameElements.getBoard().getProductionZone().getMultipleProduction();
			 return canMemberGoToPosition(position, player, familyMember, action);
		 } 
		 throw new IllegalArgumentException();
		}
	 
	 
	 
	 /**
	  * The following methods created to reduce lines of codes.
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
		 
		 if(position.IsPositionFree()){
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
