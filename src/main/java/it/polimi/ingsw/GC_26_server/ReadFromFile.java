package it.polimi.ingsw.GC_26_server;

import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;
import it.polimi.ingsw.GC_26_readJson.TimerValuesInterface;

public  interface ReadFromFile {
	
	public void start();
	public Cards getCards() ;
	public TimerValuesInterface getTimes() ;
	
	public BonusInterface getBonus();
	

}
