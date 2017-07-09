package it.polimi.ingsw.GC_26.model.game.gameComponents.resourcesAndPoints;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the Points of the game
 *
 */

public class Points {
	private final int victoryPoints;
	private final int militaryPoints;
	private final int faithPoints;
	private final int councilPrivileges;
	
	/**
	 * Constructor: it creates a Points object that contains the Victory Points, the Military Points, the Faith Points and the Council Privileges
	 * @param victoryP the Victory Points
	 * @param militaryP the Military Points
	 * @param faithP the Faith Points
	 * @param councilP the Council Privileges
	 */
	protected Points(int victoryP, int militaryP, int faithP, int councilP){
		this.victoryPoints=victoryP;
		this.militaryPoints=militaryP;
		this.faithPoints=faithP;
		this.councilPrivileges=councilP;
	}

	/**
	 * Method that returns the Victory Points of these Points
	 * @return the Victory Points of these Points
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}

	/**
	 * Method that returns the Military Points of these Points
	 * @return the Military Points of these Points
	 */
	public int getMilitaryPoints() {
		return militaryPoints;
	}

	/**
	 * Method that returns the Faith Points of these Points
	 * @return the Faith Points of these Points
	 */
	public int getFaithPoints() {
		return faithPoints;
	}

	/**
	 * Method that returns the Council Privileges of these Points
	 * @return the Council Privileges of these Points
	 */
	public int getCouncilPrivileges() {
		return councilPrivileges;
	}
	
	/**
	 * Method that describes the Points as a String value
	 */
	@Override
	public String toString(){     //returns only the non 0 fields.
		StringBuilder temp = new StringBuilder("");
		if(victoryPoints!=0)
				 temp.append(victoryPoints + " victory points ");
		if(militaryPoints!=0)
			temp= temp.append(militaryPoints + " military points ");
		if(faithPoints!=0)
			temp= temp.append(faithPoints + " faith points ");
		if(councilPrivileges!=0)
			temp= temp.append(councilPrivileges + " council privileges ");
		return temp.toString(); 
	}

	@Override
	public boolean equals(Object obj) {
		    if (this == obj)                
		        return true;
		    if (obj == null)               
		        return false;
		    if (getClass() != obj.getClass())   
		        return false;
		    Points other = (Points) obj;               
		    if (militaryPoints != other.getMilitaryPoints())        
		        return false;                
		    if(victoryPoints!=other.getVictoryPoints())
		    	return false;
		    if (faithPoints != other.getFaithPoints())        
		        return false;                
		    if(councilPrivileges!=other.getCouncilPrivileges())
		    	return false;
		    return true; 
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
