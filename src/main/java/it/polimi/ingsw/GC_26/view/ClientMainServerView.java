package it.polimi.ingsw.GC_26.view;

import it.polimi.ingsw.GC_26.jsonReader.TimerValuesInterface;
import it.polimi.ingsw.GC_26.server.connections.ServerConnectionToClient;
import it.polimi.ingsw.GC_26.view.output.ActionView;
import it.polimi.ingsw.GC_26.view.output.CardDescriberView;
import it.polimi.ingsw.GC_26.view.output.FamilyMembersView;
import it.polimi.ingsw.GC_26.view.output.MessageView;
import it.polimi.ingsw.GC_26.view.output.PlayerWalletView;
import it.polimi.ingsw.GC_26.view.output.PositionView;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the view for the ClientMainServer
 *
 */
public class ClientMainServerView {
	private String name;
	private PlayerWalletView playerWalletView;
	private CardDescriberView cardDescriberView;
	private MessageView messageView;
	private ActionView actionView;
	private StringInputView stringInputView ;
	private ActionInputView actionInputView;
	private PositionView positionView;
	private FamilyMembersView familyMembersView;
	private EndTurnView endTurnView;
	
	
	/**
	 * Constructor: it creates a ClientMainServerView with the name contained in the String parameter and based on the 
	 * ServerConnectionToClient and the TimerValuesInterface contained in the parameters
	 * @param name the name of the client
	 * @param connection the ServerConnectionToClient which this ClientMainServerView is based on
	 * @param times the TimerValuesInterface which this ClientMainServerView is based on
	 */
	public ClientMainServerView(String name, ServerConnectionToClient connection, TimerValuesInterface times) {
		if (name == null || connection == null) {
			throw new NullPointerException();
		}
		this.name = name;
		endTurnView= new EndTurnView();
		stringInputView = new StringInputView(endTurnView);
		actionInputView = new ActionInputView();
		playerWalletView =  new PlayerWalletView(connection);
		cardDescriberView = new CardDescriberView(connection);
		
		actionView = new ActionView(connection);
		
		positionView =new PositionView(connection);
		familyMembersView = new FamilyMembersView(connection);
		messageView = new MessageView( connection, endTurnView ,stringInputView,  times);
	}
	
	
	/**
	 * Method that returns the PlayerWalletView of this ClientMainServerView
	 * @return the PlayerWalletView of this ClientMainServerView
	 */
	public PlayerWalletView getPlayerWalletView() {
		return playerWalletView;
	}

	/**
	 * Method that sets the PlayerWalletView of this ClientMainServerView to the PlayerWalletView contained in the parameter
	 * @param playerWalletView the PlayerWalletView that is going to be set as the PlayerWalletView of this ClientMainServerView
	 */
	public void setPlayerWalletView(PlayerWalletView playerWalletView) {
		this.playerWalletView = playerWalletView;
	}

	/**
	 * Method that returns the CardDescriberView of this ClientMainServerView
	 * @return the CardDescriberView of this ClientMainServerView
	 */
	public CardDescriberView getCardDescriberView() {
		return cardDescriberView;
	}

	/**
	 * Method that sets the PlayerWalletView of this ClientMainServerView to the PlayerWalletView contained in the parameter
	 * @param cardDescriberView the CardDescriberView that is going to be set as the CardDescriberView of this ClientMainServerView
	 */
	public void setCardDescriberView(CardDescriberView cardDescriberView) {
		this.cardDescriberView = cardDescriberView;
	}

	/**
	 * Method that returns the MessageView of this ClientMainServerView
	 * @return the MessageView of this ClientMainServerView
	 */
	public MessageView getMessageView() {
		return messageView;
	}

	/**
	 * Method that sets the MessageView of this ClientMainServerView to the MessageView contained in the parameter
	 * @param stringView the MessageView that is going to be set as the MessageView of this ClientMainServerView
	 */
	public void setStringView(MessageView stringView) {
		this.messageView = stringView;
	}

	/**
	 * Method that returns the ActionView of this ClientMainServerView
	 * @return the ActionView of this ClientMainServerView
	 */
	public ActionView getActionView() {
		return actionView;
	}

	/**
	 * Method that sets the ActionView of this ClientMainServerView to the ActionView contained in the parameter
	 * @param actionView the ActionView that is going to be set as the ActionView of this ClientMainServerView
	 */
	public void setActionView(ActionView actionView) {
		if (actionView == null) {
			throw new NullPointerException();
		}
		this.actionView = actionView;
	}

	/**
	 * Method that returns the StringInputView of this ClientMainServerView
	 * @return the StringInputView of this ClientMainServerView
	 */
	public StringInputView getStringInputView() {
		return stringInputView;
	}

	/**
	 * Method that sets the StringInputView of this ClientMainServerView to the StringInputView contained in the parameter
	 * @param stringInputView the StringInputView that is going to be set as the StringInputView of this ClientMainServerView
	 */
	public void setStringInputView(StringInputView stringInputView) {
		if (stringInputView == null) {
			throw new NullPointerException();
		}
		this.stringInputView = stringInputView;
	}

	/**
	 * Method that returns the ActionInputView of this ClientMainServerView
	 * @return the ActionInputView of this ClientMainServerView
	 */
	public ActionInputView getActionInputView() {
		return actionInputView;
	}

	/**
	 * Method that sets the ActionInputView of this ClientMainServerView to the ActionInputView contained in the parameter
	 * @param actionInputView the ActionInputView that is going to be set as the ActionInputView of this ClientMainServerView
	 */
	public void setActionInputView(ActionInputView actionInputView) {
		if (actionInputView == null) {
			throw new NullPointerException();
		}
		this.actionInputView = actionInputView;
	}

	/**
	 * Method that returns the name of this ClientMainServerView
	 * @return the name of this ClientMainServerView
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method that returns the PositionView of this ClientMainServerView
	 * @return the PositionView of this ClientMainServerView
	 */
	public PositionView getPositionView() {
		return positionView;
	}

	/**
	 * Method that returns the FamilyMembersView of this ClientMainServerView
	 * @return the FamilyMembersView of this ClientMainServerView
	 */
	public FamilyMembersView getFamilyMembersView() {
		return familyMembersView;
	}

	/**
	 * Method that returns the EndTurnView of this ClientMainServerView
	 * @return the EndTurnView of this ClientMainServerView
	 */
	public EndTurnView getEndTurnView() {
		return endTurnView;
	}
	
	/**
	 * Method that sets the name of this ClientMainServerView to the String contained in the parameter
	 * @param name the String that is going to be set as the name of this ClientMainServerView
	 */
	public void setName(String name) {
		this.name = name;
	}
}
