package it.polimi.ingsw.GC_26.server;

import it.polimi.ingsw.GC_26.jsonReader.BonusInterface;
import it.polimi.ingsw.GC_26.jsonReader.Cards;
import it.polimi.ingsw.GC_26.jsonReader.TimerValuesInterface;

public  interface ReadFromFile {
	
	public void start();
	public Cards getCards() ;
	public TimerValuesInterface getTimes() ;
	
	public BonusInterface getBonus();
	

}
