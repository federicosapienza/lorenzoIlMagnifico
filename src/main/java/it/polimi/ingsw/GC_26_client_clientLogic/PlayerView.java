package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.HashSet;
import java.util.Set;

import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

//keeps status of players: points , resources, cards in personal board

public class PlayerView {
	private String name;
	private Set<CardDescriber> developmentsCardOwned;
	private Set <CardDescriber> leadersCardUsed;  // the cards the player has shown
	private Set <CardDescriber> leadersCardOwned; //not null only for the playerView representing the client.
	private PlayerWallet wallet;
	private Set<CardDescriber> excommunicationTaken;
	private String personalTileValues;
	private FamilyMembersDescriber familyMembers;
	
	PlayerView(PlayerWallet wallet){  //the first time the client receives a playerWallet of a player, it creates playerView
		name= wallet.getPlayerName();
		developmentsCardOwned= new HashSet<>();
		leadersCardOwned= new HashSet<>();
		leadersCardUsed= new HashSet<>();
		
		updateValues(wallet);
		
	}
	
	public void updateValues(PlayerWallet wallet){
		this.wallet=wallet;
		System.out.println(name+" "+wallet.getCoins()+" coins, "+wallet.getServants()+
		" servants, "+wallet.getWood()+" wood, "+wallet.getStone()+" stone.");
		//TODO dividere visione di punti e risorse
	}
	
	
	
	
	
	
	
	public void setPersonalTileValues(String string){
		personalTileValues= string;
	}
	
	public void addCard(CardDescriber card){
		if(card.getTypeOfCard().contains("Development"))
			developmentsCardOwned.add(card);
		if(card.getTypeOfCard().contains("Leader"))
			leadersCardUsed.add(card);
		if(card.getTypeOfCard().contains("Excommunication"))
			excommunicationTaken.add(card);
	}
	
	public void setFamilyMembers(FamilyMembersDescriber familyMembers) {
		this.familyMembers = familyMembers;
	}
	
	public FamilyMembersDescriber getFamilyMembers() {
		return familyMembers;
	}

	
}
