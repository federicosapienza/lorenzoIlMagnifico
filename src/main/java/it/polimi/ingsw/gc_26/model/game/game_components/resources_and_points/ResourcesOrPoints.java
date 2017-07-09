package it.polimi.ingsw.gc_26.model.game.game_components.resources_and_points;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * ResourcesOrPoints is the class that handles the reference to all the payments and earnings.
 * Its attributes are objects of classes Points and Resources. Any attribute in these three classes is final. 
 * Any other part of the code will refer to this class: never to resources or points classes. This can be useful because generally 
 * effects can touch both points and resources.
 * 
 * It must not be mistaken with the Warehouse class, which instead represents the status of the player (in terms of points and 
 * resources), and whose attributes are mutable.
 */

public class ResourcesOrPoints{
	private final Resources resources; 
	private final Points points;
	
	
	/**
	 * Constructor: it creates a ResourcesOrPoints object with the Resources and the Points contained in the parameters
	 * @param resources the Resources of these ResourcesOrPoints
	 * @param points the Points of these ResourcesOrPoints
	 */
	private ResourcesOrPoints(Resources resources, Points points){ // factory methods preferred here due to multiplicity of possibles uses. 
		this.resources=resources;
		this.points=points;
	}
	
	/**
	 * Method called to create a new ResourcesOrPoints object with the resources and points contained in the parameters
	 * @param coins the coins of the new ResourcesOrPoints object
	 * @param servants the servants of the new ResourcesOrPoints object
	 * @param wood the wood of the new ResourcesOrPoints object
	 * @param stone the stones of the new ResourcesOrPoints object
	 * @param victoryP the Victory Points of the new ResourcesOrPoints object
	 * @param militaryP the Military Points of the new ResourcesOrPoints object
	 * @param faithP the Faith Points of the new ResourcesOrPoints object
	 * @param councilP the Council Privileges of the new ResourcesOrPoints object
	 * @return the new ResourcesOrPoints object
	 */
	public static ResourcesOrPoints newResourcesOrPoints(int coins, int servants, int wood, int stone, int victoryP, int militaryP, int faithP, int councilP){
		return new ResourcesOrPoints(new Resources(coins, servants, wood, stone), new Points(victoryP, militaryP, faithP, councilP));
		
	}
	
	/**
	 * Method called to create a new ResourcesOrPoints object, only with the resources contained in the parameters (every type of 
	 * Points is set to 0)
	 * @param coins the coins of the new ResourcesOrPoints object
	 * @param servants the servants of the new ResourcesOrPoints object
	 * @param wood the wood of the new ResourcesOrPoints object
	 * @param stone the stones of the new ResourcesOrPoints object
	 * @return the new ResourcesOrPoints object
	 */
	public static ResourcesOrPoints newResources(int coins, int servants, int wood, int stone){
		return newResourcesOrPoints(coins, servants, wood, stone, 0, 0, 0, 0);
	}
	
	/**
	 * Method called to create a new ResourcesOrPoints object, only with the Points contained in the parameters (every type of 
	 * Resources is set to 0)
	 * @param victoryP the Victory Points of the new ResourcesOrPoints object
	 * @param militaryP the Military Points of the new ResourcesOrPoints object
	 * @param faithP the Faith Points of the new ResourcesOrPoints object
	 * @param councilP the Council Privileges of the new ResourcesOrPoints object
	 * @return the new ResourcesOrPoints object
	 */
	public static ResourcesOrPoints newPoints(int victoryP, int militaryP, int faithP, int councilP){
		return newResourcesOrPoints(0, 0, 0, 0, victoryP, militaryP, faithP, councilP);
	}
	
	/**
	 * Method that returns the points of these ResourcesOrPoints object
	 * @return the points of these ResourcesOrPoints object
	 */
	public Points getPoints() {
		return points;
	}
	
	/**
	 * Method that returns the resources of these ResourcesOrPoints object
	 * @return the resources of these ResourcesOrPoints object
	 */
	public Resources getResources() {
		return resources;
	}
	
	/**
	 * Method that sums two ResourcesOrPoints objects, by adding each resource or point of the first to the corresponding one of 
	 * the second
	 * @param res1 the first ResourcesOrPoints object
	 * @param res2 the second ResourcesOrPoints object
	 * @return the sum of the two ResourcesOrPoints objects
	 */
	public static ResourcesOrPoints sum(ResourcesOrPoints res1, ResourcesOrPoints res2){
		if (res1 == null || res2 == null) {
			throw new NullPointerException();
		}
		//static method that sums the elements of two ResourcesOrPoints objects and returns a third one
		int coins = res1.getResources().getCoins()+res2.getResources().getCoins();
		int servants = res1.getResources().getServants()+res2.getResources().getServants();
		int stone = res1.getResources().getStone()+res2.getResources().getStone();
		int wood = res1.getResources().getWood()+res2.getResources().getWood();
		int militaryP = res1.getPoints().getMilitaryPoints()+res2.getPoints().getMilitaryPoints();
		int faithP = res1.getPoints().getFaithPoints()+res2.getPoints().getFaithPoints();
		int victoryP= res1.getPoints().getVictoryPoints()+res2.getPoints().getVictoryPoints();
		int councilP= res1.getPoints().getCouncilPrivileges()+res2.getPoints().getCouncilPrivileges();
		return newResourcesOrPoints(coins, servants, wood, stone, victoryP, militaryP, faithP, councilP);
	}
	
	/**
	 * Method that describes these ResourcesOrPoints as a String value
	 */
	@Override
	public String toString(){
		String none= "";
		StringBuilder temp = new StringBuilder(none);
		if(!resources.toString().equals(none))
			temp.append(resources.toString());
		 //in case both of them are zero this space will be the only digit printed 
		if(!points.toString().equals(none))
				temp.append(points.toString());
		//in case both of them are zero this space will be the only digit printed 
		if(resources.toString().equals(none) && points.toString().equals(none))
			temp.append(" ");
		return temp.toString();
	}
	
	/**
	 * Method that subtracts two ResourcesOrPoints objects, by subtracting each resource or point of the second from the 
	 * corresponding one of the second
	 * @param main the ResourcesOrPoints object whose resources and points are going to be subtracted
	 * @param subtrahend the ResourcesOrPoints object who is going to subtract the resources and points of the first 
	 * ResourcesOrPoints object
	 * @return the resulting ResourcesOrPoints object of the subtraction
	 */
	public static ResourcesOrPoints subtract(ResourcesOrPoints main, ResourcesOrPoints subtrahend){
		int coins = main.getResources().getCoins()-subtrahend.getResources().getCoins();
		int servants = main.getResources().getServants()-subtrahend.getResources().getServants();
		int stone = main.getResources().getStone()-subtrahend.getResources().getStone();
		int wood = main.getResources().getWood()-subtrahend.getResources().getWood();
		int militaryP = main.getPoints().getMilitaryPoints()-subtrahend.getPoints().getMilitaryPoints();
		int faithP = main.getPoints().getFaithPoints()-subtrahend.getPoints().getFaithPoints();
		int victoryP= main.getPoints().getVictoryPoints()-subtrahend.getPoints().getVictoryPoints();
		 //no Council Privileges (never used directly as price)
		return ResourcesOrPoints.newResourcesOrPoints(moreThanZero(coins), moreThanZero(servants), moreThanZero(wood), 
				moreThanZero(stone), moreThanZero(victoryP), moreThanZero(militaryP), moreThanZero(faithP), 
				main.getPoints().getCouncilPrivileges());
	}
	
	/**
	 * Method that checks if the test value contained in the parameter is greater or equal to 0. If the check is positive it returns the
	 * test value, else it returns 0 
	 * @param test the test value to check
	 * @return the test value if it is greater or equal to 0, else it returns 0 
	 */
	private static int moreThanZero(int test){
		//used only by newResourcesOrPointsDiscounted, to ensure no negative value
		if(test>= 0)
			return test;
		else return 0;
	
	}

	/**
	 * Method that returns the name of the player who is involved with these ResourcesOrPoints.
	 * Note: this is added in FamilyMembersDescriber class, it's useful in Warehouse, useless here.
	 * @return the name of the player who is involved with these ResourcesOrPoints
	 */
	public String getPlayerName() { //added in ResourcesDescriber interface, useful in Warehouse, useless here.
		return null;
	}

	/**
	 * Method that returns the coins of these ResourcesOrPoints
	 * @return the coins of these ResourcesOrPoints
	 */
	public int getCoins() {
		return resources.getCoins();
	}

	/**
	 * Method that returns the servants of these ResourcesOrPoints
	 * @return the servants of these ResourcesOrPoints
	 */
	public int getServants() {
		return resources.getServants();
	}

	/**
	 * Method that returns the stones of these ResourcesOrPoints
	 * @return the stones of these ResourcesOrPoints
	 */
	public int getStone() {
		return resources.getStone();
	}

	/**
	 * Method that returns the wood of these ResourcesOrPoints
	 * @return the wood of these ResourcesOrPoints
	 */
	public int getWood() {
		return resources.getWood();

	}

	/**
	 * Method that returns the Military Points of these ResourcesOrPoints
	 * @return the Military Points of these ResourcesOrPoints
	 */
	public int getMilitaryPoints() {
		return points.getMilitaryPoints();

	}

	/**
	 * Method that returns the Council Privileges of these ResourcesOrPoints
	 * @return the Council Privileges of these ResourcesOrPoints
	 */
	public int getCouncilPrivileges() {
		return points.getCouncilPrivileges();

	}

	/**
	 * Method that returns the Faith Points of these ResourcesOrPoints
	 * @return the Faith Points of these ResourcesOrPoints
	 */
	public int getFaithPoints() {
		return points.getFaithPoints();

	}

	/**
	 * Method that returns the Victory Points of these ResourcesOrPoints
	 * @return the Victory Points of these ResourcesOrPoints
	 */
	public int getVictoryPoints() {
		return points.getVictoryPoints();

	}
	
	@Override
	public boolean equals(Object obj) {
		    if (this == obj)                
		        return true;
		    if (obj == null)               
		        return false;
		    if (getClass() != obj.getClass())   
		        return false;
		    ResourcesOrPoints other = (ResourcesOrPoints) obj;               
		    return resources.equals(other.getResources()) && points.equals(other.getPoints());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
