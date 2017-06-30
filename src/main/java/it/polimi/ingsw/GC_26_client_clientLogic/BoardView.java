package it.polimi.ingsw.GC_26_client_clientLogic;



import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_gameLogic.Action;
import it.polimi.ingsw.GC_26_gameLogic.ActionNotification;


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

	
	
	public BoardView() {
		
		this.territoriesTower = new ArrayList<>(); 
		this.buildingsTower =new ArrayList<>();
		this.charactersTower = new ArrayList<>();
		this.venturesTower = new ArrayList<>();
		this.marketZone =new ArrayList<>();
		this.productionZone = new ArrayList<>();
		this.harvestZone = new ArrayList<>();
		this.councilPalace = new ArrayList<>();  
		//even if there is only one council Palace set was chosen no order to be less specific
		this.excommunicationThisGame= new ArrayList<>();
		
		
		
	}
		//called at the beginning of the connection to save status of the board
		public void addPosition(PositionView position){
			List<PositionView> list = findSet(position.getZone());
			list.add(position.getPositionIndex()-1, position); //position Index starts from 1: in list from zero;	
		}
		
		public void update(ActionNotification action){
			PositionView position= findPosition(action);
			position.setPlayerhere(action.getPlayerName(), action.getFamilyMemberColour());
			position.setCardHere(null);
		}
		
		public void addCardWhereFree(CardDescriber card){
			List<PositionView> list =findRightTowerFromCard(card);
			boolean flag= findAndAddPositionWithoutCard(list, card);
			if(flag==false);
				//TODO lanciare exception;
		}
		
		private boolean findAndAddPositionWithoutCard(List<PositionView> list,CardDescriber card){
			for(PositionView p: list){
				if(p.getCardHere()==null){
					p.setCardHere(card);
					return true;
				}
			}
			return false;	
		}
		
		
		
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
	
	private PositionView findPosition(Action action){
		List<PositionView> list = findSet(action.getZone());
		return list.get(action.getPosition()-1);
		
	}
	
	
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

	
	
	
	
	public void addExcommunication(CardDescriber card){
		excommunicationThisGame.add(card);
	}

	
	
	public List<PositionView> getBuildingsTower() {
		return buildingsTower;
	}
	public List<PositionView> getCharactersTower() {
		return charactersTower;
	}
	public List<PositionView> getCouncilPalace() {
		return councilPalace;
	}
	public List<PositionView> getMarketZone() {
		return marketZone;
	}
	public List<PositionView> getProductionZone() {
		return productionZone;
	}
	public List<PositionView> getTerritoriesTower() {
		return territoriesTower;
	}
	public List<PositionView> getHarvestZone() {
		return harvestZone;
	}
	public List<PositionView> getVenturesTower() {
		return venturesTower;
	}
	public List<CardDescriber> getExcommunicationThisGame() {
		return excommunicationThisGame;
	}


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
	
	private void clean(List<PositionView> list){
		for(PositionView p: list){
			p.getPlayersHere().clear();
			p.setCardHere(null);
		}
	}
	
	
	}

