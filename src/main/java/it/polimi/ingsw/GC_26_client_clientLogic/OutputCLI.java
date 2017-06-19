package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.List;
import java.util.Map;
import java.util.Set;


import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;


//methods are synchronized to avoid ugly mixtures of requests from InputLogic and infos from views.
public class OutputCLI implements Output{

	
	
	@Override
	public synchronized void printBoard(BoardView board) {
		System.out.println("*****************************************************************************************");
		System.out.println("Territory Tower");
		printList(board.getTerritoriesTower());
		System.out.println("Character Tower");
		printList(board.getCharactersTower());
		System.out.println("Building Tower");
		printList(board.getBuildingsTower());
		System.out.println("Venture Tower");
		printList(board.getVenturesTower());
		System.out.println("Market");
		printList(board.getMarketZone());
		System.out.println("Production ");
		printList(board.getProductionZone());
		System.out.println("Harvest ");
		printList(board.getHarvestZone());
		System.out.println("CouncilPalace ");
		printList(board.getCouncilPalace());
		System.out.println("*****************************************************************************************");

	}
	private void printList(List<PositionView> positions){
		for(PositionView p : positions){
			printTool(p);
		}
	}
	
	private  void printTool(PositionView position){
		StringBuilder temp= new StringBuilder(" ");
		if(position.getBonusPosition() !=null)
			temp.append(position.getBonusPosition()+" ");
		if(position.getCardHere()!=null)
			temp.append(position.getCardHere()+ " ");
		if(!position.getPlayersHere().isEmpty()){
			Map<String , Colour> here= position.getPlayersHere();
			for (Map.Entry<String, Colour> entry : here.entrySet()){
			    temp.append(entry.getKey()+ " "+ entry.getValue()+" ");
			}

		}
		System.out.println(position.getPositionValue()+" "+temp);
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
		System.out.println("Production Bonus: "+ player.getPersonalTileValues());
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
