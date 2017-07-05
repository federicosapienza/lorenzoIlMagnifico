package it.polimi.ingsw.GC_26.server;

import it.polimi.ingsw.GC_26.jsonReader.BonusInterface;
import it.polimi.ingsw.GC_26.jsonReader.Cards;
import it.polimi.ingsw.GC_26.jsonReader.TimerValuesInterface;

/**
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This interface is needed to read the game elements from the file
 *
 */
public interface ReadFromFile {
	
	public void start();
	public Cards getCards() ;
	public TimerValuesInterface getTimes() ;
	
	public BonusInterface getBonus();
	

}
