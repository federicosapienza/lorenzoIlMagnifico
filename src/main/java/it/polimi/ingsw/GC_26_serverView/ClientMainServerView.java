package it.polimi.ingsw.GC_26_serverView;


import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_serverConnections.ServerConnectionToClient;

public class ClientMainServerView {
	private final String name;
	private PlayerWalletView playerWalletView;
	private CardDescriberView cardDescriberView;
	private StringView stringView;
	private ActionView actionView;
	private StringInputView stringInputView ;
	private ActionInputView actionInputView;
	private ServerConnectionToClient connection;
	
	
	public ClientMainServerView(String name, ServerConnectionToClient connection) {
		this.name = name;
		playerWalletView =  new PlayerWalletView(connection);
		cardDescriberView = new CardDescriberView(connection);
		stringView = new StringView(connection);
		actionView = new ActionView(connection);
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



	public void addConnection(ServerConnectionToClient connection) {
		// TODO Auto-generated method stub
		
	}


	


	
}
