package it.polimi.ingsw.GC_26_player;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.Warehouse;
import it.polimi.ingsw.GC_26_utilities.familyMembers.*;
import java.io.*;
import java.util.Scanner;
public class Player {
	private String name;
	private Warehouse warehouse;
	private PermanentModifiers permanentModifiers;
	Scanner scanner = new Scanner(System.in );
	public String getName(){
		System.out.println("What's your name?");
		name = scanner.nextLine();
		scanner.close();
		return name;
	}
	
	public FamilyMembers getFamilyMembers() {
		FamilyMembers familyMembers = new FamilyMembers();
		return familyMembers;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	
	public PermanentModifiers getPermanentModifiers() {
		return permanentModifiers;
	}

}
