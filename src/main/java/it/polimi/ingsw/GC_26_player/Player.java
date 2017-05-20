package it.polimi.ingsw.GC_26_player;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.rules.TemporaryFolder;

import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

public class Player {
	private String name;
	private Warehouse warehouse;
	private PermanentModifiers permanentModifiers;
	private FamilyMembers familyMembers;
	private PlayerStatus status;  // fed: introdotta per motivi che vi spiego di persona!
	private Warehouse temporaryWarehouse;
	
	public Player(String name) {
		this.name=name;
		familyMembers = new FamilyMembers();
		status= PlayerStatus.WAITINGHISTURN;
		

	}

	
	
	public FamilyMembers getFamilyMembers() {
		return familyMembers;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	public PermanentModifiers getPermanentModifiers() {
		return permanentModifiers;
	}
	
	public PlayerStatus getStatus(){
		return status;
	}
	
	public void setStatus(PlayerStatus status){
		 this.status =status;
	}



	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}



	public void endTurn() {
		// TODO Auto-generated method stub
		
	}

}
