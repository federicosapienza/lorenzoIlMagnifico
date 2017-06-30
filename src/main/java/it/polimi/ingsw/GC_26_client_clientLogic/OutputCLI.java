package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.List;
import java.util.Map;
import java.util.Set;


import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;
import junit.framework.Test;


//methods are synchronized to avoid ugly mixtures of requests from InputLogic and infos from views.
public class OutputCLI implements Output{

	
	
	@Override
	public synchronized void printBoard(BoardView board) {
		System.out.println("*****************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************");
		System.out.println("TERRITORY TOWER:");
		System.out.println("VALUE:|-BONUS:-------------------------|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
		printList(board.getTerritoriesTower());
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("CHARACTER TOWER:");
		System.out.println("VALUE:|-BONUS:-------------------------|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
		printList(board.getCharactersTower());
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("BUILDING TOWER:");
		System.out.println("VALUE:|-BONUS:-------------------------|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
		printList(board.getBuildingsTower());
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("VENTURE TOWER:");
		System.out.println("VALUE:|-BONUS:-------------------------|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
		printList(board.getVenturesTower());
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("MARKET:");
		System.out.println("VALUE:|-BONUS:-------------------------|");
		printList(board.getMarketZone());
		System.out.println("---------------------------------------|");
		System.out.println("COUNCIL PALACE:");
		System.out.println("VALUE:|-BONUS:-------------------------|");
		printList(board.getCouncilPalace());
		System.out.println("---------------------------------------|");
		System.out.println("PRODUCTION");
		System.out.println("VALUE:|");
		printList(board.getProductionZone());
		System.out.println("------|");
		System.out.println("HARVEST:");
		System.out.println("VALUE:|");
		printList(board.getHarvestZone());
		System.out.println("------|");
		System.out.println("*****************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************");

	}
	
	
	
	private void printList(List<PositionView> positions){
		for(PositionView p : positions){
			printTool(p);
		}
	}
	
	private  void printTool(PositionView position){
		StringBuilder temp= new StringBuilder(" ");
		String test = new String();
		if(position.getBonusPosition() !=null)
			test = printBonusPosition(position.getPositionDescriber());
			temp.append(test);
		if(position.getCardHere()!=null){
			//temp.append(position.getCardHere()+ " ");//before
			test = printCardsFinal(position.getCardHere());//How should it be.
			temp.append(test);
			}
		if(!position.getPlayersHere().isEmpty()){
			Map<String , Colour> here= position.getPlayersHere();
			for (Map.Entry<String, Colour> entry : here.entrySet()){
			    temp.append(entry.getKey()+ " "+ entry.getValue()+" ");
			}

		}
		System.out.println(position.getPositionValue()+"     |"+temp);
	}
	
	
	private String printBonusPosition(PositionDescriber positionDescriber){
		String repeated = new String();
		StringBuilder temp = new StringBuilder(" ");
		if("null".equals(positionDescriber.getResourcesOrPointsOnPosition())){
			return temp.toString();
		}
		if(" ".equals(positionDescriber.getResourcesOrPointsOnPosition())){
			repeated = new String(new char[30]).replace("\0", " ");
			temp.append(repeated +"|");
			}
		else{
			repeated = new String(new char[30-positionDescriber.getResourcesOrPointsOnPosition().length()]).replace("\0", " ");
			temp.append(positionDescriber.getResourcesOrPointsOnPosition() + repeated +"|");
			}
		return temp.toString();
	}
	
	private String printCardsFinal(CardDescriber card){
	String repeated = new String();
	StringBuilder temp = new StringBuilder(" ");
	if("Development Card".equals(card.getTypeOfCard())){//Dev cards finished
		if(card.getName()!=null){
			repeated = new String(new char[25-card.getName().length()]).replace("\0", " ");
			temp.append(card.getName() + repeated + "|");
		}
		if(card.getActionValue()==0){ //Valore di inizializzazione
			repeated = new String(new char[14]).replace("\0", " ");
			temp.append (repeated + "|");
		}
		if(card.getActionValue()!=0){ //Valore di inizializzazione
			repeated = new String(new char[13]).replace("\0", " ");
			temp.append (card.getActionValue() + repeated + "|");
		}
		if(card.getPaymentDescriber()!=null){
			repeated = new String(new char[80-card.getPaymentDescriber().length()]).replace("\0", " ");
			temp.append(card.getPaymentDescriber() + repeated + "|");
		}
		if(card.getPaymentDescriber()==null){
			repeated = new String(new char[80]).replace("\0", " ");
			temp.append(repeated + "|");
		}
		if(card.getImmediateEffectDescriber()!=null){
			repeated = new String(new char[120-card.getImmediateEffectDescriber().length()]).replace("\0", " ");
			temp.append(card.getImmediateEffectDescriber() + repeated + "|");
		}
		if(card.getImmediateEffectDescriber()==null){
			repeated = new String(new char[120]).replace("\0", " ");//120 should be enough
			temp.append(repeated + "|");
		}
		if(card.getPermanentEffectDescriber()!=null){
			repeated = new String(new char[140-card.getPermanentEffectDescriber().length()]).replace("\0", " ");
			temp.append(card.getPermanentEffectDescriber() + repeated + "|");
		}
		if(card.getPermanentEffectDescriber()==null){
			repeated = new String(new char[140]).replace("\0", " ");//140 should be enough
			temp.append(repeated + "|");
		}
	}
	return temp.toString();
	}
	
	@Override
	public  void printExcommunicationTiles(BoardView board) {
		for(CardDescriber card: board.getExcommunicationThisGame())
			System.out.println(card.getPeriod() +" "+  card.getPermanentEffectDescriber());
		
	}
	@Override
	public  void printString(String string) {
		System.out.println(string);
		
	}
	@Override
	public  void printResources(PlayerView player) {
		System.out.println(player.getWallet().toString());
		
	}
	@Override
	public  void printCompleteStatus(PlayerView player) {
		System.out.println(player.getName() +": recap");
		System.out.println("Family members value: " + player.getFamilyMembers().getStatus());
		System.out.println("Free family members: " + player.getFamilyMembers().whatIsFree());
		printResources(player);	
		printCards(player);
		System.out.println( player.getPersonalTileValues());
	}
	@Override
	public  void printRankings(MainClientView view) {
		// TODO sarebbe bello stampare classifica a parte da punti
		
	}
	
	public  void printCards(Set<CardDescriber> cards){
		for(CardDescriber cardDescriber : cards)
			System.out.println(cardDescriber);
	}
	
	public  void printFamilyMembers(PlayerView player){
		System.out.println(player.getFamilyMembers().getStatus());
		System.out.println(player.getFamilyMembers().whatIsFree().toString());
	}
	
	@Override
	public void printCards(PlayerView player) {  //stampare tutta la persona board
		if(!player.getCurrentCards(DevelopmentCardTypes.TERRITORYCARD).isEmpty()){
			System.out.println("territory Cards owned");
			printCards(player.getCurrentCards(DevelopmentCardTypes.TERRITORYCARD));}
		if(!player.getCurrentCards(DevelopmentCardTypes.CHARACTERCARD).isEmpty()){
			System.out.println("character cards owned");
			printCards(player.getCurrentCards(DevelopmentCardTypes.CHARACTERCARD));}
		if(!player.getCurrentCards(DevelopmentCardTypes.BUILDINGCARD).isEmpty()){
			System.out.println("building cards owned");
			printCards(player.getCurrentCards(DevelopmentCardTypes.BUILDINGCARD));}
		if(!player.getCurrentCards(DevelopmentCardTypes.VENTURECARD).isEmpty()){
			System.out.println("venture cards owned");
			printCards(player.getCurrentCards(DevelopmentCardTypes.VENTURECARD));}
		
		if(!player.getPermamentsEffect().isEmpty()){
		System.out.println("permanents effect owned");
		for(String s: player.getPermamentsEffect())
			System.out.println(s);
	}
	

	}
	

}
