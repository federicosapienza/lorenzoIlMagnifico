package it.polimi.ingsw.GC_26_player;

import java.util.Map;


import it.polimi.ingsw.GC_26_board.BoardZone;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;


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
	
	
	//Resources malus of excommunication tiles
	private boolean resourcesMalusOn=false;
	private ResourcesOrPoints resourcesMalus= ResourcesOrPoints.newResources(0, 0, 0, 0); // at the beginning of the game there are not malus
	private boolean StoneOrWoodMalus=false;
	
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
	
	
	public  ResourcesOrPoints resourcesOrPointsDiscount(BoardZone zone, ResourcesOrPoints price){
		/*Created for handling Pico Della Mirandola card and Dame, developed in order to be useful in case of creation of similar cards
		 */
		ResourcesOrPoints  discount= discounts.get(zone);
		return ResourcesOrPoints.subtract(price, discount);
			
	
	}
	

	
	
	
	//Santa Rita effect: doubles the earning of coins, servants, stone , wood. 
	private boolean doubleEarningOn=false;
	
	public boolean isDoubleEarningOn() {
		return doubleEarningOn;
	}
	public void setDoubleEarningOn() {
		doubleEarningOn = true;
	}
	
	
	
	
	
	// carta Predicatore : niente bonus da torri: qui e in effects
	private Map<BoardZone, Integer> actionModifiers;
		
	public void addModifier(BoardZone zone, int value){
		Integer temp = actionModifiers.get(zone);
		if(temp==null)
			actionModifiers.put(zone, value);
		else{
			actionModifiers.put(zone, temp+value);
		}
	}
	
	
	public  int getModifier(BoardZone zone){
		Integer temp = actionModifiers.get(zone);
		if(temp==null)
			return 0;
		else return temp;
		
	}
	
	//revoke bonuses obtained from Tower spaces (Preacher Card);
	 private boolean bonusRevoked =false;
	 public void setBonusRevokedOn(){
		 bonusRevoked=true;
	 }
	public boolean isBonusRevokedOn() {
		return bonusRevoked;
	}
	
	//excommunication tile: reduce the coloured family member value
	private int dicesMalusValue;
	public int getColouredMembersMalusValue(){
		return dicesMalusValue;
	}
	public void setColouredMembersMalusValue(int value){
		dicesMalusValue = value;
	}
}
