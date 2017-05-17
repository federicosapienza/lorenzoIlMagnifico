package it.polimi.ingsw.GC_26_board;

public abstract class Position {
	private final int value; //lo pongo public per far si che sia ereditabile da single position
	
	public Position(int value){
		this.value=value;
	}
	
	public int getValue(){
		return value;
	}
	
	public abstract void clear();
	public abstract void setFamilyMember();
}
