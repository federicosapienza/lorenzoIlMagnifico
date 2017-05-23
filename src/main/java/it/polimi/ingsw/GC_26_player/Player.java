package it.polimi.ingsw.GC_26_player;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

import javax.net.ssl.SSLEngineResult.Status;
import javax.print.attribute.standard.RequestingUserName;

import org.junit.rules.TemporaryFolder;

import it.polimi.ingsw.GC_26_personalBoard.PersonalBoard;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

public class Player {
	private final String name;
	private final Warehouse warehouse;
	private final PermanentModifiers permanentModifiers;
	private final FamilyMembers familyMembers;
	private PlayerStatus status;  // fed: introdotta per motivi che vi spiego di persona!
	private Warehouse temporaryWarehouse;
	//temporaryWarehouse is used in some calculations, such as checking if the player can pay something or keeping track of resources earned in Production
	private boolean productionDone;
	private boolean harvestDone;
	private final PersonalBoard personalBoard;
	
	public Player(String name, ResourcesOrPoints startingResources) {
		this.name=name;
		familyMembers = new FamilyMembers();
		status= PlayerStatus.WAITINGHISTURN;
		warehouse= new Warehouse(startingResources);
		productionDone=false;
		harvestDone= false;
		personalBoard= new PersonalBoard();
		permanentModifiers = new PermanentModifiers(this);

	}
	
	public void setProductionDone() {
		this.productionDone = true;;
	}
	public void setHarvestDone(){
		harvestDone= true;
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
	
	public Warehouse getTemporaryWarehouse() {
		return temporaryWarehouse;
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

	public void setTemporaryWarehouse(){
		temporaryWarehouse = new Warehouse(warehouse);
	}
	
	public void endRound() {
		//TODO pulisce la temporaryWarehouse e altri parametri che credo dovremo mettere: (per il momento lasciala perdere
		harvestDone= false;
		productionDone= false;
	}

}
