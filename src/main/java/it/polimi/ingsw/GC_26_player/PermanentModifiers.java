package it.polimi.ingsw.GC_26_player;

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
	public boolean isStoneOrWoodMalus() {
		return StoneOrWoodMalus;
	}

	public void setStoneOrWoodMalus() {
		StoneOrWoodMalus = true;
	}
	
	public void setResoursesMalusOn() {
		this.resourcesMalusOn = true; //once activated can not be undone, as in the game
	}
	
	public void setMalus(ResourcesOrPoints temp){ // update the malus
		resourcesMalus=ResourcesOrPoints.sum(resourcesMalus, temp);
	}
	
	public void runMalus(ResourcesOrPoints resources){ //receives the resourses just earn by the player and if needed subtract malus from warehouse 
		if(StoneOrWoodMalus);
			//TODO user-interaction: c è una scelta
		//TODO tutto 
	}
	
	
	// Cesare Borgia effect
	private boolean militaryPointRequirementNotNeeded; 
	public boolean isMilitaryPointRequirementNotNeeded() {
		return militaryPointRequirementNotNeeded;
	}
	public void setMilitaryPointRequirementNotNeeded() {
		this.militaryPointRequirementNotNeeded = true;
	}
	
	
	/*Pico della Mirandola Effect: anyway is handled in a general way in order to
	potentially have much more cards with this kind of effect(not only money, but also servants...)									*/
	private Boolean IsthereDiscountOnResources=false;
	private ResourcesOrPoints discount;
	public Boolean IsTherediscountOnResources() {
		return IsthereDiscountOnResources;
	}
	public void activateDiscountOnResources() {
		IsthereDiscountOnResources=true;
	}
	public ResourcesOrPoints getDiscount() {
		return discount;
	}
	
	//Santa Rita effect: doubles the earning of coins, servants, stone , wood. 
	private boolean doubleEarningOn=false;
	
	public boolean isDoubleEarningOn() {
		return doubleEarningOn;
	}
	public void setDoubleEarningOn() {
		doubleEarningOn = true;
	}
	public void DoDoubleEarning(ResourcesOrPoints resources){
		player.getWarehouse().add(ResourcesOrPoints.newResources(resources.getResources().getCoins(), 
																resources.getResources().getServants(),
																resources.getResources().getWood(),
																resources.getResources().getStone()));
	}
	
	
	
	//TODO i modificatori di valori delle carte e delle produzioni/raccolti e carta Predicatore : niente bonus da torri: qui e in effects
	

}
