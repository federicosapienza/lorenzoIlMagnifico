package it.polimi.ingsw.GC_26_actionsHandlers;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_board.CouncilPalace;
import it.polimi.ingsw.GC_26_board.MarketPosition;
import it.polimi.ingsw.GC_26_board.MultipleHarvest;
import it.polimi.ingsw.GC_26_board.MultipleProduction;
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
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the handler to use when an action has to be performed
 */
public class ActionPerformerHandler {
	private GameElements gameElements;
	protected HarvestAndProductionHandler harvestAndProductionHandler;
	
	/**
	 * Constructor: it creates a handler to perform an action, by the evaluation of the parameters expressed by 
	 * gameElements and harvestAndProductionHandler 
	 * @param gameElements the game elements involved in the action
	 * @param harvestAndProductionHandler the handler for harvest and production
	 */
	public ActionPerformerHandler(GameElements gameElements, HarvestAndProductionHandler harvestAndProductionHandler){
		this.gameElements =gameElements;
		this.harvestAndProductionHandler=harvestAndProductionHandler;
	}
	
	 
	/**
	 * Method used to pay the corresponding payment when performing an action in a tower
	 * @param player It's the player who wants to perform the action
	 * @param familyMember the family memebrs involved in the action of the player
	 * @param action It's the action that the player wants to perform
	 */
	 protected void towerPerformPayment(Player player, FamilyMember familyMember, Action action){
		Tower tower = gameElements.getBoard().getTower(action.getZone());
		
		/**
		 * If the tower is occupied, pay coins (or any payment if rules are changed) (Brunelleschi effect is not activated)
		 */
		if(tower.isTheTowerOccupied()&& !player.getPermanentModifiers().isTowerOccupiedMalusDisabled())
			player.getWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
		
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
		if(! player.getPermanentModifiers().isTowerBonusRevokedOn())
			player.getWarehouse().add(position.getResourcesOrPointsinPosition());
		
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
		 player.getWarehouse().add(position.getResourcesOrPointsinPosition());
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
		 player.getWarehouse().add(position.getResourcesOrPointsInPosition());
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
