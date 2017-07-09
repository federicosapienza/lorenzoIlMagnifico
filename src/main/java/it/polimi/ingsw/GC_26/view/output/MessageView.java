package it.polimi.ingsw.GC_26.view.output;

import java.util.Timer;

import it.polimi.ingsw.GC_26.json_reader.TimerValuesInterface;
import it.polimi.ingsw.GC_26.messages.Message;
import it.polimi.ingsw.GC_26.messages.Request;
import it.polimi.ingsw.GC_26.model.player.PlayerStatus;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26.utilities.observer.Observer;
import it.polimi.ingsw.GC_26.view.EndTurnTask;
import it.polimi.ingsw.GC_26.view.EndTurnView;
import it.polimi.ingsw.GC_26.view.EndVaticanTurnTask;
import it.polimi.ingsw.GC_26.view.StringInputView;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the view for the Message
 */
public class MessageView extends OutputView implements Observer<Message>{
	private Timer timer;
	private EndTurnView endTurnView;
	private TimerValuesInterface times;
	private StringInputView stringInputView;

	/**
	 * Constructor: it creates a MessageView based on the ServerConnectionToClient contained in the parameters and with the 
	 * EndTurnView, TimerValuesInterface and StringInputView contained in the parameters
	 * @param connection It's the ServerConnectionToClient which this MessageView is based on
	 * @param endTurnView the EndTurnView of this MessageView
	 * @param stringInputView the StringInputView of this MessageView
	 * @param times the TimerValuesInterface of this MessageView
	 */
	public MessageView(ServerConnectionToClient connection, EndTurnView endTurnView, StringInputView stringInputView, TimerValuesInterface times) {
		super(connection);
		this.endTurnView= endTurnView;
		this.stringInputView=stringInputView;
		this.times=times;
	}

	/**
	 * Method called to send the Message contained in the parameter on the ServerConnectionToClient this MessageView is based
	 * on and to terminate the timer or create a new one to schedule, depending on the player's status contained in the Message 
	 * expressed by the parameter, which is casted as a Request
	 */
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
				timer.schedule(new EndVaticanTurnTask(stringInputView), (long) times.getVaticanReportTimer()*1000);
			}
			if((request.getStatus()==PlayerStatus.WAITINGHISTURN || request.getStatus()==PlayerStatus.SUSPENDED) && timer!=null){
				timer.cancel();
			}
		}

	
	}
	

}
