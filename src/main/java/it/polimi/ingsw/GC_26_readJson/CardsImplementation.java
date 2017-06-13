package it.polimi.ingsw.GC_26_readJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.effects.MarketBanEffect;
import it.polimi.ingsw.GC_26_cards.effects.SetSecondAction;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTileImplementation;
import it.polimi.ingsw.GC_26_cards.leaderCard.CardNumbersRequirement;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCard;
import it.polimi.ingsw.GC_26_cards.leaderCard.LeaderCardImplementation;

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
		default:
			break;
		}
		return null;
	}

	@Override
	public List<LeaderCard> getRandomLeaderCards(int numOfPlayers) {
		// TODO Auto-generated method stub
		List<LeaderCard> list= new ArrayList<>();
		LeaderCard leaderCard = new LeaderCardImplementation("test",
				new CardNumbersRequirement(4, 4, 4, 4), null, new SetSecondAction(null, 4, null));
		for(int i=0;i<16;i++ ){
			list.add(leaderCard);
		}
		
		return list;
	}

	@Override
	public List<ExcommunicationTile> getExcommunicationTiles() {
		List<ExcommunicationTile> list= new ArrayList<>();
		ExcommunicationTile exc1 = new ExcommunicationTileImplementation(1, new MarketBanEffect());
		ExcommunicationTile exc2 = new ExcommunicationTileImplementation(2, new MarketBanEffect());
		ExcommunicationTile exc3 = new ExcommunicationTileImplementation(3, new MarketBanEffect());
		list.add(exc1);
		list.add(exc2);
		list.add(exc3);
		return list;
		
		}
	

}
