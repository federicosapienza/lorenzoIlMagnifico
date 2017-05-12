package it.polimi.ingsw.GC_26_utilities.resourcesAndPoints;


// go to ResourcesOrPoints for explanation
public class Points {
	private final int victoryPoints;
	private final int militaryPoints;
	private final int faithPoints;
	private final int councilPrivileges;
	
	protected Points(int victoryP, int militaryP, int faithP, int councilP){
		this.victoryPoints=victoryP;
		this.militaryPoints=militaryP;
		this.faithPoints=faithP;
		this.councilPrivileges=councilP;
	}

	public int getVictoryPoints() {
		return victoryPoints;
	}

	public int getMilitaryPoints() {
		return militaryPoints;
	}

	public int getFaithPoints() {
		return faithPoints;
	}

	public int getCouncilPrivileges() {
		return councilPrivileges;
	}
	
	
	@Override
	public String toString(){     //returns only the non 0 fields.
		StringBuilder temp =new StringBuilder();
		if(victoryPoints!=0)
				temp.append(victoryPoints + " victory points.");
		if(militaryPoints!=0)
			temp.append(militaryPoints + " military points.");
		if(faithPoints!=0)
			temp.append(faithPoints + " faith points.");
		if(councilPrivileges!=0)
			temp.append(councilPrivileges + " council privileges.");
		return temp.toString(); 
	}

	
}
