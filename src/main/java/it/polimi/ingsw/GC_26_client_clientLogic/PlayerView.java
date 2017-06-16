package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
	private List<String> permamentsEffect= new ArrayList<>();
	
	PlayerView(PlayerWallet wallet){  //the first time the client receives a playerWallet of a player, it creates playerView
		name= wallet.getPlayerName();
		leadersCardOwned= new HashSet<>();
		leadersCardUsed= new HashSet<>();
		
		this.wallet=wallet;
		
	}
	
	public void updateValues(PlayerWallet wallet){
		if(!this.wallet.equals(wallet))
		this.wallet=wallet;
		
	}
	
	
	
	
	
	
	
	public void setPersonalTileValues(String string){
		personalTileValues= string;
	}
	
	public void addLeaderCardOwned(CardDescriber card){ //called only at the beginning of the game
		leadersCardOwned.add(card);
	}
	
	public void addCard(CardDescriber card){
		if(card.getTypeOfCard().contains("Development")){
			Set<CardDescriber> temp= getCurrentCards(card.getType());
			temp.add(card);
		}
		if(card.getTypeOfCard().contains("Leader"))
			leadersCardOwned.add(card);
		if(card.getPermanentEffectDescriber()!=null )
			permamentsEffect.add(card.getPermanentEffectDescriber());
			
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
	

	
	public Set<CardDescriber> getCurrentCards(DevelopmentCardTypes type){
		switch(type){
		case TERRITORYCARD:
			return  territoryCardSet;
		case BUILDINGCARD:
			return buildingCardSet;
		case CHARACTERCARD:
			return characterCardSet;
		case VENTURECARD:
			return ventureCardSet;
		default: 
			throw new IllegalArgumentException();
		}
	}
	
	public Set<CardDescriber> getLeadersCardOwned() {
		return leadersCardOwned;
	}
}
