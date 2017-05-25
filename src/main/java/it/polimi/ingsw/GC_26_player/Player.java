package it.polimi.ingsw.GC_26_player;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;
import java.util.Observable;
import it.polimi.ingsw.GC_26_personalBoard.PersonalBoard;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;

public class Player extends Observable{
	private final String name;
	private final Warehouse warehouse;
	private final PermanentModifiers permanentModifiers;
	private final FamilyMembers familyMembers;
	private PlayerStatus status;  
	private Warehouse testWarehouse;
	//temporaryWarehouse is used in some calculations, such as checking if the player can pay something or keeping track of resources earned in Production
	private boolean productionDone;
	private boolean harvestDone;
	private final PersonalBoard personalBoard;
	private boolean playerActive; //set true if the player has at least tried to perform an action during the round.
	
	public Player(String name, ResourcesOrPoints startingResources) {
		this.name=name;
		familyMembers = new FamilyMembers();
		status= PlayerStatus.WAITINGHISTURN;
		warehouse= new Warehouse(startingResources);
		productionDone=false;
		harvestDone= false;
		personalBoard= new PersonalBoard();
		permanentModifiers = new PermanentModifiers(this);
		playerActive= false;

	}
	
	//getters methods
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
	
	public Warehouse getTestWarehouse() {
		return testWarehouse;
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
	public boolean isPlayerActive() {
		return playerActive;
	}
	
	//setters methods
	public void setProductionDone() {
		this.productionDone = true;;
	}
	public void setHarvestDone(){
		harvestDone= true;
	}
	public void setStatus(PlayerStatus status){
		this.status = status;
	}

	public void setTemporaryWarehouse(){
		testWarehouse = new Warehouse(warehouse);
	}
	public void setPlayerActive() {
		this.playerActive = true;
	}
	
	public void endTurn(){
		playerActive=false;
	}
	
	
	public void endRound() {
		//cleans parameters.
		harvestDone= false;
		productionDone= false;
	}
	
	
	
	@Override
    public void notifyObservers(Object object){  
        setChanged();
        super.notifyObservers( object);
	}
}
