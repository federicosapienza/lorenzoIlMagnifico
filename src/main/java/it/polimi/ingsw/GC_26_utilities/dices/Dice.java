package it.polimi.ingsw.GC_26_utilities.dices;
import java.util.Random;

public class Dice {
	private Random gen;
	private int nrFaces;
	private Colour colour;
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
		return 1 + gen.nextInt(nrFaces);
	}
	public int readDice() { /*secondo me questo metodo non serve, la lettura è contenuta nella throwdices*/
		int diceValue;
		diceValue=throwDice();
		return diceValue;
	}
}
