package it.polimi.ingsw.GC_26_readJson;

public class ReadAll {
	private TerritoryCardsReader territoryCardsReader = new TerritoryCardsReader();
	private CardsImplementation cards= new CardsImplementation();

	public static void main(String[] args) {
		ReadAll readAll= new ReadAll();
		readAll.readDevelopmentCards();
	}
	
	public void readDevelopmentCards(){
		territoryCardsReader.readCards(1,cards);
		territoryCardsReader.readCards(2,cards);
		territoryCardsReader.readCards(3,cards);
	}

}
