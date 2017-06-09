package it.polimi.ingsw.GC_26_serverView;

import it.polimi.ingsw.GC_26_readJson.BonusInterface;
import it.polimi.ingsw.GC_26_readJson.Cards;

public  interface ReadFromFile {
	
	public void start();
	public Cards getCards() ;
	
	
	public BonusInterface getBonus();
	

}
