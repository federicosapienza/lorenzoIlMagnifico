package it.polimi.ingsw.GC_26_actionsHandlers;

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
	
	/**
	 * Method that checks if an action that involves a tower is possible or not
	 * @param player the current player that wants to perform the action 
	 * @param familyMember the family member that the player would put in a tower
	 * @param action the action of the current player 
	 * @return true if the action can be performed in the tower, according to the rules; false if it can't be performed
	 */
	protected boolean towerIsPossible(Player player, FamilyMember familyMember, Action action){
		/**
		 * Validation of the action that has been sent
		 */
		if(action.getPosition()<=0 || action.getPosition()>GameParameters.getTowerFloorsNumber()) 
			throw new IllegalArgumentException();
		/**
		 * Checks if the player is not already owning the maximum number of cards allowed
		 */
		if(player.getPersonalBoard().getNumberOfCardPerType(convertZoneInCard(action.getZone()))
									== GameParameters.getMaxNumOfCards()){
					player.notifyObservers(new Request(player.getStatus(),"maximum number of card already reached", null));
					return false;
									}
			
		
		
		/**
		 * Checks if the player has already put a family member on the tower
		 */
		Tower tower = gameElements.getBoard().getTower(action.getZone());
		if(!tower.canFamilyMemberGoToTheTower(familyMember)){ //familyMember== null ->second Action: (considered by canPlayerGoToTheTower=
			player.notifyObservers(new Request(player.getStatus(),"Your coloured members are already in the tower", null));
			return false;
			}
		
		/**
		 * If the tower is occupied, the player has to pay coins(or whatever payment, if rules are changed)if he owns them; 
		 * it also checks that Brunelleschi effect is not activated
		 */
		if(tower.isTheTowerFree()&& !player.getPermanentModifiers().isTowerBonusRevokedOn()){
				if (!player.getTestWarehouse().areResourcesEnough(GameParameters.getTowerOccupiedMalus())){
					player.getTestWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
					player.notifyObservers(new Request(player.getStatus(),"Not enough resources for going in a occupied tower", null));
					return false;
				}
				player.getTestWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
		}
		TowerPosition position = tower.getPosition(action.getPosition());
		if(!canMemberGoToPosition( position,  player,  familyMember, action))
			return false;
		
		/**
		 * Calling the card
		 */
		DevelopmentCard card = position.getCard();

		/**
		 * Checking if the player can get the card: if he can't, it means two possible things:
		 * 1) The player has not enough resources
		 * 2) The player has not enough military points requirements needed to get 3,4,5,6 Territory card
		 */
		if(!card.canPlayerGetThis(player)){
			 player.notifyObservers(new Request(player.getStatus(),"not enough resources to get the card",new CardDescriber(card)));
			return false;
			
		}
		return true;
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
	 
	 private boolean canMemberGoToPosition(SinglePosition position, Player player, FamilyMember familyMember, Action action) {
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

	 private boolean canMemberGoToPosition(MultiplePosition position, Player player, FamilyMember familyMember, Action action){
		 
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
