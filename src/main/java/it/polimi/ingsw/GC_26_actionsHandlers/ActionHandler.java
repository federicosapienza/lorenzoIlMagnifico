package it.polimi.ingsw.GC_26_actionsHandlers;




import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_board.CouncilPalace;
import it.polimi.ingsw.GC_26_board.HarvestZone;
import it.polimi.ingsw.GC_26_board.MarketPosition;
import it.polimi.ingsw.GC_26_board.MultipleHarvest;
import it.polimi.ingsw.GC_26_board.MultipleProduction;
import it.polimi.ingsw.GC_26_board.ProductionZone;
import it.polimi.ingsw.GC_26_board.SingleHarvest;
import it.polimi.ingsw.GC_26_board.SingleProduction;
import it.polimi.ingsw.GC_26_board.Tower;
import it.polimi.ingsw.GC_26_board.TowerPosition;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.PersonalBoardChangeNotification;
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
		checkerHandler = new ActionCheckerHandler();
		performerHandler = new ActionPerformerHandler();
		
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
		 return checkerHandler.canMemberGoToPosition(position, player, familyMember, action);
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
				return checkerHandler.canMemberGoToPosition(position, player, familyMember, action);
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
			return getCheckerHandler().canMemberGoToPosition(position, player, familyMember, action);}
		 
		 /**
		  * Multiple position case
		  */
		  if(action.getPosition()==2){ 
			 MultipleHarvest position = gameElements.getBoard().getHarvestZone().getMultipleHarvest();
			 return getCheckerHandler().canMemberGoToPosition(position, player, familyMember, action);}
		  throw new IllegalArgumentException();
	 }
	 
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
		 	return checkerHandler.canMemberGoToPosition(position, player, familyMember, action);	
		 }
		 else  if(action.getPosition()==2){ //multiple position
			 MultipleProduction position = gameElements.getBoard().getProductionZone().getMultipleProduction();
			 return checkerHandler.canMemberGoToPosition(position, player, familyMember, action);
		 } 
		 throw new IllegalArgumentException();
		}
	 
	 
	 
	 /**
		 * Method used to pay the corresponding payment when performing an action in a tower
		 * @param player It's the player who wants to perform the action
		 * @param familyMember the family members involved in the action of the player
		 * @param action It's the action that the player wants to perform
		 */
		 
	 
	 protected void towerPerformPayment(Player player, FamilyMember familyMember, Action action){
			Tower tower = gameElements.getBoard().getTower(action.getZone());
			
			/**
			 * If the tower is occupied, pay coins (or any payment if rules are changed) (Brunelleschi effect is not activated)
			 */
			performerHandler.payCoinsIfTowerOccupied(tower, player);
			/**
			 * Depending on the family member, the player is added in the list of players who are occupying the tower.
			 * There are two cases in which the player is not added in the list:
			 * 1) the family member is neutral, so the player who owns this family member is not added in the list
			 * 2) the action is a secondary action, so the family member is null and the player is not added in the list
			 */
			
			tower.setPlayerInTheTower(familyMember);  
			
			/**
			 * Setting the family member in the correct position of the tower
			 */
			TowerPosition position =gameElements.getBoard().getTower(action.getZone()).getPosition(action.getPosition());
			position.setFamilyMember(familyMember);
			
			/**
			 * getting resources if the permanent effect which revokes this chance is off(preacher card)
			 */
			performerHandler.getResourcesBonusFromTowerPositions(position, player);
			
			/**
			 * Getting the card
			 */
			
			DevelopmentCard card = position.getCard();
			/**
			 * Saving the reference to the used card: it's useful if the action is interrupted (i.e. for double payments choice)
			 */
			player.setCardUsed(card);
			/**
			 * Paying the card
			 */
			card.pay(player);
			synchronized (player) {
				if(player.getStatus()==PlayerStatus.CHOOSINGPAYMENT)
					return;
			}
			
			card.runImmediateEffect(player);  //repeat any change here in TwoPaymentHandler
			/** 
			 * The permanent effects of character cards are immediately activated
			 */
			if(card.getType() == DevelopmentCardTypes.CHARACTERCARD)
				card.runPermanentEffect(player);
			/**
			 * cleaning the parameter of the card that will no more be used
			 */
			player.setCardUsed(null);  
			
			/**
			 * Notifying players about changes to the personal board
			 */
			gameElements.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), new CardDescriber(card), null));			
			}
	 
		
	 /**
	  * Method to use when performing an action involving the market
	  * @param player It's the player who wants to perform the action
	  * @param familyMember It's the family member involved in the action
	  * @param action It's the action that the player wants to perform
	  */
	 protected void marketPerform(Player player, FamilyMember familyMember, Action action){
		 if(player.getPermanentModifiers().getMarketBanFlag())
			 throw new IllegalArgumentException();
		 MarketPosition position =gameElements.getBoard().getMarket().getPosition(action.getPosition());
		 position.setFamilyMember(familyMember);
	 }
	 
	 /**
	  * Method to use when performing an action in the Council Palace
	  * @param player It's the player who wants to perform the action
	  * @param familyMember It's the family member of the player involved in the action
	  * @param action It's the action that the player wants to perform
	  */
	 protected void councilPalacePerform(Player player, FamilyMember familyMember, Action action){
		 CouncilPalace position =gameElements.getBoard().getCouncilPalace();
		 position.setFamilyMember(familyMember);
		 performerHandler.getResourcesBonusFromCouncilPalacePosition(position, player);
		 /**
		  * setting the player in the list for the new round order
		  */
		 gameElements.getNextRoundOrder().nextRoundChanging(player);
	 }
	 
	 /**
	  * Method to use when performing an action in the Production zone
	  * @param player It's the player who wants to perform the action
	  * @param familyMember It's the family member of the player involved in the action
	  * @param action It's the action that the player wants to perform 
	  */
	 protected void productionPerform(Player player, FamilyMember familyMember, Action action){
		 int servants =action.getServantsUsed();
		 /**
		  * Handling permanent effect that doubles the servants needed:
		  */
		 if(servants!=0 && player.getPermanentModifiers().isDoubleServantsOn())
			 servants = servants/2;
	 	
		 
		 if(action.getPosition()==1){
			 	gameElements.getBoard().getProductionZone().addPlayerHere(familyMember);
				SingleProduction position = gameElements.getBoard().getProductionZone().getSingleProduction();
			 	position.setFamilyMember(familyMember);
			 	//Launching production, considering also the permanent effects 
			 	int actionValue= servants+ familyMember.getValue() 
					+player.getPermanentModifiers().getActionModifier(BoardZone.PRODUCTION);
			 	//checking if the action value is valid
				if(actionValue<1){  
					throw new IllegalArgumentException();
				}
				harvestAndProductionHandler.startProduction(player, actionValue);	
			 }
			 else  if(action.getPosition()==2){
				 MultipleProduction position = gameElements.getBoard().getProductionZone().getMultipleProduction();
				 position.setFamilyMember(familyMember);
				 //Launching Production, considering also the permanent effects and that multiple position implies a malus of -3
				 //on the value of the action
				 int actionValue= servants+ familyMember.getValue() +position.getMultipleActionMalus()
					+player.getPermanentModifiers().getActionModifier(BoardZone.PRODUCTION); 
				//Checking if action value is valid
				 if(actionValue<1) //checking if action value is valid
						throw new IllegalArgumentException();
				harvestAndProductionHandler.startProduction(player, actionValue);
				return;
		 }
			  
			 throw new IllegalArgumentException();
}
	 /**
	  * Method to use when performing an action in the harvest zone
	  * @param player It's the player who wants to perform the action
	  * @param familyMember It's the family member of the player involved in the action
	  * @param action It's the action that the player wants to perform 
	  */
	 protected void harvestPerform(Player player, FamilyMember familyMember, Action action){
		 int servants =action.getServantsUsed();
		 //Handling permanent effect that doubles the servants needed:
		 if(servants!=0 && player.getPermanentModifiers().isDoubleServantsOn())
			 servants = servants/2;
		//Single position case
		 if(action.getPosition()==1){  
			 	gameElements.getBoard().getHarvestZone().addPlayerHere(familyMember);
				SingleHarvest position = gameElements.getBoard().getHarvestZone().getSingleHarvest();
			 	position.setFamilyMember(familyMember);	
			 	//Launching Harvest, considering also the permanent effects
			 	int actionValue= servants+ familyMember.getValue() 
			 								+player.getPermanentModifiers().getActionModifier(BoardZone.HARVEST); 
			 	//Checking if action value is valid
			 	if(actionValue<1) 
					throw new IllegalArgumentException();
			 	harvestAndProductionHandler.startHarvest(player, actionValue);		
			 	}
		 //Multiple position case
			 else if(action.getPosition()==2){ 
				 MultipleHarvest position = gameElements.getBoard().getHarvestZone().getMultipleHarvest();
				 position.setFamilyMember(familyMember);
				//Launching Harvest, considering also the permanent effects and the malus equal to -3 implied by multiple position
				 int actionValue= servants+ familyMember.getValue() +position.getMultipleActionMalus() 
							+player.getPermanentModifiers().getActionModifier(BoardZone.HARVEST); 
				//checking if action value is valid
				 if(actionValue<1) 
						throw new IllegalArgumentException();
				 	harvestAndProductionHandler.startHarvest(player, actionValue);	
				 	return;
				 	}

			 
			 throw new IllegalArgumentException();
}
}
