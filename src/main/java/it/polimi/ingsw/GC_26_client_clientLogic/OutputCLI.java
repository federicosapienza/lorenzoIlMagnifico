package it.polimi.ingsw.GC_26_client_clientLogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


import it.polimi.ingsw.GC_26_board.PositionDescriber;
import it.polimi.ingsw.GC_26_cards.CardDescriber;
import it.polimi.ingsw.GC_26_cards.developmentCards.DevelopmentCardTypes;
import it.polimi.ingsw.GC_26_player.Player;
import it.polimi.ingsw.GC_26_utilities.dices.Colour;


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
		System.out.println("PRODUCTION:");
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
			test = printDevelopmentCards(position.getCardHere());//How should it be.
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
		String repeated ;
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
	
	private String printDevelopmentCards(CardDescriber card){
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
	public void printLeaderCards(Set<CardDescriber> cards){
		String repeated = new String();
		StringBuilder temp = new StringBuilder(" ");
		System.out.println("");
		System.out.println(" LEADER CARDS:");
		System.out.println(" |NAME:-------------------------|-REQUIREMENTS------------------------------------------------------------------------------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:-------------------------------------------------------------------------------------------------------------------------------------");
		for(CardDescriber cardDescriber : cards){
			repeated = new String(new char[30-cardDescriber.getName().length()]).replace("\0", " ");
			temp.append("|" + cardDescriber.getName() + repeated +"|");
			repeated = new String(new char[150-cardDescriber.getRequirementDescriber().length()]).replace("\0", " ");
			temp.append(" " + cardDescriber.getRequirementDescriber() + repeated+"|");
			if(cardDescriber.getImmediateEffectDescriber()!=null){
				repeated = new String(new char[150-cardDescriber.getImmediateEffectDescriber().length()]).replace("\0", " ");
				temp.append(cardDescriber.getImmediateEffectDescriber() + repeated+"|");
			}
			if(cardDescriber.getImmediateEffectDescriber()==null){
				repeated = new String(new char[150]).replace("\0", " ");
				temp.append(repeated +"|");
			}
			if(cardDescriber.getPermanentEffectDescriber()!=null){
				repeated = new String(new char[150-cardDescriber.getPermanentEffectDescriber().length()]).replace("\0", " ");
				temp.append(cardDescriber.getPermanentEffectDescriber() + repeated+"|");
			}
			if(cardDescriber.getPermanentEffectDescriber()==null){
				repeated = new String(new char[150]).replace("\0", " ");
				temp.append(repeated +"|");
			}
			System.out.println(temp);
			temp = new StringBuilder(" ");
		}
		System.out.println(" |-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("");
	}
	
	@Override
	public  void printExcommunicationTiles(BoardView board) {
		String repeated = new String();
		System.out.println(" ");
		System.out.println(" EXCOMMUNICATION TILES:");
		System.out.println("|PERIOD:|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
		for(CardDescriber card: board.getExcommunicationThisGame()){
			repeated = new String(new char[140-card.getPermanentEffectDescriber().length()]).replace("\0", " ");
			System.out.println("|"+card.getPeriod() +"      |"+  card.getPermanentEffectDescriber()+repeated+"|");
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------|");
		System.out.println(" ");
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
		System.out.println("");
		System.out.println("RECAP OF PLAYER " + "'" + player.getName() + "' :");
		System.out.println("");
		System.out.println("Family members value: ");
		System.out.println(player.getFamilyMembers().getStatus());
		System.out.println("");
		System.out.println("Free family members:  ");
		System.out.println(player.getFamilyMembers().whatIsFree());
		System.out.println("");
		System.out.println("Resources and points:");
		printResources(player);	
		System.out.println("");
		System.out.println("Cards owned:");
		if(player.getCurrentCards(DevelopmentCardTypes.TERRITORYCARD).isEmpty() && player.getCurrentCards(DevelopmentCardTypes.CHARACTERCARD).isEmpty()
			&& player.getCurrentCards(DevelopmentCardTypes.BUILDINGCARD).isEmpty()	&& player.getCurrentCards(DevelopmentCardTypes.VENTURECARD).isEmpty()){
			System.out.println("No cards owned yet");
		}
		else{
			printCards(player);
		}
		System.out.println("");
		System.out.println("Personal tile values: ");
		System.out.println( player.getPersonalTileValues());
		System.out.println("");
	}
	@Override
	public  void printRankings(MainClientView view) {
		Map<String, PlayerView> players=view.getPlayers();

		List<PlayerView> playerViews  = new ArrayList<>(players.values());
		
	}
	
	public  void printCards(Set<CardDescriber> cards){
		for(CardDescriber cardDescriber : cards)
			System.out.println(cardDescriber);
	}
	//Specific method instead of printCards
	public void printDevelopmentCardsSet(Set<CardDescriber> cards){
		for(CardDescriber cardDescriber : cards){
			String temp = printDevelopmentCards(cardDescriber);
			System.out.println("|"+temp);
		}
	}
	
	public  void printFamilyMembers(PlayerView player){
		System.out.println(player.getFamilyMembers().getStatus());
		System.out.println(player.getFamilyMembers().whatIsFree().toString());
	}
	
	@Override
	public void printCards(PlayerView player) {  //stampare tutta la persona board
		if(!player.getCurrentCards(DevelopmentCardTypes.TERRITORYCARD).isEmpty()){
			System.out.println(" Territory Cards owned:");
			System.out.println("|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
			printDevelopmentCardsSet(player.getCurrentCards(DevelopmentCardTypes.TERRITORYCARD));}
		if(!player.getCurrentCards(DevelopmentCardTypes.CHARACTERCARD).isEmpty()){
			System.out.println(" Character cards owned:");
			System.out.println("|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
			printDevelopmentCardsSet(player.getCurrentCards(DevelopmentCardTypes.CHARACTERCARD));}
		if(!player.getCurrentCards(DevelopmentCardTypes.BUILDINGCARD).isEmpty()){
			System.out.println(" Building cards owned:");
			System.out.println("|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
			printDevelopmentCardsSet(player.getCurrentCards(DevelopmentCardTypes.BUILDINGCARD));}
		if(!player.getCurrentCards(DevelopmentCardTypes.VENTURECARD).isEmpty()){
			System.out.println(" Venture cards owned:");
			System.out.println("|-NAME:--------------------|-ACTION VALUE:|-PAYMENT:-----------------------------------------------------------------------|-IMMEDIATE EFFECT:------------------------------------------------------------------------------------------------------|-PERMANENT EFFECT:--------------------------------------------------------------------------------------------------------------------------|");
			printDevelopmentCardsSet(player.getCurrentCards(DevelopmentCardTypes.VENTURECARD));}
		
		if(!player.getPermamentsEffect().isEmpty()){
		System.out.println("");
		System.out.println("Permanents effect owned:");
		for(String s: player.getPermamentsEffect())
			System.out.println(s);
	}
	

	}



	@Override
	public void printFinalRankings(MainClientView view) {
		// TODO solo punti vittoria per tutti i player nel match
		
	}
	

}
