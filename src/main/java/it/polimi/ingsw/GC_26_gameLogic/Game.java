package it.polimi.ingsw.GC_26_gameLogic;


import java.util.ArrayList;
import java.util.List;


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
import it.polimi.ingsw.GC_26_server.Observable;
import it.polimi.ingsw.GC_26_utilities.Info;
import it.polimi.ingsw.GC_26_utilities.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

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
	private final it.polimi.ingsw.GC_26_readJson.Cards cards;
	private  List<Player> players;
	private int numberOfPlayers = 0;
	private GameElements gameElements;
	private List<ResourcesOrPoints[]> resourcesOrPointsBonus;
	private final int numberOfPeriods = GameParameters.getNumberOfPeriods(); 
	private GameStatus gameStatus=GameStatus.INITIALIZINGGAME;
	
	private List<ResourcesOrPoints> startingResources;
	private TimerValuesInterface times;
	private BonusInterface bonusInterface;
	
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
	
	/**
	 * Method used to update the status of the game whenever it changes 
	 * @param status It's the new status of the game after the change, that will become the current status of the current game 
	 */
	public synchronized void setGameStatus(GameStatus status){
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
		gameElements= new GameElements(this, players, numberOfPlayers, resourcesOrPointsBonus, bonusInterface.getFaithTrack());
		
		//TODO notificare i giocatori
		
		
		//TODO prendere 4 carte leader per giocatore e notificarliele 
	}
	
	/**
	 * Method used to start the game
	 */
	public void startGame(){
		gameElements.notifyPlayers(new  Info(GameStatus.INITIALIZINGGAME, null, "Welcome to a new game!"));
		/**
		 * Every player playing this game is notified that the game has just begun.
		 */
		gameElements.notifyPlayers(new Info(GameStatus.INITIALIZINGGAME, null, "Welcome to a new game!"));
		
		

		//TODO send rules : such as timeout etc
		 //send the first info about players
		
		/**
		 * Every player playing this game gets his warehouse and the observers are notified about this
		 */
		for(Player p: players){ 
			p.getWarehouse().notifyObservers(new PlayerWallet(p.getWarehouse()));
		}
		
		/**
		 * The following lines of code represent the situation of giving the personal bonus tiles to the players and sending
		 * them to the clients.
		 * If the the game has been playing with basic rules, players will get the normal personal bonus tile, which is the 
		 * same for every player;
		 * if the game has been playing with advanced rules, players will get the advanced personal bonus tile, which
		 * is different for every player.
		 */

		List<PersonalBoardTile> bonusTiles=bonusInterface.get4RandomPersonalBoardTiles("advanced");
		int temp =0;
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
		 * Initialization completed. Now the game can start really.
		 */
		nextStep();
	}
	
	/**
	 * This indicates how many players performed an action. -1 is the value that indicates the a new round is starting
	 */
	private int playersPerformedActions = -1; 
	private List<DevelopmentCard> territoryTowerCards;
	private List<DevelopmentCard> buildingTowerCards;
	private List<DevelopmentCard> characterTowerCards;
	private List<DevelopmentCard> ventureTowerCards;
	private boolean vaticanDone= false;
	
	/**
	 * Method that describes what to do next
	 */
	public void nextStep() {
		
		/**
		 * If a new round is starting, players will be notified about it: the turn will be initialized and the game 
		 * will be really playable after sending the cards and the family members to the players.
		 */
		if(playersPerformedActions==-1){//starting new Round
			gameElements.notifyPlayers(new Info(GameStatus.INITIALIZINGTURN, null, "Starting period "+ period+ " round "+round ));
			sendingCardsAndSettingFamilyMembers();
			gameElements.notifyPlayers(new Info(GameStatus.PLAYING, null, null ));
			//then read to 202
		}
		/**
		 * starting Vatican Turn
		 */
		if(playersPerformedActions==numberOfPlayers){
			if(round==2 && !vaticanDone){
				vaticanReportNext();
				return;
			}
		}
		playersPerformedActions++;

		if(playersPerformedActions==numberOfPlayers){
			if(round==2&& vaticanDone){
				
				/**
				 * At the end of the period, vaticanDone has to be reinitialized to false
				 */
				vaticanDone=false; 
				
				/**
				 * The order for the next round will be determined by the order of the family members present in the
				 * Council Palace. If there are no family members there, the order for the next round will be the same as
				 * the current one.
				 */
				gameElements.getNextRoundOrder().changeNextRoundOrder(players); 
				
				/**
				 * Round ends
				 */
				gameElements.getBoard().endRound();
				
				/**
				 * If the game is ended, it has to be chosen a winner, else, the game will go on with next period.
				 */
				if(period==numberOfPeriods && round==2){
					gameElements.notifyPlayers(new Info(GameStatus.ENDING, null, "calculating results "+ period+ " round "+round ));;
					chooseAWinner();  //si può fare che da view parte timeout e dopo tot libero risorse
					return; //o forse no;
					
				}
				else{
				period++;
				round=1;
				playersPerformedActions=-1;
				nextStep();
				return;
				}
			}//end of if(playersPerformedActions==numberOfPlayers)
			
			/**
			 * If the first round of the current period has come to the end, the game will go on with the next round,
			 * but in the same period.
			 */
			if(round==1){
				players= gameElements.getNextRoundOrder().changeNextRoundOrder(players);
				gameElements.getBoard().endRound();
				round++;
				playersPerformedActions=-1;
				nextStep();
				return;
			}
		}
			
		/**
		 * Getting the next player that has to perform an action
		 */
		Player player = players.get(playersPerformedActions); 
		synchronized(player){
			
			/**
			 * If the player has been suspended, he'll miss his turn
			 */
			if(player.getStatus() == PlayerStatus.SUSPENDED){
				gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ "misses his turn")) ;
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


	}

	/**
	 * Method called to send cards and set the family members
	 */
	private void sendingCardsAndSettingFamilyMembers() {
		if(round==1){
			
			gameElements.getDices().rollDices();
			for(Player p: players){
				p.getFamilyMembers().setValues(gameElements.getDices());
			}

			territoryTowerCards= cards.getRandomDevelopmentCards(period, DevelopmentCardTypes.TERRITORYCARD);
			buildingTowerCards= cards.getRandomDevelopmentCards(period, DevelopmentCardTypes.BUILDINGCARD);
			characterTowerCards= cards.getRandomDevelopmentCards(period, DevelopmentCardTypes.CHARACTERCARD);
			ventureTowerCards= cards.getRandomDevelopmentCards(period, DevelopmentCardTypes.VENTURECARD);
						
			/**
			 * Sending cards to board
			 */
			gameElements.getBoard().getTower(BoardZone.BUILDINGTOWER).setCardsForThisRound(buildingTowerCards);
			gameElements.getBoard().getTower(BoardZone.CHARACTERTOWER).setCardsForThisRound(characterTowerCards);
			gameElements.getBoard().getTower(BoardZone.TERRITORYTOWER).setCardsForThisRound(territoryTowerCards);
			gameElements.getBoard().getTower(BoardZone.VENTURETOWER).setCardsForThisRound(ventureTowerCards);

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
			gameElements.getBoard().getTower(BoardZone.BUILDINGTOWER).setCardsForThisRound(buildingTowerCards);
			gameElements.getBoard().getTower(BoardZone.CHARACTERTOWER).setCardsForThisRound(characterTowerCards);
			gameElements.getBoard().getTower(BoardZone.TERRITORYTOWER).setCardsForThisRound(territoryTowerCards);
			gameElements.getBoard().getTower(BoardZone.VENTURETOWER).setCardsForThisRound(ventureTowerCards);
		}	
	}
	
	
	private void sendCardTool(int startingPosition,int numbersToSend, List<DevelopmentCard> toSend){
		for(int i=startingPosition; i<numbersToSend; i++){
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

		if(playersDoneVatican==numberOfPlayers){
			vaticanDone=true;
			playersDoneVatican=-1;
		
			nextStep();
			return;
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
		    	gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ "is excommunicated"));
		    	gameElements.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), new CardDescriber(excommunicationTile), null));
		    	vaticanReportNext();
		    	return;
		  }
		  
		  /**
		   * If the player has been suspended, he gets the excommunication automatically.
		   */
		  synchronized (player) {
			  if(player.getStatus() == PlayerStatus.SUSPENDED){
				  gameElements.notifyPlayers(new Info(GameStatus.PLAYING, player.getName(), player.getName()+ "misses his turn and is excommunicated"));
				  gameElements.notifyPlayers(new PersonalBoardChangeNotification(GameStatus.PLAYING, player.getName(), new CardDescriber(excommunicationTile),  null));
				  excommunicationTile.runEffect(player);		    	
				  return;
				  }
			  player.notifyObservers(new Request(PlayerStatus.VATICANREPORTDECISION, "take your choice", new CardDescriber(excommunicationTile)));
		  }
	}
	
}
