package it.polimi.ingsw.GC_26.jsonReader;


import it.polimi.ingsw.GC_26.server.ReadFromFile;

public class ReadAll implements ReadFromFile{
	private TerritoryCardsReader territoryCardsReader = new TerritoryCardsReader();
	private BuildingCardsReader buildingCardsReader = new BuildingCardsReader();
	private CharacterCardsReader characterCardsReader = new CharacterCardsReader();
	private VentureCardsReader ventureCardsReader = new VentureCardsReader();
	private LeaderCardsReader leaderCardsReader = new LeaderCardsReader();
	private ExcommunicationTilesReader excommunicationTilesReader = new ExcommunicationTilesReader();
	private BoardResourcesAndStartingPlayerResourcesReader boardResourcesAndStartingPlayerResourcesReader = new BoardResourcesAndStartingPlayerResourcesReader();
	private CardsImplementation cards= new CardsImplementation();
	private BonusImplementation bonus = new BonusImplementation();
	private TimerValueImplementation timer = new TimerValueImplementation();
	
	
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
		
		leaderCardsReader.readCards(cards);
		
		boardResourcesAndStartingPlayerResourcesReader.readResources(bonus);
		boardResourcesAndStartingPlayerResourcesReader.readStartingPlayerResources(bonus);
		boardResourcesAndStartingPlayerResourcesReader.readFaithTrack(bonus);
		boardResourcesAndStartingPlayerResourcesReader.readPersonalBoardTiles(bonus, "advanced");
		boardResourcesAndStartingPlayerResourcesReader.readTimers(timer);
		
		excommunicationTilesReader.readCards(1, cards);
		excommunicationTilesReader.readCards(2, cards);
		excommunicationTilesReader.readCards(3, cards);
		
		
	}
	
	@Override
	public Cards getCards() {
		return  cards;
	}
	
	@Override
	public BonusInterface getBonus() {
		return bonus;
	}
	
	@Override
	public TimerValuesInterface getTimes() {
		return timer;
	}
}
