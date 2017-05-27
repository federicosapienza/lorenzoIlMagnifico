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
	private PlayerStatus previousStatus;  
	 //previous status is used to keep memory of status before the status== VALIDATING; 
	//It' s needed in case of actions denied.
	private Warehouse testWarehouse;
	//temporaryWarehouse is used in some calculations, such as checking if the player can pay something or keeping track of resources earned in Production

	private final PersonalBoard personalBoard;
	private boolean playerActive; //set true if the player has at least tried to perform an action during the round.
	
	public Player(String name, ResourcesOrPoints startingResources) {
		this.name=name;
		familyMembers = new FamilyMembers();
		status= PlayerStatus.WAITINGHISTURN;
		warehouse= new Warehouse(startingResources);
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
	
	public PlayerStatus getStatus(){
		return status;
	}
	public boolean isPlayerActive() {
		return playerActive;
	}
	
	public PlayerStatus getPreviousStatus() {
		if (status != PlayerStatus.VALIDATING)
			throw new IllegalStateException();
		return previousStatus;
	}
	//setters methods
	
	public void setStatus(PlayerStatus status){
		if(status== PlayerStatus.VALIDATING)
			previousStatusSet(this.status);
		this.status = status;
	}
	private void previousStatusSet(PlayerStatus status){
		previousStatus = status;
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
	
	
	
	@Override
	public boolean equals(Object obj) {
		   if (this==obj) 
			   return true;
		   if (obj == null)
			   return false;
		   if (this.getClass() != obj.getClass()) 
			   return false;
		   Player other =(Player) obj;
		   if(name.equals(other.getName()))
			   return true;
		   else return false;
	}
		   
	@Override
    public void notifyObservers(Object object){  
        setChanged();
        super.notifyObservers( object);
	}
}
