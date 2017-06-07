package it.polimi.ingsw.GC_26_client_clientLogic;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

//keeps status of players: points , resources, cards in personal board

public class PlayerView {
	private String name;
	private Set<CardDescriber> developmentsCardOwned;
	private Set <CardDescriber> leadersCardUsed;  // the cards the player has shown
	private Set <CardDescriber> leadersCardOwned; //not null only for the playerView representing the client.
	private PlayerWallet wallet;
	
	
	PlayerView(PlayerWallet wallet){  //the first time the client receives a playerWallet of a player, it creates playerView
		name= wallet.getPlayerName();
		this.wallet=wallet;
		developmentsCardOwned= new HashSet<>();
		leadersCardOwned= new HashSet<>();
		leadersCardUsed= new HashSet<>();

		
		
		
	}
	
	
	
	public void updateValues(PlayerWallet wallet){
		this.wallet=wallet;
	}
	
	public void addDevelopmentCard(CardDescriber card){
		developmentsCardOwned.add(card);
	}
	
	public void addLeaderCardOwned(CardDescriber card){
		leadersCardOwned.add(card);
	}
	
	public void addLeaderCardUsed(CardDescriber card){
		leadersCardUsed.add(card);
	}
	
	
	
	
	
	
}
