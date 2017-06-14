package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.PlayerWallet;

//keeps status of players: points , resources, cards in personal board

public class PlayerView {
	private String name;
	private Set<CardDescriber> territoryCardSet =new HashSet<>();
	private Set<CardDescriber> characterCardSet=new HashSet<>();
	private Set<CardDescriber> buildingCardSet=new HashSet<>();
	private Set<CardDescriber> ventureCardSet=new HashSet<>();


	private Set <CardDescriber> leadersCardUsed;  // the cards the player has shown
	private Set <CardDescriber> leadersCardOwned;//not null only for the playerView representing the client.
	private PlayerWallet wallet;
	private String personalTileValues;
	private FamilyMembersDescriber familyMembers;
	
	PlayerView(PlayerWallet wallet){  //the first time the client receives a playerWallet of a player, it creates playerView
		name= wallet.getPlayerName();
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
		if(card.getTypeOfCard().contains("Development")){
			Set<CardDescriber> temp= getCurrentCards(card.getType());
			temp.add(card);
		}
		if(card.getTypeOfCard().contains("Leader"))
			leadersCardUsed.add(card);
		
	}
	
	public void setFamilyMembers(FamilyMembersDescriber familyMembers) {
		this.familyMembers = familyMembers;
	}
	
	public FamilyMembersDescriber getFamilyMembers() {
		return familyMembers;
	}

	public PlayerWallet getWallet() {
		return wallet;
	}
	public String getName() {
		return name;
	}
	
	public void add(CardDescriber card){
		switch(card.getType()){
		case TERRITORYCARD: 
			territoryCardSet.add(card);
		case BUILDINGCARD: 
			buildingCardSet.add(card);
		case CHARACTERCARD:
			characterCardSet.add(card);
		case VENTURECARD:
			ventureCardSet.add(card);
		default:
			throw new IllegalArgumentException();
		}
	}
	
	public Set<CardDescriber> getCurrentCards(DevelopmentCardTypes type){
		switch(type){
		case TERRITORYCARD:
			return  Collections.unmodifiableSet(territoryCardSet);
		case BUILDINGCARD:
			return Collections.unmodifiableSet(buildingCardSet);
		case CHARACTERCARD:
			return Collections.unmodifiableSet(characterCardSet);
		case VENTURECARD:
			return Collections.unmodifiableSet(ventureCardSet);
		default: 
			throw new IllegalArgumentException();
		}
	}
	
	
}
