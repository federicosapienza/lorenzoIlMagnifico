package it.polimi.ingsw.GC_26_player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;


/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * Class that keeps a record and implements the logic of all the modifiers linked to a player 
 * and activated by Leader Cards and excommunication tiles
 * 
 * 
 * In order to make easier to understand the code in the logic of the single modifier, 
 * attributes are placed near the correspondent methods, not at the beginning of the class
*/
public class PermanentModifiers {
	private Player player;
	 /**
	  * Constructor: it associates the permanent modifiers to the player contained in the parameter
	  * @param player It's the player associated to these permanent modifiers
	  */
	public PermanentModifiers(Player player) {
		if (player == null) {
			throw new NullPointerException();
		}
		this.player = player;
	}
	
	
	/**
	 * Malus effects of excommunication tiles on resources (the first 4 in the GameRules pdf)
	 */
	private boolean resourcesMalusOn=false;
	
	// at the beginning of the game there is no malus effect
	private ResourcesOrPoints resourcesMalus= ResourcesOrPoints.newResources(0, 0, 0, 0); 
	
	/**
	 * Method that verifies if the malus effect has been activated.
	 * @return resourcesMalusOn It's the boolean that indicates if the malus has been activated: 
	 * it's true if it's been activated, else it's false.
	 */
	public boolean isResourcesMalusOn() {
		return resourcesMalusOn;
	}
	
	/**
	 * Method used to update the malus: it adds the resources or points expressed by temp 
	 * to the current value of resourcesMalus in order to update the value of resourcesMalus.
	 * @param temp It represents the resources or points that the excommunication tiles will subtract to the player
	 */
	public void setMalus(ResourcesOrPoints temp){ 
		//the activation of resourcesMalusOn is not reversible: once activated it can't be disabled.
		this.resourcesMalusOn = true; 
		
		resourcesMalus=ResourcesOrPoints.sum(resourcesMalus, temp);
	}
	
	/**
	 * Method that subtracts the malus from the resources that the player can obtain
	 * @param resources It represents the resources that the player could obtain
	 * @return ResourcesOrPoints.subtract(resources, resourcesMalus) It represents the real value of the resources
	 * that the player obtains: the value will be equal to the value of the resources expressed by the @param, 
	 * minus the value of the malus, expressed by resourcesMalus.
	 */
	public ResourcesOrPoints getResourcesAfterMalus(ResourcesOrPoints resources){ 
		return ResourcesOrPoints.subtract(resources, resourcesMalus);
			
	}
	
	/**
	 * The following lines are needed to represent the effect of Cesare Borgia Leader card:
	 * if the player owns this card, he doesn't need to satisfy the Military Points requirement 
	 * when he takes Territory Cards.
	 */
	private boolean militaryPointRequirementNotNeeded=false; 
	public boolean isMilitaryPointRequirementNotNeeded() {
		return militaryPointRequirementNotNeeded;
	}
	public void setMilitaryPointRequirementNotNeeded() {
		this.militaryPointRequirementNotNeeded = true;
	}
	
	/**
	 * 
	 * Dame and Pico della Mirandola Effect: it is handled in a general way in order to
	 * have potentially much more cards with this kind of effect (not only money, but also servants...)	
	 */
	
	private boolean discountOnBuildingTowerResources=false;
	private boolean discountOnTerritoryTowerResources=false;
	private boolean discountOnCharacterResources=false;
	private boolean discountOnVentureResources=false;
	private boolean discountOnCouncilPalaceResources=false;
	private boolean discountOnMarketResources=false;
	private boolean discountOnHarvestResources=false;
	private boolean discountOnProductionResources=false;
	private Map<BoardZone, ResourcesOrPoints> discounts= new HashMap<>();
	
	/**
	 * Method that checks the presence of a discount in the zone expressed in the parameter
	 * @param zone It's the zone to check
	 * @return true if there's a discount in the zone; false if there isn't any discount in the zone
	 */
	public boolean IsTherediscountOnResources(BoardZone zone) {
		if (zone == BoardZone.BUILDINGTOWER) {
			return discountOnBuildingTowerResources;
		}
		else if (zone == BoardZone.TERRITORYTOWER) {
			return discountOnTerritoryTowerResources;
		} 
		else if (zone == BoardZone.CHARACTERTOWER) {
			return discountOnCharacterResources;
		}
		else if (zone == BoardZone.VENTURETOWER) {
			return discountOnVentureResources;
		}
		else if (zone == BoardZone.COUNCILPALACE) {
			return discountOnCouncilPalaceResources;
		}
		else if (zone == BoardZone.MARKET) {
			return discountOnMarketResources;
		}
		else if (zone == BoardZone.PRODUCTION) {
			return discountOnProductionResources;
		}
		else if (zone == BoardZone.HARVEST) {
			return discountOnHarvestResources;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Method that activates the flag that indicates that a discount has been activated in the zone expressed in the parameter
	 * @param zone It's the zone where the flag must be activated
	 */
	public void activateFlagDiscountOnResources(BoardZone zone) {
		
		if (zone == BoardZone.BUILDINGTOWER) {
			discountOnBuildingTowerResources = true;
		}
		else if (zone == BoardZone.TERRITORYTOWER) {
			discountOnTerritoryTowerResources = true;
		} 
		else if (zone == BoardZone.CHARACTERTOWER) {
			discountOnCharacterResources = true;
		}
		else if (zone == BoardZone.VENTURETOWER) {
			discountOnVentureResources = true;
		}
		else if (zone == BoardZone.COUNCILPALACE) {
			discountOnCouncilPalaceResources = true;
		}
		else if (zone == BoardZone.MARKET) {
			discountOnMarketResources = true;
		}
		else if (zone == BoardZone.PRODUCTION) {
			discountOnProductionResources = true;
		}
		else if (zone == BoardZone.HARVEST) {
			discountOnHarvestResources = true;
		}
		
	}
	
	/**
	 * Method that adds a discount on the cost required by some cards as resources or points, when obtaining
	 * cards from particular zones of the board
	 * @param zone It's the zone of the board from which the player gets the card that he wants.
	 * @param res It's the discount on the resources or points that the player has to pay to get the card.
	 */
	public void addDiscount(BoardZone zone, ResourcesOrPoints res){
		ResourcesOrPoints temp = discounts.get(zone);
		if(discounts.isEmpty()){  // if there is no discount
			if(zone!=null) { //(i.e Dame effect)
				discounts.put(zone, res);
				activateFlagDiscountOnResources(zone);
				return;
			}
			else{
				//zone==null 
				//means that a discount will be applied on every tower: Pico della mirandola effect
					discounts.put(BoardZone.TERRITORYTOWER, res);
					activateFlagDiscountOnResources(BoardZone.TERRITORYTOWER);
					discounts.put(BoardZone.VENTURETOWER, res);
					activateFlagDiscountOnResources(BoardZone.VENTURETOWER);
					discounts.put(BoardZone.CHARACTERTOWER, res);
					activateFlagDiscountOnResources(BoardZone.CHARACTERTOWER);
					discounts.put(BoardZone.BUILDINGTOWER, res);
					activateFlagDiscountOnResources(BoardZone.BUILDINGTOWER);
					return;
				}
		}
		else if (!discounts.isEmpty()){ //there is a discount
			if(zone!=null) { //(i.e. Dame effect)
				discounts.put(zone, ResourcesOrPoints.sum(res, temp));
				activateFlagDiscountOnResources(zone);
				return;
			}
			else{
				//zone==null 
				//means that a discount will be applied on every tower: Pico della mirandola effect
				if (discounts.containsKey(BoardZone.TERRITORYTOWER)) {
					discounts.put(BoardZone.TERRITORYTOWER, ResourcesOrPoints.sum(res, discounts.get(BoardZone.TERRITORYTOWER)));
					activateFlagDiscountOnResources(BoardZone.TERRITORYTOWER);
				}
				else {
					discounts.put(BoardZone.TERRITORYTOWER, res);
					activateFlagDiscountOnResources(BoardZone.TERRITORYTOWER);
				}
				if (discounts.containsKey(BoardZone.VENTURETOWER)) {
					discounts.put(BoardZone.VENTURETOWER,ResourcesOrPoints.sum(res, discounts.get(BoardZone.VENTURETOWER)));
					activateFlagDiscountOnResources(BoardZone.VENTURETOWER);
				}
				else {
					discounts.put(BoardZone.VENTURETOWER, res);
					activateFlagDiscountOnResources(BoardZone.VENTURETOWER);
				}
				if (discounts.containsKey(BoardZone.CHARACTERTOWER)) {
					discounts.put(BoardZone.CHARACTERTOWER,ResourcesOrPoints.sum(res, discounts.get(BoardZone.CHARACTERTOWER)));
					activateFlagDiscountOnResources(BoardZone.CHARACTERTOWER);
				}
				else {
					discounts.put(BoardZone.CHARACTERTOWER, res);
					activateFlagDiscountOnResources(BoardZone.CHARACTERTOWER);
				}
				if (discounts.get(BoardZone.BUILDINGTOWER) != null) {
					ResourcesOrPoints buildingRes = discounts.get(BoardZone.BUILDINGTOWER);
					ResourcesOrPoints totalBuildingRes = ResourcesOrPoints.sum(buildingRes, res);
					discounts.put(BoardZone.BUILDINGTOWER, totalBuildingRes);
					activateFlagDiscountOnResources(BoardZone.BUILDINGTOWER);
				}
				else {
					discounts.put(BoardZone.BUILDINGTOWER, res);
					activateFlagDiscountOnResources(BoardZone.BUILDINGTOWER);
				}
				return;
			}
		}	
	}
	
	/**
	 * Getter method that returns the discount that is eventually applied on a particular zone of the board 
	 * expressed by the @param zone.
	 * @param zone It's the particular zone of the board in which a discount has been applied.
	 * @return ResourcesOrPoints.newResources(0, 0, 0, 0) if the there is no discount;
	 * @return discount It's the value of the discount in terms of resources or points, if there is a discount
	 */
	public ResourcesOrPoints getDiscount(BoardZone zone){
		ResourcesOrPoints discount = discounts.get(zone);
		if(zone==null){   //if there is no discount
			return ResourcesOrPoints.newResources(0, 0, 0, 0);
		}
		else return discount;
	}
	
	/**
	 * checked and called in ResourcesOrPointPayment.
	 * Created for handling Pico Della Mirandola card and Dame, 
	 * developed in order to be useful in case of creation of similar cards
	 * 
	 * 
	 * Method that returns the subtraction between price (as resources or points) and the discount, for the zone
	 * expressed by the @param zone
	 * @param zone It's the zone in which the player wants to check the real price to pay
	 * @param price It's the original price to pay to get a card in a particular zone, not considering an eventual discount
	 * @return ResourcesOrPoints.subtract(price, discount) It's the real price to pay to get the card in the zone
	 * expressed in the @param zone: it's the subtraction between price and an eventual discount, if it exists.
	 */
	
	public ResourcesOrPoints resourcesOrPointsDiscount(BoardZone zone, ResourcesOrPoints price){  
		ResourcesOrPoints discount= discounts.get(zone);
		return ResourcesOrPoints.subtract(price, discount);
			
	
	}
	
	
	/**
	 * Santa Rita's effect: each time the player who owns this leader card receives wood, stone, coins, or servants 
	 * as an immediate effect from Development Cards (not from an action space),
	 * he receives the resources twice. 
	 * This will be checked and called in ReceiveResourcesOrPointsEffect
	 */
	private boolean doubleEarningOn=false;
	
	/**
	 * Method that checks if Santa Rita's effect has been activated.
	 * @return doubleEarningOn It's the boolean that indicates if Santa Rita effect has been activated: 
	 * true if it's active, false if it isn't.
	 */
	public boolean isDoubleEarningOn() {
		return doubleEarningOn;
	}
	
	/**
	 * Setter method to activate Santa Rita's effect.
	 */
	public void setDoubleEarningOn() {
		doubleEarningOn = true;
	
	}
	
	
	/**
	 * The following lines represent the action modifiers: they change the value of the action when getting a card
	 * from towers or when doing production or harvest.
	 * These action modifiers are used both in development cards and excommunication tiles.
	 * They'll be checked and called in ActionCheckerHandler and SecondActionHandler
	 */
	
	private Map<BoardZone, Integer> actionModifiers= new HashMap<>();
	
	/**
	 * Method used to add an action modifier
	 * @param zone It's the zone involved in the change of the value of the action
	 * @param value It's the value to add after applying the change: it has to be added to the previous value 
	 */
	public void addActionModifier(BoardZone zone, int value){
		Integer temp = actionModifiers.get(zone);
		if(temp==null)
			actionModifiers.put(zone, value);
		else{
			actionModifiers.put(zone, temp+value);
		}
	}
	
	/**
	 * Getter method that returns the value of the action modifier in the zone expressed by the @param zone
	 * @param zone It's the zone in which I want to get the value of the action modifier.
	 * @return 0 if the zone has no action modifiers, temp if it has.
	 */
	public int getActionModifier(BoardZone zone){
		Integer temp = actionModifiers.get(zone);
		if(temp==null)
			return 0;
		else return temp;
		
	}
	
	/**
	 * The following lines represent the possibility to revoke the bonuses obtained from tower spaces 
	 * (only the preacher card does it).
	 * This possibility will be checked and called in ActionPerformedHandler
	 */
	
	 private boolean bonusRevoked =false;
	 
	 /**
	  * Setter method to activate the revocation of bonuses
	  */
	 public void setBonusRevokedOn(){
		 bonusRevoked=true;
	 }
	 
	 /**
	  * Method to check if the revocation of bonuses is active
	  * @return bonusRevoked It's the boolean that indicates if the revocation is active or not.
	  */
	public boolean isTowerBonusRevokedOn() {
		return bonusRevoked;
	}
	
	/**
	 * The following lines represent the effect of the excommunication tiles that increment or decrement the value
	 * of the coloured family members.
	 * This possibility will be checked and called in the FamilyMembers class.
	 */
	
	//It's the value of the change applied to the value of the coloured family members
	private int colouredMembersChange;
	
	/**
	 * Getter method that returns the value of the coloured family members under the effect of the 
	 * excommunication tiles that increment/decrement their value
	 * @return colouredMembersChange It's the value of the family members under the effect of the 
	 * excommunication tiles that increment/decrement their value
	 */
	public int getColouredMemberChange(){
		return colouredMembersChange;
	}
	
	/**
	 * Setter method used when the excommunication tiles that increment/decrement the value of the coloured 
	 * family members are activated
	 * @param value It's the value that will be added or subtracted from the previous value 
	 * that the family members had before being affected by the effect of the excommunication tiles.
	 */
	public void setColouredMembersChange(int value){
		colouredMembersChange += value;
	}
	
	
	private int neutralMemberChange=0;
	
	/**
	 * Getter method that returns the value of the neutral family members under the effect of the 
	 * excommunication tiles that increment/decrement their value
	 * @return neutralMembersChange It's the value of the family members under the effect of the 
	 * excommunication tiles that increment/decrement their value
	 */
	public int getneutralMemberChange(){
		return neutralMemberChange;
	}
	
	/**
	 * Setter method used when the excommunication tiles that increment/decrement the value of the neutral 
	 * family members are activated
	 * @param value It's the value that will be added or subtracted from the previous value 
	 * that the neutral family members had before being affected by the effect of the excommunication tiles.
	 */
	public void setNeutralMembersChange(int value){
		neutralMemberChange += value;
	}
	

	
	
	/**
	 * The following lines represent the effect of the excommunication tiles that inhibit the players under this effect
	 * to access to the market.
	 * This eventuality will be checked and called in the ActionCheckerHandler class.
	 */
	
	//it's a flag that indicates if the player has been banned from the market
	private boolean marketBanFlag=false; 
	
	/**
	 * Getter method that checks if the player has been banned from the market or not. 
	 * @return marketBanFlag It's the flag that indicates if the player has been banned from the market:
	 * true if he's banned, false if he isn't.
	 */
	public boolean getMarketBanFlag(){
		return marketBanFlag;
	}
	
	/**
	 * Setter method used when one or more players are affected by the excommunication tiles that 
	 * inhibit the players under this effect to access to the market.
	 * Their marketBanFlag will be set to true. 
	 */
	public void setMarketBanFlagOn(){
		marketBanFlag = true;
	}
	
	/**
	 * The following lines represent the effect of the excommunication tile that doubles the number of servants
	 * to pay in order to increase the value of the action (2 servants to increase the value of the action by 1,
	 * 4 servants to increase the value of the action by 2 etc.) (See the 6th excommunication tile of the 2nd 
	 * period in the official rulebook, page 13 of 14)
	 * This eventuality will be checked and called in ActionCheckerHandler and SecondActionHandler 
	 */
	
	private boolean doubleServants=false;
	
	/**
	 * Method that checks if the player is affected by the excommunication tile that doubles the number of 
	 * servants to pay in order to increase the value of the action
	 * @return doubleServants It's the flag that indicates if the player is affected by the excommunication tile 
	 * that doubles the number of servants to pay in order to increase the value of the action:
	 * true if he's affected, false if he isn't.
	 */
	public boolean isDoubleServantsOn() {
		return doubleServants;
	}
	
	/**
	 * Setter method that activates the effect of the excommunication tile when one or more players are affected by it.
	 */
	public void setDoubleServantsOn() {
		doubleServants = true;
	}
	
	/**
	 * The following lines represent the effects of the first 3 excommunication cards of the 3rd period (see page 13
	 * of 14 of the official rulebook)
	 * They inhibit the player to score Victory points for any of their Influenced Characters or Encouraged Ventures,
	 * or Conquered Territories.
	 */
	
	private Set<DevelopmentCardTypes> noVictoryPointsTypes= new HashSet<>();
	
	/**
	 * Method that adds the type of card to the list of types of cards that won't influence anymore the score of 
	 * Victory Points for the player who is affected by one of the first 3 Excommunication tiles.
	 * @param type It's the type of card that will be added to the list of types of cards that won't influence 
	 * anymore the score of Victory Points
	 */
	public void noVictoryPointsForCardType(DevelopmentCardTypes type){
		noVictoryPointsTypes.add(type);
	}
	
	/**
	 * Method that checks if the type of card contained in the @param type is influencing the score of Victory
	 * Points or not: if it's contained in the Set noVictoryPointsTypes, it can't have an influence in the score,
	 * if it isn't, it will be counted as reported in the rulebook. 
	 * @param type It's the type of card that will be checked in the list of types of cards that don't influence 
	 * the score of Victory Points
	 * @return noVictoryPointsTypes.contains(type) It's the result of the check: true if the type of card expressed
	 * by the @param type is contained in the Set noVictoryPointsTypes, false if it isn't contained.
	 */
	public boolean pointsForThisCardType(DevelopmentCardTypes type){
		return noVictoryPointsTypes.contains(type);
	}
	
	/**
	 * The following lines represent the effects of the 4 excommunication cards of the 3rd period (see page 13
	 * of 14 of the official rulebook)
	 * They force the player to lose Victory points for any N victory Points owned.
	 * or Conquered Territories.
	 */
	
	private int victoryPointsReducer=1;
	
	/**
	 * Method that sets the parameter which will divide the number of victory points owned. 
	 * @param the parameter which will divide the number of victory points owned.
	 */
	
	
	public void setVictoryPointsReducer(int malus){
		victoryPointsReducer=malus;
	}
	
	/**
	 * Getter method that returns the malus that shall divide the victory points owned. 
	 * @return victoryPointsReducer : the value that indicates the malus that will divide the victory points owned,
	 * If not changed the default value is 1.
	 */
	public int getVictoryPointsReducer(){
		return victoryPointsReducer;
	}
	
	
	
	
	/**
	 * The following lines represent the effects of the last 3 excommunication tiles of the 3rd period (see pag 13
	 * of 14 of the official rulebook).
	 * At the end of the game, players affected by the effects of any of these excommunication tiles, will lose
	 * Victory Points for some resources or points: 
	 * 
	 * 1st tile: players affected by the effect of this tile lose 1 Victory Point for every Military Point they have. 
	 * (For example, if the players affected by this effect end the game with 12 Military Points, they lose 12 Victory Points.)
	 * 
	 * 2nd tile: players affected by the effect of this tile lose 1 Victory Point for every wood and stone on their 
	 * Building Cards’ costs. (For example, if all their Building Cards cost 7 wood and 6 stone, they lose 
	 * 13 Victory Points.)
	 * 
	 * 3rd tile: players affected by the effect of this tile lose 1 Victory Point for every resource 
	 * (wood, stone, coin, servant) in their supply on their Personal Board. 
	 * (For example, if the players affected by this effect end the game with 3 wood, 1 stone, 4 coins, and 2 servants, 
	 * they lose 10 Victory Points.)
	 * 
	 * So here, resourcesOrPoints are used to save what and how many resources or points cause a subtraction in 
	 * scoring Victory Points: 0 means no malus applied, other positive values stand for how many victory points 
	 * will be subtracted to the original scoring.
	 * 
	 */

	// resourcesOrPoints are here used to save what and how many resources or points causes loss on victoryPoints: 
	// 0 stands for no malus, 1,2 ... stand for how many victory point for type.
	private ResourcesOrPoints parametersForLosingPoints = ResourcesOrPoints.newResources(0, 0, 0, 0);
	
	/**
	 * Method that adds the resources or points that cause loss of Victory Points to the resources or points that
	 * have already been classified as resources or points that have this negative effect in scoring points.
	 * @param resourcesOrPoints It represents the resources or points to add in the resources or points that cause
	 * a loss in scoring Victory Points.
	 */
	
	public void addParameterForLosingPoints(ResourcesOrPoints resourcesOrPoints){
		parametersForLosingPoints = ResourcesOrPoints.sum(parametersForLosingPoints, resourcesOrPoints);
	}
	
	/**
	 * Method that returns the total amount of Victory points that the player is going to lose because of the effects of
	 * the last 3 excommunication tiles.
	 * @return the total amount of Victory points that the player is going to lose because of the maluses applied to 
	 * his resources and points.
	 */
	public int howManyVictoryPointLess(){
		Warehouse test = player.getWarehouse();
		ResourcesOrPoints malus = parametersForLosingPoints;
		return test.getCoins() * malus.getCoins() + test.getServants() * malus.getServants() + test.getStone() * malus.getStone() + 
				test.getWood() * malus.getWood() + test.getVictoryPoints() * malus.getVictoryPoints()
				+ test.getFaithPoints() * malus.getFaithPoints() + test.getMilitaryPoints() * malus.getMilitaryPoints();
	}
	
	/**
	 * The following lines represent the effect of the "Ludovico Ariosto" Leader card: it allows the players who own him
	 * to put their family members in occupied action spaces.
	 * This effect is handled in ActionCheckerHandler
	 */
	private boolean goingInOccupiedPositionsAllowed=false;
	
	/**
	 * Method that checks if the effect of the "Ludovico Ariosto" Leader card is active or not.
	 * @return true if the effect of the "Ludovico Ariosto" Leader card is active; false if it isn't active.
	 */
	public boolean isGoingInOccupiedPositionsAllowed() {
		return goingInOccupiedPositionsAllowed;
	}
	
	/**
	 * Method used to activate the effect of the "Ludovico Ariosto" Leader card.
	 */
	public void setGoingInOccupiedPositionsAllowed() {
		this.goingInOccupiedPositionsAllowed = true;
	}
	
	/**
	 * The following lines represent the effect of the "Filippo Brunelleschi" Leader card: the player who owns this card,
	 * doesn't have to spend 3 coins when he places his Family Members in a Tower that is already occupied.
	 * This is used in ActionChecker and ActionPerformer
	 */

	private boolean towerOccupiedMalusDisabled=false;
	
	/**
	 * Method that checks if the effect of the "Filippo Brunelleschi" Leader card is active
	 * @return true if the effect is active, false if it isn't active.
	 */
	public boolean isTowerOccupiedMalusDisabled() {
		return towerOccupiedMalusDisabled;
	}
	
	/**
	 * Method used to activate the effect of the "Filippo Brunelleschi" Leader card.
	 */
	public void setTowerOccupiedMalusDisabled() {
		towerOccupiedMalusDisabled= true;
	}
	
	/**
	 * The following lines represent the effects of the "Ludovico il Moro" and "Federico da Montefeltro" Leader cards.
	 * 
	 * Note: "Federico da Montefeltro" has an effect that is not permanent in the official rulebook, but for simplicity, 
	 * we decided to consider it as permanent.
	 * Ludovico il Moro has the following effect: the player's coloured Family Members has a value of 5, 
	 * regardless of their related dice. (The player can increase their value by spending servants or 
	 * if he has Character Cards with this effect.)
	 * 
	 * Federico da Montefeltro has the following effect: One of the player's coloured Family Members has a value of 6,
	 * regardless of its related dice. (The player can increase their value by spending servants or 
	 * if he has Character Cards with this effect.)
	 * 
	 * These 2 effects have a very similar logic, so we'll use a unique setDicesSetted method to determine if the 
	 * update to the value has to be applied to all the 3 coloured family members or to only one coloured family member.
	 * 
	 */
	
	//TODO per il momento non consideriamo il caso in effetti attivati durante un turno, ma solo all'inizio
	
	/**
	 * Parameters and methods for "Ludovico il Moro".
	 */
	
	private boolean threeDicesChangeOn=false;
	int value3dicesChanged=0;
	
	/**
	 * Method that checks if the effect of the "Ludovico il Moro" Leader card is active.
	 * @return true if the effect is active; false if it isn't active.
	 */
	public boolean isThreeDicesChangeOn() {
		return threeDicesChangeOn;
	}
	
	/**
	 * Method used to activate the effect of the "Ludovico il Moro" Leader card.
	 * @param value It's the new value that the family members belonging to the player who owns the 
	 * "Ludovico il Moro" Leader card will assume.
	 */
	private void setValue3dicesChanged(int value) {
		this.value3dicesChanged = value;
		threeDicesChangeOn=true;
	}
	
	/**
	 * Method that returns the updated value of the coloured family members belonging to the player who owns 
	 * the "Ludovico il Moro" Leader card
	 * @return the updated value of the coloured family members belonging to the player who owns 
	 * the "Ludovico il Moro" Leader card
	 */
	public int getValue3dicesChanged() {
		return value3dicesChanged;
	}
	
	
	
	/**
	 * Method that checks if the player has the "Ludovico il Moro" Leader card  and activates the corresponding permanent effects.
	 * @param howManyDicesSetted It indicates if the player has the "Ludovico il Moro" Leader card 
	 * (howManyDicesSetted==3) 
	 * @param value It's the new value that the family members belonging to the player who owns the 
	 * "Ludovico il Moro" Leader card will assume.
	 */
	
	public void setDicesSetted(int howManyDicesSetted, int value){
		if(howManyDicesSetted==3)
		 setValue3dicesChanged(value);
	}
	
	/**
	 * The following lines represent the effect of the "Sisto IV" Leader card. 
	 * The player who owns this card gains 5 additional Victory Points when he supports the Church in 
	 * a Vatican Report phase.
	 */

	int additionalVictoryPoints = 0;
	
	
	/**
	 * Method used to update the value of the additional Victory Points: if the player has supported the church,
	 * he will gain some additional Victory Points, expressed by the @param value 
	 * @param value It's the amount of additional Victory Points that the player will gain if he supported the church.
	 */
	public void gainAdditionalVP(int value) {
		additionalVictoryPoints = additionalVictoryPoints + value;
	}
	
	/**
	 * Method that returns the value of the additional Victory Points.
	 * @return
	 */
	public int getAdditionalVP() {
		return additionalVictoryPoints;
	}
	
	
}
