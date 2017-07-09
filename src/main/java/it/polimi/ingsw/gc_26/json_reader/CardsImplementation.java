package it.polimi.ingsw.gc_26.json_reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.leaderCard.LeaderCard;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class implements the Cards interface; it contains all the implementation cards
 *
 */
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

	/**
	 * Method that shuffles and returns the cards which belong to the period and to the type contained in the parameters
	 * @param period It's the period of the cards that have to be shuffled and returned
	 * @param type It's the type of the Development Cards that have to be shuffled and returned 
	 */
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
	
	/**
	 * Method that returns the List of Development Cards expressed by the period and the type contained in the parameter
	 * @param period It's the period of the cards that have to be returned
	 * @param type It's the type of the Development Cards that have to be returned 
	 * @return the Development Cards that correspond to the period and the type expressed in the parameter
	 */
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
	
	/**
	 * Method that returns a random List of Leader Cards, whose size depends on the number of players expressed in the parameter
	 * @param numOfPlayers It's the number of players who are playing the game
	 */
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

	/**
	 * Method that returns a random List of Excommunication Tiles
	 *
	 */
	@Override
	public List<ExcommunicationTile> getRandomExcommunicationTiles() {
		List<ExcommunicationTile> excommunicationTilesTemp = new ArrayList<ExcommunicationTile>();
		Collections.shuffle(excommunicationTilesPeriod1);
		excommunicationTilesTemp.add(excommunicationTilesPeriod1.get(0));
		Collections.shuffle(excommunicationTilesPeriod2);
		excommunicationTilesTemp.add(excommunicationTilesPeriod2.get(0));
		Collections.shuffle(excommunicationTilesPeriod3);
		excommunicationTilesTemp.add(excommunicationTilesPeriod3.get(0));
		return excommunicationTilesTemp;
	}
	
	/**
	 * Method that returns the Excommunication Tile that corresponds to the period indicated in the parameter
	 * @param period It's the number of period whose Excommunication Tile has to be returned
	 * @return Excommunication Tile of the period indicated in the parameter
	 */
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
