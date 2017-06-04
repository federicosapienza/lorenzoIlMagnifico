package it.polimi.ingsw.GC_26_actionsHandlers;


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
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Resources;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

//contains the methods used both by firstActionHandler and SecondActionHandler
public abstract class ActionHandler {
	private GameElements gameElements;
	public HarvestAndProductionHandler harvestAndProductionHandler;
	
	public ActionHandler(GameElements gameElements, HarvestAndProductionHandler harvestAndProductionHandler){
		this.gameElements =gameElements;
		this.harvestAndProductionHandler=harvestAndProductionHandler;
	}
	public GameElements getGameElements() {
		return gameElements;
	}
	
	public HarvestAndProductionHandler getHarvestAndProductionHandler() {
		return harvestAndProductionHandler;
	}
	
	public abstract boolean isPossible(Player player, Action action);
	public abstract void perform(Player player, Action action);
	
	
	////// checkers
	protected boolean towerIsPossible(Player player, FamilyMember familyMember, Action action){
		//validating action sent
		if(action.getPosition()<=0 || action.getPosition()>GameParameters.getTowerFloorsNumber()) 
			throw new IllegalArgumentException();
		 //checks if the player is already on the tower
		Tower tower = gameElements.getBoard().getTower(action.getZone());
		if(tower.canFamilyMemberGoToTheTower(familyMember)){
			player.notifyObservers("Your coloured members are already in the tower");
			return false ;
					}
		
			//if the tower is occupied pay coins(or whatever payment, if rules are changed)if possible
		if(tower.isTheTowerOccupied()){
				if (!player.getTestWarehouse().areResourcesEnough(GameParameters.getTowerOccupiedMalus())){
					player.getTestWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
					player.notifyObservers("Not enough resources for going in a occupied tower");
					return false;
				}
				player.getTestWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
		}
		TowerPosition position = tower.getPosition(action.getPosition());
		canMemberGoToPosition( position,  player,  familyMember, action);
		
		//calling the card
		DevelopmentCard card = position.getCard();

		
		if(!card.canPlayerGetThis(player)){
			return false;
			//the card will notify why card could not be bought: 
			// 1) not enough resources
			// 2) military points' requirements needed for getting 3,4,5,6 territory card not reached
		}
		return true;
	 }
	 
	 protected boolean marketIsPossible(Player player, FamilyMember familyMember, Action action){
		 //validating action
		 if(action.getPosition()<=0 ||  //market's number of positions depends on number of players
					// with standard rules: if (numPlayers<4 && position>2)||(numPlayers=4 && position>4) throws exception.
					(gameElements.getNumberOfPlayers()<GameParameters.getNumPlayerforCompleteMarketActivation() 
							&& action.getPosition()>GameParameters.getMinMarketzone())||
					(gameElements.getNumberOfPlayers()>=GameParameters.getNumPlayerforCompleteMarketActivation() 
							&& action.getPosition()>GameParameters.getMinMarketzone()))
				throw new IllegalArgumentException();
		 
		 MarketPosition position = gameElements.getBoard().getMarket().getPosition(action.getPosition());
		 return canMemberGoToPosition(position, player, familyMember, action);
	 }

	 protected boolean councilPalaceIsPossible(Player player, FamilyMember familyMember, Action action){
		 
		 CouncilPalace position = gameElements.getBoard().getCouncilPalace();
				return canMemberGoToPosition(position, player, familyMember, action);
			}
	 
	 protected boolean harvestIsPossible(Player player, FamilyMember familyMember, Action action){
		 //validating action
		 if(action.getPosition()<=0 ||  //harvest  number of positions depends on number of players
					// with standard rules: if (numPlayers<3 && position>1)||(numPlayers>=3 && position>2) throws exception.
					(gameElements.getNumberOfPlayers()<GameParameters.getNumPlayersForMultipleZones() 
							&& action.getPosition()>GameParameters.getSingleHarvestOrProductionZones())||
					(gameElements.getNumberOfPlayers()>=GameParameters.getNumPlayerforCompleteMarketActivation() 
							&& action.getPosition()>GameParameters.getMultipleHarvestOrProductionZones()))
				throw new IllegalArgumentException();
		 //checking
		 HarvestZone zone =gameElements.getBoard().getHarvestZone();
		 if(zone.playerAlreadyHere(familyMember)){
			 player.notifyObservers("Already used a coloured member in harvest");
			 return false;
		 }

		 if(action.getPosition()==1){ //single position
			SingleHarvest position = zone.getSingleHarvest();
			return canMemberGoToPosition(position, player, familyMember, action);}
		  if(action.getPosition()==2){ //multiple position
			 MultipleHarvest position = gameElements.getBoard().getHarvestZone().getMultipleHarvest();
			 return canMemberGoToPosition(position, player, familyMember, action);}
		  throw new IllegalArgumentException();
	 }
	 
	 protected boolean productionIsPossible(Player player, FamilyMember familyMember, Action action){
		 //validating action. 
		 if(action.getPosition()<=0 ||  //harvest  number of positions depends on number of players
					// with standard rules: if (numPlayers<3 && position>1)||(numPlayers>=3 && position>2) throws exception.
					(gameElements.getNumberOfPlayers()<GameParameters.getNumPlayersForMultipleZones() 
							&& action.getPosition()>GameParameters.getSingleHarvestOrProductionZones())||
					(gameElements.getNumberOfPlayers()>=GameParameters.getNumPlayerforCompleteMarketActivation() 
							&& action.getPosition()>GameParameters.getMultipleHarvestOrProductionZones()))
				throw new IllegalArgumentException();
	
		 //checking
		 ProductionZone zone =gameElements.getBoard().getProductionZone();
		 if(zone.playerAlreadyHere(familyMember)){
			 player.notifyObservers("Already used a coloured member in production");
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
	 
	 
	 
	 //methods created to reduce lines of codes: 
	 //1)checking the position is free; 2)checking family member' s value is big enough for performing the action
	 private boolean canMemberGoToPosition(SinglePosition position, Player player, FamilyMember familyMember, Action action) {
		 if(position.IsPositionOccupied()){
				player.notifyObservers("position not free");
				return false;
			}
		 if(  (familyMember != null && familyMember.getValue() + action.getServantsUsed()< position.getValueOfPosition()) //first action
				 || (familyMember==null && player.getSecondactionValue()+action.getServantsUsed()
				 								<position.getValueOfPosition())){  // second action: those determined by cards
				player.notifyObservers("Family member's value and servants used not enough");
				return false;
				}
		 else return true;
	}
	// 1)checking family member' s value is big enough for performing the action
	 private boolean canMemberGoToPosition(MultiplePosition position, Player player, FamilyMember familyMember, Action action){
		 if(  (familyMember != null && familyMember.getValue() + action.getServantsUsed()< position.getValueOfPosition()) //first action
				 || (familyMember==null && player.getSecondactionValue()+action.getServantsUsed()
				 								<position.getValueOfPosition())){  // second action: those determined by cards
			 player.notifyObservers("Family member's value and servants used not enough");
				return false;
				}
		 else return true;
	 }
	
	 ///////////////////////  action performers
	 
	 protected void towerPerform(Player player, FamilyMember familyMember, Action action){
		Tower tower = gameElements.getBoard().getTower(action.getZone());
		//sets that the player is in the Tower, passing the familyMember so that:
		//1) if the member is neutral , player is not added to the list 
		//2) if is a secondary action familyMember is null and player is not added
		tower.setPlayerInTheTower(familyMember);  
		//if the tower is occupied pay coins(or any payment if rules are changed)
		if(tower.isTheTowerOccupied()){
		player.getWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
		
		//going to position 
		TowerPosition position =gameElements.getBoard().getTower(action.getZone()).getPosition(action.getPosition());
		position.setFamilyMember(familyMember);
		
		//getting resources (if the permanent effect which revoke this chance is off.
		if(! player.getPermanentModifiers().isBonusRevokedOn())
			player.getWarehouse().add(position.getResourcesOrPointsinPosition());
		
		//getting the card
		
		DevelopmentCard card = position.getCard();
		player.setCardUsed(card);//pointer to this card used if the action is interrupted (i.e. for double payments choice) 
		
		//paying the card
		card.pay(player);
		while(player.getStatus()==PlayerStatus.CHOOSINGPAYMENT){ // This status reached when player is asked to choose payment
			try {
				wait();
			} catch (InterruptedException e) { 
				System.out.println(e.getMessage());
				player.setStatus(PlayerStatus.WAITINGHISTURN);  // ends player turn
				e.printStackTrace();
			}
			
				card.runImmediateEffect(player);
				if(card.getType() == DevelopmentCardTypes.CHARACTERCARD)// character cards' permanent effect is immediately activated
							card.runPermanentEffect(player);
				player.setCardUsed(null);  //cleaning parameter of the card no more used
					
				//gameElements.getRankings().updateRankingPlayer(player);
				}
 }
		
		}
	 
	 protected void marketPerform(Player player, FamilyMember familyMember, Action action){
		 MarketPosition position =gameElements.getBoard().getMarket().getPosition(action.getPosition());
		 position.setFamilyMember(familyMember);
		 player.getWarehouse().add(position.getResourcesOrPointsinPosition());
	 }
	
	 protected void councilPalacePerform(Player player, FamilyMember familyMember, Action action){
		 CouncilPalace position =gameElements.getBoard().getCouncilPalace();
		 position.setFamilyMember(familyMember);
		 player.getWarehouse().add(position.getResourcesOrPointsInPosition());
		 //setting the player in the list for new round order
		 gameElements.getRankings().nextRoundChanging(player);
	 }
	 protected void productionPerform(Player player, FamilyMember familyMember, Action action){
		 if(action.getPosition()==1){
				SingleProduction position = gameElements.getBoard().getProductionZone().getSingleProduction();
			 	position.setFamilyMember(familyMember);
			 	//launching production
			 	harvestAndProductionHandler.startProduction(player, action.getServantsUsed()+familyMember.getValue());
			 }
			 else  if(action.getPosition()==2){
				 MultipleProduction position = gameElements.getBoard().getProductionZone().getMultipleProduction();
				 position.setFamilyMember(familyMember);
				 //launching Production
				 harvestAndProductionHandler.startProduction(player, action.getServantsUsed()+
						 											familyMember.getValue()+position.getActionMalus());
			 } 
			 throw new IllegalArgumentException();
}
	 protected void harvestPerform(Player player, FamilyMember familyMember, Action action){
		 if(action.getPosition()==1){  //single position
				SingleHarvest position = gameElements.getBoard().getHarvestZone().getSingleHarvest();
			 	position.setFamilyMember(familyMember);
			 	//launching harvest 
			 	harvestAndProductionHandler.startHarvest(player, action.getServantsUsed()+familyMember.getValue());			 }
			 else  if(action.getPosition()==2){ //multiple position
				 MultipleHarvest position = gameElements.getBoard().getHarvestZone().getMultipleHarvest();
				 position.setFamilyMember(familyMember);
				//launching harvest
				 	harvestAndProductionHandler.startHarvest(player, action.getServantsUsed()+
				 											familyMember.getValue()+position.getActionMalus());			
				 	}

			 
			 throw new IllegalArgumentException();
}
}
