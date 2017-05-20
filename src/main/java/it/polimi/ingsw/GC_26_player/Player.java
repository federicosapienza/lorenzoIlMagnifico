package it.polimi.ingsw.GC_26_player;

import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;
public class Player {
	private String name;
	private Warehouse warehouse;
	private PermanentModifiers permanentModifiers;
	private FamilyMembers familyMembers;
	public Player(String name) {
		this.name=name;
		familyMembers = new FamilyMembers();
	}

	
	
	public FamilyMembers getFamilyMembers() {
		return familyMembers;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	public PermanentModifiers getPermanentModifiers() {
		return permanentModifiers;
		
		
		//è solo una bozza  :ho messo quello che mi serviva
	}

}
