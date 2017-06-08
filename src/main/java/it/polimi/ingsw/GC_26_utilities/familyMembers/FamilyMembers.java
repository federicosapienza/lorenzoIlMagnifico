package it.polimi.ingsw.GC_26_utilities.familyMembers;
import it.polimi.ingsw.GC_26_utilities.dices.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import it.polimi.ingsw.GC_26_gameLogic.GameParameters;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_serverView.Observable;

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
public class FamilyMembers extends Observable<FamilyMembersDescriber> implements FamilyMembersDescriber{
	
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
	
	/**
	 * Constructor that creates the set of family members of a player.
	 * @param player It's the player that owns these family members.
	 */
	public FamilyMembers(Player player) {
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
			throw  new IllegalArgumentException();
		
	}
	
	/**
	 * Method that sets the value for every family member to the correct value of the corresponding dice.
	 * Furthermore, if the player got some effects that change his values(i.e his family members values are changed or
	 * he is under the effect of a malus), his family members' values will be modified.
	 * @param dices It's the set of dices that assigns the corresponding value for every family member. 
	 */
	public void setValues(Dices dices){
		//changes of permanent effect	
		
		int orangeDice= dices.readDice(Colour.ORANGE);
		int blackDice=dices.readDice(Colour.BLACK);
		int whiteDice= dices.readDice(Colour.WHITE);
		if(player.getPermanentModifiers().isThreeDicesChangeOn()){  //Ludovico Il moro Effect
			orangeDice=player.getPermanentModifiers().getValue3dicesChanged();
			whiteDice= player.getPermanentModifiers().getValue3dicesChanged();
			blackDice= player.getPermanentModifiers().getValue3dicesChanged();
		}
		if(player.getPermanentModifiers().isOneDiceChangeOn()){ //Federico Da Montefeltro effect
			int test = searchSmaller(orangeDice,whiteDice,blackDice);
			if(test==1)
				orangeDice=player.getPermanentModifiers().getValue1diceChanged();
			else  if(test==2)
				blackDice=player.getPermanentModifiers().getValue1diceChanged();
			else if(test==3)
				whiteDice=player.getPermanentModifiers().getValue1diceChanged();		
		}
			
		
		int colouredChange = player.getPermanentModifiers().getColouredMemberChange();
		int neutralChange = player.getPermanentModifiers().getneutralMemberChange();
		
		orangeMember.setValue(orangeDice+colouredChange);
        blackMember.setValue(blackDice+colouredChange);
        whiteMember.setValue(whiteDice+colouredChange);
        neutralMember.setValue(GameParameters.getDefaultNeutralValue()+neutralChange);
        notifyObservers(this);
	}
	
	/**
	 * Method that returns the set of the family members that are actually free.
	 * @return freeMembers It's the set of the family members that are actually free.
	 */
	public Set<FamilyMember> whatIsFree() {  
		Set<FamilyMember> freeMembers = new HashSet<FamilyMember>();
		if (blackMember.isFree() == true) {
			freeMembers.add(blackMember);
		}
		if (orangeMember.isFree() == true) {
			freeMembers.add(orangeMember);
		}
		if (whiteMember.isFree() == true) {
			freeMembers.add(whiteMember);
		}
		if (neutralMember.isFree() == true) {
			freeMembers.add(neutralMember);
		}
		return freeMembers;
	}
	
	
	/**
	 * Method that returns the map of the family members.
	 * @return mapDescriber :the map describing the values of the dices.
	 */
	
	public Map<Colour, Integer> getStatus(){
		Map<Colour, Integer> mapDescriber =new HashMap<>();
		mapDescriber.put(Colour.ORANGE, orangeMember.getValue());
		mapDescriber.put(Colour.BLACK, orangeMember.getValue());
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
	
	/**
	 * Method that finds the smaller of three integer(in case two are equals the first is preferred.)
	 * 
	 * @param test1. : the first integer value
	 * @param test1. : the second integer value
	 * @param test3. : the third integer value
	 * 
	 * @return temp It's the number corresponding to the number of the argument received which was smaller
	 */
	
	private int searchSmaller(int test1, int test2, int test3){
		int temp=1;
		int smallerValue=test1;
		if(test2<smallerValue){
			temp=2;
			smallerValue=test2;
		}
		if(test3<smallerValue)
			temp=3;
		return temp;
	}
	
}
