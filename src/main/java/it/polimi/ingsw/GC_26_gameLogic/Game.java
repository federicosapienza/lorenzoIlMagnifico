package it.polimi.ingsw.GC_26_gameLogic;


import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_board.Board;
import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26_personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_serverView.Observable;
import it.polimi.ingsw.GC_26_utilities.Info;
import it.polimi.ingsw.GC_26_utilities.Message;
import it.polimi.ingsw.GC_26_utilities.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;




public class Game extends Observable<CardDescriber>{
	private final it.polimi.ingsw.GC_26_readJson.Cards cards;
	private  List<Player> players;
	private int numberOfPlayers;
	private GameElements gameElements;
	private List<ResourcesOrPoints[]> resourcesOrPointsBonus;
	private final int  numberOfPeriods =GameParameters.getNumberOfPeriods(); 
	private GameStatus gameStatus=GameStatus.INITIALIZINGGAME;
	
	private List<ResourcesOrPoints> startingResources;
	private TimerValuesInterface times;
	private BonusInterface bonusInterface;
	
	private int periodNumber;
	private List<ExcommunicationTile> excommunicationTiles;
	
	private int numPeriods =GameParameters.getNumberOfPeriods();
	private int numRounds= GameParameters.getRoundsforPeriod();
	private int period;
	private int round;
	

	
	public Game(Cards cards, BonusInterface bonus, TimerValuesInterface times){
		this.cards= cards;
		this.resourcesOrPointsBonus= bonus.getListOfResourcesOfPointsArray();
		this.startingResources= bonus.getResourcesOrPointsStarting();
		this.bonusInterface =bonus;
		this.times= times;
		periodNumber=1;
		players= new ArrayList<>();
		
	}
	
	public GameElements getGameElements() {
		return gameElements;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player addPlayer(String name){
		Player player = new Player(name, startingResources.get(numberOfPlayers-1));
		players.add(player);
		numberOfPlayers++;
		return player;
	}
	
	public int getPeriod() {
		return period;
	}
	
	public synchronized void setGameStatus(GameStatus status){
		gameStatus= status;
	}
	public synchronized GameStatus getGameStatus() {
		return gameStatus;
	}
	
	public ExcommunicationTile getThisRoundExcommunicationTiles() {
		return excommunicationTiles.get(period-1);
	}
	
	
	public void initialiseGame(){
		gameElements= new GameElements(this ,players, numberOfPlayers, resourcesOrPointsBonus, times);
		
		
		//TODO notificare i giocatori
		
		
		//TODO prendere 4 carte leader per giocatore e notificarliele 
	}
	
	
	public void startGame(){
		excommunicationTiles = cards.getExcommunicationTiles();
		
		
		gameElements.notifyPlayers(new Info(GameStatus.INITIALIZINGGAME, null, "Welcome to a new game!"));
		//TODO send rules : such as timeout etc
		 //send the first info about players
		for(Player p: players){ 
			p.getWarehouse().notifyObservers(p.getWarehouse());
		}
		//gives to players  and send to clients personal bonus Tiles
		for(Player p: players){ 
			int temp =0;
			List<PersonalBoardTile> bonusTiles=bonusInterface.get4RandomBonusTiles();
			p.getPersonalBoard().setPersonalBoardTile(bonusTiles.get(temp));
			gameElements.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.INITIALIZINGGAME,p.getName(), null, null, bonusTiles.get(temp).toString()));
			temp++;
		}
		//sending positions of the board:
		gameElements.getBoard().boardSendingDescription();
		int temp =0;
		List<LeaderCard> leaderCards=cards.getRandomLeaderCards(numberOfPlayers);
		//taking and sending LeaderCard to the player
		for(Player p: players){ 
			for(int i=0; i<4; i++){
				LeaderCard leaderCard = leaderCards.get(temp);
				p.getPersonalBoard().addLeaderCard(leaderCard);
				p.getPersonalBoard().notifyObservers(leaderCard);
			}
		}
		//taking and sending excommunicationTiles
		excommunicationTiles= cards.getExcommunicationTiles();
		for(ExcommunicationTile tile : excommunicationTiles){
			notifyObservers(tile);
		}
		
		//starting game
		setGameStatus(GameStatus.INITIALIZINGTURN);
		nextStep();
	}
	
	private Player lastPlayerPerforming;
	private int playersPerformedActions=-1;  //-1 means a new round is starting
	private List<DevelopmentCard> territoryTowerCards;
	private List<DevelopmentCard> buildingTowerCards;
	private List<DevelopmentCard> characterTowerCards;
	private List<DevelopmentCard> ventureTowerCards;
	private boolean vaticanDone= false;
	
	public void nextStep() {
		if(playersPerformedActions==-1){//starting new Round
			gameElements.notifyPlayers(new Info(GameStatus.INITIALIZINGTURN, null, "starting period "+ period+ " round "+round ));;
			sendingCards();
			gameElements.notifyPlayers(new Info(GameStatus.PLAYING, null, null ));
			//than read to 202
		}
		//starting vatican Turn
		if(playersPerformedActions==numberOfPlayers){
			if(round==2 && !vaticanDone){
				vaticanReportNext();
				return;
			}
		}
		playersPerformedActions++;

		if(playersPerformedActions==numberOfPlayers){
			if(round==2&& vaticanDone){
				vaticanDone=false; //re initialaising
				gameElements.getNextROundOrder().changeNextRoundOrder(players); //as determined by council
				gameElements.getBoard().endRound();
				//is the Game finishing?
				if(period==numberOfPeriods && round==2){
					gameElements.notifyPlayers(new Info(GameStatus.ENDING, null, "calculating results "+ period+ " round "+round ));;
					chooseAWinner();  //si può fare che da view parte timeout e dopo tot libero risorse
					return; //o forse no;
					
				}
				else{
				period++;
				round=1;
				playersPerformedActions=-1;
				nextStep();//calls nextStep
				return;
				}
			}//end of if(playersPerformedActions==numberOfPlayers)
				
			if(round==1){//end round
				gameElements.getNextROundOrder().changeNextRoundOrder(players);
				gameElements.getBoard().endRound();
				round++;
				playersPerformedActions=-1;
				nextStep();//calls nextStep
				return;
			}
			}
			
		
		Player player= players.get(playersPerformedActions); //get the next player that has to perform an Action
		synchronized(player){
			if(player.getStatus()==PlayerStatus.SUSPENDED){
				gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ "misses his turn")) ;
				nextStep();
				return;
			}
			player.setStatus(new Request(PlayerStatus.PLAYING, "your turn", null));
			gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(),"Is " +player.getName()+ "turn")) ;
			return;
		}


	}

	private void sendingCards() {
		if(round==1){
			
			territoryTowerCards= cards.getRandomDevelopmentCards(numberOfPeriods, DevelopmentCardTypes.TERRITORYCARD);
			buildingTowerCards= cards.getRandomDevelopmentCards(numberOfPeriods, DevelopmentCardTypes.BUILDINGCARD);
			characterTowerCards= cards.getRandomDevelopmentCards(numberOfPeriods, DevelopmentCardTypes.CHARACTERCARD);
			ventureTowerCards= cards.getRandomDevelopmentCards(numberOfPeriods, DevelopmentCardTypes.VENTURECARD);
			
			
			//Sending cards to board
			gameElements.getBoard().getTower(BoardZone.BUILDINGTOWER).setCardsForThisPeriod(buildingTowerCards);
			gameElements.getBoard().getTower(BoardZone.CHARACTERTOWER).setCardsForThisPeriod(characterTowerCards);
			gameElements.getBoard().getTower(BoardZone.TERRITORYTOWER).setCardsForThisPeriod(territoryTowerCards);
			gameElements.getBoard().getTower(BoardZone.VENTURETOWER).setCardsForThisPeriod(ventureTowerCards);

			//sending to clients the cards for this round:
			sendCardTool(0, 4, territoryTowerCards);
			sendCardTool(0, 4, buildingTowerCards);
			sendCardTool(0, 4, characterTowerCards);
			sendCardTool(0, 4, ventureTowerCards);
			
		if(round==2){
			//sending to clients the cards for this round:
			sendCardTool(4, 4, territoryTowerCards);
			sendCardTool(4, 4, buildingTowerCards);
			sendCardTool(4, 4, characterTowerCards);
			sendCardTool(4, 4, ventureTowerCards);
		}
					
					
		}
		
	}
	
	
	private void sendCardTool(int startingPosition,int numbersToSend, List<DevelopmentCard> toSend){
		for(int i=startingPosition; i<numbersToSend; i++){
			DevelopmentCard card= toSend.get(i);
			notifyObservers(card);
		}
	}
	
	
	private void chooseAWinner(){
		//TODO
	}
	
	private int playerDoneVatican=-1;
	
	public void vaticanReportNext(){
		playerDoneVatican++;
		if(playerDoneVatican==numberOfPlayers){
			vaticanDone=true;
			playerDoneVatican=-1;
		
			nextStep();
			return;
		}	
	}
	
	
	private void VaticanLogic(Player player){
		ExcommunicationTile excommunicationTile = excommunicationTiles.get(period-1);
		
		//if the player has not enough faith points , excommunication tile effect is automatically activated.
		//Otherwise the player will be asked to choose.
		  if(player.getWarehouse().getFaithPoints()< GameParameters.getFaithPointNeeded(periodNumber)){
		    	excommunicationTile.runEffect(player);
		    	gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ "is excommunicated"));
		    	gameElements.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), excommunicationTile, 
		    								 excommunicationTile.toString(), null));
		    	vaticanReportNext();
		    	return;
		  }
		synchronized (player) {
			if(player.getStatus() == PlayerStatus.SUSPENDED){  
		    	//if player is suspended, the effect is runned.
		    	//TODO notificare i giocatori che il player salta il turno
		    	gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(),
		    			player.getName()+ "misses his turn and is excommunicated"));
		    	gameElements.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), excommunicationTile, 
						 excommunicationTile.toString(), null));
		    	
		    	excommunicationTile.runEffect(player);
		    	return;
		
		
		}
	  player.notifyObservers(new Request(PlayerStatus.VATICANREPORTDECISION, "take your choice", excommunicationTile));
		}	
	}
	
}
