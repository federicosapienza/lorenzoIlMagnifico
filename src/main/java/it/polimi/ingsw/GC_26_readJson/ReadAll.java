package it.polimi.ingsw.GC_26_readJson;

import it.polimi.ingsw.GC_26_serverView.ReadFromFile;

public class ReadAll implements ReadFromFile{
	private TerritoryCardsReader territoryCardsReader = new TerritoryCardsReader();
	private BuildingCardsReader buildingCardsReader = new BuildingCardsReader();
	private CharacterCardsReader characterCardsReader = new CharacterCardsReader();
	private VentureCardsReader ventureCardsReader = new VentureCardsReader();
//	private BoardResourcesAndStartingPlayerResourcesReader boardResourcesAndStartingPlayerResourcesReader = new BoardResourcesAndStartingPlayerResourcesReader();
	private CardsImplementation cards= new CardsImplementation();
	private BonusImplementation bonus = new BonusImplementation();
	
	
	public static void main(String[] args) {
		ReadAll readAll= new ReadAll();
		readAll.start();
	}
	
	public void start(){
		territoryCardsReader.readCards(1,cards);
		territoryCardsReader.readCards(2,cards);
		territoryCardsReader.readCards(3,cards);
		
		buildingCardsReader.readCards(1, cards);
		buildingCardsReader.readCards(2, cards);
		buildingCardsReader.readCards(3, cards);
		
		characterCardsReader.readCards(1, cards);
		characterCardsReader.readCards(2, cards);
		characterCardsReader.readCards(3, cards);
		
		ventureCardsReader.readCards(1, cards);
		ventureCardsReader.readCards(2, cards);
		ventureCardsReader.readCards(3, cards);
		
		//boardResourcesAndStartingPlayerResourcesReader.readResources(bonus);
		//boardResourcesAndStartingPlayerResourcesReader.readStartingPlayerResources(bonus);
		//boardResourcesAndStartingPlayerResourcesReader.readFaithTrack(bonus);
	}
	
	public Cards getCards() {
		return  cards;
	}
	
	public BonusInterface getBonus() {
		return bonus;
	}
}
