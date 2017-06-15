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
		System.out.println("Territory Tower");
		printList(board.getTerritoriesTower());
		System.out.println("building Tower");
		printList(board.getCharactersTower());
		System.out.println("Character Tower");
		printList(board.getBuildingsTower());
		System.out.println("Venture Tower");
		printList(board.getVenturesTower());
		System.out.println("market");
		printList(board.getMarketZone());
		System.out.println("Production ");
		printList(board.getProductionZone());
		System.out.println("Harvest ");
		printList(board.getHarvestZone());
		System.out.println("CouncilPalace ");
		printList(board.getCouncilPalace());
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
		System.out.println(player.getWallet());
		
	}
	@Override
	public  void printCompleteStatus(PlayerView player) {
		System.out.println(player.getName());
		System.out.println("Free family members: " + player.getFamilyMembers().whatIsFree());
		printCards(player.getCurrentCards(DevelopmentCardTypes.TERRITORYCARD));
		printCards(player.getCurrentCards(DevelopmentCardTypes.CHARACTERCARD));
		printCards(player.getCurrentCards(DevelopmentCardTypes.BUILDINGCARD));
		printCards(player.getCurrentCards(DevelopmentCardTypes.VENTURECARD));

	}
	@Override
	public  void printRankings(MainClientView view) {
		// TODO Auto-generated method stub
		
	}
	
	public  void printCards(Set<CardDescriber> cards){
		for(CardDescriber cardDescriber : cards)
			System.out.println(cardDescriber);
	}
	
	public  void printFamilyMembers(PlayerView player){
		System.out.println(player.getFamilyMembers().getStatus());
		System.out.println(player.getFamilyMembers().whatIsFree());
	}
	@Override
	public void printCards(PlayerView thisPlayer) {
		// TODO Auto-generated method stub
		
	}
	

}
