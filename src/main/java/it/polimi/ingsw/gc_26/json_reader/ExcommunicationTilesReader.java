package it.polimi.ingsw.gc_26.json_reader;


import it.polimi.ingsw.gc_26.model.game.game_components.cards.effects.Effect;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.excommunicationTile.ExcommunicationTile;
import it.polimi.ingsw.gc_26.model.game.game_components.cards.excommunicationTile.ExcommunicationTileImplementation;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 *
 * This class represents the reader of the Excommunication Tiles
 * 
 */
public class ExcommunicationTilesReader extends CardsReader {
	private JsonPathData jsonPathData = new JsonPathData();
	private String permanentEffectType;
	private Effect permanentEffect;
	
	/**
	 * Method that updates the CardsImplementation with the Excommunication Tiles read from Json
	 * @param numberOfPeriod It's the number of period of each tile
	 * @param cards It's the object to update with the tiles that have been created
	 */
	public void readCards(int numberOfPeriod,CardsImplementation cards){
		String[] listOfPaths = chooseListOfExcommunicationTIles(numberOfPeriod);
		for(String s:listOfPaths){
			super.createJsonObjectFromFile(s);
			permanentEffectType= super.readString("typeOfPermanentEffect");
			permanentEffect=super.createPermanentEffect(permanentEffectType);
			createExcommunicationTiles(cards,numberOfPeriod);
			super.closeBufferedReader();
		}
	}
	
	/**
	 * Method that returns the list of Excommunication Tiles which corresponds to the number of period contained in the parameter,
	 * as an array of String values
	 * @param numberOfPeriod It's the number of period of the list of cards to get
	 * @return the list of Excommunication Tiles which corresponds to the number of period contained in the parameter,
	 * as an array of String values
	 */
	private String[] chooseListOfExcommunicationTIles(int numberOfPeriod){
		switch (numberOfPeriod) {
		case 1:
			return jsonPathData.getExcommunicationTilesPeriod1();
		case 2:
			return jsonPathData.getExcommunicationTilesPeriod2();
		case 3:
			return jsonPathData.getExcommunicationTilesPeriod3();
		default:
			return new String[0]; //return empty array instead of null
		}
	}
	
	/**
	 * Method that creates an Excommunication Tile for the period indicated by the int numOfPeriod contained in the parameter
	 * @param cardsImplementation It's the object to update with the tile that has been created
	 * @param numOfPeriod It's the number of period of the tile that has to be created
	 */
	private void createExcommunicationTiles(CardsImplementation cardsImplementation, int numOfperiod){
		ExcommunicationTile excommunicationTile = new ExcommunicationTileImplementation(numOfperiod, permanentEffect);
		cardsImplementation.getExcommunicationTiles(numOfperiod).add(excommunicationTile);
	}
}
