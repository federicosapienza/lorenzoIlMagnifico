package it.polimi.ingsw.GC_26_client_clientLogic;



import java.util.ArrayList;
import java.util.List;


import it.polimi.ingsw.GC_26_board.BoardZone;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_gameLogic.Action;


public class BoardView {
	private List<PositionView> territoriesTower;
	private List<PositionView> buildingsTower;
	private List<PositionView> charactersTower;
	private List<PositionView> venturesTower;
	private List<PositionView> marketZone;
	private List<PositionView> productionZone;
	private List<PositionView> harvestZone;
	private List<PositionView> CouncilPalace;
	
	
	public BoardView() {
		
		this.territoriesTower = new ArrayList<>(); 
		this.buildingsTower =new ArrayList<>();
		this.charactersTower = new ArrayList<>();
		this.venturesTower = new ArrayList<>();
		this.marketZone =new ArrayList<>();
		this.productionZone = new ArrayList<>();
		this.harvestZone = new ArrayList<>();
		this.CouncilPalace = new ArrayList<>();  
		//even if there is only one council Palace set was chosen no order to be less specific
	}
		//called at the beginning of the connection to save status of the board
		public void addPosition(PositionView position){
			List<PositionView> list = findSet(position.getZone());
			list.add(position.getPositionIndex()-1, position);; //position Index starts from 1: in list from zero;	
		}
		
		public void addfamilyMember(Action action){
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
		
		private boolean  findAndAddPositionWithoutCard(List<PositionView> list,CardDescriber card){
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
					return CouncilPalace;
					}
		}
	
	private PositionView findPosition(Action action){
		List<PositionView> list = findSet(action.getZone());
		return  list.get(action.getPosition()-1);
		
	}
	
	
	private List<PositionView> findRightTowerFromCard(CardDescriber card){
		switch (card.getCardType()) {
		case TERRITORYCARD: 
			return territoriesTower;
		case BUILDINGCARD: 
			return buildingsTower;
		case CHARACTERCARD:
			return charactersTower;
		default:
			return venturesTower;
		}
		
	}


	}

