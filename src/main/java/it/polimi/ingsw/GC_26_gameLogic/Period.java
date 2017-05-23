package it.polimi.ingsw.GC_26_gameLogic;


import java.util.Iterator;

import it.polimi.ingsw.GC_26_board.Board;
import it.polimi.ingsw.GC_26_cards.Cards;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCard;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;

public class Period {
	private int period;
	private DevelopmentCard cards;
	private GameElements gameElements;
	private int currentRound=1; 
	private int numOfRounds =  GameParameters.getRoundsforPeriod();
	
	public Period(int period, DevelopmentCard cards, GameElements gameElements) {
		this.period=period;
		this.cards= cards;
		this.gameElements = gameElements;
	}
	
	public int getPeriod() {
		return period;
	}
	public int getRound() {
		return currentRound;
	}
	
	public void start(){
		initialisePeriod();
		while(currentRound<=numOfRounds){
			Round round = new Round( period, currentRound, gameElements);
			round.start();
			vaticanReport();
		}
	}

	
	private void vaticanReport() {
		for (Iterator<Player> iter = gameElements.getPlayers().iterator(); iter.hasNext(); ) {
		    Player p = iter.next();
		    VaticanReportRound vatican = new VaticanReportRound(period);
		    vatican.start(p);
		    //TODO si potrebbero anche gestire in contemporanea: occhio al sincronismo
		}
		
	}

	private void initialisePeriod(){
		gameElements.getBoard().getTower(2).setCardsForThisPeriod(cards.getDevelopmentCards(period, DevelopmentCardTypes.BUILDINGCARD));
		gameElements.getBoard().getTower(3).setCardsForThisPeriod(cards.getDevelopmentCards(period, DevelopmentCardTypes.CHARACTERCARD));
		gameElements.getBoard().getTower(1).setCardsForThisPeriod(cards.getDevelopmentCards(period, DevelopmentCardTypes.TERRITORYCARD));
		gameElements.getBoard().getTower(4).setCardsForThisPeriod(cards.getDevelopmentCards(period, DevelopmentCardTypes.VENTURECARD));
	}
	
	
}
