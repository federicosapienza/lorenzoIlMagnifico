package it.polimi.ingsw.GC_26.model.game.game_logic;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import it.polimi.ingsw.GC_26.json_reader.BonusInterface;
import it.polimi.ingsw.GC_26.json_reader.Cards;
import it.polimi.ingsw.GC_26.json_reader.TimerValuesInterface;
import it.polimi.ingsw.GC_26.messages.Info;
import it.polimi.ingsw.GC_26.messages.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.game_components.board.BoardZone;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26.model.game.game_components.cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26.model.game.game_components.personalBoard.PersonalBoardTile;
import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.PlayerWallet;
import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;
import it.polimi.ingsw.GC_26.utilities.observer.Observable;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class that contains the logic of the game.
 *
 */


public class Game extends Observable<CardDescriber>{
	private final it.polimi.ingsw.GC_26.json_reader.Cards cards;
	private  List<Player> players;
	private int numberOfPlayers = 0;
	private GameElements gameElements;
	private List<ResourcesOrPoints[]> resourcesOrPointsBonus;
	private final int numberOfPeriods = GameParameters.getNumberOfPeriods(); 
	private GameStatus gameStatus=GameStatus.INITIALIZINGGAME;
	
	private List<ResourcesOrPoints> startingResources;
	private TimerValuesInterface times;
	private BonusInterface bonusInterface;
	private List<Player> playersNoMoreSuspended= new CopyOnWriteArrayList<>();
	
	
	private List<ExcommunicationTile> excommunicationTiles;
	
	private int period=1;
	private int round=1;

	/**
	 * Constructor: it creates a game with the characteristics expressed in the parameters
	 * @param cards are the cards of the game
	 * @param bonus It contains all the information around the starting resources, the resources and points, the faith track and the personal bonus tile
	 * @param times It contains all the information around the intervals of time for every action of each player: if the 
	 * action is not done within this interval, the player that should have done that action is suspended.
	 */
	public Game(Cards cards, BonusInterface bonus, TimerValuesInterface times){
		this.cards = cards;
		this.resourcesOrPointsBonus = bonus.getListOfResourcesOfPointsArray();
		this.startingResources = bonus.getResourcesOrPointsStarting();
		this.bonusInterface = bonus;
		this.times = times;
		period =1;
		players = new ArrayList<>();
		
	}
	
	/**
	 * Getter method that returns the elements of the current game
	 * @return the elements of the current
	 */
	public GameElements getGameElements() {
		return gameElements;
	}
	
	/**
	 * Getter method that returns the list of the players who are playing the current game
	 * @return the list of the players who are playing the current game
	 */
	public List<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Method used when a new player wants to join the current game 
	 * @param name It's the name of the player who wants to join the current game.
	 * @return the player who has just been added into the list of the players who are playing the current game
	 */
	public Player addPlayer(String name){
		numberOfPlayers++;
		if (numberOfPlayers > 4) {
			throw new IllegalArgumentException();
		}
		Player player = new Player(name, startingResources.get(numberOfPlayers-1));
		players.add(player);
		return player;
	}
	
	/**
	 * Getter method that returns the current period of the game
	 * @return the current period of the game
	 */
	public int getPeriod() {
		return period;
	}
	
	public int getRound() {
		return round;
	}
	
	/**
	 * Method used to update the status of the game whenever it changes 
	 * @param status It's the new status of the game after the change, that will become the current status of the current game 
	 */
	public synchronized void setGameStatus(GameStatus status){
		if (status == null) {
			throw new NullPointerException();
		}
		gameStatus = status;
	}
	
	/**
	 * Getter method that returns the current status of the game
	 * @return the current status of the game
	 */
	public synchronized GameStatus getGameStatus() {
		return gameStatus;
	}
	
	/**
	 * Getter method that returns the excommunication tile of the current period: it searches in the list of excommunication
	 * tiles the corresponding one to the current period and returns it.
	 * @return the excommunication tile of the current period
	 */
	public ExcommunicationTile getThisRoundExcommunicationTiles() {
		return excommunicationTiles.get(period-1);
	}
	
	/**
	 * Method that initializes the game: it creates a new set of game elements for the players contained in the list of the
	 * current players of the game.
	 */
	public void initialiseGame(){
		if (numberOfPlayers < 0 || numberOfPlayers > 4) {
			throw new IllegalArgumentException();
		}
		gameElements= new GameElements(this, players, numberOfPlayers, resourcesOrPointsBonus, bonusInterface.getFaithTrack());
		
		
		
	}
	
	/**
	 * Method used to start the game
	 */
	public void startGame(){
		/**
		 * Every player playing this game is notified that the game has just begun.
		 */
		gameElements.notifyPlayers(new Info(GameStatus.INITIALIZINGGAME, null, "Welcome to a new game!"));
		gameElements.notifyPlayers(new Info(GameStatus.INITIALIZINGGAME, null, "Number of players: "+numberOfPlayers+". Time for round: "+times.getTurnTimer()+" s"));
	
		
		if(numberOfPlayers>=GameParameters.getNumPlayersForMultipleZones())
			gameElements.notifyPlayers(new Info(GameStatus.INITIALIZINGGAME, null, "Multiple position malus: "+GameParameters.getMultiplePositionMalus()));


		/**
		 * Every player is notified about his username: he has proposed one but id a username already exists it has been changed.
		 */
		for(Player p: players){ 
			p.notifyObservers(new Request(PlayerStatus.WAITINGHISTURN, p.getName(), null));
		}
		
		
		/**
		 * Every player playing this game gets his warehouse and the observers are notified about this
		 */
		for(Player p: players){ 
			p.getWarehouse().notifyObservers(new PlayerWallet(p.getWarehouse()));
		}
		
		/**
		 * The following lines of code represent the situation of giving the personal bonus tiles to the players and sending
		 * them to the clients.
		 * If the game has been playing with basic rules, players will get the normal personal bonus tile, which is the 
		 * same for every player;
		 * if the game has been playing with advanced rules, players will get the advanced personal bonus tile, which
		 * is different for every player.
		 */

		List<PersonalBoardTile> bonusTiles=bonusInterface.get4RandomPersonalBoardTiles("advanced");
		int temp = 0;
		for(Player p: players){ 
			p.getPersonalBoard().setPersonalBoardTile(bonusTiles.get(temp));
			gameElements.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.INITIALIZINGGAME,p.getName(), null, bonusTiles.get(temp).toString()));
			temp++;
		}   
		
		/**
		 * Sending positions of the board:
		 */
		gameElements.getBoard().boardSendingDescription();
		temp =0;
		List<LeaderCard> leaderCards=cards.getRandomLeaderCards(numberOfPlayers);
		
		/**
		 * taking and sending Leader Cards to the players, and notifying the observers about the cards taken.
		 */
		for(Player p: players){ 
			for(int i=0; i<4; i++){
				LeaderCard leaderCard = leaderCards.get(temp+i);
				p.getPersonalBoard().addLeaderCard(leaderCard);
				p.getPersonalBoard().notifyObservers(new CardDescriber(leaderCard));
			}
			temp+=4;
		}
		/**
		 * Taking and sending excommunication Tiles and notifying the observers about the excommunication tiles 
		 * that are chosen
		 */
		excommunicationTiles = cards.getRandomExcommunicationTiles();
		for(ExcommunicationTile tile : excommunicationTiles){
			notifyObservers(new CardDescriber(tile));
		}

		/**
		 * Initialization completed. Now the game can really start.
		 */
		startingRound();
		nextStep();
	}
	
	/**
	 * This indicates how many players performed an action. -1 is the value that indicates the a new round is starting
	 */
	private int playersPerformedActions = -1; 
	private int turn=1;
	private List<DevelopmentCard> territoryTowerCards;
	private List<DevelopmentCard> buildingTowerCards;
	private List<DevelopmentCard> characterTowerCards;
	private List<DevelopmentCard> ventureTowerCards;
	private final static int TURNNUMBER=4; 

	
	/**
	 * Method that describes what to do next
	 */
	public void nextStep() { 
		
		playersPerformedActions++;
		
		if(playersPerformedActions== numberOfPlayers && turn!=TURNNUMBER){
			playersPerformedActions=0; 
			turn++; 
		}
		/**
		 * starting Vatican Turn
		 */
		if(turn == TURNNUMBER && playersPerformedActions==numberOfPlayers){
			if(round==1){
				/**
				 * If the first round of the current period has come to the end, the game will go on with the next round,
				 * but in the same period.
				 */
				players= gameElements.getNextRoundOrder().changeNextRoundOrder(players);
				turn=1;
				round++;
				startingRound();
				playersPerformedActions=-1;
				nextStep();
				return;
			}
			if(round==2 ){
				vaticanReportNext();
				return;
			}
		}
		
		/**
		 *Notifying of players no more suspended
		 */
		if(!playersNoMoreSuspended.isEmpty()){
			for(Player p: playersNoMoreSuspended){
				gameElements.notifyPlayers(new Info(GameStatus.PLAYING, p.getName(), p.getName()+ " is no more suspended")) ;
				playersNoMoreSuspended.remove(p);
			}
		}
		
			
		/**
		 * Getting the next player that has to perform an action
		 */
		Player player = players.get(playersPerformedActions); 
		PlayerStatus status;
		synchronized(player){
			status= player.getStatus();
		}
		/**
		 * If the player has been suspended, he'll miss his turn
		 */
		if(status == PlayerStatus.SUSPENDED){
			gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ " misses his turn"));
			nextStep();
			return;
		}
		
		/**
		 * If the player has not been suspended, his status will change to PLAYING and the other players
		 * will be notified that it's his turn
		 */
		gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(),"Is '" +player.getName()+ "' turn")) ;
		player.setStatus(new Request(PlayerStatus.PLAYING, null , null));
		return;
	}


	

	/**
	 * Method called to send cards and set the family members
	 */
	private void startingRound() {
		gameElements.notifyPlayers(new Info(GameStatus.INITIALIZINGROUND, null, "Starting period "+ period+ " round "+round ));
		
		gameElements.getBoard().endRound();
		gameElements.getDices().rollDices();
		for(Player p: players){
			p.getPersonalBoard().endRound(); //cleans the memory of the Leader Cards with an " once per round ability"
			p.getFamilyMembers().setFreeAll(); //first of all values are resetted
			p.getFamilyMembers().setValues(gameElements.getDices());
		}
		if(round==1){
			
			

			territoryTowerCards= cards.getRandomDevelopmentCards(period, DevelopmentCardTypes.TERRITORYCARD);
			buildingTowerCards= cards.getRandomDevelopmentCards(period, DevelopmentCardTypes.BUILDINGCARD);
			characterTowerCards= cards.getRandomDevelopmentCards(period, DevelopmentCardTypes.CHARACTERCARD);
			ventureTowerCards= cards.getRandomDevelopmentCards(period, DevelopmentCardTypes.VENTURECARD);
						
			/**
			 * Sending cards to board
			 */
			gameElements.getBoard().getTower(BoardZone.BUILDINGTOWER).setCardsForThisRound(buildingTowerCards.subList(0,4));
			gameElements.getBoard().getTower(BoardZone.CHARACTERTOWER).setCardsForThisRound(characterTowerCards.subList(0, 4));
			gameElements.getBoard().getTower(BoardZone.TERRITORYTOWER).setCardsForThisRound(territoryTowerCards.subList(0, 4));
			gameElements.getBoard().getTower(BoardZone.VENTURETOWER).setCardsForThisRound(ventureTowerCards.subList(0, 4));

			/**
			 * sending the cards for this round to the clients
			 */
			sendCardTool(0, 4, territoryTowerCards);
			sendCardTool(0, 4, buildingTowerCards);
			sendCardTool(0, 4, characterTowerCards);
			sendCardTool(0, 4, ventureTowerCards);
	}
		if(round==2){
			/**
			 * sending the cards for this round to the clients 
			 */
			sendCardTool(4, 4, territoryTowerCards);
			sendCardTool(4, 4, buildingTowerCards);
			sendCardTool(4, 4, characterTowerCards);
			sendCardTool(4, 4, ventureTowerCards);
			gameElements.getBoard().getTower(BoardZone.BUILDINGTOWER).setCardsForThisRound(buildingTowerCards.subList(4, buildingTowerCards.size()));
			gameElements.getBoard().getTower(BoardZone.CHARACTERTOWER).setCardsForThisRound(characterTowerCards.subList(4, characterTowerCards.size()));
			gameElements.getBoard().getTower(BoardZone.TERRITORYTOWER).setCardsForThisRound(territoryTowerCards.subList(4, territoryTowerCards.size()));
			gameElements.getBoard().getTower(BoardZone.VENTURETOWER).setCardsForThisRound(ventureTowerCards.subList(4, ventureTowerCards.size()));

		}	
	}
	
	
	private void sendCardTool(int startingPosition,int numbersToSend, List<DevelopmentCard> toSend){
		for(int i=startingPosition; i<numbersToSend+startingPosition; i++){
			DevelopmentCard card= toSend.get(i);
			notifyObservers(new CardDescriber(card));
		}
	}
	
	/** 
	 * Method called at the end of the game to determine which player has won.
	 */
	private void chooseAWinner(){
		EndGameLogic endGame= new EndGameLogic(gameElements);
		endGame.start();
	}
	
	private int playersDoneVatican=-1;
	
	public void vaticanReportNext(){
		playersDoneVatican++;

		if(playersDoneVatican==numberOfPlayers){ //else read 401
			/**
			 * If the game is ended, it has to be chosen a winner, else, the game will go on with next period.
			 */	
			if(period==numberOfPeriods){
				gameElements.notifyPlayers(new Info(GameStatus.PLAYING, null, "calculating results " ));
				chooseAWinner();  
				return; 
				
			}
			else{ 
			/**
			 * The order for the next round will be determined by the order of the family members present in the
			 * Council Palace. If there are no family members there, the order for the next round will be the same as
			 * the current one.
			 */
			gameElements.getNextRoundOrder().changeNextRoundOrder(players); 
			
			/**
			 * Round ends
			 */
			turn=1;
			period++;
			round=1;
			playersPerformedActions=-1;
			playersDoneVatican=-1;
			startingRound();
			nextStep();
			return;
			}
		}
		vaticanLogic(players.get(playersDoneVatican));
		
	}
	
	/**
	 * Method that describes the logic of the Vatican Report phase.
	 * @param player the player who has to do the Vatican Report
	 */
	private void vaticanLogic(Player player){
		ExcommunicationTile excommunicationTile = excommunicationTiles.get(period-1);
		
		/**
		 * if the player has not enough faith points, the excommunication tile effect is automatically activated.
		 * Otherwise the player will be asked to choose if he wants to support the Church, avoiding the 
		 * excommunication and getting a certain amount of Victory Points but resetting his Faith Track from
		 * the beginning, or if he prefers not to support the Church, getting the excommunication, but leaving 
		 * his Faith Track intact.
		 *  
		 */
		  if(player.getWarehouse().getFaithPoints() < GameParameters.getFaithPointNeeded(period)){
		    	excommunicationTile.runEffect(player);
		    	gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ " is excommunicated"));
		    	gameElements.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), new CardDescriber(excommunicationTile), null));
		    	vaticanReportNext();
		    	return;
		  }
		  
		  /**
		   * If the player has been suspended, he gets the excommunication automatically.
		   */
	
		  if(player.getStatus() == PlayerStatus.SUSPENDED){
			  gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ "misses his turn and is excommunicated"));
			  gameElements.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), new CardDescriber(excommunicationTile),  null));
			  excommunicationTile.runEffect(player);		    	
			  return;
			  }
		  player.setStatus(new Request(PlayerStatus.VATICANREPORTDECISION, "Do you prefer getting"
		  		+ " excommunicated and getting "+ bonusInterface.getFaithTrack().get(player.getWarehouse().getFaithPoints()) +
		  				 " victory points or not?", new CardDescriber(excommunicationTile)));
		  
	}
	
	/**
	 * Method called when a player is not suspended anymore: it adds the player to the list of players who were suspended at before 
	 * and now are not suspended anymore
	 * @param player It's the player who was suspended and now isn't suspended
	 */
	public void addPlayerNoMoreSuspended(Player player) {
		playersNoMoreSuspended.add(player);
		
	}
	
	/**
	 * Method that returns the list of players who were suspended before and now are not suspended
	 * @return the list of players who were suspended before and now are not suspended
	 */
	public List<Player> getPlayersNoMoreSuspended() {
		return playersNoMoreSuspended;
	}

	
	
}
