package it.polimi.ingsw.GC_26.client.view;

import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_26.model.game.game_components.resourcesAndPoints.PlayerWallet;
import it.polimi.ingsw.GC_26.model.game.game_logic.GameStatus;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the Main Client View
 *
 */
public class MainClientView {
	private PlayerView thisPlayer;
	private GameStatus gameStatus=null;
	private PlayerStatus playerStatus=PlayerStatus.WAITINGHISTURN;
	private boolean loginDone=false;
	
	private String playerUsername;
	private final Map<String, PlayerView> players;
	private final BoardView board;
	
	
	/**
	 * Constructor: it creates a map for the players and a BoardView for the Board
	 */
	public MainClientView() {
		players = new HashMap<>();
		board = new BoardView();
	}
	
	/**
	 * Method that sets the player's username to the string contained in the parameter
	 * @param string It's the player's username
	 */
	public void setPlayerUsername(String string){
		playerUsername= string;

		}
	
	
	/**
	 * Method that returns the gameStatus
	 * @return the gameStatus
	 */
	public GameStatus getGameStatus() {
		synchronized (this) {
			return gameStatus;
		}
	}
	
	/**
	 * Method that returns the player's status
	 * @return the player's status
	 */
	public synchronized PlayerStatus getPlayerStatus() {
		synchronized (playerStatus) {
			return playerStatus;
		}
	}
	
	/**
	 * Method that sets the status of the game to the GameStatus contained in the parameter 
	 * @param gameStatus It's the status that has to be set as the current status of this game
	 */
	public void setGameStatus(GameStatus gameStatus) {
		synchronized (this) {
			this.gameStatus = gameStatus;
		}
			
		
	}
	
	/**
	 * Method that sets the status of the player to the PlayerStatus contained in the parameter 
	 * @param playerStatus It's the status that has to be set as the current status of the player
	 */
	public synchronized void setPlayerStatus(PlayerStatus playerStatus) {
		synchronized (this) {
			this.playerStatus = playerStatus;
		}
		this.playerStatus = playerStatus;
	}
	
	/**
	 * Method that checks if the login has been done successfully
	 * @return true if the login has been done successfully; false if it hasn't
	 */
	public boolean isLoginDone() {
		return loginDone;
	}
	
	/**
	 * Method that returns the player's username
	 * @return the player's username
	 */
	public String getPlayerUsername() {
		return playerUsername;
	}
	
	/**
	 * Method that returns the BoardView
	 * @return the BoardView
	 */
	public BoardView getBoard() {
		return board;
	}

	/**
	 * Method that updates the wallet of the player who owns the PlayerWallet contained in the parameter if his name
	 * is contained in the Map of players; else it adds a new PlayerView with the characteristics of the PlayerWallet contained
	 * in the parameter to the Map of players and sets the value of thisPlayer to the new PlayerView if the username of
	 * the owner of the PlayerWallet is the same of the playerUsername
	 * @param wallet It's the PlayerWallet whose PlayerName has to be checked
	 */
	public void updatePlayerWallet(PlayerWallet wallet){
		if(!players.containsKey(wallet.getPlayerName())){
			PlayerView playerView = new PlayerView(wallet);
			players.put(wallet.getPlayerName(), playerView);
			if(playerUsername.equals(wallet.getPlayerName()))
				thisPlayer = playerView;
		}
		else{
			PlayerView view = players.get(wallet.getPlayerName());
			view.updateValues(wallet);
		}
	}
	
	/**
	 * Method that returns the PlayerView that matches correctly with the username contained in the parameter.
	 * It has to be called only if the player has been initialized
	 * @param playerUsername It's the username to check in order to return the corresponding PlayerView
	 * @return the PlayerView that matches correctly with the username contained in the parameter
	 */
	public PlayerView getPlayer(String playerUsername){
		return players.get(playerUsername);
	}
	
	/**
	 * Method to call when the login is done successfully: it sets the boolean loginDone to true 
	 */
	public void setLoginDone() {
		loginDone = true;
	}
	
	/**
	 * Method that returns the PlayerView of this player
	 * @return the PlayerView of this player
	 */
	public PlayerView getThisPlayer() {
		return thisPlayer;
	}

	//private final Map<String, PlayerView> players;
	
	public Map<String,PlayerView> getPlayers(){
		return players;
	}
	
	
}
