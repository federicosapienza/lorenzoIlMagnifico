package it.polimi.ingsw.GC_26_player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;


/*Class that keeps memory and logic of all the modifiers linked to a player and activated by Leader Cards and excommunication tiles
 * 
 * 
 * Help for the reader: in order to keep near all the logic of a single modifier, attributes are placed near to correspondent methods,
 * not at the beginning of the class


*/
public class PermanentModifiers {
	private Player player;
	
	public PermanentModifiers(Player player) {
		this.player = player;
	}
	
	
	//Resources malus of excommunication tiles (the firsts 4 in the GameRules pdf)
	private boolean resourcesMalusOn=false;
	private ResourcesOrPoints resourcesMalus= ResourcesOrPoints.newResources(0, 0, 0, 0); // at the beginning of the game there are not malus
	
	public boolean IsresourcesMalusOn() {
		return resourcesMalusOn;
	}
	
	public void setMalus(ResourcesOrPoints temp){ // update the malus
		this.resourcesMalusOn = true; //once activated can not be undone, as in the game
	
		resourcesMalus=ResourcesOrPoints.sum(resourcesMalus, temp);
	}
	
	public ResourcesOrPoints getResourcesAfterMalus(ResourcesOrPoints resources){ // subtract malus from the resources the player can obtain
		return ResourcesOrPoints.subtract(resources, resourcesMalus);
			
	}
	
	// Cesare Borgia effect
	private boolean militaryPointRequirementNotNeeded; 
	public boolean isMilitaryPointRequirementNotNeeded() {
		return militaryPointRequirementNotNeeded;
	}
	public void setMilitaryPointRequirementNotNeeded() {
		this.militaryPointRequirementNotNeeded = true;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*Dame and Pico della Mirandola Effect: anyway is handled in a general way in order to
	potentially have much more cards with this kind of effect(not only money, but also servants...)									*/
	private Boolean discountOnResources=false;
	private Map<BoardZone, ResourcesOrPoints>  discounts;
	public Boolean IsTherediscountOnResources(BoardZone zone) {
		return discountOnResources;
	}
	public void addDiscount(BoardZone zone, ResourcesOrPoints res){
		ResourcesOrPoints temp = discounts.get(zone);
		if(temp==null){  // if there was no discount
			if(zone!=null) //(i.e Dame effect)
				discounts.put(zone, res);
			else{
				//zone==null //means that a discount on any tower: Pico della mirandola effect
					discounts.put(BoardZone.TERRITORYTOWER, res);
					discounts.put(BoardZone.VENTURETOWER, res);
					discounts.put(BoardZone.CHARACTERTOWER, res);
					discounts.put(BoardZone.BUILDINGTOWER, res);
				}
		}
		else{//there was discount
			if(zone!=null) //(i.e Dame effect)
				discounts.put(zone, ResourcesOrPoints.sum(res, temp));
			else{
				//zone==null //means that a discount on any tower: Pico della mirandola effect
					discounts.put(BoardZone.TERRITORYTOWER, ResourcesOrPoints.sum(res, temp));
					discounts.put(BoardZone.VENTURETOWER,ResourcesOrPoints.sum(res, temp));
					discounts.put(BoardZone.CHARACTERTOWER,ResourcesOrPoints.sum(res, temp));
					discounts.put(BoardZone.BUILDINGTOWER, ResourcesOrPoints.sum(res, temp));
				}
			}	
	}
	public ResourcesOrPoints getDiscount(BoardZone zone){
		ResourcesOrPoints discount = discounts.get(zone);
		if(zone==null){   //if there is no discount
			return ResourcesOrPoints.newResources(0, 0, 0, 0);
		}
		else return discount;
	}
	
	//checked and called in ResourcesOrPointPayment;
			/*Created for handling Pico Della Mirandola card and Dame, developed in order to be useful in case of creation of similar cards
			 */
	public  ResourcesOrPoints resourcesOrPointsDiscount(BoardZone zone, ResourcesOrPoints price){  
		ResourcesOrPoints  discount= discounts.get(zone);
		return ResourcesOrPoints.subtract(price, discount);
			
	
	}
	

	
	
	
	//Santa Rita effect: doubles the earning of coins, servants, stone , wood. : hecked and called in ReceiveResourcesOrPointsEffect
	private boolean doubleEarningOn=false;
	
	public boolean isDoubleEarningOn() {
		return doubleEarningOn;
	}
	public void setDoubleEarningOn() {
		doubleEarningOn = true;
	}
	
	
	
	
	
	// action Modifiers: for towers positions in getting a card and in production or harvest to change action value
	//used both in development Card and excommunication tiles: checked and called in ActionCheckerHandler and SecondActionHandler
	private Map<BoardZone, Integer> actionModifiers;
		
	public void addActionModifier(BoardZone zone, int value){
		Integer temp = actionModifiers.get(zone);
		if(temp==null)
			actionModifiers.put(zone, value);
		else{
			actionModifiers.put(zone, temp+value);
		}
	}
	
	
	public  int getActionModifier(BoardZone zone){
		Integer temp = actionModifiers.get(zone);
		if(temp==null)
			return 0;
		else return temp;
		
	}
	/////////////
	
	
	//revoke bonuses obtained from Tower spaces (Preacher Card);  //checked and called in ActionPerformedHandler
	 private boolean bonusRevoked =false;
	 public void setBonusRevokedOn(){
		 bonusRevoked=true;
	 }
	public boolean isTowerBonusRevokedOn() {
		return bonusRevoked;
	}
	
	
	//family members value increments or decrements
	//excommunication tile: reduce the coloured family member value :checked and called in "FamilyMembers"
	private int colouredMembersChange;
	public int getColouredMemberChange(){
		return colouredMembersChange;
	}
	public void setColouredMembersChange(int value){
		colouredMembersChange += value;
	}
	
	private int neutralMemberChange;
	public int getneutralMemberChange(){
		return neutralMemberChange;
	}
	public void setNeutralMembersChange(int value){
		neutralMemberChange += value;
	}
	

	
	
	
	//market Ban (excommunication tile effect),checked and  called in "ActionCheckerHandler"
	private boolean marketBanFlag=false; 
	
	public boolean getMarketBanFlag(){
		return marketBanFlag;
	}
	public void setMarketBanFlagOn(){
		marketBanFlag =true;
	}
	
	
	// doubles the have to spend 2 servants to increase your ActionHandler value by 1 " checked and called in ActionCheckerHandler and SecondActionHandler
	//excommunication tile effect:
	private boolean doubleServants=false;
	public boolean isDoubleServantsOn() {
		return doubleServants;
	}
	public void setDoubleServantsOn() {
		doubleServants =true;
	}

	//No victoryPoints for developmentCard type, (Borgia Effect)
	private Set<DevelopmentCardTypes> noVictoryPointsTypes;
	
	public void noVictoryPointsForCardType(DevelopmentCardTypes type){
		noVictoryPointsTypes.add(type);
	}
	public boolean pointsForThisCardType(DevelopmentCardTypes type){
		return noVictoryPointsTypes.contains(type);
	}
	
	
	//Lose victoryPoints at the end of the game. excommunication tile Effect
	// resourcesOrPoints are here used to save what and how many resources or points causes loss on victoryPoints: 
	// 0 stands for no malus, 1,2 ... stand for how many victory point for type.
	private ResourcesOrPoints parametersForLosingPoints = ResourcesOrPoints.newResources(0, 0, 0, 0);
	public void addParameterForLosingPoints(ResourcesOrPoints resourcesOrPoints){
		parametersForLosingPoints= ResourcesOrPoints.sum(parametersForLosingPoints, resourcesOrPoints);
	}
	public int howManyVIctoryPointLess(){
		Warehouse test =player.getWarehouse();
		ResourcesOrPoints malus = parametersForLosingPoints;
		return test.getCoins() * malus.getCoins() + test.getServants() *malus.getServants() + test.getStone()* malus.getStone()+ 
				test.getWood()*malus.getWood()+ test.getVictoryPoints()*malus.getVictoryPoints()
				+ test.getFaithPoints()*malus.getFaithPoints() + test.getMilitaryPoints()*malus.getMilitaryPoints();
	}
	
	//Ariosto effect, handled in ActionCheckerHandler
	private boolean goingInOccupiedPositionsAllowed=false;
	public boolean isGoingInOccupiedPositionsAllowed() {
		return goingInOccupiedPositionsAllowed;
	}
	public void setGoingInOccupiedPositionsAllowed() {
		this.goingInOccupiedPositionsAllowed = true;
	}
	

	//Brunelleschi effect : used in ActionChecker and ActionPerformer
	private boolean towerOccupiedMalusDisabled=false;
	
	public boolean isTowerOccupiedMalusDisabled() {
		return towerOccupiedMalusDisabled;
	}
	
	public void setTowerOccupiedMalusDisabled() {
		towerOccupiedMalusDisabled= true;
	}
	
	//Setting dices Value: 
	//Ludovico Il Moro and Federico da Montefeltro effect. 
	//The first parameter shows how many colored family Member will have "value" value
	//used in FamilyMembers
	//TODO per il momento non consideriamo il caso in effetti attivati durante un turno, ma solo all' inizio
	private boolean threeDicesChangeOn=false;
	int value3dicesChanged=0;
	public boolean isThreeDicesChangeOn() {
		return threeDicesChangeOn;
	}
	private void setValue3dicesChanged(int value) {
		this.value3dicesChanged = value;
		threeDicesChangeOn=true;
	}
	private boolean oneDicesChangeOn=false;
	int value1diceChanged=0;
	public boolean isOneDiceChangeOn() {
		return threeDicesChangeOn;
	}
	private void setValue1diceChanged(int value) {  
		this.value1diceChanged = value;
		oneDicesChangeOn=true;
	}
	
	public int getValue1diceChanged() {
		return value1diceChanged;
	}
	
	public int getValue3dicesChanged() {
		return value3dicesChanged;
	}
	
	
	public void setDicesSetted(int howManyDicesSetted, int value){
		if(howManyDicesSetted==3)
			setValue1diceChanged(value);
		else setValue3dicesChanged(value);
	}
	
	
	
	
	
	
}
