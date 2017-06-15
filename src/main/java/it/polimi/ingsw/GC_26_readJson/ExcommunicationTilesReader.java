package it.polimi.ingsw.GC_26_readJson;


import it.polimi.ingsw.GC_26_cards.effects.Effect;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.GC_26_cards.excommunicationTile.ExcommunicationTileImplementation;


public class ExcommunicationTilesReader extends CardsReader {

private JsonPathData jsonPathData = new JsonPathData();
private int period;
private String permanentEffectType;
private String name;
private Effect permanentEffect;
	
	public void readCards(int numberOfPeriod,CardsImplementation cards){
		String[] listOfPaths = chooseListOfExcommunicationTIles(numberOfPeriod);
		for(String s:listOfPaths){
			super.createJsonObjectFromFile(s);
			period = super.readInt("period");
			permanentEffectType= super.readString("typeOfPermanentEffect");
			permanentEffect=super.createPermanentEffect(permanentEffectType);
<<<<<<< HEAD
=======
			name = super.readString("name");
>>>>>>> cd6f5e1ae44d1713a7d4e23b232ca34001143eff
			createExcommunicationTiles(cards,numberOfPeriod);
			super.closeBufferedReader();
		}


	}
	
	private String[] chooseListOfExcommunicationTIles(int numberOfPeriod){
		switch (numberOfPeriod) {
		case 1:
			return jsonPathData.getExcommunicationTilesPeriod1();
		case 2:
			return jsonPathData.getExcommunicationTilesPeriod2();
		case 3:
			return jsonPathData.getExcommunicationTilesPeriod3();
		default:
			return null;
		}
	}
	
	private void createExcommunicationTiles(CardsImplementation cardsImplementation,int numOfperiod){
		ExcommunicationTile excommunicationTile = new ExcommunicationTileImplementation(numOfperiod, permanentEffect,name);
		cardsImplementation.getExcommunicationTiles(numOfperiod).add(excommunicationTile);
	}
}
