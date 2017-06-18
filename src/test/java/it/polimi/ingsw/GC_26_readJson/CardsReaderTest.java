package it.polimi.ingsw.GC_26_readJson;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.effects.ReceiveResourcesOrPointsEffect;
import it.polimi.ingsw.GC_26_cards.effects.TwoAndEffect;
import it.polimi.ingsw.GC_26_utilities.resourcesAndPoints.ResourcesOrPoints;

public class CardsReaderTest {

	CardsReader cardsReader = new CardsReader();
	DevelopmentCardTypes developmentCardTypes;
	BoardZone boardZone;
	String[] stringArray;
	String string;
	JsonPathData jsonPathData = new JsonPathData();
	
	@Test
	public void testGetDevelopmentCardType() {
		developmentCardTypes = cardsReader.getDevelopmentCardType("territory");
		assertEquals(DevelopmentCardTypes.TERRITORYCARD, developmentCardTypes);
		developmentCardTypes = cardsReader.getDevelopmentCardType("building");
		assertEquals(DevelopmentCardTypes.BUILDINGCARD, developmentCardTypes);
		developmentCardTypes = cardsReader.getDevelopmentCardType("character");
		assertEquals(DevelopmentCardTypes.CHARACTERCARD, developmentCardTypes);
		developmentCardTypes = cardsReader.getDevelopmentCardType("venture");
		assertEquals(DevelopmentCardTypes.VENTURECARD, developmentCardTypes);
	}

	@Test
	public void testGetBoardZoneType() {
		boardZone = cardsReader.getBoardZoneType("territory");
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

	@Test
	public void testChooseListOfCards() {
		stringArray = cardsReader.chooseListOfCards(1, DevelopmentCardTypes.TERRITORYCARD);
		assertArrayEquals(jsonPathData.territoryCardsPeriod1PathArray, stringArray);
		stringArray = cardsReader.chooseListOfCards(2, DevelopmentCardTypes.TERRITORYCARD);
		assertArrayEquals(jsonPathData.territoryCardsPeriod2PathArray, stringArray);
		stringArray = cardsReader.chooseListOfCards(3, DevelopmentCardTypes.TERRITORYCARD);
		assertArrayEquals(jsonPathData.territoryCardsPeriod3PathArray, stringArray);
		
		stringArray = cardsReader.chooseListOfCards(1, DevelopmentCardTypes.CHARACTERCARD);
		assertArrayEquals(jsonPathData.characterCardsPeriod1PathArray, stringArray);
		stringArray = cardsReader.chooseListOfCards(2, DevelopmentCardTypes.CHARACTERCARD);
		assertArrayEquals(jsonPathData.characterCardsPeriod2PathArray, stringArray);
		stringArray = cardsReader.chooseListOfCards(3, DevelopmentCardTypes.CHARACTERCARD);
		assertArrayEquals(jsonPathData.characterCardsPeriod3PathArray, stringArray);
		
		stringArray = cardsReader.chooseListOfCards(1, DevelopmentCardTypes.BUILDINGCARD);
		assertArrayEquals(jsonPathData.buildingCardsPeriod1PathArray, stringArray);
		stringArray = cardsReader.chooseListOfCards(2, DevelopmentCardTypes.BUILDINGCARD);
		assertArrayEquals(jsonPathData.buildingCardsPeriod2PathArray, stringArray);
		stringArray = cardsReader.chooseListOfCards(3, DevelopmentCardTypes.BUILDINGCARD);
		assertArrayEquals(jsonPathData.buildingCardsPeriod3PathArray, stringArray);
		
		stringArray = cardsReader.chooseListOfCards(1, DevelopmentCardTypes.VENTURECARD);
		assertArrayEquals(jsonPathData.ventureCardsPeriod1PathArray, stringArray);
		stringArray = cardsReader.chooseListOfCards(2, DevelopmentCardTypes.VENTURECARD);
		assertArrayEquals(jsonPathData.ventureCardsPeriod2PathArray, stringArray);
		stringArray = cardsReader.chooseListOfCards(3, DevelopmentCardTypes.VENTURECARD);
		assertArrayEquals(jsonPathData.ventureCardsPeriod3PathArray, stringArray);
	
	}
	
	
	ResourcesOrPoints resourcesOrPoints = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
	ResourcesOrPoints resourcesOrPoints2 = ResourcesOrPoints.newResourcesOrPoints(0, 0, 0, 0, 0, 0, 0, 0);
	ReceiveResourcesOrPointsEffect resourcesOrPointsEffect = new ReceiveResourcesOrPointsEffect(resourcesOrPoints);
	ReceiveResourcesOrPointsEffect resourcesOrPointsEffect2 = new ReceiveResourcesOrPointsEffect(resourcesOrPoints);
	Effect effect = new TwoAndEffect(resourcesOrPointsEffect, resourcesOrPointsEffect2);
	Effect effect2 = new TwoAndEffect(resourcesOrPointsEffect, resourcesOrPointsEffect2);
	@Test
	public void testCreateDoubleEffect(){
		Effect myReturnedObject = cardsReader.createDoubleEffect(resourcesOrPointsEffect,resourcesOrPointsEffect2);
		assertNotNull(myReturnedObject);
		assertTrue(myReturnedObject instanceof TwoAndEffect);
		assertTrue(effect.equals(myReturnedObject));
	}


	/*public Effect createDoubleEffect(Effect effect1,Effect effect2){
		 TwoAndEffect effect = new TwoAndEffect(effect1, effect2);
		 return effect;
	}*/
	
	
	
	
	
	
	
	/*public String readString(String stringToRead){
		jsonElement= jsonObject.get(stringToRead);
		return returnString = jsonElement.getAsString();
	}*/
	/*Gson gson = new Gson();
	JsonObject jsonObject= gson.fromJson(new BufferedReader(new FileReader("src/Cards/Territory_cards/Period_1/Woods.json")), JsonObject.class);
	String string2= jsonObject.get("name").getAsString();
	public void testReadString(){
		cardsReader.readString(stringToRead)
		assertNotNull();
	}*/
}




















	
	