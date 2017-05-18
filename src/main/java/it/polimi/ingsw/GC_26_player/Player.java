package it.polimi.ingsw.GC_26_player;

import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembersSet;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;
import java.io.*;
import java.util.Scanner;
public class Player {
	private String name;
	private Warehouse warehouse;
	private PermanentModifiers permanentModifiers;
	Scanner scan = new Scanner(System.in );
	public String getName(){
		System.out.println("What's your name? ");
		name = scan.next();
		return name;
	}
	
	public FamilyMembersSet getFamilyMembers() {
		FamilyMembersSet familyMembersSet = new FamilyMembersSet();
		return familyMembersSet;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	public PermanentModifiers getPermanentModifiers() {
		return permanentModifiers;
		
		
		//è solo una bozza  :ho messo quello che mi serviva
	}

}
