package it.polimi.ingsw.GC_26_client;




import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_client_clientLogic.IOlogic;
import it.polimi.ingsw.GC_26_client_clientLogic.MainClientView;
import it.polimi.ingsw.GC_26_client_clientLogic.PositionView;
import it.polimi.ingsw.GC_26_gameLogic.ActionNotification;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_utilities.Info;
import it.polimi.ingsw.GC_26_utilities.Message;
import it.polimi.ingsw.GC_26_utilities.PersonalBoardChangeNotification;
import it.polimi.ingsw.GC_26_utilities.Request;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

public class ClientController {
	private String playerPlaying;
	private MainClientView view;
	private IOlogic iOlogic;
	

	public ClientController(IOlogic iOlogic, MainClientView view) {
		this.iOlogic= iOlogic;
		this.view= view;
	}
	
	public void setPlayerPlaying(String playerPlaying) {
		this.playerPlaying = playerPlaying;
	}

	
	
	public void receiveCard(CardDescriber card){
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME || view.getGameStatus()==GameStatus.INITIALIZINGTURN){
			if(card.getTypeOfCard().equalsIgnoreCase("Development Card"))
				view.getBoard().addCardWhereFree(card);
			if(card.getTypeOfCard().equalsIgnoreCase("Excommunication Tile"))
				view.getBoard().addExcommunication(card);
			
			//TODO tutti i check
		
			if(card.getTypeOfCard().equalsIgnoreCase("LeaderCard")){
					view.getPlayer(view.getPlayerUsername()).addCard(card);; }
			else throw new IllegalArgumentException();	
		}
		/*dovrebbe essere diventato inutile
		 * 
		 * if(view.getGameStatus()== GameStatus.PLAYING){
			if(card.getTypeOfCard().equalsIgnoreCase("Development Card"))
				view.getPlayer(playerPlaying).addDevelopmentCard(card);;
			if(card.getTypeOfCard().equalsIgnoreCase("Excommunication Tile"));
				//TODO LAnciare eccezione 
				 
			
			if(card.getTypeOfCard().equalsIgnoreCase("LeaderCard"))
				view.getPlayer(playerPlaying).addLeaderCardUsed(card);

			else throw new IllegalArgumentException();	
		}
		if(view.getGameStatus()== GameStatus.RECONNETTINGAPLAYER);
			//TODO
			*/
	}
	
	public void receiveAction(ActionNotification action){
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME||view.getGameStatus()==GameStatus.INITIALIZINGTURN)
			throw new IllegalStateException();
		if(view.getGameStatus()==GameStatus.PLAYING){
			view.getBoard().addfamilyMember(action); 
			System.out.println(action.toString());//TODO migliorare
			view.getBoard().printBoard();
		}
			
	}
	
	public void receivePosition(PositionDescriber position){
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME)
			view.getBoard().addPosition(new PositionView(position));
		if(view.getGameStatus()==GameStatus.PLAYING || view.getGameStatus()==GameStatus.INITIALIZINGTURN){}
			//TODO lancia eccezione;
		if(view.getGameStatus()==GameStatus.RECONNETTINGAPLAYER );
			//just ignores themessages;
		
	}
	
	public void receivePlayerPocket(PlayerWallet playerWallet){
		view.getPlayer(playerWallet.getPlayerName()).updateValues(playerWallet);
	}
	
	
	
	
	public void receiveMessage(Message message){
		if(message instanceof Request ){
			Request request = (Request) message;
			handleRequests(request);
		}
		if(message instanceof Info ){
			Info info = (Info) message;
			handleInfo(info);
		}
		if(message instanceof PersonalBoardChangeNotification){
			PersonalBoardChangeNotification change= (PersonalBoardChangeNotification) message;
			handlePersonalBoardChangeNotification(change);
		}
	}

	



	



	public void setLoginDone() {
		view.setLoginDone();
		
	}
	
	private void handleRequests(Request request){
		view.setPlayerStatus(request.getStatus());
		System.out.println(request.getMessage());
		if(request.getStatus()==PlayerStatus.PLAYING)
				iOlogic.setWaitingFirstAction();
		if(request.getStatus()== PlayerStatus.SECONDPLAY)
				iOlogic.setWaitingSecondAction();
		if(request.getStatus()==PlayerStatus.SUSPENDED){
			//TODO dovrà chiedere reinserimento
			return;
		}
			
		if(request.getStatus()==PlayerStatus.WAITINGHISTURN)
			return;
		else {
			iOlogic.setWaitingResponse();
		}
		
	}
	
	private void handleInfo(Info info) {
		if(info.getMessage()!=null){
			System.out.println(info.getMessage());
		}
		GameStatus old= GameStatus.INITIALIZINGTURN;
		view.setGameStatus(info.getGameStatus());
		if(old== GameStatus.INITIALIZINGTURN && info.getGameStatus()==GameStatus.PLAYING)
			view.getBoard().printBoard();
		System.out.println("CLIEnt controller exit 154");
	}
	
	private void handlePersonalBoardChangeNotification(PersonalBoardChangeNotification change) {
		if(view.getGameStatus() == GameStatus.RECONNETTINGAPLAYER)
			return;
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME){
			if(change.getBoardTileValues()!=null)
				view.getPlayer(change.getPlayerName()).setPersonalTileValues(change.getBoardTileValues());
		}
		if(view.getGameStatus()==GameStatus.INITIALIZINGGAME){
			if(change.getBoardTileValues()!=null)
				view.getPlayer(change.getPlayerName());
		}		
	}
	

}
