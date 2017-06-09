package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public class MainClientView {
	private GameStatus gameStatus=null;
	private PlayerStatus playerStatus=PlayerStatus.WAITINGHISTURN;
	private boolean loginDone=false;
	
	private  String playerUsername;
	private final Map< String, PlayerView> players;
	private final BoardView board;
	private  IOlogic iOlogic;
	
	
	
	
	public MainClientView() {
		players = new HashMap<>();
		board =new BoardView();
	}
	
	public void setPlayerUsername(String string){
		playerUsername= string;
	}
	
	
	public GameStatus getGameStatus() {
		synchronized (gameStatus) {
			return gameStatus;
		}
	}
	
	public PlayerStatus getPlayerStatus() {
		synchronized (playerStatus) {
			return playerStatus;
		}
	}
	
	
	public void setGameStatus(GameStatus gameStatus) {
		synchronized (this.gameStatus) {
			this.gameStatus = gameStatus;
		}
	}
	
	public void setPlayerStatus(PlayerStatus playerStatus) {
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
		}
		else{
			PlayerView view = players.get(wallet.getPlayerName());
			view.updateValues(wallet);
		}
	}
	
	public PlayerView getPlayer(String playerUsername){
		return players.get(playerUsername);
	}
	
	public void setLoginDone() {
		loginDone = true;
		
		
	}


}
