package it.polimi.ingsw.GC_26_readJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;

public class CardsImplementation implements Cards {
	
	private List<DevelopmentCard> territoryCardsPeriod1 = new ArrayList<>();
	private List<DevelopmentCard> territoryCardsPeriod2 = new ArrayList<>();
	private List<DevelopmentCard> territoryCardsPeriod3 = new ArrayList<>();
	
	private List<DevelopmentCard> buildingCardsPeriod1 = new ArrayList<>();
	private List<DevelopmentCard> buildingCardsPeriod2 = new ArrayList<>();
	private List<DevelopmentCard> buildingCardsPeriod3 = new ArrayList<>();
	
	private List<DevelopmentCard> characterCardsPeriod1 = new ArrayList<>();
	private List<DevelopmentCard> characterCardsPeriod2 = new ArrayList<>();
	private List<DevelopmentCard> characterCardsPeriod3 = new ArrayList<>();
	
	private List<DevelopmentCard> ventureCardsPeriod1 = new ArrayList<>();
	private List<DevelopmentCard> ventureCardsPeriod2 = new ArrayList<>();
	private List<DevelopmentCard> ventureCardsPeriod3 = new ArrayList<>();
	
	private List<LeaderCard> leaderCard = new ArrayList<>();
	private List<LeaderCard> leaderCardTemp = new ArrayList<>();
	
	private List<ExcommunicationTile> excommunicationTilesPeriod1 = new ArrayList<>();
	private List<ExcommunicationTile> excommunicationTilesPeriod2 = new ArrayList<>();
	private List<ExcommunicationTile> excommunicationTilesPeriod3 = new ArrayList<>();
	private List<ExcommunicationTile> excommunicationTilesTemp = new ArrayList<>();

	
	

	@Override
	public List<DevelopmentCard> getRandomDevelopmentCards(int period, DevelopmentCardTypes type) {
		switch (period) {
		case 1:
			switch (type) {
			case TERRITORYCARD:
				Collections.shuffle(territoryCardsPeriod1);
				return territoryCardsPeriod1;
			case CHARACTERCARD:
				Collections.shuffle(characterCardsPeriod1);
				return characterCardsPeriod1;
			case BUILDINGCARD:
				Collections.shuffle(buildingCardsPeriod1);
				return buildingCardsPeriod1;
			case VENTURECARD:
				Collections.shuffle(ventureCardsPeriod1);
				return ventureCardsPeriod1;
			default:
				break;
			}
			break;
		case 2:
			switch (type) {
			case TERRITORYCARD:
				Collections.shuffle(territoryCardsPeriod2);
				return territoryCardsPeriod2;
			case CHARACTERCARD:
				Collections.shuffle(characterCardsPeriod2);
				return characterCardsPeriod2;
			case BUILDINGCARD:
				Collections.shuffle(buildingCardsPeriod2);
				return buildingCardsPeriod2;
			case VENTURECARD:
				Collections.shuffle(ventureCardsPeriod2);
				return ventureCardsPeriod2;
			default:
				break;
			}
			break;
		case 3:
			switch (type) {
			case TERRITORYCARD:
				Collections.shuffle(territoryCardsPeriod3);
				return territoryCardsPeriod3;
			case CHARACTERCARD:
				Collections.shuffle(characterCardsPeriod3);
				return characterCardsPeriod3;
			case BUILDINGCARD:
				Collections.shuffle(buildingCardsPeriod3);
				return buildingCardsPeriod3;
			case VENTURECARD:
				Collections.shuffle(ventureCardsPeriod3);
				return ventureCardsPeriod3;
			default:
				break;
			}
			break;
		default:
			break;
		}
		return Collections.emptyList();
	}
	
	
	
	public List<DevelopmentCard> getDevelopmentCards(int period, DevelopmentCardTypes type) {
		switch (period) {
		case 1:
			switch (type) {
			case TERRITORYCARD:
				return territoryCardsPeriod1;
			case CHARACTERCARD:
				return characterCardsPeriod1;
			case BUILDINGCARD:
				return buildingCardsPeriod1;
			case VENTURECARD:
				return ventureCardsPeriod1;
			default:
				break;
			}
			break;
		case 2:
			switch (type) {
			case TERRITORYCARD:
				return territoryCardsPeriod2;
			case CHARACTERCARD:
				return characterCardsPeriod2;
			case BUILDINGCARD:
				return buildingCardsPeriod2;
			case VENTURECARD:
				return ventureCardsPeriod2;
			default:
				break;
			}
			break;
		case 3:
			switch (type) {
			case TERRITORYCARD:
				return territoryCardsPeriod3;
			case CHARACTERCARD:
				return characterCardsPeriod3;
			case BUILDINGCARD:
				return buildingCardsPeriod3;
			case VENTURECARD:
				return ventureCardsPeriod3;
			default:
				break;
			}
			break;
		default:
			break;
		}
		return Collections.emptyList();
	}
	
	@Override
	public List<LeaderCard> getRandomLeaderCards(int numOfPlayers) {
		Collections.shuffle(leaderCard);
		switch (numOfPlayers) {
		case 2:
			for(int i=0; i < 8; i++){
				leaderCardTemp.add(leaderCard.get(i));
			}
			return leaderCardTemp;
		case 3:
			for(int i=0; i < 12; i++){
				leaderCardTemp.add(leaderCard.get(i));
			}
			return leaderCardTemp;
		case 4:
			for(int i=0; i < 16; i++){
				leaderCardTemp.add(leaderCard.get(i));
			}
			return leaderCardTemp;
		default:
			break;
		}
		return Collections.emptyList();
		}
	
	public List<LeaderCard> getLeaderCards(){
		return leaderCard;
	}

	@Override
	public List<ExcommunicationTile> getRandomExcommunicationTiles() {
		Collections.shuffle(excommunicationTilesPeriod1);
		excommunicationTilesTemp.add(excommunicationTilesPeriod1.get(0));
		Collections.shuffle(excommunicationTilesPeriod2);
		excommunicationTilesTemp.add(excommunicationTilesPeriod2.get(0));
		Collections.shuffle(excommunicationTilesPeriod3);
		excommunicationTilesTemp.add(excommunicationTilesPeriod3.get(0));
		return excommunicationTilesTemp;
		}
	
	public List<ExcommunicationTile> getExcommunicationTiles(int period){
		switch (period) {
		case 1:
			return excommunicationTilesPeriod1;
		case 2:
			return excommunicationTilesPeriod2;
		case 3:
			return excommunicationTilesPeriod3;
		default:
			return Collections.emptyList();
		}
	}
	
	

}
