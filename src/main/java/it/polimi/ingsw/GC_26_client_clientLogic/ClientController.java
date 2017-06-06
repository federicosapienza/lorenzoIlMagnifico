package it.polimi.ingsw.GC_26_client_clientLogic;



import javax.swing.text.Position;

import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_client_connection.ClientConnection;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.GameStatus;
import it.polimi.ingsw.GC_26_player.PlayerStatus;

public class ClientController implements Runnable {
	private ClientConnection connection; 
	private BoardView boardView= new BoardView();
	private GameStatus gameStatus=GameStatus.INITIALAISINGGAME;
	private PlayerStatus playerStatus=PlayerStatus.WAITINGHISTURN;
	private String playerPlaying;

	public ClientController(ClientConnection connection) {
		this.connection=connection;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void receiveCard(CardDescriber card){
		if(gameStatus==GameStatus.INITIALAISINGGAME|| gameStatus==GameStatus.INITIALAISINGTURN){
			if(card.getTypeOfCard().equalsIgnoreCase("Development Card"))
				boardView.addCardWhereFree(card);
			if(card.getTypeOfCard().equalsIgnoreCase("Excommunication Tile"))
				//TODO  aggiungere carta scomunica;
				;
			if(card.getTypeOfCard().equalsIgnoreCase("LeaderCard"));
					//TODO 
			else throw new IllegalArgumentException();	
		}
		if(gameStatus== GameStatus.PLAYING){
			//TODO aggiungere carta alla personal board del player playing: ci posso mettere anche quella vaticano
			// e segnare tutte le carte con un effetto permanente attivo
		}
		if(gameStatus== GameStatus.RECONNETTINGAPLAYER);
			//TODO
	}
	
	public void receiveAction(Action action){
		if(gameStatus==GameStatus.INITIALAISINGGAME|| gameStatus==GameStatus.INITIALAISINGTURN)
			throw new IllegalStateException();
		if(gameStatus==GameStatus.PLAYING){
			boardView.addfamilyMember(action); }
			//TODO stampa action
	}
	
	public void receivePosition(PositionDescriber position){
		if(gameStatus==GameStatus.INITIALAISINGGAME)
			boardView.addPosition(new PositionView(position));
		if(gameStatus==GameStatus.PLAYING || gameStatus==GameStatus.INITIALAISINGTURN){
			//TODO lancia eccezione;
		if(gameStatus==GameStatus.RECONNETTINGAPLAYER );
			//does nothing;
			//TODO stampa action
	}
	
	

	}
}
