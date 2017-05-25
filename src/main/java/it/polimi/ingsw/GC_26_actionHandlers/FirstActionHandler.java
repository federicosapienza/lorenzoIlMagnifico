package it.polimi.ingsw.GC_26_actionHandlers;


import it.polimi.ingsw.GC_26_actions.Action;
import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_board.Tower;
import it.polimi.ingsw.GC_26_board.TowerPosition;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_gameLogic.GameElements;
import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMember;

// synchronization is ensured by Controllers which calls method of this function, only if the player is allowed
public class FirstActionHandler{
	private GameElements gameElements;
	
	public FirstActionHandler(GameElements gameElements){
		this.gameElements =gameElements;
	}
	

	public void startAction(Player player, Action action) {
		player.setPlayerActive();
	 // trovare un modo nel controller per verificare che l oggetto mandato sia un action
		Boolean flag = isPossible(player, action);
		// if action not possible player is notified in IsPossible and linked methods
		if(!flag)
			perform(player, action);
	}	  
		//if(flag)
			//pay()	

	
	private boolean isPossible(Player player, Action action ){
		//spostare sopra
		//if(action.getPlayer().getStatus() != PlayerStatus.PLAYING)  //TODO la spostiamo in controller
			//TODO notificare:forse va lanciata un' eccezione
		//	return false;
		
		//mettere try catch: spiegare che progrmmazione molto difensiva
		
			player.setTemporaryWarehouse();  // prepares the action
			if(!player.getTestWarehouse().areResourcesEnough(action.getServantsUsed())){
				player.notifyObservers("Not enough servants");
				return false;}
			player.getTestWarehouse().spendResources(action.getServantsUsed());
			
			//takes the family member used and checks is free
			FamilyMember familyMemberUsed = player.getFamilyMembers().getfamilyMember(action. getFamilyMemberColour());
			if(!familyMemberUsed.isFree()){
				player.notifyObservers("Family member not free");
				return false;
			}
			
			//calling the right checker
			
			try {
				//towers actions : here is also controlled that the parameter action sent is correct!
				if(action.getZone()==BoardZone.BUILDINGTOWER  || action.getZone() == BoardZone.CHARACTERTOWER || 
						action.getZone()==BoardZone.TERRITORYTOWER || action.getZone()==BoardZone.VENTURETOWER){
					if(action.getPosition()<=0 || action.getPosition()>GameParameters.getTowerFloorsNumber()) 
						throw new IllegalArgumentException();
					else return towerIsPossible(player, familyMemberUsed, action);
				}
				if(action.getZone()==BoardZone.MARKET){
					if(action.getPosition()<=0 ||  //market's number of position depends on number of players
							// with standard rules: if (numPlayers<4 && position>2)||(numPlayers=4 && position>4) throw exception.
							(gameElements.getNumberOfPlayers()<GameParameters.getNumPlayerforCompleteMarketActivation() 
									&& action.getPosition()>GameParameters.getMinMarketzone())||
							(gameElements.getNumberOfPlayers()>=GameParameters.getNumPlayerforCompleteMarketActivation() 
									&& action.getPosition()>GameParameters.getMinMarketzone()))
						throw new IllegalArgumentException();
				}
				// if action.getZone()==BoardZone.COUNCILPALACE always ok!
					
			} catch (IllegalArgumentException e) {
				player.notifyObservers("action not valid");
				e.printStackTrace();
			}
			return false; //AGGIUSTARE
			}
					
	 
		
		
	// also used in secondary action.
	 protected boolean towerIsPossible(Player player, FamilyMember familyMember, Action action){
		 //checks if the player is already on the tower
		Tower tower = gameElements.getBoard().getTower(action.getZone());
		if(tower.isThePlayerInTheTower(player)){
			player.notifyObservers(" you are already in the tower");
			return false ;
					}
		
			//if the tower is occupied pay coins(or whatever payment, if rules are changed)if possible
		if(tower.isTheTowerOccupied()){
				if (!player.getTestWarehouse().areResourcesEnough(GameParameters.getTowerOccupiedMalus())){
					player.getTestWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
					player.notifyObservers(" not enough resources for going in a occupied tower");
					return false;
				}
				player.getTestWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
		}
		//going to position and calling the Card!
		TowerPosition towerPosition = gameElements.getBoard().getTower(action.getZone()).getPosition(action.getPosition());
		DevelopmentCard card = towerPosition.getCard();
		if(!card.canPlayerGetThis(player)){
			return false;
			//the card will notify why card could not be bought: 1) not enough resources
		// 2) military points' requirements needed for getting 3,4,5,6 territory card not reached
		}
		return true;
	 }

		
/////////////////////////////////////////////////////////////////////
	 private void perform(Player player, Action action) {
			try{
				//spends the servants
				player.getWarehouse().spendResources(action.getServantsUsed());
				//takes the family member and sets it used
				FamilyMember familyMemberUsed = player.getFamilyMembers().getfamilyMember(action.getFamilyMemberColour()); 
				familyMemberUsed.setUsed();
			
			//calling the right checker
				//Towers zone
				if(action.getZone()==BoardZone.BUILDINGTOWER  || action.getZone() == BoardZone.CHARACTERTOWER || 
						action.getZone()==BoardZone.TERRITORYTOWER || action.getZone()==BoardZone.VENTURETOWER)
					towerPerform(player, familyMemberUsed, action);
				
			}
			
			 
			finally{}
			}
			
	
	 private void towerPerform(Player player, FamilyMember familyMember, Action action){
		 try {
			Tower tower = gameElements.getBoard().getTower(action.getZone());
			//sets that the player is in the Tower, passing the familyMember so that:
			//1) if the member is neutral , player is not added to the list 
			//2) if is a secondary action familyMember is null and player is not added
			tower.setPlayerInTheTower(familyMember);  
			//if the tower is occupied pay coins(or any payment if rules are changed)
			if(tower.isTheTowerOccupied()){
			player.getWarehouse().spendResources(GameParameters.getTowerOccupiedMalus());
			
			//going to position and calling the Card!
			TowerPosition towerPosition = gameElements.getBoard().getTower(action.getZone()).getPosition(action.getPosition());
			player.getWarehouse().add(towerPosition.getResourcesOrPointsinPosition());
			DevelopmentCard card = towerPosition.getCard();
			//paying the card
			player.setStatus(PlayerStatus.CHOOSINGPAYMENT);
			card.pay(player);
			while(player.getStatus()==PlayerStatus.CHOOSINGPAYMENT){ // This status reached when player is asked to choose payment
				try {
					wait();
				} catch (InterruptedException e) { 
					System.out.println(e.getMessage());
					player.setStatus(PlayerStatus.WAITINGHISTURN);  // ends player turn
					e.printStackTrace();
				}
				//mettere un notifyAll!!
				
					card.runImmediateEffect(player);
					if(card.getType() == DevelopmentCardTypes.CHARACTERCARD)// character cards' permanent effect is immediately activated
							card.runPermanentEffect(player);
						gameElements.getRankings().updateRankingPlayer(player);
				}
 }
			} catch (IllegalArgumentException e) {
				player.notifyObservers("Action not valid. retry");  // defensive catch.
				player.setStatus(PlayerStatus.WAITINGHISTURN);
				e.printStackTrace();
			}
		}
}