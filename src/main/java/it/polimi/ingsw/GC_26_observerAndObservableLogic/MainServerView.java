package it.polimi.ingsw.GC_26_observerAndObservableLogic;


public class MainServerView {
	
	String name;
	PlayerWalletView playerWalletView;
	CardDescriberView cardDescriberView;
	StringView stringView;
	
	
	public MainServerView(String name) {
		this.name = name;
		playerWalletView =  new PlayerWalletView();
		cardDescriberView = new CardDescriberView();
		stringView = new StringView();
	}


	public String getName() {
		return name;
	}

	public PlayerWalletView getPlayerWalletView() {
		return playerWalletView;
	}


	public CardDescriberView getCardDescriberView() {
		return cardDescriberView;
	}


	public StringView getStringView() {
		return stringView;
	}



	
}
