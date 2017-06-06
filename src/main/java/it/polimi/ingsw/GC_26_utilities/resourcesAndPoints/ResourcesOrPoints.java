package it.polimi.ingsw.GC_26_utilities.resourcesAndPoints;


/*ResourcesOrPoints is the class the handles the reference to all the payments and earnings; 
 * Its attributes are objects of classes Points and Resources. Any attribute in these three classes is final. 
 * Any other part of the code will refer to this class: never to resources or points classes: this can be useful because in 
 * a general case effects can touch both points and resources.
 * 
 * It must not be mistaken with Warehouse , which instead represent the status of the player (points and resources) , and whose 
 * attributes are mutable.
 */
public class ResourcesOrPoints implements PlayerWallet{
	private final Resources resources; 
	private final Points points;
	
	
	// constructors
	private ResourcesOrPoints(Resources resources, Points points){ // factory methods preferred here due to multiplicity of possibles uses. 
		this.resources=resources;
		this.points=points;
	}

	public static ResourcesOrPoints newResourcesOrPoints(int coins, int servants, int wood, int stone, int victoryP, int militaryP, int faithP, int councilP){
		return new ResourcesOrPoints(new Resources(coins, servants, wood, stone), new Points(victoryP, militaryP, faithP, councilP));
		
	}
	
	public static ResourcesOrPoints newResources(int coins, int servants, int wood, int stone){
		return newResourcesOrPoints(coins, servants, wood, stone, 0, 0, 0, 0);
	}
	
	public static ResourcesOrPoints newPoints(int victoryP, int militaryP, int faithP, int councilP){
		return newResourcesOrPoints(0, 0, 0, 0, victoryP, militaryP, faithP, councilP);
	}
	
	public Points getPoints() {
		return points;
	}
	
	public Resources getResources() {
		return resources;
	}
	
	
	public static  ResourcesOrPoints sum(ResourcesOrPoints res1, ResourcesOrPoints res2){
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
	
			
	@Override
	public String toString(){
		return resources.toString() + " "+ points.toString()+ " ";
	}
	
	public static  ResourcesOrPoints subtract(ResourcesOrPoints main , ResourcesOrPoints subtrahend){
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
	private static int moreThanZero(int test){
		//used only by newResourcesOrPointsDiscounted, to ensure no negative value
		if(test>= 0)
			return test;
		else return 0;
	
	}

	@Override
	public String getPlayerName() { //added in ResourcesDescriber interface, useful in Warehouse, useless here.
		return null;
	}

	@Override
	public int getCoins() {
		return resources.getCoins();
	}

	@Override
	public int getServants() {
		return resources.getServants();
	}

	@Override
	public int getStone() {
		return resources.getStone();
	}

	@Override
	public int getWood() {
		return resources.getWood();

	}

	@Override
	public int getMilitaryPoints() {
		return points.getMilitaryPoints();

	}

	@Override
	public int getCouncilPrivileges() {
		return points.getCouncilPrivileges();

	}

	@Override
	public int getFaithPoints() {
		return points.getFaithPoints();

	}

	@Override
	public int getVictoryPoints() {
		return points.getVictoryPoints();

	}
}
