package it.polimi.ingsw.GC_26_utilities.dices;
import java.util.Random;

public class Dice {
	private Random gen;
	private int nrFaces;
	private Colour colour;
	private int value;
	public Dice(int nrFaces, Colour colour) //Costruttore che ha come parametro il numero di facce del dado
	{
		this.colour = colour;
		this.nrFaces = nrFaces;
		gen = new Random(); //Genero numeri casuali
	}
	public Dice() //Implementazione dado a 6 facce
	{
		nrFaces = 6;
		gen = new Random(); //Genero numeri casuali
	}
	public int throwDice() //Metodo che simula il lancio del dado
	{
		return value = 1 + gen.nextInt(nrFaces);
	}
	public int getValue() { 
		return value;
	}
}
