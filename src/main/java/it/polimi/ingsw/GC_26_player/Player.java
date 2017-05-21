package it.polimi.ingsw.GC_26_player;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.rules.TemporaryFolder;

import it.polimi.ingsw.GC_26_personalBoard.PersonalBoard;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

public class Player {
	private String name;
	private Warehouse warehouse;
	private PermanentModifiers permanentModifiers;
	private FamilyMembers familyMembers;
	private PlayerStatus status;  // fed: introdotta per motivi che vi spiego di persona!
	private Warehouse temporaryWarehouse; // fed: la sistemo io: ti devo spiegare a cosa servirà
	private boolean harvestDone;
	private boolean productionDone;
	private PersonalBoard personalBoard;
	
	public Player(String name) {
		this.name=name;
		familyMembers = new FamilyMembers();
		status= PlayerStatus.WAITINGHISTURN;
		

	}

	public String getName() {
		return this.name;
	}
	
	public FamilyMembers getFamilyMembers() {
		return familyMembers;
	}
	
	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	public PermanentModifiers getPermanentModifiers() {
		return permanentModifiers;
	}
	
	public boolean hasDoneProduction() {
		return this.productionDone;
	}
	public boolean hasDoneHarvest() {
		return this.harvestDone;
	}
	public PlayerStatus getStatus(){
		return status;
	}
	
	public void setStatus(PlayerStatus status){
		this.status = status;
	}

	

	

	public void endTurn() {
		//TODO pulisce la temporaryWarehouse e altri parametri che credo dovremo mettere: (per il momento lasciala perdere
		
	}

}
