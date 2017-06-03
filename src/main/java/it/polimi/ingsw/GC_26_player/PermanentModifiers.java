package it.polimi.ingsw.GC_26_player;

import java.util.Map;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

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
	
	public  ResourcesOrPoints resourcesOrPointsDiscount(ResourcesOrPoints price){
		/*Created for handling Pico Della Mirandola card, developed in order to be useful in case of creation of similar cards
		 */
		int coins = price.getResources().getCoins()-discount.getResources().getCoins();
		int servants = price.getResources().getServants()-discount.getResources().getServants();
		int stone = price.getResources().getStone()-discount.getResources().getStone();
		int wood = price.getResources().getWood()-discount.getResources().getWood();
		int militaryP = price.getPoints().getMilitaryPoints()-discount.getPoints().getMilitaryPoints();
		int faithP = price.getPoints().getFaithPoints()-discount.getPoints().getFaithPoints();
		int victoryP= price.getPoints().getVictoryPoints()-discount.getPoints().getVictoryPoints();
		 //no Council Privileges (never used directly as price)
		return ResourcesOrPoints.newResourcesOrPoints(moreThanZero(coins), moreThanZero(servants), moreThanZero(wood), 
				moreThanZero(stone), moreThanZero(victoryP), moreThanZero(militaryP), moreThanZero(faithP), 
				price.getPoints().getCouncilPrivileges());
	}
	private static int moreThanZero(int test){
		//used only by newResourcesOrPointsDiscounted, to ensure no negative value
		if(test>= 0)
			return test;
		else return 0;
	}
	

	
	
	
	//Santa Rita effect: doubles the earning of coins, servants, stone , wood. 
	private boolean doubleEarningOn=false;
	
	public boolean isDoubleEarningOn() {
		return doubleEarningOn;
	}
	public void setDoubleEarningOn() {
		doubleEarningOn = true;
	}
	
	
	
	
	
	//TODO i modificatori di valori delle carte e delle produzioni/raccolti e carta Predicatore : niente bonus da torri: qui e in effects
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
	
}
