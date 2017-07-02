package it.polimi.ingsw.GC_26_serverView;

import java.util.Timer;

import it.polimi.ingsw.GC_26_player.PlayerStatus;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_server.Observer;
import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26_utilities.Message;
import it.polimi.ingsw.GC_26_utilities.Request;

public class MessageView extends OutputView implements Observer<Message>{
	private Timer timer;
	private EndTurnView endTurnView;
	private TimerValuesInterface times;

	public MessageView(ServerConnectionToClient connection, EndTurnView endTurnView, TimerValuesInterface times) {
		super(connection);
		this.endTurnView= endTurnView;
		this.times=times;
	}

	
	
	public void update(Message message) {
		super.getConnection().send(message);
		
		if(message instanceof Request){
			Request request= (Request) message;
			if(request.getStatus()==PlayerStatus.PLAYING && request.getMessage()==null){ //in our protocol means starting round
				timer = new Timer(true);
				timer.schedule(new EndTurnTask(endTurnView), (long) times.getTurnTimer()*1000);
			}
			if(request.getStatus()==PlayerStatus.VATICANREPORTDECISION){
				timer = new Timer(true);
				timer.schedule(new EndTurnTask(endTurnView), (long) times.getVaticanReportTimer()*1000);
			}
			if((request.getStatus()==PlayerStatus.WAITINGHISTURN || request.getStatus()==PlayerStatus.SUSPENDED) && timer!=null){
				timer.cancel();
			}
		}

	
	}
	

}
