package it.polimi.ingsw.GC_26_client;



import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_client_clientLogic.MainClientView;
import it.polimi.ingsw.GC_26_client_clientLogic.PositionView;
import it.polimi.ingsw.GC_26_client_connection.ClientConnection;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.ActionNotification;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class ClientController {
	private String playerPlaying;
	private MainClientView view;
	

	

	
	
	public void receiveCard(CardDescriber card){
		if(view.getGameStatus()==GameStatus.INITIALAISINGGAME||view.getGameStatus()==GameStatus.INITIALAISINGTURN){
			if(card.getTypeOfCard().equalsIgnoreCase("Development Card"))
				view.getBoard().addCardWhereFree(card);
			if(card.getTypeOfCard().equalsIgnoreCase("Excommunication Tile"))
				//TODO  aggiungere carta scomunica;
				;
			if(card.getTypeOfCard().equalsIgnoreCase("LeaderCard"));
					//TODO 
			else throw new IllegalArgumentException();	
		}
		if(view.getGameStatus()== GameStatus.PLAYING){
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
	}
	
	public void receiveAction(ActionNotification action){
		if(view.getGameStatus()==GameStatus.INITIALAISINGGAME||view.getGameStatus()==GameStatus.INITIALAISINGTURN)
			throw new IllegalStateException();
		if(view.getGameStatus()==GameStatus.PLAYING){
			view.getBoard().addfamilyMember(action); 
			System.out.println(action.toString());//TODO migliorare
		}
			
	}
	
	public void receivePosition(PositionDescriber position){
		if(view.getGameStatus()==GameStatus.INITIALAISINGGAME)
			view.getBoard().addPosition(new PositionView(position));
		if(view.getGameStatus()==GameStatus.PLAYING || view.getGameStatus()==GameStatus.INITIALAISINGTURN){}
			//TODO lancia eccezione;
		if(view.getGameStatus()==GameStatus.RECONNETTINGAPLAYER );
			//just ignores themessages;
		
	}
	
	
	
	
	public void receiveString(String string){
		if(string.contains("playerStatus"))
			changePlayerStatus(string);
		}	
			//cerca in quale playerStatus siamo
	private void changePlayerStatus(String string){
			if(string.contains(PlayerStatus.WAITINGHISTURN.toString()))
				view.setPlayerStatus(PlayerStatus.WAITINGHISTURN);
			if(string.contains(PlayerStatus.PLAYING.toString()))
				view.setPlayerStatus(PlayerStatus.PLAYING);
			if(string.contains(PlayerStatus.SECONDPLAY.toString()))
				view.setPlayerStatus(PlayerStatus.SECONDPLAY);
			if(string.contains(PlayerStatus.ACTIONPERFORMED.toString()))
				view.setPlayerStatus(PlayerStatus.ACTIONPERFORMED);
			if(string.contains(PlayerStatus.CHOOSINGPAYMENT.toString()))
				view.setPlayerStatus(PlayerStatus.CHOOSINGPAYMENT);
			if(string.contains(PlayerStatus.TRADING.toString()))
				view.setPlayerStatus(PlayerStatus.TRADING);
			if(string.contains(PlayerStatus.VATICANREPORTDECISION.toString()))
				view.setPlayerStatus(PlayerStatus.VATICANREPORTDECISION);
			if(string.contains(PlayerStatus.SUSPENDED.toString()))
				view.setPlayerStatus(PlayerStatus.SUSPENDED);
	}

	public void setLoginDone() {
		// TODO Auto-generated method stub
		
	}

}
