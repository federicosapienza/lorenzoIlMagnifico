package it.polimi.ingsw.GC_26_player;
<<<<<<< HEAD
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;
import it.polimi.ingsw.GC_26_utilities.familyMembers.*;
import java.io.*;
import java.util.Scanner;
=======

import it.polimi.ingsw.GC_26_utilities.familyMembers.FamilyMembers;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;
>>>>>>> fcfd1c1935be0eb0dad88e215df8ff8270013bc4
public class Player {
	private String name;
	private Warehouse warehouse;
	private PermanentModifiers permanentModifiers;
<<<<<<< HEAD
	Scanner scanner = new Scanner(System.in );
	public String getName(){
		System.out.println("What's your name?");
		name = scanner.nextLine();
		scanner.close();
		return name;
=======
	private FamilyMembers familyMembers;
	public Player(String name) {
		this.name=name;
		familyMembers = new FamilyMembers();
>>>>>>> fcfd1c1935be0eb0dad88e215df8ff8270013bc4
	}

	
	
	public FamilyMembers getFamilyMembers() {
<<<<<<< HEAD
		FamilyMembers familyMembers = new FamilyMembers();
=======
>>>>>>> fcfd1c1935be0eb0dad88e215df8ff8270013bc4
		return familyMembers;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	public PermanentModifiers getPermanentModifiers() {
		return permanentModifiers;
	}

}
