package it.polimi.ingsw.GC_26_readJson;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;

public class CardsImplementation implements Cards {
	
	private List<DevelopmentCard> territoryCardsPeriod1 = new ArrayList<DevelopmentCard>();
	private List<DevelopmentCard> territoryCardsPeriod2 = new ArrayList<DevelopmentCard>();
	private List<DevelopmentCard> territoryCardsPeriod3 = new ArrayList<DevelopmentCard>();
	private List<DevelopmentCard> buildingCardsPeriod1 = new ArrayList<DevelopmentCard>();
	private List<DevelopmentCard> buildingCardsPeriod2 = new ArrayList<DevelopmentCard>();
	private List<DevelopmentCard> buildingCardsPeriod3 = new ArrayList<DevelopmentCard>();
	private List<DevelopmentCard> characterCardsPeriod1 = new ArrayList<DevelopmentCard>();
	private List<DevelopmentCard> characterCardsPeriod2 = new ArrayList<DevelopmentCard>();
	private List<DevelopmentCard> characterCardsPeriod3 = new ArrayList<DevelopmentCard>();
	private List<DevelopmentCard> ventureCardsPeriod1 = new ArrayList<DevelopmentCard>();
	private List<DevelopmentCard> ventureCardsPeriod2 = new ArrayList<DevelopmentCard>();
	private List<DevelopmentCard> ventureCardsPeriod3 = new ArrayList<DevelopmentCard>();
	
	@Override
	public List<DevelopmentCard> getRandomDevelopmentCards(int period, DevelopmentCardTypes type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LeaderCard> getRandomLeaderCards(int numOfPlayers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExcommunicationTile> getExcommunicationTiles() {
		// TODO Auto-generated method stub
		return null;
	}

}
