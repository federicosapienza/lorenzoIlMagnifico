package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public class MainClientView {
	private PlayerView thisPlayer;
	private GameStatus gameStatus=null;
	private PlayerStatus playerStatus=PlayerStatus.WAITINGHISTURN;
	private boolean loginDone=false;
	
	private String playerUsername;
	private final Map<String, PlayerView> players;
	private final BoardView board;
	
	
	
	public MainClientView() {
		players = new HashMap<>();
		board = new BoardView();
	}
	
	public void setPlayerUsername(String string){
		System.out.println(string);
		playerUsername= string;
		
	}
	
	
	public GameStatus getGameStatus() {
		synchronized (this) {
			return gameStatus;
		}
	}
	
	public synchronized PlayerStatus getPlayerStatus() {
		synchronized (playerStatus) {
			return playerStatus;
		}
	}
	
	
	public void setGameStatus(GameStatus gameStatus) {
		synchronized (this) {
			System.out.println();
			this.gameStatus = gameStatus;
		}
			
		
	}
	
	public synchronized void setPlayerStatus(PlayerStatus playerStatus) {
		synchronized (this.playerStatus) {
			this.playerStatus = playerStatus;
		}
		this.playerStatus = playerStatus;
	}
	
	public boolean isLoginDone() {
		return loginDone;
	}
	
	
	
	
	
	
	
	public String getPlayerUsername() {
		return playerUsername;
	}
	
	
	public BoardView getBoard() {
		return board;
	}
	
	public void updatePlayerWallet(PlayerWallet wallet){
		if(!players.containsKey(wallet.getPlayerName())){
			PlayerView playerView = new PlayerView(wallet);
			players.put(wallet.getPlayerName(), playerView);
			if(playerUsername.equals(wallet.getPlayerName()))
				thisPlayer= playerView;
		}
		else{
			PlayerView view = players.get(wallet.getPlayerName());
			view.updateValues(wallet);
		}
	}
	
	public PlayerView getPlayer(String playerUsername){ //calling it only if sure player has been initialised
		return players.get(playerUsername);
	}
	
	public void setLoginDone() {
		loginDone = true;
		
		
	}
	
	public PlayerView getThisPlayer() {
		return thisPlayer;
	}



}
