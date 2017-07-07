package it.polimi.ingsw.GC_26.client.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.messages.describers.FamilyMembersDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints.PlayerWallet;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the Player View. It keeps the status of the players, in terms of points, resources and cards
 * in their Personal Boards
 *
 */


public class PlayerView {
	private String name;
	private Set<CardDescriber> territoryCardSet =new HashSet<>();
	private Set<CardDescriber> characterCardSet=new HashSet<>();
	private Set<CardDescriber> buildingCardSet=new HashSet<>();
	private Set<CardDescriber> ventureCardSet=new HashSet<>();


	private List <CardDescriber> leadersCardUsed;  // the cards the player has shown
	private List <CardDescriber> leadersCardOwned; //not null only for the playerView representing the client.
	private PlayerWallet wallet;
	private String personalTileValues;
	private FamilyMembersDescriber familyMembers;
	private List<String> permamentsEffect= new ArrayList<>();
	
	/**
	 * Constructor: the first time the client receives a PlayerWallet of a player, a PlayerView with the characteristics of 
	 * the PlayerWallet contained in the parameter is created
	 * @param wallet It's the PlayerWallet which defines the characteristics of the PlayerView
	 */
	public PlayerView(PlayerWallet wallet){  
		name = wallet.getPlayerName();
		leadersCardOwned= new ArrayList<>();
		leadersCardUsed= new ArrayList<>();
		
		this.wallet=wallet;
		
	}
	
	/**
	 * Method that updates the values of the PlayerView with the ones of the PlayerWallet contained in the parameter,
	 * if it isn't equal to the current PlayerWallet 
	 * @param wallet It's PlayerWallet whose values will update the PlayerView
	 */
	public void updateValues(PlayerWallet wallet){
		if(!this.wallet.equals(wallet))
			this.wallet=wallet;
	}
	
	/**
	 * Method that sets personalTileValues to the string contained in the parameter
	 * @param string It's the string to assign to personalTileValues
	 */
	public void setPersonalTileValues(String string){
		personalTileValues = string;
	}

	/**
	 * Method called exclusively at the beginning of the game to add LeaderCard to the player
	 * @param card It's the CardDescriber to add to the Set of Leader Cards owned by the player
	 */
	public void addLeaderCardOwned(CardDescriber card){
		leadersCardOwned.add(card);

	}
	
	/**
	 * Method that adds the card contained in the parameter to the Set of Cards owned (if the CardDescriber describes
	 * a Development Card) or used (if the CardDescriber describes a Leader Card) by the player
	 * @param card It's the CardDescriber to add
	 */
	public void addCard(CardDescriber card){
		if(card.getTypeOfCard().contains("Development")){
			Set<CardDescriber> temp= getCurrentCards(card.getType());
			temp.add(card);
			if(card.getPermanentEffectDescriber()!=null && card.getType()==DevelopmentCardTypes.CHARACTERCARD)
				permamentsEffect.add(card.getPermanentEffectDescriber());
			return;
		}
		if(card.getTypeOfCard().contains("Leader"))
			leadersCardUsed.add(card);
		if(card.getPermanentEffectDescriber()!=null ) //if is a leader card , or a excommunication Tile 
			permamentsEffect.add(card.getPermanentEffectDescriber());
			
	}
	
	/**
	 * Method that sets the current FamilyMembersDescriber to the one contained in the parameter
	 * @param familyMembers It's the FamilyMembersDescriber to assign to familyMembers 
	 */
	public void setFamilyMembers(FamilyMembersDescriber familyMembers) {
		this.familyMembers = familyMembers;
	}
	
	/**
	 * Method that returns the current value of FamilyMembersDescriber, i.e. familyMembers
	 * @return the current value of FamilyMembersDescriber, i.e. familyMembers
	 */
	public FamilyMembersDescriber getFamilyMembers() {
		return familyMembers;
	}

	/**
	 * Method that returns the current PlayerWallet
	 * @return the current PlayerWallet
	 */
	public PlayerWallet getWallet() {
		return wallet;
	}
	
	/**
	 * Method that returns the name of the player
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method that returns the list of Permanent Effects applied on the player
	 * @return the list of Permanent Effects applied on the player
	 */
	public List<String> getPermamentsEffect() {
		return permamentsEffect;
	}
	
	/**
	 * Method that returns the current Set of cards that match correctly with the type of card contained in the parameter
	 * @param type It's the type of Development Card to check to find the corresponding Set of cards
	 * @return the current Set of cards that match correctly with the type of card contained in the parameter
	 */
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
	
	/**
	 * Method that returns the Set of CardDescriber of Leader Cards actually owned by the player
	 * @return the Set of CardDescriber of Leader Cards actually owned by the player
	 */
	public List<CardDescriber> getLeadersCardOwned() {
		return leadersCardOwned;
	}
	
	/**
	 * Method that returns the Personal Tile Values as a string
	 * @return the Personal Tile Values as a string
	 */
	public String getPersonalTileValues() {
		return personalTileValues;
	}
	
	/**
	 * Method that returns the Set of CardDescriber of Leader Cards used at least once by the player
	 * @return the Set of CardDescriber of Leader Cards used by the player
	 */
	public List<CardDescriber> getLeaderCardUsed() {
		return leadersCardUsed;
	}
	
}
