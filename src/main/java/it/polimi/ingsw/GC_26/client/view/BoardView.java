package it.polimi.ingsw.GC_26.client.view;



import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26.messages.action.Action;
import it.polimi.ingsw.GC_26.messages.action.ActionNotification;
import it.polimi.ingsw.GC_26.messages.describers.CardDescriber;
import it.polimi.ingsw.GC_26.model.game.gameComponents.board.BoardZone;

/**
 * 
 * @author David Yun (david.yun@mail.polimi.it)
 * @author Federico Sapienza (federico.sapienza@mail.polimi.it)
 * @author Leonardo Varè (leonardo.vare@mail.polimi.it)
 * 
 * This class represents the board view.
 *
 */
public class BoardView {
	private List<PositionView> territoriesTower;
	private List<PositionView> buildingsTower;
	private List<PositionView> charactersTower;
	private List<PositionView> venturesTower;
	private List<PositionView> marketZone;
	private List<PositionView> productionZone;
	private List<PositionView> harvestZone;
	private List<PositionView> councilPalace;
	
	private List<CardDescriber> excommunicationThisGame;

	
	/**
	 * Constructor: it creates the lists for all the zones of the board and for the Excommunication tiles
	 */
	public BoardView() {
		
		this.territoriesTower = new ArrayList<>(); 
		this.buildingsTower = new ArrayList<>();
		this.charactersTower = new ArrayList<>();
		this.venturesTower = new ArrayList<>();
		this.marketZone = new ArrayList<>();
		this.productionZone = new ArrayList<>();
		this.harvestZone = new ArrayList<>();
		this.councilPalace = new ArrayList<>();  
		//even if there is only one council Palace set was chosen no order to be less specific
		this.excommunicationThisGame= new ArrayList<>();
		
		
		
	}
	
	/**
	 * Method called at the beginning of the connection to save the status of the board.
	 * @param position It's the positionView to add in the list of positionView 
	 * (Note: positionIndex starts from 1; the index of the list starts from 0)
	 */
	public void addPosition(PositionView position){
		List<PositionView> list = findSet(position.getZone());
		list.add(position.getPositionIndex()-1, position); 
	}
	
	/**
	 * Method called to update the positionView of the Action, when an Action Notification is received.
	 * @param action It's the ActionNotification that changes the status of the positionView
	 */
	public void update(ActionNotification action){
		PositionView position= findPosition(action);
		position.setPlayerhere(action.getPlayerName(), action.getFamilyMemberColour());
		position.setCardHere(null);
	}
	
	/**
	 * Method called to add a card in a free tower
	 * @param card It's the CardDescriber which describes the card that has to be added in the tower, if it's free
	 */
	public void addCardWhereFree(CardDescriber card){
		List<PositionView> list =findRightTowerFromCard(card);
		findAndAddPositionWithoutCard(list, card);
	}
	
	/**
	 * Method that checks if there's a free PositionView in the list contained in the parameter, and if the check is positive,
	 * it sets the card in the first free positionView and returns true; if the check is negative (i.e. there's no free
	 * PositionView in the list) it returns false
	 * @param list It's the list of PositionView that has to be checked
	 * @param card It's the CardDescriber to set in the first free PositionView, if it exists
	 * @return true if there is a free PositionView in the list; false if there isn't any free PositionView 
	 */
	private boolean findAndAddPositionWithoutCard(List<PositionView> list,CardDescriber card){
		for(PositionView p: list){
			if(p.getCardHere()==null){
				p.setCardHere(card);
				return true;
			}
		}
		return false;	
	}
		
	
	/**
	 * Method that returns the list that corresponds to the Board Zone contained in the parameter
	 * @param zone It's the Board Zone to check to find the corresponding list
	 * @return the list that corresponds to the Board Zone contained in the parameter
	 */
	private List<PositionView> findSet(BoardZone zone){
		switch(zone){
		case TERRITORYTOWER: 
			return territoriesTower;
			case BUILDINGTOWER: 
				return buildingsTower;
				case CHARACTERTOWER:
					return charactersTower;
					case VENTURETOWER:
						return venturesTower;
						case PRODUCTION:
							return productionZone;
							case HARVEST:
								return harvestZone;
								case MARKET:
									return marketZone;
									default: 
										return councilPalace;
										}
	}
	
	/**
	 * Method that checks and returns the PositionView that corresponds to the Board Zone of the action contained in 
	 * the parameter
	 * @param action It's the action whose Board Zone has to be checked in order to determine the corresponding PositionView 
	 * @return the PositionView that corresponds to the Board Zone of the action contained in the parameter
	 */
	private PositionView findPosition(Action action){
		List<PositionView> list = findSet(action.getZone());
		return list.get(action.getPosition()-1);
		
	}
	
	/**
	 * Method that checks the type of the card contained in the parameter in order to determine and return the 
	 * corresponding list of PositionView
	 * @param card It's the CardDescriber whose type has to be checked 
	 * @return the list of PositionView that corresponds to the type of the card contained in the parameter
	 */
	private List<PositionView> findRightTowerFromCard(CardDescriber card){
		switch (card.getType()) {
		case TERRITORYCARD: 
			return territoriesTower;
			case BUILDINGCARD: 
				return buildingsTower;
				case CHARACTERCARD:
					return charactersTower;
					case VENTURECARD:
						return venturesTower;
						default:
							throw new IllegalArgumentException();
		}
	}

	
	
	
	/**
	 * Method that adds the CardDescriber contained in the parameter to the list of the Excommunication tiles of this game
	 * @param card It's the CardDescirber to add in the list of the Excommunication tiles
	 */
	public void addExcommunication(CardDescriber card){
		excommunicationThisGame.add(card);
	}

	/**
	 * Method that returns the list of PositionView for the Territory Tower
	 * @return the list of PositionView for the Territory Tower
	 */
	public List<PositionView> getTerritoriesTower() {
		return territoriesTower;
	}
	
	/**
	 * Method that returns the list of PositionView for the Building Tower
	 * @return the list of PositionView for the Building Tower
	 */
	public List<PositionView> getBuildingsTower() {
		return buildingsTower;
	}
	
	/**
	 * Method that returns the list of PositionView for the Character Tower
	 * @return the list of PositionView for the Character Tower
	 */
	public List<PositionView> getCharactersTower() {
		return charactersTower;
	}
	
	/**
	 * Method that returns the list of PositionView for the Venture Tower
	 * @return the list of PositionView for the Venture Tower
	 */
	public List<PositionView> getVenturesTower() {
		return venturesTower;
	}
	
	/**
	 * Method that returns the list of PositionView for the Council Palace
	 * @return the list of PositionView for the Council Palace
	 */
	public List<PositionView> getCouncilPalace() {
		return councilPalace;
	}
	
	/**
	 * Method that returns the list of PositionView for the Market Zone
	 * @return the list of PositionView for the Market Zone
	 */
	public List<PositionView> getMarketZone() {
		return marketZone;
	}
	
	/**
	 * Method that returns the list of PositionView for the Production Zone
	 * @return the list of PositionView for the Production Zone
	 */
	public List<PositionView> getProductionZone() {
		return productionZone;
	}
	
	/**
	 * Method that returns the list of PositionView for the Harvest Zone
	 * @return the list of PositionView for the Harvest Zone
	 */
	public List<PositionView> getHarvestZone() {
		return harvestZone;
	}
	
	/**
	 * Method that returns the list of CardDescriber for the Excommunication tiles
	 * @return the list of CardDescriber for the Excommunication tiles
	 */
	public List<CardDescriber> getExcommunicationThisGame() {
		return excommunicationThisGame;
	}

	/**
	 * Method that wipes all the board, resetting the BoardView with the initial conditions (every PositionView is empty)
	 */
	public void cleanBoard(){
		clean(territoriesTower);
		clean(charactersTower);
		clean(buildingsTower);
		clean(venturesTower);
		clean(marketZone);
		clean(harvestZone);
		clean(productionZone);
		clean(councilPalace);
		
	}
	
	/**
	 * Method that removes all the players' family members and the cards from every PositionView contained in the list
	 * of the parameter 
	 * @param list It's the list of PositionView to check and to clean
	 */
	private void clean(List<PositionView> list){
		for(PositionView p: list){
			p.getPlayersHere().clear();
			p.setCardHere(null);
		}
	}
	
	
	}

