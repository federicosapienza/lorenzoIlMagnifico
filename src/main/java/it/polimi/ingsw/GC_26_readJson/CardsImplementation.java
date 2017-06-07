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
	
	public List<DevelopmentCard> getTerritoryCardsPeriod1() {
		return territoryCardsPeriod1;
	}

	public void setTerritoryCardsPeriod1(List<DevelopmentCard> territoryCardsPeriod1) {
		this.territoryCardsPeriod1 = territoryCardsPeriod1;
	}

	public List<DevelopmentCard> getTerritoryCardsPeriod2() {
		return territoryCardsPeriod2;
	}

	public void setTerritoryCardsPeriod2(List<DevelopmentCard> territoryCardsPeriod2) {
		this.territoryCardsPeriod2 = territoryCardsPeriod2;
	}

	public List<DevelopmentCard> getTerritoryCardsPeriod3() {
		return territoryCardsPeriod3;
	}

	public void setTerritoryCardsPeriod3(List<DevelopmentCard> territoryCardsPeriod3) {
		this.territoryCardsPeriod3 = territoryCardsPeriod3;
	}

	public List<DevelopmentCard> getBuildingCardsPeriod1() {
		return buildingCardsPeriod1;
	}

	public void setBuildingCardsPeriod1(List<DevelopmentCard> buildingCardsPeriod1) {
		this.buildingCardsPeriod1 = buildingCardsPeriod1;
	}

	public List<DevelopmentCard> getBuildingCardsPeriod2() {
		return buildingCardsPeriod2;
	}

	public void setBuildingCardsPeriod2(List<DevelopmentCard> buildingCardsPeriod2) {
		this.buildingCardsPeriod2 = buildingCardsPeriod2;
	}

	public List<DevelopmentCard> getBuildingCardsPeriod3() {
		return buildingCardsPeriod3;
	}

	public void setBuildingCardsPeriod3(List<DevelopmentCard> buildingCardsPeriod3) {
		this.buildingCardsPeriod3 = buildingCardsPeriod3;
	}

	public List<DevelopmentCard> getCharacterCardsPeriod1() {
		return characterCardsPeriod1;
	}

	public void setCharacterCardsPeriod1(List<DevelopmentCard> characterCardsPeriod1) {
		this.characterCardsPeriod1 = characterCardsPeriod1;
	}

	public List<DevelopmentCard> getCharacterCardsPeriod2() {
		return characterCardsPeriod2;
	}

	public void setCharacterCardsPeriod2(List<DevelopmentCard> characterCardsPeriod2) {
		this.characterCardsPeriod2 = characterCardsPeriod2;
	}

	public List<DevelopmentCard> getCharacterCardsPeriod3() {
		return characterCardsPeriod3;
	}

	public void setCharacterCardsPeriod3(List<DevelopmentCard> characterCardsPeriod3) {
		this.characterCardsPeriod3 = characterCardsPeriod3;
	}

	public List<DevelopmentCard> getVentureCardsPeriod1() {
		return ventureCardsPeriod1;
	}

	public void setVentureCardsPeriod1(List<DevelopmentCard> ventureCardsPeriod1) {
		this.ventureCardsPeriod1 = ventureCardsPeriod1;
	}

	public List<DevelopmentCard> getVentureCardsPeriod2() {
		return ventureCardsPeriod2;
	}

	public void setVentureCardsPeriod2(List<DevelopmentCard> ventureCardsPeriod2) {
		this.ventureCardsPeriod2 = ventureCardsPeriod2;
	}

	public List<DevelopmentCard> getVentureCardsPeriod3() {
		return ventureCardsPeriod3;
	}

	public void setVentureCardsPeriod3(List<DevelopmentCard> ventureCardsPeriod3) {
		this.ventureCardsPeriod3 = ventureCardsPeriod3;
	}

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
