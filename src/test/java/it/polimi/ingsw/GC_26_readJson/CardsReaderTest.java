package it.polimi.ingsw.GC_26_readJson;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;

public class CardsReaderTest {


	CardsReader cardsReader = new CardsReader();
	
	@Test
	public void testGetDevelopmentCardType() {
		DevelopmentCardTypes developmentCardTypes = cardsReader.getDevelopmentCardType("territory");
		assertEquals(DevelopmentCardTypes.TERRITORYCARD, developmentCardTypes);
		developmentCardTypes = cardsReader.getDevelopmentCardType("building");
		assertEquals(DevelopmentCardTypes.BUILDINGCARD, developmentCardTypes);
		developmentCardTypes = cardsReader.getDevelopmentCardType("character");
		assertEquals(DevelopmentCardTypes.CHARACTERCARD, developmentCardTypes);
		developmentCardTypes = cardsReader.getDevelopmentCardType("venture");
		assertEquals(DevelopmentCardTypes.VENTURECARD, developmentCardTypes);
	}
	
	@Test
	public void testGetBoardZoneType(){ 
		BoardZone boardZone = cardsReader.getBoardZoneType("territory");
		assertEquals(BoardZone.TERRITORYTOWER, boardZone);
		boardZone = cardsReader.getBoardZoneType("building");
		assertEquals(BoardZone.BUILDINGTOWER, boardZone);
		boardZone = cardsReader.getBoardZoneType("character");
		assertEquals(BoardZone.CHARACTERTOWER, boardZone);
		boardZone = cardsReader.getBoardZoneType("venture");
		assertEquals(BoardZone.VENTURETOWER, boardZone);
		boardZone = cardsReader.getBoardZoneType("harvest");
		assertEquals(BoardZone.HARVEST, boardZone);
		boardZone = cardsReader.getBoardZoneType("production");
		assertEquals(BoardZone.PRODUCTION, boardZone);
		boardZone = cardsReader.getBoardZoneType("market");
		assertEquals(BoardZone.MARKET, boardZone);
		boardZone = cardsReader.getBoardZoneType("council");
		assertEquals(BoardZone.COUNCILPALACE, boardZone);
	}

}
