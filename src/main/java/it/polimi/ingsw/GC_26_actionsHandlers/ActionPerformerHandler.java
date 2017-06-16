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

public class ActionPerformerHandler {
	private GameElements gameElements;
	protected HarvestAndProductionHandler harvestAndProductionHandler;
	
	public ActionPerformerHandler(GameElements gameElements, HarvestAndProductionHandler harvestAndProductionHandler){
		this.gameElements =gameElements;
		this.harvestAndProductionHandler=harvestAndProductionHandler;
	}
	
	 ///////////////////////  action performers
	 
	 protected void towerPerformPayment(Player player, FamilyMember familyMember, Action action){
		Tower tower = gameElements.getBoard().getTower(action.getZone());
		
		//if the tower is occupied pay coins(or any payment if rules are changed)(and Brunelleschi effect is not activated)
		if(tower.isTheTowerOccupied()&& !player.getPermanentModifiers().isTowerOccupiedMalusDisabled())
			player.getWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
		
		//sets that the player is in the Tower, passing the familyMember so that:
				//1) if the member is neutral , player is not added to the list 
				//2) if is a secondary action familyMember is null and player is not added
				tower.setPlayerInTheTower(familyMember);  
		
		//going to position 
		TowerPosition position =gameElements.getBoard().getTower(action.getZone()).getPosition(action.getPosition());
		position.setFamilyMember(familyMember);
		
		//getting resources (if the permanent effect which revoke this chance is off.(preacher card)
		if(! player.getPermanentModifiers().isTowerBonusRevokedOn())
			player.getWarehouse().add(position.getResourcesOrPointsinPosition());
		
		//getting the card
		
		DevelopmentCard card = position.getCard();
		//saves the reference to the card used: useful if the action is interrupted (i.e. for double payments choice)
		player.setCardUsed(card);
		//paying the card
		card.pay(player);
		synchronized (player) {
			if(player.getStatus()==PlayerStatus.CHOOSINGPAYMENT)
				return;
		}
		
		card.runImmediateEffect(player);  //repeat any change here in TwoPaymentHandler
				if(card.getType() == DevelopmentCardTypes.CHARACTERCARD)// character cards' permanent effect is immediately activated
							card.runPermanentEffect(player);
				player.setCardUsed(null);  //cleaning parameter of the card no more used
		gameElements.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), new CardDescriber(card), null));			
				}
		
		
		

		
	 
	 protected void marketPerform(Player player, FamilyMember familyMember, Action action){
		 if(player.getPermanentModifiers().getMarketBanFlag())
			 throw new IllegalArgumentException();
		 MarketPosition position =gameElements.getBoard().getMarket().getPosition(action.getPosition());
		 position.setFamilyMember(familyMember);
		 player.getWarehouse().add(position.getResourcesOrPointsinPosition());
	 }
	
	 protected void councilPalacePerform(Player player, FamilyMember familyMember, Action action){
		 CouncilPalace position =gameElements.getBoard().getCouncilPalace();
		 position.setFamilyMember(familyMember);
		 player.getWarehouse().add(position.getResourcesOrPointsInPosition());
		 //setting the player in the list for new round order
		 gameElements.getNextRoundOrder().nextRoundChanging(player);
	 }
	 protected void productionPerform(Player player, FamilyMember familyMember, Action action){
		 int servants =action.getServantsUsed();
		 //handling permanent effect that doubles the servants neeeded:
		 if(servants!=0 && player.getPermanentModifiers().isDoubleServantsOn())
			 servants = servants/2;
	 	
		 
		 if(action.getPosition()==1){
			 	gameElements.getBoard().getProductionZone().addPlayerHere(familyMember);
				SingleProduction position = gameElements.getBoard().getProductionZone().getSingleProduction();
			 	position.setFamilyMember(familyMember);
			 	//launching production
			 	int actionValue= servants+ familyMember.getValue() 
					+player.getPermanentModifiers().getActionModifier(BoardZone.PRODUCTION); //permanent Effect
				if(actionValue<1){  //checking if action value is valid
				throw new IllegalArgumentException();
				}
				harvestAndProductionHandler.startProduction(player, actionValue);	
			 }
			 else  if(action.getPosition()==2){
				 MultipleProduction position = gameElements.getBoard().getProductionZone().getMultipleProduction();
				 position.setFamilyMember(familyMember);
				 //launching Production
				 int actionValue= servants+ familyMember.getValue() +position.getMultipleActionMalus() //multiple position (-3)
					+player.getPermanentModifiers().getActionModifier(BoardZone.PRODUCTION); //permanent Effect
				 if(actionValue<1) //checking if action value is valid
						throw new IllegalArgumentException();
				harvestAndProductionHandler.startProduction(player, actionValue);
				return;
		 }
			  
			 throw new IllegalArgumentException();
}
	 protected void harvestPerform(Player player, FamilyMember familyMember, Action action){
		 int servants =action.getServantsUsed();
		 //handling permanent effect that doubles the servants neeeded:
		 if(servants!=0 && player.getPermanentModifiers().isDoubleServantsOn())
			 servants = servants/2;
	 	
		 if(action.getPosition()==1){  //single position
			 	gameElements.getBoard().getHarvestZone().addPlayerHere(familyMember);
				SingleHarvest position = gameElements.getBoard().getHarvestZone().getSingleHarvest();
			 	position.setFamilyMember(familyMember);	
			 	//launching harvest 
			 	int actionValue= servants+ familyMember.getValue() 
			 								+player.getPermanentModifiers().getActionModifier(BoardZone.HARVEST); //permanent Effect
			 	if(actionValue<1){ //checking if action value is valid
					throw new IllegalArgumentException();}
			 	harvestAndProductionHandler.startHarvest(player, actionValue);		
			 	}
			 else  if(action.getPosition()==2){ //multiple position
				 MultipleHarvest position = gameElements.getBoard().getHarvestZone().getMultipleHarvest();
				 position.setFamilyMember(familyMember);
				//launching harvest
				 int actionValue= servants+ familyMember.getValue() +position.getMultipleActionMalus() //multiple position (-3)
							+player.getPermanentModifiers().getActionModifier(BoardZone.HARVEST); //permanent Effect
				 if(actionValue<1) //checking if action value is valid
						throw new IllegalArgumentException();
				 	harvestAndProductionHandler.startHarvest(player, actionValue);	
				 	return;
				 	}

			 
			 throw new IllegalArgumentException();
}
	 

}
