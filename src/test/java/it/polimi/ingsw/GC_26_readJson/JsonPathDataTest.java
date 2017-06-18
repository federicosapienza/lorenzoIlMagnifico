package it.polimi.ingsw.GC_26_readJson;

import static org.junit.Assert.*;

import org.junit.Test;

public class JsonPathDataTest {

	String[] stringArray;
	JsonPathData jsonPathData = new JsonPathData();
	//sta roba la posso fare solo perchè le stringhe non sono private!
	@Test
	public void getCardsTest() {
	stringArray = jsonPathData.getTerritoryCardsPeriod1PathArray();
	assertArrayEquals(jsonPathData.territoryCardsPeriod1PathArray, stringArray);
	stringArray = jsonPathData.getTerritoryCardsPeriod2PathArray();
	assertArrayEquals(jsonPathData.territoryCardsPeriod2PathArray, stringArray);
	stringArray = jsonPathData.getTerritoryCardsPeriod3PathArray();
	assertArrayEquals(jsonPathData.territoryCardsPeriod3PathArray, stringArray);
	

	stringArray = jsonPathData.getCharacterCardsPeriod1PathArray();
	assertArrayEquals(jsonPathData.characterCardsPeriod1PathArray, stringArray);
	stringArray = jsonPathData.getCharacterCardsPeriod2PathArray();
	assertArrayEquals(jsonPathData.characterCardsPeriod2PathArray, stringArray);
	stringArray = jsonPathData.getCharacterCardsPeriod3PathArray();
	assertArrayEquals(jsonPathData.characterCardsPeriod3PathArray, stringArray);
	
	stringArray = jsonPathData.getBuildingCardsPeriod1PathArray();
	assertArrayEquals(jsonPathData.buildingCardsPeriod1PathArray, stringArray);
	stringArray = jsonPathData.getBuildingCardsPeriod2PathArray();
	assertArrayEquals(jsonPathData.buildingCardsPeriod2PathArray, stringArray);
	stringArray = jsonPathData.getBuildingCardsPeriod3PathArray();
	assertArrayEquals(jsonPathData.buildingCardsPeriod3PathArray, stringArray);
	
	stringArray = jsonPathData.getVentureCardsPeriod1PathArray();
	assertArrayEquals(jsonPathData.ventureCardsPeriod1PathArray, stringArray);
	stringArray = jsonPathData.getVentureCardsPeriod2PathArray();
	assertArrayEquals(jsonPathData.ventureCardsPeriod2PathArray, stringArray);
	stringArray = jsonPathData.getVentureCardsPeriod3PathArray();
	assertArrayEquals(jsonPathData.ventureCardsPeriod3PathArray, stringArray);
	
	stringArray = jsonPathData.getResources();
	assertArrayEquals(jsonPathData.resources, stringArray);
	
	stringArray = jsonPathData.getStartingResources();
	assertArrayEquals(jsonPathData.startingResources, stringArray);
	
	stringArray = jsonPathData.getLeaderCards();
	assertArrayEquals(jsonPathData.leaderCards, stringArray);
	
	stringArray = jsonPathData.getExcommunicationTilesPeriod1();
	assertArrayEquals(jsonPathData.excommunicationTilesPeriod1, stringArray);
	stringArray = jsonPathData.getExcommunicationTilesPeriod2();
	assertArrayEquals(jsonPathData.excommunicationTilesPeriod2, stringArray);
	stringArray = jsonPathData.getExcommunicationTilesPeriod3();
	assertArrayEquals(jsonPathData.excommunicationTilesPeriod3, stringArray);
	
	stringArray = jsonPathData.getPersonalBoardTilesNormal();
	assertArrayEquals(jsonPathData.personalBoardTilesNormal, stringArray);
	stringArray = jsonPathData.getPersonalBoardTilesAdvanced();
	assertArrayEquals(jsonPathData.personalBoardTilesAdvanced, stringArray);
	
	stringArray = jsonPathData.getTimers();
	assertArrayEquals(jsonPathData.timers, stringArray);
	}

}
