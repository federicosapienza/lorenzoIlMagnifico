package it.polimi.ingsw.GC_26_actionsHandlers;




import it.polimi.ingsw.GC_26_board.Tower;
import it.polimi.ingsw.GC_26_board.TowerPosition;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the abstract class that represents the handler for every action.
 * It contains references to ActionCheckerHandler and ActionPerformerHandler that have the methods used both
 * by firstActionHandler and SecondActionHandler
 *
 */


public abstract class ActionHandler {
	private GameElements gameElements;
	protected HarvestAndProductionHandler harvestAndProductionHandler;
	private ActionCheckerHandler checkerHandler;
	private ActionPerformerHandler performerHandler;
	
	/**
	 * Constructor: it creates an action handler with the same game elements and harvest and production handler expressed
	 * in the parameters and 2 new handlers: the first to check to possibility to do an action, 
	 * the second to allow a player to do the action if it is possible. Both are created with the same parameters as above. 
	 * @param gameElements the game elements of the game
	 * @param harvestAndProductionHandler the handler for harvest and production 
	 */
	public ActionHandler(GameElements gameElements, HarvestAndProductionHandler harvestAndProductionHandler){
		this.gameElements = gameElements;
		this.harvestAndProductionHandler = harvestAndProductionHandler;
		checkerHandler = new ActionCheckerHandler(gameElements, harvestAndProductionHandler);
		performerHandler = new ActionPerformerHandler(gameElements, harvestAndProductionHandler);
		
	}
	
	/**
	 * Getter method that returns the game elements
	 * @return the game elements
	 */
	public GameElements getGameElements() {
		return gameElements;
	}
	
	/**
	 * Getter method that returns the handler for harvest and production
	 * @return the handler for harvest and production
	 */
	public HarvestAndProductionHandler getHarvestAndProductionHandler() {
		return harvestAndProductionHandler;
	}
	
	/**
	 * Method that verifies if a player can perform an action or not
	 * @param player It's the player who wants to perform the action
	 * @param action It's the action of the player that has to be verified
	 * @return true if the player has the right to perform the action; false if he cannot do the action
	 */
	public abstract boolean isPossible(Player player, Action action);
	
	/**
	 * Method used to allow a player to perform an action
	 * @param player It's the player who wants to perform an action
	 * @param action It's the action that the player wants to perform
	 */
	public abstract void perform(Player player, Action action);
	
	/**
	 * Getter method that returns the handler that checks the possibility of an action
	 * @return the handler that checks the possibility of an action
	 */
	public ActionCheckerHandler getCheckerHandler() {
		return checkerHandler;
	}
	
	/**
	 * Getter method that returns the handler that allows a player to perform an action
	 * @return the handler that allows a player to perform an action
	 */
	
	public ActionPerformerHandler getPerformerHandler() {
		return performerHandler;
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
		if(!checkerHandler.checkMaximumNumberOfCardsNotReached(player, action))
			return false;			
	
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
		if(!checkerHandler.canMemberGoToPosition( position,  player,  familyMember, action))
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
	 
	
	
	
	
	
}
