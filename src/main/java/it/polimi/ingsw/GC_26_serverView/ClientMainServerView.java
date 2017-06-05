package it.polimi.ingsw.GC_26_serverView;


import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_serverConnections.ClientConnection;

public class ClientMainServerView {
	private final String name;
	private PlayerWalletView playerWalletView;
	private CardDescriberView cardDescriberView;
	private StringView stringView;
	private ActionView actionView;
	private StringInputView stringInputView ;
	private ActionInputView actionInputView;
	private ClientConnection connection;
	
	
	public ClientMainServerView(String name, ClientConnection connection) {
		this.name = name;
		playerWalletView =  new PlayerWalletView();
		cardDescriberView = new CardDescriberView();
		stringView = new StringView();
		actionView = new ActionView();
		stringInputView = new StringInputView();
		actionInputView = new ActionInputView();
		this.connection= connection;
		
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


	public StringView getStringView() {
		return stringView;
	}


	public void setStringView(StringView stringView) {
		this.stringView = stringView;
	}


	public ActionView getActionView() {
		return actionView;
	}


	public void setActionView(ActionView actionView) {
		this.actionView = actionView;
	}


	public StringInputView getStringInputView() {
		return stringInputView;
	}


	public void setStringInputView(StringInputView stringInputView) {
		this.stringInputView = stringInputView;
	}


	public ActionInputView getActionInputView() {
		return actionInputView;
	}


	public void setActionInputView(ActionInputView actionInputView) {
		this.actionInputView = actionInputView;
	}


	public String getName() {
		return name;
	}



	public void addConnection(ClientConnection connection) {
		// TODO Auto-generated method stub
		
	}


	


	
}
