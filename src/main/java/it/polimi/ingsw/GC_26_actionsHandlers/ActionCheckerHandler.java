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

public class ActionCheckerHandler {
	private GameElements gameElements;
	protected HarvestAndProductionHandler harvestAndProductionHandler;
	
	public ActionCheckerHandler(GameElements gameElements, HarvestAndProductionHandler harvestAndProductionHandler){
		this.gameElements =gameElements;
		this.harvestAndProductionHandler=harvestAndProductionHandler;
	}
	
//////checkers
	protected boolean towerIsPossible(Player player, FamilyMember familyMember, Action action){
		//validating action sent
		if(action.getPosition()<=0 || action.getPosition()>GameParameters.getTowerFloorsNumber()) 
			throw new IllegalArgumentException();
		//checks if the player has not already the maximum number of cards allowed
		if(player.getPersonalBoard().getNumberOfCardPerType(convertZoneInCard(action.getZone()))
									== GameParameters.getMaxNumOfCards()){
					player.notifyObservers(new Request(player.getStatus(),"maximum number of card already reached", null));
					return false;
									}
			
		
		
		 //checks if the player is already on the tower
		Tower tower = gameElements.getBoard().getTower(action.getZone());
		if(!tower.canFamilyMemberGoToTheTower(familyMember)){ //familyMember== null ->second Action: (considered by canPlayerGoToTheTower=
			player.notifyObservers(new Request(player.getStatus(),"Your coloured members are already in the tower", null));
			return false ;
					}
		
		//if the tower is occupied pay coins(or whatever payment, if rules are changed)if possible; also checks that Brunelleschi effect not activated
		if(tower.isTheTowerOccupied()&& !player.getPermanentModifiers().isTowerBonusRevokedOn()){
				if (!player.getTestWarehouse().areResourcesEnough(GameParameters.getTowerOccupiedMalus())){
					player.getTestWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
					player.notifyObservers(new Request(player.getStatus(),"Not enough resources for going in a occupied tower", null));
					return false;
				}
				player.getTestWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
		}
		TowerPosition position = tower.getPosition(action.getPosition());
		canMemberGoToPosition( position,  player,  familyMember, action);
		
		//calling the card
		DevelopmentCard card = position.getCard();

		
		if(!card.canPlayerGetThis(player)){
			 player.notifyObservers(new Request(player.getStatus(),"not enough resources to get the card",new CardDescriber(card)));
			return false;
			//could mean
			// 1) not enough resources
			// 2) military points' requirements needed for getting 3,4,5,6 territory card not reached
		}
		return true;
	 }
	 
	 protected boolean marketIsPossible(Player player, FamilyMember familyMember, Action action){
		 //validating action
		 if(player.getPermanentModifiers().getMarketBanFlag()){
			player.notifyObservers(new Request(player.getStatus(),"player is banned from the Market!", null));
			 return false;
		 }
		 
		 
		 if(action.getPosition()<=0 ||  //market's number of positions depends on number of players
					// with standard rules: if (numPlayers<4 && position>2)||(numPlayers=4 && position>4) throws exception.
					(gameElements.getNumberOfPlayers()<GameParameters.getNumPlayerforCompleteMarketActivation() 
							&& action.getPosition()>GameParameters.getMinMarketZones())||
					(gameElements.getNumberOfPlayers()>=GameParameters.getNumPlayerforCompleteMarketActivation() 
							&& action.getPosition()>GameParameters.getMinMarketZones()))
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
				player.notifyObservers(new Request(player.getStatus(),"Already used a coloured member in harvest", null));
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
	 
	 
	 
	 //methods created to reduce lines of codes: 
	 //1)checking the position is free; 2)checking family member' s value is big enough for performing the action
	 private boolean canMemberGoToPosition(SinglePosition position, Player player, FamilyMember familyMember, Action action) {
		 if(player.getPermanentModifiers().isGoingInOccupiedPositionsAllowed())//Ariosto Effect
			 return true;
		 
		 if(position.IsPositionOccupied()){
				player.notifyObservers(new Request(player.getStatus(),"position not free", null));
				return false;
			}
		 
		 int servants =action.getServantsUsed();
		 //handling permanent effect that doubles the servants neeeded:
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
	 //multiple position
	// 1)checking family member' s value is big enough for performing the action
	 private boolean canMemberGoToPosition(MultiplePosition position, Player player, FamilyMember familyMember, Action action){
		 int servants =action.getServantsUsed();
		 //handling permanent effect that doubles the servants neeeded:
		 if(servants!=0 && player.getPermanentModifiers().isDoubleServantsOn())
			 servants = servants/2;
		 
		 if(  (familyMember != null && familyMember.getValue() + servants+ 
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
