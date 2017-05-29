package it.polimi.ingsw.GC_26_player;

import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;

import java.util.Observable;

import it.polimi.ingsw.GC_26_actionsHandlers.Action;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_personalBoard.PersonalBoard;
import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;

public class Player extends Observable{
	private final String name;
	private final Warehouse warehouse;
	private final PermanentModifiers permanentModifiers;
	private final FamilyMembers familyMembers;
	private PlayerStatus status;
	// private PlayerStatus previousStatus;  
	 //previous status is used to keep memory of status before the status== VALIDATING; 
	//It' s needed in case of actions denied.
	private Warehouse testWarehouse;
	//temporaryWarehouse is used in some calculations, such as checking if the player can pay something or keeping track of resources earned in Production

	private final PersonalBoard personalBoard;
	private boolean playerActive; //set true if the player has at least tried to perform an action during the round.
	// reached when a player has not performed an action 	
	
	private boolean secondaryAction=false;
	private ResourcesOrPoints discountOnSecondAction; 
	private Action typeOfSecondaryAction;
	private DevelopmentCard cardUsed; // pointer to the card used when the player is asked something, such as choosing payment or trade 
	private int secondActionValue;
	
	public Player(String name, ResourcesOrPoints startingResources) {
		this.name=name;
		familyMembers = new FamilyMembers(this);
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
	
/*	public PlayerStatus getPreviousStatus() {
		if (status != PlayerStatus.VALIDATING)
			throw new IllegalStateException();
		return previousStatus;} */
	
	//setters methods
	
	public void setStatus(PlayerStatus status){
	//	if(status== PlayerStatus.VALIDATING)
		//	previousStatusSet(this.status);
		
		//notificare il player di cambio stato (serve per modificare le view)!
		this.status = status;
	}
	
	public void setTemporaryWarehouse(){
		testWarehouse = new Warehouse(warehouse);}
	
	public void setPlayerActive() {
		this.playerActive = true;
	}
	
	public void endTurn(){
		playerActive=false;
	}
	
	public boolean isThereAsecondaryAction(){
		return secondaryAction;
	}
	public void setSecondaryAction(){
		secondaryAction = true;
	}
	public void setDiscountOnSecondAction(ResourcesOrPoints discountOnSecondAction) {
		this.discountOnSecondAction = discountOnSecondAction;
	}
	public ResourcesOrPoints getDiscountOnSecondAction() {
		return discountOnSecondAction;
	}
	public void setTypeOfSecondaryAction(Action typeOfSecondaryAction) {
		this.typeOfSecondaryAction = typeOfSecondaryAction;
	}
	public Action getTypeOfSecondaryAction() {
		return typeOfSecondaryAction;
	}
	public int getSecondactionValue() {
		return secondActionValue;
	}
	public void setSecondactionValue(int secondactionValue) {
		this.secondActionValue = secondactionValue;
	}
	
	public void resetSecondAction(){
		secondaryAction=false;
		discountOnSecondAction=null;
		typeOfSecondaryAction=null;
		secondActionValue=0;
	}
	
	public DevelopmentCard getCardUsed() {
		return cardUsed;
	}
	
	public void setCardUsed(DevelopmentCard cardUsed) {
		this.cardUsed = cardUsed;
	}
	
	public void resetCardUsed(DevelopmentCard cardUsed){
		cardUsed=null;
	}
	
	
		   
	@Override
    public void notifyObservers(Object object){  
        setChanged();
        super.notifyObservers( object);
	}
}
