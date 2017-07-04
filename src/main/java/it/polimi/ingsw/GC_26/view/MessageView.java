package it.polimi.ingsw.GC_26.view;

import java.util.Timer;

import it.polimi.ingsw.GC_26.jsonReader.TimerValuesInterface;
import it.polimi.ingsw.GC_26.messages.Message;
import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26.utilities.observer.Observer;

public class MessageView extends OutputView implements Observer<Message>{
	private Timer timer;
	private EndTurnView endTurnView;
	private TimerValuesInterface times;

	public MessageView(ServerConnectionToClient connection, EndTurnView endTurnView, TimerValuesInterface times) {
		super(connection);
		this.endTurnView= endTurnView;
		this.times=times;
	}

	
	@Override
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
