package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;
import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;

public class ClientMainServerView {
	private final String name;
	private PlayerWalletView playerWalletView;
	private CardDescriberView cardDescriberView;
	private MessageView messageView;
	private ActionView actionView;
	private StringInputView stringInputView ;
	private ActionInputView actionInputView;
	private PositionView positionView;
	private FamilyMembersView familyMembersView;
	private EndTurnView endTurnView;
	
	
	
	public ClientMainServerView(String name, ServerConnectionToClient connection, TimerValuesInterface times) {
		if (name == null || connection == null) {
			throw new NullPointerException();
		}
		this.name = name;
		playerWalletView =  new PlayerWalletView(connection);
		cardDescriberView = new CardDescriberView(connection);
		endTurnView= new EndTurnView();
		messageView = new MessageView(connection, endTurnView , times);
		actionView = new ActionView(connection);
		stringInputView = new StringInputView(endTurnView);
		actionInputView = new ActionInputView();
		positionView =new PositionView(connection);
		familyMembersView = new FamilyMembersView(connection);
	}
	
	
	
	public PlayerWalletView getPlayerWalletView() {
		return playerWalletView;
	}


	public void setPlayerWalletView(PlayerWalletView playerWalletView) {
		this.playerWalletView = playerWalletView;
	}


	public CardDescriberView getCardDescriberView() {
		return cardDescriberView;
	}


	public void setCardDescriberView(CardDescriberView cardDescriberView) {
		this.cardDescriberView = cardDescriberView;
	}


	public MessageView getMessageView() {
		return messageView;
	}


	public void setStringView(MessageView stringView) {
		this.messageView = stringView;
	}


	public ActionView getActionView() {
		return actionView;
	}


	public void setActionView(ActionView actionView) {
		if (actionView == null) {
			throw new NullPointerException();
		}
		this.actionView = actionView;
	}


	public StringInputView getStringInputView() {
		return stringInputView;
	}


	public void setStringInputView(StringInputView stringInputView) {
		if (stringInputView == null) {
			throw new NullPointerException();
		}
		this.stringInputView = stringInputView;
	}


	public ActionInputView getActionInputView() {
		return actionInputView;
	}


	public void setActionInputView(ActionInputView actionInputView) {
		if (actionInputView == null) {
			throw new NullPointerException();
		}
		this.actionInputView = actionInputView;
	}


	public String getName() {
		return name;
	}
	
	
	public PositionView getPositionView() {
		return positionView;
	}

	
	public FamilyMembersView getFamilyMembersView() {
		return familyMembersView;
	}


	public EndTurnView getEndTurnView() {
		return endTurnView;
	}
	
	
}
