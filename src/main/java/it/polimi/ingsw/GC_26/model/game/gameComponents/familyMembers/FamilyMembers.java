package it.polimi.ingsw.GC_26.model.game.gameComponents.familyMembers;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.GC_26.model.game.gameComponents.dices.*;
import it.polimi.ingsw.GC_26.model.game.gameLogic.GameParameters;
import it.polimi.ingsw.GC_26.model.player.Player;
import it.polimi.ingsw.GC_26.server.Observable;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This is the class that represents the set of family members that every player owns.
 * Every set of family members includes:
 * an orange family member, a white family member, a black family member and a neutral family member.
 * The values of the coloured family members correspond with the value of the dices of the same colour.
 * 
 */
public class FamilyMembers extends Observable<FamilyMembersDescriber> {
	
	/**
	 * 
	 */

	//it's the orange family member.
	private FamilyMember orangeMember;
	
	//it's the white family member.
	private FamilyMember whiteMember;
	
	//it's the black family member.
	private FamilyMember blackMember;
	
	//it's the neutral family member.
	private FamilyMember neutralMember;
	
	//it's the player that owns these family members.
	private Player player;
	
	//saves the values of the lastDices passed to setvalues(); useful for changeValues();
	private Dices lastDices;
	
	
	/**
	 * Constructor that creates the set of family members of a player.
	 * @param player It's the player that owns these family members.
	 */
	public FamilyMembers(Player player) {
		if (player == null) {
			throw new NullPointerException("Attention: player is null");
		}
		this.player= player;
		
		orangeMember = new FamilyMember(Colour.ORANGE, player);
		
		whiteMember = new FamilyMember(Colour.WHITE, player);
		
		blackMember = new FamilyMember(Colour.BLACK, player);
		
		neutralMember = new FamilyMember(Colour.NEUTRAL, player);
		
	}
	
	/**
	 * Method that returns the player who owns these family members.
	 * @return player It's the player who owns these family members.
	 */
	public Player getPlayer() {
		return player;
	}
	
	public String getPlayerName(){
		return player.getName();
	}
	
	/**
	 * Method that returns the family member for the desired colour.
	 * @param colour It's the desired colour of the family member to get.
	 * @return blackMember if the desired colour is black.
	 * @return whiteMember if the desired colour is white.
	 * @return orangeMember if the desired colour is orange.
	 * @return neutralMember if the desired colour is neutral.
	 */
	public FamilyMember getfamilyMember(Colour colour){
		if (colour == Colour.BLACK) {
			return blackMember;
		}
		else if (colour == Colour.WHITE) {
			return whiteMember;
		}
		else if (colour == Colour.ORANGE) {
			return orangeMember;
		}
		else if (colour == Colour.NEUTRAL) {
			return neutralMember;
		}
		else 
			throw new IllegalArgumentException();
		
	}
	
	/**
	 * Method that sets the value for every family member to the correct value of the corresponding dice.
	 * Furthermore, if the player got some effects that change his values(i.e his family members values are changed or
	 * he is under the effect of a malus), his family members' values will be modified.
	 * @param dices It's the set of dices that assigns the corresponding value for every family member. 
	 */
	public void setValues(Dices dices){
		if (dices == null) {
			throw new NullPointerException("Attention: dices are null");
		}
		
		if (dices.readDice(Colour.BLACK) < 1 || dices.readDice(Colour.BLACK) > 6) {
			throw new IllegalArgumentException("The value of the black dice should be between 1 and 6");
		}
		
		if (dices.readDice(Colour.ORANGE) < 1 || dices.readDice(Colour.ORANGE) > 6) {
			throw new IllegalArgumentException("The value of the orange dice should be between 1 and 6");
		}
		
		if (dices.readDice(Colour.WHITE) < 1 || dices.readDice(Colour.WHITE) > 6) {
			throw new IllegalArgumentException("The value of the white dice should be between 1 and 6");
		}
		
		
		//changes of permanent effect	
		lastDices= dices;
		
		int orangeDice= dices.readDice(Colour.ORANGE);
		int blackDice=dices.readDice(Colour.BLACK);
		int whiteDice= dices.readDice(Colour.WHITE);
		if(player.getPermanentModifiers().isThreeDicesChangeOn()){  //Ludovico Il moro Effect
			orangeDice=player.getPermanentModifiers().getValue3dicesChanged();
			whiteDice= player.getPermanentModifiers().getValue3dicesChanged();
			blackDice= player.getPermanentModifiers().getValue3dicesChanged();
		}
		
		int colouredChange = player.getPermanentModifiers().getColouredMemberChange();
		int neutralChange = player.getPermanentModifiers().getneutralMemberChange();
		
		orangeMember.setValue(orangeDice+colouredChange);
		blackMember.setValue(blackDice+colouredChange);
		whiteMember.setValue(whiteDice+colouredChange);
		neutralMember.setValue(GameParameters.getDefaultNeutralValue()+neutralChange);
		notifyObservers(new FamilyMembersDescriber(this));
	}
	
	/**
	 * Method that sets the value for every family member to the correct value after the activation of a permanent effect.
	 *
	 * 
	 */
	public void changeValues(){
		//changes of permanent effect	
		Dices dices= lastDices;
		
		if (dices == null) {
			throw new NullPointerException("Attention: dices are null");
		}
		
		if (dices.readDice(Colour.BLACK) < 1 || dices.readDice(Colour.BLACK) > 6 )
			throw new IllegalArgumentException();

		if (dices.readDice(Colour.WHITE) < 1 || dices.readDice(Colour.WHITE) > 6)
			throw new IllegalArgumentException();

		if (dices.readDice(Colour.ORANGE) < 1 || dices.readDice(Colour.ORANGE) > 6) 
			throw new IllegalArgumentException();

		
		setValues(dices);
		
		notifyObservers(new FamilyMembersDescriber(this));
	}
	
	
	
	
	
	/**
	 * Method that returns the colors representing  the family members that are actually free.
	 * @return freeMembers It's the set of the colors representing the family members that are actually free.
	 */
	public Set<Colour> whatIsFree() {  
		Set<Colour> freeMembers = new HashSet<>();
		if (blackMember.isFree() ) {
			freeMembers.add(Colour.BLACK);
		}
		if (orangeMember.isFree()) {
			freeMembers.add(Colour.ORANGE);
		}
		if (whiteMember.isFree()) {
			freeMembers.add(Colour.WHITE);
		}
		if (neutralMember.isFree()) {
			freeMembers.add(Colour.NEUTRAL);
		}
		return freeMembers;
	}
	
	
	/**
	 * Method that returns the map of the family members.
	 * @return mapDescriber :the map describing the values of the dices.
	 */
	
	public Map<Colour, Integer> getStatus(){
		Map<Colour, Integer> mapDescriber = new EnumMap<>(Colour.class);
		mapDescriber.put(Colour.ORANGE, orangeMember.getValue());
		mapDescriber.put(Colour.BLACK, blackMember.getValue());
		mapDescriber.put(Colour.WHITE, whiteMember.getValue());
		mapDescriber.put(Colour.NEUTRAL, neutralMember.getValue());
		return mapDescriber;
	}
	
	
	/**
	 * Method that sets all the family members to free, 
	 * i.e. the attributes "free" of the family members will be set to true.  
	 */
	public void setFreeAll() {
		orangeMember.setFree();
		whiteMember.setFree();
		blackMember.setFree();
		neutralMember.setFree();
	}
	
	
	
	
}
